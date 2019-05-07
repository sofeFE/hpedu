window.jwplayer = function(e) {
    function t(n) {
        if (i[n]) return i[n].exports;
        var r = i[n] = {
            exports: {},
            id: n,
            loaded: !1
        };
        return e[n].call(r.exports, r, r.exports, t),
        r.loaded = !0,
        r.exports
    }
    var n = window.webpackJsonpjwplayer;
    window.webpackJsonpjwplayer = function(i, o) {
        for (var a, s, l = 0,
        u = []; l < i.length; l++) s = i[l],
        r[s] && u.push.apply(u, r[s]),
        r[s] = 0;
        for (a in o) e[a] = o[a];
        for (n && n(i, o); u.length;) u.shift().call(null, t)
    };
    var i = {},
    r = {
        0 : 0
    };
    return t.e = function(e, n) {
        if (0 === r[e]) return n.call(null, t);
        if (void 0 !== r[e]) r[e].push(n);
        else {
            r[e] = [n];
            var i = document.getElementsByTagName("head")[0],
            o = document.createElement("script");
            o.type = "text/javascript",
            o.charset = "utf-8",
            o.async = !0,
            o.src = t.p + "" + ({
                1 : "provider.hlsjs",
                2 : "provider.shaka",
                3 : "provider.cast",
                4 : "provider.html5",
                5 : "provider.flash",
                6 : "provider.youtube",
                7 : "polyfills.vttrenderer",
                8 : "polyfills.promise",
                9 : "polyfills.intersection-observer",
                10 : "polyfills.base64",
                11 : "vttparser"
            } [e] || e) + ".js",
            i.appendChild(o)
        }
    },
    t.m = e,
    t.c = i,
    t.p = "",
    t(0)
} ([function(e, t, n) {
    e.exports = n(168)
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        var e = {},
        t = Array.prototype,
        n = Object.prototype,
        i = Function.prototype,
        r = t.slice,
        o = t.concat,
        a = n.toString,
        s = n.hasOwnProperty,
        l = t.map,
        u = t.reduce,
        c = t.forEach,
        d = t.filter,
        p = t.every,
        h = t.some,
        f = t.indexOf,
        g = Array.isArray,
        m = Object.keys,
        w = i.bind,
        v = function(e) {
            return e instanceof v ? e: this instanceof v ? void 0 : new v(e)
        },
        y = v.each = v.forEach = function(t, n, i) {
            if (null == t) return t;
            if (c && t.forEach === c) t.forEach(n, i);
            else if (t.length === +t.length) {
                for (var r = 0,
                o = t.length; r < o; r++) if (n.call(i, t[r], r, t) === e) return
            } else for (var a = v.keys(t), r = 0, o = a.length; r < o; r++) if (n.call(i, t[a[r]], a[r], t) === e) return;
            return t
        };
        v.map = v.collect = function(e, t, n) {
            var i = [];
            return null == e ? i: l && e.map === l ? e.map(t, n) : (y(e,
            function(e, r, o) {
                i.push(t.call(n, e, r, o))
            }), i)
        };
        var j = "Reduce of empty array with no initial value";
        v.reduce = v.foldl = v.inject = function(e, t, n, i) {
            var r = arguments.length > 2;
            if (null == e && (e = []), u && e.reduce === u) return i && (t = v.bind(t, i)),
            r ? e.reduce(t, n) : e.reduce(t);
            if (y(e,
            function(e, o, a) {
                r ? n = t.call(i, n, e, o, a) : (n = e, r = !0)
            }), !r) throw new TypeError(j);
            return n
        },
        v.find = v.detect = function(e, t, n) {
            var i;
            return b(e,
            function(e, r, o) {
                if (t.call(n, e, r, o)) return i = e,
                !0
            }),
            i
        },
        v.filter = v.select = function(e, t, n) {
            var i = [];
            return null == e ? i: d && e.filter === d ? e.filter(t, n) : (y(e,
            function(e, r, o) {
                t.call(n, e, r, o) && i.push(e)
            }), i)
        },
        v.reject = function(e, t, n) {
            return v.filter(e,
            function(e, i, r) {
                return ! t.call(n, e, i, r)
            },
            n)
        },
        v.compact = function(e) {
            return v.filter(e, v.identity)
        },
        v.every = v.all = function(t, n, i) {
            n || (n = v.identity);
            var r = !0;
            return null == t ? r: p && t.every === p ? t.every(n, i) : (y(t,
            function(t, o, a) {
                if (! (r = r && n.call(i, t, o, a))) return e
            }), !!r)
        };
        var b = v.some = v.any = function(t, n, i) {
            n || (n = v.identity);
            var r = !1;
            return null == t ? r: h && t.some === h ? t.some(n, i) : (y(t,
            function(t, o, a) {
                if (r || (r = n.call(i, t, o, a))) return e
            }), !!r)
        };
        v.size = function(e) {
            return null == e ? 0 : e.length === +e.length ? e.length: v.keys(e).length
        },
        v.last = function(e, t, n) {
            if (null != e) return null == t || n ? e[e.length - 1] : r.call(e, Math.max(e.length - t, 0))
        },
        v.after = function(e, t) {
            return function() {
                if (--e < 1) return t.apply(this, arguments)
            }
        },
        v.before = function(e, t) {
            var n;
            return function() {
                return--e > 0 && (n = t.apply(this, arguments)),
                e <= 1 && (t = null),
                n
            }
        };
        var E = function(e) {
            return null == e ? v.identity: v.isFunction(e) ? e: v.property(e)
        },
        x = function(e) {
            return function(t, n, i) {
                var r = {};
                return n = E(n),
                y(t,
                function(o, a) {
                    var s = n.call(i, o, a, t);
                    e(r, s, o)
                }),
                r
            }
        };
        v.groupBy = x(function(e, t, n) {
            v.has(e, t) ? e[t].push(n) : e[t] = [n]
        }),
        v.indexBy = x(function(e, t, n) {
            e[t] = n
        }),
        v.sortedIndex = function(e, t, n, i) {
            n = E(n);
            for (var r = n.call(i, t), o = 0, a = e.length; o < a;) {
                var s = o + a >>> 1;
                n.call(i, e[s]) < r ? o = s + 1 : a = s
            }
            return o
        };
        var b = v.some = v.any = function(t, n, i) {
            n || (n = v.identity);
            var r = !1;
            return null == t ? r: h && t.some === h ? t.some(n, i) : (y(t,
            function(t, o, a) {
                if (r || (r = n.call(i, t, o, a))) return e
            }), !!r)
        };
        v.contains = v.include = function(e, t) {
            return null != e && (e.length !== +e.length && (e = v.values(e)), v.indexOf(e, t) >= 0)
        },
        v.pluck = function(e, t) {
            return v.map(e, v.property(t))
        },
        v.where = function(e, t) {
            return v.filter(e, v.matches(t))
        },
        v.findWhere = function(e, t) {
            return v.find(e, v.matches(t))
        },
        v.max = function(e, t, n) {
            if (!t && v.isArray(e) && e[0] === +e[0] && e.length < 65535) return Math.max.apply(Math, e);
            var i = -(1 / 0),
            r = -(1 / 0);
            return y(e,
            function(e, o, a) {
                var s = t ? t.call(n, e, o, a) : e;
                s > r && (i = e, r = s)
            }),
            i
        },
        v.difference = function(e) {
            var n = o.apply(t, r.call(arguments, 1));
            return v.filter(e,
            function(e) {
                return ! v.contains(n, e)
            })
        },
        v.without = function(e) {
            return v.difference(e, r.call(arguments, 1))
        },
        v.indexOf = function(e, t, n) {
            if (null == e) return - 1;
            var i = 0,
            r = e.length;
            if (n) {
                if ("number" != typeof n) return i = v.sortedIndex(e, t),
                e[i] === t ? i: -1;
                i = n < 0 ? Math.max(0, r + n) : n
            }
            if (f && e.indexOf === f) return e.indexOf(t, n);
            for (; i < r; i++) if (e[i] === t) return i;
            return - 1
        };
        var k = function() {};
        v.bind = function(e, t) {
            var n, i;
            if (w && e.bind === w) return w.apply(e, r.call(arguments, 1));
            if (!v.isFunction(e)) throw new TypeError;
            return n = r.call(arguments, 2),
            i = function() {
                if (! (this instanceof i)) return e.apply(t, n.concat(r.call(arguments)));
                k.prototype = e.prototype;
                var o = new k;
                k.prototype = null;
                var a = e.apply(o, n.concat(r.call(arguments)));
                return Object(a) === a ? a: o
            }
        },
        v.partial = function(e) {
            var t = r.call(arguments, 1);
            return function() {
                for (var n = 0,
                i = t.slice(), r = 0, o = i.length; r < o; r++) i[r] === v && (i[r] = arguments[n++]);
                for (; n < arguments.length;) i.push(arguments[n++]);
                return e.apply(this, i)
            }
        },
        v.once = v.partial(v.before, 2),
        v.memoize = function(e, t) {
            var n = {};
            return t || (t = v.identity),
            function() {
                var i = t.apply(this, arguments);
                return v.has(n, i) ? n[i] : n[i] = e.apply(this, arguments)
            }
        },
        v.delay = function(e, t) {
            var n = r.call(arguments, 2);
            return setTimeout(function() {
                return e.apply(null, n)
            },
            t)
        },
        v.defer = function(e) {
            return v.delay.apply(v, [e, 1].concat(r.call(arguments, 1)))
        },
        v.throttle = function(e, t, n) {
            var i, r, o, a = null,
            s = 0;
            n || (n = {});
            var l = function() {
                s = n.leading === !1 ? 0 : v.now(),
                a = null,
                o = e.apply(i, r),
                i = r = null
            };
            return function() {
                var u = v.now();
                s || n.leading !== !1 || (s = u);
                var c = t - (u - s);
                return i = this,
                r = arguments,
                c <= 0 ? (clearTimeout(a), a = null, s = u, o = e.apply(i, r), i = r = null) : a || n.trailing === !1 || (a = setTimeout(l, c)),
                o
            }
        },
        v.keys = function(e) {
            if (!v.isObject(e)) return [];
            if (m) return m(e);
            var t = [];
            for (var n in e) v.has(e, n) && t.push(n);
            return t
        },
        v.invert = function(e) {
            for (var t = {},
            n = v.keys(e), i = 0, r = n.length; i < r; i++) t[e[n[i]]] = n[i];
            return t
        },
        v.defaults = function(e) {
            return y(r.call(arguments, 1),
            function(t) {
                if (t) for (var n in t) void 0 === e[n] && (e[n] = t[n])
            }),
            e
        },
        v.extend = function(e) {
            return y(r.call(arguments, 1),
            function(t) {
                if (t) for (var n in t) e[n] = t[n]
            }),
            e
        },
        v.pick = function(e) {
            var n = {},
            i = o.apply(t, r.call(arguments, 1));
            return y(i,
            function(t) {
                t in e && (n[t] = e[t])
            }),
            n
        },
        v.omit = function(e) {
            var n = {},
            i = o.apply(t, r.call(arguments, 1));
            for (var a in e) v.contains(i, a) || (n[a] = e[a]);
            return n
        },
        v.clone = function(e) {
            return v.isObject(e) ? v.isArray(e) ? e.slice() : v.extend({},
            e) : e
        },
        v.isArray = g ||
        function(e) {
            return "[object Array]" == a.call(e)
        },
        v.isObject = function(e) {
            return e === Object(e)
        },
        y(["Arguments", "Function", "String", "Number", "Date", "RegExp"],
        function(e) {
            v["is" + e] = function(t) {
                return a.call(t) == "[object " + e + "]"
            }
        }),
        v.isArguments(arguments) || (v.isArguments = function(e) {
            return ! (!e || !v.has(e, "callee"))
        }),
        v.isFunction = function(e) {
            return "function" == typeof e
        },
        v.isFinite = function(e) {
            return isFinite(e) && !isNaN(parseFloat(e))
        },
        v.isNaN = function(e) {
            return v.isNumber(e) && e != +e
        },
        v.isBoolean = function(e) {
            return e === !0 || e === !1 || "[object Boolean]" == a.call(e)
        },
        v.isNull = function(e) {
            return null === e
        },
        v.isUndefined = function(e) {
            return void 0 === e
        },
        v.has = function(e, t) {
            return s.call(e, t)
        },
        v.identity = function(e) {
            return e
        },
        v.constant = function(e) {
            return function() {
                return e
            }
        },
        v.property = function(e) {
            return function(t) {
                return t[e]
            }
        },
        v.propertyOf = function(e) {
            return null == e ?
            function() {}: function(t) {
                return e[t]
            }
        },
        v.matches = function(e) {
            return function(t) {
                if (t === e) return ! 0;
                for (var n in e) if (e[n] !== t[n]) return ! 1;
                return ! 0
            }
        },
        v.now = Date.now ||
        function() {
            return (new Date).getTime()
        },
        v.result = function(e, t) {
            if (null != e) {
                var n = e[t];
                return v.isFunction(n) ? n.call(e) : n
            }
        };
        var A = 0;
        return v.uniqueId = function(e) {
            var t = ++A + "";
            return e ? e + t: t
        },
        v
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8), n(1), n(27), n(32), n(23), n(33), n(14), n(60), n(34), n(63), n(141), n(140)],
    r = function(e, t, n, i, r, o, a, s, l, u, c, d) {
        var p = {};
        return p.log = function() {
            window.console && ("object" == typeof console.log ? console.log(Array.prototype.slice.call(arguments, 0)) : console.log.apply(console, arguments))
        },
        p.between = function(e, t, n) {
            return Math.max(Math.min(e, n), t)
        },
        p.foreach = function(e, t) {
            var n, i;
            for (n in e)"function" === p.typeOf(e.hasOwnProperty) ? e.hasOwnProperty(n) && (i = e[n], t(n, i)) : (i = e[n], t(n, i))
        },
        p.indexOf = t.indexOf,
        p.noop = function() {},
        p.seconds = e.seconds,
        p.prefix = e.prefix,
        p.suffix = e.suffix,
        t.extend(p, o, a, l, n, s, i, r, u, c, d),
        p
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        var e = {
            DRAG: "drag",
            DRAG_START: "dragStart",
            DRAG_END: "dragEnd",
            CLICK: "click",
            DOUBLE_CLICK: "doubleClick",
            TAP: "tap",
            DOUBLE_TAP: "doubleTap",
            OVER: "over",
            MOVE: "move",
            OUT: "out"
        },
        t = {
            COMPLETE: "complete",
            ERROR: "error",
            JWPLAYER_AD_CLICK: "adClick",
            JWPLAYER_AD_COMPANIONS: "adCompanions",
            JWPLAYER_AD_COMPLETE: "adComplete",
            JWPLAYER_AD_ERROR: "adError",
            JWPLAYER_AD_IMPRESSION: "adImpression",
            JWPLAYER_AD_META: "adMeta",
            JWPLAYER_AD_PAUSE: "adPause",
            JWPLAYER_AD_PLAY: "adPlay",
            JWPLAYER_AD_SKIPPED: "adSkipped",
            JWPLAYER_AD_TIME: "adTime",
            JWPLAYER_CAST_AD_CHANGED: "castAdChanged",
            JWPLAYER_MEDIA_COMPLETE: "complete",
            JWPLAYER_READY: "ready",
            JWPLAYER_MEDIA_SEEK: "seek",
            JWPLAYER_MEDIA_BEFOREPLAY: "beforePlay",
            JWPLAYER_MEDIA_BEFORECOMPLETE: "beforeComplete",
            JWPLAYER_MEDIA_BUFFER_FULL: "bufferFull",
            JWPLAYER_DISPLAY_CLICK: "displayClick",
            JWPLAYER_PLAYLIST_COMPLETE: "playlistComplete",
            JWPLAYER_CAST_SESSION: "cast",
            JWPLAYER_MEDIA_ERROR: "mediaError",
            JWPLAYER_MEDIA_FIRST_FRAME: "firstFrame",
            JWPLAYER_MEDIA_PLAY_ATTEMPT: "playAttempt",
            JWPLAYER_MEDIA_LOADED: "loaded",
            JWPLAYER_MEDIA_SEEKED: "seeked",
            JWPLAYER_SETUP_ERROR: "setupError",
            JWPLAYER_ERROR: "error",
            JWPLAYER_PLAYER_STATE: "state",
            JWPLAYER_CAST_AVAILABLE: "castAvailable",
            JWPLAYER_MEDIA_BUFFER: "bufferChange",
            JWPLAYER_MEDIA_TIME: "time",
            JWPLAYER_MEDIA_TYPE: "mediaType",
            JWPLAYER_MEDIA_VOLUME: "volume",
            JWPLAYER_MEDIA_MUTE: "mute",
            JWPLAYER_MEDIA_META: "meta",
            JWPLAYER_MEDIA_LEVELS: "levels",
            JWPLAYER_MEDIA_LEVEL_CHANGED: "levelsChanged",
            JWPLAYER_CONTROLS: "controls",
            JWPLAYER_FULLSCREEN: "fullscreen",
            JWPLAYER_RESIZE: "resize",
            JWPLAYER_PLAYLIST_ITEM: "playlistItem",
            JWPLAYER_PLAYLIST_LOADED: "playlist",
            JWPLAYER_AUDIO_TRACKS: "audioTracks",
            JWPLAYER_AUDIO_TRACK_CHANGED: "audioTrackChanged",
            JWPLAYER_LOGO_CLICK: "logoClick",
            JWPLAYER_CAPTIONS_LIST: "captionsList",
            JWPLAYER_CAPTIONS_CHANGED: "captionsChanged",
            JWPLAYER_PROVIDER_CHANGED: "providerChanged",
            JWPLAYER_PROVIDER_FIRST_FRAME: "providerFirstFrame",
            JWPLAYER_USER_ACTION: "userAction",
            JWPLAYER_PROVIDER_CLICK: "providerClick",
            JWPLAYER_VIEW_TAB_FOCUS: "tabFocus",
            JWPLAYER_CONTROLBAR_DRAGGING: "scrubbing",
            JWPLAYER_INSTREAM_CLICK: "instreamClick"
        };
        return t.touchEvents = e,
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = [],
        n = t.slice,
        i = {
            on: function(e, t, n) {
                if (!o(this, "on", e, [t, n]) || !t) return this;
                this._events || (this._events = {});
                var i = this._events[e] || (this._events[e] = []);
                return i.push({
                    callback: t,
                    context: n
                }),
                this
            },
            once: function(t, n, i) {
                if (!o(this, "once", t, [n, i]) || !n) return this;
                var r = this,
                a = e.once(function() {
                    r.off(t, a),
                    n.apply(this, arguments)
                });
                return a._callback = n,
                this.on(t, a, i)
            },
            off: function(t, n, i) {
                var r, a, s, l, u, c, d, p;
                if (!this._events || !o(this, "off", t, [n, i])) return this;
                if (!t && !n && !i) return this._events = void 0,
                this;
                for (l = t ? [t] : e.keys(this._events), u = 0, c = l.length; u < c; u++) if (t = l[u], s = this._events[t]) {
                    if (this._events[t] = r = [], n || i) for (d = 0, p = s.length; d < p; d++) a = s[d],
                    (n && n !== a.callback && n !== a.callback._callback || i && i !== a.context) && r.push(a);
                    r.length || delete this._events[t]
                }
                return this
            },
            trigger: function(e) {
                if (!this._events) return this;
                var t = n.call(arguments, 1);
                if (!o(this, "trigger", e, t)) return this;
                var i = this._events[e],
                r = this._events.all;
                return i && a(i, t, this),
                r && a(r, arguments, this),
                this
            },
            triggerSafe: function(e) {
                if (!this._events) return this;
                var t = n.call(arguments, 1);
                if (!o(this, "trigger", e, t)) return this;
                var i = this._events[e],
                r = this._events.all;
                return i && s(i, t, this, e),
                r && s(r, arguments, this, e),
                this
            }
        },
        r = /\s+/,
        o = function(e, t, n, i) {
            if (!n) return ! 0;
            if ("object" == typeof n) {
                for (var o in n) e[t].apply(e, [o, n[o]].concat(i));
                return ! 1
            }
            if (r.test(n)) {
                for (var a = n.split(r), s = 0, l = a.length; s < l; s++) e[t].apply(e, [a[s]].concat(i));
                return ! 1
            }
            return ! 0
        },
        a = function(e, t, n) {
            var i, r = -1,
            o = e.length,
            a = t[0],
            s = t[1],
            l = t[2];
            switch (t.length) {
            case 0:
                for (; ++r < o;)(i = e[r]).callback.call(i.context || n);
                return;
            case 1:
                for (; ++r < o;)(i = e[r]).callback.call(i.context || n, a);
                return;
            case 2:
                for (; ++r < o;)(i = e[r]).callback.call(i.context || n, a, s);
                return;
            case 3:
                for (; ++r < o;)(i = e[r]).callback.call(i.context || n, a, s, l);
                return;
            default:
                for (; ++r < o;)(i = e[r]).callback.apply(i.context || n, t);
                return
            }
        },
        s = function(e, t, n, i) {
            for (var r, o = -1,
            a = e.length; ++o < a;) try { (r = e[o]).callback.apply(r.context || n, t)
            } catch(s) {
                console.log('Error in "' + i + '" event handler:', s)
            }
        };
        return i
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return {
            BUFFERING: "buffering",
            IDLE: "idle",
            COMPLETE: "complete",
            PAUSED: "paused",
            PLAYING: "playing",
            ERROR: "error",
            LOADING: "loading",
            STALLED: "stalled"
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
,
function(e, t, n) {
    var i, r;
    i = [n(4), n(3), n(1), n(2)],
    r = function(e, t, n, i) {
        function r(e, t) {
            return /touch/.test(e.type) ? (e.originalEvent || e).changedTouches[0]["page" + t] : e["page" + t]
        }
        function o(e) {
            var t = e || window.event;
            return e instanceof MouseEvent && ("which" in t ? 3 === t.which: "button" in t && 2 === t.button)
        }
        function a(e, t, n) {
            var i;
            return i = t instanceof MouseEvent || !t.touches && !t.changedTouches ? t: t.touches && t.touches.length ? t.touches[0] : t.changedTouches[0],
            {
                type: e,
                target: t.target,
                currentTarget: n,
                pageX: i.pageX,
                pageY: i.pageY
            }
        }
        function s(e) { (e instanceof MouseEvent || e instanceof window.TouchEvent) && (e.preventManipulation && e.preventManipulation(), e.preventDefault && e.preventDefault())
        }
        var l = t.touchEvents,
        u = "PointerEvent" in window,
        c = "ontouchstart" in window,
        d = !(u || c && i.isMobile()),
        p = i.isFF() && i.isOSX(),
        h = function(e, t) {
            function i(e) {
                "touch" !== e.pointerType && v(l.OVER, e)
            }
            function c(e) {
                "touch" !== e.pointerType && v(l.MOVE, e)
            }
            function h(t) { (d || u && "touch" !== t.pointerType && !e.contains(document.elementFromPoint(t.x, t.y))) && v(l.OUT, t)
            }
            function f(e, t, n) {
                e.removeEventListener(t, n),
                e.addEventListener(t, n)
            }
            function g(n) {
                y = n.target,
                x = r(n, "X"),
                k = r(n, "Y"),
                o(n) || ("pointerdown" === n.type && n.isPrimary ? (t.preventScrolling && (j = n.pointerId, e.setPointerCapture(j)), f(e, "pointermove", m), f(e, "pointercancel", w), "mouse" === n.pointerType && "OBJECT" === y.nodeName ? f(document, "mouseup", w) : f(e, "pointerup", w)) : "mousedown" === n.type ? (f(document, "mousemove", m), p && "object" === n.target.nodeName.toLowerCase() ? f(e, "click", w) : f(document, "mouseup", w)) : "touchstart" === n.type && (f(y, "touchmove", m), f(y, "touchcancel", w), f(y, "touchend", w)), t.preventScrolling && s(n))
            }
            function m(e) {
                var n = 6;
                if (E) v(l.DRAG, e);
                else {
                    var i = r(e, "X"),
                    o = r(e, "Y"),
                    a = i - x,
                    u = o - k;
                    a * a + u * u > n * n && (v(l.DRAG_START, e), E = !0, v(l.DRAG, e))
                }
                t.preventScrolling && s(e)
            }
            function w(n) {
                var i = "pointerup" === n.type || "pointercancel" === n.type;
                i && t.preventScrolling && e.releasePointerCapture(j),
                e.removeEventListener("pointermove", m),
                e.removeEventListener("pointercancel", w),
                e.removeEventListener("pointerup", w),
                document.removeEventListener("mousemove", m),
                document.removeEventListener("mouseup", w),
                y.removeEventListener("touchmove", m),
                y.removeEventListener("touchcancel", w),
                y.removeEventListener("touchend", w),
                E ? v(l.DRAG_END, n) : t.directSelect && n.target !== e || n.type.indexOf("cancel") !== -1 || ("mouseup" === n.type || "click" === n.type || i && "mouse" === n.pointerType ? v(l.CLICK, n) : (v(l.TAP, n), "touchend" === n.type && s(n))),
                y = null,
                E = !1
            }
            function v(e, i) {
                var r;
                if (t.enableDoubleTap && (e === l.CLICK || e === l.TAP)) if (n.now() - A < _) {
                    var o = e === l.CLICK ? l.DOUBLE_CLICK: l.DOUBLE_TAP;
                    r = a(o, i, b),
                    C.trigger(o, r),
                    A = 0
                } else A = n.now();
                r = a(e, i, b),
                C.trigger(e, r)
            }
            var y, j, b = e,
            E = !1,
            x = 0,
            k = 0,
            A = 0,
            _ = 300;
            t = t || {},
            u ? (e.addEventListener("pointerdown", g), t.useHover && (e.addEventListener("pointerover", i), e.addEventListener("pointerout", h)), t.useMove && e.addEventListener("pointermove", c)) : (d && (e.addEventListener("mousedown", g), t.useHover && (e.addEventListener("mouseover", i), e.addEventListener("mouseout", h)), t.useMove && e.addEventListener("mousemove", c)), e.addEventListener("touchstart", g));
            var C = this;
            return this.triggerEvent = v,
            this.destroy = function() {
                e.removeEventListener("touchstart", g),
                e.removeEventListener("mousedown", g),
                y && (y.removeEventListener("touchmove", m), y.removeEventListener("touchcancel", w), y.removeEventListener("touchend", w), y = null),
                u && (t.preventScrolling && e.releasePointerCapture(j), e.removeEventListener("pointerover", i), e.removeEventListener("pointerdown", g), e.removeEventListener("pointermove", m), e.removeEventListener("pointermove", c), e.removeEventListener("pointercancel", w), e.removeEventListener("pointerout", h), e.removeEventListener("pointerup", w)),
                e.removeEventListener("click", w),
                e.removeEventListener("mouseover", i),
                e.removeEventListener("mousemove", c),
                e.removeEventListener("mouseout", h),
                document.removeEventListener("mousemove", m),
                document.removeEventListener("mouseup", w)
            },
            this
        };
        return n.extend(h.prototype, e),
        h
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        function t(e) {
            return !! /[\(,]format=m3u8-/i.test(e) && "m3u8"
        }
        var n = function(e) {
            return e.replace(/^\s+|\s+$/g, "")
        },
        i = function(e, t, n) {
            for (e = "" + e, n = n || "0"; e.length < t;) e = n + e;
            return e
        },
        r = function(e, t) {
            for (var n = 0; n < e.attributes.length; n++) if (e.attributes[n].name && e.attributes[n].name.toLowerCase() === t.toLowerCase()) return e.attributes[n].value.toString();
            return ""
        },
        o = function(e) {
            if (!e || "rtmp" === e.substr(0, 4)) return "";
            var n = t(e);
            return n ? n: (e = e.split("?")[0].split("#")[0], e.lastIndexOf(".") > -1 ? e.substr(e.lastIndexOf(".") + 1, e.length).toLowerCase() : void 0)
        },
        a = function(e) {
            var t = parseInt(e / 3600),
            n = parseInt(e / 60) % 60,
            r = e % 60;
            return i(t, 2) + ":" + i(n, 2) + ":" + i(r.toFixed(3), 6)
        },
        s = function(t, n) {
            if (e.isNumber(t)) return t;
            t = t.replace(",", ".");
            var i = t.split(":"),
            r = i.length,
            o = 0;
            if ("s" === t.slice( - 1)) o = parseFloat(t);
            else if ("m" === t.slice( - 1)) o = 60 * parseFloat(t);
            else if ("h" === t.slice( - 1)) o = 3600 * parseFloat(t);
            else if (r > 1) {
                var a = r - 1;
                4 === r && (n && (o = parseFloat(i[a]) / n), a -= 1),
                o += parseFloat(i[a]),
                o += 60 * parseFloat(i[a - 1]),
                r >= 3 && (o += 3600 * parseFloat(i[a - 2]))
            } else o = parseFloat(t);
            return o
        },
        l = function(t, n) {
            return e.map(t,
            function(e) {
                return n + e
            })
        },
        u = function(t, n) {
            return e.map(t,
            function(e) {
                return e + n
            })
        };
        return {
            trim: n,
            pad: i,
            xmlAttribute: r,
            extension: o,
            hms: a,
            seconds: s,
            suffix: u,
            prefix: l
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    e.exports = n(96)["default"]
},
,
function(e, t) {
    "use strict";
    function n(e) {
        return c[e]
    }
    function i(e) {
        for (var t = 1; t < arguments.length; t++) for (var n in arguments[t]) Object.prototype.hasOwnProperty.call(arguments[t], n) && (e[n] = arguments[t][n]);
        return e
    }
    function r(e, t) {
        for (var n = 0,
        i = e.length; n < i; n++) if (e[n] === t) return n;
        return - 1
    }
    function o(e) {
        if ("string" != typeof e) {
            if (e && e.toHTML) return e.toHTML();
            if (null == e) return "";
            if (!e) return e + "";
            e = "" + e
        }
        return p.test(e) ? e.replace(d, n) : e
    }
    function a(e) {
        return ! e && 0 !== e || !(!g(e) || 0 !== e.length)
    }
    function s(e) {
        var t = i({},
        e);
        return t._parent = e,
        t
    }
    function l(e, t) {
        return e.path = t,
        e
    }
    function u(e, t) {
        return (e ? e + ".": "") + t
    }
    t.__esModule = !0,
    t.extend = i,
    t.indexOf = r,
    t.escapeExpression = o,
    t.isEmpty = a,
    t.createFrame = s,
    t.blockParams = l,
    t.appendContextPath = u;
    var c = {
        "&": "&amp;",
        "<": "&lt;",
        ">": "&gt;",
        '"': "&quot;",
        "'": "&#x27;",
        "`": "&#x60;",
        "=": "&#x3D;"
    },
    d = /[&<>"'`=]/g,
    p = /[&<>"'`=]/,
    h = Object.prototype.toString;
    t.toString = h;
    var f = function(e) {
        return "function" == typeof e
    };
    f(/x/) && (t.isFunction = f = function(e) {
        return "function" == typeof e && "[object Function]" === h.call(e)
    }),
    t.isFunction = f;
    var g = Array.isArray ||
    function(e) {
        return ! (!e || "object" != typeof e) && "[object Array]" === h.call(e)
    };
    t.isArray = g
},
,
function(e, t, n) {
    var i, r;
    i = [n(2), n(3), n(5), n(1)],
    r = function(e, t, n, i) {
        var r = e.noop,
        o = i.constant(!1),
        a = {
            supports: o,
            play: r,
            load: r,
            stop: r,
            volume: r,
            mute: r,
            seek: r,
            resize: r,
            remove: r,
            destroy: r,
            setVisibility: r,
            setFullscreen: o,
            getFullscreen: r,
            getContainer: r,
            setContainer: o,
            getName: r,
            getQualityLevels: r,
            getCurrentQuality: r,
            setCurrentQuality: r,
            getAudioTracks: r,
            getCurrentAudioTrack: r,
            setCurrentAudioTrack: r,
            checkComplete: r,
            setControls: r,
            attachMedia: r,
            detachMedia: r,
            setState: function(e) {
                var i = this.state || n.IDLE;
                this.state = e,
                e !== i && this.trigger(t.JWPLAYER_PLAYER_STATE, {
                    newstate: e
                })
            },
            sendMediaType: function(e) {
                var n = e[0].type,
                i = "oga" === n || "aac" === n || "mp3" === n || "mpeg" === n || "vorbis" === n;
                this.trigger(t.JWPLAYER_MEDIA_TYPE, {
                    mediaType: i ? "audio": "video"
                })
            }
        };
        return a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = {},
        n = {
            TIT2: "title",
            TT2: "title",
            WXXX: "url",
            TPE1: "artist",
            TP1: "artist",
            TALB: "album",
            TAL: "album"
        };
        return t.utf8ArrayToStr = function(e, t) {
            var n, i, r, o, a, s;
            for (n = "", r = e.length, i = t || 0; i < r;) if (o = e[i++], 0 !== o && 3 !== o) switch (o >> 4) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                n += String.fromCharCode(o);
                break;
            case 12:
            case 13:
                a = e[i++],
                n += String.fromCharCode((31 & o) << 6 | 63 & a);
                break;
            case 14:
                a = e[i++],
                s = e[i++],
                n += String.fromCharCode((15 & o) << 12 | (63 & a) << 6 | (63 & s) << 0)
            }
            return n
        },
        t.utf16BigEndianArrayToStr = function(e, t) {
            var n, i, r;
            for (n = "", r = e.length - 1, i = t || 0; i < r;) 254 === e[i] && 255 === e[i + 1] || (n += String.fromCharCode((e[i] << 8) + e[i + 1])),
            i += 2;
            return n
        },
        t.syncSafeInt = function(e) {
            var n = t.arrayToInt(e);
            return 127 & n | (32512 & n) >> 1 | (8323072 & n) >> 2 | (2130706432 & n) >> 3
        },
        t.arrayToInt = function(e) {
            for (var t = "0x",
            n = 0; n < e.length; n++) t += e[n].toString(16);
            return parseInt(t)
        },
        t.parseID3 = function(i) {
            return e.reduce(i,
            function(i, r) {
                if (! ("value" in r) && "data" in r && r.data instanceof ArrayBuffer) {
                    var o = r,
                    a = new Uint8Array(o.data),
                    s = a.length;
                    r = {
                        value: {
                            key: "",
                            data: ""
                        }
                    };
                    for (var l = 10; l < 14 && l < a.length && 0 !== a[l];) r.value.key += String.fromCharCode(a[l]),
                    l++;
                    var u = 19,
                    c = a[u];
                    3 !== c && 0 !== c || (c = a[++u], s--);
                    var d = 0;
                    if (1 !== c && 2 !== c) for (var p = u + 1; p < s; p++) if (0 === a[p]) {
                        d = p - u;
                        break
                    }
                    if (d > 0) {
                        var h = t.utf8ArrayToStr(a.subarray(u, u += d), 0);
                        if ("PRIV" === r.value.key) {
                            if ("com.apple.streaming.transportStreamTimestamp" === h) {
                                var f = 1 & t.syncSafeInt(a.subarray(u, u += 4)),
                                g = t.syncSafeInt(a.subarray(u, u += 4));
                                f && (g += 4294967296),
                                r.value.data = g
                            } else r.value.data = t.utf8ArrayToStr(a, u + 1);
                            r.value.info = h
                        } else r.value.info = h,
                        r.value.data = t.utf8ArrayToStr(a, u + 1)
                    } else {
                        var m = a[u];
                        1 === m || 2 === m ? r.value.data = t.utf16BigEndianArrayToStr(a, u + 1) : r.value.data = t.utf8ArrayToStr(a, u + 1)
                    }
                }
                if (n.hasOwnProperty(r.value.key) && (i[n[r.value.key]] = r.value.data), r.value.info) {
                    var w = i[r.value.key];
                    e.isObject(w) || (w = {},
                    i[r.value.key] = w),
                    w[r.value.info] = r.value.data
                } else i[r.value.key] = r.value.data;
                return i
            },
            {})
        },
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(3), n(4), n(1)],
    r = function(e, t, n) {
        var i = {},
        r = {
            NEW: 0,
            LOADING: 1,
            ERROR: 2,
            COMPLETE: 3
        },
        o = function(o, a) {
            function s(t) {
                c = r.ERROR,
                u.trigger(e.ERROR, t)
            }
            function l(t) {
                c = r.COMPLETE,
                u.trigger(e.COMPLETE, t)
            }
            var u = n.extend(this, t),
            c = r.NEW;
            this.addEventListener = this.on,
            this.removeEventListener = this.off,
            this.makeStyleLink = function(e) {
                var t = document.createElement("link");
                return t.type = "text/css",
                t.rel = "stylesheet",
                t.href = e,
                t
            },
            this.makeScriptTag = function(e) {
                var t = document.createElement("script");
                return t.src = e,
                t
            },
            this.makeTag = a ? this.makeStyleLink: this.makeScriptTag,
            this.load = function() {
                if (c === r.NEW) {
                    var t = i[o];
                    if (t && (c = t.getStatus(), c < 2)) return t.on(e.ERROR, s),
                    void t.on(e.COMPLETE, l);
                    var n = document.getElementsByTagName("head")[0] || document.documentElement,
                    u = this.makeTag(o),
                    d = !1;
                    u.onload = u.onreadystatechange = function(e) {
                        d || this.readyState && "loaded" !== this.readyState && "complete" !== this.readyState || (d = !0, l(e), u.onload = u.onreadystatechange = null, n && u.parentNode && !a && n.removeChild(u))
                    },
                    u.onerror = s,
                    n.insertBefore(u, n.firstChild),
                    c = r.LOADING,
                    i[o] = this
                }
            },
            this.getStatus = function() {
                return c
            }
        };
        return o.loaderstatus = r,
        o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = "free",
        n = "premium",
        i = "enterprise",
        r = "platinum",
        o = "ads",
        a = "unlimited",
        s = "trial",
        l = {
            setup: [t, n, i, o, a, s, r],
            dash: [n, i, o, a, s, r],
            drm: [i, o, a, s],
            hls: [n, o, i, a, s, r],
            ads: [o, a, s, r, i],
            casting: [n, i, o, a, s, r],
            jwpsrv: [t, n, i, o, s, r]
        },
        u = function(t) {
            return function(n) {
                return e.contains(l[n], t)
            }
        };
        return u
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t) {
    "use strict";
    function n(e, t) {
        var r = t && t.loc,
        o = void 0,
        a = void 0;
        r && (o = r.start.line, a = r.start.column, e += " - " + o + ":" + a);
        for (var s = Error.prototype.constructor.call(this, e), l = 0; l < i.length; l++) this[i[l]] = s[i[l]];
        Error.captureStackTrace && Error.captureStackTrace(this, n),
        r && (this.lineNumber = o, this.column = a)
    }
    t.__esModule = !0;
    var i = ["description", "fileName", "lineNumber", "message", "name", "number", "stack"];
    n.prototype = new Error,
    t["default"] = n,
    e.exports = t["default"]
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return {
            createId: function(e, t) {
                var n, i = e.kind || "cc";
                return n = e["default"] || e.defaulttrack ? "default": e._id || e.file || e.name || e.label || i + t
            },
            createLabel: function(e, t) {
                var n = e.label || e.name || e.language;
                return n || (n = "Unknown CC", t += 1, t > 1 && (n += " [" + t + "]")),
                {
                    label: n,
                    unknownCount: t
                }
            }
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2), n(20), n(30), n(127), n(46)],
    r = function(e, t, i, r, o, a) {
        function s(e, t, n, a) {
            var s, u, c = e.responseXML ? e.responseXML.firstChild: null;
            if (c) for ("xml" === i.localName(c) && (c = c.nextSibling); c.nodeType === c.COMMENT_NODE;) c = c.nextSibling;
            try {
                if (c && "tt" === i.localName(c)) s = o(e.responseXML),
                u = this.convertToVTTCues(s),
                delete t.xhr,
                n(u);
                else {
                    var d = e.responseText;
                    d.indexOf("WEBVTT") >= 0 ? l(d, t, n, a) : (s = r(d), u = this.convertToVTTCues(s), delete t.xhr, n(u))
                }
            } catch(p) {
                delete t.xhr,
                a(p)
            }
        }
        function l(e, t, i, r) {
            n.e(11,
            function(require) {
                var o = n(47),
                a = new o(window),
                s = [];
                a.oncue = function(e) {
                    s.push(e)
                },
                a.onflush = function() {
                    delete t.xhr,
                    i(s)
                };
                try {
                    a.parse(e).flush()
                } catch(l) {
                    delete t.xhr,
                    r(l)
                }
            })
        }
        var u = {};
        return u.loadFile = function(e, n, i) {
            e.xhr = t.ajax(e.file,
            function(t) {
                s.call(u, t, e, n, i)
            },
            i)
        },
        u.cancelXhr = function(t) {
            e.each(t,
            function(e) {
                var t = e.xhr;
                t && (t.onload = null, t.onreadystatechange = null, t.onerror = null, "abort" in t && t.abort()),
                delete e.xhr
            })
        },
        u.convertToVTTCues = function(t) {
            var n = e.map(t,
            function(e) {
                return new a(e.begin, e.end, e.text)
            });
            return n
        },
        u
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8)],
    r = function(e) {
        return {
            localName: function(e) {
                return e ? e.localName ? e.localName: e.baseName ? e.baseName: "": ""
            },
            textContent: function(t) {
                return t ? t.textContent ? e.trim(t.textContent) : t.text ? e.trim(t.text) : "": ""
            },
            getChildNode: function(e, t) {
                return e.childNodes[t]
            },
            numChildren: function(e) {
                return e.childNodes ? e.childNodes.length: 0
            }
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(131), n(132), n(52), n(26)],
    r = function(e, t, n, i) {
        var r = {},
        o = {},
        a = function(n, i) {
            return o[n] = new e(new t(r), i),
            o[n]
        },
        s = function(e, t, o, a) {
            var s = i.getPluginName(e);
            r[s] || (r[s] = new n(e)),
            r[s].registerPlugin(e, t, o, a)
        };
        return {
            loadPlugins: a,
            registerPlugin: s
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return {
        	//请求播放器路径
            repo: "/",
            SkinsIncluded: ["seven"],
            SkinsLoadable: ["beelden", "bekle", "five", "glow", "roundster", "six", "stormtrooper", "vapor"],
            dvrSeekLimit: -25
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8), n(68)],
    r = function(e, t) {
        function n(e) {
            e = e.split("-");
            for (var t = 1; t < e.length; t++) e[t] = e[t].charAt(0).toUpperCase() + e[t].slice(1);
            return e.join("")
        }
        function i(t, n, i) {
            if ("" === n || void 0 === n || null === n) return "";
            var r = i ? " !important": "";
            return "string" == typeof n && isNaN(n) ? /png|gif|jpe?g/i.test(n) && n.indexOf("url") < 0 ? "url(" + n + ")": n + r: 0 === n || "z-index" === t || "opacity" === t ? "" + n + r: /color/i.test(t) ? "#" + e.pad(n.toString(16).replace(/^0x/i, ""), 6) + r: Math.ceil(n) + "px" + r
        }
        var r = function(e, n, i) {
            i = i || "all-players";
            var r = "";
            if ("object" == typeof n) {
                var a = document.createElement("div");
                o(a, n),
                r = "{" + a.style.cssText + "}"
            } else "string" == typeof n && (r = n);
            t.style([[e, e + r]], i)
        },
        o = function(e, t) {
            if (void 0 !== e && null !== e) {
                void 0 === e.length && (e = [e]);
                var r, o = {};
                for (r in t) o[r] = i(r, t[r]);
                for (var a = 0; a < e.length; a++) {
                    var s, l = e[a];
                    if (void 0 !== l && null !== l) for (r in o) s = n(r),
                    l.style[s] !== o[r] && (l.style[s] = o[r])
                }
            }
        },
        a = function(e, t) {
            o(e, {
                transform: t,
                webkitTransform: t,
                msTransform: t,
                mozTransform: t,
                oTransform: t
            })
        },
        s = function(e, t) {
            var n = "rgb";
            e ? (e = String(e).replace("#", ""), 3 === e.length && (e = e[0] + e[0] + e[1] + e[1] + e[2] + e[2])) : e = "000000";
            var i = [parseInt(e.substr(0, 2), 16), parseInt(e.substr(2, 2), 16), parseInt(e.substr(4, 2), 16)];
            return void 0 !== t && 100 !== t && (n += "a", i.push(t / 100)),
            n + "(" + i.join(",") + ")"
        };
        return {
            css: r,
            style: o,
            clearCss: t.clear,
            transform: a,
            hexToRgba: s
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
    	//请求文件路径
        return "js/jwplayer"
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(39), n(123), n(1), n(4), n(139), n(3), n(5)],
    r = function(e, t, n, i, r, o, a, s) {
        var l = function() {
            function o(e, t) {
                var n = i.extend({},
                t, {
                    type: e
                });
                switch (e) {
                case "flashThrottle":
                    var r = "resume" !== t.state;
                    this.set("flashThrottle", r),
                    this.set("flashBlocked", r);
                    break;
                case "flashBlocked":
                    return void this.set("flashBlocked", !0);
                case "flashUnblocked":
                    return void this.set("flashBlocked", !1);
                case "volume":
                    return void this.set(e, t[e]);
                case "mute":
                    return void(this.get("autostartMuted") || this.set(e, t[e]));
                case a.JWPLAYER_MEDIA_TYPE:
                    return void(this.mediaModel.get("mediaType") !== t.mediaType && (this.mediaModel.set("mediaType", t.mediaType), this.mediaController.trigger(e, n)));
                case a.JWPLAYER_PLAYER_STATE:
                    return void this.mediaModel.set("state", t.newstate);
                case a.JWPLAYER_MEDIA_BUFFER:
                    this.set("buffer", t.bufferPercent);
                case a.JWPLAYER_MEDIA_META:
                    var o = t.duration;
                    i.isNumber(o) && !i.isNaN(o) && (this.mediaModel.set("duration", o), this.set("duration", o));
                    break;
                case a.JWPLAYER_MEDIA_BUFFER_FULL:
                    this.mediaModel.get("playAttempt") ? this.playVideo() : this.mediaModel.on("change:playAttempt",
                    function() {
                        this.playVideo()
                    },
                    this);
                    break;
                case a.JWPLAYER_MEDIA_TIME:
                    this.mediaModel.set("position", t.position),
                    this.set("position", t.position),
                    i.isNumber(t.duration) && (this.mediaModel.set("duration", t.duration), this.set("duration", t.duration));
                    break;
                case a.JWPLAYER_PROVIDER_CHANGED:
                    this.set("provider", d.getName());
                    break;
                case a.JWPLAYER_MEDIA_LEVELS:
                    this.setQualityLevel(t.currentQuality, t.levels),
                    this.mediaModel.set("levels", t.levels);
                    break;
                case a.JWPLAYER_MEDIA_LEVEL_CHANGED:
                    this.setQualityLevel(t.currentQuality, t.levels),
                    this.persistQualityLevel(t.currentQuality, t.levels);
                    break;
                case a.JWPLAYER_AUDIO_TRACKS:
                    this.setCurrentAudioTrack(t.currentTrack, t.tracks),
                    this.mediaModel.set("audioTracks", t.tracks);
                    break;
                case a.JWPLAYER_AUDIO_TRACK_CHANGED:
                    this.setCurrentAudioTrack(t.currentTrack, t.tracks);
                    break;
                case "subtitlesTrackChanged":
                    this.persistVideoSubtitleTrack(t.currentTrack, t.tracks);
                    break;
                case "visualQuality":
                    var s = i.extend({},
                    t);
                    this.mediaModel.set("visualQuality", s);
                    break;
                case "autoplayFailed":
                    this.set("autostartFailed", !0)
                }
                this.mediaController.trigger(e, n)
            }
            function l() {
                return !! e.isIOS() && !(e.isIOS(6) || e.isIOS(7) || e.isIOS(8) || e.isIOS(9))
            }
            var c, d, p = this,
            h = e.noop;
            this.mediaController = i.extend({},
            r),
            this.mediaModel = new u,
            n.model(this),
            this.set("mediaModel", this.mediaModel),
            this.setup = function(e) {
                return i.extend(this.attributes, e, {
                    item: 0,
                    state: s.IDLE,
                    flashBlocked: !1,
                    fullscreen: !1,
                    scrubbing: !1,
                    duration: 0,
                    position: 0,
                    buffer: 0
                }),
                this.updateProviders(),
                this
            },
            this.getConfiguration = function() {
                return i.omit(this.clone(), ["mediaModel"])
            },
            this.updateProviders = function() {
                c = new t(this.getConfiguration())
            },
            this.setQualityLevel = function(e, t) {
                e > -1 && t.length > 1 && "youtube" !== d.getName().name && this.mediaModel.set("currentLevel", parseInt(e))
            },
            this.persistQualityLevel = function(e, t) {
                var n = t[e] || {},
                i = n.label;
                this.set("qualityLabel", i)
            },
            this.setCurrentAudioTrack = function(e, t) {
                e > -1 && t.length > 0 && e < t.length && this.mediaModel.set("currentAudioTrack", parseInt(e))
            },
            this.onMediaContainer = function() {
                var e = this.get("mediaContainer");
                h.setContainer(e)
            },
            this.changeVideoProvider = function(e) {
                if (this.off("change:mediaContainer", this.onMediaContainer), d && (d.off(null, null, this), d.getContainer() && d.remove(), delete d.instreamMode), !e) return d = h = e,
                void this.set("provider", void 0);
                h = new e(p.get("id"), p.getConfiguration());
                var t = this.get("mediaContainer");
                t ? h.setContainer(t) : this.once("change:mediaContainer", this.onMediaContainer),
                this.set("provider", h.getName()),
                h.getName().name.indexOf("flash") === -1 && (this.set("flashThrottle", void 0), this.set("flashBlocked", !1)),
                d = h,
                d.volume(p.get("volume")),
                d.mute(this.autoStartOnMobile() || p.get("mute")),
                d.on("all", o, this),
                this.get("instreamMode") === !0 && (d.instreamMode = !0),
                this.set("renderCaptionsNatively", d.renderNatively)
            },
            this.destroy = function() {
                this.off(),
                d && (d.off(null, null, this), d.destroy())
            },
            this.getVideo = function() {
                return d
            },
            this.setFullscreen = function(e) {
                e = !!e,
                e !== p.get("fullscreen") && p.set("fullscreen", e)
            },
            this.chooseProvider = function(e) {
                return c.choose(e).provider
            },
            this.setItemIndex = function(e) {
                var t = this.get("playlist");
                e = parseInt(e, 10) || 0,
                e = (e + t.length) % t.length,
                this.set("item", e),
                this.set("playlistItem", t[e]),
                this.setActiveItem(t[e])
            },
            this.setActiveItem = function(t) {
                this.mediaModel.off(),
                this.mediaModel = new u,
                this.set("mediaModel", this.mediaModel),
                this.set("position", t.starttime || 0),
                this.set("minDvrWindow", t.minDvrWindow),
                this.set("duration", t.duration && e.seconds(t.duration) || 0),
                this.setProvider(t)
            },
            this.setProvider = function(e) {
                var t = e && e.sources && e.sources[0];
                if (void 0 !== t) {
                    var n = this.chooseProvider(t);
                    n && h instanceof n || p.changeVideoProvider(n),
                    h && (h.init && h.init(e), this.trigger("itemReady", e))
                }
            },
            this.getProviders = function() {
                return c
            },
            this.resetProvider = function() {
                h = null
            },
            this.setVolume = function(e) {
                e = Math.round(e),
                this.set("volume", e),
                d && d.volume(e);
                var t = 0 === e;
                t !== this.get("mute") && this.setMute(t)
            },
            this.setMute = function(t) {
                if (e.exists(t) || (t = !(this.get("autostartMuted") || this.get("mute"))), this.set("mute", t), d && d.mute(t), !t) {
                    var n = Math.max(10, this.get("volume"));
                    this.set("autostartMuted", !1),
                    this.setVolume(n)
                }
            },
            this.loadVideo = function(t) {
                if (!t) {
                    var n = this.get("item");
                    t = this.get("playlist")[n]
                }
                this.set("position", t.starttime || 0),
                this.set("duration", t.duration && e.seconds(t.duration) || 0),
                this.mediaModel.set("playAttempt", !0),
                this.mediaController.trigger(a.JWPLAYER_MEDIA_PLAY_ATTEMPT, {
                    playReason: this.get("playReason")
                }),
                d.load(t)
            },
            this.stopVideo = function() {
                d && d.stop()
            },
            this.playVideo = function() {
                d.play()
            },
            this.persistCaptionsTrack = function() {
                var e = this.get("captionsTrack");
                e ? this.set("captionLabel", e.name) : this.set("captionLabel", "Off")
            },
            this.setVideoSubtitleTrack = function(e, t) {
                this.set("captionsIndex", e),
                e && t && e <= t.length && t[e - 1].data && this.set("captionsTrack", t[e - 1]),
                d && d.setSubtitlesTrack && d.setSubtitlesTrack(e)
            },
            this.persistVideoSubtitleTrack = function(e, t) {
                this.setVideoSubtitleTrack(e, t),
                this.persistCaptionsTrack()
            },
            this.setNextUp = function(e) {
                this.set("nextUp", e)
            },
            this.autoStartOnMobile = function() {
                return this.get("autostart") && !this.get("sdkplatform") && (l() && e.isSafari() || e.isAndroid() && e.isChrome()) && (!this.get("advertising") || this.get("advertising").autoplayadsmuted)
            }
        },
        u = l.MediaModel = function() {
            this.set("state", s.IDLE)
        };
        return i.extend(l.prototype, o),
        i.extend(u.prototype, o),
        l
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8)],
    r = function(e) {
        var t = {},
        n = t.pluginPathType = {
            ABSOLUTE: 0,
            RELATIVE: 1,
            CDN: 2
        };
        return t.getPluginPathType = function(t) {
            if ("string" == typeof t) {
                t = t.split("?")[0];
                var i = t.indexOf("://");
                if (i > 0) return n.ABSOLUTE;
                var r = t.indexOf("/"),
                o = e.extension(t);
                return ! (i < 0 && r < 0) || o && isNaN(o) ? n.RELATIVE: n.CDN
            }
        },
        t.getPluginName = function(e) {
            return e.replace(/^(.*\/)?([^-]*)-?.*\.(swf|js)$/, "$2")
        },
        t.getPluginVersion = function(e) {
            return e.replace(/[^-]*-?([^\.]*).*$/, "$1")
        },
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        function t(e) {
            return function() {
                return i(e)
            }
        }
        var n = {},
        i = e.memoize(function(e) {
            var t = navigator.userAgent.toLowerCase();
            return null !== t.match(e)
        }),
        r = n.isInt = function(e) {
            return parseFloat(e) % 1 === 0
        };
        n.isFlashSupported = function() {
            var e = n.flashVersion();
            return e && e >= 11.2
        },
        n.isFF = t(/gecko\//i),
        n.isIPod = t(/iP(hone|od)/i),
        n.isIPad = t(/iPad/i),
        n.isSafari602 = t(/Macintosh.*Mac OS X 10_8.*6\.0\.\d* Safari/i),
        n.isOSX = t(/Mac OS X/i);
        var o = n.isEdge = function(e) {
            return i(e ? new RegExp("\\sedge\\/" + e, "i") : /\sEdge\/\d+/i)
        },
        a = n.isIETrident = t(/trident\/.+rv:\s*11/i),
        s = n.isMSIE = function(e) {
            return e ? (e = parseFloat(e).toFixed(1), i(new RegExp("msie\\s*" + e, "i"))) : i(/msie/i)
        };
        n.isChrome = function() {
            return i(/\s(?:Chrome|CriOS)\//i) && !n.isEdge()
        },
        n.isIE = function(e) {
            return e ? (e = parseFloat(e).toFixed(1), e >= 12 ? o(e) : e >= 11 ? a() : s(e)) : o() || a() || s()
        },
        n.isSafari = function() {
            return i(/safari/i) && !i(/chrome/i) && !i(/crios/i) && !i(/chromium/i) && !i(/android/i)
        };
        var l = n.isIOS = function(e) {
            return i(e ? new RegExp("iP(hone|ad|od).+\\s(OS\\s" + e + "|.*\\sVersion/" + e + ")", "i") : /iP(hone|ad|od)/i)
        };
        n.isAndroidNative = function(e) {
            return u(e, !0)
        };
        var u = n.isAndroid = function(e, t) {
            return ! (t && i(/chrome\/[123456789]/i) && !i(/chrome\/18/)) && (e ? (r(e) && !/\./.test(e) && (e = "" + e + "."), i(new RegExp("Android\\s*" + e, "i"))) : i(/Android/i))
        };
        return n.isMobile = function() {
            return l() || u()
        },
        n.isIframe = function() {
            return window.frameElement && "IFRAME" === window.frameElement.nodeName
        },
        n.flashVersion = function() {
            if (n.isAndroid()) return 0;
            var e, t = navigator.plugins;
            if (t && (e = t["Shockwave Flash"], e && e.description)) return parseFloat(e.description.replace(/\D+(\d+\.?\d*).*/, "$1"));
            if ("undefined" != typeof window.ActiveXObject) {
                try {
                    if (e = new window.ActiveXObject("ShockwaveFlash.ShockwaveFlash")) return parseFloat(e.GetVariable("$version").split(" ")[1].replace(/\s*,\s*/, "."))
                } catch(i) {
                    return 0
                }
                return e
            }
            return 0
        },
        n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        var e = window.chrome,
        t = {};
        return t.NS = "urn:x-cast:com.longtailvideo.jwplayer",
        t.debug = !1,
        t.availability = null,
        t.available = function(n) {
            n = n || t.availability;
            var i = "available";
            return e && e.cast && e.cast.ReceiverAvailability && (i = e.cast.ReceiverAvailability.AVAILABLE),
            n === i
        },
        t.log = function() {
            if (t.debug) {
                var e = Array.prototype.slice.call(arguments, 0);
                console.log.apply(console, e)
            }
        },
        t.error = function() {
            var e = Array.prototype.slice.call(arguments, 0);
            console.error.apply(console, e)
        },
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(5)],
    r = function(e) {
        function t(t) {
            return t === e.COMPLETE || t === e.ERROR ? e.IDLE: t
        }
        return function(e, n, i) {
            if (n = t(n), i = t(i), n !== i) {
                var r = n.replace(/(?:ing|d)$/, ""),
                o = {
                    type: r,
                    newstate: n,
                    oldstate: i,
                    reason: e.mediaModel.get("state")
                };
                "play" === r ? o.playReason = e.get("playReason") : "pause" === r && (o.pauseReason = e.get("pauseReason")),
                this.trigger(r, o)
            }
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(8)],
    r = function(e, t) {
        function n(e) {
            var t = {},
            n = e.split("\r\n");
            1 === n.length && (n = e.split("\n"));
            var r = 1;
            if (n[0].indexOf(" --> ") > 0 && (r = 0), n.length > r + 1 && n[r + 1]) {
                var o = n[r],
                a = o.indexOf(" --> ");
                a > 0 && (t.begin = i(o.substr(0, a)), t.end = i(o.substr(a + 5)), t.text = n.slice(r + 1).join("\r\n"))
            }
            return t
        }
        var i = e.seconds;
        return function(e) {
            var i = [];
            e = t.trim(e);
            var r = e.split("\r\n\r\n");
            1 === r.length && (r = e.split("\n\n"));
            for (var o = 0; o < r.length; o++) if ("WEBVTT" !== r[o]) {
                var a = n(r[o]);
                a.text && i.push(a)
            }
            return i
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(51), n(130)],
    r = function(e, t, n) {
        var i = {
            sources: [],
            tracks: [],
            minDvrWindow: 120
        };
        return function(r) {
            r = r || {},
            e.isArray(r.tracks) || delete r.tracks;
            var o = e.extend({},
            i, r);
            e.isObject(o.sources) && !e.isArray(o.sources) && (o.sources = [t(o.sources)]),
            e.isArray(o.sources) && 0 !== o.sources.length || (r.levels ? o.sources = r.levels: o.sources = [t(r)]);
            for (var a = 0; a < o.sources.length; a++) {
                var s = o.sources[a];
                if (s) {
                    var l = s["default"];
                    l ? s["default"] = "true" === l.toString() : s["default"] = !1,
                    o.sources[a].label || (o.sources[a].label = a.toString()),
                    o.sources[a] = t(o.sources[a])
                }
            }
            return o.sources = e.compact(o.sources),
            e.isArray(o.tracks) || (o.tracks = []),
            e.isArray(o.captions) && (o.tracks = o.tracks.concat(o.captions), delete o.captions),
            o.tracks = e.compact(e.map(o.tracks, n)),
            o
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8), n(1), n(138)],
    r = function(e, t, n) {
        var i = {};
        i.createElement = function(e) {
            var t = document.createElement("div");
            return t.innerHTML = e,
            t.firstChild
        },
        i.styleDimension = function(e) {
            return e + (e.toString().indexOf("%") > 0 ? "": "px")
        };
        var r = function(e) {
            return t.isString(e.className) ? e.className.split(" ") : []
        },
        o = function(t, n) {
            n = e.trim(n),
            t.className !== n && (t.className = n)
        };
        return i.classList = function(e) {
            return e.classList ? e.classList: r(e)
        },
        i.hasClass = n.hasClass,
        i.addClass = function(e, n) {
            var i = r(e),
            a = t.isArray(n) ? n: n.split(" ");
            t.each(a,
            function(e) {
                t.contains(i, e) || i.push(e)
            }),
            o(e, i.join(" "))
        },
        i.removeClass = function(e, n) {
            var i = r(e),
            a = t.isArray(n) ? n: n.split(" ");
            o(e, t.difference(i, a).join(" "))
        },
        i.replaceClass = function(e, t, n) {
            var i = e.className || "";
            t.test(i) ? i = i.replace(t, n) : n && (i += " " + n),
            o(e, i)
        },
        i.toggleClass = function(e, n, r) {
            var o = i.hasClass(e, n);
            r = t.isBoolean(r) ? r: !o,
            r !== o && (r ? i.addClass(e, n) : i.removeClass(e, n))
        },
        i.emptyElement = function(e) {
            for (; e.firstChild;) e.removeChild(e.firstChild)
        },
        i.addStyleSheet = function(e) {
            var t = document.createElement("link");
            t.rel = "stylesheet",
            t.href = e,
            document.getElementsByTagName("head")[0].appendChild(t)
        },
        i.empty = function(e) {
            if (e) for (; e.childElementCount > 0;) e.removeChild(e.children[0])
        },
        i.bounds = function(e) {
            var t = {
                left: 0,
                right: 0,
                width: 0,
                height: 0,
                top: 0,
                bottom: 0
            };
            if (!e || !document.body.contains(e)) return t;
            var n = e.getBoundingClientRect(),
            i = window.pageYOffset,
            r = window.pageXOffset;
            return n.width || n.height || n.left || n.top ? (t.left = n.left + r, t.right = n.right + r, t.top = n.top + i, t.bottom = n.bottom + i, t.width = n.right - n.left, t.height = n.bottom - n.top, t) : t
        },
        i
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(34)],
    r = function(e, t) {
        function n(e) {
            return /^(?:(?:https?|file)\:)?\/\//.test(e)
        }
        function i(t) {
            return e.some(t,
            function(e) {
                return "parsererror" === e.nodeName
            })
        }
        var r = {};
        return r.getAbsolutePath = function(e, i) {
            if (t.exists(i) || (i = document.location.href), t.exists(e)) {
                if (n(e)) return e;
                var r, o = i.substring(0, i.indexOf("://") + 3),
                a = i.substring(o.length, i.indexOf("/", o.length + 1));
                if (0 === e.indexOf("/")) r = e.split("/");
                else {
                    var s = i.split("?")[0];
                    s = s.substring(o.length + a.length + 1, s.lastIndexOf("/")),
                    r = s.split("/").concat(e.split("/"))
                }
                for (var l = [], u = 0; u < r.length; u++) r[u] && t.exists(r[u]) && "." !== r[u] && (".." === r[u] ? l.pop() : l.push(r[u]));
                return o + a + "/" + l.join("/")
            }
        },
        r.getScriptPath = e.memoize(function(e) {
            for (var t = document.getElementsByTagName("script"), n = 0; n < t.length; n++) {
                var i = t[n].src;
                if (i && i.indexOf(e) >= 0) return i.substr(0, i.indexOf(e))
            }
            return ""
        }),
        r.parseXML = function(e) {
            var t = null;
            try {
                "DOMParser" in window ? (t = (new window.DOMParser).parseFromString(e, "text/xml"), (i(t.childNodes) || t.childNodes && i(t.childNodes[0].childNodes)) && (t = null)) : (t = new window.ActiveXObject("Microsoft.XMLDOM"), t.async = "false", t.loadXML(e))
            } catch(n) {}
            return t
        },
        r.serialize = function(e) {
            if (void 0 === e) return null;
            if ("string" == typeof e && e.length < 6) {
                var t = e.toLowerCase();
                if ("true" === t) return ! 0;
                if ("false" === t) return ! 1;
                if (!isNaN(Number(e)) && !isNaN(parseFloat(e))) return Number(e)
            }
            return e
        },
        r.parseDimension = function(e) {
            return "string" == typeof e ? "" === e ? 0 : e.lastIndexOf("%") > -1 ? e: parseInt(e.replace("px", ""), 10) : e
        },
        r.timeFormat = function(t, n) {
            if (t <= 0 && !n || e.isNaN(parseInt(t))) return "00:00";
            var i = t < 0 ? "-": "";
            t = Math.abs(t);
            var r = Math.floor(t / 3600),
            o = Math.floor((t - 3600 * r) / 60),
            a = Math.floor(t % 60);
            return i + (r ? r + ":": "") + (o < 10 ? "0": "") + o + ":" + (a < 10 ? "0": "") + a
        },
        r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = {};
        return t.exists = function(e) {
            switch (typeof e) {
            case "string":
                return e.length > 0;
            case "object":
                return null !== e;
            case "undefined":
                return ! 1
            }
            return ! 0
        },
        t.isHTTPS = function() {
            return 0 === window.location.href.indexOf("https")
        },
        t.isRtmp = function(e, t) {
            return 0 === e.indexOf("rtmp") || "rtmp" === t
        },
        t.isYouTube = function(e, t) {
            return "youtube" === t || /^(http|\/\/).*(youtube\.com|youtu\.be)\/.+/.test(e)
        },
        t.youTubeID = function(e) {
            var t = /v[=\/]([^?&]*)|youtu\.be\/([^?]*)|^([\w-]*)$/i.exec(e);
            return t ? t.slice(1).join("").replace("?", "") : ""
        },
        t.typeOf = function(t) {
            if (null === t) return "null";
            var n = typeof t;
            return "object" === n && e.isArray(t) ? "array": n
        },
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(62), n(7), n(94), n(2)],
    r = function(e, t, n, i) {
        var r = function(e) {
            var t = i.bounds(e),
            n = window.pageXOffset;
            return n && i.isAndroid() && document.body.parentElement.getBoundingClientRect().left >= 0 && (t.left -= n, t.right -= n),
            t
        },
        o = e.extend({
            constructor: function(e, t) {
                this.className = e + " jw-background-color jw-reset",
                this.orientation = t,
                this.dragStartListener = this.dragStart.bind(this),
                this.dragMoveListener = this.dragMove.bind(this),
                this.dragEndListener = this.dragEnd.bind(this),
                this.tapListener = this.tap.bind(this),
                this.setup()
            },
            setup: function() {
                var e = {
                    "default": this["default"],
                    className: this.className,
                    orientation: "jw-slider-" + this.orientation
                };
                this.el = i.createElement(n(e)),
                this.elementRail = this.el.getElementsByClassName("jw-slider-container")[0],
                this.elementBuffer = this.el.getElementsByClassName("jw-buffer")[0],
                this.elementProgress = this.el.getElementsByClassName("jw-progress")[0],
                this.elementThumb = this.el.getElementsByClassName("jw-knob")[0],
                this.userInteract = new t(this.element(), {
                    preventScrolling: !0
                }),
                this.userInteract.on("dragStart", this.dragStartListener),
                this.userInteract.on("drag", this.dragMoveListener),
                this.userInteract.on("dragEnd", this.dragEndListener),
                this.userInteract.on("tap click", this.tapListener)
            },
            dragStart: function() {
                this.trigger("dragStart"),
                this.railBounds = r(this.elementRail)
            },
            dragEnd: function(e) {
                this.dragMove(e),
                this.trigger("dragEnd")
            },
            dragMove: function(e) {
                var t, n, o = this.railBounds = this.railBounds ? this.railBounds: r(this.elementRail);
                "horizontal" === this.orientation ? (t = e.pageX, n = t < o.left ? 0 : t > o.right ? 100 : 100 * i.between((t - o.left) / o.width, 0, 1)) : (t = e.pageY, n = t >= o.bottom ? 0 : t <= o.top ? 100 : 100 * i.between((o.height - (t - o.top)) / o.height, 0, 1));
                var a = this.limit(n);
                return this.render(a),
                this.update(a),
                !1
            },
            tap: function(e) {
                this.railBounds = r(this.elementRail),
                this.dragMove(e)
            },
            limit: function(e) {
                return e
            },
            update: function(e) {
                this.trigger("update", {
                    percentage: e
                })
            },
            render: function(e) {
                e = Math.max(0, Math.min(e, 100)),
                "horizontal" === this.orientation ? (this.elementThumb.style.left = e + "%", this.elementProgress.style.width = e + "%") : (this.elementThumb.style.bottom = e + "%", this.elementProgress.style.height = e + "%")
            },
            updateBuffer: function(e) {
                this.elementBuffer.style.width = e + "%"
            },
            element: function() {
                return this.el
            }
        });
        return o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(62), n(2)],
    r = function(e, t) {
        var n = e.extend({
            constructor: function(e, t, n) {
                this.el = document.createElement("div"),
                this.el.className = "jw-icon jw-icon-tooltip " + e + " jw-button-color jw-reset jw-hidden",
                t && (this.el.setAttribute("tabindex", "0"), this.el.setAttribute("role", "button"), this.el.setAttribute("aria-label", t)),
                n === !0 ? this.el.setAttribute("aria-hidden", "false") : this.el.setAttribute("aria-hidden", "true"),
                this.container = document.createElement("div"),
                this.container.className = "jw-overlay jw-reset",
                this.openClass = "jw-open",
                this.componentType = "tooltip",
                this.el.appendChild(this.container)
            },
            addContent: function(e) {
                this.content && this.removeContent(),
                this.content = e,
                this.container.appendChild(e)
            },
            removeContent: function() {
                this.content && (this.container.removeChild(this.content), this.content = null)
            },
            hasContent: function() {
                return !! this.content
            },
            element: function() {
                return this.el
            },
            openTooltip: function(e) {
                this.trigger("open-" + this.componentType, e, {
                    isOpen: !0
                }),
                this.isOpen = !0,
                t.toggleClass(this.el, this.openClass, this.isOpen)
            },
            closeTooltip: function(e) {
                this.trigger("close-" + this.componentType, e, {
                    isOpen: !1
                }),
                this.isOpen = !1,
                t.toggleClass(this.el, this.openClass, this.isOpen)
            },
            toggleOpenState: function(e) {
                this.isOpen ? this.closeTooltip(e) : this.openTooltip(e)
            }
        });
        return n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(16)],
    r = function(e, t) {
        var n, i = [{
            configName: "clearkey",
            keyName: "org.w3.clearkey"
        },
        {
            configName: "widevine",
            keyName: "com.widevine.alpha"
        },
        {
            configName: "playready",
            keyName: "com.microsoft.playready"
        }],
        r = [],
        o = {},
        a = function(t) {
            var n = t.get("playlist");
            return !! t.get("drm") || e.some(n,
            function(t) {
                return !! t.drm || e.some(t.sources,
                function(e) {
                    return !! e.drm
                })
            })
        },
        s = function(e) {
            return new Promise(function(t, n) {
                var i;
                try {
                    i = new window.MSMediaKeys(e)
                } catch(r) {}
                i ? t() : n()
            })
        },
        l = function(t) {
            var a = s;
            return navigator.requestMediaKeySystemAccess && (a = navigator.requestMediaKeySystemAccess.bind(navigator)),
            n ? n.then(t) : (e.forEach(i,
            function(e) {
                var t = a(e.keyName, [{}]).then(function() {
                    o[e.configName] = !0
                },
                function() {
                    o[e.configName] = !1
                });
                r.push(t)
            }), n = Promise.all(r).then(t))
        },
        u = function() {
            return !! navigator.requestMediaKeySystemAccess && !!MediaKeySystemAccess.prototype.getConfiguration || !!window.MSMediaKeys
        },
        c = function(e) {
            return o[e]
        },
        d = function(t) {
            n || console.error('DRM only supported with "drm" block in initial setup.', t);
            var i = e.keys(t);
            return e.some(i,
            function(e) {
                return c(e)
            })
        };
        return {
            containsDrm: a,
            probe: function(e, n) {
                u() && t(n)("drm") ? l(e) : e()
            },
            anySupported: d,
            isSupported: c
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(27), n(16), n(1), n(135), n(37)],
    r = function(e, t, n, i, r) {
        function o() {
            return !! window.MediaSource && !!window.MediaSource.isTypeSupported && window.MediaSource.isTypeSupported('video/mp4;codecs="avc1.4d400d,mp4a.40.2"')
        }
        function a(i, o) {
            if (e.isSafari()) return ! 1;
            var a = t(o);
            if (!a("dash")) return ! 1;
            if (i.drm && !r.anySupported(i.drm)) return ! 1;
            var s = window.MediaSource;
            if (!window.HTMLVideoElement || !s) return ! 1;
            var l = !0;
            return i.mediaTypes && (l = n.all(i.mediaTypes,
            function(e) {
                return s.isTypeSupported(e)
            })),
            l && ("dash" === i.type || "mpd" === i.type || (i.file || "").indexOf("mpd-time-csf") > -1)
        }
        var s = n.find(i, n.matches({
            name: "flash"
        })),
        l = s.supports;
        s.supports = function(n, i) {
            if (!e.isFlashSupported() || n.drm) return ! 1;
            var r = n && n.type;
            if ("hls" === r || "m3u8" === r) {
                var o = t(i);
                return o("hls")
            }
            return l.apply(this, arguments)
        };
        var u = n.find(i, n.matches({
            name: "html5"
        })),
        c = u.supports;
        return u.supports = function(e, n) {
            var i = c.apply(this, arguments);
            if (i && e.drm && "hls" === e.type) {
                var r = t(n),
                o = r("drm");
                if (o && e.drm.fairplay) {
                    var a = window.WebKitMediaKeys;
                    return a && a.isTypeSupported && a.isTypeSupported("com.apple.fps.1_0", "video/mp4")
                }
                return o
            }
            return i
        },
        i.push({
            name: "shaka",
            supports: a
        }),
        i.push({
            name: "hlsjs",
            supports: function(n, i) {
                if ((e.isChrome() || e.isFF()) && o() && !e.isMobile() && !n.drm) {
                    var r = n && n.type,
                    a = n && n.file;
                    if (a.indexOf(".m3u8") > -1 || "hls" === r || "m3u8" === r) {
                        var s = t(i);
                        return s("hls")
                    }
                }
            }
        }),
        i
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(136), n(1), n(38)],
    r = function(e, t) {
        function i(e, n, i) {
            var r = t.indexOf(e, t.findWhere(e, {
                name: i
            })),
            o = t.indexOf(e, t.findWhere(e, {
                name: n
            })),
            a = e.splice(o, 1)[0];
            e.splice(r, 0, a)
        }
        var r, o = e.registerProvider,
        a = e.prototype.reorderProviders;
        return t.extend(e.loaders, {
            shaka: function(e) {
                n.e(2,
                function(require) {
                    var t = n(72);
                    o(t),
                    e(t)
                })
            },
            hlsjs: function(e) {
                n.e(1,
                function(require) {
                    var t = n(43);
                    t["default"] && (t = t["default"]),
                    t.setEdition && t.setEdition(r),
                    o(t),
                    e(t)
                })
            }
        }),
        t.extend(e.prototype, {
            reorderProviders: function(e) {
                var t = a.call(this, e);
                return "flash" !== e && i(t, "hlsjs", "flash"),
                t
            },
            providerSupports: function(e, t) {
                return r = this.config.edition,
                e.supports(t, r)
            }
        }),
        e
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
, , , ,
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    function r(e, t, n) {
        this.helpers = e || {},
        this.partials = t || {},
        this.decorators = n || {},
        l.registerDefaultHelpers(this),
        u.registerDefaultDecorators(this)
    }
    t.__esModule = !0,
    t.HandlebarsEnvironment = r;
    var o = n(11),
    a = n(17),
    s = i(a),
    l = n(99),
    u = n(97),
    c = n(107),
    d = i(c),
    p = "4.0.5";
    t.VERSION = p;
    var h = 7;
    t.COMPILER_REVISION = h;
    var f = {
        1 : "<= 1.0.rc.2",
        2 : "== 1.0.0-rc.3",
        3 : "== 1.0.0-rc.4",
        4 : "== 1.x.x",
        5 : "== 2.0.0-alpha.x",
        6 : ">= 2.0.0-beta.1",
        7 : ">= 4.0.0"
    };
    t.REVISION_CHANGES = f;
    var g = "[object Object]";
    r.prototype = {
        constructor: r,
        logger: d["default"],
        log: d["default"].log,
        registerHelper: function(e, t) {
            if (o.toString.call(e) === g) {
                if (t) throw new s["default"]("Arg not supported with multiple helpers");
                o.extend(this.helpers, e)
            } else this.helpers[e] = t
        },
        unregisterHelper: function(e) {
            delete this.helpers[e]
        },
        registerPartial: function(e, t) {
            if (o.toString.call(e) === g) o.extend(this.partials, e);
            else {
                if ("undefined" == typeof t) throw new s["default"]('Attempting to register a partial called "' + e + '" as undefined');
                this.partials[e] = t
            }
        },
        unregisterPartial: function(e) {
            delete this.partials[e]
        },
        registerDecorator: function(e, t) {
            if (o.toString.call(e) === g) {
                if (t) throw new s["default"]("Arg not supported with multiple decorators");
                o.extend(this.decorators, e)
            } else this.decorators[e] = t
        },
        unregisterDecorator: function(e) {
            delete this.decorators[e]
        }
    };
    var m = d["default"].log;
    t.log = m,
    t.createFrame = o.createFrame,
    t.logger = d["default"]
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(1)],
    r = function(e, t) {
        function i(n) {
            t.each(n,
            function(t, i) {
                n[i] = e.serialize(t)
            })
        }
        function r(e) {
            return e.slice && "px" === e.slice( - 2) && (e = e.slice(0, -2)),
            e
        }
        function o(t, n) {
            if (n.toString().indexOf("%") === -1) return 0;
            if ("string" != typeof t || !e.exists(t)) return 0;
            if (/^\d*\.?\d+%$/.test(t)) return t;
            var i = t.indexOf(":");
            if (i === -1) return 0;
            var r = parseFloat(t.substr(0, i)),
            o = parseFloat(t.substr(i + 1));
            return r <= 0 || o <= 0 ? 0 : o / r * 100 + "%"
        }
        var a = {
            autostart: !1,
            controls: !0,
            displaytitle: !0,
            displaydescription: !0,
            mobilecontrols: !1,
            repeat: !1,
            castAvailable: !1,
            skin: "seven",
            stretching: "uniform",
            mute: !1,
            volume: 90,
            width: 480,
            height: 270,
            localization: {
                play: "Play",
                playback: "Start playback",
                pause: "Pause",
                volume: "Volume",
                prev: "Previous",
                next: "Next",
                cast: "Chromecast",
                fullscreen: "Fullscreen",
                playlist: "Playlist",
                hd: "Quality",
                cc: "Closed captions",
                audioTracks: "Audio tracks",
                replay: "Replay",
                buffer: "Loading",
                more: "More",
                liveBroadcast: "Live broadcast",
                loadingAd: "Loading ad",
                rewind: "Rewind 10s",
                nextUp: "Next Up",
                related: "Related"
            },
            renderCaptionsNatively: !1
        },
        s = function(s, l) {
            var u = l && l.getAllItems(),
            c = t.extend({},
            (window.jwplayer || {}).defaults, u, s);
            i(c),
            c.localization = t.extend({},
            a.localization, c.localization);
            var d = t.extend({},
            a, c);
            "." === d.base && (d.base = e.getScriptPath("jwplayer.js")),
            d.base = (d.base || e.loadFrom()).replace(/\/?$/, "/"),
            n.p = d.base,
            d.width = r(d.width),
            d.height = r(d.height);
            var p = e.getScriptPath("jwplayer.js") || d.base;
            if (d.flashplayer = d.flashplayer || p + "jwplayer.flash.swf", d.flashloader = d.flashloader || p + "jwplayer.loader.swf", "http:" === window.location.protocol && (d.flashplayer = d.flashplayer.replace("https", "http"), d.flashloader = d.flashloader.replace("https", "http")), d.aspectratio = o(d.aspectratio, d.width), t.isObject(d.skin) && (d.skinUrl = d.skin.url, d.skinColorInactive = d.skin.inactive, d.skinColorActive = d.skin.active, d.skinColorBackground = d.skin.background, d.skin = t.isString(d.skin.name) ? d.skin.name: a.skin), t.isString(d.skin) && d.skin.indexOf(".xml") > 0 && (console.log("JW Player does not support XML skins, please update your config"), d.skin = d.skin.replace(".xml", "")), d.aspectratio || delete d.aspectratio, !d.playlist) {
                var h = t.pick(d, ["title", "description", "type", "mediaid", "image", "file", "sources", "tracks", "preload"]);
                d.playlist = [h]
            }
            return d
        };
        return s
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i;
    i = function() {
        function e(e) {
            if ("string" != typeof e) return ! 1;
            var t = o[e.toLowerCase()];
            return !! t && e.toLowerCase()
        }
        function t(e) {
            if ("string" != typeof e) return ! 1;
            var t = a[e.toLowerCase()];
            return !! t && e.toLowerCase()
        }
        function n(e) {
            for (var t = 1; t < arguments.length; t++) {
                var n = arguments[t];
                for (var i in n) e[i] = n[i]
            }
            return e
        }
        function i(i, o, a) {
            var s = this,
            l = /MSIE\s8\.0/.test(navigator.userAgent),
            u = {};
            l ? s = document.createElement("custom") : u.enumerable = !0,
            s.hasBeenReset = !1;
            var c = "",
            d = !1,
            p = i,
            h = o,
            f = a,
            g = null,
            m = "",
            w = !0,
            v = "auto",
            y = "start",
            j = 50,
            b = "middle",
            E = 50,
            x = "middle";
            if (Object.defineProperty(s, "id", n({},
            u, {
                get: function() {
                    return c
                },
                set: function(e) {
                    c = "" + e
                }
            })), Object.defineProperty(s, "pauseOnExit", n({},
            u, {
                get: function() {
                    return d
                },
                set: function(e) {
                    d = !!e
                }
            })), Object.defineProperty(s, "startTime", n({},
            u, {
                get: function() {
                    return p
                },
                set: function(e) {
                    if ("number" != typeof e) throw new TypeError("Start time must be set to a number.");
                    p = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "endTime", n({},
            u, {
                get: function() {
                    return h
                },
                set: function(e) {
                    if ("number" != typeof e) throw new TypeError("End time must be set to a number.");
                    h = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "text", n({},
            u, {
                get: function() {
                    return f
                },
                set: function(e) {
                    f = "" + e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "region", n({},
            u, {
                get: function() {
                    return g
                },
                set: function(e) {
                    g = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "vertical", n({},
            u, {
                get: function() {
                    return m
                },
                set: function(t) {
                    var n = e(t);
                    if (n === !1) throw new SyntaxError("An invalid or illegal string was specified.");
                    m = n,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "snapToLines", n({},
            u, {
                get: function() {
                    return w
                },
                set: function(e) {
                    w = !!e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "line", n({},
            u, {
                get: function() {
                    return v
                },
                set: function(e) {
                    if ("number" != typeof e && e !== r) throw new SyntaxError("An invalid number or illegal string was specified.");
                    v = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "lineAlign", n({},
            u, {
                get: function() {
                    return y
                },
                set: function(e) {
                    var n = t(e);
                    if (!n) throw new SyntaxError("An invalid or illegal string was specified.");
                    y = n,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "position", n({},
            u, {
                get: function() {
                    return j
                },
                set: function(e) {
                    if (e < 0 || e > 100) throw new Error("Position must be between 0 and 100.");
                    j = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "positionAlign", n({},
            u, {
                get: function() {
                    return b
                },
                set: function(e) {
                    var n = t(e);
                    if (!n) throw new SyntaxError("An invalid or illegal string was specified.");
                    b = n,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "size", n({},
            u, {
                get: function() {
                    return E
                },
                set: function(e) {
                    if (e < 0 || e > 100) throw new Error("Size must be between 0 and 100.");
                    E = e,
                    this.hasBeenReset = !0
                }
            })), Object.defineProperty(s, "align", n({},
            u, {
                get: function() {
                    return x
                },
                set: function(e) {
                    var n = t(e);
                    if (!n) throw new SyntaxError("An invalid or illegal string was specified.");
                    x = n,
                    this.hasBeenReset = !0
                }
            })), s.displayState = void 0, l) return s
        }
        if (window.VTTCue) return window.VTTCue;
        var r = "auto",
        o = {
            "": !0,
            lr: !0,
            rl: !0
        },
        a = {
            start: !0,
            middle: !0,
            end: !0,
            left: !0,
            right: !0
        };
        return i.prototype.getCueAsHTML = function() {
            var e = window.WebVTT;
            return e.convertCueToDOMTree(window, this.text)
        },
        i
    }.call(t, n, t, e),
    !(void 0 !== i && (e.exports = i))
},
,
function(e, t, n) {
    var i, r;
    i = [n(8), n(20), n(128), n(129), n(31)],
    r = function(e, t, n, i, r) {
        function o(t) {
            for (var o = {},
            s = 0; s < t.childNodes.length; s++) {
                var l = t.childNodes[s],
                c = u(l);
                if (c) switch (c.toLowerCase()) {
                case "enclosure":
                    o.file = e.xmlAttribute(l, "url");
                    break;
                case "title":
                    o.title = a(l);
                    break;
                case "guid":
                    o.mediaid = a(l);
                    break;
                case "pubdate":
                    o.date = a(l);
                    break;
                case "description":
                    o.description = a(l);
                    break;
                case "link":
                    o.link = a(l);
                    break;
                case "category":
                    o.tags ? o.tags += a(l) : o.tags = a(l)
                }
            }
            return o = i(t, o),
            o = n(t, o),
            new r(o)
        }
        var a = t.textContent,
        s = t.getChildNode,
        l = t.numChildren,
        u = t.localName,
        c = {};
        return c.parse = function(e) {
            for (var t = [], n = 0; n < l(e); n++) {
                var i = s(e, n),
                r = u(i).toLowerCase();
                if ("channel" === r) for (var a = 0; a < l(i); a++) {
                    var c = s(i, a);
                    "item" === u(c).toLowerCase() && t.push(o(c))
                }
            }
            return t
        },
        c
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(20), n(48), n(2), n(3), n(4), n(1)],
    r = function(e, t, n, i, r, o) {
        var a = function() {
            function a(r) {
                var a = n.tryCatch(function() {
                    var n, a = r.responseXML ? r.responseXML.childNodes: null,
                    s = "";
                    if (a) {
                        for (var c = 0; c < a.length && (s = a[c], 8 === s.nodeType); c++);
                        "xml" === e.localName(s) && (s = s.nextSibling),
                        "rss" === e.localName(s) && (n = {
                            playlist: t.parse(s)
                        })
                    }
                    if (!n) try {
                        var d = JSON.parse(r.responseText);
                        if (o.isArray(d)) n = {
                            playlist: d
                        };
                        else {
                            if (!o.isArray(d.playlist)) throw null;
                            n = d
                        }
                    } catch(p) {
                        return void l("Not a valid RSS/JSON feed")
                    }
                    u.trigger(i.JWPLAYER_PLAYLIST_LOADED, n)
                });
                a instanceof n.Error && l()
            }
            function s(e) {
                l("Playlist load error: " + e)
            }
            function l(e) {
                u.trigger(i.JWPLAYER_ERROR, {
                    message: e ? e: "Error loading file"
                })
            }
            var u = o.extend(this, r);
            this.load = function(e) {
                n.ajax(e, a, s)
            },
            this.destroy = function() {
                this.off()
            }
        };
        return a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(31), n(51), n(1), n(39)],
    r = function(e, t, n, i) {
        function r(e, t) {
            for (var n = 0; n < e.length; n++) {
                var i = e[n],
                r = t.choose(i);
                if (r) return {
                    type: i.type,
                    provider: r.providerToCheck
                }
            }
            return null
        }
        function o(e, t) {
            return n.isUndefined(e) ? t: e
        }
        var a = function(t) {
            return t = n.isArray(t) ? t: [t],
            n.compact(n.map(t, e))
        };
        a.filterPlaylist = function(e, t, i, r, a, u, c) {
            var d = [];
            return n.each(e,
            function(e) {
                e = n.extend({},
                e),
                e.allSources = s(e.sources, i, e.drm || r, e.preload || a, o(e.withCredentials, c)),
                e.sources = l(e.allSources, t),
                e.sources.length && (e.file = e.sources[0].file, (e.preload || a) && (e.preload = e.preload || a), (e.feedid || u) && (e.feedid = e.feedid || u), d.push(e))
            }),
            d
        };
        var s = function(e, i, r, a, s) {
            return n.compact(n.map(e,
            function(e) {
                if (n.isObject(e)) {
                    void 0 !== i && null !== i && (e.androidhls = i),
                    (e.drm || r) && (e.drm = e.drm || r),
                    (e.preload || a) && (e.preload = e.preload || a);
                    var l = o(e.withCredentials, s);
                    return n.isUndefined(l) || (e.withCredentials = l),
                    t(e)
                }
            }))
        },
        l = function(e, t) {
            t && t.choose || (t = new i({
                primary: t ? "flash": null
            }));
            var o = r(e, t);
            if (!o) return [];
            var a = o.provider,
            s = o.type;
            return n.filter(e,
            function(e) {
                return e.type === s && t.providerSupports(a, e)
            })
        };
        return a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(8), n(1)],
    r = function(e, t, n) {
        var i = {
            "default": !1
        },
        r = function(r) {
            if (r && r.file) {
                var o = n.extend({},
                i, r);
                o.file = t.trim("" + o.file);
                var a = /^[^\/]+\/(?:x-)?([^\/]+)$/;
                if (a.test(o.type) && (o.mimeType = o.type, o.type = o.type.replace(a, "$1")), e.isYouTube(o.file) ? o.type = "youtube": e.isRtmp(o.file) ? o.type = "rtmp": o.type || (o.type = t.extension(o.file)), o.type) {
                    switch (o.type) {
                    case "m3u8":
                    case "vnd.apple.mpegurl":
                        o.type = "hls";
                        break;
                    case "dash+xml":
                        o.type = "dash";
                        break;
                    case "smil":
                        o.type = "rtmp";
                        break;
                    case "m4a":
                        o.type = "aac"
                    }
                    return n.each(o,
                    function(e, t) {
                        "" === e && delete o[t]
                    }),
                    o
                }
            }
        };
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(26), n(3), n(4), n(15), n(1)],
    r = function(e, t, n, i, r, o) {
        var a = {
            FLASH: 0,
            JAVASCRIPT: 1,
            HYBRID: 2
        },
        s = function(s) {
            function l() {
                switch (t.getPluginPathType(s)) {
                case t.pluginPathType.ABSOLUTE:
                    return s;
                case t.pluginPathType.RELATIVE:
                    return e.getAbsolutePath(s, window.location.href)
                }
            }
            function u() {
                o.defer(function() {
                    m = r.loaderstatus.COMPLETE,
                    g.trigger(n.COMPLETE)
                })
            }
            function c() {
                m = r.loaderstatus.ERROR,
                g.trigger(n.ERROR, {
                    url: s
                })
            }
            var d, p, h, f, g = o.extend(this, i),
            m = r.loaderstatus.NEW;
            this.load = function() {
                if (m === r.loaderstatus.NEW) {
                    if (s.lastIndexOf(".swf") > 0) return d = s,
                    m = r.loaderstatus.COMPLETE,
                    void g.trigger(n.COMPLETE);
                    if (t.getPluginPathType(s) === t.pluginPathType.CDN) return m = r.loaderstatus.COMPLETE,
                    void g.trigger(n.COMPLETE);
                    m = r.loaderstatus.LOADING;
                    var e = new r(l());
                    e.on(n.COMPLETE, u),
                    e.on(n.ERROR, c),
                    e.load()
                }
            },
            this.registerPlugin = function(e, t, i, o) {
                f && (clearTimeout(f), f = void 0),
                h = t,
                i && o ? (d = o, p = i) : "string" == typeof i ? d = i: "function" == typeof i ? p = i: i || o || (d = e),
                m = r.loaderstatus.COMPLETE,
                g.trigger(n.COMPLETE)
            },
            this.getStatus = function() {
                return m
            },
            this.getPluginName = function() {
                return t.getPluginName(s)
            },
            this.getFlashPath = function() {
                if (d) switch (t.getPluginPathType(d)) {
                case t.pluginPathType.ABSOLUTE:
                    return d;
                case t.pluginPathType.RELATIVE:
                    return s.lastIndexOf(".swf") > 0 ? e.getAbsolutePath(d, window.location.href) : e.getAbsolutePath(d, l())
                }
                return null
            },
            this.getJS = function() {
                return p
            },
            this.getTarget = function() {
                return h
            },
            this.getPluginmode = function() {
                return void 0 !== typeof d && void 0 !== typeof p ? a.HYBRID: void 0 !== typeof d ? a.FLASH: void 0 !== typeof p ? a.JAVASCRIPT: void 0
            },
            this.getNewInstance = function(e, t, n) {
                return new p(e, t, n)
            },
            this.getURL = function() {
                return s
            }
        };
        return s
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
, , , , ,
function(e, t, n) {
    var i, r;
    i = [n(27)],
    r = function(e) {
        return function(t) {
            if ("hls" === t.type) {
                if (t.androidhls === !1 && e.isAndroid()) return ! 1;
                var n = e.isAndroidNative;
                if (n(2) || n(3) || n("4.0")) return ! 1;
                if (e.isAndroid() && !e.isFF()) return ! 0
            }
            return null
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
,
function(e, t, n) {
    var i, r;
    i = [n(1), n(33)],
    r = function(e, t) {
        function n(e) {
            e.onload = null,
            e.onprogress = null,
            e.onreadystatechange = null,
            e.onerror = null,
            "abort" in e && e.abort()
        }
        function i(t, i) {
            return function(r) {
                var o = r.currentTarget || i.xhr;
                if (clearTimeout(i.timeoutId), i.retryWithoutCredentials && i.xhr.withCredentials) {
                    n(o);
                    var a = e.extend({},
                    i, {
                        xhr: null,
                        withCredentials: !1,
                        retryWithoutCredentials: !1
                    });
                    return void d(a)
                }
                i.onerror(t, i.url, o)
            }
        }
        function r(e) {
            return function(t) {
                var n = t.currentTarget || e.xhr;
                if (4 === n.readyState) {
                    if (clearTimeout(e.timeoutId), n.status >= 400) {
                        var i;
                        return i = 404 === n.status ? "File not found": "" + n.status + "(" + n.statusText + ")",
                        e.onerror(i, e.url, n)
                    }
                    if (200 === n.status) return o(e)(t)
                }
            }
        }
        function o(e) {
            return function(n) {
                var i = n.currentTarget || e.xhr;
                if (clearTimeout(e.timeoutId), e.responseType) {
                    if ("json" === e.responseType) return a(i, e)
                } else {
                    var r, o = i.responseXML;
                    if (o) try {
                        r = o.firstChild
                    } catch(l) {}
                    if (o && r) return s(i, o, e);
                    if (u && i.responseText && !o && (o = t.parseXML(i.responseText), o && o.firstChild)) return s(i, o, e);
                    if (e.requireValidXML) return void e.onerror("Invalid XML", e.url, i)
                }
                e.oncomplete(i)
            }
        }
        function a(t, n) {
            if (!t.response || e.isString(t.response) && '"' !== t.responseText.substr(1)) try {
                t = e.extend({},
                t, {
                    response: JSON.parse(t.responseText)
                })
            } catch(i) {
                return void n.onerror("Invalid JSON", n.url, t)
            }
            return n.oncomplete(t)
        }
        function s(t, n, i) {
            var r = n.documentElement;
            return i.requireValidXML && ("parsererror" === r.nodeName || r.getElementsByTagName("parsererror").length) ? void i.onerror("Invalid XML", i.url, t) : (t.responseXML || (t = e.extend({},
            t, {
                responseXML: n
            })), i.oncomplete(t))
        }
        var l = function() {},
        u = !1,
        c = function(e) {
            var t = document.createElement("a"),
            n = document.createElement("a");
            t.href = location.href;
            try {
                return n.href = e,
                n.href = n.href,
                t.protocol + "//" + t.host != n.protocol + "//" + n.host
            } catch(i) {}
            return ! 0
        },
        d = function(t, a, s, d) {
            e.isObject(t) && (d = t, t = d.url);
            var p, h = e.extend({
                xhr: null,
                url: t,
                withCredentials: !1,
                retryWithoutCredentials: !1,
                timeout: 6e4,
                timeoutId: -1,
                oncomplete: a || l,
                onerror: s || l,
                mimeType: d && !d.responseType ? "text/xml": "",
                requireValidXML: !1,
                responseType: d && d.plainText ? "text": ""
            },
            d);
            if ("XDomainRequest" in window && c(t)) p = h.xhr = new window.XDomainRequest,
            p.onload = o(h),
            p.ontimeout = p.onprogress = l,
            u = !0;
            else {
                if (! ("XMLHttpRequest" in window)) return void h.onerror("", t);
                p = h.xhr = new window.XMLHttpRequest,
                p.onreadystatechange = r(h)
            }
            var f = i("Error loading file", h);
            p.onerror = f,
            "overrideMimeType" in p ? h.mimeType && p.overrideMimeType(h.mimeType) : u = !0;
            try {
                t = t.replace(/#.*$/, ""),
                p.open("GET", t, !0)
            } catch(g) {
                return f(g),
                p
            }
            if (h.responseType) try {
                p.responseType = h.responseType
            } catch(g) {}
            h.timeout && (h.timeoutId = setTimeout(function() {
                n(p),
                h.onerror("Timeout", t, p)
            },
            h.timeout), p.onabort = function() {
                clearTimeout(h.timeoutId)
            });
            try {
                h.withCredentials && "withCredentials" in p && (p.withCredentials = !0),
                p.send()
            } catch(g) {
                f(g)
            }
            return p
        };
        return {
            ajax: d,
            crossdomain: c
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(4), n(1)],
    r = function(e, t, n) {
        function i(e, t, n) {
            var i = document.createElement("param");
            i.setAttribute("name", t),
            i.setAttribute("value", n),
            e.appendChild(i)
        }
        function r(r, o, l, u) {
            var c;
            if (u = u || "opaque", e.isMSIE()) {
                var d = document.createElement("div");
                o.appendChild(d),
                d.outerHTML = '<object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="100%" height="100%" id="' + l + '" name="' + l + '" tabindex="0"><param name="movie" value="' + r + '"><param name="allowfullscreen" value="true"><param name="allowscriptaccess" value="always"><param name="wmode" value="' + u + '"><param name="bgcolor" value="' + s + '"><param name="menu" value="false"></object>';
                for (var p = o.getElementsByTagName("object"), h = p.length; h--;) p[h].id === l && (c = p[h])
            } else c = document.createElement("object"),
            c.setAttribute("type", "application/x-shockwave-flash"),
            c.setAttribute("data", r),
            c.setAttribute("width", "100%"),
            c.setAttribute("height", "100%"),
            c.setAttribute("bgcolor", s),
            c.setAttribute("id", l),
            c.setAttribute("name", l),
            i(c, "allowfullscreen", "true"),
            i(c, "allowscriptaccess", "always"),
            i(c, "wmode", u),
            i(c, "menu", "false"),
            o.appendChild(c, o);
            return c.className = "jw-swf jw-reset",
            c.style.display = "block",
            c.style.position = "absolute",
            c.style.left = 0,
            c.style.right = 0,
            c.style.top = 0,
            c.style.bottom = 0,
            e.isIE() && "PointerEvent" in window && (c.style.pointerEvents = "none"),
            n.extend(c, t),
            c.queueCommands = !0,
            c.triggerFlash = function(t) {
                var i = this;
                if ("setup" !== t && i.queueCommands || !i.__externalCall) {
                    for (var r = i.__commandQueue,
                    o = r.length; o--;) r[o][0] === t && r.splice(o, 1);
                    return r.push(Array.prototype.slice.call(arguments)),
                    i
                }
                var s = Array.prototype.slice.call(arguments, 1),
                l = e.tryCatch(function() {
                    if (s.length) {
                        for (var e = s.length; e--;)"object" == typeof s[e] && n.each(s[e], a);
                        var r = JSON.stringify(s);
                        i.__externalCall(t, r)
                    } else i.__externalCall(t)
                });
                return l instanceof e.Error && (console.error(t, l), "setup" === t) ? (l.name = "Failed to setup flash", l) : i
            },
            c.__commandQueue = [],
            c
        }
        function o(e) {
            e && e.parentNode && (e.style.display = "none", e.parentNode.removeChild(e))
        }
        function a(e, t, n) {
            e instanceof window.HTMLElement && delete n[t]
        }
        var s = "#000000";
        return {
            embed: r,
            remove: o
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(4), n(1)],
    r = function(e, t) {
        function n() {}
        var i = function(e, n) {
            var i, r = this;
            i = e && t.has(e, "constructor") ? e.constructor: function() {
                return r.apply(this, arguments)
            },
            t.extend(i, r, n);
            var o = function() {
                this.constructor = i
            };
            return o.prototype = r.prototype,
            i.prototype = new o,
            e && t.extend(i.prototype, e),
            i.__super__ = r.prototype,
            i
        };
        return n.extend = i,
        t.extend(n.prototype, e),
        n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(22), n(1), n(34), n(33), n(24)],
    r = function(e, t, n, i, r) {
        var o = {};
        return o.repo = t.memoize(function() {
            var t = r.split("+")[0],
            i = e.repo + t + "/";
            return n.isHTTPS() ? i.replace(/^http:/, "https:") : i
        }),
        o.versionCheck = function(e) {
            var t = ("0" + e).split(/\W/),
            n = r.split(/\W/),
            i = parseFloat(t[0]),
            o = parseFloat(n[0]);
            return ! (i > o) && !(i === o && parseFloat("0" + t[1]) > parseFloat(n[1]))
        },
        o.loadFrom = function() {
            return o.repo()
        },
        o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = function() {
            var t = {},
            n = {},
            i = {},
            r = {};
            return {
                start: function(n) {
                    t[n] = e.now(),
                    i[n] = i[n] + 1 || 1
                },
                end: function(i) {
                    if (t[i]) {
                        var r = e.now() - t[i];
                        n[i] = n[i] + r || r
                    }
                },
                dump: function() {
                    return {
                        counts: i,
                        sums: n,
                        events: r
                    }
                },
                tick: function(t, n) {
                    r[t] = n || e.now()
                },
                between: function(e, t) {
                    return r[t] && r[e] ? r[t] - r[e] : -1
                }
            }
        };
        return t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return document.createElement("video")
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(7)],
    r = function(e) {
        var t = function(t, n, i) {
            var r = document.createElement("div");
            return r.className = "jw-icon jw-icon-inline jw-button-color jw-reset " + t,
            r.setAttribute("role", "button"),
            r.setAttribute("tabindex", "0"),
            i && r.setAttribute("aria-label", i),
            r.style.display = "none",
            n && new e(r).on("click tap",
            function() {
                n()
            }),
            {
                element: function() {
                    return r
                },
                toggle: function(e) {
                    e ? this.show() : this.hide()
                },
                show: function() {
                    r.style.display = ""
                },
                hide: function() {
                    r.style.display = "none"
                }
            }
        };
        return t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
,
function(e, t, n) {
    var i;
    i = function(require, e, t) {
        function n(e, t) {
            r(t, o(e))
        }
        function i(e) {
            var t = c[e];
            if (t) {
                for (var n = Object.keys(t), i = 0; i < n.length; i += 1) for (var r = t[n[i]], o = 0; o < r.parts.length; o += 1) r.parts[o]();
                delete c[e]
            }
        }
        function r(e, t) {
            for (var n = 0; n < t.length; n++) {
                var i = t[n],
                r = (c[e] || {})[i.id];
                if (r) {
                    for (var o = 0; o < r.parts.length; o++) r.parts[o](i.parts[o]);
                    for (; o < i.parts.length; o++) r.parts.push(l(e, i.parts[o]))
                } else {
                    for (var a = [], o = 0; o < i.parts.length; o++) a.push(l(e, i.parts[o]));
                    c[e] = c[e] || {},
                    c[e][i.id] = {
                        id: i.id,
                        parts: a
                    }
                }
            }
        }
        function o(e) {
            for (var t = [], n = {},
            i = 0; i < e.length; i++) {
                var r = e[i],
                o = r[0],
                a = r[1],
                s = r[2],
                l = {
                    css: a,
                    media: s
                };
                n[o] ? n[o].parts.push(l) : t.push(n[o] = {
                    id: o,
                    parts: [l]
                })
            }
            return t
        }
        function a(e) {
            h().appendChild(e)
        }
        function s(e) {
            var t = document.createElement("style");
            return t.type = "text/css",
            t.setAttribute("data-jwplayer-id", e),
            a(t),
            t
        }
        function l(e, t) {
            var n, i, r, o = d[e];
            o || (o = d[e] = {
                element: s(e),
                counter: 0
            });
            var a = o.counter++;
            return n = o.element,
            i = u.bind(null, n, a, !1),
            r = u.bind(null, n, a, !0),
            i(t),
            function(e) {
                if (e) {
                    if (e.css === t.css && e.media === t.media) return;
                    i(t = e)
                } else r()
            }
        }
        function u(e, t, n, i) {
            var r = n ? "": i.css;
            if (e.styleSheet) e.styleSheet.cssText = f(t, r);
            else {
                var o = document.createTextNode(r),
                a = e.childNodes;
                a[t] && e.removeChild(a[t]),
                a.length ? e.insertBefore(o, a[t]) : e.appendChild(o)
            }
        }
        var c = {},
        d = {},
        p = function(e) {
            var t;
            return function() {
                return "undefined" == typeof t && (t = e.apply(this, arguments)),
                t
            }
        },
        h = p(function() {
            return document.head || document.getElementsByTagName("head")[0]
        });
        t.exports = {
            style: n,
            clear: i
        };
        var f = function() {
            var e = [];
            return function(t, n) {
                return e[t] = n,
                e.filter(Boolean).join("\n")
            }
        } ()
    }.call(t, n, t, e),
    !(void 0 !== i && (e.exports = i))
},
function(e, t, n) {
    var i, r;
    i = [n(116), n(21), n(1)],
    r = function(e, t, n) {
        var i = e.selectPlayer,
        r = function() {
            var e = i.apply(this, arguments);
            return e ? e: {
                registerPlugin: function(e, n, i) {
                    "jwpsrv" !== e && t.registerPlugin(e, n, i)
                }
            }
        };
        return n.extend(e, {
            selectPlayer: r
        })
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
, , ,
function(e, t, n) {
    var i, r;
    i = [n(2), n(74), n(16)],
    r = function(e, t, n) {
        var i = "invalid",
        r = "RnXcsftYjWRDA^Uy",
        o = function(o) {
            function a(o) {
                e.exists(o) || (o = "");
                try {
                    o = t.decrypt(o, r);
                    var a = o.split("/");
                    s = a[0],
                    "pro" === s && (s = "premium");
                    var c = n(s);
                    if (a.length > 2 && c("setup")) {
                        l = a[1];
                        var d = parseInt(a[2]);
                        d > 0 && (u = new Date, u.setTime(d))
                    } else s = i
                } catch(p) {
                    s = i
                }
            }
            var s, l, u;
            this.edition = function() {
                return u && u.getTime() < (new Date).getTime() ? i: s
            },
            this.token = function() {
                return l
            },
            this.expiration = function() {
                return u
            },
            a(o)
        };
        return o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        var e = function(e) {
            return window.atob(e)
        },
        t = function(e) {
            return unescape(encodeURIComponent(e))
        },
        n = function(e) {
            try {
                return decodeURIComponent(escape(e))
            } catch(t) {
                return e
            }
        },
        i = function(e) {
            for (var t = new Array(Math.ceil(e.length / 4)), n = 0; n < t.length; n++) t[n] = e.charCodeAt(4 * n) + (e.charCodeAt(4 * n + 1) << 8) + (e.charCodeAt(4 * n + 2) << 16) + (e.charCodeAt(4 * n + 3) << 24);
            return t
        },
        r = function(e) {
            for (var t = new Array(e.length), n = 0; n < e.length; n++) t[n] = String.fromCharCode(255 & e[n], e[n] >>> 8 & 255, e[n] >>> 16 & 255, e[n] >>> 24 & 255);
            return t.join("")
        };
        return {
            decrypt: function(o, a) {
                if (o = String(o), a = String(a), 0 == o.length) return "";
                for (var s, l, u = i(e(o)), c = i(t(a).slice(0, 16)), d = u.length, p = u[d - 1], h = u[0], f = 2654435769, g = Math.floor(6 + 52 / d), m = g * f; 0 != m;) {
                    l = m >>> 2 & 3;
                    for (var w = d - 1; w >= 0; w--) p = u[w > 0 ? w - 1 : d - 1],
                    s = (p >>> 5 ^ h << 2) + (h >>> 3 ^ p << 4) ^ (m ^ h) + (c[3 & w ^ l] ^ p),
                    h = u[w] -= s;
                    m -= f
                }
                var v = r(u);
                return v = v.replace(/\0+$/, ""),
                n(v)
            }
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(159), n(3), n(170)],
    r = function(e, t, n) {
        var i = function(i, r) {
            var o = new e(i, r),
            a = o.setup;
            return o.setup = function() {
                a.call(this),
                r.on("change:skipButton", this.onSkipButton, this),
                r.on("change:castActive change:playlistItem", this.showDisplayIconImage, this)
            },
            o.showDisplayIconImage = function(e) {
                var t = e.get("castActive"),
                n = e.get("playlistItem"),
                i = o.controlsContainer().getElementsByClassName("jw-display-icon-container")[0];
                t && n && n.image ? (i.style.backgroundImage = 'url("' + n.image + '")', i.style.backgroundSize = "contain") : (i.style.backgroundImage = "", i.style.backgroundSize = "")
            },
            o.onSkipButton = function(e, t) {
                t ? this.addSkipButton() : this._skipButton && (this._skipButton.destroy(), this._skipButton = null)
            },
            o.addSkipButton = function() {
                this._skipButton = new n(this.instreamModel),
                this._skipButton.on(t.JWPLAYER_AD_SKIPPED,
                function() {
                    this.api.skipAd()
                },
                this),
                this.controlsContainer().appendChild(this._skipButton.element())
            },
            o
        };
        return i
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
, , , , ,
function(e, t, n) {
    t = e.exports = n(82)(),
    t.push([e.id, ".jw-reset{color:inherit;background-color:transparent;padding:0;margin:0;float:none;font-family:Arial,Helvetica,sans-serif;font-size:1em;line-height:1em;list-style:none;text-align:left;text-transform:none;vertical-align:baseline;border:0;direction:ltr;font-variant:inherit;font-stretch:inherit;-webkit-tap-highlight-color:rgba(255,255,255,0)}@font-face{font-family:jw-icons;src:url(" + n(84) + ") format('woff'),url(" + n(83) + ') format(\'truetype\');font-weight:400;font-style:normal}.jw-controlbar .jw-menu .jw-option:before,.jw-icon-display,.jw-icon-inline,.jw-icon-tooltip{font-family:jw-icons;-webkit-font-smoothing:antialiased;font-style:normal;font-weight:400;text-transform:none;background-color:transparent;font-variant:normal;-webkit-font-feature-settings:"liga";-ms-font-feature-settings:"liga" 1;-o-font-feature-settings:"liga";font-feature-settings:"liga";-moz-osx-font-smoothing:grayscale}.jw-icon-audio-tracks:before{content:"\\E600"}.jw-icon-buffer:before{content:"\\E601"}.jw-icon-cast:before{content:"\\E603"}.jw-icon-cast.jw-off:before{content:"\\E602"}.jw-icon-cc:before{content:"\\E605"}.jw-icon-cue:before,.jw-icon-menu-bullet:before{content:"\\E606"}.jw-icon-error:before{content:"\\E607"}.jw-icon-fullscreen:before{content:"\\E608"}.jw-icon-fullscreen.jw-off:before{content:"\\E613"}.jw-icon-hd:before{content:"\\E60A"}.jw-rightclick-logo:before{content:"\\E60B"}.jw-icon-next:before{content:"\\E60C"}.jw-icon-pause:before{content:"\\E60D"}.jw-icon-play:before{content:"\\E60E"}.jw-icon-replay:before{content:"\\E610"}.jw-icon-volume:before{content:"\\E612"}.jw-icon-volume.jw-off:before{content:"\\E611"}.jw-icon-more:before{content:"\\E614"}.jw-icon-close:before{content:"\\E615"}.jw-icon-rewind:before{content:"\\E900";}.jw-icon-rewind{display:none;}.jwplayer{width:100%;font-size:16px;position:relative;display:block;min-height:0;overflow:hidden;box-sizing:border-box;font-family:Arial,Helvetica,sans-serif;background-color:#000;-webkit-touch-callout:none;-webkit-user-select:none;-moz-user-select:none;-ms-user-select:none;user-select:none}.jwplayer *{box-sizing:inherit}.jwplayer.jw-flag-aspect-mode{height:auto!important}.jwplayer.jw-flag-aspect-mode .jw-aspect{display:block}.jwplayer .jw-aspect{display:none}.jwplayer.jw-no-focus:focus,.jwplayer .jw-swf{outline:none}.jwplayer.jw-ie:focus{outline:1px dotted #585858}.jwplayer:hover .jw-display-icon-container{background-color:#212121}.jw-controls,.jw-media,.jw-overlays,.jw-preview{position:absolute;width:100%;height:100%;top:0;left:0;bottom:0;right:0}.jw-media{overflow:hidden;cursor:pointer}.jw-overlays{cursor:auto}.jw-media.jw-media-show{visibility:visible;opacity:1}.jw-controls.jw-controls-disabled{display:none}.jw-controls .jw-controls-right{position:absolute;top:0;right:0;left:0;bottom:2.5em}.jw-text{height:1em;font-family:Arial,Helvetica,sans-serif;font-size:.75em;font-style:normal;font-weight:400;color:#fff;text-align:center;font-variant:normal;font-stretch:normal}.jw-plugin{position:absolute;bottom:2.5em}.jw-plugin .jw-banner{max-width:100%;opacity:0;cursor:pointer;position:absolute;margin:auto auto 0;left:0;right:0;bottom:0;display:block}.jw-cast-screen{width:100%;height:100%}.jw-instream{position:absolute;top:0;right:0;bottom:0;left:0;display:none}.jw-icon-playback:before{content:"\\E60E"}.jw-captions,.jw-controls,.jw-overlays,.jw-preview,.jw-title{pointer-events:none}.jw-autostart-mute,.jw-controlbar,.jw-display-icon-container,.jw-display-icon-container .jw-icon,.jw-dock .jw-dock-button,.jw-logo,.jw-media,.jw-nextup-container,.jw-overlays .jw-plugin,.jw-skip{pointer-events:all}.jwplayer video{position:absolute;top:0;right:0;bottom:0;left:0;width:100%;height:100%;margin:auto;background:transparent}.jwplayer video::-webkit-media-controls-start-playback-button{display:none}.jwplayer.jw-stretch-uniform video{-o-object-fit:contain;object-fit:contain}.jwplayer.jw-stretch-none video{-o-object-fit:none;object-fit:none}.jwplayer.jw-stretch-fill video{-o-object-fit:cover;object-fit:cover}.jwplayer.jw-stretch-exactfit video{-o-object-fit:fill;object-fit:fill}.jw-click,.jw-preview{position:absolute;width:100%;height:100%}.jw-preview{display:none;opacity:1;visibility:visible;background:#000 no-repeat 50% 50%}.jw-error .jw-preview,.jw-stretch-uniform .jw-preview,.jwplayer .jw-preview{background-size:contain}.jw-stretch-none .jw-preview{background-size:auto auto}.jw-stretch-fill .jw-preview{background-size:cover}.jw-stretch-exactfit .jw-preview{background-size:100% 100%}.jw-display-icon-container{position:relative;top:50%;display:table;height:3.75em;width:3.75em;margin:-1.875em auto 0;cursor:pointer}.jw-display-icon-container .jw-icon-display{position:relative;display:table-cell;text-align:center;vertical-align:middle!important;background-position:50% 50%;background-repeat:no-repeat;font-size:2.5em}.jw-breakpoint-0 .jw-display-icon-container{height:44px;width:44px;margin:-22px auto 0}.jw-breakpoint-0 .jw-display-icon-container .jw-icon{font-size:22px}.jw-breakpoint-1 .jw-display-icon-container{height:55px;width:55px;margin:-27.5px auto 0}.jw-breakpoint-1 .jw-display-icon-container .jw-icon{font-size:35.2px}.jw-flag-audio-player .jw-display-icon-container,.jw-flag-dragging .jw-display-icon-container{display:none}.jw-icon{font-family:jw-icons;-webkit-font-smoothing:antialiased;font-style:normal;font-weight:400;text-transform:none;background-color:transparent;font-variant:normal;-webkit-font-feature-settings:"liga";-ms-font-feature-settings:"liga" 1;-o-font-feature-settings:"liga";font-feature-settings:"liga";-moz-osx-font-smoothing:grayscale}.jw-controlbar{display:table;position:absolute;bottom:0;height:2.5em;width:100%;padding:0 .5em}.jw-controlbar .jw-hidden{display:none}.jw-background-color{background-color:rgba(33,33,33,.8)}.jw-slider-horizontal{background-color:transparent}.jw-group{display:table-cell}.jw-controlbar-center-group{width:100%;padding:0 .5em}.jw-controlbar-center-group .jw-slider-time,.jw-controlbar-center-group .jw-text-alt{padding:0}.jw-controlbar-center-group .jw-text-alt{display:none}.jw-controlbar-left-group,.jw-controlbar-right-group{white-space:nowrap}.jw-icon-display:hover,.jw-icon-inline:hover,.jw-icon-tooltip:hover,.jw-knob:hover,.jw-option:before:hover{color:#fff}.jw-icon-inline,.jw-icon-tooltip,.jw-slider-horizontal,.jw-text-duration,.jw-text-elapsed{display:inline-block;height:2.5em;position:relative;line-height:2.5em;vertical-align:middle;cursor:pointer;padding:0 .5em}.jw-icon-inline,.jw-icon-tooltip{min-width:1.5625em;text-align:center}.jw-icon-playback{min-width:2.25em}.jw-icon-volume{min-width:1.75em;text-align:left}.jw-time-tip{line-height:1em;pointer-events:none}.jw-icon-cast,.jw-icon-inline.jw-icon-volume,.jw-slider-volume.jw-slider-horizontal{display:none}.jw-button-color{color:hsla(0,0%,100%,.6)}.jw-button-color:hover{color:#fff}.jw-button-color:focus{outline:none;color:#fff}.jw-toggle{color:#fff}.jw-toggle.jw-off{color:hsla(0,0%,100%,.6)}.jw-toggle:focus{outline:none;color:#fff}.jw-dock{clear:right;margin:.75em;display:block;opacity:1}.jw-breakpoint-0 .jw-dock{margin:.225em .1125em}.jw-breakpoint-1 .jw-dock{margin:.45em .225em}.jw-dock:after{content:\'\';clear:both;display:block}.jw-dock-button{cursor:pointer;float:right;height:2.5em;margin:.5em;position:relative;width:2.5em}.jw-breakpoint-0 .jw-dock-button{margin:0 .1125em;height:44px;width:44px}.jw-breakpoint-1 .jw-dock-button{margin:0 .225em;height:44px;width:44px}.jw-dock-button .jw-arrow{display:none;position:absolute;bottom:-.2em;width:.5em;height:.2em;left:50%;margin-left:-.25em}.jw-dock-button .jw-overlay{display:none;position:absolute;top:2.5em;right:0;margin-top:.25em;padding:.5em;white-space:nowrap}.jw-dock-button:hover .jw-arrow,.jw-dock-button:hover .jw-overlay{display:block}.jw-dock-image{width:100%;height:100%;background-position:50% 50%;background-repeat:no-repeat;opacity:.75}.jw-title{display:none;position:absolute;top:0;width:100%;font-size:.875em;height:8em;background:-webkit-linear-gradient(top,#000,#000 18%,transparent);background:linear-gradient(180deg,#000 0,#000 18%,transparent)}.jw-title-primary,.jw-title-secondary{padding:.75em 1.5em;min-height:2.5em;width:100%;color:#fff;white-space:nowrap;text-overflow:ellipsis;overflow-x:hidden}.jw-title-primary{font-weight:700}.jw-title-secondary{margin-top:-.5em}.jw-slider-container{height:1em;width:100%;position:relative;touch-action:none}.jw-buffer,.jw-progress,.jw-rail{position:absolute;cursor:pointer}.jw-progress{background-color:#fff}.jw-rail{background-color:hsla(0,0%,100%,.2)}.jw-buffer{background-color:hsla(0,0%,100%,.3)}.jw-cue,.jw-knob{position:absolute;cursor:pointer}.jw-cue{height:.25em;background-color:rgba(33,33,33,.9);border-radius:25%;width:.5em}.jw-knob{background-color:#fff;width:.5em;height:.5em;border-radius:.25em}.jw-slider-horizontal{height:.25em}.jw-slider-horizontal.jw-slider-volume{width:4em}.jw-slider-horizontal .jw-rail{width:100%}.jw-slider-horizontal .jw-knob{top:-.125em;margin-left:-.25em}.jw-slider-horizontal .jw-buffer,.jw-slider-horizontal .jw-progress,.jw-slider-horizontal .jw-rail{height:.25em}.jw-slider-vertical{padding:1em 1em 0;position:absolute}.jw-slider-vertical .jw-buffer,.jw-slider-vertical .jw-progress,.jw-slider-vertical .jw-rail{bottom:0;height:100%;left:0;right:0;margin:0 auto}.jw-slider-vertical .jw-progress,.jw-slider-vertical .jw-rail,.jw-slider-vertical .jw-slider-container{width:.25em}.jw-slider-vertical .jw-slider-container{height:4em}.jw-slider-vertical .jw-knob{right:0;left:-.125em}.jw-slider-time{width:100%}.jw-tooltip-time{position:absolute}.jw-slider-volume .jw-buffer{display:none}.jw-captions{position:absolute;width:100%;height:inherit;text-align:center;display:none;max-height:81.375%;line-height:1.3em;letter-spacing:normal;word-spacing:normal;text-transform:none;text-indent:0;text-decoration:none;pointer-events:none;overflow:hidden;top:0}.jw-captions.jw-captions-enabled{display:block}.jw-captions-window{display:none;padding:.25em;border-radius:.25em}.jw-captions-text,.jw-captions-window.jw-captions-window-active{display:inline-block}.jw-captions-text{color:#fff;background-color:#000;word-wrap:normal;word-break:normal;white-space:pre-line;font-style:normal;font-weight:400;text-align:center;text-decoration:none;line-height:1.3em;padding:.1em .8em}.jw-text-track-display{font-size:inherit;line-height:1.3em}.jwplayer video::-webkit-media-controls{-webkit-box-pack:start;justify-content:flex-start}.jwplayer video::-webkit-media-text-track-container{max-height:81.375%;line-height:1.3em}.jwplayer .jw-rightclick{display:none;position:absolute;white-space:nowrap}.jwplayer .jw-rightclick.jw-open{display:block}.jwplayer .jw-rightclick ul{list-style:none;font-weight:700;border-radius:.15em;margin:0;border:1px solid #444;padding:0}.jwplayer .jw-rightclick ul li{background-color:#000;border-bottom:1px solid #444;margin:0}.jwplayer .jw-rightclick ul li .jw-rightclick-logo{font-size:2em;color:#ff0147;vertical-align:middle;padding-right:.3em;margin-right:.3em;border-right:1px solid #444}.jwplayer .jw-rightclick ul li a{color:#fff;text-decoration:none;padding:1em;display:block;font-size:.6875em;line-height:1em}.jwplayer .jw-rightclick ul li:last-child{border-bottom:none}.jwplayer .jw-rightclick ul li:hover{background-color:#1a1a1a;cursor:pointer}.jwplayer .jw-rightclick ul .jw-featured{background-color:#252525;vertical-align:middle}.jwplayer .jw-rightclick ul .jw-featured a{color:#777}.jw-logo{position:absolute;margin:.75em;cursor:pointer;pointer-events:all;background-repeat:no-repeat;background-size:contain;top:auto;right:auto;left:auto;bottom:auto}.jw-logo .jw-flag-audio-player{display:none}.jw-logo-top-right{top:0;right:0}.jw-logo-top-left{top:0;left:0}.jw-logo-bottom-left{bottom:0;left:0}.jw-logo-bottom-right{bottom:0;right:0}.jw-icon-tooltip.jw-open .jw-overlay{opacity:1;visibility:visible}.jw-icon-tooltip.jw-hidden{display:none}.jw-overlay:before{position:absolute;top:0;bottom:0;left:-50%;width:100%;background-color:transparent;content:" "}.jw-slider-time .jw-overlay:before{height:1em;top:auto}.jw-menu,.jw-time-tip,.jw-volume-tip{position:relative;left:-50%;margin:0}.jw-volume-tip{width:100%;height:100%;display:block}.jw-time-tip{text-align:center;font-family:inherit;bottom:1.25em;padding:.5em;border-radius:.25em}.jw-time-tip .jw-text{color:#fff;line-height:1em}.jw-controlbar .jw-overlay{margin:0;position:absolute;bottom:2.5em;left:50%;opacity:0;visibility:hidden}.jw-controlbar .jw-overlay .jw-contents{position:relative}.jw-controlbar .jw-option{position:relative;white-space:nowrap;cursor:pointer;list-style:none;height:1.5em;font-family:inherit;line-height:1.5em;color:hsla(0,0%,100%,.6);padding:0 .5em;font-size:.8em}.jw-controlbar .jw-option:before:hover,.jw-controlbar .jw-option:hover{color:#fff}.jw-controlbar .jw-option:before{padding-right:.125em}.jw-controlbar .jw-option.jw-active-option{color:#fff}.jw-skip{cursor:default;position:absolute;float:right;display:inline-block;right:.75em;bottom:3em;padding:.5em}.jw-skip.jw-skippable{cursor:pointer}.jw-skip.jw-hidden{visibility:hidden}.jw-skip .jw-skip-icon{display:none;margin-left:-.75em}.jw-skip .jw-skip-icon:before{content:"\\E60C"}.jw-skip .jw-skip-icon,.jw-skip .jw-text{color:hsla(0,0%,100%,.6);vertical-align:middle;line-height:1.5em;font-size:.7em}.jw-skip.jw-skippable:hover{cursor:pointer}.jw-icon-rewind{display:none}.jw-skip.jw-skippable:hover .jw-skip-icon,.jw-skip.jw-skippable:hover .jw-text{color:#fff}.jw-skip.jw-skippable .jw-skip-icon{display:inline;margin:0}.jwplayer.jw-state-paused.jw-flag-casting .jw-display-icon-container,.jwplayer.jw-state-playing.jw-flag-casting .jw-display-icon-container{display:table}.jwplayer.jw-flag-casting .jw-display-icon-container{border-radius:0;border:1px solid #fff;position:absolute;top:auto;left:.5em;right:.5em;bottom:50%;margin-bottom:-12.5%;height:50%;width:50%;padding:0;background-repeat:no-repeat;background-position:50%}.jwplayer.jw-flag-casting .jw-display-icon-container .jw-icon{font-size:3em}.jwplayer.jw-flag-casting.jw-state-complete .jw-preview{display:none}.jw-cast{position:absolute;width:100%;height:100%;background-repeat:no-repeat;background-size:auto;background-position:50% 50%}.jw-cast-label{position:absolute;left:.5em;right:.5em;bottom:75%;margin-bottom:1.5em;text-align:center}.jw-cast-name{color:#ccc}.jw-nextup-container{-webkit-font-smoothing:antialiased;-moz-font-smoothing:antialiased;background-color:transparent;bottom:3em;cursor:pointer;max-width:300px;min-width:200px;opacity:0;position:absolute;right:.5em;-webkit-transform:translateY(5px);transform:translateY(5px);-webkit-transition:all .15s ease;transition:all .15s ease;visibility:hidden;width:40%}.jw-nextup-container-visible{opacity:1;-webkit-transform:translateY(0);transform:translateY(0);visibility:visible}.jw-nextup{border-radius:0;overflow:hidden;position:relative}.jw-nextup-header{background:rgba(33,33,33,.8);box-sizing:border-box;color:#fff;font-size:12px;font-weight:700;line-height:normal;padding:8px}.jw-nextup-body{background:rgba(0,0,0,.8);color:#fff;overflow:hidden}.jw-nextup-thumbnail{background-position:50%;background-size:cover;display:none;float:left;height:60px;width:45%}.jw-nextup-thumbnail-visible{display:block}.jw-nextup-title{box-sizing:border-box;float:left;font-size:12px;font-weight:700;line-height:1.3;overflow:hidden;padding:5px 6px;position:relative;text-overflow:ellipsis;white-space:nowrap;width:100%}.jw-nextup-thumbnail-visible+.jw-nextup-title{height:60px;white-space:normal;width:55%}.jw-nextup-thumbnail-visible+.jw-nextup-title:after{background:-webkit-linear-gradient(top,transparent,#000);background:linear-gradient(-180deg,transparent,#000);bottom:0;content:\'\';height:30px;left:0;position:absolute;width:100%}.jw-nextup-close{font-family:jw-icons;-webkit-font-smoothing:antialiased;font-style:normal;font-weight:400;text-transform:none;background-color:transparent;font-variant:normal;-webkit-font-feature-settings:"liga";-ms-font-feature-settings:"liga" 1;-o-font-feature-settings:"liga";font-feature-settings:"liga";-moz-osx-font-smoothing:grayscale;border:none;color:hsla(0,0%,100%,.6);font-size:13px;opacity:0;position:absolute;right:5px;top:6px;-webkit-transition:color .15s ease,opacity .15s ease,visibility .15s ease;transition:color .15s ease,opacity .15s ease,visibility .15s ease;visibility:hidden}.jw-nextup-close:before{content:"\\E615"}.jw-nextup-close:active,.jw-nextup-close:hover{color:#fff}.jw-nextup-sticky .jw-nextup-close{opacity:1;visibility:visible}.jw-autostart-mute{min-width:1.75em;text-align:left;position:absolute;bottom:.5em;right:.5em;height:44px;width:44px;text-align:center}.jw-autostart-mute:before{content:"\\E612"}.jw-autostart-mute.jw-off:before{content:"\\E611"}.jw-autostart-mute:before{background-color:rgba(33,33,33,.8);padding:5px 4px 5px 6px}.jw-flag-autostart .jw-controlbar,.jw-flag-autostart .jw-nextup{display:none}.jw-state-idle .jw-preview{display:block}.jw-state-idle .jw-icon-display:before{content:"\\E60E"}.jw-state-idle .jw-captions,.jw-state-idle .jw-controlbar{display:none}.jw-state-idle .jw-title{display:block}.jwplayer.jw-state-playing .jw-display-icon-container{display:none}.jwplayer.jw-state-playing .jw-display-icon-container .jw-icon-display:before,.jwplayer.jw-state-playing .jw-icon-playback:before{content:"\\E60D"}.jwplayer.jw-state-paused .jw-display-icon-container{display:none}.jwplayer.jw-state-paused .jw-display-icon-container .jw-icon-display:before,.jwplayer.jw-state-paused .jw-icon-playback:before{content:"\\E60E"}.jwplayer.jw-state-paused .jw-autostart-mute{display:none}.jwplayer.jw-state-buffering .jw-display-icon-container .jw-icon-display{-webkit-animation:spin 2s linear infinite;animation:spin 2s linear infinite}.jwplayer.jw-state-buffering .jw-display-icon-container .jw-icon-display:before{content:"\\E601"}@-webkit-keyframes spin{to{-webkit-transform:rotate(1turn)}}@keyframes spin{to{-webkit-transform:rotate(1turn);transform:rotate(1turn)}}.jwplayer.jw-state-buffering .jw-display-icon-container .jw-text{display:none}.jwplayer.jw-state-buffering .jw-icon-playback:before{content:"\\E60D"}.jwplayer.jw-state-complete .jw-preview{display:block}.jwplayer.jw-state-complete .jw-display-icon-container .jw-icon-display:before{content:"\\E610"}.jwplayer.jw-state-complete .jw-display-icon-container .jw-text{display:none}.jwplayer.jw-state-complete .jw-icon-playback:before{content:"\\E60E"}.jwplayer.jw-state-complete .jw-captions{display:none}.jwplayer.jw-state-error .jw-title,body .jw-error .jw-title{display:block}.jwplayer.jw-state-error .jw-title .jw-title-primary,body .jw-error .jw-title .jw-title-primary{white-space:normal}.jwplayer.jw-state-error .jw-preview,body .jw-error .jw-preview{display:block}.jwplayer.jw-state-error .jw-captions,.jwplayer.jw-state-error .jw-controlbar,body .jw-error .jw-captions,body .jw-error .jw-controlbar{display:none}.jwplayer.jw-state-error:hover .jw-display-icon-container,body .jw-error:hover .jw-display-icon-container{cursor:default;color:#fff;background:#000}.jwplayer.jw-state-error .jw-icon-display,body .jw-error .jw-icon-display{cursor:default;font-family:jw-icons;-webkit-font-smoothing:antialiased;font-style:normal;font-weight:400;text-transform:none;background-color:transparent;font-variant:normal;-webkit-font-feature-settings:"liga";-ms-font-feature-settings:"liga" 1;-o-font-feature-settings:"liga";font-feature-settings:"liga";-moz-osx-font-smoothing:grayscale}.jwplayer.jw-state-error .jw-icon-display:before,body .jw-error .jw-icon-display:before{content:"\\E607"}.jwplayer.jw-state-error .jw-icon-display:hover,body .jw-error .jw-icon-display:hover{color:#fff}body .jw-error{font-size:16px;background-color:#000;color:#fff;width:100%;height:100%;display:table;opacity:1;position:relative}body .jw-error .jw-icon-container{position:absolute;width:100%;height:100%;top:0;left:0;bottom:0;right:0}.jwplayer.jw-flag-cast-available .jw-controlbar{display:table}.jwplayer.jw-flag-cast-available .jw-icon-cast{display:inline-block}.jwplayer.jw-flag-skin-loading .jw-captions,.jwplayer.jw-flag-skin-loading .jw-controls,.jwplayer.jw-flag-skin-loading .jw-title{display:none}.jwplayer.jw-flag-fullscreen{width:100%!important;height:100%!important;top:0;right:0;bottom:0;left:0;z-index:1000;margin:0;position:fixed}.jwplayer.jw-flag-live .jw-controlbar .jw-slider-time,.jwplayer.jw-flag-live .jw-controlbar .jw-text-duration,.jwplayer.jw-flag-live .jw-controlbar .jw-text-elapsed{display:none}.jwplayer.jw-flag-live .jw-controlbar .jw-text-alt{display:inline-block;height:auto;line-height:2.5em;margin:0 -.5em;overflow:hidden;text-overflow:ellipsis;vertical-align:middle;white-space:nowrap}.jwplayer.jw-flag-live.jw-breakpoint-0 .jw-text-alt{max-width:80px}.jwplayer.jw-flag-live.jw-breakpoint-1 .jw-text-alt{max-width:150px}.jwplayer.jw-flag-live.jw-breakpoint-2 .jw-text-alt{max-width:250px}.jwplayer.jw-flag-live.jw-breakpoint-3 .jw-text-alt{max-width:370px}.jwplayer.jw-flag-live.jw-breakpoint-4 .jw-text-alt{max-width:470px}.jwplayer.jw-flag-live.jw-breakpoint-5 .jw-text-alt{max-width:630px}.jwplayer.jw-flag-live.jw-breakpoint-6 .jw-text-alt{max-width:790px}.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-controlbar,.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-dock,.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-logo.jw-hide{display:none}.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-nextup-container,.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-plugin{bottom:.5em}.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-media{cursor:none;-webkit-cursor-visibility:auto-hide}.jwplayer.jw-flag-user-inactive.jw-state-playing .jw-captions{max-height:none}.jwplayer.jw-flag-user-inactive.jw-state-playing video::-webkit-media-text-track-container{max-height:none}.jwplayer.jw-flag-user-inactive.jw-state-buffering .jw-controlbar{display:none}.jwplayer.jw-flag-user-inactive.jw-state-buffering .jw-nextup-container{bottom:.5em}.jwplayer.jw-flag-media-audio .jw-controlbar,.jwplayer.jw-flag-media-audio.jw-flag-user-inactive .jw-controlbar{display:table}.jwplayer.jw-flag-media-audio.jw-flag-user-inactive .jw-nextup-container,.jwplayer.jw-flag-media-audio.jw-flag-user-inactive.jw-state-playing .jw-plugin{bottom:3em}.jwplayer.jw-flag-media-audio.jw-flag-user-inactive.jw-state-playing .jw-captions{max-height:81.375%}.jwplayer.jw-flag-media-audio.jw-flag-user-inactive.jw-state-playing video::-webkit-media-text-track-container{max-height:81.375%}.jwplayer.jw-flag-media-audio.jw-flag-user-inactive.jw-state-playing.jw-flag-touch video::-webkit-media-text-track-container{max-height:70%}.jwplayer.jw-flag-media-audio .jw-autostart-mute{display:none}.jw-flag-media-audio .jw-preview{display:block}.jwplayer.jw-flag-ads .jw-autostart-mute,.jwplayer.jw-flag-ads .jw-captions.jw-captions-enabled,.jwplayer.jw-flag-ads .jw-dock,.jwplayer.jw-flag-ads .jw-logo,.jwplayer.jw-flag-ads .jw-nextup-container,.jwplayer.jw-flag-ads .jw-preview{display:none}.jwplayer.jw-flag-ads video::-webkit-media-text-track-container{display:none}.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-inline,.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-tooltip,.jwplayer.jw-flag-ads .jw-controlbar .jw-slider-horizontal,.jwplayer.jw-flag-ads .jw-controlbar .jw-text{display:none}.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-fullscreen,.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-playback,.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-volume,.jwplayer.jw-flag-ads .jw-controlbar .jw-slider-volume{display:inline-block}.jwplayer.jw-flag-ads .jw-controlbar .jw-text-alt{display:inline-block;height:auto;line-height:2.5em;margin:0 -.5em;overflow:hidden;text-overflow:ellipsis;vertical-align:middle;white-space:nowrap}.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-inline.jw-icon-volume,.jwplayer.jw-flag-ads .jw-controlbar .jw-slider-volume.jw-slider-horizontal{display:inline-block}.jwplayer.jw-flag-ads .jw-controlbar .jw-icon-tooltip.jw-icon-volume{display:none}.jwplayer.jw-flag-ads.jw-breakpoint-0 .jw-text-alt{max-width:80px}.jwplayer.jw-flag-ads.jw-breakpoint-1 .jw-text-alt{max-width:150px}.jwplayer.jw-flag-ads.jw-breakpoint-2 .jw-text-alt{max-width:250px}.jwplayer.jw-flag-ads.jw-breakpoint-3 .jw-text-alt{max-width:370px}.jwplayer.jw-flag-ads.jw-breakpoint-4 .jw-text-alt{max-width:470px}.jwplayer.jw-flag-ads.jw-breakpoint-5 .jw-text-alt{max-width:630px}.jwplayer.jw-flag-ads.jw-breakpoint-6 .jw-text-alt{max-width:790px}.jwplayer.jw-flag-ads-googleima .jw-controlbar{display:table;bottom:0}.jwplayer.jw-flag-ads-googleima.jw-flag-touch .jw-controlbar{font-size:1em}.jwplayer.jw-flag-ads-googleima.jw-flag-touch.jw-state-paused .jw-display-icon-container{display:none}.jwplayer.jw-flag-ads-googleima.jw-skin-seven .jw-controlbar{font-size:.9em}.jwplayer.jw-flag-ads.jw-flag-touch .jw-controlbar{display:table}.jwplayer.jw-flag-ads-vpaid .jw-controlbar,.jwplayer.jw-flag-ads-vpaid .jw-skip,.jwplayer.jw-flag-touch.jw-flag-ads-vpaid .jw-controlbar,.jwplayer.jw-flag-touch.jw-flag-ads-vpaid .jw-skip{display:none}.jwplayer.jw-flag-ads-hide-controls .jw-controls{display:none!important}.jwplayer.jw-flag-overlay-open-related .jw-controls,.jwplayer.jw-flag-overlay-open-related .jw-title,.jwplayer.jw-flag-overlay-open-sharing .jw-controls,.jwplayer.jw-flag-overlay-open-sharing .jw-title,.jwplayer.jw-flag-overlay-open .jw-controls-right .jw-logo,.jwplayer.jw-flag-overlay-open .jw-title{display:none}.jwplayer.jw-flag-rightclick-open{overflow:visible}.jwplayer.jw-flag-rightclick-open .jw-rightclick{z-index:16777215}.jw-flag-controls-disabled .jw-controls{visibility:hidden}.jw-flag-controls-disabled .jw-logo{visibility:visible}.jw-flag-controls-disabled .jw-media{cursor:auto}.jw-flag-controls-disabled.jwplayer .jw-captions{max-height:none}.jw-flag-controls-disabled.jwplayer video::-webkit-media-text-track-container{max-height:none}body .jwplayer.jw-flag-flash-blocked .jw-title{display:block}body .jwplayer.jw-flag-flash-blocked .jw-controls,body .jwplayer.jw-flag-flash-blocked .jw-overlays,body .jwplayer.jw-flag-flash-blocked .jw-preview{display:none}.jw-flag-touch.jw-breakpoint-4 .jw-controlbar,.jw-flag-touch.jw-breakpoint-4 .jw-plugin,.jw-flag-touch.jw-breakpoint-4 .jw-skip,.jw-flag-touch.jw-breakpoint-5 .jw-controlbar,.jw-flag-touch.jw-breakpoint-5 .jw-plugin,.jw-flag-touch.jw-breakpoint-5 .jw-skip,.jw-flag-touch.jw-breakpoint-6 .jw-controlbar,.jw-flag-touch.jw-breakpoint-6 .jw-plugin,.jw-flag-touch.jw-breakpoint-6 .jw-skip,.jw-flag-touch.jw-breakpoint-7 .jw-controlbar,.jw-flag-touch.jw-breakpoint-7 .jw-plugin,.jw-flag-touch.jw-breakpoint-7 .jw-skip{font-size:1.5em}.jw-flag-touch.jw-breakpoint-4 .jw-captions,.jw-flag-touch.jw-breakpoint-5 .jw-captions,.jw-flag-touch.jw-breakpoint-6 .jw-captions,.jw-flag-touch.jw-breakpoint-7 .jw-captions{bottom:4.25em}.jw-flag-touch.jw-breakpoint-4 video::-webkit-media-text-track-container,.jw-flag-touch.jw-breakpoint-5 video::-webkit-media-text-track-container,.jw-flag-touch.jw-breakpoint-6 video::-webkit-media-text-track-container,.jw-flag-touch.jw-breakpoint-7 video::-webkit-media-text-track-container{max-height:70%}.jw-flag-touch.jw-breakpoint-4 .jw-nextup-container,.jw-flag-touch.jw-breakpoint-5 .jw-nextup-container,.jw-flag-touch.jw-breakpoint-6 .jw-nextup-container,.jw-flag-touch.jw-breakpoint-7 .jw-nextup-container{bottom:4.25em}.jw-flag-touch .jw-controlbar .jw-icon-volume{display:inline-block}.jw-flag-touch .jw-display-icon-container{pointer-events:none}.jw-flag-touch.jw-state-paused .jw-display-icon-container{display:table}.jw-flag-touch.jw-state-paused.jw-flag-dragging .jw-display-icon-container{display:none}.jwplayer.jw-flag-audio-player{background-color:transparent}.jwplayer.jw-flag-audio-player .jw-media{visibility:hidden}.jwplayer.jw-flag-audio-player .jw-media object{width:1px;height:1px}.jwplayer.jw-flag-audio-player .jw-display-icon-container,.jwplayer.jw-flag-audio-player .jw-dock,.jwplayer.jw-flag-audio-player .jw-nextup-container,.jwplayer.jw-flag-audio-player .jw-preview,.jwplayer.jw-flag-audio-player .jw-title{display:none}.jwplayer.jw-flag-audio-player .jw-controlbar{vertical-align:middle;display:table;height:100%;left:0;bottom:0;margin:0;width:100%;min-width:100%}.jwplayer.jw-flag-audio-player .jw-controlbar .jw-icon-fullscreen,.jwplayer.jw-flag-audio-player .jw-controlbar .jw-icon-tooltip{display:none}.jwplayer.jw-flag-audio-player .jw-controlbar .jw-icon-inline.jw-icon-volume,.jwplayer.jw-flag-audio-player .jw-controlbar .jw-slider-volume.jw-slider-horizontal{display:inline-block}.jwplayer.jw-flag-audio-player .jw-controlbar .jw-icon-tooltip.jw-icon-volume{display:none}.jwplayer.jw-flag-audio-player.jw-flag-user-inactive .jw-controlbar{display:table}.jwplayer.jw-flag-audio-player .jw-icon-inline{height:auto;line-height:normal}.jwplayer.jw-flag-audio-player .jw-group{vertical-align:middle}.jwplayer.jw-flag-audio-player .jw-controlbar-center-group{padding-bottom:2px}.jw-breakpoint-0 .jw-nextup-container,.jw-breakpoint-0 .jw-text-duration,.jw-breakpoint-0 .jw-text-elapsed,.jw-breakpoint-1 .jw-nextup-container,.jw-breakpoint-1 .jw-text-duration,.jw-breakpoint-1 .jw-text-elapsed{display:none}.jw-skin-seven .jw-color-active,.jw-skin-seven .jw-color-active-hover:hover{color:#fff;stroke:#fff;border-color:#fff}.jw-skin-seven .jw-color-inactive,.jw-skin-seven .jw-color-inactive-hover:hover{color:#cecece;stroke:#cecece;border-color:#cecece}.jw-skin-seven .jw-active-option{background-color:hsla(0,0%,100%,.1)}.jw-skin-seven .jw-display-icon-container{display:table;font-size:20px;border-radius:3.5em}.jw-skin-seven .jw-display-icon-container:hover .jw-icon{color:#fff}.jw-skin-seven .jw-display-icon-container>.jw-icon{color:hsla(0,0%,100%,.9);font-size:1.7em}.jw-skin-seven.jw-state-idle .jw-display-icon-container>.jw-icon{padding-left:4px}.jw-skin-seven .jw-dock-button{border-radius:2.5em}.jw-skin-seven .jw-menu{padding:0}.jw-skin-seven .jw-dock .jw-overlay,.jw-skin-seven .jw-skip{border-radius:.5em}.jw-skin-seven .jw-text{text-rendering:optimizeLegibility;-webkit-font-smoothing:antialiased;-moz-osx-font-smoothing:grayscale}.jw-skin-seven.jw-breakpoint-0 .jw-display-icon-container .jw-icon{font-size:22px}.jw-skin-seven.jw-breakpoint-1 .jw-display-icon-container .jw-icon{font-size:35.2px}', ""]);
},
function(e, t) {
    e.exports = function() {
        var e = [];
        return e.toString = function() {
            for (var e = [], t = 0; t < this.length; t++) {
                var n = this[t];
                n[2] ? e.push("@media " + n[2] + "{" + n[1] + "}") : e.push(n[1])
            }
            return e.join("")
        },
        e.i = function(t, n) {
            "string" == typeof t && (t = [[null, t, ""]]);
            for (var i = {},
            r = 0; r < this.length; r++) {
                var o = this[r][0];
                "number" == typeof o && (i[o] = !0)
            }
            for (r = 0; r < t.length; r++) {
                var a = t[r];
                "number" == typeof a[0] && i[a[0]] || (n && !a[2] ? a[2] = n: n && (a[2] = "(" + a[2] + ") and (" + n + ")"), e.push(a))
            }
        },
        e
    }
},
function(e, t, n) {
    e.exports = n.p + "jw-icons.ttf"
},
function(e, t, n) {
    e.exports = n.p + "jw-icons.woff"
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            return '<div class="jw-skip jw-background-color jw-hidden jw-reset">\n    <span class="jw-text jw-skiptext jw-reset"></span>\n    <span class="jw-icon-inline jw-skip-icon jw-reset"></span>\n</div>'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o;
            return '<div class="jw-display-icon-container jw-background-color jw-reset">\n    <div class="jw-icon jw-icon-display jw-button-color jw-reset" role="button" tabindex="0" aria-label="' + e.escapeExpression((o = null != (o = n.ariaLabel || (null != t ? t.ariaLabel: t)) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "ariaLabel",
                hash: {},
                data: r
            }) : o)) + '"></div>\n</div>\n'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        1 : function(e, t, n, i, r) {
            var o, a, s = null != t ? t: {};
            return '    <div class="jw-dock-button jw-background-color jw-reset' + (null != (o = n["if"].call(s, null != t ? t.btnClass: t, {
                name: "if",
                hash: {},
                fn: e.program(2, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + '" button="' + e.escapeExpression((a = null != (a = n.id || (null != t ? t.id: t)) ? a: n.helperMissing, "function" == typeof a ? a.call(s, {
                name: "id",
                hash: {},
                data: r
            }) : a)) + '">\n        <div class="jw-icon jw-dock-image jw-reset" ' + (null != (o = n["if"].call(s, null != t ? t.img: t, {
                name: "if",
                hash: {},
                fn: e.program(4, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + '></div>\n        <div class="jw-arrow jw-reset"></div>\n' + (null != (o = n["if"].call(s, null != t ? t.tooltip: t, {
                name: "if",
                hash: {},
                fn: e.program(6, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + "    </div>\n"
        },
        2 : function(e, t, n, i, r) {
            var o;
            return " " + e.escapeExpression((o = null != (o = n.btnClass || (null != t ? t.btnClass: t)) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "btnClass",
                hash: {},
                data: r
            }) : o))
        },
        4 : function(e, t, n, i, r) {
            var o;
            return "style='background-image: url(\"" + e.escapeExpression((o = null != (o = n.img || (null != t ? t.img: t)) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "img",
                hash: {},
                data: r
            }) : o)) + "\")'"
        },
        6 : function(e, t, n, i, r) {
            var o;
            return '        <div class="jw-overlay jw-background-color jw-reset">\n            <span class="jw-text jw-dock-text jw-reset">' + e.escapeExpression((o = null != (o = n.tooltip || (null != t ? t.tooltip: t)) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "tooltip",
                hash: {},
                data: r
            }) : o)) + "</span>\n        </div>\n"
        },
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o;
            return '<div class="jw-dock jw-reset">\n' + (null != (o = n.each.call(null != t ? t: {},
            t, {
                name: "each",
                hash: {},
                fn: e.program(1, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + "</div>"
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o, a = null != t ? t: {},
            s = n.helperMissing,
            l = "function",
            u = e.escapeExpression;
            return '<div id="' + u((o = null != (o = n.id || (null != t ? t.id: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "id",
                hash: {},
                data: r
            }) : o)) + '"class="jw-skin-' + u((o = null != (o = n.skin || (null != t ? t.skin: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "skin",
                hash: {},
                data: r
            }) : o)) + ' jw-error jw-reset">\n    <div class="jw-title jw-reset">\n        <div class="jw-title-primary jw-reset">' + u((o = null != (o = n.title || (null != t ? t.title: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "title",
                hash: {},
                data: r
            }) : o)) + '</div>\n        <div class="jw-title-secondary jw-reset">' + u((o = null != (o = n.body || (null != t ? t.body: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "body",
                hash: {},
                data: r
            }) : o)) + '</div>\n    </div>\n\n    <div class="jw-icon-container jw-reset">\n        <div class="jw-display-icon-container jw-background-color jw-reset">\n            <div class="jw-icon jw-icon-display jw-reset" aria-hidden="true"></div>\n        </div>\n    </div>\n</div>\n'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            return '<div class="jw-logo jw-reset"></div>'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        1 : function(e, t, n, i, r) {
            var o, a = e.escapeExpression;
            return "        <li class='jw-text jw-option jw-item-" + a((o = null != (o = n.index || r && r.index) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "index",
                hash: {},
                data: r
            }) : o)) + " jw-reset'>" + a(e.lambda(null != t ? t.label: t, t)) + "</li>\n"
        },
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o;
            return '<ul class="jw-menu jw-background-color jw-reset">\n' + (null != (o = n.each.call(null != t ? t: {},
            t, {
                name: "each",
                hash: {},
                fn: e.program(1, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + "</ul>"
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o = e.lambda,
            a = e.escapeExpression;
            return '<div class="jw-nextup jw-reset">\n    <div class="jw-nextup-tooltip jw-reset">\n        <div class="jw-nextup-header jw-reset">\n            ' + a(o(null != t ? t.nextUpText: t, t)) + '\n        </div>\n        <div class="jw-nextup-body jw-background-color jw-reset">\n            <div class="jw-nextup-thumbnail jw-reset"></div>\n            <div class="jw-nextup-title jw-reset">' + a(o(null != t ? t.title: t, t)) + '</div>\n        </div>\n    </div>\n    <button class="jw-nextup-close"></button>\n</div>\n'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o;
            return '<div id="' + e.escapeExpression((o = null != (o = n.id || (null != t ? t.id: t)) ? o: n.helperMissing, "function" == typeof o ? o.call(null != t ? t: {},
            {
                name: "id",
                hash: {},
                data: r
            }) : o)) + '" class="jwplayer jw-reset jw-state-idle" tabindex="0">\n    <div class="jw-aspect jw-reset"></div>\n    <div class="jw-media jw-reset"></div>\n    <div class="jw-preview jw-reset"></div>\n    <div class="jw-title jw-reset">\n        <div class="jw-title-primary jw-reset"></div>\n        <div class="jw-title-secondary jw-reset"></div>\n    </div>\n    <div class="jw-overlays jw-reset"></div>\n    <div class="jw-controls jw-reset"></div>\n</div>'
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        1 : function(e, t, n, i, r) {
            var o, a, s = null != t ? t: {},
            l = n.helperMissing,
            u = "function",
            c = e.escapeExpression;
            return '        <li class="jw-reset' + (null != (o = n["if"].call(s, null != t ? t.featured: t, {
                name: "if",
                hash: {},
                fn: e.program(2, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + '">\n            <a href="' + c((a = null != (a = n.link || (null != t ? t.link: t)) ? a: l, typeof a === u ? a.call(s, {
                name: "link",
                hash: {},
                data: r
            }) : a)) + '" class="jw-reset" target="_top">\n' + (null != (o = n["if"].call(s, null != t ? t.showLogo: t, {
                name: "if",
                hash: {},
                fn: e.program(4, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + "                " + c((a = null != (a = n.title || (null != t ? t.title: t)) ? a: l, typeof a === u ? a.call(s, {
                name: "title",
                hash: {},
                data: r
            }) : a)) + "\n            </a>\n        </li>\n"
        },
        2 : function(e, t, n, i, r) {
            return " jw-featured"
        },
        4 : function(e, t, n, i, r) {
            return '                <span class="jw-icon jw-rightclick-logo jw-reset"></span>\n'
        },
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o;
            return '<div class="jw-rightclick jw-reset">\n    <ul class="jw-reset">\n' + (null != (o = n.each.call(null != t ? t: {},
            null != t ? t.items: t, {
                name: "each",
                hash: {},
                fn: e.program(1, r, 0),
                inverse: e.noop,
                data: r
            })) ? o: "") + "    </ul>\n</div>"
        },
        useData: !0
    })
},
function(e, t, n) {
    var i = n(9);
    e.exports = (i["default"] || i).template({
        compiler: [7, ">= 4.0.0"],
        main: function(e, t, n, i, r) {
            var o, a = null != t ? t: {},
            s = n.helperMissing,
            l = "function",
            u = e.escapeExpression;
            return '<div class="' + u((o = null != (o = n.className || (null != t ? t.className: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "className",
                hash: {},
                data: r
            }) : o)) + " " + u((o = null != (o = n.orientation || (null != t ? t.orientation: t)) ? o: s, typeof o === l ? o.call(a, {
                name: "orientation",
                hash: {},
                data: r
            }) : o)) + ' jw-reset" aria-hidden="true">\n    <div class="jw-slider-container jw-reset">\n        <div class="jw-rail jw-reset"></div>\n        <div class="jw-buffer jw-reset"></div>\n        <div class="jw-progress jw-reset"></div>\n        <div class="jw-knob jw-reset"></div>\n    </div>\n</div>'
        },
        useData: !0
    })
},
,
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    function r(e) {
        if (e && e.__esModule) return e;
        var t = {};
        if (null != e) for (var n in e) Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n]);
        return t["default"] = e,
        t
    }
    function o() {
        var e = new s.HandlebarsEnvironment;
        return h.extend(e, s),
        e.SafeString = u["default"],
        e.Exception = d["default"],
        e.Utils = h,
        e.escapeExpression = h.escapeExpression,
        e.VM = g,
        e.template = function(t) {
            return g.template(t, e)
        },
        e
    }
    t.__esModule = !0;
    var a = n(44),
    s = r(a),
    l = n(110),
    u = i(l),
    c = n(17),
    d = i(c),
    p = n(11),
    h = r(p),
    f = n(109),
    g = r(f),
    m = n(108),
    w = i(m),
    v = o();
    v.create = o,
    w["default"](v),
    v["default"] = v,
    t["default"] = v,
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    function r(e) {
        a["default"](e)
    }
    t.__esModule = !0,
    t.registerDefaultDecorators = r;
    var o = n(98),
    a = i(o)
},
function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var i = n(11);
    t["default"] = function(e) {
        e.registerDecorator("inline",
        function(e, t, n, r) {
            var o = e;
            return t.partials || (t.partials = {},
            o = function(r, o) {
                var a = n.partials;
                n.partials = i.extend({},
                a, t.partials);
                var s = e(r, o);
                return n.partials = a,
                s
            }),
            t.partials[r.args[0]] = r.fn,
            o
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    function r(e) {
        a["default"](e),
        l["default"](e),
        c["default"](e),
        p["default"](e),
        f["default"](e),
        m["default"](e),
        v["default"](e)
    }
    t.__esModule = !0,
    t.registerDefaultHelpers = r;
    var o = n(100),
    a = i(o),
    s = n(101),
    l = i(s),
    u = n(102),
    c = i(u),
    d = n(103),
    p = i(d),
    h = n(104),
    f = i(h),
    g = n(105),
    m = i(g),
    w = n(106),
    v = i(w)
},
function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var i = n(11);
    t["default"] = function(e) {
        e.registerHelper("blockHelperMissing",
        function(t, n) {
            var r = n.inverse,
            o = n.fn;
            if (t === !0) return o(this);
            if (t === !1 || null == t) return r(this);
            if (i.isArray(t)) return t.length > 0 ? (n.ids && (n.ids = [n.name]), e.helpers.each(t, n)) : r(this);
            if (n.data && n.ids) {
                var a = i.createFrame(n.data);
                a.contextPath = i.appendContextPath(n.data.contextPath, n.name),
                n = {
                    data: a
                }
            }
            return o(t, n)
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    t.__esModule = !0;
    var r = n(11),
    o = n(17),
    a = i(o);
    t["default"] = function(e) {
        e.registerHelper("each",
        function(e, t) {
            function n(t, n, o) {
                u && (u.key = t, u.index = n, u.first = 0 === n, u.last = !!o, c && (u.contextPath = c + t)),
                l += i(e[t], {
                    data: u,
                    blockParams: r.blockParams([e[t], t], [c + t, null])
                })
            }
            if (!t) throw new a["default"]("Must pass iterator to #each");
            var i = t.fn,
            o = t.inverse,
            s = 0,
            l = "",
            u = void 0,
            c = void 0;
            if (t.data && t.ids && (c = r.appendContextPath(t.data.contextPath, t.ids[0]) + "."), r.isFunction(e) && (e = e.call(this)), t.data && (u = r.createFrame(t.data)), e && "object" == typeof e) if (r.isArray(e)) for (var d = e.length; s < d; s++) s in e && n(s, s, s === e.length - 1);
            else {
                var p = void 0;
                for (var h in e) e.hasOwnProperty(h) && (void 0 !== p && n(p, s - 1), p = h, s++);
                void 0 !== p && n(p, s - 1, !0)
            }
            return 0 === s && (l = o(this)),
            l
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    t.__esModule = !0;
    var r = n(17),
    o = i(r);
    t["default"] = function(e) {
        e.registerHelper("helperMissing",
        function() {
            if (1 !== arguments.length) throw new o["default"]('Missing helper: "' + arguments[arguments.length - 1].name + '"')
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var i = n(11);
    t["default"] = function(e) {
        e.registerHelper("if",
        function(e, t) {
            return i.isFunction(e) && (e = e.call(this)),
            !t.hash.includeZero && !e || i.isEmpty(e) ? t.inverse(this) : t.fn(this)
        }),
        e.registerHelper("unless",
        function(t, n) {
            return e.helpers["if"].call(this, t, {
                fn: n.inverse,
                inverse: n.fn,
                hash: n.hash
            })
        })
    },
    e.exports = t["default"]
},
function(e, t) {
    "use strict";
    t.__esModule = !0,
    t["default"] = function(e) {
        e.registerHelper("log",
        function() {
            for (var t = [void 0], n = arguments[arguments.length - 1], i = 0; i < arguments.length - 1; i++) t.push(arguments[i]);
            var r = 1;
            null != n.hash.level ? r = n.hash.level: n.data && null != n.data.level && (r = n.data.level),
            t[0] = r,
            e.log.apply(e, t)
        })
    },
    e.exports = t["default"]
},
function(e, t) {
    "use strict";
    t.__esModule = !0,
    t["default"] = function(e) {
        e.registerHelper("lookup",
        function(e, t) {
            return e && e[t]
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var i = n(11);
    t["default"] = function(e) {
        e.registerHelper("with",
        function(e, t) {
            i.isFunction(e) && (e = e.call(this));
            var n = t.fn;
            if (i.isEmpty(e)) return t.inverse(this);
            var r = t.data;
            return t.data && t.ids && (r = i.createFrame(t.data), r.contextPath = i.appendContextPath(t.data.contextPath, t.ids[0])),
            n(e, {
                data: r,
                blockParams: i.blockParams([e], [r && r.contextPath])
            })
        })
    },
    e.exports = t["default"]
},
function(e, t, n) {
    "use strict";
    t.__esModule = !0;
    var i = n(11),
    r = {
        methodMap: ["debug", "info", "warn", "error"],
        level: "info",
        lookupLevel: function(e) {
            if ("string" == typeof e) {
                var t = i.indexOf(r.methodMap, e.toLowerCase());
                e = t >= 0 ? t: parseInt(e, 10)
            }
            return e
        },
        log: function(e) {
            if (e = r.lookupLevel(e), "undefined" != typeof console && r.lookupLevel(r.level) <= e) {
                var t = r.methodMap[e];
                console[t] || (t = "log");
                for (var n = arguments.length,
                i = Array(n > 1 ? n - 1 : 0), o = 1; o < n; o++) i[o - 1] = arguments[o];
                console[t].apply(console, i)
            }
        }
    };
    t["default"] = r,
    e.exports = t["default"]
},
function(e, t) { (function(n) {
        "use strict";
        t.__esModule = !0,
        t["default"] = function(e) {
            var t = "undefined" != typeof n ? n: window,
            i = t.Handlebars;
            e.noConflict = function() {
                return t.Handlebars === e && (t.Handlebars = i),
                e
            }
        },
        e.exports = t["default"]
    }).call(t,
    function() {
        return this
    } ())
},
function(e, t, n) {
    "use strict";
    function i(e) {
        return e && e.__esModule ? e: {
            "default": e
        }
    }
    function r(e) {
        if (e && e.__esModule) return e;
        var t = {};
        if (null != e) for (var n in e) Object.prototype.hasOwnProperty.call(e, n) && (t[n] = e[n]);
        return t["default"] = e,
        t
    }
    function o(e) {
        var t = e && e[0] || 1,
        n = w.COMPILER_REVISION;
        if (t !== n) {
            if (t < n) {
                var i = w.REVISION_CHANGES[n],
                r = w.REVISION_CHANGES[t];
                throw new m["default"]("Template was precompiled with an older version of Handlebars than the current runtime. Please update your precompiler to a newer version (" + i + ") or downgrade your runtime to an older version (" + r + ").")
            }
            throw new m["default"]("Template was precompiled with a newer version of Handlebars than the current runtime. Please update your runtime to a newer version (" + e[1] + ").")
        }
    }
    function a(e, t) {
        function n(n, i, r) {
            r.hash && (i = f.extend({},
            i, r.hash), r.ids && (r.ids[0] = !0)),
            n = t.VM.resolvePartial.call(this, n, i, r);
            var o = t.VM.invokePartial.call(this, n, i, r);
            if (null == o && t.compile && (r.partials[r.name] = t.compile(n, e.compilerOptions, t), o = r.partials[r.name](i, r)), null != o) {
                if (r.indent) {
                    for (var a = o.split("\n"), s = 0, l = a.length; s < l && (a[s] || s + 1 !== l); s++) a[s] = r.indent + a[s];
                    o = a.join("\n")
                }
                return o
            }
            throw new m["default"]("The partial " + r.name + " could not be compiled when running in runtime-only mode")
        }
        function i(t) {
            function n(t) {
                return "" + e.main(r, t, r.helpers, r.partials, a, l, s)
            }
            var o = arguments.length <= 1 || void 0 === arguments[1] ? {}: arguments[1],
            a = o.data;
            i._setup(o),
            !o.partial && e.useData && (a = d(t, a));
            var s = void 0,
            l = e.useBlockParams ? [] : void 0;
            return e.useDepths && (s = o.depths ? t !== o.depths[0] ? [t].concat(o.depths) : o.depths: [t]),
            (n = p(e.main, n, r, o.depths || [], a, l))(t, o)
        }
        if (!t) throw new m["default"]("No environment passed to template");
        if (!e || !e.main) throw new m["default"]("Unknown template object: " + typeof e);
        e.main.decorator = e.main_d,
        t.VM.checkRevision(e.compiler);
        var r = {
            strict: function(e, t) {
                if (! (t in e)) throw new m["default"]('"' + t + '" not defined in ' + e);
                return e[t]
            },
            lookup: function(e, t) {
                for (var n = e.length,
                i = 0; i < n; i++) if (e[i] && null != e[i][t]) return e[i][t]
            },
            lambda: function(e, t) {
                return "function" == typeof e ? e.call(t) : e
            },
            escapeExpression: f.escapeExpression,
            invokePartial: n,
            fn: function(t) {
                var n = e[t];
                return n.decorator = e[t + "_d"],
                n
            },
            programs: [],
            program: function(e, t, n, i, r) {
                var o = this.programs[e],
                a = this.fn(e);
                return t || r || i || n ? o = s(this, e, a, t, n, i, r) : o || (o = this.programs[e] = s(this, e, a)),
                o
            },
            data: function(e, t) {
                for (; e && t--;) e = e._parent;
                return e
            },
            merge: function(e, t) {
                var n = e || t;
                return e && t && e !== t && (n = f.extend({},
                t, e)),
                n
            },
            noop: t.VM.noop,
            compilerInfo: e.compiler
        };
        return i.isTop = !0,
        i._setup = function(n) {
            n.partial ? (r.helpers = n.helpers, r.partials = n.partials, r.decorators = n.decorators) : (r.helpers = r.merge(n.helpers, t.helpers), e.usePartial && (r.partials = r.merge(n.partials, t.partials)), (e.usePartial || e.useDecorators) && (r.decorators = r.merge(n.decorators, t.decorators)))
        },
        i._child = function(t, n, i, o) {
            if (e.useBlockParams && !i) throw new m["default"]("must pass block params");
            if (e.useDepths && !o) throw new m["default"]("must pass parent depths");
            return s(r, t, e[t], n, 0, i, o)
        },
        i
    }
    function s(e, t, n, i, r, o, a) {
        function s(t) {
            var r = arguments.length <= 1 || void 0 === arguments[1] ? {}: arguments[1],
            s = a;
            return a && t !== a[0] && (s = [t].concat(a)),
            n(e, t, e.helpers, e.partials, r.data || i, o && [r.blockParams].concat(o), s)
        }
        return s = p(n, s, e, a, i, o),
        s.program = t,
        s.depth = a ? a.length: 0,
        s.blockParams = r || 0,
        s
    }
    function l(e, t, n) {
        return e ? e.call || n.name || (n.name = e, e = n.partials[e]) : e = "@partial-block" === n.name ? n.data["partial-block"] : n.partials[n.name],
        e
    }
    function u(e, t, n) {
        n.partial = !0,
        n.ids && (n.data.contextPath = n.ids[0] || n.data.contextPath);
        var i = void 0;
        if (n.fn && n.fn !== c && (n.data = w.createFrame(n.data), i = n.data["partial-block"] = n.fn, i.partials && (n.partials = f.extend({},
        n.partials, i.partials))), void 0 === e && i && (e = i), void 0 === e) throw new m["default"]("The partial " + n.name + " could not be found");
        if (e instanceof Function) return e(t, n)
    }
    function c() {
        return ""
    }
    function d(e, t) {
        return t && "root" in t || (t = t ? w.createFrame(t) : {},
        t.root = e),
        t
    }
    function p(e, t, n, i, r, o) {
        if (e.decorator) {
            var a = {};
            t = e.decorator(t, a, n, i && i[0], r, o, i),
            f.extend(t, a)
        }
        return t
    }
    t.__esModule = !0,
    t.checkRevision = o,
    t.template = a,
    t.wrapProgram = s,
    t.resolvePartial = l,
    t.invokePartial = u,
    t.noop = c;
    var h = n(11),
    f = r(h),
    g = n(17),
    m = i(g),
    w = n(44)
},
function(e, t) {
    "use strict";
    function n(e) {
        this.string = e
    }
    t.__esModule = !0,
    n.prototype.toString = n.prototype.toHTML = function() {
        return "" + this.string
    },
    t["default"] = n,
    e.exports = t["default"]
},
,
function(e, t, n) {
    var i, r;
    i = [n(21), n(1)],
    r = function(e, t) {
        return function(n, i) {
            var r = ["seek", "skipAd", "stop", "playlistNext", "playlistPrev", "playlistItem", "resize", "addButton", "removeButton", "registerPlugin", "attachMedia", "next"];
            t.each(r,
            function(e) {
                n[e] = function() {
                    return i[e].apply(i, arguments),
                    n
                }
            }),
            n.registerPlugin = e.registerPlugin
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        return function(t, n) {
            var i = ["buffer", "controls", "position", "duration", "fullscreen", "volume", "mute", "item", "stretching", "playlist", "captions"];
            e.each(i,
            function(e) {
                var i = e.slice(0, 1).toUpperCase() + e.slice(1);
                t["get" + i] = function() {
                    return n._model.get(e)
                }
            });
            var r = ["getAudioTracks", "getCaptionsList", "getWidth", "getHeight", "getCurrentAudioTrack", "setCurrentAudioTrack", "getCurrentCaptions", "setCurrentCaptions", "getCurrentQuality", "setCurrentQuality", "getQualityLevels", "getVisualQuality", "getConfig", "getState", "getSafeRegion", "isBeforeComplete", "isBeforePlay", "getProvider", "detachMedia"],
            o = ["setControls", "setFullscreen", "setVolume", "setMute", "setCues", "setCaptions"];
            e.each(r,
            function(e) {
                t[e] = function() {
                    return n[e] ? n[e].apply(n, arguments) : null
                }
            }),
            e.each(o,
            function(e) {
                t[e] = function() {
                    return n[e].apply(n, arguments),
                    t
                }
            }),
            t.getPlaylistIndex = t.getItem
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(3), n(5), n(4), n(2), n(64), n(1), n(166), n(112), n(113), n(115), n(24)],
    r = function(e, t, n, i, r, o, a, s, l, u, c) {
        var d = function(d, p) {
            var h, f = this,
            g = !1,
            m = {};
            o.extend(this, n),
            this.utils = i,
            this._ = o,
            this.Events = n,
            this.version = c,
            this.trigger = function(e, t) {
                t = o.isObject(t) ? o.extend({},
                t) : {},
                t.type = e;
                var i = window.jwplayer;
                return i && i.debug ? n.trigger.call(f, e, t) : n.triggerSafe.call(f, e, t)
            },
            this.dispatchEvent = this.trigger,
            this.removeEventListener = this.off.bind(this);
            var w = function() {
                h = new a(d),
                s(f, h),
                l(f, h),
                h.on(e.JWPLAYER_PLAYLIST_ITEM,
                function() {
                    m = {}
                }),
                h.on(e.JWPLAYER_MEDIA_META,
                function(e) {
                    o.extend(m, e.metadata)
                }),
                h.on(e.JWPLAYER_READY,
                function(e) {
                    g = !0,
                    v.tick("ready"),
                    e.setupTime = v.between("setup", "ready")
                }),
                h.on("all", f.trigger)
            };
            w(),
            u(this),
            this.id = d.id;
            var v = this._qoe = new r;
            v.tick("init");
            var y = function() {
                g = !1,
                m = {},
                f.off(),
                h && h.off(),
                h && h.playerDestroy && h.playerDestroy()
            };
            return this.getPlugin = function(e) {
                return f.plugins && f.plugins[e]
            },
            this.addPlugin = function(e, t) {
                this.plugins = this.plugins || {},
                this.plugins[e] = t,
                this.onReady(t.addToPlayer),
                t.resize && this.onResize(t.resizeHandler)
            },
            this.setup = function(e) {
                return v.tick("setup"),
                y(),
                w(),
                i.foreach(e.events,
                function(e, t) {
                    var n = f[e];
                    "function" == typeof n && n.call(f, t)
                }),
                e.id = f.id,
                h.setup(e, this),
                f
            },
            this.qoe = function() {
                var t = h.getItemQoe(),
                n = v.between("setup", "ready"),
                i = t.between(e.JWPLAYER_MEDIA_PLAY_ATTEMPT, e.JWPLAYER_MEDIA_FIRST_FRAME);
                return {
                    setupTime: n,
                    firstFrame: i,
                    player: v.dump(),
                    item: t.dump()
                }
            },
            this.getContainer = function() {
                return h.getContainer ? h.getContainer() : d
            },
            this.getMeta = this.getItemMeta = function() {
                return m
            },
            this.getPlaylistItem = function(e) {
                if (!i.exists(e)) return h._model.get("playlistItem");
                var t = f.getPlaylist();
                return t ? t[e] : null
            },
            this.getRenderingMode = function() {
                return "html5"
            },
            this.load = function(e) {
                return h.load(e),
                f
            },
            this.play = function(e, n) {
                if (o.isBoolean(e) || (n = e), n || (n = {
                    reason: "external"
                }), e === !0) return h.play(n),
                f;
                if (e === !1) return h.pause(n),
                f;
                switch (e = f.getState()) {
                case t.PLAYING:
                case t.BUFFERING:
                    h.pause(n);
                    break;
                default:
                    h.play(n)
                }
                return f
            },
            this.pause = function(e) {
                return o.isBoolean(e) ? this.play(!e) : this.play()
            },
            this.createInstream = function() {
                return h.createInstream()
            },
            this.castToggle = function() {
                h && h.castToggle && h.castToggle()
            },
            this.playAd = this.pauseAd = i.noop,
            this.remove = function() {
                return p(f),
                f.trigger("remove"),
                y(),
                f
            },
            this
        };
        return d
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(3)],
    r = function(e, t) {
        return function(n) {
            var i = {
                onBufferChange: t.JWPLAYER_MEDIA_BUFFER,
                onBufferFull: t.JWPLAYER_MEDIA_BUFFER_FULL,
                onError: t.JWPLAYER_ERROR,
                onSetupError: t.JWPLAYER_SETUP_ERROR,
                onFullscreen: t.JWPLAYER_FULLSCREEN,
                onMeta: t.JWPLAYER_MEDIA_META,
                onMute: t.JWPLAYER_MEDIA_MUTE,
                onPlaylist: t.JWPLAYER_PLAYLIST_LOADED,
                onPlaylistItem: t.JWPLAYER_PLAYLIST_ITEM,
                onPlaylistComplete: t.JWPLAYER_PLAYLIST_COMPLETE,
                onReady: t.JWPLAYER_READY,
                onResize: t.JWPLAYER_RESIZE,
                onComplete: t.JWPLAYER_MEDIA_COMPLETE,
                onSeek: t.JWPLAYER_MEDIA_SEEK,
                onTime: t.JWPLAYER_MEDIA_TIME,
                onVolume: t.JWPLAYER_MEDIA_VOLUME,
                onBeforePlay: t.JWPLAYER_MEDIA_BEFOREPLAY,
                onBeforeComplete: t.JWPLAYER_MEDIA_BEFORECOMPLETE,
                onDisplayClick: t.JWPLAYER_DISPLAY_CLICK,
                onControls: t.JWPLAYER_CONTROLS,
                onQualityLevels: t.JWPLAYER_MEDIA_LEVELS,
                onQualityChange: t.JWPLAYER_MEDIA_LEVEL_CHANGED,
                onCaptionsList: t.JWPLAYER_CAPTIONS_LIST,
                onCaptionsChange: t.JWPLAYER_CAPTIONS_CHANGED,
                onAdError: t.JWPLAYER_AD_ERROR,
                onAdClick: t.JWPLAYER_AD_CLICK,
                onAdImpression: t.JWPLAYER_AD_IMPRESSION,
                onAdTime: t.JWPLAYER_AD_TIME,
                onAdComplete: t.JWPLAYER_AD_COMPLETE,
                onAdCompanions: t.JWPLAYER_AD_COMPANIONS,
                onAdSkipped: t.JWPLAYER_AD_SKIPPED,
                onAdPlay: t.JWPLAYER_AD_PLAY,
                onAdPause: t.JWPLAYER_AD_PAUSE,
                onAdMeta: t.JWPLAYER_AD_META,
                onCast: t.JWPLAYER_CAST_SESSION,
                onAudioTrackChange: t.JWPLAYER_AUDIO_TRACK_CHANGED,
                onAudioTracks: t.JWPLAYER_AUDIO_TRACKS
            },
            r = {
                onBuffer: "buffer",
                onPause: "pause",
                onPlay: "play",
                onIdle: "idle"
            };
            e.each(r,
            function(t, i) {
                n[i] = e.partial(n.on, t, e)
            }),
            e.each(i,
            function(t, i) {
                n[i] = e.partial(n.on, t, e)
            })
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(114), n(1), n(39), n(38), n(21)],
    r = function(e, t, n, i, r) {
        var o = [],
        a = 0,
        s = function(t) {
            var n, i;
            return t ? "string" == typeof t ? (n = l(t), n || (i = document.getElementById(t))) : "number" == typeof t ? n = o[t] : t.nodeType && (i = t, n = l(i.id)) : n = o[0],
            n ? n: i ? u(new e(i, c)) : {
                registerPlugin: r.registerPlugin
            }
        },
        l = function(e) {
            for (var t = 0; t < o.length; t++) if (o[t].id === e) return o[t];
            return null
        },
        u = function(e) {
            return a++,
            e.uniqueId = a,
            o.push(e),
            e
        },
        c = function(e) {
            for (var t = o.length; t--;) if (o[t].uniqueId === e.uniqueId) {
                o.splice(t, 1);
                break
            }
        },
        d = {
            selectPlayer: s,
            registerProvider: n.registerProvider,
            availableProviders: i,
            registerPlugin: r.registerPlugin
        };
        return s.api = d,
        d
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(167), n(4), n(1), n(3)],
    r = function(e, t, n, i) {
        var r = function(t, r, o, a) {
            function s() {
                p("Setup Timeout Error", "Setup took longer than " + m + " seconds to complete.")
            }
            function l() {
                n.each(g,
                function(e) {
                    e.complete !== !0 && e.running !== !0 && null !== t && c(e.depends) && (e.running = !0, u(e))
                })
            }
            function u(e) {
                var n = function(t) {
                    t = t || {},
                    d(e, t)
                };
                e.method(n, r, t, o, a)
            }
            function c(e) {
                return n.all(e,
                function(e) {
                    return g[e].complete
                })
            }
            function d(e, t) {
                "error" === t.type ? p(t.msg, t.reason) : "complete" === t.type ? (clearTimeout(h), f.trigger(i.JWPLAYER_READY)) : (e.complete = !0, l())
            }
            function p(e, t) {
                clearTimeout(h),
                f.trigger(i.JWPLAYER_SETUP_ERROR, {
                    message: e + ": " + t
                }),
                f.destroy()
            }
            var h, f = this,
            g = e.getQueue(),
            m = 30;
            this.start = function() {
                h = setTimeout(s, 1e3 * m),
                l()
            },
            this.destroy = function() {
                clearTimeout(h),
                this.off(),
                g.length = 0,
                t = null,
                r = null,
                o = null
            }
        };
        return r.prototype = t,
        r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(19), n(18)],
    r = function(e, t, n) {
        var i = function(i, r) {
            function o(e) {
                if (e.tracks.length) {
                    for (var t = e.tracks || [], n = 0; n < t.length; n++) {
                        var i = t[n];
                        v[i._id] || p(i)
                    }
                    var r = h();
                    f(),
                    this.setCaptionsList(r)
                }
            }
            function a(e, t) {
                m = t,
                w = [],
                v = {},
                y = {},
                j = 0
            }
            function s(e) {
                a(r, e);
                var n = e.tracks,
                i = n && n.length;
                if (!r.get("renderCaptionsNatively") && i) {
                    var o, s;
                    for (o = 0; o < i; o++) s = n[o],
                    l(s.kind) && !v[s._id] && (p(s), t.loadFile(s, u.bind(null, s), c))
                }
                var d = h();
                f(),
                this.setCaptionsList(d)
            }
            function l(e) {
                return "subtitles" === e || "captions" === e
            }
            function u(e, t) {
                e.data = t
            }
            function c(t) {
                e.log("CAPTIONS(" + t + ")")
            }
            function d(e, t) {
                var n = null;
                0 !== t && (n = w[t - 1]),
                e.set("captionsTrack", n)
            }
            function p(e) {
                if (e.data = e.data || [], e.name = e.label || e.name || e.language, e._id = n.createId(e, w.length), !e.name) {
                    var t = n.createLabel(e, j);
                    e.name = t.label,
                    j = t.unknownCount
                }
                w.push(e),
                v[e._id] = e
            }
            function h() {
                for (var e = [{
                    id: "off",
                    label: "Off"
                }], t = 0; t < w.length; t++) e.push({
                    id: w[t]._id,
                    label: w[t].name || "Unknown CC"
                });
                return e
            }
            function f() {
                var e = 0,
                t = r.get("captionLabel");
                if ("Off" === t) return void r.set("captionsIndex", 0);
                for (var n = 0; n < w.length; n++) {
                    var i = w[n];
                    if (t && t === i.name) {
                        e = n + 1;
                        break
                    }
                    i["default"] || i.defaulttrack || "default" === i._id ? e = n + 1 : i.autoselect
                }
                g(e)
            }
            function g(e) {
                w.length ? r.setVideoSubtitleTrack(e, w) : r.set("captionsIndex", e)
            }
            r.on("change:playlistItem", a, this),
            r.on("change:captionsIndex", d, this),
            r.on("itemReady", s, this),
            r.mediaController.on("subtitlesTracks", o, this);
            var m = {},
            w = [],
            v = {},
            y = {},
            j = 0;
            this.getCurrentIndex = function() {
                return r.get("captionsIndex")
            },
            this.getCaptionsList = function() {
                return r.get("captionsList")
            },
            this.setCaptionsList = function(e) {
                r.set("captionsList", e)
            }
        };
        return i
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(45), n(120), n(1), n(117), n(118), n(25), n(125), n(50), n(49), n(2), n(75), n(4), n(29), n(5), n(3), n(154)],
    r = function(e, t, i, r, o, a, s, l, u, c, d, p, h, f, g, m) {
        function w(e) {
            return function() {
                var t = Array.prototype.slice.call(arguments, 0);
                this._model.getVideo() ? this["_" + e].apply(this, t) : this.eventsQueue.push([e, t])
            }
        }
        function v(e) {
            return e === f.LOADING || e === f.STALLED ? f.BUFFERING: e
        }
        var y = function(e) {
            this.originalContainer = this.currentContainer = e,
            this.eventsQueue = [],
            i.extend(this, p),
            this._model = new a
        };
        return y.prototype = {
            play: w("play"),
            pause: w("pause"),
            seek: w("seek"),
            stop: w("stop"),
            load: w("load"),
            playlistNext: w("next"),
            playlistPrev: w("prev"),
            playlistItem: w("item"),
            setCurrentCaptions: w("setCurrentCaptions"),
            setCurrentQuality: w("setCurrentQuality"),
            setFullscreen: w("setFullscreen"),
            setup: function(a, p) {
                function m(e, t) {
                    pe.triggerAfterReady(e, t)
                }
                function w() {
                    oe = null,
                    ie.on("all", m, pe),
                    pe.showView(ie.element()),
                    i.defer(y)
                }
                function y() {
                    pe.trigger(g.JWPLAYER_READY, {
                        setupTime: 0
                    }),
                    pe.triggerAfterReady = pe.trigger;
                    for (var e = 0; e < me.length; e++) {
                        var t = me[e];
                        pe.trigger(t.type, t.args)
                    }
                    var n = p.getPlugin("related");
                    n && n.on("nextUp", ue.setNextUp, ue),
                    ne() && (c.isMobile() && he().video ? j(he().video) : pe.play({
                        reason: "autostart"
                    }))
                }
                function j(e) {
                    "IntersectionObserver" in window && "IntersectionObserverEntry" in window && "intersectionRatio" in window.IntersectionObserverEntry.prototype ? b(e) : n.e(9,
                    function(require) {
                        n(54),
                        b(e)
                    })
                }
                function b(e) {
                    le = new window.IntersectionObserver(x, {
                        threshold: .5
                    }),
                    le.observe(e)
                }
                function E() {
                    le.disconnect(),
                    le = void 0
                }
                function x(e) {
                    if (e && e.length) {
                        var t = he().video,
                        n = e[0],
                        i = {
                            reason: "autoplay"
                        };
                        n.target === t && n.intersectionRatio >= .5 ? pe.play(i) : pe.pause(i)
                    }
                }
                function k(e) {
                    var t = ue.getProviders(),
                    n = t.required(e, ue.get("primary"));
                    return t.load(n).then(function() {
                        pe.getProvider() || (ue.setProvider(ue.get("playlistItem")), A())
                    })
                }
                function A() {
                    for (; pe.eventsQueue.length > 0;) {
                        var e = pe.eventsQueue.shift(),
                        t = e[0],
                        n = e[1] || [];
                        pe["_" + t].apply(pe, n)
                    }
                }
                function _(e) {
                    switch (ue.get("state") === f.ERROR && ue.set("state", f.IDLE), ue.set("preInstreamState", "instream-idle"), R(!0), ne() && ue.once("itemReady", P), pe.trigger("destroyPlugin", {}), typeof e) {
                    case "string":
                        C(e);
                        break;
                    case "object":
                        var t = D(e);
                        t && N(0);
                        break;
                    case "number":
                        N(e)
                    }
                }
                function C(e) {
                    var t = new u;
                    t.on(g.JWPLAYER_PLAYLIST_LOADED,
                    function(e) {
                        _(e.playlist)
                    }),
                    t.on(g.JWPLAYER_ERROR,
                    function(e) {
                        e.message = "Error loading playlist: " + e.message,
                        this.triggerError(e)
                    },
                    this),
                    t.load(e)
                }
                function L() {
                    return pe._instreamAdapter && pe._instreamAdapter.getState()
                }
                function T() {
                    var e = L();
                    return i.isString(e) ? e: ue.get("state")
                }
                function P(e) {
                    var t;
                    if (e && ue.set("playReason", e.reason), ue.get("state") !== f.ERROR) {
                        var n = L();
                        if (i.isString(n)) return p.pauseAd(!1);
                        if (ue.get("state") === f.COMPLETE && (R(!0), N(0)), !ce && (ce = !0, pe.trigger(g.JWPLAYER_MEDIA_BEFOREPLAY, {
                            playReason: ue.get("playReason")
                        }), ce = !1, se)) return se = !1,
                        void(ae = null);
                        if (M()) {
                            if (0 === ue.get("playlist").length) return ! 1;
                            t = c.tryCatch(function() {
                                ue.loadVideo()
                            })
                        } else ue.get("state") === f.PAUSED && (t = c.tryCatch(function() {
                            ue.playVideo()
                        }));
                        return ! (t instanceof c.Error) || (pe.triggerError(t), ae = null, !1)
                    }
                }
                function R(e) {
                    ue.off("itemReady", P);
                    var t = !e;
                    ae = null;
                    var n = c.tryCatch(function() {
                        ue.stopVideo()
                    },
                    pe);
                    return n instanceof c.Error ? (pe.triggerError(n), !1) : (t && (de = !0), ce && (se = !0), !0)
                }
                function I(e) {
                    ae = null,
                    e && (ue.set("pauseReason", e.reason), !le || "interaction" !== e.reason && "external" !== e.reason || E());
                    var t = L();
                    if (i.isString(t)) return p.pauseAd(!0);
                    switch (ue.get("state")) {
                    case f.ERROR:
                        return ! 1;
                    case f.PLAYING:
                    case f.BUFFERING:
                        var n = c.tryCatch(function() {
                            he().pause()
                        },
                        this);
                        if (n instanceof c.Error) return pe.triggerError(n),
                        !1;
                        break;
                    default:
                        ce && (se = !0)
                    }
                    return ! 0
                }
                function M() {
                    var e = ue.get("state");
                    return e === f.IDLE || e === f.COMPLETE || e === f.ERROR
                }
                function S(e) {
                    ue.get("state") !== f.ERROR && (ue.get("scrubbing") || ue.get("state") === f.PLAYING || P(!0), he().seek(e))
                }
                function O(e, t) {
                    R(!0),
                    ue.get("state") === f.ERROR && ue.set("state", f.IDLE),
                    N(e),
                    P(t)
                }
                function D(e) {
                    var t = l(e);
                    return t = l.filterPlaylist(t, ue.getProviders(), ue.get("androidhls"), ue.get("drm"), ue.get("preload"), ue.get("feedid"), ue.get("withCredentials")),
                    ue.set("playlist", t),
                    i.isArray(t) && 0 !== t.length ? (k(t), !0) : (pe.triggerError({
                        message: "Error loading playlist: No playable sources found"
                    }), !1)
                }
                function N(e) {
                    ue.setItemIndex(e)
                }
                function Y(e) {
                    O(ue.get("item") - 1, e || {
                        reason: "external"
                    })
                }
                function F(e) {
                    O(ue.get("item") + 1, e || {
                        reason: "external"
                    })
                }
                function W() {
                    if (M()) {
                        if (de) return void(de = !1);
                        ae = W;
                        var e = ue.get("item");
                        return e === ue.get("playlist").length - 1 ? void(ue.get("repeat") ? F({
                            reason: "repeat"
                        }) : (le && E(), ue.set("state", f.COMPLETE), pe.trigger(g.JWPLAYER_PLAYLIST_COMPLETE, {}))) : void F({
                            reason: "playlist"
                        })
                    }
                }
                function J(e) {
                    he() && (e = parseInt(e, 10) || 0, he().setCurrentQuality(e))
                }
                function B() {
                    return he() ? he().getCurrentQuality() : -1
                }
                function V() {
                    if (this._model) return this._model.getConfiguration()
                }
                function z() {
                    if (this._model.mediaModel) return this._model.mediaModel.get("visualQuality");
                    var e = U();
                    if (e) {
                        var t = B(),
                        n = e[t];
                        if (n) return {
                            level: i.extend({
                                index: t
                            },
                            n),
                            mode: "",
                            reason: ""
                        }
                    }
                    return null
                }
                function U() {
                    return he() ? he().getQualityLevels() : null
                }
                function H(e) {
                    he() && (e = parseInt(e, 10) || 0, he().setCurrentAudioTrack(e))
                }
                function G() {
                    return he() ? he().getCurrentAudioTrack() : -1
                }
                function K() {
                    return he() ? he().getAudioTracks() : null
                }
                function q(e) {
                    e = parseInt(e, 10) || 0,
                    ue.persistVideoSubtitleTrack(e),
                    pe.trigger(g.JWPLAYER_CAPTIONS_CHANGED, {
                        tracks: Q(),
                        track: e
                    })
                }
                function X() {
                    return re.getCurrentIndex()
                }
                function Q() {
                    return re.getCaptionsList()
                }
                function $() {
                    var e = ue.getVideo();
                    if (e) {
                        var t = e.detachMedia();
                        if (t instanceof HTMLVideoElement) return t
                    }
                    return null
                }
                function Z() {
                    var e = c.tryCatch(function() {
                        ue.getVideo().attachMedia()
                    });
                    return e instanceof c.Error ? void c.log("Error calling _attachMedia", e) : void("function" == typeof ae && ae())
                }
                function ee(e) {
                    i.isBoolean(e) || (e = !ue.get("fullscreen")),
                    ue.set("fullscreen", e),
                    this._instreamAdapter && this._instreamAdapter._adModel && this._instreamAdapter._adModel.set("fullscreen", e)
                }
                function te() {
                    var e = p.getPlugin("related");
                    e && e.next()
                }
                function ne() {
                    return ue.get("autostart") && !c.isMobile() || ue.autoStartOnMobile()
                }
                var ie, re, oe, ae, se, le, ue = this._model,
                ce = !1,
                de = !1,
                pe = this,
                he = function() {
                    return ue.getVideo()
                },
                fe = new s;
                fe.track(ue);
                var ge = new e(a, fe),
                me = [];
                ue.setup(ge, fe),
                ie = this._view = new d(p, ue),
                oe = new r(p, ue, ie, D),
                oe.on(g.JWPLAYER_READY, w, this),
                oe.on(g.JWPLAYER_SETUP_ERROR, this.setupError, this),
                ue.mediaController.on("all", m, this),
                ue.mediaController.on(g.JWPLAYER_MEDIA_COMPLETE,
                function() {
                    i.defer(W)
                }),
                ue.mediaController.on(g.JWPLAYER_MEDIA_ERROR, this.triggerError, this),
                ue.on("change:flashBlocked",
                function(e, t) {
                    if (!t) return void this._model.set("errorEvent", void 0);
                    var n = !!e.get("flashThrottle"),
                    i = {
                        message: n ? "Click to run Flash": "Flash plugin failed to load"
                    };
                    n || this.trigger(g.JWPLAYER_ERROR, i),
                    this._model.set("errorEvent", i)
                },
                this),
                ue.on("change:state", h, this),
                ue.on("change:castState",
                function(e, t) {
                    pe.trigger(g.JWPLAYER_CAST_SESSION, t)
                }),
                ue.on("change:fullscreen",
                function(e, t) {
                    pe.trigger(g.JWPLAYER_FULLSCREEN, {
                        fullscreen: t
                    }),
                    t && le && E()
                }),
                ue.on("itemReady",
                function() {
                    pe.triggerAfterReady(g.JWPLAYER_PLAYLIST_ITEM, {
                        index: ue.get("item"),
                        item: ue.get("playlistItem")
                    })
                }),
                ue.on("change:playlist",
                function(e, t) {
                    t.length && pe.triggerAfterReady(g.JWPLAYER_PLAYLIST_LOADED, {
                        playlist: t
                    })
                }),
                ue.on("change:volume",
                function(e, t) {
                    pe.trigger(g.JWPLAYER_MEDIA_VOLUME, {
                        volume: t
                    })
                }),
                ue.on("change:mute",
                function(e, t) {
                    pe.trigger(g.JWPLAYER_MEDIA_MUTE, {
                        mute: t
                    })
                }),
                ue.on("change:controls",
                function(e, t) {
                    pe.trigger(g.JWPLAYER_CONTROLS, {
                        controls: t
                    })
                }),
                ue.on("change:scrubbing",
                function(e, t) {
                    t ? I() : P()
                }),
                ue.on("change:captionsList",
                function(e, t) {
                    try {
                        pe.triggerAfterReady(g.JWPLAYER_CAPTIONS_LIST, {
                            tracks: t,
                            track: X()
                        })
                    } catch(n) {
                        c.log("Error with captionsList event:", n)
                    }
                }),
                ue.on("change:mediaModel",
                function(e) {
                    e.mediaModel.on("change:state",
                    function(t, n) {
                        var i = v(n);
                        e.set("state", i)
                    })
                }),
                re = new o(p, ue),
                this.triggerAfterReady = function(e, t) {
                    me.push({
                        type: e,
                        args: t
                    })
                },
                this._play = P,
                this._pause = I,
                this._seek = S,
                this._stop = R,
                this._load = _,
                this._next = F,
                this._prev = Y,
                this._item = O,
                this._setCurrentCaptions = q,
                this._setCurrentQuality = J,
                this._setFullscreen = ee,
                this.detachMedia = $,
                this.attachMedia = Z,
                this.getCurrentQuality = B,
                this.getQualityLevels = U,
                this.setCurrentAudioTrack = H,
                this.getCurrentAudioTrack = G,
                this.getAudioTracks = K,
                this.getCurrentCaptions = X,
                this.getCaptionsList = Q,
                this.getVisualQuality = z,
                this.getConfig = V,
                this.getState = T,
                this.setVolume = ue.setVolume.bind(ue),
                this.setMute = ue.setMute.bind(ue),
                this.getProvider = function() {
                    return ue.get("provider")
                },
                this.getWidth = function() {
                    return ue.get("containerWidth")
                },
                this.getHeight = function() {
                    return ue.get("containerHeight")
                },
                this.getContainer = function() {
                    return this.currentContainer
                },
                this.resize = ie.resize,
                this.getSafeRegion = ie.getSafeRegion,
                this.setCues = ie.addCues,
                this.setCaptions = ie.setCaptions,
                this.next = te,
                this.addButton = function(e, t, n, r, o) {
                    var a = {
                        img: e,
                        tooltip: t,
                        callback: n,
                        id: r,
                        btnClass: o
                    },
                    s = ue.get("dock");
                    s = s ? s.slice(0) : [],
                    s = i.reject(s, i.matches({
                        id: a.id
                    })),
                    s.push(a),
                    ue.set("dock", s)
                },
                this.removeButton = function(e) {
                    var t = ue.get("dock") || [];
                    t = i.reject(t, i.matches({
                        id: e
                    })),
                    ue.set("dock", t)
                },
                this.checkBeforePlay = function() {
                    return ce
                },
                this.getItemQoe = function() {
                    return ue._qoeItem
                },
                this.setControls = function(e) {
                    i.isBoolean(e) || (e = !ue.get("controls")),
                    ue.set("controls", e);
                    var t = ue.getVideo();
                    t && t.setControls(e)
                },
                this.playerDestroy = function() {
                    this.stop(),
                    this.showView(this.originalContainer),
                    ie && ie.destroy(),
                    ue && ue.destroy(),
                    oe && (oe.destroy(), oe = null)
                },
                this.isBeforePlay = this.checkBeforePlay,
                this.isBeforeComplete = function() {
                    return ue.getVideo().checkComplete()
                },
                this.createInstream = function() {
                    return this.instreamDestroy(),
                    this._instreamAdapter = new t(this, ue, ie),
                    this._instreamAdapter
                },
                this.skipAd = function() {
                    this._instreamAdapter && this._instreamAdapter.skipAd()
                },
                this.instreamDestroy = function() {
                    pe._instreamAdapter && pe._instreamAdapter.destroy()
                },
                oe.start()
            },
            showView: function(e) { (document.documentElement.contains(this.currentContainer) || (this.currentContainer = document.getElementById(this._model.get("id")), this.currentContainer)) && (this.currentContainer.parentElement && this.currentContainer.parentElement.replaceChild(e, this.currentContainer), this.currentContainer = e)
            },
            triggerError: function(e) {
                this._model.set("errorEvent", e),
                this._model.set("state", f.ERROR),
                this._model.once("change:state",
                function() {
                    this._model.set("errorEvent", void 0)
                },
                this),
                this.trigger(g.JWPLAYER_ERROR, e)
            },
            setupError: function(e) {
                var t = e.message,
                n = c.createElement(m(this._model.get("id"), this._model.get("skin"), t)),
                r = this._model.get("width"),
                o = this._model.get("height");
                c.style(n, {
                    width: r.toString().indexOf("%") > 0 ? r: r + "px",
                    height: o.toString().indexOf("%") > 0 ? o: o + "px"
                }),
                this.showView(n);
                var a = this;
                i.defer(function() {
                    a.trigger(g.JWPLAYER_SETUP_ERROR, {
                        message: t
                    })
                })
            }
        },
        y
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(122), n(121), n(3), n(5), n(2), n(4), n(1)],
    r = function(e, t, n, i, r, o, a) {
        function s(n) {
            var i = "",
            r = n.get("provider");
            return r && (i = r.name),
            i.indexOf("flash") >= 0 ? t: e
        }
        var l = {
            skipoffset: null,
            tag: null
        },
        u = function(e, o, u) {
            function c() {
                y._adModel.set("state", "buffering"),
                o.set("skipButton", !1),
                j++;
                var e, t = h[j];
                f && (e = f[j]),
                E.loadItem(t, e)
            }
            function d(e, t) {
                "complete" !== e && (t = t || {},
                b.tag && !t.tag && (t.tag = b.tag), this.trigger(e, t), "mediaError" !== e && "error" !== e || h && j + 1 < h.length && c())
            }
            function p(e) {
                y._adModel.set("duration", e.duration),
                y._adModel.set("position", e.position)
            }
            var h, f, g, m, w, v = s(o),
            y = new v(e, o),
            j = 0,
            b = {},
            E = this,
            x = a.bind(function(e) {
                e = e || {},
                e.hasControls = !!o.get("controls"),
                this.trigger(n.JWPLAYER_INSTREAM_CLICK, e),
                y && y._adModel && (y._adModel.get("state") === i.PAUSED ? e.hasControls && y.instreamPlay() : y.instreamPause())
            },
            this),
            k = a.bind(function() {
                y && y._adModel && y._adModel.get("state") === i.PAUSED && o.get("controls") && (e.setFullscreen(), e.play())
            },
            this);
            this.type = "instream",
            this.init = function() {
                g = o.getVideo(),
                m = o.get("position"),
                w = o.get("playlist")[o.get("item")],
                y.on("all", d, this),
                y.on(n.JWPLAYER_MEDIA_TIME, p, this),
                y.on(n.JWPLAYER_MEDIA_COMPLETE, A, this),
                y.init(),
                g.detachMedia(),
                o.mediaModel.set("state", i.BUFFERING),
                e.checkBeforePlay() || 0 === m && !g.checkComplete() ? (m = 0, o.set("preInstreamState", "instream-preroll")) : g && g.checkComplete() || o.get("state") === i.COMPLETE ? o.set("preInstreamState", "instream-postroll") : o.set("preInstreamState", "instream-midroll");
                var t = o.get("state");
                return t !== i.PLAYING && t !== i.BUFFERING || g.pause(),
                u.setupInstream(y._adModel),
                y._adModel.set("state", i.BUFFERING),
                u.clickHandler().setAlternateClickHandlers(r.noop, null),
                this.setText(o.get("localization").loadingAd),
                this
            };
            var A = function(e) {
                var t = {};
                b.tag && (t.tag = b.tag),
                h && j + 1 < h.length ? (this.trigger(n.JWPLAYER_MEDIA_COMPLETE, t), c()) : (e.type === n.JWPLAYER_MEDIA_COMPLETE && (this.trigger(n.JWPLAYER_MEDIA_COMPLETE, t), this.trigger(n.JWPLAYER_PLAYLIST_COMPLETE, {})), this.destroy())
            };
            this.loadItem = function(e, i) {
                if (r.isAndroid(2.3)) return void this.trigger({
                    type: n.JWPLAYER_ERROR,
                    message: "Error loading instream: Cannot play instream on Android 2.3"
                });
                var s = e;
                a.isArray(e) ? (h = e, f = i, e = h[j], f && (i = f[j])) : s = [e];
                var u = this,
                c = o.getProviders(),
                d = v === t ? "flash": void 0,
                p = c.required(s, d);
                o.set("hideAdsControls", !1),
                c.load(p).then(function() {
                    if (null !== y) {
                        u.trigger(n.JWPLAYER_PLAYLIST_ITEM, {
                            index: j,
                            item: e
                        }),
                        b = a.extend({},
                        l, i),
                        y.load(e),
                        u.addClickHandler();
                        var t = e.skipoffset || b.skipoffset;
                        t && u.setupSkipButton(t, b)
                    }
                })
            },
            this.setupSkipButton = function(e, t, n) {
                o.set("skipButton", !1),
                n && (A = n),
                y._adModel.set("skipMessage", t.skipMessage),
                y._adModel.set("skipText", t.skipText),
                y._adModel.set("skipOffset", e),
                o.set("skipButton", !0)
            },
            this.applyProviderListeners = function(e) {
                y.applyProviderListeners(e),
                this.addClickHandler()
            },
            this.play = function() {
                y.instreamPlay()
            },
            this.pause = function() {
                y.instreamPause()
            },
            this.addClickHandler = function() {
                u.clickHandler().setAlternateClickHandlers(x, k),
                y.on(n.JWPLAYER_MEDIA_META, this.metaHandler, this)
            },
            this.skipAd = function(e) {
                var t = n.JWPLAYER_AD_SKIPPED;
                this.trigger(t, e),
                A.call(this, {
                    type: t
                })
            },
            this.metaHandler = function(e) {
                e.width && e.height && u.resizeMedia()
            },
            this.destroy = function() {
                if (this.off(), o.set("skipButton", !1), y) {
                    u.clickHandler() && u.clickHandler().revertAlternateClickHandlers(),
                    o.off(null, null, y),
                    y.instreamDestroy(),
                    u.destroyInstream(),
                    y = null,
                    e.attachMedia();
                    var t = o.get("preInstreamState");
                    switch (t) {
                    case "instream-preroll":
                    case "instream-midroll":
                        var n = a.extend({},
                        w);
                        n.starttime = m,
                        o.loadVideo(n),
                        r.isMobile() && o.mediaModel.get("state") === i.BUFFERING && g.setState(i.BUFFERING),
                        g.play();
                        break;
                    case "instream-postroll":
                    case "instream-idle":
                        g.stop()
                    }
                }
            },
            this.getState = function() {
                return ! (!y || !y._adModel) && y._adModel.get("state")
            },
            this.setText = function(e) {
                u.setAltText(e ? e: "")
            },
            this.hide = function() {
                o.set("hideAdsControls", !0)
            }
        };
        return a.extend(u.prototype, o),
        u
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(4), n(25), n(29), n(3), n(5), n(2), n(1)],
    r = function(e, t, n, i, r, o, a) {
        var s = function(e, i) {
            this.model = i,
            this._adModel = (new t).setup({
                id: i.get("id"),
                volume: i.get("volume"),
                fullscreen: i.get("fullscreen"),
                mute: i.get("mute")
            }),
            this._adModel.on("change:state", n, this);
            var r = e.getContainer();
            this.swf = r.querySelector("object")
        };
        return s.prototype = a.extend({
            init: function() {
                if (o.isChrome()) {
                    var e = -1,
                    t = !1;
                    this.swf.on("throttle",
                    function(n) {
                        if (clearTimeout(e), "resume" === n.state) t && (t = !1, this.instreamPlay());
                        else {
                            var i = this;
                            e = setTimeout(function() {
                                i._adModel.get("state") === r.PLAYING && (t = !0, i.instreamPause())
                            },
                            250)
                        }
                    },
                    this)
                }
                this.swf.on("instream:state", this.stateHandler, this).on("instream:time",
                function(e) {
                    this._adModel.set("position", e.position),
                    this._adModel.set("duration", e.duration),
                    this.trigger(i.JWPLAYER_MEDIA_TIME, e)
                },
                this).on("instream:complete",
                function(e) {
                    this.trigger(i.JWPLAYER_MEDIA_COMPLETE, e)
                },
                this).on("instream:error",
                function(e) {
                    this.trigger(i.JWPLAYER_MEDIA_ERROR, e)
                },
                this),
                this.swf.triggerFlash("instream:init"),
                this.applyProviderListeners = function(e) {
                    this.model.on("change:volume",
                    function(t, n) {
                        e.volume(n)
                    },
                    this),
                    this.model.on("change:mute",
                    function(t, n) {
                        e.mute(n)
                    },
                    this),
                    e.volume(this.model.get("volume")),
                    e.mute(this.model.get("mute")),
                    e.off(),
                    e.on(i.JWPLAYER_PLAYER_STATE, this.stateHandler, this),
                    e.on(i.JWPLAYER_MEDIA_TIME,
                    function(e) {
                        this.trigger(i.JWPLAYER_MEDIA_TIME, e)
                    },
                    this)
                }
            },
            stateHandler: function(e) {
                switch (e.newstate) {
                case r.PLAYING:
                case r.PAUSED:
                    this._adModel.set("state", e.newstate)
                }
            },
            instreamDestroy: function() {
                this._adModel && (this.off(), this.swf.off(null, null, this), this.swf.triggerFlash("instream:destroy"), this.swf = null, this._adModel.off(), this._adModel = null, this.model = null)
            },
            load: function(e) {
                this.swf.triggerFlash("instream:load", e)
            },
            instreamPlay: function() {
                this.swf.triggerFlash("instream:play")
            },
            instreamPause: function() {
                this.swf.triggerFlash("instream:pause")
            }
        },
        e),
        s
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(4), n(29), n(3), n(5), n(25)],
    r = function(e, t, n, i, r, o) {
        var a = function(a, s) {
            function l(t) {
                var r = t || p.getVideo();
                if (h !== r) {
                    if (h = r, !r) return;
                    r.off(),
                    r.on("all",
                    function(t, n) {
                        n = e.extend({},
                        n, {
                            type: t
                        }),
                        this.trigger(t, n)
                    },
                    f),
                    r.on(i.JWPLAYER_MEDIA_BUFFER_FULL, d),
                    r.on(i.JWPLAYER_PLAYER_STATE, u),
                    r.attachMedia(),
                    r.volume(s.get("volume")),
                    r.mute(s.get("mute") || s.get("autostartMuted")),
                    p.on("change:state", n, f)
                }
            }
            function u(e) {
                switch (e.newstate) {
                case r.PLAYING:
                case r.PAUSED:
                    p.set("state", e.newstate)
                }
            }
            function c(e) {
                s.trigger(e.type, e),
                f.trigger(i.JWPLAYER_FULLSCREEN, {
                    fullscreen: e.jwstate
                })
            }
            function d() {
                p.getVideo().play()
            }
            var p, h, f = e.extend(this, t);
            return a.on(i.JWPLAYER_FULLSCREEN,
            function(e) {
                this.trigger(i.JWPLAYER_FULLSCREEN, e)
            },
            f),
            this.init = function() {
                p = (new o).setup({
                    id: s.get("id"),
                    volume: s.get("volume"),
                    fullscreen: s.get("fullscreen"),
                    mute: s.get("mute") || s.get("autostartMuted"),
                    instreamMode: !0
                }),
                p.on("fullscreenchange", c),
                this._adModel = p
            },
            f.load = function(e) {
                p.set("item", 0),
                p.set("playlistItem", e),
                p.setActiveItem(e),
                l(),
                p.off(i.JWPLAYER_ERROR),
                p.on(i.JWPLAYER_ERROR,
                function(e) {
                    this.trigger(i.JWPLAYER_ERROR, e)
                },
                f),
                p.loadVideo(e)
            },
            f.applyProviderListeners = function(e) {
                l(e),
                e.off(i.JWPLAYER_ERROR),
                e.on(i.JWPLAYER_ERROR,
                function(e) {
                    this.trigger(i.JWPLAYER_ERROR, e)
                },
                f),
                s.on("change:volume",
                function(e, t) {
                    h.volume(t)
                },
                f),
                s.on("change:mute",
                function(e, t) {
                    h.mute(t)
                },
                f),
                s.on("change:autostartMuted",
                function(e, t) {
                    t || h.mute(s.get("mute"))
                },
                f)
            },
            this.instreamDestroy = function() {
                p && (p.off(), this.off(), h && (h.detachMedia(), h.off(), p.getVideo() && h.destroy()), p = null, a.off(null, null, this), a = null)
            },
            f.instreamPlay = function() {
                p.getVideo() && p.getVideo().play(!0)
            },
            f.instreamPause = function() {
                p.getVideo() && p.getVideo().pause(!0)
            },
            f
        };
        return a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(64), n(3), n(1)],
    r = function(e, t, n) {
        function i(e) {
            e.mediaController.off(t.JWPLAYER_MEDIA_PLAY_ATTEMPT, e._onPlayAttempt),
            e.mediaController.off(t.JWPLAYER_PROVIDER_FIRST_FRAME, e._triggerFirstFrame),
            e.mediaController.off(t.JWPLAYER_MEDIA_TIME, e._onTime)
        }
        function r(e) {
            i(e),
            e._triggerFirstFrame = n.once(function() {
                var n = e._qoeItem;
                n.tick(t.JWPLAYER_MEDIA_FIRST_FRAME);
                var r = n.between(t.JWPLAYER_MEDIA_PLAY_ATTEMPT, t.JWPLAYER_MEDIA_FIRST_FRAME);
                e.mediaController.trigger(t.JWPLAYER_MEDIA_FIRST_FRAME, {
                    loadTime: r
                }),
                i(e)
            }),
            e._onTime = a(e._triggerFirstFrame),
            e._onPlayAttempt = function() {
                e._qoeItem.tick(t.JWPLAYER_MEDIA_PLAY_ATTEMPT)
            },
            e.mediaController.on(t.JWPLAYER_MEDIA_PLAY_ATTEMPT, e._onPlayAttempt),
            e.mediaController.once(t.JWPLAYER_PROVIDER_FIRST_FRAME, e._triggerFirstFrame),
            e.mediaController.on(t.JWPLAYER_MEDIA_TIME, e._onTime)
        }
        function o(n) {
            function i(n, i, o) {
                n._qoeItem && o && n._qoeItem.end(o.get("state")),
                n._qoeItem = new e,
                n._qoeItem.tick(t.JWPLAYER_PLAYLIST_ITEM),
                n._qoeItem.start(i.get("state")),
                r(n),
                i.on("change:state",
                function(e, t, i) {
                    n._qoeItem.end(i),
                    n._qoeItem.start(t)
                })
            }
            n.on("change:mediaModel", i)
        }
        var a = function(e) {
            var t = 0;
            return function(n) {
                var i = n.position;
                i > t && e(),
                t = i
            }
        };
        return {
            model: o
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(21), n(49), n(15), n(61), n(22), n(1), n(2), n(3)],
    r = function(e, t, i, r, o, a, s, l) {
        function u() {
            var e = {
                LOAD_PROMISE_POLYFILL: {
                    method: c,
                    depends: []
                },
                LOAD_BASE64_POLYFILL: {
                    method: d,
                    depends: []
                },
                LOADED_POLYFILLS: {
                    method: p,
                    depends: ["LOAD_PROMISE_POLYFILL", "LOAD_BASE64_POLYFILL"]
                },
                LOAD_PLUGINS: {
                    method: h,
                    depends: ["LOADED_POLYFILLS"]
                },
                INIT_PLUGINS: {
                    method: f,
                    depends: ["LOAD_PLUGINS", "SETUP_VIEW"]
                },
                LOAD_SKIN: {
                    method: E,
                    depends: ["LOADED_POLYFILLS"]
                },
                LOAD_PLAYLIST: {
                    method: m,
                    depends: ["LOADED_POLYFILLS"]
                },
                CHECK_FLASH: {
                    method: w,
                    depends: ["LOADED_POLYFILLS"]
                },
                FILTER_PLAYLIST: {
                    method: v,
                    depends: ["LOAD_PLAYLIST", "CHECK_FLASH"]
                },
                SETUP_VIEW: {
                    method: x,
                    depends: ["LOAD_SKIN"]
                },
                SET_ITEM: {
                    method: k,
                    depends: ["INIT_PLUGINS", "FILTER_PLAYLIST"]
                },
                SEND_READY: {
                    method: A,
                    depends: ["SETUP_VIEW", "SET_ITEM"]
                }
            };
            return e
        }
        function c(e) {
            window.Promise ? e() : n.e(8,
            function(require) {
                n(55),
                e()
            })
        }
        function d(e) {
            window.btoa && window.atob ? e() : n.e(10,
            function(require) {
                n(53),
                e()
            })
        }
        function p(e) {
            e()
        }
        function h(t, n) {
            window.jwplayerPluginJsonp = e.registerPlugin,
            C = e.loadPlugins(n.get("id"), n.get("plugins")),
            C.on(l.COMPLETE, t),
            C.on(l.ERROR, a.partial(g, t)),
            C.load()
        }
        function f(e, t, n) {
            delete window.jwplayerPluginJsonp,
            C.setupPlugins(n, t),
            e()
        }
        function g(e, t) {
            _(e, "Could not load plugin", t.message)
        }
        function m(e, n) {
            var i = n.get("playlist");
            a.isString(i) ? (L = new t, L.on(l.JWPLAYER_PLAYLIST_LOADED,
            function(t) {
                n.set("playlist", t.playlist),
                n.set("feedid", t.feedid),
                e()
            }), L.on(l.JWPLAYER_ERROR, a.partial(y, e)), L.load(i)) : e()
        }
        function w(e, t, n) {
            var i = "flash" === t.get("primary"),
            o = s.flashVersion();
            if (i && o) {
                var a = n.getContainer(),
                l = a.parentElement;
                l || e();
                var u = document.createElement("div");
                u.id = t.get("id");
                var c = "" + u.id + "-" + Math.random().toString(16).substr(2),
                d = t.get("flashloader"),
                p = t.get("width"),
                h = t.get("height");
                s.style(u, {
                    position: "relative",
                    width: p.toString().indexOf("%") > 0 ? p: p + "px",
                    height: h.toString().indexOf("%") > 0 ? h: h + "px"
                });
                var f = r.embed(d, u, c, null);
                l.replaceChild(u, a);
                var g = function() {
                    m !== -1 && (clearTimeout(m), m = -1, e())
                };
                f.embedCallback = g;
                var m = setTimeout(function() {
                    t.set("primary", void 0),
                    t.updateProviders(),
                    g()
                },
                1500)
            } else e()
        }
        function v(e, t, n, i, r) {
            var o = t.get("playlist"),
            a = r(o);
            a ? e() : y(e)
        }
        function y(e, t) {
            t && t.message ? _(e, "Error loading playlist", t.message) : _(e, "Error loading player", "No playable sources found")
        }
        function j(e, t) {
            if (a.contains(o.SkinsLoadable, e)) return t + "skins/" + e + ".css"
        }
        function b(e) {
            for (var t = document.styleSheets,
            n = 0,
            i = t.length; n < i; n++) if (t[n].href === e) return ! 0;
            return ! 1
        }
        function E(e, t) {
            var n = t.get("skin"),
            r = t.get("skinUrl");
            if (a.contains(o.SkinsIncluded, n)) return void e();
            if (r || (r = j(n, t.get("base"))), a.isString(r) && !b(r)) {
                t.set("skin-loading", !0);
                var s = !0,
                u = new i(r, s);
                u.addEventListener(l.COMPLETE,
                function() {
                    t.set("skin-loading", !1)
                }),
                u.addEventListener(l.ERROR,
                function() {
                    t.set("skin", "seven"),
                    t.set("skin-loading", !1)
                }),
                u.load()
            }
            a.defer(function() {
                e()
            })
        }
        function x(e, t, n, i) {
            i.setup(),
            e()
        }
        function k(e, t) {
            t.once("itemReady", e),
            t.setItemIndex(t.get("item"))
        }
        function A(e) {
            e({
                type: "complete"
            })
        }
        function _(e, t, n) {
            e({
                type: "error",
                msg: t,
                reason: n
            })
        }
        var C, L;
        return {
            getQueue: u,
            error: _
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2)],
    r = function(e, t) {
        function n(e) {
            return "jwplayer." + e
        }
        function i() {
            return e.reduce(this.persistItems,
            function(e, i) {
                var r = l[n(i)];
                return r && (e[i] = t.serialize(r)),
                e
            },
            {})
        }
        function r(e, t) {
            try {
                l[n(e)] = t
            } catch(i) {
                var r = window.jwplayer;
                r && r.debug && console.error(i)
            }
        }
        function o() {
            e.each(this.persistItems,
            function(e) {
                l.removeItem(n(e))
            })
        }
        function a() {
            this.persistItems = ["volume", "mute", "captionLabel", "qualityLabel"]
        }
        function s(t) {
            e.each(this.persistItems,
            function(e) {
                t.on("change:" + e,
                function(t, n) {
                    r(e, n)
                })
            })
        }
        var l = {
            removeItem: t.noop
        };
        try {
            l = window.localStorage
        } catch(u) {}
        return e.extend(a.prototype, {
            getAllItems: i,
            track: s,
            clear: o
        }),
        a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(69), n(2)],
    r = function(e, t) {
        return n.p = t.loadFrom(),
        e.selectPlayer
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(8)],
    r = function(e) {
        function t(e) {
            e || n()
        }
        function n() {
            throw new Error("Invalid DFXP file")
        }
        var i = e.seconds;
        return function(r) {
            t(r);
            var o = [],
            a = r.getElementsByTagName("p"),
            s = 30,
            l = r.getElementsByTagName("tt");
            if (l && l[0]) {
                var u = parseFloat(l[0].getAttribute("ttp:frameRate"));
                isNaN(u) || (s = u)
            }
            t(a),
            a.length || (a = r.getElementsByTagName("tt:p"), a.length || (a = r.getElementsByTagName("tts:p")));
            for (var c = 0; c < a.length; c++) {
                for (var d = a[c], p = d.getElementsByTagName("br"), h = 0; h < p.length; h++) {
                    var f = p[h];
                    f.parentNode.replaceChild(r.createTextNode("\r\n"), f)
                }
                var g = d.innerHTML || d.textContent || d.text || "",
                m = e.trim(g).replace(/>\s+</g, "><").replace(/(<\/?)tts?:/g, "$1").replace(/<br.*?\/>/g, "\r\n");
                if (m) {
                    var w = d.getAttribute("begin"),
                    v = d.getAttribute("dur"),
                    y = d.getAttribute("end"),
                    j = {
                        begin: i(w, s),
                        text: m
                    };
                    y ? j.end = i(y, s) : v && (j.end = j.begin + i(v, s)),
                    o.push(j)
                }
            }
            return o.length || n(),
            o
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(20), n(8), n(2)],
    r = function(e, t, n) {
        var i = "jwplayer",
        r = function(r, o) {
            for (var a = [], s = [], l = t.xmlAttribute, u = "default", c = "label", d = "file", p = "type", h = 0; h < r.childNodes.length; h++) {
                var f = r.childNodes[h];
                if (f.prefix === i) {
                    var g = e.localName(f);
                    "source" === g ? (delete o.sources, a.push({
                        file: l(f, d),
                        "default": l(f, u),
                        label: l(f, c),
                        type: l(f, p)
                    })) : "track" === g ? (delete o.tracks, s.push({
                        file: l(f, d),
                        "default": l(f, u),
                        kind: l(f, "kind"),
                        label: l(f, c)
                    })) : (o[g] = n.serialize(e.textContent(f)), "file" === g && o.sources && delete o.sources)
                }
                o[d] || (o[d] = o.link)
            }
            if (a.length) for (o.sources = [], h = 0; h < a.length; h++) a[h].file.length > 0 && (a[h][u] = "true" === a[h][u], a[h].label.length || delete a[h].label, o.sources.push(a[h]));
            if (s.length) for (o.tracks = [], h = 0; h < s.length; h++) s[h].file.length > 0 && (s[h][u] = "true" === s[h][u], s[h].kind = s[h].kind.length ? s[h].kind: "captions", s[h].label.length || delete s[h].label, o.tracks.push(s[h]));
            return o
        };
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(20), n(8), n(2)],
    r = function(e, t, n) {
        function i(e) {
            for (var t = [], n = 0; n < s(e); n++) {
                var i = e.childNodes[n];
                "jwplayer" === i.prefix && "mediatypes" === o(i).toLowerCase() && t.push(a(i))
            }
            return t
        }
        var r = t.xmlAttribute,
        o = e.localName,
        a = e.textContent,
        s = e.numChildren,
        l = "media",
        u = function(e, t) {
            function c(e) {
                var t = {
                    zh: "Chinese",
                    nl: "Dutch",
                    en: "English",
                    fr: "French",
                    de: "German",
                    it: "Italian",
                    ja: "Japanese",
                    pt: "Portuguese",
                    ru: "Russian",
                    es: "Spanish"
                };
                return t[e] ? t[e] : e
            }
            var d, p, h = "tracks",
            f = [];
            for (p = 0; p < s(e); p++) if (d = e.childNodes[p], d.prefix === l) {
                if (!o(d)) continue;
                switch (o(d).toLowerCase()) {
                case "content":
                    if (r(d, "duration") && (t.duration = n.seconds(r(d, "duration"))), r(d, "url")) {
                        t.sources || (t.sources = []);
                        var g = {
                            file: r(d, "url"),
                            type: r(d, "type"),
                            width: r(d, "width"),
                            label: r(d, "label")
                        },
                        m = i(d);
                        m.length && (g.mediaTypes = m),
                        t.sources.push(g)
                    }
                    s(d) > 0 && (t = u(d, t));
                    break;
                case "title":
                    t.title = a(d);
                    break;
                case "description":
                    t.description = a(d);
                    break;
                case "guid":
                    t.mediaid = a(d);
                    break;
                case "thumbnail":
                    t.image || (t.image = r(d, "url"));
                    break;
                case "player":
                    break;
                case "group":
                    u(d, t);
                    break;
                case "subtitle":
                    var w = {};
                    w.file = r(d, "url"),
                    w.kind = "captions",
                    r(d, "lang").length > 0 && (w.label = c(r(d, "lang"))),
                    f.push(w)
                }
            }
            for (t.hasOwnProperty(h) || (t[h] = []), p = 0; p < f.length; p++) t[h].push(f[p]);
            return t
        };
        return u
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = {
            kind: "captions",
            "default": !1
        },
        n = function(n) {
            if (n && n.file) return e.extend({},
            t, n)
        };
        return n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(26), n(2), n(3), n(4), n(1), n(15)],
    r = function(e, t, n, i, r, o) {
        function a(e, t, n) {
            return function() {
                var i = e.getContainer().getElementsByClassName("jw-overlays")[0];
                i && (i.appendChild(n), n.left = i.style.left, n.top = i.style.top, t.displayArea = i)
            }
        }
        function s(e) {
            function t() {
                var t = e.displayArea;
                t && e.resize(t.clientWidth, t.clientHeight)
            }
            return function() {
                t(),
                setTimeout(t, 400)
            }
        }
        var l = function(l, u) {
            function c() {
                m || (m = !0, g = o.loaderstatus.COMPLETE, f.trigger(n.COMPLETE))
            }
            function d() {
                if (!v && (u && 0 !== r.keys(u).length || c(), !m)) {
                    var i = l.getPlugins();
                    h = r.after(w, c),
                    r.each(u,
                    function(r, a) {
                        var s = e.getPluginName(a),
                        l = i[s],
                        u = l.getJS(),
                        c = l.getTarget(),
                        d = l.getStatus();
                        d !== o.loaderstatus.LOADING && d !== o.loaderstatus.NEW && (u && !t.versionCheck(c) && f.trigger(n.ERROR, {
                            message: "Incompatible player version"
                        }), h())
                    })
                }
            }
            function p(e) {
                if (!v) {
                    var i = "File not found";
                    e.url && t.log(i, e.url),
                    this.off(),
                    this.trigger(n.ERROR, {
                        message: i
                    }),
                    d()
                }
            }
            var h, f = r.extend(this, i),
            g = o.loaderstatus.NEW,
            m = !1,
            w = r.size(u),
            v = !1;
            this.setupPlugins = function(n, i) {
                var o = [],
                u = l.getPlugins(),
                c = i.get("plugins");
                r.each(c,
                function(i, l) {
                    var d = e.getPluginName(l),
                    p = u[d],
                    h = p.getFlashPath(),
                    f = p.getJS(),
                    g = p.getURL();
                    if (h) {
                        var m = r.extend({
                            name: d,
                            swf: h,
                            pluginmode: p.getPluginmode()
                        },
                        i);
                        o.push(m)
                    }
                    var w = t.tryCatch(function() {
                        if (f) {
                            var e = c[g];
                            if (!e) return void t.log("JW Plugin already loaded", d, g);
                            var i = document.createElement("div");
                            i.id = n.id + "_" + d,
                            i.className = "jw-plugin jw-reset";
                            var o = r.extend({},
                            e),
                            l = p.getNewInstance(n, o, i);
                            l.addToPlayer = a(n, l, i),
                            l.resizeHandler = s(l),
                            n.addPlugin(d, l, i)
                        }
                    });
                    w instanceof t.Error && t.log("ERROR: Failed to load " + d + ".")
                }),
                i.set("flashPlugins", o)
            },
            this.load = function() {
                if (t.exists(u) && "object" !== t.typeOf(u)) return void d();
                g = o.loaderstatus.LOADING,
                r.each(u,
                function(e, i) {
                    if (t.exists(i)) {
                        var r = l.addPlugin(i);
                        r.on(n.COMPLETE, d),
                        r.on(n.ERROR, p)
                    }
                });
                var e = l.getPlugins();
                r.each(e,
                function(e) {
                    e.load()
                }),
                d()
            },
            this.destroy = function() {
                v = !0,
                this.off()
            },
            this.getStatus = function() {
                return g
            }
        };
        return l
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(26), n(52)],
    r = function(e, t) {
        var n = function(n) {
            this.addPlugin = function(i) {
                var r = e.getPluginName(i);
                return n[r] || (n[r] = new t(i)),
                n[r]
            },
            this.getPlugins = function() {
                return n
            }
        };
        return n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
,
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return {}
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(58), n(2), n(1), n(65)],
    r = function(e, t, n, i) {
        var r = [{
            name: "youtube",
            supports: function(e) {
                return t.isYouTube(e.file, e.type)
            }
        },
        {
            name: "html5",
            supports: function(n) {
                var r = {
                    aac: "audio/mp4",
                    mp4: "video/mp4",
                    f4v: "video/mp4",
                    m4v: "video/mp4",
                    mov: "video/mp4",
                    mp3: "audio/mpeg",
                    mpeg: "audio/mpeg",
                    ogv: "video/ogg",
                    ogg: "video/ogg",
                    oga: "video/ogg",
                    vorbis: "video/ogg",
                    webm: "video/webm",
                    f4a: "video/aac",
                    m3u8: "application/vnd.apple.mpegurl",
                    m3u: "application/vnd.apple.mpegurl",
                    hls: "application/vnd.apple.mpegurl"
                },
                o = n.file,
                a = n.type,
                s = e(n);
                if (null !== s) return s;
                if (t.isRtmp(o, a)) return ! 1;
                if (!r[a]) return ! 1;
                if (i.canPlayType) {
                    var l = i.canPlayType(r[a]);
                    return !! l
                }
                return ! 1
            }
        },
        {
            name: "flash",
            supports: function(e) {
                var i = {
                    flv: "video",
                    f4v: "video",
                    mov: "video",
                    m4a: "video",
                    m4v: "video",
                    mp4: "video",
                    aac: "video",
                    f4a: "video",
                    mp3: "sound",
                    mpeg: "sound",
                    smil: "rtmp"
                },
                r = n.keys(i);
                if (!t.isFlashSupported()) return ! 1;
                var o = e.file,
                a = e.type;
                return !! t.isRtmp(o, a) || n.contains(r, a)
            }
        }];
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(13), n(38), n(134), n(1), n(137)],
    r = function(e, t, i, r, o) {
        function a(e) {
            this.config = e || {},
            this.providers = this.reorderProviders(this.config.primary)
        }
        a.loaders = {
            html5: function(e) {
                n.e(4,
                function(require) {
                    var t = n(71);
                    s(t),
                    e(t)
                })
            },
            flash: function(e) {
                n.e(5,
                function(require) {
                    var t = n(57);
                    s(t),
                    e(t)
                })
            },
            youtube: function(e) {
                n.e(6,
                function(require) {
                    var t = n(59);
                    s(t),
                    e(t)
                })
            }
        };
        var s = a.registerProvider = function(n) {
            var a = n.getName().name;
            if (!i[a]) {
                if (!r.find(t, r.matches({
                    name: a
                }))) {
                    if (!r.isFunction(n.supports)) throw {
                        message: "Tried to register a provider with an invalid object"
                    };
                    t.unshift({
                        name: a,
                        supports: n.supports
                    })
                }
                o(n.prototype, e),
                i[a] = n
            }
        };
        return r.extend(a.prototype, {
            load: function(e) {
                return Promise.all(r.map(e,
                function(e) {
                    return new Promise(function(t) {
                        var n = a.loaders[e.name];
                        n ? n(t) : t()
                    })
                }))
            },
            reorderProviders: function(e) {
                var n = r.clone(t);
                if ("flash" === e) {
                    var i = r.indexOf(n, r.findWhere(n, {
                        name: "flash"
                    })),
                    o = n.splice(i, 1)[0],
                    a = r.indexOf(n, r.findWhere(n, {
                        name: "html5"
                    }));
                    n.splice(a, 0, o)
                }
                return n
            },
            providerSupports: function(e, t) {
                return e.supports(t)
            },
            required: function(e, t) {
                var n = this,
                i = this.reorderProviders(t);
                return e = e.slice(),
                r.compact(r.map(i,
                function(t) {
                    for (var i = !1,
                    r = e.length; r--;) {
                        var o = e[r],
                        a = n.providerSupports(t, o.sources[0]);
                        a && e.splice(r, 1),
                        i = i || a
                    }
                    if (i) return t
                }))
            },
            choose: function(e) {
                e = r.isObject(e) ? e: {};
                for (var t = this.providers.length,
                n = 0; n < t; n++) {
                    var o = this.providers[n];
                    if (this.providerSupports(o, e)) {
                        var a = t - n - 1;
                        return {
                            priority: a,
                            name: o.name,
                            type: e.type,
                            providerToCheck: o,
                            provider: i[o.name]
                        }
                    }
                }
                return null
            }
        }),
        a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        return function(t) {
            return e.each(Array.prototype.slice.call(arguments, 1),
            function(e) {
                if (e) for (var n in e) n in t || (t[n] = e[n])
            }),
            t
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        return {
            hasClass: function(e, t) {
                var n = " " + t + " ";
                return 1 === e.nodeType && (" " + e.className + " ").replace(/[\t\r\n\f]/g, " ").indexOf(n) >= 0
            }
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(4)],
    r = function(e, t) {
        var n = e.extend({
            get: function(e) {
                return this.attributes = this.attributes || {},
                this.attributes[e]
            },
            set: function(e, t) {
                if (this.attributes = this.attributes || {},
                this.attributes[e] !== t) {
                    var n = this.attributes[e];
                    this.attributes[e] = t,
                    this.trigger("change:" + e, this, t, n)
                }
            },
            clone: function() {
                return e.clone(this.attributes)
            }
        },
        t);
        return n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1)],
    r = function(e) {
        var t = {};
        return t.isDvr = function(e, t) {
            return Math.abs(e) >= Math.max(t, 0)
        },
        t.streamType = function(n, i) {
            var r = e.isUndefined(i) ? 120 : i,
            o = "VOD";
            return n === 1 / 0 ? o = "LIVE": n < 0 && (o = t.isDvr(n, r) ? "DVR": "LIVE"),
            o
        },
        t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        var e = function(e, n, i) {
            n = n || this,
            i = i || [];
            var r = window.jwplayer;
            if (r && r.debug) return e.apply(n, i);
            try {
                return e.apply(n, i)
            } catch(o) {
                return new t(e.name, o)
            }
        },
        t = function(e, t) {
            this.name = e,
            this.message = t.message || t.toString(),
            this.error = t
        };
        return {
            tryCatch: e,
            Error: t
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(1)],
    r = function(e) {
        return function(t, n, i) {
            var r = "jw-breakpoint-",
            o = n,
            a = i;
            r += o >= 1280 ? "7": o >= 960 ? "6": o >= 800 ? "5": o >= 640 ? "4": o >= 540 ? "3": o >= 420 ? "2": o >= 320 ? "1": "0",
            e.replaceClass(t, /jw-breakpoint-\d+/, r),
            e.toggleClass(t, "jw-orientation-portrait", a > o)
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(23), n(5), n(1)],
    r = function(e, t, i, r) {
        var o = t.style,
        a = {
            back: !0,
            fontSize: 14,
            fontFamily: "Arial,sans-serif",
            fontOpacity: 100,
            color: "#FFF",
            backgroundColor: "#000",
            backgroundOpacity: 100,
            edgeStyle: null,
            windowColor: "#FFF",
            windowOpacity: 0,
            preprocessor: r.identity
        },
        s = function(s) {
            function l(e, n, i) {
                if (t.css("#" + e + " .jw-text-track-display", n, e), t.css("#" + e + " .jw-text-track-cue", i, e), t.css("#" + e + " .jw-video::-webkit-media-text-track-display", n, e), t.css("#" + e + " .jw-video::cue", i, e), i.backgroundColor) {
                    var r = "{background-color: " + i.backgroundColor + " !important;}";
                    t.css("#" + e + " .jw-video::-webkit-media-text-track-display-backdrop", r, e)
                }
            }
            function u(e, n, i) {
                var r = t.hexToRgba("#000000", i);
                "dropshadow" === e ? n.textShadow = "0 2px 1px " + r: "raised" === e ? n.textShadow = "0 0 5px " + r + ", 0 1px 5px " + r + ", 0 2px 5px " + r: "depressed" === e ? n.textShadow = "0 -2px 1px " + r: "uniform" === e && (n.textShadow = "-2px 0 1px " + r + ",2px 0 1px " + r + ",0 -2px 1px " + r + ",0 2px 1px " + r + ",-1px 1px 1px " + r + ",1px 1px 1px " + r + ",1px -1px 1px " + r + ",1px 1px 1px " + r)
            }
            function c(e) {
                f = e,
                this.selectCues(p, f)
            }
            function d() {
                s.get("renderCaptionsNatively") || n.e(7,
                function(require) {
                    v = n(56)
                })
            }
            var p, h, f, g, m, w, v, y = {};
            g = document.createElement("div"),
            g.className = "jw-captions jw-reset",
            this.show = function() {
                g.className = "jw-captions jw-captions-enabled jw-reset"
            },
            this.hide = function() {
                g.className = "jw-captions jw-reset"
            },
            this.populate = function(e) {
                return h = [],
                p = e,
                e ? void this.selectCues(e, f) : (h = [], void this.renderCues())
            },
            this.resize = function() {
                var e = g.clientWidth,
                t = Math.pow(e / 400, .6);
                if (t) {
                    var n = y.fontSize * t;
                    o(g, {
                        fontSize: Math.floor(2 * n) / 2 + "px"
                    })
                }
                this.renderCues(!0)
            },
            this.renderCues = function(e) {
                e = !!e,
                v && v.WebVTT.processCues(window, h, g, e)
            },
            this.selectCues = function(e, t) {
                var n, i;
                e && e.data && t && (i = this.getAlignmentPosition(e, t), i !== !1 && (n = this.getCurrentCues(e.data, i), this.updateCurrentCues(n), this.renderCues(!0)))
            },
            this.getCurrentCues = function(e, t) {
                return r.filter(e,
                function(e) {
                    return t >= e.startTime && (!e.endTime || t <= e.endTime)
                })
            },
            this.updateCurrentCues = function(e) {
                return e.length ? r.difference(e, h).length && (m.className = "jw-captions-window jw-reset jw-captions-window-active", h = e) : h = [],
                h
            },
            this.getAlignmentPosition = function(e, t) {
                var n = e.source,
                i = t.metadata;
                return n ? !(!i || !r.isNumber(i[n])) && i[n] : e.embedded && t.duration < 0 ? t.position - t.duration: t.position
            },
            this.clear = function() {
                e.empty(g)
            },
            this.setContainerHeight = function(e) {
                o(g, {
                    height: e
                })
            },
            this.setup = function(e, n) {
                m = document.createElement("div"),
                w = document.createElement("span"),
                m.className = "jw-captions-window jw-reset",
                w.className = "jw-captions-text jw-reset",
                y = r.extend({},
                a, n);
                var i = y.fontOpacity,
                c = y.windowOpacity,
                d = y.edgeStyle,
                p = y.backgroundColor,
                h = {},
                f = {
                    color: t.hexToRgba(y.color, i),
                    fontFamily: y.fontFamily,
                    fontStyle: y.fontStyle,
                    fontWeight: y.fontWeight,
                    textDecoration: y.textDecoration
                };
                c && (h.backgroundColor = t.hexToRgba(y.windowColor, c)),
                u(d, f, i),
                y.back ? f.backgroundColor = t.hexToRgba(p, y.backgroundOpacity) : null === d && u("uniform", f),
                o(m, h),
                o(w, f),
                l(e, h, f),
                m.appendChild(w),
                g.appendChild(m),
                this.populate(s.get("captionsTrack")),
                s.set("captions", y)
            },
            this.element = function() {
                return g
            },
            s.on("change:playlistItem",
            function() {
                f = null,
                h = []
            },
            this),
            s.on("change:captionsTrack",
            function(e, t) {
                this.populate(t)
            },
            this),
            s.mediaController.on("seek",
            function() {
                h = []
            },
            this),
            s.mediaController.on("time seek", c, this),
            s.mediaController.on("subtitlesTrackData",
            function() {
                this.selectCues(p, f)
            },
            this),
            s.on("change:state",
            function(e, t) {
                switch (t) {
                case i.IDLE:
                case i.ERROR:
                case i.COMPLETE:
                    this.hide();
                    break;
                default:
                    this.show()
                }
            },
            this),
            s.on("itemReady", d, this)
        };
        return s
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(7), n(3), n(4), n(1)],
    r = function(e, t, n, i) {
        var r = function(r, o, a) {
            function s(e) {
                if (!r.get("flashBlocked")) return c ? void c(e) : void f.trigger(e.type === t.touchEvents.CLICK ? "click": "tap")
            }
            function l() {
                return d ? void d() : void f.trigger("doubleClick")
            }
            var u, c, d, p = {
                enableDoubleTap: !0,
                useMove: !0
            };
            i.extend(this, n),
            u = o,
            this.element = function() {
                return u
            };
            var h = new e(u, i.extend(p, a));
            h.on("click tap", s),
            h.on("doubleClick doubleTap", l),
            h.on("move",
            function() {
                f.trigger("move")
            }),
            h.on("over",
            function() {
                f.trigger("over")
            }),
            h.on("out",
            function() {
                f.trigger("out")
            }),
            this.clickHandler = s;
            var f = this;
            this.setAlternateClickHandlers = function(e, t) {
                c = e,
                d = t || null
            },
            this.revertAlternateClickHandlers = function() {
                c = null,
                d = null
            }
        };
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2), n(30)],
    r = function(e, t, n) {
        function i(e, t) {
            this.time = e,
            this.text = t,
            this.el = document.createElement("div"),
            this.el.className = "jw-cue jw-reset"
        }
        e.extend(i.prototype, {
            align: function(e) {
                if ("%" === this.time.toString().slice( - 1)) this.pct = this.time;
                else {
                    var t = this.time / e * 100;
                    this.pct = t + "%"
                }
                this.el.style.left = this.pct
            }
        });
        var r = {
            loadChapters: function(e) {
                t.ajax(e, this.chaptersLoaded.bind(this), this.chaptersFailed, {
                    plainText: !0
                })
            },
            chaptersLoaded: function(t) {
                var i = n(t.responseText);
                e.isArray(i) && (e.each(i, this.addCue, this), this.drawCues())
            },
            chaptersFailed: function() {},
            addCue: function(e) {
                this.cues.push(new i(e.begin, e.text))
            },
            drawCues: function() {
                var t = this._model.mediaModel.get("duration");
                if (!t || t <= 0) return void this._model.mediaModel.once("change:duration", this.drawCues, this);
                var n = this;
                e.each(this.cues,
                function(e) {
                    e.align(t),
                    e.el.addEventListener("mouseover",
                    function() {
                        n.activeCue = e
                    }),
                    e.el.addEventListener("mouseout",
                    function() {
                        n.activeCue = null
                    }),
                    n.elementRail.appendChild(e.el)
                })
            },
            resetChapters: function() {
                e.each(this.cues,
                function(e) {
                    e.el.parentNode && e.el.parentNode.removeChild(e.el)
                },
                this),
                this.cues = []
            }
        };
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(36), n(2), n(1), n(7), n(90)],
    r = function(e, t, n, i, r) {
        var o = e.extend({
            setup: function(e, o, a) {
                a = a || {},
                this.iconUI || (this.iconUI = new i(this.el, {
                    useHover: !0,
                    directSelect: !0
                }), this.toggleValueListener = this.toggleValue.bind(this), this.toggleOpenStateListener = this.toggleOpenState.bind(this), this.openTooltipListener = this.openTooltip.bind(this), this.closeTooltipListener = this.closeTooltip.bind(this), this.selectListener = this.select.bind(this)),
                this.reset(),
                e = n.isArray(e) ? e: [],
                t.toggleClass(this.el, "jw-hidden", e.length < 2);
                var s = e.length > 2 || 2 === e.length && a && a.toggle === !1,
                l = !s && 2 === e.length;
                if (t.toggleClass(this.el, "jw-toggle", l || !!a.isToggle), t.toggleClass(this.el, "jw-button-color", !l), this.isActive = s || l, s) {
                    t.removeClass(this.el, "jw-off"),
                    this.iconUI.on("tap", this.toggleOpenStateListener).on("over", this.openTooltipListener).on("out", this.closeTooltipListener);
                    var u = r(e),
                    c = t.createElement(u);
                    this.addContent(c),
                    this.contentUI = new i(this.content).on("click tap", this.selectListener)
                } else l && this.iconUI.on("click tap", this.toggleValueListener);
                this.selectItem(o)
            },
            toggleValue: function() {
                this.trigger("toggleValue")
            },
            select: function(e) {
                if (e.target.parentElement === this.content) {
                    var i = t.classList(e.target),
                    r = n.find(i,
                    function(e) {
                        return 0 === e.indexOf("jw-item")
                    });
                    r && (this.trigger("select", parseInt(r.split("-")[2])), this.closeTooltipListener())
                }
            },
            selectItem: function(e) {
                if (this.content) for (var n = 0; n < this.content.children.length; n++) t.toggleClass(this.content.children[n], "jw-active-option", e === n);
                t.toggleClass(this.el, "jw-off", 0 === e)
            },
            reset: function() {
                t.addClass(this.el, "jw-off"),
                this.iconUI.off(),
                this.contentUI && this.contentUI.off().destroy(),
                this.removeContent()
            }
        });
        return o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(32), n(7), n(1), n(2), n(91)],
    r = function(e, t, n, i, r) {
        var o = function(e, t, n, i) {
            this._model = e,
            this._api = t,
            this._nextButton = n,
            this._playerElement = i,
            this.nextUpText = e.get("localization").nextUp,
            this.state = "tooltip"
        };
        return n.extend(o.prototype, {
            setup: function() {
                this.container = document.createElement("div"),
                this.container.className = "jw-nextup-container jw-reset";
                var e = i.createElement(r());
                this.addContent(e),
                this.closeButton = this.content.querySelector(".jw-nextup-close"),
                this.tooltip = this.content.querySelector(".jw-nextup-tooltip"),
                this.showNextUp = !1,
                this.streamType = void 0,
                this._model.on("change:mediaModel", this.onMediaModel, this),
                this._model.on("change:streamType", this.onStreamType, this),
                this._model.on("change:nextUp", this.onNextUp, this),
                this._model.on("change:duration", this.onDuration, this),
                this._model.on("change:position", this.onElapsed, this),
                this.onMediaModel(this._model, this._model.get("mediaModel")),
                new t(this.closeButton, {
                    directSelect: !0
                }).on("click tap", this.hide, this),
                new t(this.tooltip).on("click tap", this.click, this),
                new t(this._nextButton.element(), {
                    useHover: !0,
                    directSelect: !0
                }).on("click tap", this.click, this).on("over", this.show, this).on("out", this.hoverOut, this)
            },
            loadThumbnail: function(e) {
                return this.nextUpImage = new Image,
                this.nextUpImage.onload = function() {
                    this.nextUpImage.onload = null
                }.bind(this),
                this.nextUpImage.src = e,
                {
                    backgroundImage: 'url("' + e + '")'
                }
            },
            click: function() {
                this.state = "tooltip",
                this._api.next(),
                this.hide()
            },
            show: function() {
                "opened" === this.state || this.hideToolTip || (e.addClass(this.container, "jw-nextup-container-visible"), e.addClass(this._playerElement, "jw-flag-nextup"))
            },
            hide: function() {
                e.removeClass(this.container, "jw-nextup-container-visible"),
                e.removeClass(this.container, "jw-nextup-sticky"),
                e.removeClass(this._playerElement, "jw-flag-nextup"),
                "opened" === this.state && (this.state = "closed")
            },
            hoverOut: function() {
                "opened" !== this.state && this.hide()
            },
            showTilEnd: function() {
                "opened" !== this.state && "closed" !== this.state && (e.addClass(this.container, "jw-nextup-sticky"), this.show(), this.state = "opened")
            },
            setNextUpItem: function(t) {
                var n = this;
                setTimeout(function() {
                    if (n.hideToolTip = !(t.title || t.image), !n.hideToolTip) {
                        if (n.thumbnail = n.content.querySelector(".jw-nextup-thumbnail"), e.toggleClass(n.thumbnail, "jw-nextup-thumbnail-visible", !!t.image), t.image) {
                            var r = n.loadThumbnail(t.image);
                            i.style(n.thumbnail, r)
                        }
                        n.header = n.content.querySelector(".jw-nextup-header"),
                        n.header.innerText = n.nextUpText,
                        n.title = n.content.querySelector(".jw-nextup-title");
                        var o = t.title;
                        n.title.innerText = o ? i.createElement(o).textContent: ""
                    }
                },
                500)
            },
            onNextUp: function(e, t) {
                return t ? (this.showNextUp = !0, this._nextButton.toggle(!0), void this.setNextUpItem(t)) : (this._nextButton.toggle(!1), void(this.showNextUp = !1))
            },
            onDuration: function(e, t) {
                if (t) {
                    var n = i.seconds(e.get("nextupoffset") || -10);
                    n < 0 && (n += t),
                    this.offset = n
                }
            },
            onMediaModel: function(e, t) {
                t.on("change:state",
                function(e, t) {
                    "complete" === t && (this.state = "tooltip", this.hide())
                },
                this)
            },
            onElapsed: function(e, t) {
                "VOD" === this.streamType && this.showNextUp && t >= this.offset ? this.showTilEnd() : "opened" !== this.state && "closed" !== this.state || (this.state = "tooltip", this.hide())
            },
            onStreamType: function(e, t) {
                this.streamType = t
            },
            element: function() {
                return this.container
            },
            addContent: function(e) {
                this.content && this.removeContent(),
                this.content = e,
                this.container.appendChild(e)
            },
            removeContent: function() {
                this.content && (this.container.removeChild(this.content), this.content = null)
            }
        }),
        o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2), n(30)],
    r = function(e, t, n) {
        function i(e) {
            this.begin = e.begin,
            this.end = e.end,
            this.img = e.text
        }
        var r = {
            loadThumbnails: function(e) {
                e && (this.vttPath = e.split("?")[0].split("/").slice(0, -1).join("/"), this.individualImage = null, t.ajax(e, this.thumbnailsLoaded.bind(this), this.thumbnailsFailed.bind(this), {
                    plainText: !0
                }))
            },
            thumbnailsLoaded: function(t) {
                var r = n(t.responseText);
                e.isArray(r) && (e.each(r,
                function(e) {
                    this.thumbnails.push(new i(e))
                },
                this), this.drawCues())
            },
            thumbnailsFailed: function() {},
            chooseThumbnail: function(t) {
                var n = e.sortedIndex(this.thumbnails, {
                    end: t
                },
                e.property("end"));
                n >= this.thumbnails.length && (n = this.thumbnails.length - 1);
                var i = this.thumbnails[n].img;
                return i.indexOf("://") < 0 && (i = this.vttPath ? this.vttPath + "/" + i: i),
                i
            },
            loadThumbnail: function(t) {
                var n = this.chooseThumbnail(t),
                i = {
                    display: "block",
                    margin: "0 auto",
                    backgroundPosition: "0 0"
                },
                r = n.indexOf("#xywh");
                if (r > 0) try {
                    var o = /(.+)\#xywh=(\d+),(\d+),(\d+),(\d+)/.exec(n);
                    n = o[1],
                    i.backgroundPosition = o[2] * -1 + "px " + o[3] * -1 + "px",
                    i.width = o[4],
                    i.height = o[5]
                } catch(a) {
                    return
                } else this.individualImage || (this.individualImage = new Image, this.individualImage.onload = e.bind(function() {
                    this.individualImage.onload = null,
                    this.timeTip.image({
                        width: this.individualImage.width,
                        height: this.individualImage.height
                    })
                },
                this), this.individualImage.src = n);
                return i.backgroundImage = 'url("' + n + '")',
                i
            },
            showThumbnail: function(e) {
                this.thumbnails.length < 1 || this.timeTip.image(this.loadThumbnail(e))
            },
            resetThumbnails: function() {
                this.timeTip.image({
                    backgroundImage: "",
                    width: 0,
                    height: 0
                }),
                this.thumbnails = []
            }
        };
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2), n(22), n(35), n(36), n(145), n(148)],
    r = function(e, t, n, i, r, o, a) {
        var s = r.extend({
            setup: function() {
                this.text = document.createElement("span"),
                this.text.className = "jw-text jw-reset",
                this.img = document.createElement("div"),
                this.img.className = "jw-reset";
                var e = document.createElement("div");
                e.className = "jw-time-tip jw-background-color jw-reset",
                e.appendChild(this.img),
                e.appendChild(this.text),
                t.removeClass(this.el, "jw-hidden"),
                this.addContent(e)
            },
            image: function(e) {
                t.style(this.img, e)
            },
            update: function(e) {
                this.text.innerHTML = e
            }
        }),
        l = i.extend({
            constructor: function(t, n) {
                this._model = t,
                this._api = n,
                this.timeTip = new s("jw-tooltip-time"),
                this.timeTip.setup(),
                this.cues = [],
                this.seekThrottled = e.throttle(this.performSeek, 400),
                this._model.on("change:playlistItem", this.onPlaylistItem, this).on("change:position", this.onPosition, this).on("change:duration", this.onDuration, this).on("change:buffer", this.onBuffer, this),
                i.call(this, "jw-slider-time", "horizontal")
            },
            setup: function() {
                i.prototype.setup.apply(this, arguments),
                this._model.get("playlistItem") && this.onPlaylistItem(this._model, this._model.get("playlistItem")),
                this.elementRail.appendChild(this.timeTip.element()),
                this.el.addEventListener("mousemove", this.showTimeTooltip.bind(this), !1),
                this.el.addEventListener("mouseout", this.hideTimeTooltip.bind(this), !1)
            },
            limit: function(t) {
                if (this.activeCue && e.isNumber(this.activeCue.pct)) return this.activeCue.pct;
                var i = this._model.get("duration"),
                r = this._model.get("streamType");
                if ("DVR" === r) {
                    var o = (1 - t / 100) * i,
                    a = this._model.get("position"),
                    s = Math.min(o, Math.max(n.dvrSeekLimit, a)),
                    l = 100 * s / i;
                    return 100 - l
                }
                return t
            },
            update: function(e) {
                this.seekTo = e,
                this.seekThrottled(),
                i.prototype.update.apply(this, arguments)
            },
            dragStart: function() {
                this._model.set("scrubbing", !0),
                i.prototype.dragStart.apply(this, arguments)
            },
            dragEnd: function() {
                i.prototype.dragEnd.apply(this, arguments),
                this._model.set("scrubbing", !1)
            },
            onSeeked: function() {
                this._model.get("scrubbing") && this.performSeek()
            },
            onBuffer: function(e, t) {
                this.updateBuffer(t)
            },
            onPosition: function(e, t) {
                this.updateTime(t, e.get("duration"))
            },
            onDuration: function(e, t) {
                this.updateTime(e.get("position"), t)
            },
            updateTime: function(e, t) {
                var n = 0;
                if (t) {
                    var i = this._model.get("streamType");
                    "DVR" === i ? n = (t - e) / t * 100 : "VOD" === i && (n = e / t * 100)
                }
                this.render(n)
            },
            onPlaylistItem: function(t, n) {
                this.reset(),
                t.mediaModel.on("seeked", this.onSeeked, this);
                var i = n.tracks;
                e.each(i,
                function(e) {
                    e && e.kind && "thumbnails" === e.kind.toLowerCase() ? this.loadThumbnails(e.file) : e && e.kind && "chapters" === e.kind.toLowerCase() && this.loadChapters(e.file)
                },
                this)
            },
            performSeek: function() {
                var e, t = this.seekTo,
                n = this._model.get("duration"),
                i = this._model.get("streamType");
                0 === n ? this._api.play() : "DVR" === i ? (e = (100 - t) / 100 * n, this._api.seek(e)) : (e = t / 100 * n, this._api.seek(Math.min(e, n - .25)))
            },
            showTimeTooltip: function(e) {
                var i = this._model.get("duration");
                if (0 !== i) {
                    var r = t.bounds(this.elementRail),
                    o = e.pageX ? e.pageX - r.left: e.x;
                    o = t.between(o, 0, r.width);
                    var a = o / r.width,
                    s = i * a;
                    i < 0 && (s = i - s);
                    var l;
                    if (this.activeCue) l = this.activeCue.text;
                    else {
                        var u = !0;
                        l = t.timeFormat(s, u),
                        i < 0 && s > n.dvrSeekLimit && (l = "Live")
                    }
                    this.timeTip.update(l),
                    this.showThumbnail(s),
                    t.addClass(this.timeTip.el, "jw-open"),
                    this.timeTip.el.style.left = 100 * a + "%"
                }
            },
            hideTimeTooltip: function() {
                t.removeClass(this.timeTip.el, "jw-open")
            },
            reset: function() {
                this.resetChapters(),
                this.resetThumbnails()
            }
        });
        return e.extend(l.prototype, o, a),
        l
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(36), n(35), n(7), n(2)],
    r = function(e, t, n, i) {
        var r = e.extend({
            constructor: function(r, o, a) {
                this._model = r,
                e.call(this, o, a, !0),
                this.volumeSlider = new t("jw-slider-volume jw-volume-tip", "vertical"),
                this.addContent(this.volumeSlider.element()),
                this.volumeSlider.on("update",
                function(e) {
                    this.trigger("update", e)
                },
                this),
                i.removeClass(this.el, "jw-hidden"),
                new n(this.el, {
                    useHover: !0,
                    directSelect: !0
                }).on("click", this.toggleValue, this).on("tap", this.toggleOpenState, this).on("over", this.openTooltip, this).on("out", this.closeTooltip, this),
                this._model.on("change:volume", this.onVolume, this)
            },
            toggleValue: function() {
                this.trigger("toggleValue")
            }
        });
        return r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(1), n(4), n(22), n(7), n(35), n(149), n(146), n(150), n(66)],
    r = function(e, t, n, i, r, o, a, s, l, u) {
        function c(e, t) {
            var n = document.createElement("span");
            return n.className = "jw-text jw-reset " + e,
            t && n.setAttribute("role", t),
            n
        }
        function d(e, t) {
            var n = new s(e, t);
            return n
        }
        function p(e, n) {
            var i = document.createElement("div");
            return i.className = "jw-group jw-controlbar-" + e + "-group jw-reset",
            t.each(n,
            function(e) {
                e.element && (e = e.element()),
                i.appendChild(e)
            }),
            i
        }
        function h(t, n) {
            this._api = t,
            this._model = n,
            this._isMobile = e.isMobile(),
            this._localization = this._model.get("localization"),
            this.setup()
        }
        return t.extend(h.prototype, n, {
            setup: function() {
                this.build(),
                this.initialize()
            },
            build: function() {
                var n, i, r, s = new a(this._model, this._api),
                h = this._localization.play,
                f = this._localization.next,
                g = this._localization.volume,
                m = this._localization.rewind;
                this._isMobile || (n = new o("jw-slider-volume", "horizontal"), i = new l(this._model, "jw-icon-volume", g)),
                this._model.get("sdkplatform") || e.isIOS(8) || e.isIOS(9) || (r = u("jw-icon-volume", this._api.setMute, g)),
                this.elements = {
                    alt: c("jw-text-alt", "status"),
                    play: u("jw-icon-playback", this._api.play.bind(this, {
                        reason: "interaction"
                    }), h),
                    rewind: u("jw-icon-rewind", this.rewind.bind(this), m),
                    next: u("jw-icon-next", null, f),
                    elapsed: c("jw-text-elapsed", "timer"),
                    time: s,
                    duration: c("jw-text-duration", "timer"),
                    hd: d("jw-icon-hd", this._localization.hd),
                    cc: d("jw-icon-cc", this._localization.cc),
                    audiotracks: d("jw-icon-audio-tracks", this._localization.audioTracks),
                    mute: r,
                    volume: n,
                    volumetooltip: i,
                    cast: u("jw-icon-cast jw-off", this._api.castToggle, this._localization.cast),
                    fullscreen: u("jw-icon-fullscreen", this._api.setFullscreen, this._localization.fullscreen)
                },
                this.layout = {
                    left: [this.elements.play, this.elements.rewind, this.elements.elapsed],
                    center: [this.elements.time, this.elements.alt],
                    right: [this.elements.duration, this.elements.next, this.elements.hd, this.elements.cc, this.elements.audiotracks, this.elements.mute, this.elements.cast, this.elements.volume, this.elements.volumetooltip, this.elements.fullscreen]
                },
                this.menus = t.compact([this.elements.hd, this.elements.cc, this.elements.audiotracks, this.elements.volumetooltip]),
                this.layout.left = t.compact(this.layout.left),
                this.layout.center = t.compact(this.layout.center),
                this.layout.right = t.compact(this.layout.right),
                this.el = document.createElement("div"),
                this.el.className = "jw-controlbar jw-background-color jw-reset",
                this.elements.left = p("left", this.layout.left),
                this.elements.center = p("center", this.layout.center),
                this.elements.right = p("right", this.layout.right),
                this.el.appendChild(this.elements.left),
                this.el.appendChild(this.elements.center),
                this.el.appendChild(this.elements.right)
            },
            initialize: function() {
                this.elements.play.show(),
                this.elements.fullscreen.show(),
                this.elements.mute && this.elements.mute.show(),
                this.onVolume(this._model, this._model.get("volume")),
                this.onPlaylistItem(),
                this.onMediaModel(this._model, this._model.get("mediaModel")),
                this.onCastAvailable(this._model, this._model.get("castAvailable")),
                this.onCastActive(this._model, this._model.get("castActive")),
                this.onCaptionsList(this._model, this._model.get("captionsList")),
                this._model.on("change:volume", this.onVolume, this),
                this._model.on("change:mute", this.onMute, this),
                this._model.on("change:playlistItem", this.onPlaylistItem, this),
                this._model.on("change:mediaModel", this.onMediaModel, this),
                this._model.on("change:castAvailable", this.onCastAvailable, this),
                this._model.on("change:castActive", this.onCastActive, this),
                this._model.on("change:duration", this.onDuration, this),
                this._model.on("change:position", this.onElapsed, this),
                this._model.on("change:fullscreen", this.onFullscreen, this),
                this._model.on("change:captionsList", this.onCaptionsList, this),
                this._model.on("change:captionsIndex", this.onCaptionsIndex, this),
                this._model.on("change:streamType", this.onStreamTypeChange, this),
                this.elements.volume && this.elements.volume.on("update",
                function(e) {
                    var t = e.percentage;
                    this._api.setVolume(t)
                },
                this),
                this.elements.volumetooltip && (this.elements.volumetooltip.on("update",
                function(e) {
                    var t = e.percentage;
                    this._api.setVolume(t)
                },
                this), this.elements.volumetooltip.on("toggleValue",
                function() {
                    this._api.setMute()
                },
                this)),
                this.elements.hd.on("select",
                function(e) {
                    this._model.getVideo().setCurrentQuality(e)
                },
                this),
                this.elements.hd.on("toggleValue",
                function() {
                    this._model.getVideo().setCurrentQuality(0 === this._model.getVideo().getCurrentQuality() ? 1 : 0)
                },
                this),
                this.elements.cc.on("select",
                function(e) {
                    this._api.setCurrentCaptions(e)
                },
                this),
                this.elements.cc.on("toggleValue",
                function() {
                    var e = this._model.get("captionsIndex");
                    this._api.setCurrentCaptions(e ? 0 : 1)
                },
                this),
                this.elements.audiotracks.on("select",
                function(e) {
                    this._model.getVideo().setCurrentAudioTrack(e)
                },
                this),
                new r(this.elements.duration).on("click tap",
                function() {
                    if ("DVR" === this._model.get("streamType")) {
                        var e = this._model.get("position");
                        this._api.seek(Math.max(i.dvrSeekLimit, e))
                    }
                },
                this),
                new r(this.el).on("click tap drag",
                function() {
                    this.trigger("userAction")
                },
                this),
                t.each(this.menus,
                function(e) {
                    e.on("open-tooltip", this.closeMenus, this)
                },
                this)
            },
            onCaptionsList: function(e, t) {
                var n = e.get("captionsIndex");
                this.elements.cc.setup(t, n, {
                    isToggle: !0
                })
            },
            onCaptionsIndex: function(e, t) {
                this.elements.cc.selectItem(t)
            },
            onPlaylistItem: function() {
                this.elements.time.updateBuffer(0),
                this.elements.time.render(0),
                this.elements.duration.innerHTML = "00:00",
                this.elements.elapsed.innerHTML = "00:00",
                this.elements.audiotracks.setup()
            },
            onMediaModel: function(e, n) {
                n.on("change:levels",
                function(e, t) {
                    this.elements.hd.setup(t, e.get("currentLevel"))
                },
                this),
                n.on("change:currentLevel",
                function(e, t) {
                    this.elements.hd.selectItem(t)
                },
                this),
                n.on("change:audioTracks",
                function(e, n) {
                    var i = t.map(n,
                    function(e) {
                        return {
                            label: e.name
                        }
                    });
                    this.elements.audiotracks.setup(i, e.get("currentAudioTrack"), {
                        toggle: !1
                    })
                },
                this),
                n.on("change:currentAudioTrack",
                function(e, t) {
                    this.elements.audiotracks.selectItem(t)
                },
                this)
            },
            onVolume: function(e, t) {
                this.renderVolume(e.get("mute"), t)
            },
            onMute: function(e, t) {
                this.renderVolume(t, e.get("volume"))
            },
            renderVolume: function(t, n) {
                this.elements.mute && e.toggleClass(this.elements.mute.element(), "jw-off", t),
                this.elements.volume && this.elements.volume.render(t ? 0 : n),
                this.elements.volumetooltip && (this.elements.volumetooltip.volumeSlider.render(t ? 0 : n), e.toggleClass(this.elements.volumetooltip.element(), "jw-off", t))
            },
            onCastAvailable: function(e, t) {
                this.elements.cast.toggle(t)
            },
            onCastActive: function(t, n) {
                e.toggleClass(this.elements.cast.element(), "jw-off", !n)
            },
            onElapsed: function(t, n) {
                var i, r = t.get("duration");
                i = "DVR" === t.get("streamType") ? "-" + e.timeFormat( - r) : e.timeFormat(n),
                this.elements.elapsed.innerHTML = i
            },
            onDuration: function(t, n) {
                var i;
                i = "DVR" === t.get("streamType") ? "Live": e.timeFormat(n),
                this.elements.duration.innerHTML = i
            },
            onFullscreen: function(t, n) {
                e.toggleClass(this.elements.fullscreen.element(), "jw-off", n)
            },
            element: function() {
                return this.el
            },
            setAltText: function(e) {
                this.elements.alt.innerHTML = e
            },
            addCues: function(e) {
                this.elements.time && (t.each(e,
                function(e) {
                    this.elements.time.addCue(e)
                },
                this), this.elements.time.drawCues())
            },
            closeMenus: function(e) {
                t.each(this.menus,
                function(t) {
                    e && e.target === t.el || t.closeTooltip(e)
                })
            },
            hideComponents: function() {
                this.closeMenus()
            },
            rewind: function() {
                var e = this._model.get("position"),
                t = this._model.get("duration"),
                n = e - 10,
                i = 0;
                "DVR" === this._model.get("streamType") && (i = t),
                this._api.seek(Math.max(n, i))
            },
            onStreamTypeChange: function(e) {
                var t = e.get("streamType");
                this.elements.rewind.toggle("LIVE" !== t),
                "DVR" === t && (this.elements.duration.innerHTML = "Live")
            }
        }),
        h
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(4), n(7), n(86), n(1)],
    r = function(e, t, n, i, r) {
        var o = function(o) {
            r.extend(this, t),
            this.model = o,
            this.el = e.createElement(i({
                ariaLabel: this.model.get("localization").playback
            }));
            var a = this;
            this.iconUI = new n(this.el).on("click tap",
            function(e) {
                a.trigger(e.type)
            }),
            this.model.on("change:state",
            function(e, t) {
                var n = a.el.getElementsByClassName("jw-icon-display");
                if (n.length) {
                    var i = a.model.get("localization"),
                    r = i.playback;
                    switch (t) {
                    case "buffering":
                        r = i.buffer;
                        break;
                    case "playing":
                        r = i.pause;
                        break;
                    case "complete":
                        r = i.replay;
                        break;
                    case "error":
                        r = ""
                    }
                    "" === r ? n[0].removeAttribute("aria-label") : n[0].setAttribute("aria-label", r)
                }
            })
        };
        return r.extend(o.prototype, {
            element: function() {
                return this.el
            }
        }),
        o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(87), n(2), n(1), n(7)],
    r = function(e, t, n, i) {
        var r = function(e) {
            this.model = e,
            this.setup(),
            this.model.on("change:dock", this.render, this)
        };
        return n.extend(r.prototype, {
            setup: function() {
                var n = this.model.get("dock"),
                r = this.click.bind(this),
                o = e(n);
                this.el = t.createElement(o),
                new i(this.el).on("click tap", r)
            },
            getDockButton: function(e) {
                return t.hasClass(e.target, "jw-dock-button") ? e.target: t.hasClass(e.target, "jw-dock-text") ? e.target.parentElement.parentElement: e.target.parentElement
            },
            click: function(e) {
                var t = this.getDockButton(e),
                i = t.getAttribute("button"),
                r = this.model.get("dock"),
                o = n.findWhere(r, {
                    id: i
                });
                o && o.callback && o.callback(e)
            },
            render: function() {
                var n = this.model.get("dock"),
                i = e(n),
                r = t.createElement(i);
                this.el.innerHTML = r.innerHTML
            },
            element: function() {
                return this.el
            }
        }),
        r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(88)],
    r = function(e) {
        function t(t, n, i, r) {
            return e({
                id: t,
                skin: n,
                title: i,
                body: r
            })
        }
        return t
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(7), n(2), n(3), n(1), n(4), n(89)],
    r = function(e, t, n, i, r, o) {
        var a = t.style,
        s = {
            linktarget: "_blank",
            margin: 8,
            hide: !1,
            position: "top-right"
        },
        l = function(l) {
            var u, c, d = new Image,
            p = i.extend({},
            l.get("logo"));
            return i.extend(this, r),
            this.setup = function(r) {
                if (c = i.extend({},
                s, p), c.hide = "true" === c.hide.toString(), u = t.createElement(o()), c.file) {
                    c.hide && t.addClass(u, "jw-hide"),
                    t.addClass(u, "jw-logo-" + (c.position || s.position)),
                    "top-right" === c.position && (l.on("change:dock", this.topRight, this), l.on("change:controls", this.topRight, this), this.topRight(l)),
                    l.set("logo", c),
                    d.onload = function() {
                        var e = {
                            backgroundImage: 'url("' + this.src + '")',
                            width: this.width,
                            height: this.height
                        };
                        if (c.margin !== s.margin) {
                            var t = /(\w+)-(\w+)/.exec(c.position);
                            3 === t.length && (e["margin-" + t[1]] = c.margin, e["margin-" + t[2]] = c.margin)
                        }
                        a(u, e),
                        l.set("logoWidth", e.width)
                    },
                    d.src = c.file;
                    var h = new e(u);
                    h.on("click tap",
                    function(e) {
                        t.exists(e) && e.stopPropagation && e.stopPropagation(),
                        this.trigger(n.JWPLAYER_LOGO_CLICK, {
                            link: c.link,
                            linktarget: c.linktarget
                        })
                    },
                    this),
                    r.appendChild(u)
                }
            },
            this.topRight = function(e) {
                var t = e.get("controls"),
                n = e.get("dock"),
                i = t && (n && n.length || e.get("sharing") || e.get("related"));
                a(u, {
                    top: i ? "3.5em": 0
                })
            },
            this.element = function() {
                return u
            },
            this.position = function() {
                return c.position
            },
            this.destroy = function() {
                d.onload = null
            },
            this
        };
        return l
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2)],
    r = function(e, t) {
        function n(e, t) {
            t.off("change:mediaType", null, this),
            t.on("change:mediaType",
            function(t, n) {
                "audio" === n && this.setImage(e.get("playlistItem").image)
            },
            this)
        }
        function i(e, n) {
            var i = e.get("autostart") && !t.isMobile() || e.get("item") > 0;
            return i ? (this.setImage(null), e.off("change:state", null, this), void e.on("change:state",
            function(e, t) {
                "complete" !== t && "idle" !== t && "error" !== t || (this.setImage(n.image), this.resize(null, null, e.get("stretching")))
            },
            this)) : void this.setImage(n.image)
        }
        var r = function(e) {
            this.model = e,
            e.on("change:playlistItem", i, this),
            e.on("change:mediaModel", n, this)
        };
        return e.extend(r.prototype, {
            setup: function(e) {
                this.el = e;
                var t = this.model.get("playlistItem");
                t && this.setImage(t.image)
            },
            setImage: function(n) {
                var i = this.image;
                i && (i.onload = null, this.image = null),
                this.model.off("change:state", null, this);
                var r = "";
                e.isString(n) && (r = 'url("' + n + '")', i = this.image = new Image, i.src = n),
                t.style(this.el, {
                    backgroundImage: r
                })
            },
            resize: function(e, n, i) {
                if ("uniform" === i) {
                    if (e && (this.playerAspectRatio = e / n), !this.playerAspectRatio) return;
                    var r = this.image,
                    o = null;
                    if (r) {
                        if (0 === r.width) {
                            var a = this;
                            return void(r.onload = function() {
                                a.resize(e, n, i)
                            })
                        }
                        var s = r.width / r.height;
                        Math.abs(this.playerAspectRatio - s) < .09 && (o = "cover")
                    }
                    t.style(this.el, {
                        backgroundSize: o
                    })
                }
            },
            element: function() {
                return this.el
            }
        }),
        r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(93), n(1), n(7), n(24)],
    r = function(e, t, n, i, r) {
        var o = function() {};
        return n.extend(o.prototype, {
            buildArray: function() {
                var t = r.split("+"),
                n = t[0],
                i = {
                    items: [{
                        title: "Powered by JW Player " + n,
                        featured: !0,
                        showLogo: !0,
                        link: "https://jwplayer.com/learn-more"
                    }]
                },
                o = n.indexOf("-") > 0,
                a = t[1];
                if (o && a) {
                    var s = a.split(".");
                    i.items.push({
                        title: "build: (" + s[0] + "." + s[1] + ")",
                        link: "#"
                    })
                }
                var l = this.model.get("provider");
                if (l && l.name.indexOf("flash") >= 0) {
                    var u = "Flash Version " + e.flashVersion();
                    i.items.push({
                        title: u,
                        link: "http://www.adobe.com/software/flash/about/"
                    })
                }
                return i
            },
            buildMenu: function() {
                var n = this.buildArray();
                return e.createElement(t(n))
            },
            updateHtml: function() {
                this.el.innerHTML = this.buildMenu().innerHTML
            },
            rightClick: function(e) {
                return this.lazySetup(),
                !this.mouseOverContext && (this.hideMenu(), this.showMenu(e), !1)
            },
            getOffset: function(e) {
                for (var t = e.target,
                n = e.offsetX || e.layerX,
                i = e.offsetY || e.layerY; t !== this.playerElement;) n += t.offsetLeft,
                i += t.offsetTop,
                t = t.parentNode;
                return {
                    x: n,
                    y: i
                }
            },
            showMenu: function(t) {
                var n = this.getOffset(t);
                return this.el.style.left = n.x + "px",
                this.el.style.top = n.y + "px",
                e.addClass(this.playerElement, "jw-flag-rightclick-open"),
                e.addClass(this.el, "jw-open"),
                clearTimeout(this._menuTimeout),
                this._menuTimeout = setTimeout(this.hideMenu.bind(this), 3e3),
                !1
            },
            hideMenu: function() {
                return this.elementUI.off("out", this.hideMenu, this),
                this.mouseOverContext ? void this.elementUI.on("out", this.hideMenu, this) : (e.removeClass(this.playerElement, "jw-flag-rightclick-open"), void e.removeClass(this.el, "jw-open"))
            },
            lazySetup: function() {
                this.el || (this.el = this.buildMenu(), this.layer.appendChild(this.el), this.hideMenuHandler = this.hideMenu.bind(this), this.addOffListener(this.playerElement), this.addOffListener(document), this.model.on("change:provider", this.updateHtml, this), this.elementUI = new i(this.el, {
                    useHover: !0
                }).on("over",
                function() {
                    this.mouseOverContext = !0
                },
                this).on("out",
                function() {
                    this.mouseOverContext = !1
                },
                this))
            },
            setup: function(e, t, n) {
                this.playerElement = t,
                this.model = e,
                this.mouseOverContext = !1,
                this.layer = n,
                t.oncontextmenu = this.rightClick.bind(this)
            },
            addOffListener: function(e) {
                e.addEventListener("mousedown", this.hideMenuHandler),
                e.addEventListener("touchstart", this.hideMenuHandler),
                e.addEventListener("pointerdown", this.hideMenuHandler)
            },
            removeOffListener: function(e) {
                e.removeEventListener("mousedown", this.hideMenuHandler),
                e.removeEventListener("touchstart", this.hideMenuHandler),
                e.removeEventListener("pointerdown", this.hideMenuHandler)
            },
            destroy: function() {
                clearTimeout(this._menuTimeout),
                this.el && (this.hideMenu(), this.elementUI.off(), this.removeOffListener(this.playerElement), this.removeOffListener(document), this.hideMenuHandler = null, this.el = null),
                this.playerElement && (this.playerElement.oncontextmenu = null, this.playerElement = null),
                this.model && (this.model.off("change:provider", this.updateHtml), this.model = null)
            }
        }),
        o
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(1), n(2)],
    r = function(e, t) {
        var n = function(e) {
            this.model = e,
            this.model.on("change:playlistItem", this.playlistItem, this)
        };
        return e.extend(n.prototype, {
            hide: function() {
                this.el.style.display = "none"
            },
            show: function() {
                this.el.style.display = ""
            },
            setup: function(e) {
                this.el = e;
                var t = this.el.getElementsByTagName("div");
                this.title = t[0],
                this.description = t[1],
                this.model.get("playlistItem") && this.playlistItem(this.model, this.model.get("playlistItem")),
                this.model.on("change:logoWidth", this.update, this),
                this.model.on("change:dock", this.update, this)
            },
            update: function(e) {
                var n = {
                    paddingLeft: 0,
                    paddingRight: 0
                },
                i = e.get("controls"),
                r = e.get("dock"),
                o = e.get("logo");
                if (o) {
                    var a = 1 * ("" + o.margin).replace("px", ""),
                    s = e.get("logoWidth") + (isNaN(a) ? 0 : a);
                    "top-left" === o.position ? n.paddingLeft = s: "top-right" === o.position && (n.paddingRight = s)
                }
                if (i && r && r.length) {
                    var l = 56 * r.length;
                    n.paddingRight = Math.max(n.paddingRight, l)
                }
                t.style(this.el, n)
            },
            playlistItem: function(e, t) {
                if (e.get("displaytitle") || e.get("displaydescription")) {
                    var n = "",
                    i = "";
                    t.title && e.get("displaytitle") && (n = t.title),
                    t.description && e.get("displaydescription") && (i = t.description),
                    this.updateText(n, i)
                } else this.hide()
            },
            updateText: function(e, t) {
                this.title.innerHTML = e,
                this.description.innerHTML = t,
                this.title.firstChild || this.description.firstChild ? this.show() : this.hide()
            },
            element: function() {
                return this.el
            }
        }),
        n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(3), n(4), n(22), n(5), n(143), n(144), n(152), n(153), n(155), n(151), n(156), n(171), n(158), n(147), n(1), n(92), n(142), n(66)],
    r = function(e, t, i, r, o, a, s, l, u, c, d, p, h, f, g, m, w, v, y) {
        var j = e.style,
        b = e.bounds,
        E = e.isMobile(),
        x = ["fullscreenchange", "webkitfullscreenchange", "mozfullscreenchange", "MSFullscreenChange"],
        k = function(k, A) {
            function _(t) {
                var n = 0,
                i = A.get("duration"),
                o = A.get("position");
                "DVR" === A.get("streamType") && (n = i, i = Math.max(o, r.dvrSeekLimit));
                var a = e.between(o + t, n, i);
                k.seek(a)
            }
            function C(t) {
                var n = e.between(A.get("volume") + t, 0, 100);
                k.setVolume(n)
            }
            function L(e) {
                return ! e.ctrlKey && !e.metaKey && !!A.get("controls")
            }
            function T(e) {
                if (!L(e)) return ! 0;
                switch (Ee || oe(), e.keyCode) {
                case 27:
                    k.setFullscreen(!1);
                    break;
                case 13:
                case 32:
                    k.play({
                        reason:
                        "interaction"
                    });
                    break;
                case 37:
                    Ee || _( - 5);
                    break;
                case 39:
                    Ee || _(5);
                    break;
                case 38:
                    C(10);
                    break;
                case 40:
                    C( - 10);
                    break;
                case 67:
                    var t = k.getCaptionsList(),
                    n = t.length;
                    if (n) {
                        var i = (k.getCurrentCaptions() + 1) % n;
                        k.setCurrentCaptions(i)
                    }
                    break;
                case 77:
                    k.setMute();
                    break;
                case 70:
                    k.setFullscreen();
                    break;
                default:
                    if (e.keyCode >= 48 && e.keyCode <= 59) {
                        var r = e.keyCode - 48,
                        o = r / 10 * A.get("duration");
                        k.seek(o)
                    }
                }
                return /13|32|37|38|39|40/.test(e.keyCode) ? (e.preventDefault(), !1) : void 0
            }
            function P() {
                qe = !1,
                e.removeClass(we, "jw-no-focus")
            }
            function R(e) {
                e.target && e.target.blur && e.target.blur()
            }
            function I() {
                qe = !0,
                e.addClass(we, "jw-no-focus")
            }
            function M() {
                qe || P(),
                Ee || oe()
            }
            function S() {
                var e = b(we),
                n = Math.round(e.width),
                i = Math.round(e.height);
                He(ze),
                n && i && (n !== je || i !== be) && (je = n, be = i, clearTimeout(Ve), Ve = setTimeout(Z, 50), A.set("containerWidth", n), A.set("containerHeight", i), v(we, n, i), Xe.trigger(t.JWPLAYER_RESIZE, {
                    width: n,
                    height: i
                }))
            }
            function O() {
                document.body.contains(we) && (He(ze), ze = Ue(S))
            }
            function D(t, n, i) {
                var r = {
                    color: t,
                    borderColor: t,
                    stroke: t
                },
                o = {
                    color: n,
                    borderColor: n,
                    stroke: n
                };
                e.css("#" + i + " .jw-color-active", r, i),
                e.css("#" + i + " .jw-color-active-hover:hover", r, i),
                e.css("#" + i + " .jw-color-inactive", o, i),
                e.css("#" + i + " .jw-color-inactive-hover:hover", o, i)
            }
            function N(t, n) {
                n = n || !1,
                e.toggleClass(we, "jw-flag-casting", n)
            }
            function Y(t, n) {
                e.toggleClass(we, "jw-flag-cast-available", n),
                e.toggleClass(ve, "jw-flag-cast-available", n)
            }
            function F(t, n) {
                e.replaceClass(we, /jw-stretch-\S+/, "jw-stretch-" + n)
            }
            function W(e) {
                e && !E && (e.element().addEventListener("mousemove", V, !1), e.element().addEventListener("mouseout", z, !1))
            }
            function J() {
                A.get("state") !== o.IDLE && A.get("state") !== o.COMPLETE && A.get("state") !== o.PAUSED || !A.get("controls") || k.play({
                    reason: "interaction"
                }),
                Be ? re() : oe();
            }
            function B(e) {
                e.link ? (k.pause(!0), k.setFullscreen(!1), window.open(e.link, e.linktarget)) : A.get("controls") && k.play({
                    reason: "interaction"
                })
            }
            function V() {
                clearTimeout(Fe)
            }
            function z() {
                oe()
            }
            function U(e) {
                Xe.trigger(e.type, e)
            }
            function H(t, n) {
                n ? (Se && Se.destroy(), e.addClass(we, "jw-flag-flash-blocked")) : (Se && Se.setup(A, we, we), e.removeClass(we, "jw-flag-flash-blocked"))
            }
            function G() {
                A.get("controls") && k.setFullscreen()
            }
            function K() {
                var n = we.getElementsByClassName("jw-overlays")[0];
                n.addEventListener("mousemove", oe),
                Ae = new s(A, ye, {
                    useHover: !0
                }),
                Ae.on("click",
                function() {
                    U({
                        type: t.JWPLAYER_DISPLAY_CLICK
                    }),
                    A.get("controls") && k.play({
                        reason: "interaction"
                    })
                }),
                Ae.on("tap",
                function() {
                    U({
                        type: t.JWPLAYER_DISPLAY_CLICK
                    }),
                    J()
                }),
                Ae.on("doubleClick", G),
                Ae.on("move", oe),
                Ae.on("over", oe);
                var i = new l(A);
                i.on("click",
                function() {
                    U({
                        type: t.JWPLAYER_DISPLAY_CLICK
                    }),
                    k.play({
                        reason: "interaction"
                    })
                }),
                i.on("tap",
                function() {
                    U({
                        type: t.JWPLAYER_DISPLAY_CLICK
                    }),
                    J()
                }),
                e.isChrome() && !e.isMobile() && i.el.addEventListener("mousedown",
                function() {
                    var e = A.getVideo(),
                    t = e && 0 === e.getName().name.indexOf("flash");
                    if (t) {
                        var n = function() {
                            document.removeEventListener("mouseup", n),
                            i.el.style.pointerEvents = "auto"
                        };
                        this.style.pointerEvents = "none",
                        document.addEventListener("mouseup", n)
                    }
                }),
                ve.appendChild(i.element()),
                Ce = new u(A),
                Le = new c(A),
                Le.on(t.JWPLAYER_LOGO_CLICK, B);
                var r = document.createElement("div");
                r.className = "jw-controls-right jw-reset",
                Le.setup(r),
                r.appendChild(Ce.element()),
                ve.appendChild(r),
                Ie = new a(A),
                Ie.setup(we.id, A.get("captions")),
                ve.parentNode.insertBefore(Ie.element(), Te.element());
                var o = A.get("height");
                E && ("string" == typeof o || o >= 1.5 * Je) ? e.addClass(we, "jw-flag-touch") : (Se = new h, Se.setup(A, we, we)),
                xe = new d(k, A),
                xe.on(t.JWPLAYER_USER_ACTION, oe),
                A.on("change:scrubbing", ge),
                A.autoStartOnMobile() && (Re = y("jw-autostart-mute jw-off", ee, A.get("localization").volume), Re.show(), ve.appendChild(Re.element()), xe.renderVolume(!0, A.get("volume")), e.addClass(we, "jw-flag-autostart"), A.set("autostartMuted", !0), A.on("change:autostartFailed", ee), A.on("change:autostartMuted", ee), A.on("change:mute", ee)),
                Pe = new g(A, k, xe.elements.next, we),
                Pe.setup(),
                ve.appendChild(Pe.element()),
                ve.appendChild(xe.element()),
                we.addEventListener("focus", M),
                we.addEventListener("blur", P),
                we.addEventListener("keydown", T),
                we.onmousedown = I,
                we.onmouseup = R
            }
            function q(t, n, i) {
                var r, o = we.className;
                i = !!i,
                i && (o = o.replace(/\s*aspectMode/, ""), we.className !== o && (we.className = o), j(we, {
                    display: "block"
                },
                i)),
                e.exists(t) && e.exists(n) && (A.set("width", t), A.set("height", n)),
                r = {
                    width: t
                },
                e.hasClass(we, "jw-flag-aspect-mode") || (r.height = n),
                A.get("aspectratio") && me(),
                j(we, r, !0),
                X(n),
                Z(t, n)
            }
            function X(t) {
                if (Me = Q(t), xe && !Me) {
                    var n = Ee ? Ee: A;
                    he(n, n.get("state"))
                }
                e.toggleClass(we, "jw-flag-audio-player", Me)
            }
            function Q(e) {
                if (A.get("aspectratio")) return ! 1;
                if (m.isString(e) && e.indexOf("%") > -1) return ! 1;
                var t = m.isNumber(e) ? e: A.get("containerHeight");
                return $(t)
            }
            function $(e) {
                return e && e <= Je * (E ? 1.75 : 1)
            }
            function Z(t, n) {
                if (!t || isNaN(Number(t))) {
                    if (!ye) return;
                    t = ye.clientWidth
                }
                if (!n || isNaN(Number(n))) {
                    if (!ye) return;
                    n = ye.clientHeight
                }
                ke && ke.resize(t, n, A.get("stretching")),
                e.isMSIE(9) && document.all && !window.atob && (t = n = "100%");
                var i = A.getVideo();
                if (i) {
                    var r = i.resize(t, n, A.get("stretching"));
                    r && (clearTimeout(Ve), Ve = setTimeout(Z, 250)),
                    A.get("aspectratio") && me(),
                    Ie.resize()
                }
            }
            function ee() {
                var t = !A.get("autostartFailed"),
                n = A.get("mute");
                t && (n = !1),
                A.off("change:autostartFailed", ee),
                A.off("change:mute", ee),
                A.off("change:autostartMuted", ee),
                A.set("autostartFailed", void 0),
                A.set("autostartMuted", void 0),
                k.setMute(n),
                xe.renderVolume(n, A.get("volume")),
                Re.hide(),
                e.removeClass(we, "jw-flag-autostart")
            }
            function te() {
                if (Ke) {
                    var e = document.fullscreenElement || document.webkitCurrentFullScreenElement || document.mozFullScreenElement || document.msFullscreenElement;
                    return ! (!e || e.id !== A.get("id"))
                }
                return Ee ? Ee.getVideo().getFullScreen() : A.getVideo().getFullScreen()
            }
            function ne(e) {
                var t = A.get("fullscreen"),
                n = void 0 !== e.jwstate ? e.jwstate: te();
                t !== n && A.set("fullscreen", n),
                clearTimeout(Ve),
                Ve = setTimeout(Z, 200)
            }
            function ie(t, n) {
                n ? (e.addClass(t, "jw-flag-fullscreen"), j(document.body, {
                    "overflow-y": "hidden"
                }), oe()) : (e.removeClass(t, "jw-flag-fullscreen"), j(document.body, {
                    "overflow-y": ""
                })),
                Z()
            }
            function re() {
                Be = !1,
                clearTimeout(Fe),
                xe.hideComponents(),
                e.addClass(we, "jw-flag-user-inactive"),
                Ie.renderCues(!0)
            }
            function oe() {
                Be || (e.removeClass(we, "jw-flag-user-inactive"), Ie.renderCues(!0)),
                Be = !0,
                clearTimeout(Fe),
                Fe = setTimeout(re, We)
            }
            function ae() {
                k.setFullscreen(!1)
            }
            function se() {
                _e && _e.setState(A.get("state")),
                le(A, A.mediaModel.get("mediaType")),
                A.mediaModel.on("change:mediaType", le, this)
            }
            function le(t, n) {
                var i = "audio" === n,
                r = A.getVideo(),
                o = r && 0 === r.getName().name.indexOf("flash");
                e.toggleClass(we, "jw-flag-media-audio", i),
                i && !o ? we.insertBefore(ke.el, ye) : we.insertBefore(ke.el, Ie.element())
            }
            function ue(t, n) {
                var i = t.get("minDvrWindow"),
                r = e.streamType(n, i),
                o = "LIVE" === r;
                t.set("streamType", r),
                e.toggleClass(we, "jw-flag-live", o),
                Xe.setAltText(o ? t.get("localization").liveBroadcast: "")
            }
            function ce(e, t) {
                return t ? void(t.name ? Te.updateText(t.name, t.message) : Te.updateText(t.message, "")) : void Te.playlistItem(e, e.get("playlistItem"))
            }
            function de() {
                var e = A.getVideo();
                return !! e && e.isCaster
            }
            function pe() {
                e.replaceClass(we, /jw-state-\S+/, "jw-state-" + Oe)
            }
            function he(e, t) {
                Oe = t,
                clearTimeout(Ge),
                t === o.PLAYING ? fe(e, t) : Ge = setTimeout(function() {
                    fe(e, t)
                },
                33)
            }
            function fe(t, n) {
                if (e.toggleClass(we, "jw-flag-dragging", t.get("scrubbing")), pe(), de()) return void e.addClass(ye, "jw-media-show");
                switch (n) {
                case o.PLAYING:
                    Z();
                    break;
                case o.PAUSED:
                    oe()
                }
            }
            function ge(e) {
                he(e, e.get("state"))
            }
            function me() {
                var e = we.getElementsByClassName("jw-aspect")[0];
                Ie.setContainerHeight(e.offsetHeight)
            }
            var we, ve, ye, je, be, Ee, xe, ke, Ae, _e, Ce, Le, Te, Pe, Re, Ie, Me, Se, Oe, De, Ne, Ye, Fe = -1,
            We = E ? 4e3: 2e3,
            Je = 40,
            Be = !1,
            Ve = -1,
            ze = -1,
            Ue = window.requestAnimationFrame ||
            function(e) {
                return window.setTimeout(e, 17)
            },
            He = window.cancelAnimationFrame || window.clearTimeout,
            Ge = -1,
            Ke = !1,
            qe = !1,
            Xe = m.extend(this, i);
            window.webpackJsonpjwplayer && n(161),
            this.model = A,
            this.api = k,
            we = e.createElement(w({
                id: A.get("id")
            })),
            e.isIE() && e.addClass(we, "jw-ie");
            var Qe = A.get("width"),
            $e = A.get("height");
            j(we, {
                width: Qe.toString().indexOf("%") > 0 ? Qe: Qe + "px",
                height: $e.toString().indexOf("%") > 0 ? $e: $e + "px"
            }),
            Ne = we.requestFullscreen || we.webkitRequestFullscreen || we.webkitRequestFullScreen || we.mozRequestFullScreen || we.msRequestFullscreen,
            Ye = document.exitFullscreen || document.webkitExitFullscreen || document.webkitCancelFullScreen || document.mozCancelFullScreen || document.msExitFullscreen,
            Ke = Ne && Ye,
            this.onChangeSkin = function(t, n) {
                e.replaceClass(we, /jw-skin-\S+/, n ? "jw-skin-" + n: "")
            },
            this.handleColorOverrides = function() {
                function t(t, i, r) {
                    if (r) {
                        t = e.prefix(t, "#" + n + " ");
                        var o = {};
                        o[i] = r,
                        e.css(t.join(", "), o, n)
                    }
                }
                var n = A.get("id"),
                i = A.get("skinColorActive"),
                r = A.get("skinColorInactive"),
                o = A.get("skinColorBackground");
                t([".jw-toggle", ".jw-button-color:hover"], "color", i),
                t([".jw-active-option", ".jw-progress"], "background", i),
                t([".jw-text", ".jw-option", ".jw-button-color", ".jw-toggle.jw-off", ".jw-tooltip-title", ".jw-skip .jw-skip-icon"], "color", r),
                t([".jw-cue", ".jw-knob"], "background", r),
                t([".jw-background-color", ".jw-tooltip-title"], "background", o),
                D(i, r, n)
            },
            this.setup = function() {
                this.handleColorOverrides(),
                A.get("skin-loading") === !0 && (e.addClass(we, "jw-flag-skin-loading"), A.once("change:skin-loading",
                function() {
                    e.removeClass(we, "jw-flag-skin-loading")
                })),
                this.onChangeSkin(A, A.get("skin"), ""),
                A.on("change:skin", this.onChangeSkin, this),
                ye = we.getElementsByClassName("jw-media")[0],
                ve = we.getElementsByClassName("jw-controls")[0];
                var n = we.getElementsByClassName("jw-preview")[0];
                ke = new p(A),
                ke.setup(n);
                var i = we.getElementsByClassName("jw-title")[0];
                Te = new f(A),
                Te.setup(i),
                K(),
                oe(),
                A.set("mediaContainer", ye),
                A.mediaController.on("fullscreenchange", ne);
                for (var r = x.length; r--;) document.addEventListener(x[r], ne, !1);
                window.removeEventListener("resize", O),
                window.addEventListener("resize", O, !1),
                E && (window.removeEventListener("orientationchange", O), window.addEventListener("orientationchange", O, !1)),
                A.on("change:errorEvent", ce),
                A.on("change:controls", Ze),
                Ze(A, A.get("controls")),
                A.on("change:state", he),
                A.on("change:duration", ue, this),
                A.on("change:flashBlocked", H),
                H(A, A.get("flashBlocked")),
                k.onPlaylistComplete(ae),
                k.onPlaylistItem(se),
                A.on("change:castAvailable", Y),
                Y(A, A.get("castAvailable")),
                A.on("change:castActive", N),
                N(A, A.get("castActive")),
                A.on("change:hideAdsControls",
                function(t, n) {
                    e.toggleClass(we, "jw-flag-ads-hide-controls", n)
                }),
                A.get("stretching") && F(A, A.get("stretching")),
                A.on("change:stretching", F),
                he(A, o.IDLE),
                A.on("change:fullscreen", et),
                W(xe),
                W(Le);
                var a = A.get("aspectratio");
                if (a) {
                    e.addClass(we, "jw-flag-aspect-mode");
                    var s = we.getElementsByClassName("jw-aspect")[0];
                    j(s, {
                        paddingTop: a
                    })
                }
                k.on(t.JWPLAYER_READY,
                function() {
                    S(),
                    q(A.get("width"), A.get("height"))
                })
            };
            var Ze = function(t, n) {
                if (n) {
                    var i = Ee ? Ee.get("state") : A.get("state");
                    he(t, i)
                }
                e.toggleClass(we, "jw-flag-controls-disabled", !n)
            },
            et = function(t, n) {
                var i = A.getVideo();
                n && A.get("autostartMuted") && ee(),
                Ke ? (n ? Ne.apply(we) : Ye.apply(document), ie(we, n)) : e.isIE() ? ie(we, n) : (Ee && Ee.getVideo() && Ee.getVideo().setFullscreen(n), i.setFullscreen(n)),
                i && 0 === i.getName().name.indexOf("flash") && i.setFullscreen(n)
            };
            this.resize = function(e, t) {
                var n = !0;
                q(e, t, n),
                S()
            },
            this.resizeMedia = Z,
            this.reset = function() {
                document.contains(we) && we.parentNode.replaceChild(De, we),
                e.emptyElement(we)
            },
            this.setupInstream = function(t) {
                this.instreamModel = Ee = t,
                Ee.on("change:controls", Ze, this),
                Ee.on("change:state", he, this),
                e.addClass(we, "jw-flag-ads"),
                oe()
            },
            this.setAltText = function(e) {
                xe.setAltText(e)
            },
            this.destroyInstream = function() {
                if (Ee && (Ee.off(null, null, this), Ee = null), this.setAltText(""), e.removeClass(we, ["jw-flag-ads", "jw-flag-ads-hide-controls"]), A.set("hideAdsControls", !1), A.getVideo) {
                    var t = A.getVideo();
                    t.setContainer(ye)
                }
                ue(A, A.get("duration")),
                Ae.revertAlternateClickHandlers()
            },
            this.addCues = function(e) {
                xe && xe.addCues(e)
            },
            this.clickHandler = function() {
                return Ae
            },
            this.controlsContainer = function() {
                return ve
            },
            this.getContainer = this.element = function() {
                return we
            },
            this.getSafeRegion = function(t) {
                var n = {
                    x: 0,
                    y: 0,
                    width: A.get("containerWidth") || 0,
                    height: A.get("containerHeight") || 0
                },
                i = A.get("dock");
                return i && i.length && A.get("controls") && (n.y = Ce.element().clientHeight, n.height -= n.y),
                t = t || !e.exists(t),
                t && A.get("controls") && (n.height -= xe.element().clientHeight),
                n
            },
            this.setCaptions = function(e) {
                Ie.clear(),
                Ie.setup(A.get("id"), e),
                Ie.resize()
            },
            this.destroy = function() {
                clearTimeout(Ge),
                clearTimeout(Ve),
                clearTimeout(Fe),
                window.removeEventListener("resize", O),
                window.removeEventListener("orientationchange", O);
                for (var t = x.length; t--;) document.removeEventListener(x[t], ne, !1);
                A.mediaController && A.mediaController.off("fullscreenchange", ne),
                we.removeEventListener("keydown", T, !1),
                Se && Se.destroy(),
                _e && (A.off("change:state", _e.statusDelegate), _e.destroy(), _e = null),
                Ee && this.destroyInstream(),
                Le && Le.destroy(),
                e.clearCss(A.get("id"))
            }
        };
        return k
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
,
function(e, t, n) {
    var i = n(81);
    "string" == typeof i && (i = [["all-players", i, ""]]),
    n(68).style(i, "all-players"),
    i.locals && (e.exports = i.locals)
},
function(e, t, n) {
    var i, r;
    i = [n(69), n(1), n(24), n(2), n(8), n(7), n(73), n(15), n(74), n(65), n(3), n(5), n(50), n(31), n(28), n(48), n(21)],
    r = function(e, t, n, i, r, o, a, s, l, u, c, d, p, h, f, g, m) {
        var w = {};
        return w.api = e,
        w._ = t,
        w.version = n,
        w.utils = t.extend(i, r, {
            canCast: f.available,
            key: a,
            extend: t.extend,
            scriptloader: s,
            rssparser: g,
            tea: l,
            UI: o
        }),
        w.utils.css.style = w.utils.style,
        w.vid = u,
        w.events = t.extend({},
        c, {
            state: d
        }),
        w.playlist = t.extend({},
        p, {
            item: h
        }),
        w.plugins = m,
        w.cast = f,
        w
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
, , ,
function(e, t, n) {
    var i, r;
    i = [n(119), n(16), n(169)],
    r = function(e, t, i) {
        var r = e.prototype.setup;
        return e.prototype.setup = function(e, o) {
            e.analytics && (e.sdkplatform = e.sdkplatform || e.analytics.sdkplatform),
            r.apply(this, arguments);
            var a = this._model.get("edition"),
            s = t(a),
            l = this._model.get("cast"),
            u = this;
            s("casting") && l && l.appid && n.e(3,
            function(require) {
                var e = n(70);
                u._castController = new e(u, u._model),
                u.castToggle = u._castController.castToggle.bind(u._castController)
            });
            var c = i.setup();
            this.once("ready", c.onReady, this),
            o.getAdBlock = c.checkAdBlock
        },
        e
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(73), n(16), n(1), n(2), n(63), n(124), n(37)],
    r = function(e, t, i, r, o, a, s) {
        function l(e, t, n) {
            if (t) {
                var i = t.client;
                delete t.client,
                /\.(js|swf)$/.test(i || "") || (i = o.repo() + n),
                e[i] = t
            }
        }
        function u(e, n) {
            var r = i.clone(n.get("plugins")) || {},
            a = n.get("edition"),
            s = t(a),
            u = /^(vast|googima|freewheel)$/,
            c = /\.(js|swf)$/,
            d = o.repo(),
            p = i.clone(n.get("advertising"));
            if (s("ads") && p && (c.test(p.client) ? r[p.client] = p: u.test(p.client) && (r[d + p.client + ".js"] = p), delete p.client), s("jwpsrv")) {
                var h = n.get("analytics");
                i.isObject(h) || (h = {}),
                l(r, h, "jwpsrv.js")
            }
            l(r, n.get("ga"), "gapro.js"),
            l(r, n.get("sharing"), "sharing.js");
            var f = n.get("related"),
            g = i.isObject(f),
            m = n.get("visualplaylist") !== !1 || g;
            g || (f = {
                disableRelated: !0
            }),
            f.showDockButton = m,
            l(r, f, "related.js");
            var w = n.get("mobileSdk");
            if (!w) {
                var v = n.get("playlist");
                i.some(v,
                function(e) {
                    if (e.stereomode && e.stereomode.length > 0) return l(r, n.get("vr") || {},
                    "vr.js"),
                    !0
                })
            }
            n.set("plugins", r),
            e()
        }
        function c(t, i) {
            var s = i.get("key") || window.jwplayer && window.jwplayer.key,
            l = new e(s),
            u = l.edition();
            if (i.set("key", s), i.set("edition", u), "unlimited" === u) {
                var c = r.getScriptPath("jwplayer.js");
                if (!c) return void a.error(t, "Error setting up player", "Could not locate jwplayer.js script tag");
                n.p = c,
                r.repo = o.repo = o.loadFrom = function() {
                    return c
                }
            }
            i.updateProviders(),
            "invalid" === u ? a.error(t, "Error setting up player", (void 0 === s ? "Missing": "Invalid") + " license key") : t()
        }
        function d(e, t) {
            s.containsDrm(t) ? s.probe(e, t.get("edition")) : e()
        }
        function p() {
            var e = a.getQueue();
            return e.CHECK_KEY = {
                method: c,
                depends: ["LOADED_POLYFILLS"]
            },
            e.PROBE_DRM_SUPPORT = {
                method: d,
                depends: ["CHECK_KEY"]
            },
            e.FILTER_PLUGINS = {
                method: u,
                depends: ["CHECK_KEY"]
            },
            e.FILTER_PLAYLIST.depends.push("PROBE_DRM_SUPPORT"),
            e.LOAD_PLUGINS.depends.push("FILTER_PLUGINS"),
            e.SETUP_VIEW.depends.push("CHECK_KEY"),
            e
        }
        return {
            getQueue: p
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(126), n(162), n(1)],
    r = function(e, t, n) {
        var i = window,
        r = n.extend(e, t);
        return "function" == typeof i.define && i.define.amd && i.define([],
        function() {
            return r
        }),
        i.jwplayer ? i.jwplayer: r
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [],
    r = function() {
        function e() {
            var e = document.createElement("div");
            return e.className = n,
            e.innerHTML = "&nbsp;",
            e.style.width = "1px",
            e.style.height = "1px",
            e.style.position = "absolute",
            e.style.background = "transparent",
            e
        }
        function t() {
            function t() {
                var e = this,
                t = e._view.element();
                t.appendChild(a),
                r = !0,
                i() && e.trigger("adBlock")
            }
            function i() {
                return !! r && ( !! o || ("" !== a.innerHTML && a.className === n && null !== a.offsetParent && 0 !== a.clientHeight || (o = !0), o))
            }
            var r = !1,
            o = !1,
            a = e();
            return {
                onReady: t,
                checkAdBlock: i
            }
        }
        var n = "afs_ads";
        return {
            setup: t
        }
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(2), n(3), n(7), n(4), n(1), n(85)],
    r = function(e, t, n, i, r, o) {
        var a = function(e) {
            this.model = e,
            this.setup()
        };
        return r.extend(a.prototype, i, {
            setup: function() {
                this.destroy(),
                this.skipMessage = this.model.get("skipText"),
                this.skipMessageCountdown = this.model.get("skipMessage"),
                this.setWaitTime(this.model.get("skipOffset"));
                var t = o();
                this.el = e.createElement(t),
                this.skiptext = this.el.getElementsByClassName("jw-skiptext")[0],
                this.skipAdOnce = r.once(this.skipAd.bind(this)),
                new n(this.el).on("click tap", r.bind(function() {
                    this.skippable && this.skipAdOnce()
                },
                this)),
                this.model.on("change:duration", this.onChangeDuration, this),
                this.model.on("change:position", this.onChangePosition, this),
                this.onChangeDuration(this.model, this.model.get("duration")),
                this.onChangePosition(this.model, this.model.get("position"))
            },
            updateMessage: function(e) {
                this.skiptext.innerHTML = e
            },
            updateCountdown: function(e) {
                this.updateMessage(this.skipMessageCountdown.replace(/xx/gi, Math.ceil(this.waitTime - e)))
            },
            onChangeDuration: function(t, n) {
                if (n) {
                    if (this.waitPercentage) {
                        if (!n) return;
                        this.itemDuration = n,
                        this.setWaitTime(this.waitPercentage),
                        delete this.waitPercentage
                    }
                    e.removeClass(this.el, "jw-hidden")
                }
            },
            onChangePosition: function(t, n) {
                this.waitTime - n > 0 ? this.updateCountdown(n) : (this.updateMessage(this.skipMessage), this.skippable = !0, e.addClass(this.el, "jw-skippable"))
            },
            element: function() {
                return this.el
            },
            setWaitTime: function(t) {
                if (r.isString(t) && "%" === t.slice( - 1)) {
                    var n = parseFloat(t);
                    return void(this.itemDuration && !isNaN(n) ? this.waitTime = this.itemDuration * n / 100 : this.waitPercentage = t)
                }
                r.isNumber(t) ? this.waitTime = t: "string" === e.typeOf(t) ? this.waitTime = e.seconds(t) : isNaN(Number(t)) ? this.waitTime = 0 : this.waitTime = Number(t)
            },
            skipAd: function() {
                this.trigger(t.JWPLAYER_AD_SKIPPED)
            },
            destroy: function() {
                this.el && (this.el.removeEventListener("click", this.skipAdOnce), this.el.parentElement && this.el.parentElement.removeChild(this.el)),
                delete this.skippable,
                delete this.itemDuration,
                delete this.waitPercentage
            }
        }),
        a
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
},
function(e, t, n) {
    var i, r;
    i = [n(157), n(1)],
    r = function(e, t) {
        var n = function() {};
        return t.extend(n.prototype, e.prototype, {
            buildArray: function() {
                var t = e.prototype.buildArray.apply(this, arguments);
                if (this.model.get("abouttext")) {
                    t.items[0].showLogo = !1,
                    t.items.push(t.items.shift());
                    var n = {
                        title: this.model.get("abouttext"),
                        link: this.model.get("aboutlink") || t.items[0].link
                    };
                    t.items.unshift(n)
                }
                return t
            }
        }),
        n
    }.apply(t, i),
    !(void 0 !== r && (e.exports = r))
}]);