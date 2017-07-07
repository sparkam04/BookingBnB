(function () {
    var app = angular.module("myApp");

    app.service("CityDataSvc", function ($http) {

        this.getCities = function () {

            return $http.get("/city")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getCityById = function (id) {

            return $http.get("/city/" + id)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getCitiesByCountry = function (countryId) {

            return $http.get("/city/country/" + countryId)
                .then(function (response) {
                    return response.data;
                });
        }
    });
})();