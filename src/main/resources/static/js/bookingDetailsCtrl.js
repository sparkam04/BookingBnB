(function () {

    var app = angular.module("myApp");
    app.controller('bookingDetailsCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, BookingDataSvc, DataSvc, StatusDataSvc) {
        var self = this;
        this.minChkInDate = new Date();
        this.minChkOutDate = this.minChkInDate;
        this.init = function () {

            self.country = DataSvc.country;
            self.city = DataSvc.city;
            self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
            self.booking = DataSvc.booking;

            // self.booking.cIn = new Date(self.booking.checkIn);
            // self.booking.cOut = new Date(self.booking.checkOut);
            CountryDataSvc.getCountries()
                .then(function(data) {
                    self.countries = data;
                });
            StatusDataSvc.getStatuses()
                .then(function (statuses) {
                    self.statuses = statuses;
                })
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

        this.setStatus = function () {
          self.booking.statusId = self.booking.status.id;
        };

        this.updateBooking = function () {
          BookingDataSvc.updateBooking(self.booking)
              .then(function (response) {
                  if (response.status === 200) {
                      self.resp = "Booking Status updated.";
                  } else {
                      self.resp = "Error"
                  }
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