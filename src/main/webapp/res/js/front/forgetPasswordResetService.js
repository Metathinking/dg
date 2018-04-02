app.service('forgetPasswordResetService', function ($http) {
    this.resetPassword = function (_data) {
        var req = {
            method: 'POST',
            url: context + '/resetPasswordForForget.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: _data
        }
        return $http(req);
    }
});
