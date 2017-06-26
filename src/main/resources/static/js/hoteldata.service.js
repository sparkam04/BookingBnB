(function () {
    var app = angular.module("myApp");

    app.service("HotelDataSvc", function ($http) {
        var self = this;
        this.getHotels = function () {

            return $http.get("/hotel")
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
                )
            ;
        }

    });
})();