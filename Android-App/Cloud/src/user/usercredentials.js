/**
 * File contains code for the User database
 */

var StringConst = require('./../common/string_const').StringConst;
var User = require('./user').User;

var postgresDB = require('pg'); // postgres database module
var client = new postgresDB.Client(StringConst.DB_CONNECTION_STRING);


/**
 * Returns object that allows manipulation of LoginCredential database.
 *
 * @param tableName specifies if table or test-table will be used (separate to avoid data corruption during testing)
 */
exports.UserCredentials = function (tableName) {

    dbName = tableName; // set dbName (may be table or test-table name)

    /**
     * Function invocation connects to DB
     */
    (function connectClient () {
        client.connect(function(err) {
            if(err) {
                console.error('could not connect to postgres', err);
            }
        });
    })();

    return {

        insertNewUser: function(user, callback) {

            try {
                if (user) {

                    // TODO: add StringConst.FIRST_NAME/LAST again
                    client.query("INSERT INTO " + dbName + " ("
                        + StringConst.USER_ID  + ", "
                        + StringConst.EMAIL    + ", "
                        + StringConst.PASSWORD + ", "
                        + ") values($1, $2, $3)",
                        [user.userID, user.email, user.password],

                        function(err, result) {
                            if (err) {
                                console.log("Error in UserCredentials.insertNewUser(): " + err);
                                client.on('drain', cleint.end.bind(client));
                                callback(0);
                            } else {
                                callback(result.rowCount);
                                client.on('drain', client.end.bind(client));
                            }
                        });
                    
                }
            } catch (err) {
                console.log("Error in UserCredentials.insertNewUser(): " + err);
                callback(0); // 0 rows touched
            }
        },

        getUserByID: function(userID, callback) {

            try {
                if (userID) {
                    client.query( "SELECT * FROM " + dbName + " WHERE "
                        + StringConst.USER_ID + " = \'" + userID + "\'",

                        function(err, result) {
                            if (err) {
                                console.log("Error in UserCredentials.getUserByID(): " + err);
                                callback(null);
                            } else {

                                var user = User();

                                if (result.rowCount == 1) {

                                    user.userID = result.rows[0].userid;
                                    user.email = result.rows[0].email;
                                    user.password = result.rows[0].password;
                                    
                                }

                                callback(user);
                            }
                        });
                }
            } catch (err) {
                console.log("Error in UserCredentials.getUserByID(): " + err);
                callback(null);
            }
        },

        updateUser: function(user, callback) {

            try {
                if (user) {

                    client.query( " UPDATE " + dbName + " SET "
                        + StringConst.EMAIL + " = \'" + user.email + "\',"
                        + StringConst.PASSWORD + " = \'" + user.password + "\',"
                        + " WHERE " + StringConst.USER_ID + " = \'" + user.userID + "\'",

                        function(err, result) {
                            if (err) {
                                console.log("Error in UserCredentials.updateUser(): " + err);
                                callback(0);
                            } else {

                                callback(result.rowCount);
                            }
                        });
                }
            } catch (err) {
                console.log("Error in UserCredentials.updateUser(): " + err);
                callback(0);
            }
        },

        updateEmail: function(user, callback) {

          try {
            if (user) {

              client.query(" UPDATE " + dbName + " SET "
                + StringConst.EMAIL + " = \'" + user.email + "\'"
                + " WHERE " + StringConst.USER_ID + " = \'" + user.userID + "\'",

           function(err, result) {
               if (err) {
                   console.log("Error in UserCredentials.updateEmail()1: " + err);
                   callback(0);
               } else {

                   callback(result.rowCount);
               }
           });
          }
           } catch (err) {
             console.log("Error in UserCredentials.updateEmail()2: " + err);
             callback(0);
           }
        },

        updatefirstName: function(user, callback) {

          try {
            if (user) {
              console.log("Updating first name...");

              client.query(" UPDATE " + dbName + " SET "
                + StringConst.FIRST_NAME + " = \'" + user.firstName + "\'"
                + " WHERE " + StringConst.USER_ID + " = \'" + user.userID + "\'",

           function(err, result) {
               if (err) {
                   console.log("Error in UserCredentials.updatefirstName(): " + err);
                   callback(0);
               } else {

                   callback(result.rowCount);
               }
           });
          }
           } catch (err) {
             console.log("Error in UserCredentials.updatefirstName(): " + err);
             callback(0);
           }
        },


        deleteUser: function(userID, callback) {

            try {
                if (!userID) {
                    client.query( "DELETE FROM " + dbName + " WHERE " + StringConst.USER_ID
                        + " = \'" + userID + "\'",

                        function(err, result) {
                            if (err) {
                                console.log("!!Error in UserCredentials.deleteUser(): " + err);
                                callback(0);
                            } else {

                                callback(result.rowCount);
                            }
                        });
                }
            } catch (err) {
                console.log("Error in UserCredentials.deleteUser(): " + err);
                callback(0);
            }
        }
    }
};
