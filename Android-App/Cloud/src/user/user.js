
var StringConst = require('../common/string_const').StringConst;

/**
 * Returns object that holds/manipulates User Data.
 */
exports.User = function () {

    return {

        userID: null,
        email: null,
        password: null,

        user: function (userID,
                        email,
                        password) {
            this.userID = userID;
            this.email = email;
            this.password = password;
        }
    }
};
