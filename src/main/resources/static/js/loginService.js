(function () {
    var app = angular.module('myApp');

    app.service('loginService', function ($http, $rootScope, $localStorage, DataSvc) {
        var self = this;
        self.appUser = DataSvc.appUser;

        // self.init = function () {
        //     if ($localStorage['appSecurityDate']) {
        //         DataSvc.appUser = $localStorage['appSecurityDate'];
        //         $rootScope.$broadcast('LoginSuccessful');
        //     }
        // }

        self.logout = function () {
            DataSvc.appUser = {};
            delete $localStorage['appSecurityDate'];
            $http.defaults.headers.common['Authorization'] = '';
            $rootScope.$broadcast('LogoutSuccessful');
        };

        self.authenticate = function (email, password) {

            return $http({
                url: 'authenticate',
                method: 'POST',
                params: {
                    email: email,
                    password: password
                }
            }).then(function (response) {
                password = null;
                // checking if the token is available in the response
                if (response.data) {

                    // this.appUser.userId
                    DataSvc.appUser = response.data;

                    $localStorage['appSecurityDate'] = DataSvc.appUser;
                    $http.defaults.headers.common['Authorization'] = 'Bearer ' + DataSvc.appUser.token;
                    $rootScope.$broadcast('LoginSuccessful');

                    return true;
                } else {
                    self.logout();
                    return false;
                }
            });
        };

        self.register = function (user) {
            return $http.post('register', user)
                .then(function (response) {
                    return response.data;
                });
        };

        self.change = function (user, oldPassword) {
            return $http.get('/change/'
                + user.id + '/'
                + user.firstName + '/'
                + user.lastName + '/'
                + user.phone + '/'
                + user.email + '/'
                + user.pass + '/'
                + oldPassword)
                .then(function (response) {
                    return response.data;
                });
        };

    });
})();


