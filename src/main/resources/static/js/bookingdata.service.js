(function () {
    var app = angular.module("myApp");

    app.service("BookingDataSvc", function ($http, RatingDataSvs, RoomDataSvc, HotelDataSvc, UserDataSvc, PayDataSvc, StatusDataSvc) {
        var self = this;
        this.getBookings = function () {

            return $http.get("/booking")
                .then(function (response) {
                    return response.data;
                });
        };

        this.getBookingById = function (bookingId) {

            return $http.get("/booking/" + bookingId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getBookingsByLocation = function (roomId) {

            return $http.get("/booking/room/" + roomId)
                .then(function (response) {
                    return response.data;
                });
        };

        this.getBookingsByDates = function (chkIn, chkOut) {
            cIn = new Date(chkIn);
            cOut = new Date(chkOut);

            cIn.setDate(cIn.getDate() + 1);
            cOut.setDate(cOut.getDate() + 1);

            return $http.get("/booking/date/" + cIn.toISOString().slice(0, 10) + "/" + cOut.toISOString().slice(0, 10))
                .then(function (response) {
                    return response.data;
                })
                .then(function (data) {
                    for (i = 0, len = data.length; i < len; i++) {
                        booking = data[i];
                        (function (booking) {
                            RoomDataSvc.getRoomById(booking.roomId)
                                .then(function (room) {
                                    booking.room = room;

                                    booking.cIn = new Date(booking.checkIn);
                                    booking.cOut = new Date(booking.checkOut);
                                    booking.price = booking.room.cost * (booking.cOut - booking.cIn) / (1000 * 3600 * 24);

                                    return room;
                                })
                                .then(function (room) {

                                    HotelDataSvc.getHotelById(room.hotelId)
                                        .then(function (hotel) {
                                            booking.hotel = hotel;
                                        })
                                });

                            UserDataSvc.getUserById(booking.userId)
                                .then(function (user) {
                                    booking.user = user;
                                });
                            PayDataSvc.getPaySystemById(booking.paySysId)
                                .then(function (paySys) {
                                    booking.paySystem = paySys;
                                });
                            StatusDataSvc.getStatusById(booking.statusId)
                                .then(function (status) {
                                    booking.status = status;
                                });

                        })(booking);
                    }
                    return data;
                })
                ;
        };

        this.getBookingsByDatesHotel = function (chkIn, chkOut, hotelId) {
            cIn = new Date(chkIn);
            cOut = new Date(chkOut);

            cIn.setDate(cIn.getDate() + 1);
            cOut.setDate(cOut.getDate() + 1);

            return $http.get("/booking/date/" + cIn.toISOString().slice(0, 10) + "/" + cOut.toISOString().slice(0, 10) + "/hotel/" + hotelId)
                .then(function (response) {
                    return response.data;
                })
                .then(function (data) {
                    for (i = 0, len = data.length; i < len; i++) {
                        booking = data[i];
                        (function (booking) {
                            RoomDataSvc.getRoomById(booking.roomId)
                                .then(function (room) {
                                    booking.room = room;

                                    booking.cIn = new Date(booking.checkIn);
                                    booking.cOut = new Date(booking.checkOut);
                                    booking.price = booking.room.cost * (booking.cOut - booking.cIn) / (1000 * 3600 * 24);

                                    return room;
                                })
                                .then(function (room) {

                                    HotelDataSvc.getHotelById(room.hotelId)
                                        .then(function (hotel) {
                                            booking.hotel = hotel;
                                        })
                                });

                            UserDataSvc.getUserById(booking.userId)
                                .then(function (user) {
                                    booking.user = user;
                                });
                            PayDataSvc.getPaySystemById(booking.paySysId)
                                .then(function (paySys) {
                                    booking.paySystem = paySys;
                                });
                            StatusDataSvc.getStatusById(booking.statusId)
                                .then(function (status) {
                                    booking.status = status;
                                });

                        })(booking);
                    }
                    return data;
                })
                ;
        };

        this.getBookingsByDatesHotelClientStatus = function (chkIn, chkOut, clientId, hotelId, statusId) {
            cIn = new Date(chkIn);
            cOut = new Date(chkOut);

            cIn.setDate(cIn.getDate() + 1);
            cOut.setDate(cOut.getDate() + 1);

            return $http({
                url: '/booking/getall/date/client/hotel/status',
                method: 'POST',
                params: {
                    from: cIn.toISOString().slice(0, 10),
                    to: cOut.toISOString().slice(0, 10),
                    clientId: clientId,
                    hotelId: hotelId,
                    statusId: statusId
                }
            })
            // .get("/booking/date/" + cIn.toISOString().slice(0, 10) + "/" + cOut.toISOString().slice(0, 10) + "/hotel/" + hotelId)
                .then(function (response) {
                    return response.data;
                })
                .then(function (data) {
                    for (i = 0, len = data.length; i < len; i++) {
                        booking = data[i];
                        (function (booking) {
                            RoomDataSvc.getRoomById(booking.roomId)
                                .then(function (room) {
                                    booking.room = room;

                                    booking.cIn = new Date(booking.checkIn);
                                    booking.cOut = new Date(booking.checkOut);
                                    booking.price = booking.room.cost * (booking.cOut - booking.cIn) / (1000 * 3600 * 24);

                                    return room;
                                })
                                .then(function (room) {

                                    HotelDataSvc.getHotelById(room.hotelId)
                                        .then(function (hotel) {
                                            booking.hotel = hotel;
                                            return hotel;
                                        })
                                        .then(function (hotel) {
                                            UserDataSvc.getUserById(hotel.ownerId)
                                                .then(function (user) {
                                                    booking.user = user;
                                                });
                                        })
                                });


                            PayDataSvc.getPaySystemById(booking.paySysId)
                                .then(function (paySys) {
                                    booking.paySystem = paySys;
                                });
                            StatusDataSvc.getStatusById(booking.statusId)
                                .then(function (status) {
                                    booking.status = status;
                                });
                            RatingDataSvs.getRatingByBooking(booking.id)
                                .then(function (rating) {
                                    booking.rating = rating[0];
                                })

                        })(booking);
                    }
                    return data;
                })
                ;
        };

        this.getBookingsByClient = function (clientId) {
            return $http.get('/booking/client/'+ clientId)
            // .get("/booking/date/" + cIn.toISOString().slice(0, 10) + "/" + cOut.toISOString().slice(0, 10) + "/hotel/" + hotelId)
                .then(function (response) {
                    return response.data;
                })
                .then(function (data) {
                    for (i = 0, len = data.length; i < len; i++) {
                        booking = data[i];
                        (function (booking) {
                            RoomDataSvc.getRoomById(booking.roomId)
                                .then(function (room) {
                                    booking.room = room;

                                    booking.cIn = new Date(booking.checkIn);
                                    booking.cOut = new Date(booking.checkOut);
                                    booking.price = booking.room.cost * (booking.cOut - booking.cIn) / (1000 * 3600 * 24);

                                    return room;
                                })
                                .then(function (room) {

                                    HotelDataSvc.getHotelById(room.hotelId)
                                        .then(function (hotel) {
                                            booking.hotel = hotel;
                                            return hotel;
                                        })
                                        .then(function (hotel) {
                                            UserDataSvc.getUserById(hotel.ownerId)
                                                .then(function (user) {
                                                    booking.user = user;
                                                });
                                        })
                                });


                            PayDataSvc.getPaySystemById(booking.paySysId)
                                .then(function (paySys) {
                                    booking.paySystem = paySys;
                                });
                            StatusDataSvc.getStatusById(booking.statusId)
                                .then(function (status) {
                                    booking.status = status;
                                });
                            RatingDataSvs.getRatingByBooking(booking.id)
                                .then(function (rating) {
                                    booking.rating = rating[0];
                                })

                        })(booking);
                    }
                    return data;
                })
                ;
        };

        this.addBooking = function (bookingData) {

            return $http.post("/booking", bookingData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    }
                )
                ;
        };

        this.updateBooking = function (bookingData) {

            return $http.put("/booking", bookingData)
                .then(
                    function (response) {
                        // success callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    },
                    function (response) {
                        // failure callback
                        console.log(response);
                        // this.window.alert(response.status);
                        return response;
                    }
                )
                ;
        }

    });
})();