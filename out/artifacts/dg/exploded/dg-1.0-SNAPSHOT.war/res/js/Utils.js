var Util = {};
//检查是否为email类型
Util.checkEmail= function(_params) {
    var t = /^([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return t.test(_params);
}