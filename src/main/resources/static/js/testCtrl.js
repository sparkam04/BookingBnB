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

        this.selectHotel = function (hotel_) {
            this.hotel = hotel_;
            this.hotel.fullAddess = this.getFullAddressByLocationId(this.hotel.locationId);
        };

        this.selectCountry = function () {
            this.location = {id: 0};
        };

        this.selectCity = function () {
            this.location = {id: 0};
        };

        this.selectLocation = function () {
        };

        this.getFullAddressByLocationId = function (locationId) {
            var _location = ($filter('filter')(this.locations, {'id': locationId}))[0];
            var _city = ($filter('filter')(this.cities, {'id': _location.cityId}))[0];
            var _country = ($filter('filter')(this.countries, {'id': _city.countryId}))[0];

            return {
                'country': _country,
                'city': _city,
                'location': _location
            };

        };

        this.updateHotel = function (hotelData) {
            if(hotelData.cInTime === undefined) {
                var t1 = hotelData.checkInTime.split(':');
                var d1 = new Date();
                d1.setHours(t1[0]);
                d1.setMinutes(t1[1]);
                d1.setSeconds(0);
                d1.setMilliseconds(0);
                hotelData.cInTime = new Date(d1);
            }
            if(hotelData.cOutTime === undefined) {
                var t2 = hotelData.checkOutTime.split(':');
                var d2 = new Date();
                d2.setHours(t2[0]);
                d2.setMinutes(t2[1]);
                d2.setSeconds(0);
                d2.setMilliseconds(0);
                hotelData.cOutTime = new Date(d2);
            }

            hotelData.checkInTime = $filter('date')(hotelData.cInTime, 'HH:mm:ss').toString();
            hotelData.checkOutTime = $filter('date')(hotelData.cOutTime, 'HH:mm:ss').toString();

            // console.log(JSON.stringify(hotelData.valueOf()));
            HotelDataSvc.updHotel(hotelData);
        };

        this.toggleEditMode = function () {
            this.editMode = !this.editMode;
            // this.editedHotel = Object.create(this.hotel);
            this.editedHotel = JSON.parse(JSON.stringify(this.hotel));
        };
    });
})();