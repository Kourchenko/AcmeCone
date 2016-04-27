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

var embeddedJavaScript = require('ejs');
var httpServer = require('http');
var fileSystem = require('fs'); // enables easy file reading
var bodyParser = require('body-parser'); // allows easy form submissions
var postgresDB = require('pg');
var html = fileSystem.readFileSync('index.html');
var port = process.env.PORT || 3000;

var BAD_HTTP_REQUEST_CODE = 400;
var CREATED_HTTP_REQUEST_CODE = 201;
var INTERNAL_SERVER_ERROR_HTTP_REQUEST_CODE = 500;
var OK_HTTP_REQUEST_CODE = 200;
var RESOURCE_NOT_FOUND_HTTP_REQUEST_CODE = 404;
var NO_CONTENT_HTTP_REQUEST_CODE = 204;

var log = function(entry) {
    fs.appendFileSync('/tmp/sample-app.log', new Date().toISOString() + ' - ' + entry + '\n');
};

// Handles the main page
var server = httpServer.createServer(function (req, res) {
    if (req.method === 'POST') {
        var body = '';

        req.on('data', function(chunk) {
            body += chunk;
        });

        req.on('end', function() {
            if (req.url === '/') {
                log('Received message: ' + body);
            } else if (req.url = '/scheduled') {
                log('Received task ' + req.headers['x-aws-sqsd-taskname'] + ' scheduled at ' + req.headers['x-aws-sqsd-scheduled-at']);
            }

            res.writeHead(200, 'OK', {'Content-Type': 'text/plain'});
            res.end();
        });
    } else {
        res.writeHead(200);
        res.write(html);
        res.end();
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
    next();cd
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

 var client = new postgresDB.Client(StringConst.DB_CONNECITON_STRING);
 client.connect(function(err) {
    if (err) {
        console.error('could not connect to postgres', err);
    }
 });


function ifNonexistentCreateDB(dbName, dbCreationQuery) {

    client.query( "SELECT COUNT(*) FROM " + dbName, function(err, result) {

        if (err) {

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
        }
    });
}

function createUserDB() {

    ifNonexistentCreateDB(StringConst.USER_DB, StringConst.createUserDB(StringConst.USER_DB));
    ifNonexistentCreateDB(StringConst.USER_TEST_DB, StringConst.createUserDB(StringConst.USER_TEST_DB));
}

createUserDB();

// Listen on port 3000, IP Defaults to 127.0.0.1
server.listen(port);

// Put a friendly message on the terminal
console.log('Server running at http://127.0.0.1:' + port + '/');
