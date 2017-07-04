(function () {

    var app = angular.module('myApp');
    app.controller('testCtrl', function(HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, DataSvc, $filter, $timeout, $window) {
        var self = this;

        this.init = function () {
            self.country = DataSvc.country;
            self.city = DataSvc.city;
            self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
            self.editedHotel = DataSvc.editedHotel;

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
        };

        this.init();

        this.selectHotel = function (hotel_) {
            DataSvc.hotel = hotel_;
            DataSvc.hotel.fullAddess = this.getFullAddressByLocationId(hotel_.locationId);
        };

        this.selectCountry = function () {
            self.location = {id: 0};
        };

        this.selectCity = function () {
            self.location = {id: 0};
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
            if(hotelData.cInTime !== undefined) {
                hotelData.checkInTime = $filter('date')(hotelData.cInTime, 'HH:mm:ss').toString();
            }
            if(hotelData.cOutTime !== undefined) {
                hotelData.checkOutTime = $filter('date')(hotelData.cOutTime, 'HH:mm:ss').toString();
            }

            // console.log(JSON.stringify(hotelData.valueOf()));
            HotelDataSvc.updHotel(hotelData)
                .then(function (response) {
                    if (response.status === 200) {
                        self.resp = 'Success.';
                        self.clearMessage();
                    } else {
                        self.resp = 'Error'
                    }
                });
            hotelData.cInTime = undefined;
            hotelData.cOutTime = undefined;

        };

        this.clearMessage = function () {
            $timeout(function () {
                self.resp = undefined;
                $window.location.href = '/';
                // $route.reload();
                $window.route.reload();
            }, 3000)
        };

        this.toggleEditMode = function () {
            // this.editedHotel = Object.create(this.hotel);
            DataSvc.editedHotel = JSON.parse(JSON.stringify(DataSvc.hotel));
        };
    });
})();