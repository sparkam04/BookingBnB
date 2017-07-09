(function () {
    var app = angular.module("myApp");

    app.service("RatingDataSvs", function ($http, HotelDataSvc) {
        this.getRatingByBooking = function (bookingId) {
            return $http.get('/rating/booking/' + bookingId)
                .then(function (response) {
                    // if (response.status === 500)
                    //     return undefined;
                    return response.data;
                });

        };

        this.addRating = function (rating) {

            return $http.post("/rating", rating)
                .then(
                    function (response) {
                        // success callback
                        HotelDataSvc.getRatingByHotelId(rating.booking.hotel.id)
                            .then(function (rating_) {
                                console.log("rating " + rating_);
                            });

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