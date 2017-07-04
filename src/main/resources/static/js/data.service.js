(function () {
    var app = angular.module("myApp");

    app.service("DataSvc", function ($localStorage) {
        this.editedHotel = {};
        this.appUser = $localStorage['appSecurityDate'];
    });
})();