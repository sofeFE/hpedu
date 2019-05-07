!
function(t) {
    function e(i) {
        if (n[i]) return n[i].exports;
        var o = n[i] = {
            exports: {},
            id: i,
            loaded: !1
        };
        return t[i].call(o.exports, o, o.exports, e),
        o.loaded = !0,
        o.exports
    }
    var n = {};
    return e.m = t,
    e.c = n,
    e.p = "",
    e(0)
} ([function(t, e, n) {
    t.exports = n(1)
},
function(t, e, n) {
    function i() {
        var t, e;
        if (navigator.plugins && "object" == typeof navigator.plugins["Shockwave Flash"]) {
            if (e = navigator.plugins["Shockwave Flash"].description) return e
        } else if ("undefined" != typeof window.ActiveXObject) try {
            if (t = new window.ActiveXObject("ShockwaveFlash.ShockwaveFlash"), t && (e = t.GetVariable("$version"))) return e
        } catch(n) {}
        return ""
    }
    var o = n(2),
    r = n(5),
    a = n(3),
    u = n(4),
    s = n(6),
    c = n(7),
    l = function(t, e) {
        function n() {
            var t = g().preload;
            return Ce[t] || 0
        }
        function i() {
            return ke[ze] || 0
        }
        function l() {
            var e = a._.some(g().tracks,
            function(t) {
                var e = t.kind;
                return "captions" === e || "subtitles" === e
            });
            if (e) return 1;
            var n = t.getCaptionsList();
            return n.length > 1 ? 2 : 0
        }
        function f() {
            var t = g(),
            e = t.sources[0],
            n = e.type;
            if (!n) {
                var i = e.file;
                n = a.extension(i)
            }
            return n
        }
        function p() {
            var t = g(),
            e = t.tracks;
            return a._.some(e,
            function(t) {
                return "thumbnails" === t.kind
            })
        }
        function h() {
            return be[Be] || 0
        }
        function g(e) {
            try {
                return t.getPlaylistItem(e)
            } catch(n) {}
            return e = e || ye,
            this.getConfig().playlist[e] || null
        }
        function v() {
            return !! t.getConfig().autostart
        }
        function m() {
            return t.getAdBlock ? t.getAdBlock() : -1
        }
        function w(t, e, n) {
            if (e === 1 / 0) return null;
            var i = e / n;
            return t / i + 1 | 0
        }
        function y(e) {
            return fe || pe.calculate(),
            [re($, Re, 21), re(G, v(), 11), re(Y, ge, 21), re(mt, we, 28), re(wt, ce, 28), re(lt, !0, 10), re(ot, fe.bucket, 21), re(at, fe.width, 21), re(ut, fe.height, 21), re(Xt, t.getVolume(), 20), re(Qt, t.getMute(), 28), re(zt, qe.getViewablePercentage(fe), 30), re(Kt, qe.getPosition(), 31), re(dt, le || R(e), 101), re(et, F(e), 20), re(pt, m(), 100)]
        }
        function k(t) {
            return [re(tt, A(t), 100)].concat(y(t))
        }
        function b(t, e) {
            return [re(ct, e, 23), re(Gt, D(), 24)].concat(k(t))
        }
        function C(t) {
            Be = t.playReason || "",
            le = R(t.item),
            le && q()
        }
        function P(e) {
            t.off("beforePlay", C),
            t.once("beforePlay", C),
            I(),
            ce = O(12),
            ye = e.index,
            tn = en = !1,
            ze = null
        }
        function I() {
            Ne = {},
            Oe = !1,
            Ke = 0,
            Qe = 0
        }
        function T(t) {
            return function(e) {
                if (!Ye) {
                    var n = Ne[t];
                    if (t === He) {
                        e = e.metadata || e;
                        var i = e.segment;
                        i && (tn = !0, en = i.encryption);
                        var o = e.drm;
                        if (o && (ze = o), n && (e.width = e.width || n.width, e.height = e.height || n.height, e.duration = e.duration || n.duration), (100 === e.duration || 0 === e.duration) && 0 === e.width && 0 === e.height) return
                    }
                    if (Ne[t] = e, t === Je && (n || ($e = 0), Ge = L(), Qe = 0), Ne[Je] && Ne[He] && Ne[We] && Ne[Ze]) {
                        var r = x();
                        "flash_adaptive" === r ? !Oe && tn && (Oe = !0, tn = !1, B(r)) : Oe || (Oe = !0, B(r))
                    }
                }
            }
        }
        function x() {
            if (a._.isFunction(t.getProvider)) {
                var e = t.getProvider();
                return e ? e.name: ""
            }
            return ""
        }
        function _() {
            var e = t.getDuration();
            if (e <= 0) {
                var n = Ne[He];
                n && (e = n.duration)
            }
            return 0 | e
        }
        function M(t) {
            return t === 1 / 0 ? 1 / 0 : (t = 0 | t, t <= 0 ? 0 : t < 30 ? 1 : t < 60 ? 4 : t < 180 ? 8 : t < 300 ? 16 : 32)
        }
        function E(t) {
            return t = 0 | t,
            t <= 0 || t === 1 / 0 ? 0 : t < 15 ? 1 : t <= 300 ? 2 : t <= 1200 ? 3 : 4
        }
        function L() {
            var e = t.getContainer().querySelector("video");
            return e ? e.currentTime: t.getPosition()
        }
        function S() {
            Ge = L(),
            Qe = 0,
            Ke = 0
        }
        function A(t) {
            var e;
            if (!t) return null;
            var n = t.sources;
            if (n) {
                for (var i = [], o = n.length; o--;) n[o].file && i.push(n[o].file);
                i.sort(),
                e = i[0]
            } else e = t.file;
            return a.getAbsolutePath(e)
        }
        function F(t) {
            return t ? t.title: null
        }
        function V(t) {
            var e = t || g();
            if (!e) return null;
            var n = e.feedid || se.feedid;
            return a.validateId(n) ? n: null
        }
        function R(t) {
            var e = t || g();
            if (!e) return null;
            var n = e.mediaid;
            return a.validateId(n) ? n: (n = a.getMediaId(e.file), a.validateId(n) ? n: null)
        }
        function j(t) {
            var e = t || g();
            if (!e || !e.stereomode) return null;
            switch (e.stereomode) {
            case "monoscopic":
                return 0;
            case "stereoscopicTopBottom":
                return 1;
            case "stereoscopicLeftRight":
                return 2;
            default:
                return null
            }
        }
        function D() {
            if (t.getPlugin) {
                var e = t.getPlugin("vr");
                if (e) switch (e.getMode()) {
                case "magic-window":
                    return 0;
                case "cardboard":
                    return 1;
                case "gear-vr":
                    return 2;
                default:
                    return null
                }
            }
            return null
        }
        function U(e) {
            if (!e) return null;
            var n = 1,
            i = 2,
            o = 3,
            r = 4,
            u = 5,
            s = 6,
            c = 0,
            l = Ne[We];
            if (l && l.levels && l.levels.length) {
                var d = l.levels[0];
                if (d && "auto" === ("" + d.label).toLowerCase()) return u
            }
            var f, p = e.sources;
            if (f = p[0].type, a.hasClass(t.getContainer(), "jw-flag-audio-player")) return s;
            var h = Ne[He] || {},
            g = 0 | h.width,
            v = 0 | h.height;
            return 0 === g && 0 === v ? "rtmp" === f ? s: c: g <= 320 ? n: g <= 640 ? i: g <= 1280 ? o: r
        }
        function q() {
            var t = x();
            Xe.track(Ve, Z, [re(vt, t, 21), re(Jt, se.hlshtml, 21), re(_t, h(), 22), re(yt, V(), 22)].concat(k(g())))
        }
        function N(e, i) {
            e = e || g();
            var o = !t.getControls(),
            r = -1;
            i && i.setupTime && (r = i.setupTime),
            pe.calculate(),
            Xe.track(Ve, J, [re(ft, d(), 22), re(ht, r, 24), re(kt, Pe, 22), re(Ot, _e, 22), re(bt, Te, 22), re(Ct, Me, 23), re(Pt, Ee, 23), re(It, xe, 22), re(Ft, n(), 22), re(Bt, se.pad, 22), re(Tt, Le, 23), re(xt, Se, 23), re(Ut, Ae, 21), re(qt, Ie, 22), re(Jt, se.hlshtml, 24), re(Nt, o, 24), re(Ht, Fe, 25), re(Yt, j(e), 26)].concat(k(e)))
        }
        function O(t) {
            return new Array(t + 1).join((Math.random().toString(36) + "00000000000000000").slice(2, 18)).slice(0, t)
        }
        function B(e) {
            var n = _(),
            o = g(),
            r = -1;
            "function" == typeof t.qoe && (r = t.qoe().firstFrame);
            var a = f(),
            u = p(),
            s = l(),
            c = i() || en;
            pe.calculate(),
            Xe.track(Ve, H, [re(rt, U(o), 21), re(st, E(n), 22), re(Mt, n, 22), re(yt, V(o), 22), re(Et, a, 22), re(Dt, t.getPlaylist().length, 22), re(Lt, u, 23), re(St, s, 23), re(At, c, 23), re(gt, r, 23), re(vt, e, 24), re(Jt, se.hlshtml, 24), re(_t, h(), 25), re(Yt, j(o), 27)].concat(b(o, M(n))).concat(Q()))
        }
        function K(e, n, i, o) {
            if (le) {
                var r = [];
                o && r.push(re($t, o, 23));
                var u = n + .5 | 0;
                u > 0 && (pe.calculate(), Xe.track(Ve, W, [re(nt, u, 21), re(it, 0 | e, 22), re(yt, V(), 22), re(Vt, a.getActiveTab(), 31), re(Rt, a.getDocumentFocus(), 31), re(jt, a.getTabFocus(t.getContainer()), 31), re(Wt, ve, 23), re(Zt, me, 23)].concat(r).concat(b(g(), i)).concat(z())))
            }
        }
        function Q() {
            return de ? [re(te, oe.cx, 30), re(ee, oe.o, 30)] : []
        }
        function z() {
            return de ? [re(ee, oe.o, 30), re(ne, oe.cs, 30), re(ie, oe.pip, 30)] : []
        }
        function X(t) {
            Ye = !!t.active
        }
        a._ || t._.extend(a, t.utils, {
            _: t._
        });
        var J = "e",
        H = "s",
        W = "t",
        Z = "pa",
        $ = "ed",
        G = "d",
        Y = "ph",
        tt = "mu",
        et = "t",
        nt = "ti",
        it = "pw",
        ot = "ps",
        rt = "vs",
        at = "wd",
        ut = "pl",
        st = "l",
        ct = "q",
        lt = "m",
        dt = "id",
        ft = "fv",
        pt = "eb",
        ht = "st",
        gt = "ff",
        vt = "pp",
        mt = "emi",
        wt = "pli",
        yt = "fed",
        kt = "vp",
        bt = "po",
        Ct = "s",
        Pt = "r",
        It = "sn",
        Tt = "cb",
        xt = "ga",
        _t = "pr",
        Mt = "vd",
        Et = "mk",
        Lt = "tt",
        St = "cct",
        At = "drm",
        Ft = "pd",
        Vt = "at",
        Rt = "df",
        jt = "tf",
        Dt = "plc",
        Ut = "pid",
        qt = "dd",
        Nt = "cp",
        Ot = "ab",
        Bt = "pad",
        Kt = "ap",
        Qt = "mt",
        zt = "vi",
        Xt = "vl",
        Jt = "hls",
        Ht = "rf",
        Wt = "tvs",
        Zt = "set",
        $t = "pdt",
        Gt = "vr",
        Yt = "vrt",
        te = "cx",
        ee = "o",
        ne = "cs",
        ie = "pip",
        oe = {
            cx: 0,
            o: 0,
            cs: 0,
            pip: 0
        },
        re = function(t, e, n) {
            return new u(t, e, n)
        },
        ae = 128,
        ue = e.debug === !0,
        se = t.getConfig();
        e.version = a.simpleSemver(t.version);
        var ce, le, de = parseInt(e.sdkplatform, 10),
        fe = null,
        pe = this,
        he = a._.isObject(window.jwplayer) ? window.jwplayer.defaults || {}: {},
        ge = se[Y] || he[Y] || 0,
        ve = se[Wt] || 0,
        me = se[Zt] || null,
        we = O(12),
        ye = 0,
        ke = {
            aes: 1,
            widevine: 2,
            playready: 3
        },
        be = {
            interaction: 1,
            "related-interaction": 1,
            autostart: 2,
            repeat: 3,
            external: 4,
            "related-auto": 5,
            playlist: 6
        },
        Ce = {
            none: 1,
            metadata: 2,
            auto: 3
        },
        Pe = se.visualplaylist !== !1,
        Ie = se.displaydescription !== !1,
        Te = !!se.image,
        xe = se.skin,
        _e = !!se.advertising,
        Me = !!se.sharing,
        Ee = !!se.related,
        Le = !!se.cast,
        Se = !!se.ga,
        Ae = se.pid,
        Fe = "";
        Ee && (Fe = se.related.recommendations || se.related.file || Fe);
        var Ve, Re = 0,
        je = se.key;
        if (je) {
            var De = new a.key(je),
            Ue = De.edition();
            "invalid" !== Ue && (Ve = De.token()),
            "platinum" === Ue ? Re = 8 : "trial" === Ue ? Re = 7 : "enterprise" === Ue ? Re = 6 : "invalid" === Ue ? Re = 4 : "ads" === Ue ? Re = 3 : "premium" === Ue ? Re = 2 : "pro" === Ue && (Re = 1)
        }
        Ve || (Ve = "_"),
        pe.getCommonAdTrackingParameters = function() {
            return y(g(), !1)
        },
        pe.calculate = function() {
            fe = function() {
                var e = 1,
                n = 2,
                i = 3,
                o = 4,
                r = 5,
                u = 0,
                s = t.getConfig(),
                c = t.getWidth(),
                l = t.getHeight(),
                d = /\d+%/.test(c);
                if (d) {
                    var f = a.bounds(t.getContainer());
                    c = Math.ceil(f.width),
                    l = Math.ceil(f.height)
                }
                return c = 0 | c,
                /\d+%/.test(s.width || c) && s.aspectratio ? {
                    bucket: o,
                    width: c,
                    height: l
                }: a.hasClass(t.getContainer(), "jw-flag-audio-player") ? {
                    bucket: r,
                    width: c,
                    height: l
                }: 0 === c ? {
                    bucket: u,
                    width: c,
                    height: l
                }: c <= 320 ? {
                    bucket: e,
                    width: c,
                    height: l
                }: c <= 640 ? {
                    bucket: n,
                    width: c,
                    height: l
                }: {
                    bucket: i,
                    width: c,
                    height: l
                }
            } ()
        },
        pe.getPlayerSize = function() {
            return fe
        };
        var qe = new r(t, 2e3);
        s(pe, e, Ve, ue, t, qe),
        c(pe, t, e, Ve, ue, qe);
        var Ne, Oe, Be, Ke, Qe, ze, Xe = new o(e, ue, "jwplayer6", qe, t.getContainer()),
        Je = "play",
        He = "meta",
        We = "levels",
        Ze = "firstFrame",
        $e = 0,
        Ge = null,
        Ye = !1,
        tn = !1,
        en = !1;
        t.on("mobile-sdk-lt",
        function(t) {
            a._.extend(oe, t)
        }),
        t.onReady(function(t) {
            N(g(), t)
        }),
        t.onPlay(T(Je)),
        t.on("firstFrame", T(Ze)),
        t.onMeta(T(He)),
        t.onQualityLevels(T(We)),
        t.onCast(X),
        t.onTime(function(e) {
            if (!Ye) {
                var n = L(),
                i = e.duration;
                if (n) {
                    if (n > 1) {
                        if (!Ne[He]) {
                            var o = {
                                duration: i
                            },
                            r = t.getContainer().querySelector("video");
                            r && (o.width = r.videoWidth, o.height = r.videoHeight),
                            T(He)(o)
                        }
                        Ne[We] || T(We)({})
                    }
                    var a = M(i),
                    u = w(n, i, a);
                    0 === Ke && (Ke = u),
                    null === Ge && (Ge = n);
                    var s = n - Ge;
                    if (Ge = n, s = Math.min(Math.max(0, s), 4), $e += s, !(i <= 0 || i === 1 / 0) && u === Ke + 1) {
                        var c = ae * Ke / a;
                        if (Ke = 0, u > a) return;
                        K(c, $e, a),
                        $e = 0
                    }
                }
            }
        }),
        t.onComplete(function() {
            if (!Ye) {
                var t = _();
                if (! (t <= 0 || t === 1 / 0)) {
                    var e = M(t);
                    K(ae, $e, e),
                    $e = 0
                }
            }
        }),
        t.on("meta",
        function(t) {
            t = t.metadata || t;
            var e, n = t.TXXX;
            if (n && (e = n.programDateTime)) {
                var i = Date.parse(e);
                if (i >= Qe) {
                    var o = 3e4,
                    r = Qe;
                    Qe = Math.floor(i / o) * o + o,
                    r > 0 && (K(null, $e || 1, null, e), $e = 0)
                }
            }
        }),
        t.onSeek(S),
        t.onIdle(I),
        t.onPlaylistItem(P),
        I()
    },
    d = function() {
        var t = i().replace("Shockwave Flash", "").replace(/ /g, "");
        return function() {
            return t
        }
    } (),
    f = window.jwplayerPluginJsonp || window.jwplayer().registerPlugin;
    f("jwpsrv", "6.0", l)
},
function(t, e, n) {
    var i = n(3),
    o = n(4),
    r = function(t, e, n, o, r) {
        i._.isFunction(t.onping) && (this.onping = t.onping);
        var a = parseInt(t.sdkplatform, 10),
        u = 1 === a || 2 === a,
        s = r.ownerDocument,
        c = s.documentElement,
        l = "complete" === c.readyState,
        d = {
            trackerVersion: "2.8.3",
//          serverURL: "jwpltx.com",
//          serverPath: "v1/" + n + "/ping.gif?",
            playerVersion: t.version,
            config: t,
            SDKPlatform: t.sdkplatform || "0",
            isMobileSDK: u,
            iFrame: void 0,
            pageURL: void 0,
            pageTitle: void 0,
            pageLoaded: l,
            queue: [],
            images: [],
            debug: e,
            positionUtils: o
        };
        if (!u) {
            if (d.iFrame = window.top !== window.self, d.iFrame) {
                d.pageURL = c.referrer;
                try {
                    d.pageURL = d.pageURL || window.top.location.href,
                    d.pageTitle = window.top.document.title
                } catch(f) {}
            }
            d.pageURL = d.pageURL || window.location.href,
            d.pageTitle = d.pageTitle || s.title
        }
        var p = i._.extend(this, d);
        if (!l) {
            var h = function() {
                for (p.pageLoaded = !0; p.queue.length;) p.ping(p.queue.shift());
                window.removeEventListener("load", h)
            };
            window.addEventListener && window.addEventListener("load", h),
            setTimeout(h, 5e3)
        }
    };
    r.prototype.track = function(t, e, n) {
        this.ping(this.buildTrackingURL(t, e, n), e)
    };
    var a = "tv",
    u = "n",
    s = "aid",
    c = "e",
    l = "i",
    d = "ifd",
    f = "pv",
    p = "pu",
    h = "pt",
    g = "sdk",
    v = "sv",
    m = "bi",
    w = "an",
    y = "did",
    k = "dm",
    b = "sc",
    C = "ts",
    P = "ha",
    I = function(t, e, n) {
        return new o(t, e, n)
    },
    T = function(t) {
        var e, n, i = 0;
        if (t = decodeURIComponent(t), !t.length) return i;
        for (e = 0; e < t.length; e++) n = t.charCodeAt(e),
        i = (i << 5) - i + n,
        i &= i;
        return i
    };
    r.prototype.buildTrackingURL = function(t, e, n) {
        var i = [I(a, this.trackerVersion, 0), I(u, Math.random().toFixed(16).substr(2, 16), 2), I(s, t, 4), I(c, e, 5), I(l, this.iFrame, 6), I(d, this.positionUtils.getIFrameDepth(), 6), I(f, this.playerVersion, 7), I(p, this.pageURL, 101), I(h, this.pageTitle, 103), I(g, this.SDKPlatform, 25)].concat(n);
        this.isMobileSDK && i.push(I(y, this.config.mobiledeviceid || "", 26), I(v, this.config.iossdkversion || "", 27), I(k, this.config.mobiledevicemodel || "", 28), I(m, this.config.bundleid || "", 29), I(w, this.config.applicationname || "", 30), I(b, this.config.systemcaptions || "", 31), I(C, this.config.texttospeech || "", 32), I(P, this.config.hardwareacceleration || "", 33)),
        i.sort(function(t, e) {
            return t.priority - e.priority
        });
        for (var o = [], r = 0; r < i.length; r++) o.push(i[r].getKey() + "=" + encodeURIComponent(i[r].getValue()));
        var x = o.join("&"),
        _ = "h=" + T(x) + "&" + x,
        M = ["//", this.serverURL, "/", this.serverPath, _];
        return "file:" === window.location.protocol && M.unshift("https:"),
        M.join("")
    },
    r.prototype.ping = function(t, e) {
        var n = "i" !== e && "ar" !== e && "as" !== e;
        if (!this.pageLoaded && n) return void this.queue.push(t);
        var i = new Image;
        i.src = t;
        for (var o = this.images,
        r = o.length; r--&&(o[r].width || o[r].complete);) o.length = r;
        if (o.push(i), this.debug && this.onping) try {
            this.onping.call(this, t)
        } catch(a) {
            console.error("jwpsrv.onping:", a.message)
        }
    },
    t.exports = r
},
function(t, e) {
    var n = {};
    n.getAdClientValue = function(t) {
        if (t) {
            if (/vast/.test(t)) return 0;
            if (/googima/.test(t)) return 1;
            if (/freewheel/.test(t)) return 2
        }
        return - 1
    },
    n.getMediaId = function(t) {
        var e = /.*\/(?:manifests|videos)\/([a-zA-Z0-9]{8})[\.-].*/,
        n = e.exec(t);
        return n && 2 === n.length ? n[1] : null
    },
    n.getActiveTab = function(t, e) {
        for (var n = 0; n < e.length && (n && (t = e[n] + "Hidden"), !(t in document)); n++);
        return t ?
        function() {
            return ! document[t]
        }: function() {
            return null
        }
    } ("", ["hidden", "webkit", "moz", "ms", "o"]),
    n.getDocumentFocus = function() {
        return document.hasFocus && "function" == typeof document.hasFocus && document.hasFocus()
    },
    n.getTabFocus = function(t) {
        for (var e = document.activeElement; e;) {
            if (e === t) return ! 0;
            e = e.parentNode
        }
        return ! 1
    },
    n.isAbsolutePath = function(t) {
        return t.match(/^[a-zA-Z]+:\/\//)
    },
    n.simpleSemver = function(t) {
        return t.split("+")[0]
    },
    n.hasClass = function(t, e) {
        var n = " " + e + " ";
        return 1 === t.nodeType && (" " + t.className + " ").replace(/[\t\r\n\f]/g, " ").indexOf(n) >= 0
    },
    n.validateId = function(t) {
        var e = /^[a-zA-Z0-9]{8}$/;
        return e.test(t)
    },
    t.exports = n
},
function(t, e) {
    var n = function(t, e, n) {
        e === !0 || e === !1 ? e = e ? 1 : 0 : null !== e && void 0 !== e || (e = ""),
        this.key = t,
        this.value = e,
        this.priority = n
    };
    n.prototype.getKey = function() {
        return this.key
    },
    n.prototype.getValue = function() {
        return this.value
    },
    t.exports = n
},
function(t, e) {
    function n(t, e) {
        var n = t.top + t.height < e.top || t.top > e.top + e.height || t.left + t.width < e.left || t.left > e.left + e.width,
        i = {
            top: 0,
            left: 0,
            width: 0,
            height: 0
        };
        return n === !1 && (i = {
            top: Math.max(t.top, e.top),
            left: Math.max(t.left, e.left),
            width: Math.min(Math.abs(t.left - (e.left + e.width)), Math.abs(e.left - (t.left + t.width)), t.width, e.width),
            height: Math.min(Math.abs(t.top - (e.top + e.height)), Math.abs(e.top - (t.top + t.height)), t.height, e.height)
        }),
        i
    }
    function i(t, e) {
        for (var i, o = t,
        r = e; null !== o.parentElement && "undefined" != typeof o.parentElement.tagName;) i = s(o.parentElement),
        r = n(r, i),
        o = o.parentElement;
        var a = s(o.ownerDocument.body).width,
        u = s(o.ownerDocument.body).height;
        return r = n(r, {
            top: 0,
            left: 0,
            width: a,
            height: u
        })
    }
    function o(t, e) {
        for (var n = t,
        i = e; null !== n && "undefined" != typeof n.tagName;) null !== n.offsetParent && n.offsetParent === n.parentElement.offsetParent ? (i.top += n.offsetTop - n.parentElement.offsetTop, i.left += n.offsetLeft - n.parentElement.offsetLeft) : (i.top += n.offsetTop, i.left += n.offsetLeft),
        null !== n.parentElement && "BODY" !== n.parentElement.tagName && (i.top -= "undefined" != typeof n.parentElement.scrollTop ? n.parentElement.scrollTop: 0, i.left -= "undefined" != typeof n.parentElement.scrollLeft ? n.parentElement.scrollLeft: 0),
        n = n.parentElement;
        return i
    }
    function r(t, e) {
        for (var n = {
            top: 0,
            left: 0
        },
        i = t.getContainer(); null !== i;) {
            if (n = o(i, n), e || c(i) === window.top) return n;
            i = c(i).frameElement
        }
        return n
    }
    function a(t) {
        for (var e = t.getContainer(), n = s(e); null !== e;) {
            if (n = i(e, n), c(e) === window.top) return n;
            try {
                e = c(e).frameElement,
                n.top += e.offsetTop,
                n.left += e.offsetLeft
            } catch(o) {
                e = null
            }
        }
        return n
    }
    function u(t, e) {
        for (var n = t.getContainer(), r = s(n), a = {
            top: 0,
            left: 0
        }; null !== n;) {
            r = i(n, r),
            a = o(n, a);
            var u = c(n);
            if (u === window.top) {
                u.parent.postMessage(JSON.stringify({
                    type: "jwpsrv_position_response",
                    playerId: t.id,
                    rect: {
                        top: r.top,
                        left: r.left,
                        width: r.width,
                        height: r.height
                    },
                    iframeDepth: 0,
                    coords: a,
                    responseChain: e
                }), "*"),
                n = null;
                break
            }
            try {
                n = u.frameElement
            } catch(l) {
                n = null
            }
            null === n ? parent.postMessage(JSON.stringify({
                type: "jwpsrv_position",
                playerId: t.id,
                rect: {
                    top: r.top,
                    left: r.left,
                    width: r.width,
                    height: r.height
                },
                iframeDepth: 0,
                coords: a,
                responseChain: e
            }), "*") : (r.top += n.offsetTop, r.left += n.offsetLeft)
        }
    }
    function s(t) {
        if (!t.getBoundingClientRect) throw new Error("Cannot get bounds: " + t);
        var e = t.getBoundingClientRect(),
        n = {
            left: e.left,
            top: e.top,
            width: e.width,
            height: e.height
        };
        return "width" in e || (n.width = e.right - e.left),
        "height" in e || (n.height = e.bottom - e.top),
        n
    }
    function c(t) {
        var e = t.ownerDocument;
        return e.defaultView || e.parentWindow
    }
    var l = function(t, e) {
        function n() {
            var e = t.getContainer();
            return ! e || t.getState() === o && !e.parentNode
        }
        this.playerAPI = t,
        this.lastViewRect = void 0,
        this.lastViewPos = void 0,
        this.iframeDepth = 0;
        var i, o = "idle",
        r = window.location;
        try {
            i = !(window.top.location.protocol === r.protocol && window.top.location.host === r.host && window.top.location.port === r.port)
        } catch(a) {
            i = !0
        }
        if (this.isPolling = i, i) {
            var s = this,
            c = "" + Math.floor(1e11 * Math.random()),
            l = function(t) {
                var e;
                try {
                    e = JSON.parse(t.data)
                } catch(n) {}
                e && e.type && "jwpsrv_position_response" === e.type && s.playerAPI.id === e.playerId && (s.lastViewRect = e.rect, s.lastViewPos = e.coords, s.iframeDepth = e.iframeDepth)
            };
            window.addEventListener ? window.addEventListener("message", l) : window.attachEvent("onmessage", l),
            u(t, c);
            var d = setInterval(function() {
                n() ? clearInterval(d) : s.isPolling && u(t, c)
            },
            e)
        }
    };
    l.prototype.getPosition = function() {
        if (this.playerAPI.getFullscreen()) return "0,0";
        if (this.isPolling) return this.lastViewPos ? this.lastViewPos.left + "," + this.lastViewPos.top: null;
        var t = r(this.playerAPI, this.isPolling);
        return t.left + "," + t.top
    },
    l.prototype.getViewablePercentage = function(t) {
        if (this.playerAPI.getFullscreen()) return 1;
        if (this.isPolling) return this.lastViewRect ? Math.round(1e3 * (this.lastViewRect.width * this.lastViewRect.height / (t.width * t.height))) / 1e3: null;
        var e = a(this.playerAPI);
        return Math.round(1e3 * (e.width * e.height / (t.width * t.height))) / 1e3
    },
    l.prototype.getIFrameDepth = function() {
        if (this.isPolling === !0) return this.iframeDepth ? this.iframeDepth: null;
        for (var t = c(this.playerAPI.getContainer()), e = 0; t !== window.top;) t = c(t.frameElement),
        e++;
        return e
    },
    t.exports = l
},
function(t, e, n) {
    function i(t, e, n) {
        return new r(t, e, n)
    }
    var o = n(2),
    r = n(4),
    a = n(3);
    t.exports = function(t, e, n, r, u, s) {
        function c(t, e) {
            C.track(n, t, e)
        }
        function l(t) {
            I === -1 && t && (I = a.getAdClientValue(t.client)),
            d(t, "adbreakid"),
            d(t, "adtagid"),
            d(t, "offset"),
            d(t, "witem"),
            d(t, "wcount"),
            d(t, "skipoffset"),
            d(t, "linear",
            function(t, e) {
                return e === t
            }),
            d(t, "adposition",
            function(t, e) {
                return {
                    pre: 0,
                    mid: 1,
                    post: 2,
                    api: 3
                } [e]
            }),
            d(t, "creativetype",
            function(t, e) {
                var n = "";
                switch (e) {
                case "static":
                    n = "image/unknown";
                    break;
                case "video":
                    n = "video/unknown";
                    break;
                case "vpaid":
                case "vpaid-swf":
                    n = "application/x-shockwave-flash";
                    break;
                case "vpaid-js":
                    n = "application/javascript";
                    break;
                default:
                    n = e || n
                }
                return b.adCreativeType = n,
                n
            }),
            d(t, "tag",
            function(t, e) {
                return b.tagdomain = g(a.getAbsolutePath(e)),
                e
            }),
            b.adSystem = t.adsystem || b.adSystem,
            b.vastVersion = t.vastversion || b.vastVersion,
            b.podIndex = t.sequence || b.podIndex,
            b.podCount = t.podcount || b.podCount
        }
        function d(t, e, n) {
            t.hasOwnProperty(e) && (n = n || f, b[e] = n(e, t[e]))
        }
        function f(t, e) {
            return e
        }
        function p() {
            var t, e = u.getPlaylistItem();
            return e && (t = e.feedid),
            t || (t = k.feedid),
            a.validateId(t) ? t: null
        }
        function h(t, e) {
            if (! (e > 4)) {
                if (P.adscheduleid && e > b.previousQuartile) {
                    l(t);
                    var n = [i(N, b.duration, 1), i(W, e, 1)].concat(w());
                    c(E, n)
                }
                b.previousQuartile = e
            }
        }
        function g(t) {
            if (t) {
                var e = t.match(new RegExp(/^[^\/]*:\/\/\/?([^\/]*)/));
                if (e && e.length > 1) return e[1]
            }
            return ""
        }
        function v() {
            return [i(A, b.adId, 1), i(V, I, 19), i(F, u.getMute(), 28), i(X, b.tagdomain, 29), i(j, b.adposition, 31), i(Z, b.witem, 32), i($, b.wcount, 32)].concat(t.getCommonAdTrackingParameters())
        }
        function m() {
            return [i(G, P.adscheduleid, 20), i(Y, b.adbreakid, 21), i(tt, b.adtagid, 21), i(et, b.skipoffset, 22), i(nt, b.offset, 23)]
        }
        function w() {
            return t.calculate(),
            [i(Q, b.podCount, 22), i(z, b.podIndex, 23), i(U, b.adCreativeType, 24), i(R, b.linear, 25), i(D, b.vastVersion, 26), i(q, b.adSystem, 27), i(B, a.getActiveTab(), 31), i(K, a.getDocumentFocus(), 31), i(O, a.getTabFocus(u.getContainer()), 31)].concat(v())
        }
        var y = {
            numCompanions: -1,
            podCount: 0,
            podIndex: 0,
            linear: -1,
            vastVersion: -1,
            adSystem: "",
            adCreativeType: "",
            adposition: -1,
            tagdomain: "",
            position: "",
            previousQuartile: 0,
            duration: "",
            witem: 1,
            wcount: 1
        },
        k = u.getConfig(),
        b = null,
        C = new o(e, r, "clienta", s, u.getContainer()),
        P = (k || {}).advertising || {},
        I = a.getAdClientValue(P.client),
        T = "i",
        x = "as",
        _ = "c",
        M = "s",
        E = "v",
        L = "ae",
        S = "ar",
        A = "adi",
        F = "mt",
        V = "c",
        R = "al",
        j = "p",
        D = "vv",
        U = "ct",
        q = "ad",
        N = "du",
        O = "tf",
        B = "at",
        K = "df",
        Q = "pc",
        z = "pi",
        X = "vu",
        J = "ec",
        H = "tw",
        W = "qt",
        Z = "awi",
        $ = "awc",
        G = "ask",
        Y = "abk",
        tt = "atk",
        et = "sko",
        nt = "abo",
        it = "fed";
        u.on("adRequest adMeta adImpression adComplete adSkipped adError adTime",
        function(t) {
            b && b.adId === t.id && t.id !== -1 || (b = a._.extend({
                adId: t.id
            },
            y))
        }).on("adRequest adMeta adImpression adComplete adSkipped adError", l).on("adRequest",
        function() {
            P.adscheduleid && c(S, v().concat(m()))
        }).on("adImpression",
        function() {
            c(T, [i(et, b.skipoffset, 1), i(it, p(), 2)].concat(w()).concat(m()))
        }).on("adStarted",
        function() {
            c(x, w().concat(m()))
        }).on("adComplete",
        function(t) {
            h(t, 4)
        }).on("adSkipped",
        function() {
            var t = [i(H, b.position, 1), i(W, b.previousQuartile, 1), i(N, b.duration, 1)].concat(m()).concat(w());
            c(M, t)
        }).on("adError",
        function(t) {
            if (P.adscheduleid) {
                var e = 1;
                "object" == typeof t && t && void 0 !== t.code && (e = t.code);
                var n = [i(J, e, 1)].concat(v().concat(m()));
                c(L, n)
            }
        }).on("adClick",
        function() {
            var t = [i(H, b.position, 1), i(N, b.duration, 1), i(W, b.previousQuartile, 1)].concat(w().concat(m()));
            c(_, t)
        }).on("adTime",
        function(t) {
            if (b.position = t.position, b.duration = b.duration || t.duration, b.duration) {
                var e = Math.floor((4 * b.position + .05) / b.duration);
                h(t, e)
            }
        })
    }
},
function(t, e, n) {
    function i(t, e, n, i) {
        function a(t) {
            return function(o) {
                t(o, e, n, i)
            }
        }
        if (t.getPlugin) {
            var u = t.getPlugin("related");
            u && (u.on("open", a(o)), u.on("play", a(r)))
        }
    }
    function o(t, e, n, i) {
        a(p, t, [], e, n, i)
    }
    function r(t, e, n, i) {
        var o = [];
        if (l._.has(t, "item")) {
            var r;
            r = "play" === t.onclick ? t.item.mediaid: t.item.link,
            o.push(I(y, r, 5))
        }
        l._.has(t, "autoplaytimer") && o.push(I(b, t.autoplaytimer, 5)),
        a(f, t, o, e, n, i)
    }
    function a(t, e, n, i, o, r) {
        n = u(t, n, e, i),
        r.track(o, t, n.concat(i.getCommonAdTrackingParameters()))
    }
    function u(t, e, n, i) {
        if (i.calculate(), l._.has(n, "onclick") && e.push(I(h, "play" === n.onclick ? 1 : 0, 21)), l._.has(n, "method")) {
            var o, r = P[n.method] || 0;
            t === f ? o = m: t === p && (o = w),
            o && e.push(I(o, r, 5))
        }
        return l._.has(n, "method_group_id") && e.push(I(g, n.method_group_id, 5)),
        l._.has(n, "rec_id") && e.push(I(v, n.rec_id, 6)),
        l._.has(n, "position") && e.push(I(k, n.position + 1, 6)),
        e.push(I(C, s(n), 6)),
        e
    }
    function s(t) {
        var e = null;
        return l._.has(t, "feedid") && l.validateId(t.feedid) && (e = t.feedid),
        e
    }
    var c = n(2),
    l = n(3),
    d = n(4),
    f = "rc",
    p = "rs",
    h = "os",
    g = "mgi",
    v = "ri",
    m = "rct",
    w = "rst",
    y = "rn",
    k = "oc",
    b = "rat",
    C = "fed",
    P = {
        play: 1,
        api: 2,
        interaction: 3,
        complete: 4,
        auto: 5,
        manual: 6,
        link: 7
    };
    t.exports = function(t, e, n, o, r, a) {
        var u = new c(n, r, "jwplayer6", a, e.getContainer());
        e.on("ready",
        function() {
            i(e, t, o, u)
        })
    };
    var I = function(t, e, n) {
        return new d(t, e, n)
    }
}]);