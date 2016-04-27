/** 
 * File contains code for that functions as "main"
 * segment of execution for this web application
 **/

var StringConst = require('./src/common/string_const').StringConst;
var cookieParser = require('cookie-parser');
var express = require('express');

var User = require('./src/user/user.js').User;
var UserDB = require('./src/apis/userAPI.js').UserCredentials(StringConst.USER_DB);
var utils = require('./src/common/utils.js').Utils;

var httpServer = require('http');
var embeddedJavaScript = require('ejs');
var fileSystem = require('fs'); // enables easy file reading

var favicon = require('serve-favicon'); // allows use of a custom favicon
var bodyParser = require('body-parser'); // allows easy form submissions
var postgresDB = require('pg');

var app = express();
app.use(bodyParser.json());
app.use(cookieParser());
app.use(bodyParser.urlencoded({
  extended: true
}));

var router = express.Router();

router.use(function(req, res, next) {
    console.log("something is happening")
    next();
});


app.use('/api', router);
app.set('port',  process.env.PORT || 3000);
app.use(express.static(__dirname));

function displayPage(httpStatusCode, res, path, log, embeddedJavaScriptParams) {

    fileSystem.readFile(__dirname + path, 'utf-8', function(err, content) {
        if (err) {
            console.log(log + err);
        } else {
          res.status(httpStatusCode).send(embeddedJavaScript.render(content, embeddedJavaScriptParams));
        }
    });
}

/**
 * Handles main web page
 */
app.get('/', function (req, res) {

    displayPage(ConstVar.OK_HTTP_REQUEST_CODE, res, 'index.html', "Error serving index.html: ", {"user":""});
});

/**
 * Handles all other queries; responds with 404 page
 */
app.get('*', function(req, res) {

    displayPage(ConstVar.RESOURCE_NOT_FOUND_HTTP_REQUEST_CODE, res, '/public/templates/404.html', "Error serving 404.html: ", {user: ""});
});

var client = new postgresDB.Client(StringConst.DB_CONNECTION_STRING);
client.connect(function(err) {
  if(err) {
      console.error('could not connect to postgres', err);
  }
});

var app = express();
app.use(bodyParser.json());
app.use(cookieParser());
app.use(bodyParser.urlencoded({
  extended: true
}));

var router = express.Router();

router.use(function(req, res, next) {
    next();
});

app.use('/api', router);
app.use(express.static(__dirname));


router.route('/users')
    // API to create user
    .post(function(req, res) {


        // TODO - ensure request is valid (rate limit, etc)
        if (utils.isValidSignupRequest(req.query)) {

            var user = User();

            user.userID = req.query.userID;
            user.email = req.query.email;
            user.password = req.query.password;

            // TODO - allow user to populate remaining fields via profile page

            UserDB.insertNewUser(user, function(rowsTouched) {

                if (rowsTouched == 1) {
                    res.statusCode = CREATED_HTTP_REQUEST_CODE;
                    res.json({message: "Successfully created user with userID: " + user.userID});
                } else {
                    res.statusCode = INTERNAL_SERVER_ERROR_HTTP_REQUEST_CODE;
                    res.json({message: "Sign up request for user with userID: " + user.userID
                            + " has passed validation but could not be stored in the database."})
                }
            });

        } else {
            res.statusCode = BAD_HTTP_REQUEST_CODE;
            res.json({message: "Invalid sign up request for user with userID: " + req.body.userID
                                + ". Please ensure username, password, and email are valid"});
        }
    });

router.route('/users/:userID')

    // API to get user by userID
    .get(function(req, res) {

        // TODO - validate userID && also take password to verify user is legitimate
          UserDB.getUserByID(req.params.userID, function(user) {

            if (user) {
                res.statusCode = OK_HTTP_REQUEST_CODE;
                res.json({message:
                        " " + user.firstName + " "
                        + user.lastName + " was found. "
                        + " Last Logged in : "  + user.lastLoginTime
                        + " Logged in via: "    + user.lastLoginType
                        + " UserID: "           + user.userID
                        + " Email: "            + user.email});
            } else {
                res.statusCode = NO_CONTENT_HTTP_REQUEST_CODE;
                res.json({message: "Failed to find user with userID: " + req.params.userID})
            }
        });

        // TODO - ensure request is valid (rate limit, etc)

        // TODO - validate input and return user
    })


    // API to update user by userID
    .put(function(req, res) {

      UserDB.getUserByID(req.params.userID, function(user) {

        if (user) {

          var user = User();
          user.userID = req.query.userID;
          user.email = req.query.email;
          user.firstName = req.query.firstName;
          user.lastName = req.query.lastName;

          console.log(user.userID + " + "
                    + user.email + " + "
                    + " + " + user.firstName
                    + " + " + user.lastName);

          UserDB.updateUser(user, function(err) {
            res.json("Updated user.");
          });
        }
      });
    })


    // API to delete user by userID
    .delete(function(req, res) {
        // TODO - validate input and delete user

          UserDB.getUserByID(req.params.userID, function(user) {

            if (user) {
              UserDB.deleteUser(req.params.userID, function(rowsTouched) {
                if (rowsTouched == 1) {
                res.json({ message: "Deleted user."});
              } else {
                res.json({message: user.userID + "not in database for deletion."});
              }
              })
            } else {
              res.json({message: "User:" + user.userID + " not in database for deletion."});
            }
          });

        // TODO - ensure request is valid (rate limit, etc)

        // TODO - return SUCCESS/FAILURE accordingly
    });


/**
 * Function creates DB table if it currently don't exist.
 *
 * @param dbName suspect table name
 * @param dbCreationQuery creation query to be invoked if table doesn't exist
 */
function ifNonexistentCreateDB(dbName, dbCreationQuery) {

    client.query( "SELECT COUNT(*) FROM " + dbName, function(err, result) {

        if (err) {

            console.log("here" + err);

            // TODO - perform more sophisticated check

            var errWords = toString(err).split(" ");
            var naiveCheckPasses = true;

            // create table if naive check passes
            naiveCheckPasses &= errWords.indexOf("does") === -1;
            naiveCheckPasses &= errWords.indexOf("not") === -1;
            naiveCheckPasses &= errWords.indexOf("exist") === -1;

            if (naiveCheckPasses) {

                client.query(dbCreationQuery);
            }
        } else {
            console.log("result: " + result);
            console.dir(result);
        }
    });
}

function createUserDB() {

    console.log("trying to create");
    ifNonexistentCreateDB(ConstVar.USER_DB, ConstVar.createUserDB(ConstVar.USER_DB));
    ifNonexistentCreateDB(ConstVar.USER_TEST_DB, ConstVar.createUserDB(ConstVar.USER_TEST_DB));
    console.log("create complete");
}

function createEventDB() {
    ifNonexistentCreateDB(ConstVar.EVENT_DB, ConstVar.createEventDB(ConstVar.EVENT_DB));
    ifNonexistentCreateDB(ConstVar.EVENT_TEST_DB, ConstVar.createEventDB(ConstVar.EVENT_TEST_DB));
}

//createUserDB();
//createEventDB();

httpServer.createServer(app).listen(app.get('port'), function() {
	console.log('Express server listening on port ' + app.get('port'));
});