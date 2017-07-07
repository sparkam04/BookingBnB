(function () {

    var app = angular.module('myApp');
    app.controller('editUserRoleCtrl', function (UserDataSvc, RoleDataSvc, $timeout) {
        var self = this;

        this.init = function () {
            RoleDataSvc.getRoles()
                .then(function (data) {
                    self.roles = data;
                });

        };
        this.init();

        this.checkUserByEmail = function () {
            UserDataSvc.getUserByEmail(self.user.email)
                .then(function (oldUser) {

                    if (self.user.email === oldUser.email) {
                        self.user = oldUser;
                        self.isFound = true;

                        RoleDataSvc.getRoleById(self.user.roleId)
                            .then(function (data) {
                                self.role = data;
                            })
                    } else {
                        self.user.firstName = undefined;
                        self.user.lastName = undefined;
                        self.user.phone = undefined;
                        self.user.pass = undefined;
                        self.user.roleId = undefined;

                        self.isFound = false;
                    }
                })
            ;
        };

        this.updateUser = function () {
            self.user.roleId = self.role.id;
            UserDataSvc.updateUser(self.user)
                .then(function (response) {
                    if (response.data === true) {
                        self.updateResponse = "User role updated";
                        self.clearMessage();
                    }
                })
        };

        this.clearMessage = function () {
            $timeout(function () {
                self.updateResponse = undefined;
            }, 3000)
        };

    });
})();