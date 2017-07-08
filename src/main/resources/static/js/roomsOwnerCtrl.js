(function () {

    var app = angular.module("myApp");
    app.controller('roomsOwnerCtrl', function (HotelDataSvc, RoomDataSvc, DataSvc, $state) {
        var self = this;

        this.init = function () {

            HotelDataSvc.getHotelsByOwner(DataSvc.appUser.userId)
                .then(function (data) {
                    self.hotels = data;
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

        this.selectHotel = function (hotel_) {
            RoomDataSvc.getRoomsByHotel(self.hotel.id)
                .then(function (rooms_) {
                    self.rooms = rooms_;
                })


        };

        this.selectRoom = function (room_) {
                DataSvc.room = room_;
                // DataSvc.editedRoom = JSON.parse(JSON.stringify(room_));
                $state.go('editRoom');

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