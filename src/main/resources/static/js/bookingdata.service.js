(function () {
    var app = angular.module("myApp");

    app.service("BookingDataSvc", function ($http) {
        var self = this;
        this.getBookings = function () {

            return $http.get("/booking")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getBookingById = function (bookingId) {

            return $http.get("/booking/" + bookingId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getBookingsByLocation = function (roomId) {

            return $http.get("/booking/room/" + roomId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.addBooking = function (bookingData) {

            return $http.post("/booking", bookingData)
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
        }

    });
})();