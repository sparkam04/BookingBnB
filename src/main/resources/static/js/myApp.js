var app = angular.module('myApp', ["ngRoute"]);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "partials/roomSearch.html",
            controller : "roomSearchCtrl",
            controllerAs: 'bc'
        })
        .when("/details", {
            templateUrl: "partials/details.html",
            controller : "testCtrl",
            controllerAs: 'tc'
        })
        .when("/editHotel", {
            templateUrl: "partials/editHotel.html",
            controller : "testCtrl",
            controllerAs: 'tc'
        })
        .when("/test", {
            templateUrl: "partials/home.html",
            controller : "testCtrl",
            controllerAs: 'tc'

        })
        .when("/booking", {
            templateUrl: "partials/booking.html",
            controller : "bookingCtrl",
            controllerAs: 'bc'

        })
    ;
});