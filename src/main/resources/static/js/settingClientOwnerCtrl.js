(function () {

    var app = angular.module('myApp');

    app.controller('settingClientOwnerCtrl', function ($http, DataSvc, loginService) {
        var self = this;

        self.user = {
            'id': 0,
            'firstName': '',
            'lastName': '',
            'phone': '',
            'email': '',
            'pass': '',
            'roleId': 0
        };

        self.firstName = DataSvc.appUser.firstName;
        self.lastName = DataSvc.appUser.lastName;
        self.phone = DataSvc.appUser.phone;
        self.email = DataSvc.appUser.email;


        this.change = function () {

            if (self.firstName && self.lastName && self.phone && self.email && self.password && self.confirmPassword && self.oldPassword) {

                if (self.password === self.confirmPassword) {

                    self.user.id = DataSvc.appUser.userId;
                    self.user.firstName = self.firstName;
                    self.user.lastName = self.lastName;
                    self.user.phone = self.phone;
                    self.user.email = self.email;
                    self.user.pass = self.password;

                    loginService.change(self.user, self.oldPassword).then(function (result) {
                        self.message = result.message;
                    });

                } else {
                    self.message = 'New password not matching';
                }
            } else {
                self.message = 'Incorrect change data';
            }

        };

    });
})();




