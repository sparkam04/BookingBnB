(function () {

    var app = angular.module('myApp');
// Creating the Angular Controller
    app.controller('loginCtrl', function ($http, loginService, $window) {
        // this.password = '';
        // this.email = '';
        // this.message = '';
        var self = this;

        this.login = function () {
            if (self.email && self.password) {

                loginService.authenticate(self.email, self.password).then(function (isLogin) {
                    if (isLogin) {
                        self.password = '';
                        self.message = '';
                        $window.location.href = '/';
                    } else {
                        self.email = '';
                        self.password = '';
                        self.message = 'Authentication Failed!';
                    }
                });

            } else {
                self.message = 'Authentication Failed!';
            }

        };
    });
})();