(function () {
    var app = angular.module("myApp");

    app.service("RoleDataSvc", function ($http) {

        this.getRoles = function () {

            return $http.get("/role")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getRoleById = function (id) {

            return $http.get("/role/" + id)
                .then(function (response) {
                    return response.data;
                });
        };
    });
})();