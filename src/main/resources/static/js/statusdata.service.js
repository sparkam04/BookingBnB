(function () {
    var app = angular.module("myApp");

    app.service("StatusDataSvc", function ($http) {

        this.getStatuses = function () {

            return $http.get("/status")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getStatusById = function (Id) {

            return $http.get("/status/" + Id)
                .then(function (response) {
                    return response.data;
                });
        }
    });
})();