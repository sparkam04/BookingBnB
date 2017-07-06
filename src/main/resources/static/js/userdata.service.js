(function () {
    var app = angular.module("myApp");

    app.service("UserDataSvc", function ($http) {
        var self = this;
        this.getUsers = function () {

            return $http.get("/user")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getUserById = function (userId) {

            return $http.get("/user/" + userId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getUserByEmail = function (email) {

            return $http.get("/user/email/" + email + "/")
                .then(function (response) {
                    return response.data;
                },
                function (response) {
                    console.log(response);
                    return {"email":""};
                });
        };


        this.addUser = function (user) {

            return $http.post("/user", user)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    }
                );
        };

        this.updateUser = function (user) {

            return $http.put("/user", user)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    }
                );
        }
    });
})();