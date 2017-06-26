(function () {
    var app = angular.module("myApp");

    app.service("CityDataSvc", function ($http) {

    this.getCities = function() {

         return $http.get("/city")
         .then(function(response) {
            return response.data;
         });
    }

    });
})();