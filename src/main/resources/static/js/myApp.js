var app = angular.module('myApp', ['ui.router', 'ngStorage']);

// app.config(function ($routeProvider) {
//     $routeProvider
//         .when('/', {
//             templateUrl: 'partials/roomSearch.html',
//             controller: 'roomSearchCtrl',
//             controllerAs: 'bc'
//         })
//         .when('/details', {
//             templateUrl: 'partials/details.html',
//             controller: 'testCtrl',
//             controllerAs: 'tc'
//         })
//         .when('/editHotel', {
//             templateUrl: 'partials/editHotel.html',
//             controller: 'testCtrl',
//             controllerAs: 'tc'
//         })
//         .when('/test', {
//             templateUrl: 'partials/home.html',
//             controller: 'testCtrl',
//             controllerAs: 'tc'
//         })
//         .when('/booking', {
//             templateUrl: 'partials/booking.html',
//             controller: 'bookingCtrl',
//             controllerAs: 'bc'
//         })
//         .when('/hotelAdmin', {
//             templateUrl: 'partials/hotelAdmin.html',
//             controller: 'hotelCtrl',
//             controllerAs: 'hc'
//         })
//         .when('/editHotel_', {
//             templateUrl: 'partials/editHotel_.html',
//             controller: 'hotelCtrl',
//             controllerAs: 'hc'
//         })
//         .when('/addHotel', {
//             templateUrl: 'partials/addHotel.html',
//             controller: 'hotelCtrl',
//             controllerAs: 'hc'
//         })
//         .when('/bookingAdmin', {
//             templateUrl: 'partials/bookingAdmin.html',
//             controller: 'bookingSearchCtrl',
//             controllerAs: 'bc'
//         })
//         .when('/bookingDetails', {
//             templateUrl: 'partials/bookingDetails.html',
//             controller: 'bookingDetailsCtrl',
//             controllerAs: 'bd'
//         })
//         .when('/login', {
//             templateUrl: 'partials/login.html',
//             controller: 'loginCtrl',
//             controllerAs: 'lc'
//         })
//         // .when('navbar',{
//         //     abstract : true,
//         //     templateUrl: 'partials/navbar.html',
//         //     controller : 'navBarCntrl',
//         //     controllerAs: 'nbc'
//         // })
//         .otherwise('/')
//     ;
// });

app.config(function ($stateProvider, $urlRouterProvider) {

    // the ui router will redirect if a invalid state has come.
    $urlRouterProvider.otherwise('/');
    // parent view - navigation state
    $stateProvider
        .state('nav', {
            abstract: true,
            url: '',
            views: {
                'nav@': {
                    templateUrl: 'partials/navbar.html',
                    controller: 'navBarCtrl',
                    controllerAs: 'nbc'
                }
            }
        })
        .state('roomSearch', {
            parent: 'nav',
            url: '/',
            views: {
                'content@': {
                    templateUrl: 'partials/roomSearch.html',
                    controller: 'roomSearchCtrl',
                    controllerAs: 'bc'
                }
            }
        })
        .state('details', {
            parent: 'nav',
            url: '/details',
            views: {
                'content@': {
                    templateUrl: 'partials/details.html',
                    controller: 'testCtrl',
                    controllerAs: 'tc'
                }
            }
        })
        .state('editHotel', {
            parent: 'nav',
            url: '/editHotel',
            views: {
                'content@': {
                    templateUrl: 'partials/editHotel.html',
                    controller: 'testCtrl',
                    controllerAs: 'tc'
                }
            }
        })
        .state('test', {
            parent: 'nav',
            url: '/test',
            views: {
                'content@': {
                    templateUrl: 'partials/home.html',
                    controller: 'testCtrl',
                    controllerAs: 'tc'
                }
            }
        })
        .state('booking', {
            parent: 'nav',
            url: '/booking',
            views: {
                'content@': {
                    templateUrl: 'partials/booking.html',
                    controller: 'bookingCtrl',
                    controllerAs: 'bc'
                }
            }
        })
        .state('hotelAdmin', {
            parent: 'nav',
            url: '/hotelAdmin',
            views: {
                'content@': {
                    templateUrl: 'partials/hotelAdmin.html',
                    controller: 'hotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('editHotel_', {
            parent: 'nav',
            url: '/editHotel_',
            views: {
                'content@': {
                    templateUrl: 'partials/editHotel_.html',
                    controller: 'hotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('addHotel', {
            parent: 'nav',
            url: '/addHotel',
            views: {
                'content@': {
                    templateUrl: 'partials/addHotel.html',
                    controller: 'hotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('bookingAdmin', {
            parent: 'nav',
            url: '/bookingAdmin',
            views: {
                'content@': {
                    templateUrl: 'partials/bookingAdmin.html',
                    controller: 'bookingSearchCtrl',
                    controllerAs: 'bc'
                }
            }
        })
        .state('bookingDetails', {
            parent: 'nav',
            url: '/bookingDetails',
            views: {
                'content@': {
                    templateUrl: 'partials/bookingDetails.html',
                    controller: 'bookingDetailsCtrl',
                    controllerAs: 'bd'
                }
            }
        })
        .state('login', {
            parent: 'nav',
            url: '/login',
            views: {
                'content@': {
                    templateUrl: 'partials/login.html',
                    controller: 'loginCtrl',
                    controllerAs: 'lc'
                }
            }
        })
        .state('register', {
            parent: 'nav',
            url: '/register',
            views: {
                'content@': {
                    templateUrl: 'partials/register.html',
                    controller: 'registerCtrl',
                    controllerAs: 'rc'
                }
            }
        })
    ;
});
