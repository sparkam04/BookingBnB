(function () {

    var app = angular.module("myApp");
    app.controller('hotelCtrl', function (HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, PayDataSvc, UserDataSvc, DataSvc, $filter) {
        var self = this;

        this.init = function () {
            CountryDataSvc.getCountries()
                .then(function (data) {
                    self.countries = data;
                });

            self.pSselection = {ids: {}};
            PayDataSvc.getPaySystems()
                .then(function (data) {
                    self.paySystems = data;
                    return data;
                })
                .then(function (data) {

                    for (var i = 0; i < self.editedHotel.paySysIds.length; i++) {
                        self.pSselection.ids[self.editedHotel.paySysIds[i]] = true;
                    }
                });

            UserDataSvc.getUsersByRoleId(21)
                .then(function (data) {
                    self.users = data;
                });

            // self.country = DataSvc.country;
            // self.city = DataSvc.city;
            // self.location = DataSvc.location;
            self.hotel = DataSvc.hotel;
            self.editedHotel = DataSvc.editedHotel;
            self.newHotel = {
                "id": "",
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
            self.numRooms = 1;
            self.RoomNumStart = 1;
            self.RoomCost = 1000;
            self.numOfPlaces = 1;
            self.roomName = "Room";

            self.hasBathroom = true;
            self.hasTV = true;
            self.hasExtraBed = false;

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
                    self.cities = undefined;
                    self.selectCountry();
                })
            ;

        };

        this.getFullAddressByLocationId = function (locationId) {
            return {
                'country': self.country,
                'city': self.city,
                'location': self.location
            };
            // return {
            //     'country': self.country.name,
            //     'city': self.city.name,
            //     'location': self.location.streetAddress
            // };

        };

        this.checkAddress = function () {
            LocationDataSvc.getLocationByStreetAddress(self.newHotel.location)
                .then(function (response) {
                    self.isExistsAddress = self.newHotel.location.streetAddress === response.data.streetAddress;
                });
        };

        this.checkAddressEdit = function () {
            LocationDataSvc.getLocationByStreetAddress(self.editedHotel.fullAddess.location)
                .then(function (response) {
                    if (self.hotel.fullAddess.location.streetAddress === response.data.streetAddress) {
                        self.isExistsAddress = false;
                    }
                    else {
                        self.isExistsAddress = self.editedHotel.fullAddess.location.streetAddress === response.data.streetAddress;
                    }
                });
        };

        this.addPaysystem = function () {
            self.newHotel.paySysIds = [];
            self.newHotel.paySystems.forEach(function (paySys) {
                self.newHotel.paySysIds.push(paySys.id)
            });
        };

        this.editPaysystem = function () {
            self.editedHotel.paySysIds = [];
            for (var property in self.pSselection.ids) {
                // if (object.hasOwnProperty(property)) {
                if (self.pSselection.ids[property] === true) {
                    self.editedHotel.paySysIds.push(property);
                }
                // }
            }
        };

        this.updateHotel = function (hotelData) {
            if (hotelData.cInTime !== undefined && hotelData.cInTime !== null) {
                hotelData.checkInTime = $filter('date')(hotelData.cInTime, 'HH:mm:ss').toString();
            }
            if (hotelData.cOutTime !== undefined && hotelData.cOutTime !== null) {
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
                }).then(function () {
                LocationDataSvc.updateLocation(self.editedHotel.fullAddess.location)
            });
            hotelData.cInTime = undefined;
            hotelData.cOutTime = undefined;

        };

        this.addRoom1 = function () {
            for (i = 0; i < self.numRooms; i++) {
                var newRoom = {
                    "roomName": self.roomName,
                    "hotelId": self.hotel.id,
                    "roomNumber": self.RoomNumStart + i,
                    "numOfPlaces": self.numOfPlaces,
                    "cost": self.RoomCost,
                    "hasBathroom": self.hasBathroom,
                    "hasTV": self.hasTV,
                    "hasExtraBed": self.hasExtraBed,
                    "enabled": 'true',
                    "images": []
                };

                (function (newRoom_) {
                    RoomDataSvc.addRoom(newRoom_)
                })(newRoom);
            }
        };

        this.submitHotel = function () {
            self.newHotel.checkInTime = $filter('date')(self.newHotel.cInTime, 'HH:mm:ss').toString();
            self.newHotel.checkOutTime = $filter('date')(self.newHotel.cOutTime, 'HH:mm:ss').toString();
            self.newHotel.ownerId = self.newHotel.user.id;
            // self.newHotel.locationId = self.location.id;

            delete self.newHotel.cInTime;
            delete self.newHotel.cOutTime;
            delete self.newHotel.user;
            delete self.newHotel.paySystems;


            LocationDataSvc.addLocation(self.newHotel.location)
                .then(function () {
                    LocationDataSvc.getLocationByStreetAddress(self.newHotel.location)
                        .then(function (response) {
                            self.newHotel.locationId = response.data.id;
                        })
                        .then(function () {
                            HotelDataSvc.addHotel(self.newHotel)
                                .then(function (response) {
                                    if (response.status === 200) {
                                        self.creationResponse = "Success.";
                                        self.isCreated = true;
                                    } else {
                                        self.creationResponse = "Error"
                                    }
                                })
                        });
                })


        };
    });
})();