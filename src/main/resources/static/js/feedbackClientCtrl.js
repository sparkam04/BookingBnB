(function () {

    var app = angular.module('myApp');
// Creating the Angular Controller
    app.controller('feedbackClientCtrl', function ($state, DataSvc, RatingDataSvs) {
        var self = this;
        // self.value = undefined;
        // self.comment = undefined;
        self.rating = {
            'id': 0,
            'bookingId': 0,
            'value': 0,
            'comment': ''
        };

        self.rating.bookingId = DataSvc.booking.id;
        self.rating.booking = DataSvc.booking;
        self.booking = DataSvc.booking;

        self.cancel = function () {
            $state.go('bookingClient');
        };

        self.send = function () {
            if (self.value1 && self.comment) {
                self.rating.value = self.value1;
                self.rating.comment = self.comment;
                RatingDataSvs.addRating(self.rating)
                    .then(function (response) {
                        if (response.status === 200)
                            $state.go('bookingClient');
                    })
            }
        }

    });
})();