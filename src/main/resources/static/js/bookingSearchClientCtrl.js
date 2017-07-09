(function () {

    var app = angular.module("myApp");
    app.controller('bookingSearchClientCtrl', function (StatusDataSvc, HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, BookingDataSvc, DataSvc) {
        var self = this;

        this.minChkInDate = new Date();
        this.minChkOutDate = this.minChkInDate;

        HotelDataSvc.getHotelsByClient(DataSvc.appUser.userId)
            .then(function (data) {
                self.hotels = data;
            });

        StatusDataSvc.getStatuses()
            .then(function (data) {
                self.statuses = data;
            });

        BookingDataSvc.getBookingsByClient(DataSvc.appUser.userId)
            .then(function (date) {
                self.bookings = date;
            })


        this.setMinChkOut = function () {
            self.minChkOutDate = new Date(self.checkIn);
            self.minChkOutDate.setDate(self.minChkOutDate.getDate() + 2);
            self.checkOut = new Date(self.checkIn);
            self.checkOut.setDate(self.checkOut.getDate() + 1);
        };


        this.selectBooking = function (booking) {
            DataSvc.booking = booking;
        };

        this.findBookings = function () {
            if (self.checkIn && self.checkOut && DataSvc.appUser.userId && self.hotel.id && self.status.id)
                BookingDataSvc.getBookingsByDatesHotelClientStatus(self.checkIn, self.checkOut,
                    DataSvc.appUser.userId, self.hotel.id, self.status.id)
                    .then(function (data) {
                        self.bookings = data;
                    });
        };
    });
})();