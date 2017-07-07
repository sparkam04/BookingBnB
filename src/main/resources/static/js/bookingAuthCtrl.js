(function () {

    var app = angular.module('myApp');
    app.controller('bookingAuthCtrl', function (HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, RoomDataSvc, PayDataSvc, UserDataSvc, BookingDataSvc, DataSvc) {
        var self = this;

        this.init = function () {
            PayDataSvc.getPaySystems()
                .then(function (data) {
                    self.paySystems = data;
                });

            UserDataSvc.getUserById(DataSvc.appUser.userId)
                .then(function(data) {
                    self.user = data;
                });

            self.city = DataSvc.city;
            self.room = DataSvc.room;
            self.checkIn = DataSvc.checkin;
            self.checkOut = DataSvc.checkOut;
            self.totalCost = self.room.cost * (self.checkOut - self.checkIn) / (1000 * 3600 * 24);
            self.paySystem = {"id": 33, "name": "Visa"};
            self.customerMessage = "";
            self.numPersons = 1;
        };
        this.init();

        this.selectRoom = function (room_) {
            DataSvc.room = room_;
        };

        this.checkUserByEmail = function () {
            UserDataSvc.getUserByEmail(self.user.email)
                .then(function (oldUser) {
                    self.isExistsUser = self.user.email === oldUser.email;

                });
        };

        this.submitBooking = function () {
            cIn = new Date(self.checkIn);
            cOut = new Date(self.checkOut);

            cIn.setDate(cIn.getDate() + 1);
            cOut.setDate(cOut.getDate() + 1);

            RoomDataSvc.getFreeRoomsByDateCity(self.checkIn, self.checkOut, self.city.id)
                .then(function (data) {
                    self.rooms = data;
                    return data;
                })
                .then(function (data) {
                    var isFound = false;
                    for (i = 0, len = data.length; i < len; i++) {
                        _room = data[i];
                        if (_room.id === self.room.id) {
                            isFound = true;

                            BookingDataSvc.addBooking(
                                {
                                    // "id":"",
                                    "roomId": self.room.id,
                                    "userId": DataSvc.appUser.userId,
                                    "statusId": 30,
                                    "paySysId": self.paySystem.id,
                                    "code": new Date().toISOString().slice(0, 10) + "-" + Math.random().toString(36).substring(7),
                                    "numPersons": self.numPersons,
                                    "message": self.customerMessage,
                                    "checkIn": cIn.toISOString().slice(0, 10),
                                    "checkOut": cOut.toISOString().slice(0, 10),
                                    "paid": true
                                }
                            ).then(function (response) {
                                if (response.status === 200) {
                                    self.creationResponse = "Booking successfull. Check e-mail for details";
                                    self.isBooked = true;
                                } else {
                                    self.creationResponse = "Booking error.";
                                    self.isBooked = true;
                                }
                            });

                        }
                    }
                    if (!isFound) {
                        self.creationResponse = "Error. Room already booked.";
                        self.isBooked = true;
                    }
                });
        };

    });
})();