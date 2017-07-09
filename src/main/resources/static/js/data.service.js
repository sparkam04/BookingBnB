(function () {
    var app = angular.module("myApp");

    app.service("DataSvc", function ($localStorage, $http) {
        this.editedHotel = {};

        // this.appUser = $localStorage['appSecurityDate'];
        //
        // $http.defaults.headers.common['Authorization'] = 'Bearer ' + this.appUser.token;

    });
})();