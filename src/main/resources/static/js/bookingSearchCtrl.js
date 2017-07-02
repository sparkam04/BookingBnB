(function () {

    var app = angular.module("myApp");
    app.controller('bookingSearchCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, BookingDataSvc, DataSvc) {
        var self = this;
        this.minChkInDate = new Date();
        this.minChkOutDate = this.minChkInDate;
        this.init = function () {

            self.country = DataSvc.country;
            self.city = DataSvc.city;
            self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
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

        this.selectLocation = function () {
            HotelDataSvc.getHotelsByLocation(self.location.id)
                .then(function(data) {
                    self.hotels = data;
                });
        };

        this.selectHotel = function () {
            self.bookibgs = {};
        };

        this.selectBooking = function (booking_) {
            DataSvc.booking = booking_;
        };

        this.findBookings = function () {
            BookingDataSvc.getBookingsByDates(self.checkIn, self.checkOut)
                .then(function(data) {
                    self.bookings = data;
                });
        };
    });
})();