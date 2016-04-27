
/**
 * File contains constant strings as well as methods for generating DB Schema.
 */

exports.StringConst = {


    BAD_HTTP_REQUEST_CODE: 400,
    CREATED_HTTP_REQUEST_CODE: 201,
    INTERNAL_SERVER_ERROR_HTTP_REQUEST_CODE: 500,
    OK_HTTP_REQUEST_CODE: 200,
    RESOURCE_NOT_FOUND_HTTP_REQUEST_CODE: 404,
    NO_CONTENT_HTTP_REQUEST_CODE: 204,
    
    // database tables
    USER_DB: "User",
    USER_TEST_DB: "TestUser",

    // database columns
    USER_ID : "userID",
    EMAIL: "email",
    PASSWORD: "password",
    

    // TODO - healthConditions, location, emergencyContact, secretQuestion/Answer
    // TODO - data-related fields

    createUserDB : function(dbName) {
        return "CREATE TABLE " + dbName + "("
            + this.USER_ID + " TEXT, "
            + this.EMAIL + " TEXT, "
            + this.PASSWORD + " TEXT, "
            + "PRIMARY KEY( " + this.USER_ID + " ))";
    }
};
