(function () {
    var app = angular.module("myApp");

    app.service("RoomDataSvc", function ($http, HotelDataSvc, LocationDataSvc) {
        var self = this;
        this.getRooms = function () {

            return $http.get("/room")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getRoomById = function (roomId) {

            return $http.get("/room/" + roomId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getRoomsByHotel = function (hotelId) {

            return $http.get("/room/hotel/" + hotelId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getFreeRoomsByDateCity = function (chkIn, chkOut, cityId) {
            cIn = new Date(chkIn);
            cOut = new Date(chkOut);

            cIn.setDate(cIn.getDate() + 1);
            cOut.setDate(cOut.getDate() + 1);

            return $http.get("/room/free/" + cIn.toISOString().slice(0, 10) + "/" + cOut.toISOString().slice(0, 10) + "/" + cityId)
                .then(function (response) {

                    return response.data;
                }).then(function (data) {
                    for (i = 0, len = data.length; i < len; i++) {
                        room = data[i];
                        (function (room) {
                            //todo
                            HotelDataSvc.getHotelById(room.hotelId)
                                .then(function (hotel) {
                                    room.hotel = hotel;
                                    LocationDataSvc.getLocationById(hotel.locationId)
                                        .then(function (loc) {
                                            room.hotel.location = loc;
                                        })
                                });
                        })(room);
                    }
                    return data;
                });
        };

        this.addRoom = function (roomData) {
            return $http.post("/room", roomData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        return response;
                    }
                );
        };

        this.updateRoom = function (roomData) {
            return $http.put("/room", roomData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        return response;
                    }
                );
        };

        this.deleteRoom = function (roomData) {

            return $http.delete("/room/"+ roomData.id)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        return response;
                    }
                );
        };
    });
})();