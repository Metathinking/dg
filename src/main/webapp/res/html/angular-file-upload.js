/*
 angular-file-upload v2.3.3
 https://github.com/nervgh/angular-file-upload
 */

!function (e, t) {
    "object" == typeof exports && "object" == typeof module ? module.exports = t() : "function" == typeof define && define.amd ? define([], t) : "object" == typeof exports ? exports["angular-file-upload"] = t() : e["angular-file-upload"] = t()
}(this, function () {
    return function (e) {
        function t(n) {
            if (o[n])return o[n].exports;
            var r = o[n] = {exports: {}, id: n, loaded: !1};
            return e[n].call(r.exports, r, r.exports, t), r.loaded = !0, r.exports
        }

        var o = {};
        return t.m = e, t.c = o, t.p = "", t(0)
    }([function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        var r = o(1), i = n(r), s = o(2), a = n(s), l = o(3), u = n(l), p = o(4), c = n(p), f = o(5), d = n(f), h = o(6), y = n(h), m = o(7), _ = n(m), v = o(8), g = n(v), F = o(9), b = n(F), O = o(10), C = n(O), I = o(11), U = n(I), w = o(12), A = n(w);
        angular.module(i["default"].name, []).value("fileUploaderOptions", a["default"]).factory("FileUploader", u["default"]).factory("FileLikeObject", c["default"]).factory("FileItem", d["default"]).factory("FileDirective", y["default"]).factory("FileSelect", _["default"]).factory("FileDrop", g["default"]).factory("FileOver", b["default"]).directive("nvFileSelect", C["default"]).directive("nvFileDrop", U["default"]).directive("nvFileOver", A["default"]).run(["FileUploader", "FileLikeObject", "FileItem", "FileDirective", "FileSelect", "FileDrop", "FileOver", function (e, t, o, n, r, i, s) {
            e.FileLikeObject = t, e.FileItem = o, e.FileDirective = n, e.FileSelect = r, e.FileDrop = i, e.FileOver = s
        }])
    }, function (e, t) {
        e.exports = {name: "angularFileUpload"}
    }, function (e, t) {
        "use strict";
        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = {
            url: "/",
            alias: "file",
            headers: {},
            queue: [],
            progress: 0,
            autoUpload: !1,
            removeAfterUpload: !1,
            method: "POST",
            filters: [],
            formData: [],
            queueLimit: Number.MAX_VALUE,
            withCredentials: !1,
            disableMultipart: !1
        }
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i(e, t, o, n, i, s, a) {
            var m = n.File, _ = n.FormData, v = function () {
                function n(t) {
                    r(this, n);
                    var o = l(e);
                    u(this, o, t, {
                        isUploading: !1,
                        _nextIndex: 0,
                        _failFilterIndex: -1,
                        _directives: {select: [], drop: [], over: []}
                    }), this.filters.unshift({
                        name: "queueLimit",
                        fn: this._queueLimitFilter
                    }), this.filters.unshift({name: "folder", fn: this._folderFilter})
                }

                return n.prototype.addToQueue = function (e, t, o) {
                    var n = this, r = this.isArrayLikeObject(e) ? e : [e], i = this._getFilters(o), l = this.queue.length, u = [];
                    p(r, function (e) {
                        var o = new s(e);
                        if (n._isValidFile(o, i, t)) {
                            var r = new a(n, e, t);
                            u.push(r), n.queue.push(r), n._onAfterAddingFile(r)
                        } else {
                            var l = i[n._failFilterIndex];
                            n._onWhenAddingFileFailed(o, l, t)
                        }
                    }), this.queue.length !== l && (this._onAfterAddingAll(u), this.progress = this._getTotalProgress()), this._render(), this.autoUpload && this.uploadAll()
                }, n.prototype.removeFromQueue = function (e) {
                    var t = this.getIndexOfItem(e), o = this.queue[t];
                    o.isUploading && o.cancel(), this.queue.splice(t, 1), o._destroy(), this.progress = this._getTotalProgress()
                }, n.prototype.clearQueue = function () {
                    for (; this.queue.length;)this.queue[0].remove();
                    this.progress = 0
                }, n.prototype.uploadItem = function (e) {
                    var t = this.getIndexOfItem(e), o = this.queue[t], n = this.isHTML5 ? "_xhrTransport" : "_iframeTransport";
                    o._prepareToUploading(), this.isUploading || (this._onBeforeUploadItem(o), o.isCancel || (o.isUploading = !0, this.isUploading = !0, this[n](o), this._render()))
                }, n.prototype.cancelItem = function (e) {
                    var t = this, o = this.getIndexOfItem(e), n = this.queue[o], r = this.isHTML5 ? "_xhr" : "_form";
                    n && (n.isCancel = !0, n.isUploading ? n[r].abort() : !function () {
                        var e = [void 0, 0, {}], o = function () {
                            t._onCancelItem.apply(t, [n].concat(e)), t._onCompleteItem.apply(t, [n].concat(e))
                        };
                        i(o)
                    }())
                }, n.prototype.uploadAll = function () {
                    var e = this.getNotUploadedItems().filter(function (e) {
                        return !e.isUploading
                    });
                    e.length && (p(e, function (e) {
                        return e._prepareToUploading()
                    }), e[0].upload())
                }, n.prototype.cancelAll = function () {
                    var e = this.getNotUploadedItems();
                    p(e, function (e) {
                        return e.cancel()
                    })
                }, n.prototype.isFile = function (e) {
                    return this.constructor.isFile(e)
                }, n.prototype.isFileLikeObject = function (e) {
                    return this.constructor.isFileLikeObject(e)
                }, n.prototype.isArrayLikeObject = function (e) {
                    return this.constructor.isArrayLikeObject(e)
                }, n.prototype.getIndexOfItem = function (e) {
                    return f(e) ? e : this.queue.indexOf(e)
                }, n.prototype.getNotUploadedItems = function () {
                    return this.queue.filter(function (e) {
                        return !e.isUploaded
                    })
                }, n.prototype.getReadyItems = function () {
                    return this.queue.filter(function (e) {
                        return e.isReady && !e.isUploading
                    }).sort(function (e, t) {
                        return e.index - t.index
                    })
                }, n.prototype.destroy = function () {
                    var e = this;
                    p(this._directives, function (t) {
                        p(e._directives[t], function (e) {
                            e.destroy()
                        })
                    })
                }, n.prototype.onAfterAddingAll = function (e) {
                }, n.prototype.onAfterAddingFile = function (e) {
                }, n.prototype.onWhenAddingFileFailed = function (e, t, o) {
                }, n.prototype.onBeforeUploadItem = function (e) {
                }, n.prototype.onProgressItem = function (e, t) {
                }, n.prototype.onProgressAll = function (e) {
                }, n.prototype.onSuccessItem = function (e, t, o, n) {
                }, n.prototype.onErrorItem = function (e, t, o, n) {
                }, n.prototype.onCancelItem = function (e, t, o, n) {
                }, n.prototype.onCompleteItem = function (e, t, o, n) {
                }, n.prototype.onCompleteAll = function () {
                }, n.prototype._getTotalProgress = function (e) {
                    if (this.removeAfterUpload)return e || 0;
                    var t = this.getNotUploadedItems().length, o = t ? this.queue.length - t : this.queue.length, n = 100 / this.queue.length, r = (e || 0) * n / 100;
                    return Math.round(o * n + r)
                }, n.prototype._getFilters = function (e) {
                    if (!e)return this.filters;
                    if (h(e))return e;
                    var t = e.match(/[^\s,]+/g);
                    return this.filters.filter(function (e) {
                        return -1 !== t.indexOf(e.name)
                    })
                }, n.prototype._render = function () {
                    t.$$phase || t.$apply()
                }, n.prototype._folderFilter = function (e) {
                    return !(!e.size && !e.type)
                }, n.prototype._queueLimitFilter = function () {
                    return this.queue.length < this.queueLimit
                }, n.prototype._isValidFile = function (e, t, o) {
                    var n = this;
                    return this._failFilterIndex = -1, t.length ? t.every(function (t) {
                        return n._failFilterIndex++, t.fn.call(n, e, o)
                    }) : !0
                }, n.prototype._isSuccessCode = function (e) {
                    return e >= 200 && 300 > e || 304 === e
                }, n.prototype._transformResponse = function (e, t) {
                    var n = this._headersGetter(t);
                    return p(o.defaults.transformResponse, function (t) {
                        e = t(e, n)
                    }), e
                }, n.prototype._parseHeaders = function (e) {
                    var t, o, n, r = {};
                    return e ? (p(e.split("\n"), function (e) {
                        n = e.indexOf(":"), t = e.slice(0, n).trim().toLowerCase(), o = e.slice(n + 1).trim(), t && (r[t] = r[t] ? r[t] + ", " + o : o)
                    }), r) : r
                }, n.prototype._headersGetter = function (e) {
                    return function (t) {
                        return t ? e[t.toLowerCase()] || null : e
                    }
                }, n.prototype._xhrTransport = function (e) {
                    var t, o = this, n = e._xhr = new XMLHttpRequest;
                    if (e.disableMultipart ? t = e._file : (t = new _, p(e.formData, function (e) {
                            p(e, function (e, o) {
                                t.append(o, e)
                            })
                        }), t.append(e.alias, e._file, e.file.name)), "number" != typeof e._file.size)throw new TypeError("The file specified is no longer valid");
                    n.upload.onprogress = function (t) {
                        var n = Math.round(t.lengthComputable ? 100 * t.loaded / t.total : 0);
                        o._onProgressItem(e, n)
                    }, n.onload = function () {
                        var t = o._parseHeaders(n.getAllResponseHeaders()), r = o._transformResponse(n.response, t), i = o._isSuccessCode(n.status) ? "Success" : "Error", s = "_on" + i + "Item";
                        o[s](e, r, n.status, t), o._onCompleteItem(e, r, n.status, t)
                    }, n.onerror = function () {
                        var t = o._parseHeaders(n.getAllResponseHeaders()), r = o._transformResponse(n.response, t);
                        o._onErrorItem(e, r, n.status, t), o._onCompleteItem(e, r, n.status, t)
                    }, n.onabort = function () {
                        var t = o._parseHeaders(n.getAllResponseHeaders()), r = o._transformResponse(n.response, t);
                        o._onCancelItem(e, r, n.status, t), o._onCompleteItem(e, r, n.status, t)
                    }, n.open(e.method, e.url, !0), n.withCredentials = e.withCredentials, p(e.headers, function (e, t) {
                        n.setRequestHeader(t, e)
                    }), n.send(t)
                }, n.prototype._iframeTransport = function (e) {
                    var t = this, o = y('<form style="display: none;" />'), n = y('<iframe name="iframeTransport' + Date.now() + '">'), r = e._input;
                    e._form && e._form.replaceWith(r), e._form = o, r.prop("name", e.alias), p(e.formData, function (e) {
                        p(e, function (e, t) {
                            var n = y('<input type="hidden" name="' + t + '" />');
                            n.val(e), o.append(n)
                        })
                    }), o.prop({
                        action: e.url,
                        method: "POST",
                        target: n.prop("name"),
                        enctype: "multipart/form-data",
                        encoding: "multipart/form-data"
                    }), n.bind("load", function () {
                        var o = "", r = 200;
                        try {
                            o = n[0].contentDocument.body.innerHTML
                        } catch (i) {
                            r = 500
                        }
                        var s = {response: o, status: r, dummy: !0}, a = {}, l = t._transformResponse(s.response, a);
                        t._onSuccessItem(e, l, s.status, a), t._onCompleteItem(e, l, s.status, a)
                    }), o.abort = function () {
                        var i, s = {status: 0, dummy: !0}, a = {};
                        n.unbind("load").prop("src", "javascript:false;"), o.replaceWith(r), t._onCancelItem(e, i, s.status, a), t._onCompleteItem(e, i, s.status, a)
                    }, r.after(o), o.append(r).append(n), o[0].submit()
                }, n.prototype._onWhenAddingFileFailed = function (e, t, o) {
                    this.onWhenAddingFileFailed(e, t, o)
                }, n.prototype._onAfterAddingFile = function (e) {
                    this.onAfterAddingFile(e)
                }, n.prototype._onAfterAddingAll = function (e) {
                    this.onAfterAddingAll(e)
                }, n.prototype._onBeforeUploadItem = function (e) {
                    e._onBeforeUpload(), this.onBeforeUploadItem(e)
                }, n.prototype._onProgressItem = function (e, t) {
                    var o = this._getTotalProgress(t);
                    this.progress = o, e._onProgress(t), this.onProgressItem(e, t), this.onProgressAll(o), this._render()
                }, n.prototype._onSuccessItem = function (e, t, o, n) {
                    e._onSuccess(t, o, n), this.onSuccessItem(e, t, o, n)
                }, n.prototype._onErrorItem = function (e, t, o, n) {
                    e._onError(t, o, n), this.onErrorItem(e, t, o, n)
                }, n.prototype._onCancelItem = function (e, t, o, n) {
                    e._onCancel(t, o, n), this.onCancelItem(e, t, o, n)
                }, n.prototype._onCompleteItem = function (e, t, o, n) {
                    e._onComplete(t, o, n), this.onCompleteItem(e, t, o, n);
                    var r = this.getReadyItems()[0];
                    return this.isUploading = !1, d(r) ? void r.upload() : (this.onCompleteAll(), this.progress = this._getTotalProgress(), void this._render())
                }, n.isFile = function (e) {
                    return m && e instanceof m
                }, n.isFileLikeObject = function (e) {
                    return e instanceof s
                }, n.isArrayLikeObject = function (e) {
                    return c(e) && "length" in e
                }, n.inherit = function (e, t) {
                    e.prototype = Object.create(t.prototype), e.prototype.constructor = e, e.super_ = t
                }, n
            }();
            return v.prototype.isHTML5 = !(!m || !_), v.isHTML5 = v.prototype.isHTML5, v
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = i;
        var s = o(1), a = (n(s), angular), l = a.copy, u = a.extend, p = a.forEach, c = a.isObject, f = a.isNumber, d = a.isDefined, h = a.isArray, y = a.element;
        i.$inject = ["fileUploaderOptions", "$rootScope", "$http", "$window", "$timeout", "FileLikeObject", "FileItem"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i() {
            return function () {
                function e(t) {
                    r(this, e);
                    var o = u(t), n = o ? t.value : t, i = p(n) ? "FakePath" : "Object", s = "_createFrom" + i;
                    this[s](n)
                }

                return e.prototype._createFromFakePath = function (e) {
                    this.lastModifiedDate = null, this.size = null, this.type = "like/" + e.slice(e.lastIndexOf(".") + 1).toLowerCase(), this.name = e.slice(e.lastIndexOf("/") + e.lastIndexOf("\\") + 2)
                }, e.prototype._createFromObject = function (e) {
                    this.lastModifiedDate = l(e.lastModifiedDate), this.size = e.size, this.type = e.type, this.name = e.name
                }, e
            }()
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = i;
        var s = o(1), a = (n(s), angular), l = a.copy, u = a.isElement, p = a.isString
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i(e, t) {
            return function () {
                function o(e, n, i) {
                    r(this, o);
                    var s = c(n), a = s ? p(n) : null, f = s ? null : n;
                    u(this, {
                        url: e.url,
                        alias: e.alias,
                        headers: l(e.headers),
                        formData: l(e.formData),
                        removeAfterUpload: e.removeAfterUpload,
                        withCredentials: e.withCredentials,
                        disableMultipart: e.disableMultipart,
                        method: e.method
                    }, i, {
                        uploader: e,
                        file: new t(n),
                        isReady: !1,
                        isUploading: !1,
                        isUploaded: !1,
                        isSuccess: !1,
                        isCancel: !1,
                        isError: !1,
                        progress: 0,
                        index: null,
                        _file: f,
                        _input: a
                    }), a && this._replaceNode(a)
                }

                return o.prototype.upload = function () {
                    try {
                        this.uploader.uploadItem(this)
                    } catch (e) {
                        this.uploader._onCompleteItem(this, "", 0, []), this.uploader._onErrorItem(this, "", 0, [])
                    }
                }, o.prototype.cancel = function () {
                    this.uploader.cancelItem(this)
                }, o.prototype.remove = function () {
                    this.uploader.removeFromQueue(this)
                }, o.prototype.onBeforeUpload = function () {
                }, o.prototype.onProgress = function (e) {
                }, o.prototype.onSuccess = function (e, t, o) {
                }, o.prototype.onError = function (e, t, o) {
                }, o.prototype.onCancel = function (e, t, o) {
                }, o.prototype.onComplete = function (e, t, o) {
                }, o.prototype._onBeforeUpload = function () {
                    this.isReady = !0, this.isUploading = !1, this.isUploaded = !1, this.isSuccess = !1, this.isCancel = !1, this.isError = !1, this.progress = 0, this.onBeforeUpload()
                }, o.prototype._onProgress = function (e) {
                    this.progress = e, this.onProgress(e)
                }, o.prototype._onSuccess = function (e, t, o) {
                    this.isReady = !1, this.isUploading = !1, this.isUploaded = !0, this.isSuccess = !0, this.isCancel = !1, this.isError = !1, this.progress = 100, this.index = null, this.onSuccess(e, t, o)
                }, o.prototype._onError = function (e, t, o) {
                    this.isReady = !1, this.isUploading = !1, this.isUploaded = !0, this.isSuccess = !1, this.isCancel = !1, this.isError = !0, this.progress = 0, this.index = null, this.onError(e, t, o)
                }, o.prototype._onCancel = function (e, t, o) {
                    this.isReady = !1, this.isUploading = !1, this.isUploaded = !1, this.isSuccess = !1, this.isCancel = !0, this.isError = !1, this.progress = 0, this.index = null, this.onCancel(e, t, o)
                }, o.prototype._onComplete = function (e, t, o) {
                    this.onComplete(e, t, o), this.removeAfterUpload && this.remove()
                }, o.prototype._destroy = function () {
                    this._input && this._input.remove(), this._form && this._form.remove(), delete this._form, delete this._input
                }, o.prototype._prepareToUploading = function () {
                    this.index = this.index || ++this.uploader._nextIndex, this.isReady = !0
                }, o.prototype._replaceNode = function (t) {
                    var o = e(t.clone())(t.scope());
                    o.prop("value", null), t.css("display", "none"), t.after(o)
                }, o
            }()
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = i;
        var s = o(1), a = (n(s), angular), l = a.copy, u = a.extend, p = a.element, c = a.isElement;
        i.$inject = ["$compile", "FileLikeObject"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i() {
            var e = function () {
                function e(t) {
                    r(this, e), l(this, t), this.uploader._directives[this.prop].push(this), this._saveLinks(), this.bind()
                }

                return e.prototype.bind = function () {
                    for (var e in this.events) {
                        var t = this.events[e];
                        this.element.bind(e, this[t])
                    }
                }, e.prototype.unbind = function () {
                    for (var e in this.events)this.element.unbind(e, this.events[e])
                }, e.prototype.destroy = function () {
                    var e = this.uploader._directives[this.prop].indexOf(this);
                    this.uploader._directives[this.prop].splice(e, 1), this.unbind()
                }, e.prototype._saveLinks = function () {
                    for (var e in this.events) {
                        var t = this.events[e];
                        this[t] = this[t].bind(this)
                    }
                }, e
            }();
            return e.prototype.events = {}, e
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = i;
        var s = o(1), a = (n(s), angular), l = a.extend
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i(e, t) {
            if (!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return !t || "object" != typeof t && "function" != typeof t ? e : t
        }

        function s(e, t) {
            if ("function" != typeof t && null !== t)throw new TypeError("Super expression must either be null or a function, not " + typeof t);
            e.prototype = Object.create(t && t.prototype, {
                constructor: {
                    value: e,
                    enumerable: !1,
                    writable: !0,
                    configurable: !0
                }
            }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
        }

        function a(e) {
            return function (e) {
                function t(o) {
                    r(this, t);
                    var n = p(o, {
                        events: {$destroy: "destroy", change: "onChange"},
                        prop: "select"
                    }), s = i(this, e.call(this, n));
                    return s.uploader.isHTML5 || s.element.removeAttr("multiple"), s.element.prop("value", null), s
                }

                return s(t, e), t.prototype.getOptions = function () {
                }, t.prototype.getFilters = function () {
                }, t.prototype.isEmptyAfterSelection = function () {
                    return !!this.element.attr("multiple")
                }, t.prototype.onChange = function () {
                    var e = this.uploader.isHTML5 ? this.element[0].files : this.element[0], t = this.getOptions(), o = this.getFilters();
                    this.uploader.isHTML5 || this.destroy(), this.uploader.addToQueue(e, t, o), this.isEmptyAfterSelection() && (this.element.prop("value", null), this.element.replaceWith(this.element = this.element.clone(!0)))
                }, t
            }(e)
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = a;
        var l = o(1), u = (n(l), angular), p = u.extend;
        a.$inject = ["FileDirective"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i(e, t) {
            if (!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return !t || "object" != typeof t && "function" != typeof t ? e : t
        }

        function s(e, t) {
            if ("function" != typeof t && null !== t)throw new TypeError("Super expression must either be null or a function, not " + typeof t);
            e.prototype = Object.create(t && t.prototype, {
                constructor: {
                    value: e,
                    enumerable: !1,
                    writable: !0,
                    configurable: !0
                }
            }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
        }

        function a(e) {
            return function (e) {
                function t(o) {
                    r(this, t);
                    var n = p(o, {
                        events: {
                            $destroy: "destroy",
                            drop: "onDrop",
                            dragover: "onDragOver",
                            dragleave: "onDragLeave"
                        }, prop: "drop"
                    });
                    return i(this, e.call(this, n))
                }

                return s(t, e), t.prototype.getOptions = function () {
                }, t.prototype.getFilters = function () {
                }, t.prototype.onDrop = function (e) {
                    var t = this._getTransfer(e);
                    if (t) {
                        var o = this.getOptions(), n = this.getFilters();
                        this._preventAndStop(e), c(this.uploader._directives.over, this._removeOverClass, this), this.uploader.addToQueue(t.files, o, n)
                    }
                }, t.prototype.onDragOver = function (e) {
                    var t = this._getTransfer(e);
                    this._haveFiles(t.types) && (t.dropEffect = "copy", this._preventAndStop(e), c(this.uploader._directives.over, this._addOverClass, this))
                }, t.prototype.onDragLeave = function (e) {
                    e.currentTarget !== this.element[0] && (this._preventAndStop(e), c(this.uploader._directives.over, this._removeOverClass, this))
                }, t.prototype._getTransfer = function (e) {
                    return e.dataTransfer ? e.dataTransfer : e.originalEvent.dataTransfer
                }, t.prototype._preventAndStop = function (e) {
                    e.preventDefault(), e.stopPropagation()
                }, t.prototype._haveFiles = function (e) {
                    return e ? e.indexOf ? -1 !== e.indexOf("Files") : e.contains ? e.contains("Files") : !1 : !1
                }, t.prototype._addOverClass = function (e) {
                    e.addOverClass()
                }, t.prototype._removeOverClass = function (e) {
                    e.removeOverClass()
                }, t
            }(e)
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = a;
        var l = o(1), u = (n(l), angular), p = u.extend, c = u.forEach;
        a.$inject = ["FileDirective"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            if (!(e instanceof t))throw new TypeError("Cannot call a class as a function")
        }

        function i(e, t) {
            if (!e)throw new ReferenceError("this hasn't been initialised - super() hasn't been called");
            return !t || "object" != typeof t && "function" != typeof t ? e : t
        }

        function s(e, t) {
            if ("function" != typeof t && null !== t)throw new TypeError("Super expression must either be null or a function, not " + typeof t);
            e.prototype = Object.create(t && t.prototype, {
                constructor: {
                    value: e,
                    enumerable: !1,
                    writable: !0,
                    configurable: !0
                }
            }), t && (Object.setPrototypeOf ? Object.setPrototypeOf(e, t) : e.__proto__ = t)
        }

        function a(e) {
            return function (e) {
                function t(o) {
                    r(this, t);
                    var n = p(o, {events: {$destroy: "destroy"}, prop: "over", overClass: "nv-file-over"});
                    return i(this, e.call(this, n))
                }

                return s(t, e), t.prototype.addOverClass = function () {
                    this.element.addClass(this.getOverClass())
                }, t.prototype.removeOverClass = function () {
                    this.element.removeClass(this.getOverClass())
                }, t.prototype.getOverClass = function () {
                    return this.overClass
                }, t
            }(e)
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = a;
        var l = o(1), u = (n(l), angular), p = u.extend;
        a.$inject = ["FileDirective"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t, o) {
            return {
                link: function (n, r, i) {
                    var s = n.$eval(i.uploader);
                    if (!(s instanceof t))throw new TypeError('"Uploader" must be an instance of FileUploader');
                    var a = new o({uploader: s, element: r});
                    a.getOptions = e(i.options).bind(a, n), a.getFilters = function () {
                        return i.filters
                    }
                }
            }
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = r;
        var i = o(1);
        n(i);
        r.$inject = ["$parse", "FileUploader", "FileSelect"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t, o) {
            return {
                link: function (n, r, i) {
                    var s = n.$eval(i.uploader);
                    if (!(s instanceof t))throw new TypeError('"Uploader" must be an instance of FileUploader');
                    if (s.isHTML5) {
                        var a = new o({uploader: s, element: r});
                        a.getOptions = e(i.options).bind(a, n), a.getFilters = function () {
                            return i.filters
                        }
                    }
                }
            }
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = r;
        var i = o(1);
        n(i);
        r.$inject = ["$parse", "FileUploader", "FileDrop"]
    }, function (e, t, o) {
        "use strict";
        function n(e) {
            return e && e.__esModule ? e : {"default": e}
        }

        function r(e, t) {
            return {
                link: function (o, n, r) {
                    var i = o.$eval(r.uploader);
                    if (!(i instanceof e))throw new TypeError('"Uploader" must be an instance of FileUploader');
                    var s = new t({uploader: i, element: n});
                    s.getOverClass = function () {
                        return r.overClass || s.overClass
                    }
                }
            }
        }

        Object.defineProperty(t, "__esModule", {value: !0}), t["default"] = r;
        var i = o(1);
        n(i);
        r.$inject = ["FileUploader", "FileOver"]
    }])
});
//# sourceMappingURL=angular-file-upload.min.js.map