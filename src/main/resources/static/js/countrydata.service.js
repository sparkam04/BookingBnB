(function () {
    var app = angular.module("myApp");

    app.service("CountryDataSvc", function ($http) {

    this.getCountries = function() {

         return $http.get("/Country")
         .then(function(response) {
            return response.data;
         });
    }

    });
})();