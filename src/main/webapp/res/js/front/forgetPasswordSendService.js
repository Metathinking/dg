app.service('forgetPasswordSendService', function ($http) {
    this.sendEmail = function (_email) {
        var req = {
            method: 'POST',
            url: context + '/sendEmailForPassword.rest?email=' + _email,
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
});
