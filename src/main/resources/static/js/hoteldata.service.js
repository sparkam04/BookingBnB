(function () {
    var app = angular.module("myApp");

    app.service("HotelDataSvc", function ($http) {

    this.getHotels = function() {

         return $http.get("/Hotel")
         .then(function(response) {
            return response.data;
         });
    }

    });
})();