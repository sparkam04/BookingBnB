(function () {
    var app = angular.module("myApp");

    app.service("HotelDataSvc", function ($http) {

        this.getHotels = function () {

            return $http.get("/Hotel")
                .then(function (response) {
                    return response.data;
                });
        };

        this.updHotel = function (hotelData) {

            // console.log(JSON.stringify(hotelData));

            $http.put("/Hotel", hotelData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                    }
                );
        }

    });
})();