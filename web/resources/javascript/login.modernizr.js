/* Modernizr 2.7.1 (Custom Build) | MIT & BSD
 * Build: http://modernizr.com/download/#-fontface-backgroundsize-borderimage-borderradius-boxshadow-flexbox-hsla-multiplebgs-opacity-rgba-textshadow-cssanimations-csscolumns-generatedcontent-cssgradients-cssreflections-csstransforms-csstransforms3d-csstransitions-applicationcache-canvas-canvastext-draganddrop-hashchange-history-audio-video-indexeddb-input-inputtypes-localstorage-postmessage-sessionstorage-websockets-websqldatabase-webworkers-geolocation-inlinesvg-smil-svg-svgclippaths-touch-webgl-shiv-cssclasses-addtest-prefixed-teststyles-testprop-testallprops-hasevent-prefixes-domprefixes-load
 */
window.Modernizr = function(e, t, n) {
    function L(e) {
        f.cssText = e
    }
    function A(e, t) {
        return L(p.join(e + ";") + (t || ""))
    }
    function O(e, t) {
        return typeof e === t
    }
    function M(e, t) {
        return!!~("" + e).indexOf(t)
    }
    function _(e, t) {
        for (var r in e) {
            var i = e[r];
            if (!M(i, "-") && f[i] !== n)
                return t == "pfx" ? i : !0
        }
        return!1
    }
    function D(e, t, r) {
        for (var i in e) {
            var s = t[e[i]];
            if (s !== n)
                return r === !1 ? e[i] : O(s, "function") ? s.bind(r || t) : s
        }
        return!1
    }
    function P(e, t, n) {
        var r = e.charAt(0).toUpperCase() + e.slice(1), i = (e + " " + v.join(r + " ") + r).split(" ");
        return O(t, "string") || O(t, "undefined") ? _(i, t) : (i = (e + " " + m.join(r + " ") + r).split(" "), D(i, t, n))
    }
    function H() {
        i.input = function(n) {
            for (var r = 0, i = n.length; r < i; r++)
                w[n[r]] = n[r]in l;
            return w.list && (w.list = !!t.createElement("datalist") && !!e.HTMLDataListElement), w
        }("autocomplete autofocus list placeholder max min multiple pattern required step".split(" ")), i.inputtypes = function(e) {
            for (var r = 0, i, s, u, a = e.length; r < a; r++)
                l.setAttribute("type", s = e[r]), i = l.type !== "text", i && (l.value = c, l.style.cssText = "position:absolute;visibility:hidden;", /^range$/.test(s) && l.style.WebkitAppearance !== n ? (o.appendChild(l), u = t.defaultView, i = u.getComputedStyle && u.getComputedStyle(l, null).WebkitAppearance !== "textfield" && l.offsetHeight !== 0, o.removeChild(l)) : /^(search|tel)$/.test(s) || (/^(url|email)$/.test(s) ? i = l.checkValidity && l.checkValidity() === !1 : i = l.value != c)), b[e[r]] = !!i;
            return b
        }("search tel url email datetime date month week time datetime-local number range color".split(" "))
    }
    var r = "2.7.1", i = {}, s = !0, o = t.documentElement, u = "modernizr", a = t.createElement(u), f = a.style, l = t.createElement("input"), c = ":)", h = {}.toString, p = " -webkit- -moz- -o- -ms- ".split(" "), d = "Webkit Moz O ms", v = d.split(" "), m = d.toLowerCase().split(" "), g = {svg: "http://www.w3.org/2000/svg"}, y = {}, b = {}, w = {}, E = [], S = E.slice, x, T = function(e, n, r, i) {
        var s, a, f, l, c = t.createElement("div"), h = t.body, p = h || t.createElement("body");
        if (parseInt(r, 10))
            while (r--)
                f = t.createElement("div"), f.id = i ? i[r] : u + (r + 1), c.appendChild(f);
        return s = ["&#173;", '<style id="s', u, '">', e, "</style>"].join(""), c.id = u, (h ? c : p).innerHTML += s, p.appendChild(c), h || (p.style.background = "", p.style.overflow = "hidden", l = o.style.overflow, o.style.overflow = "hidden", o.appendChild(p)), a = n(c, e), h ? c.parentNode.removeChild(c) : (p.parentNode.removeChild(p), o.style.overflow = l), !!a
    }, N = function() {
        function r(r, i) {
            i = i || t.createElement(e[r] || "div"), r = "on" + r;
            var s = r in i;
            return s || (i.setAttribute || (i = t.createElement("div")), i.setAttribute && i.removeAttribute && (i.setAttribute(r, ""), s = O(i[r], "function"), O(i[r], "undefined") || (i[r] = n), i.removeAttribute(r))), i = null, s
        }
        var e = {select: "input", change: "input", submit: "form", reset: "form", error: "img", load: "img", abort: "img"};
        return r
    }(), C = {}.hasOwnProperty, k;
    !O(C, "undefined") && !O(C.call, "undefined") ? k = function(e, t) {
        return C.call(e, t)
    } : k = function(e, t) {
        return t in e && O(e.constructor.prototype[t], "undefined")
    }, Function.prototype.bind || (Function.prototype.bind = function(t) {
        var n = this;
        if (typeof n != "function")
            throw new TypeError;
        var r = S.call(arguments, 1), i = function() {
            if (this instanceof i) {
                var e = function() {
                };
                e.prototype = n.prototype;
                var s = new e, o = n.apply(s, r.concat(S.call(arguments)));
                return Object(o) === o ? o : s
            }
            return n.apply(t, r.concat(S.call(arguments)))
        };
        return i
    }), y.flexbox = function() {
        return P("flexWrap")
    }, y.canvas = function() {
        var e = t.createElement("canvas");
        return!!e.getContext && !!e.getContext("2d")
    }, y.canvastext = function() {
        return!!i.canvas && !!O(t.createElement("canvas").getContext("2d").fillText, "function")
    }, y.webgl = function() {
        return!!e.WebGLRenderingContext
    }, y.touch = function() {
        var n;
        return"ontouchstart"in e || e.DocumentTouch && t instanceof DocumentTouch ? n = !0 : T(["@media (", p.join("touch-enabled),("), u, ")", "{#modernizr{top:9px;position:absolute}}"].join(""), function(e) {
            n = e.offsetTop === 9
        }), n
    }, y.geolocation = function() {
        return"geolocation"in navigator
    }, y.postmessage = function() {
        return!!e.postMessage
    }, y.websqldatabase = function() {
        return!!e.openDatabase
    }, y.indexedDB = function() {
        return!!P("indexedDB", e)
    }, y.hashchange = function() {
        return N("hashchange", e) && (t.documentMode === n || t.documentMode > 7)
    }, y.history = function() {
        return!!e.history && !!history.pushState
    }, y.draganddrop = function() {
        var e = t.createElement("div");
        return"draggable"in e || "ondragstart"in e && "ondrop"in e
    }, y.websockets = function() {
        return"WebSocket"in e || "MozWebSocket"in e
    }, y.rgba = function() {
        return L("background-color:rgba(150,255,150,.5)"), M(f.backgroundColor, "rgba")
    }, y.hsla = function() {
        return L("background-color:hsla(120,40%,100%,.5)"), M(f.backgroundColor, "rgba") || M(f.backgroundColor, "hsla")
    }, y.multiplebgs = function() {
        return L("background:url(https://),url(https://),red url(https://)"), /(url\s*\(.*?){3}/.test(f.background)
    }, y.backgroundsize = function() {
        return P("backgroundSize")
    }, y.borderimage = function() {
        return P("borderImage")
    }, y.borderradius = function() {
        return P("borderRadius")
    }, y.boxshadow = function() {
        return P("boxShadow")
    }, y.textshadow = function() {
        return t.createElement("div").style.textShadow === ""
    }, y.opacity = function() {
        return A("opacity:.55"), /^0.55$/.test(f.opacity)
    }, y.cssanimations = function() {
        return P("animationName")
    }, y.csscolumns = function() {
        return P("columnCount")
    }, y.cssgradients = function() {
        var e = "background-image:", t = "gradient(linear,left top,right bottom,from(#9f9),to(white));", n = "linear-gradient(left top,#9f9, white);";
        return L((e + "-webkit- ".split(" ").join(t + e) + p.join(n + e)).slice(0, -e.length)), M(f.backgroundImage, "gradient")
    }, y.cssreflections = function() {
        return P("boxReflect")
    }, y.csstransforms = function() {
        return!!P("transform")
    }, y.csstransforms3d = function() {
        var e = !!P("perspective");
        return e && "webkitPerspective"in o.style && T("@media (transform-3d),(-webkit-transform-3d){#modernizr{left:9px;position:absolute;height:3px;}}", function(t, n) {
            e = t.offsetLeft === 9 && t.offsetHeight === 3
        }), e
    }, y.csstransitions = function() {
        return P("transition")
    }, y.fontface = function() {
        var e;
        return T('@font-face {font-family:"font";src:url("https://")}', function(n, r) {
            var i = t.getElementById("smodernizr"), s = i.sheet || i.styleSheet, o = s ? s.cssRules && s.cssRules[0] ? s.cssRules[0].cssText : s.cssText || "" : "";
            e = /src/i.test(o) && o.indexOf(r.split(" ")[0]) === 0
        }), e
    }, y.generatedcontent = function() {
        var e;
        return T(["#", u, "{font:0/0 a}#", u, ':after{content:"', c, '";visibility:hidden;font:3px/1 a}'].join(""), function(t) {
            e = t.offsetHeight >= 3
        }), e
    }, y.video = function() {
        var e = t.createElement("video"), n = !1;
        try {
            if (n = !!e.canPlayType)
                n = new Boolean(n), n.ogg = e.canPlayType('video/ogg; codecs="theora"').replace(/^no$/, ""), n.h264 = e.canPlayType('video/mp4; codecs="avc1.42E01E"').replace(/^no$/, ""), n.webm = e.canPlayType('video/webm; codecs="vp8, vorbis"').replace(/^no$/, "")
        } catch (r) {
        }
        return n
    }, y.audio = function() {
        var e = t.createElement("audio"), n = !1;
        try {
            if (n = !!e.canPlayType)
                n = new Boolean(n), n.ogg = e.canPlayType('audio/ogg; codecs="vorbis"').replace(/^no$/, ""), n.mp3 = e.canPlayType("audio/mpeg;").replace(/^no$/, ""), n.wav = e.canPlayType('audio/wav; codecs="1"').replace(/^no$/, ""), n.m4a = (e.canPlayType("audio/x-m4a;") || e.canPlayType("audio/aac;")).replace(/^no$/, "")
        } catch (r) {
        }
        return n
    }, y.localstorage = function() {
        try {
            return localStorage.setItem(u, u), localStorage.removeItem(u), !0
        } catch (e) {
            return!1
        }
    }, y.sessionstorage = function() {
        try {
            return sessionStorage.setItem(u, u), sessionStorage.removeItem(u), !0
        } catch (e) {
            return!1
        }
    }, y.webworkers = function() {
        return!!e.Worker
    }, y.applicationcache = function() {
        return!!e.applicationCache
    }, y.svg = function() {
        return!!t.createElementNS && !!t.createElementNS(g.svg, "svg").createSVGRect
    }, y.inlinesvg = function() {
        var e = t.createElement("div");
        return e.innerHTML = "<svg/>", (e.firstChild && e.firstChild.namespaceURI) == g.svg
    }, y.smil = function() {
        return!!t.createElementNS && /SVGAnimate/.test(h.call(t.createElementNS(g.svg, "animate")))
    }, y.svgclippaths = function() {
        return!!t.createElementNS && /SVGClipPath/.test(h.call(t.createElementNS(g.svg, "clipPath")))
    };
    for (var B in y)
        k(y, B) && (x = B.toLowerCase(), i[x] = y[B](), E.push((i[x] ? "" : "no-") + x));
    return i.input || H(), i.addTest = function(e, t) {
        if (typeof e == "object")
            for (var r in e)
                k(e, r) && i.addTest(r, e[r]);
        else {
            e = e.toLowerCase();
            if (i[e] !== n)
                return i;
            t = typeof t == "function" ? t() : t, typeof s != "undefined" && s && (o.className += " " + (t ? "" : "no-") + e), i[e] = t
        }
        return i
    }, L(""), a = l = null, function(e, t) {
        function c(e, t) {
            var n = e.createElement("p"), r = e.getElementsByTagName("head")[0] || e.documentElement;
            return n.innerHTML = "x<style>" + t + "</style>", r.insertBefore(n.lastChild, r.firstChild)
        }
        function h() {
            var e = y.elements;
            return typeof e == "string" ? e.split(" ") : e
        }
        function p(e) {
            var t = f[e[u]];
            return t || (t = {}, a++, e[u] = a, f[a] = t), t
        }
        function d(e, n, r) {
            n || (n = t);
            if (l)
                return n.createElement(e);
            r || (r = p(n));
            var o;
            return r.cache[e] ? o = r.cache[e].cloneNode() : s.test(e) ? o = (r.cache[e] = r.createElem(e)).cloneNode() : o = r.createElem(e), o.canHaveChildren && !i.test(e) && !o.tagUrn ? r.frag.appendChild(o) : o
        }
        function v(e, n) {
            e || (e = t);
            if (l)
                return e.createDocumentFragment();
            n = n || p(e);
            var r = n.frag.cloneNode(), i = 0, s = h(), o = s.length;
            for (; i < o; i++)
                r.createElement(s[i]);
            return r
        }
        function m(e, t) {
            t.cache || (t.cache = {}, t.createElem = e.createElement, t.createFrag = e.createDocumentFragment, t.frag = t.createFrag()), e.createElement = function(n) {
                return y.shivMethods ? d(n, e, t) : t.createElem(n)
            }, e.createDocumentFragment = Function("h,f", "return function(){var n=f.cloneNode(),c=n.createElement;h.shivMethods&&(" + h().join().replace(/[\w\-]+/g, function(e) {
                return t.createElem(e), t.frag.createElement(e), 'c("' + e + '")'
            }) + ");return n}")(y, t.frag)
        }
        function g(e) {
            e || (e = t);
            var n = p(e);
            return y.shivCSS && !o && !n.hasCSS && (n.hasCSS = !!c(e, "article,aside,dialog,figcaption,figure,footer,header,hgroup,main,nav,section{display:block}mark{background:#FF0;color:#000}template{display:none}")), l || m(e, n), e
        }
        var n = "3.7.0", r = e.html5 || {}, i = /^<|^(?:button|map|select|textarea|object|iframe|option|optgroup)$/i, s = /^(?:a|b|code|div|fieldset|h1|h2|h3|h4|h5|h6|i|label|li|ol|p|q|span|strong|style|table|tbody|td|th|tr|ul)$/i, o, u = "_html5shiv", a = 0, f = {}, l;
        (function() {
            try {
                var e = t.createElement("a");
                e.innerHTML = "<xyz></xyz>", o = "hidden"in e, l = e.childNodes.length == 1 || function() {
                    t.createElement("a");
                    var e = t.createDocumentFragment();
                    return typeof e.cloneNode == "undefined" || typeof e.createDocumentFragment == "undefined" || typeof e.createElement == "undefined"
                }()
            } catch (n) {
                o = !0, l = !0
            }
        })();
        var y = {elements: r.elements || "abbr article aside audio bdi canvas data datalist details dialog figcaption figure footer header hgroup main mark meter nav output progress section summary template time video", version: n, shivCSS: r.shivCSS !== !1, supportsUnknownElements: l, shivMethods: r.shivMethods !== !1, type: "default", shivDocument: g, createElement: d, createDocumentFragment: v};
        e.html5 = y, g(t)
    }(this, t), i._version = r, i._prefixes = p, i._domPrefixes = m, i._cssomPrefixes = v, i.hasEvent = N, i.testProp = function(e) {
        return _([e])
    }, i.testAllProps = P, i.testStyles = T, i.prefixed = function(e, t, n) {
        return t ? P(e, t, n) : P(e, "pfx")
    }, o.className = o.className.replace(/(^|\s)no-js(\s|$)/, "$1$2") + (s ? " js " + E.join(" ") : ""), i
}(this, this.document), function(e, t, n) {
    function r(e) {
        return"[object Function]" == d.call(e)
    }
    function i(e) {
        return"string" == typeof e
    }
    function s() {
    }
    function o(e) {
        return!e || "loaded" == e || "complete" == e || "uninitialized" == e
    }
    function u() {
        var e = v.shift();
        m = 1, e ? e.t ? h(function() {
            ("c" == e.t ? k.injectCss : k.injectJs)(e.s, 0, e.a, e.x, e.e, 1)
        }, 0) : (e(), u()) : m = 0
    }
    function a(e, n, r, i, s, a, f) {
        function l(t) {
            if (!d && o(c.readyState) && (w.r = d = 1, !m && u(), c.onload = c.onreadystatechange = null, t)) {
                "img" != e && h(function() {
                    b.removeChild(c)
                }, 50);
                for (var r in T[n])
                    T[n].hasOwnProperty(r) && T[n][r].onload()
            }
        }
        var f = f || k.errorTimeout, c = t.createElement(e), d = 0, g = 0, w = {t: r, s: n, e: s, a: a, x: f};
        1 === T[n] && (g = 1, T[n] = []), "object" == e ? c.data = n : (c.src = n, c.type = e), c.width = c.height = "0", c.onerror = c.onload = c.onreadystatechange = function() {
            l.call(this, g)
        }, v.splice(i, 0, w), "img" != e && (g || 2 === T[n] ? (b.insertBefore(c, y ? null : p), h(l, f)) : T[n].push(c))
    }
    function f(e, t, n, r, s) {
        return m = 0, t = t || "j", i(e) ? a("c" == t ? E : w, e, t, this.i++, n, r, s) : (v.splice(this.i++, 0, e), 1 == v.length && u()), this
    }
    function l() {
        var e = k;
        return e.loader = {load: f, i: 0}, e
    }
    var c = t.documentElement, h = e.setTimeout, p = t.getElementsByTagName("script")[0], d = {}.toString, v = [], m = 0, g = "MozAppearance"in c.style, y = g && !!t.createRange().compareNode, b = y ? c : p.parentNode, c = e.opera && "[object Opera]" == d.call(e.opera), c = !!t.attachEvent && !c, w = g ? "object" : c ? "script" : "img", E = c ? "script" : w, S = Array.isArray || function(e) {
        return"[object Array]" == d.call(e)
    }, x = [], T = {}, N = {timeout: function(e, t) {
            return t.length && (e.timeout = t[0]), e
        }}, C, k;
    k = function(e) {
        function t(e) {
            var e = e.split("!"), t = x.length, n = e.pop(), r = e.length, n = {url: n, origUrl: n, prefixes: e}, i, s, o;
            for (s = 0; s < r; s++)
                o = e[s].split("="), (i = N[o.shift()]) && (n = i(n, o));
            for (s = 0; s < t; s++)
                n = x[s](n);
            return n
        }
        function o(e, i, s, o, u) {
            var a = t(e), f = a.autoCallback;
            a.url.split(".").pop().split("?").shift(), a.bypass || (i && (i = r(i) ? i : i[e] || i[o] || i[e.split("/").pop().split("?")[0]]), a.instead ? a.instead(e, i, s, o, u) : (T[a.url] ? a.noexec = !0 : T[a.url] = 1, s.load(a.url, a.forceCSS || !a.forceJS && "css" == a.url.split(".").pop().split("?").shift() ? "c" : n, a.noexec, a.attrs, a.timeout), (r(i) || r(f)) && s.load(function() {
                l(), i && i(a.origUrl, u, o), f && f(a.origUrl, u, o), T[a.url] = 2
            })))
        }
        function u(e, t) {
            function n(e, n) {
                if (e) {
                    if (i(e))
                        n || (f = function() {
                            var e = [].slice.call(arguments);
                            l.apply(this, e), c()
                        }), o(e, f, t, 0, u);
                    else if (Object(e) === e)
                        for (p in h = function() {
                            var t = 0, n;
                            for (n in e)
                                e.hasOwnProperty(n) && t++;
                            return t
                        }(), e)
                            e.hasOwnProperty(p) && (!n && !--h && (r(f) ? f = function() {
                                var e = [].slice.call(arguments);
                                l.apply(this, e), c()
                            } : f[p] = function(e) {
                                return function() {
                                    var t = [].slice.call(arguments);
                                    e && e.apply(this, t), c()
                                }
                            }(l[p])), o(e[p], f, t, p, u))
                } else
                    !n && c()
            }
            var u = !!e.test, a = e.load || e.both, f = e.callback || s, l = f, c = e.complete || s, h, p;
            n(u ? e.yep : e.nope, !!a), a && n(a)
        }
        var a, f, c = this.yepnope.loader;
        if (i(e))
            o(e, 0, c, 0);
        else if (S(e))
            for (a = 0; a < e.length; a++)
                f = e[a], i(f) ? o(f, 0, c, 0) : S(f) ? k(f) : Object(f) === f && u(f, c);
        else
            Object(e) === e && u(e, c)
    }, k.addPrefix = function(e, t) {
        N[e] = t
    }, k.addFilter = function(e) {
        x.push(e)
    }, k.errorTimeout = 1e4, null == t.readyState && t.addEventListener && (t.readyState = "loading", t.addEventListener("DOMContentLoaded", C = function() {
        t.removeEventListener("DOMContentLoaded", C, 0), t.readyState = "complete"
    }, 0)), e.yepnope = l(), e.yepnope.executeStack = u, e.yepnope.injectJs = function(e, n, r, i, a, f) {
        var l = t.createElement("script"), c, d, i = i || k.errorTimeout;
        l.src = e;
        for (d in r)
            l.setAttribute(d, r[d]);
        n = f ? u : n || s, l.onreadystatechange = l.onload = function() {
            !c && o(l.readyState) && (c = 1, n(), l.onload = l.onreadystatechange = null)
        }, h(function() {
            c || (c = 1, n(1))
        }, i), a ? l.onload() : p.parentNode.insertBefore(l, p)
    }, e.yepnope.injectCss = function(e, n, r, i, o, a) {
        var i = t.createElement("link"), f, n = a ? u : n || s;
        i.href = e, i.rel = "stylesheet", i.type = "text/css";
        for (f in r)
            i.setAttribute(f, r[f]);
        o || (p.parentNode.insertBefore(i, p), h(n, 0))
    }
}(this, document), Modernizr.load = function() {
    yepnope.apply(window, [].slice.call(arguments, 0))
};