(function () {

    var app = angular.module("myApp");
    app.controller('testCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, $filter) {
        this.editMode = false;
        this.failure = undefined;
        this.country = {};
        this.city = {};
        this.location = {id: 0};
        this.hotel = {};
        this.editedHotel = {};


        var self = this;
        HotelDataSvc.getHotels()
        .then(function(data) {
            self.hotels = data;
        });

        CountryDataSvc.getCountries()
            .then(function(data) {
                self.countries = data;
            });

        CityDataSvc.getCities()
            .then(function(data) {
                self.cities = data;
            });

        LocationDataSvc.getLocations()
            .then(function(data) {
                self.locations = data;
            });

        this.selectHotel = function (index) {
            this.hotel = this.hotels[index];
        };

        this.selectCountry = function () {
            this.location = {id: 0};
        };

        this.selectCity = function () {
            this.location = {id: 0};
        };

        this.selectLocation = function () {
        };

        this.updateHotel = function (hotelData) {
            hotelData.checkInTime = $filter('date')(hotelData.cInTime, 'HH:mm:ss').toString();
            hotelData.checkOutTime = $filter('date')(hotelData.cOutTime, 'HH:mm:ss').toString();

            console.log(JSON.stringify(hotelData.valueOf()));
            HotelDataSvc.updHotel(hotelData);
        };

        this.toggleEditMode = function () {
            this.editMode = !this.editMode;
            // this.editedHotel = Object.create(this.hotel);
            this.editedHotel = JSON.parse(JSON.stringify(this.hotel));
        };
    });
})();