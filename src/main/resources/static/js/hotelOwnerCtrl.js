(function () {

    var app = angular.module("myApp");
    app.controller('hotelOwnerCtrl', function (HotelDataSvc, CountryDataSvc, CityDataSvc, LocationDataSvc, DataSvc, $state) {
        var self = this;

        this.init = function () {

            HotelDataSvc.getHotelsByOwner(DataSvc.appUser.userId)
                .then(function (data) {
                    self.hotels = data;
                });
        };

        this.init();

        this.selectHotel = function (hotel_) {

            self.getFullAddress(hotel_).then(function (data) {
                DataSvc.hotel = data;
                DataSvc.editedHotel = JSON.parse(JSON.stringify(data));
            }).then(function () {
                $state.go('ownerEditHotel');
            });

        };

        this.deleteHotel = function (hotel_) {
            HotelDataSvc.deleteHotel(hotel_)
                .then(function () {
                    self.init();
                })
            ;
        };

        this.getFullAddress = function (hotel_) {
            hotel_.fullAddess = {};

            return LocationDataSvc.getLocationById(hotel_.locationId)
                .then(function (data) {
                    hotel_.fullAddess.location = data;
                    return hotel_
                })
                .then(function (hotel_) {
                    return CityDataSvc.getCityById(hotel_.fullAddess.location.cityId)
                        .then(function (data) {
                            hotel_.fullAddess.city = data;
                            return hotel_;
                        });
                })
                .then(function (hotel_) {
                    return CountryDataSvc.getCountryById(hotel_.fullAddess.city.countryId)
                        .then(function (data) {
                            hotel_.fullAddess.country = data;
                            return hotel_;
                        });

                });
        };
    });
})();