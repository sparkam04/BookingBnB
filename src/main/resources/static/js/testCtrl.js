(function () {

    var app = angular.module("myApp");
    app.controller('testCtrl', function(HotelDataSvc) {
        this.editMode = false;
        this.failure = undefined;
        this.hotel = {};
        var self = this;
        HotelDataSvc.getHotels()
        .then(function(data) {
            self.hotels = data;
        })

        this.selectHotel = function (index) {
            this.hotel = this.hotels[index];
        };
        this.toggleEditMode = function () {
            this.editMode = !this.editMode;
        };
    });
})();