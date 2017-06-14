(function () {

    var app = angular.module("myApp");
    app.controller('testCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc) {
        this.editMode = false;
        this.failure = undefined;
        this.country = {};
        this.city = {};
        this.location = {};
        this.hotel = {};


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
        };

        this.selectCyty = function () {
        };

        this.selectLocation = function () {
        };

        this.toggleEditMode = function () {
            this.editMode = !this.editMode;
        };
    });
})();