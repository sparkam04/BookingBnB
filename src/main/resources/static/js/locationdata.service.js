(function () {
    var app = angular.module("myApp");

    app.service("LocationDataSvc", function ($http) {

        this.getLocations = function () {

            return $http.get("/location")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getLocationById = function (id) {

            return $http.get("/location/" + id)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getLocationsByCity = function (cityId) {

            return $http.get("/location/city/" + cityId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.addLocation = function (locationData) {

            return $http.post("/location", locationData)
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
                )
                ;
        };

        this.updateLocation = function (locationData) {

            return $http.put("/location", locationData)
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
                )
                ;
        };

        this.getLocationByStreetAddress = function (locationData) {

            return $http.post("/location/streetAddress", locationData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        response.data = {"streetAddress":""};
                        return response;
                    }
                )
                ;
        };

        this.deleteLocation = function (locationId) {

            return $http.delete("/location/"+ locationId)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        return response;
                    }
                );
        };

    });
})();