(function () {
    var app = angular.module('myApp');

    app.service('CountryDataSvc', function ($http) {

    this.getCountries = function() {

         return $http.get('/country')
         .then(function(response) {
            return response.data;
         });
    };

        this.getCountryById = function(id) {

            return $http.get('/country/' + id)
                .then(function(response) {
                    return response.data;
                });
        }

    });
})();