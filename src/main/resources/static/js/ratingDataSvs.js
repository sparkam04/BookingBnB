(function () {
    var app = angular.module("myApp");

    app.service("RatingDataSvs", function ($http) {
        this.getRatingByBooking = function (bookingId) {
            return $http.get('/rating/booking/' + bookingId)
                .then(function (response) {
                    // if (response.status === 500)
                    //     return undefined;
                    return response.data;
                });

        }

        this.addRating = function (rating) {

            return $http.post("/rating", rating)
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