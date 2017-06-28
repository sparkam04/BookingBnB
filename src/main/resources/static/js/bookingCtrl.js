(function () {

    var app = angular.module("myApp");
    app.controller('bookingCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, PayDataSvc, DataSvc, $filter, $timeout, $window) {
        var self = this;

        this.init = function () {
            PayDataSvc.getPaySystems()
                .then(function(data) {
                    self.paySystems = data;
                });
            self.room = DataSvc.room;
            self.checkIn = DataSvc.checkin;
            self.checkOut = DataSvc.checkOut;
            self.totalCost = self.room.cost * (self.checkOut - self.checkIn) / (1000 * 3600 * 24);
        };
        this.init();

        this.selectRoom = function (room_) {
            DataSvc.room = room_;
        };

        this.clearMessage = function () {
            $timeout(function () {
                self.resp = undefined;
                $window.location.href = '/';
                // $route.reload();
                $window.route.reload();
            }, 3000)
        };
    });
})();