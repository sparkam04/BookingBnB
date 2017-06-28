(function () {

    var app = angular.module("myApp");
    app.controller('roomSearchCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, DataSvc) {
        var self = this;
        this.minChkInDate = new Date();
        this.minChkOutDate = this.minChkInDate;
        this.init = function () {

            self.country = DataSvc.country;
            self.city = DataSvc.city;
            self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
            self.editedHotel = DataSvc.editedHotel;
            CountryDataSvc.getCountries()
                .then(function(data) {
                    self.countries = data;
                });
        };
        this.init();

        this.setMinChkOut = function () {
            self.minChkOutDate = new Date(self.checkIn);
            self.minChkOutDate.setDate(self.minChkOutDate.getDate() + 2);
            self.checkOut = new Date(self.checkIn);
            self.checkOut.setDate(self.checkOut.getDate() + 1);
        };

        this.findRoom = function () {
            RoomDataSvc.getFreeRoomsByDateCity(self.checkIn, self.checkOut, self.city.id)
                .then(function(data) {
                    self.rooms = data;
                });
        };

        this.selectRoom = function (room_) {
            DataSvc.room = room_;
            DataSvc.checkin = self.checkIn;
            DataSvc.checkOut = self.checkOut;
        };

        this.selectCountry = function () {
            this.location = {id: 0};
            self.hotels = {};
            CityDataSvc.getCitiesByCountry(self.country.id)
                .then(function(data) {
                    self.cities = data;
                });
        };

        this.selectCity = function () {
            self.hotels = {};
            LocationDataSvc.getLocationsByCity(self.city.id)
                .then(function(data) {
                    self.locations = data;
                });
        };
    });
})();