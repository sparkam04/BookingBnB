(function () {

    var app = angular.module('myApp');

    app.controller('registerCtrl', function ($http, loginService, $state) {
        var self = this;
        self.user = {
            'id': '',
            'firstName': '',
            'lastName': '',
            'phone': '',
            'email': '',
            'pass': '',
            'roleId': ''
        };

        this.register = function () {

            if (self.firstName && self.lastName && self.phone && self.email && self.password && self.confirmPassword) {

                if (self.password === self.confirmPassword) {

                    self.user.firstName = self.firstName;
                    self.user.lastName = self.lastName;
                    self.user.phone = self.phone;
                    self.user.email = self.email;
                    self.user.pass = self.password;

                    loginService.register(self.user).then(function (result) {
                        self.message = result.message;
                        if (result.isRegistered === true) {
                            $state.go('login');
                        }
                    });
                } else { self.message = 'Password not matching'; }
            } else { self.message = 'Incorrect registration data!'; }

        };
    });
})();