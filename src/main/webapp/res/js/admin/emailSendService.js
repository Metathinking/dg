app.service('emailSendService', function ($http) {

    this.findEditEmailById = function (_id) {
        var req = {
            method: 'POST',
            url: context + '/admin/email/findById.rest?id=' + _id,
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
    //获取默认邮箱设置
    this.getDefault = function () {
        var req = {
            method: 'POST',
            url: context + '/admin/emailSetting/getDefault.rest',
            headers: {
                'Content-Type': 'application/json'
            }
        }
        return $http(req);
    }
    this.toRecord = function (emailSetting) {
        var emailRecord = {
            hostName: emailSetting.hostName,
            smtpPort: emailSetting.smtpPort,
            authenticationName: emailSetting.authenticationName,
            authenticationPassword: emailSetting.authenticationPassword,
            charset: emailSetting.charset,
            sendPerson: emailSetting.sendPerson,
        }
        return emailRecord;
    }

    this.send = function (data) {
        var req = {
            method: 'POST',
            url: context + '/admin/email/send.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: data
        }
        return $http(req);
    }
})