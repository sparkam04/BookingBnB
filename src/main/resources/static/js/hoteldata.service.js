(function () {
    var app = angular.module("myApp");

    app.service("HotelDataSvc", function ($http, LocationDataSvc) {
        var self = this;
        this.getHotels = function () {

            return $http.get("/hotel")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getHotelById = function (hotelId) {

            return $http.get("/hotel/" + hotelId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getHotelsByLocation = function (locationId) {

            return $http.get("/hotel/location/" + locationId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.updHotel = function (hotelData) {

            // console.log(JSON.stringify(hotelData));

            return $http.put("/hotel", hotelData)
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

        this.addHotel = function (hotelData) {

            return $http.post("/hotel", hotelData)
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

        this.deleteHotel = function (hotelData) {

            // return $http.delete("/hotel", hotelData)
            return $http.delete("/hotel/"+ hotelData.id)
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
                )
                .then(function () {
                    LocationDataSvc.deleteLocation(hotelData.locationId)
                });
        };

    });
})();