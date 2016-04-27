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
