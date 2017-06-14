var app = angular.module('myApp', ["ngRoute"]);

app.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "partials/home.html"
    })
    .when("/details", {
        templateUrl : "partials/details.html"
    })
    ;
});