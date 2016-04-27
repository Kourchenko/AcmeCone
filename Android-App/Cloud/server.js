/**
 * File contains code for that functions as "main"
 * segment of execution for the Acme Cone application
 **/

 /**
 * TODO: Fix POST method
 * Error: undefined is not a function
 *      when using Postman to POST
 **/

 // AcmeCone


 var express = require('express');
 var app = express();
 var bodyParser = require('body-parser');
 var cookieParser = require('cookie-parser');
 var postgresDB = require('pg');
 var http = require('http');

var User = require('./src/user/user.js').User;
var StringConst = require('./src/common/string_const.js').StringConst;
var UserDB = require('./src/user/usercredentials.js').UserCredentials(StringConst.USER_DB);
var Utils = require('./src/common/utils.js').Utils

var BAD_HTTP_REQUEST_CODE = 400;
var CREATED_HTTP_REQUEST_CODE = 201;
var INTERNAL_SERVER_ERROR_HTTP_REQUEST_CODE = 500;
var OK_HTTP_REQUEST_CODE = 200;
var RESOURCE_NOT_FOUND_HTTP_REQUEST_CODE = 404;
var NO_CONTENT_HTTP_REQUEST_CODE = 204;

app.use(bodyParser.json());
app.use(cookieParser());
app.use(bodyParser.urlencoded({
    extended: true
}));

var port = process.env.PORT || 3000,
    http = require('http'),
    fs = require('fs'),
    html = fs.readFileSync('index.html');

var log = function(entry) {
    fs.appendFileSync('/tmp/sample-app.log', new Date().toISOString() + ' - ' + entry + '\n');
};

var server = http.createServer(function (req, res) {
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

// API Code
var router = express.Router();
router.use(function (req, res, next) {
    next();
});

app.use('/api', router);
app.set('port', process.env.PORT || 3000);
app.use(express.static(__dirname));

router.route('/users')
    // API to create user
    .post(function (req, res) {
        console.log(req);
        if (Utils.isValidSignupRequest(req.query)) {

            var user = User();
            user.userID = req.query.userID;
            user.email = req.query.email;
            user.password = req.query.password;
            
            UserDB.insertNewUser(user, function(rowsTouched) {
                console.log("Inserting new user...");

                if (rowsTouched == 1) {
                    res.statusCode = CREATED_HTTP_REQUEST_CODE;
                    res.json({message: "Succesfully created user with userID: " + user.userID});
                } else {
                    res.statusCode = INTERNAL_SERVER_ERROR_HTTP_REQUEST_CODE;
                    res.json({message: "Signup request for user: " + user.userID
                        + " has passed validation but could not be stored in database."});
                }
            });
        } else {
            res.statuscode = BAD_HTTP_REQUEST_CODE;
            res.json({message: "Invalid singup request for user: " + req.query.userID
                + ". Please ensure username, password, and email are valid"});
        }
    });

router.route('/userID/:userID')
        // API to get user by userID
    .get(function(req, res) {

        // TODO - validate userID && also take password to verify user is legitimate
          UserDB.getUserByID(req.query.userID, function(user) {

            if (user) {
                res.statusCode = OK_HTTP_REQUEST_CODE;
                res.json({message:
                        " " + user.firstName + " " + user.lastName + " was found. "
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

          console.log(user.userID + " + " + user.email);

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


var client = new postgresDB.Client(StringConst.DB_CONNECTION_STRING);
client.connect(function(err) {
    if (err) {
        console.error('could not connect to postgres', err);
    }
});

function ifNonexistentCreateDb(dbName, dbCreationQuery) {
    client.query( "SELECT COUNT(*) FROM " + dbName, function (err, result) {
        if (err) {
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

function creatUserDB() {
    ifNonexistentCreateDB(StringConst.USER_DB, StringConst.createUserDB(StringConst.USER_ID));
    ifNonexistentCreateDB(StringConst.USER_TEST_DB, StringConst.createUserDB(StringConst.USER_TEST_DB));
}
app.listen(port);

// Listen on port 3000, IP defaults to 127.0.0.1

// Put a friendly message on the terminal
console.log('Server running at http://127.0.0.1:' + port + '/');
