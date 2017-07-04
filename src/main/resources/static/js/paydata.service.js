(function () {
    var app = angular.module('myApp');

    app.service('PayDataSvc', function ($http) {

        this.getPaySystems = function () {

            return $http.get('/paysystem')
                .then(function (response) {
                    return response.data;
                });
        };

        this.getPaySystemById = function (Id) {

            return $http.get('/paysystem/' + Id)
                .then(function (response) {
                    return response.data;
                });
        }
    });
})();