(function () {

    var app = angular.module("myApp");
    app.controller('roomsAdminCtrl', function (CountryDataSvc, CityDataSvc, LocationDataSvc, HotelDataSvc, RoomDataSvc, DataSvc, $state) {
        var self = this;

        this.init = function () {
            CountryDataSvc.getCountries()
                .then(function (data) {
                    self.countries = data;
                });

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

        this.selectHotel = function () {
            RoomDataSvc.getRoomsByHotel(self.hotel.id)
                .then(function (rooms_) {
                    self.rooms = rooms_;
                })


        };

        this.selectRoom = function (room_) {
                DataSvc.room = room_;
                // DataSvc.editedRoom = JSON.parse(JSON.stringify(room_));
                $state.go('editRoomAdmin');

        };

        this.deleteRoom = function (room_) {
            RoomDataSvc.deleteRoom(room_)
                .then(function () {
                    self.selectHotel(self.hotel)
                    // self.init();
                })
            ;
        };

        this.addRoom = function () {
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
                        .then(function () {
                            self.selectHotel(self.hotel)
                        });
                })(newRoom);
            }
        };

    });
})();