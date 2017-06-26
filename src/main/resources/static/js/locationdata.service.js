(function () {
    var app = angular.module("myApp");

    app.service("LocationDataSvc", function ($http) {

    this.getLocations = function() {

         return $http.get("/location")
         .then(function(response) {
            return response.data;
         });
    }

    });
})();