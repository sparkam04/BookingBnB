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
        .when("/hotelAdmin", {
            templateUrl: "partials/hotelAdmin.html",
            controller : "hotelCtrl",
            controllerAs: 'hc'
        })
        .when("/editHotel_", {
            templateUrl: "partials/editHotel_.html",
            controller : "hotelCtrl",
            controllerAs: 'hc'
        })
        .when("/addHotel", {
            templateUrl: "partials/addHotel.html",
            controller : "hotelCtrl",
            controllerAs: 'hc'
        })
        .when("/bookingAdmin", {
            templateUrl: "partials/bookingAdmin.html",
            controller : "bookingSearchCtrl",
            controllerAs: 'bc'
        })
        .when("/bookingDetails", {
            templateUrl: "partials/bookingDetails.html",
            controller : "bookingDetailsCtrl",
            controllerAs: 'bd'
        })
        .otherwise('/')
    ;
});