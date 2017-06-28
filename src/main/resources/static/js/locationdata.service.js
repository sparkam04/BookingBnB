(function () {
    var app = angular.module("myApp");

    app.service("LocationDataSvc", function ($http) {

        this.getLocations = function () {

            return $http.get("/location")
                .then(function (response) {
                    return response.data;
                });
        };
        this.getLocationsByCity = function (cityId) {

            return $http.get("/location/city/" + cityId)
                .then(function (response) {
                    return response.data;
                });
        }

    });
})();