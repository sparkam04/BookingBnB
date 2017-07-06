(function () {

    var app = angular.module('myApp');
    app.controller('navBarCtrl', function (DataSvc, $scope, loginService) {
        var self = this;

        self.appUser = DataSvc.appUser;

        $scope.$on('LoginSuccessful', function () {
            self.appUser = DataSvc.appUser;
        });

        $scope.$on('LogoutSuccessful', function () {
            self.appUser = undefined;
        });

        self.logout = function () {
            loginService.logout();
        };
    });
})();