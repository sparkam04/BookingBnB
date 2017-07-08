(function () {

    var app = angular.module('myApp');
    app.controller('editRoomCtrl', function (UserDataSvc, RoomDataSvc, DataSvc, $timeout) {
        var self = this;

        this.init = function () {

            self.room = DataSvc.room;
        };
        this.init();

        this.submitRoom = function () {
            RoomDataSvc.updateRoom(self.room)
                .then(function (response) {
                    if (response.status === 200) {
                        self.updateResponse = "Room updated";
                        self.clearMessage();
                    } else {
                        self.updateResponse = "Update error";
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