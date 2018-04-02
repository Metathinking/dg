app.service('tagService', function ($http) {
    this.initList = function (_index) {
        var request = {
            method: 'POST',
            url: context + '/admin/tag/list.rest',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                index: _index
            }
        };
        return $http(request);
    };
});