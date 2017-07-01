(function () {

    var app = angular.module("myApp");
    app.controller('hotelCtrl', function (HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, PayDataSvc, UserDataSvc, DataSvc, $filter) {
        var self = this;

        this.init = function () {
            CountryDataSvc.getCountries()
                .then(function (data) {
                    self.countries = data;
                });

            PayDataSvc.getPaySystems()
                .then(function (data) {
                    self.paySystems = data;
                });

            UserDataSvc.getUsers()
                .then(function (data) {
                    self.users = data;
                });

            self.country = DataSvc.country;
            self.city = DataSvc.city;
            self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
            self.editedHotel = DataSvc.editedHotel;
            self.newHotel = {
                "id":"",
                "ownerId": "",
                "locationId": "",
                "hotelRating": "",
                "hasWifi": false,
                "hasShuttle": false,
                "hasSmoking": false,
                "hasParking": false,
                "hasConditioning": false,
                "hasPets": false,
                "hasPool": false,
                "hasKitchen": false,
                "hasBreakfast": false,
                "hotelName": "",
                "phone": "",
                "description": "",
                "checkInTime": "",
                "checkOutTime": "",
                "images": [],
                "paySysIds": [],
                "preorder": false
            };


        };
        this.init();

        this.selectCountry = function () {
            self.location = {id: 0};
            self.hotels = undefined;
            CityDataSvc.getCitiesByCountry(self.country.id)
                .then(function (data) {
                    self.cities = data;
                });
        };

        this.selectCity = function () {
            self.hotels = undefined;
            LocationDataSvc.getLocationsByCity(self.city.id)
                .then(function (data) {
                    self.locations = data;
                });
        };

        this.selectLocation = function () {
            HotelDataSvc.getHotelsByLocation(self.location.id)
                .then(function (data) {
                    self.hotels = data;
                });
        };

        this.selectHotel = function (hotel_) {
            hotel_.fullAddess = this.getFullAddressByLocationId(hotel_.locationId);
            DataSvc.hotel = hotel_;
            DataSvc.editedHotel = JSON.parse(JSON.stringify(hotel_));
        };

        this.deleteHotel = function (hotel_) {
            HotelDataSvc.deleteHotel(hotel_)
                .then(function () {
                    self.selectLocation();
                });

        };

        this.getFullAddressByLocationId = function (locationId) {

            return {
                'country': self.country.name,
                'city': self.city.name,
                'location': self.country.streetAddress
            };

        };

        this.addPaysystem = function () {
            self.newHotel.paySysIds = [];
            self.newHotel.paySystems.forEach(function (paySys) {
                self.newHotel.paySysIds.push(paySys.id)
            });
        };

        this.updateHotel = function (hotelData) {
            if (hotelData.cInTime !== undefined) {
                hotelData.checkInTime = $filter('date')(hotelData.cInTime, 'HH:mm:ss').toString();
            }
            if (hotelData.cOutTime !== undefined) {
                hotelData.checkOutTime = $filter('date')(hotelData.cOutTime, 'HH:mm:ss').toString();
            }

            // console.log(JSON.stringify(hotelData.valueOf()));
            HotelDataSvc.updHotel(hotelData)
                .then(function (response) {
                    if (response.status === 200) {
                        self.resp = "Success.";
                    } else {
                        self.resp = "Error"
                    }
                });
            hotelData.cInTime = undefined;
            hotelData.cOutTime = undefined;

        };

        this.submitHotel = function () {
            self.newHotel.checkInTime = $filter('date')(self.newHotel.cInTime, 'HH:mm:ss').toString();
            self.newHotel.checkOutTime = $filter('date')(self.newHotel.cOutTime, 'HH:mm:ss').toString();
            self.newHotel.ownerId = self.newHotel.user.id;
            self.newHotel.locationId = self.location.id;

            delete self.newHotel.cInTime;
            delete self.newHotel.cOutTime;
            delete self.newHotel.user;
            delete self.newHotel.paySystems;

            HotelDataSvc.addHotel(self.newHotel)
                .then(function (response) {
                    if (response.status === 200) {
                        self.creationResponse = "Success.";
                        self.isCreated = true;
                    } else {
                        self.creationResponse = "Error"
                    }
                })
        };
    });
})();