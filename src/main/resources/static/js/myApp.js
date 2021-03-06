var app = angular.module('myApp', ['ui.router', 'ngStorage', 'ngRateIt'])

    .run(function (DataSvc, $rootScope, $state, $http, $localStorage) {

        if ($localStorage['appSecurityDate']) {
            DataSvc.appUser = $localStorage['appSecurityDate'];
            $http.defaults.headers.common['Authorization'] = 'Bearer ' + DataSvc.appUser.token;
        }
        $rootScope.$on('$stateChangeStart', function (event, toState, toParams, fromState, fromParams) {
            if (DataSvc.appUser && toState.data && toState.data.roles) {
                var hasAccess = false;
                for (var i = 0; i < toState.data.roles.length; i++) {
                    var role = toState.data.roles[i];
                    if (role === DataSvc.appUser.role) {
                        hasAccess = true;
                        break;
                    }
                }
                if (!hasAccess) {
                    event.preventDefault();
                    $state.go('access-denied');
                }
            }
        });
    });

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
            // data: {
            //     roles: ['USER']
            // },
            views: {
                'content@': {
                    templateUrl: 'partials/roomSearch.html',
                    controller: 'roomSearchCtrl',
                    controllerAs: 'bc'
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
        .state('bookingAuth', {
            parent: 'nav',
            url: '/bookingAuth',
            views: {
                'content@': {
                    templateUrl: 'partials/bookingAuth.html',
                    controller: 'bookingAuthCtrl',
                    controllerAs: 'bc'
                }
            }
        })
        .state('hotelAdmin', {
            parent: 'nav',
            url: '/hotelAdmin',
            data: {
                roles: ['SYSADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/hotelAdmin.html',
                    controller: 'hotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('editUserRole', {
            parent: 'nav',
            url: '/editUserRole',
            data: {
                roles: ['SYSADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/editUserRole.html',
                    controller: 'editUserRoleCtrl',
                    controllerAs: 're'
                }
            }
        })
        .state('hotelOwner', {
            parent: 'nav',
            url: '/hotelOwner',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/hotelOwner.html',
                    controller: 'hotelOwnerCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('ownerEditHotel', {
            parent: 'nav',
            url: '/ownerEditHotel',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/ownerEditHotel.html',
                    controller: 'ownerEditHotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('ownerAddHotel', {
            parent: 'nav',
            url: '/ownerAddHotel',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/ownerAddHotel.html',
                    controller: 'ownerEditHotelCtrl',
                    controllerAs: 'hc'
                }
            }
        })
        .state('roomsOwner', {
            parent: 'nav',
            url: '/roomsOwner',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/roomsOwner.html',
                    controller: 'roomsOwnerCtrl',
                    controllerAs: 'rc'
                }
            }
        })
        .state('editRoom', {
            parent: 'nav',
            url: '/editRoom',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/editRoom.html',
                    controller: 'editRoomCtrl',
                    controllerAs: 'rc'
                }
            }
        })
        .state('roomsAdmin', {
            parent: 'nav',
            url: '/roomsAdmin',
            data: {
                roles: ['SYSADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/roomsAdmin.html',
                    controller: 'roomsAdminCtrl',
                    controllerAs: 'rc'
                }
            }
        })
        .state('editRoomAdmin', {
            parent: 'nav',
            url: '/editRoomAdmin',
            data: {
                roles: ['SYSADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/editRoomAdmin.html',
                    controller: 'editRoomCtrl',
                    controllerAs: 'rc'
                }
            }
        })
        .state('bookingOwner', {
            parent: 'nav',
            url: '/bookingOwner',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/bookingOwner.html',
                    controller: 'bookingSearchOwnerCtrl',
                    controllerAs: 'bc'
                }
            }
        })
        .state('bookingDetailsOwner', {
            parent: 'nav',
            url: '/bookingDetailsOwner',
            data: {
                roles: ['ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/bookingDetailsOwner.html',
                    controller: 'bookingDetailsCtrl',
                    controllerAs: 'bd'
                }
            }
        })
        .state('editHotel_', {
            parent: 'nav',
            url: '/editHotel_',
            data: {
                roles: ['SYSADMIN']
            },
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
            data: {
                roles: ['SYSADMIN']
            },
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
            data: {
                roles: ['SYSADMIN']
            },
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
            data: {
                roles: ['SYSADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/bookingDetails.html',
                    controller: 'bookingDetailsCtrl',
                    controllerAs: 'bd'
                }
            }
        })
        .state('bookingClient', {
            parent: 'nav',
            url: '/bookingClient',
            data: {
                roles: ['USER']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/bookingClient.html',
                    controller: 'bookingSearchClientCtrl',
                    controllerAs: 'bcc'
                }
            }
        })
        .state('feedbackClient', {
            parent: 'nav',
            url: '/feedback',
            data: {
                roles: ['USER']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/feedbackClient.html',
                    controller: 'feedbackClientCtrl',
                    controllerAs: 'fcc'
                }
            }
        })
        .state('setting', {
            parent: 'nav',
            url: '/setting',
            data: {
                roles: ['USER', 'ADMIN']
            },
            views: {
                'content@': {
                    templateUrl: 'partials/settingClientOwner.html',
                    controller: 'settingClientOwnerCtrl',
                    controllerAs: 'scoc'
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
        .state('access-denied', {
            parent: 'nav',
            url: '/access-denied',
            views: {
                'content@': {
                    templateUrl: 'partials/access-denied.html',
                    controller: 'accessDeniedCtrl'
                }
            }
        })
    ;
});
