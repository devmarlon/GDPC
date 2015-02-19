package com.gardnerdenver.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gardnerdenver.model.FactoryUserItem;
import java.util.Date;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class UserCheckFilter
 */
//@WebFilter(filterName = "LoginCheckFilter", urlPatterns = {"*.xhtml"})
public class LoginCheckFilter extends AbstractFilter implements Filter {

    private static List<String> allowedURIs;

    /**
     * @param fConfig
     * @throws javax.servlet.ServletException
     * @see Filter#init(FilterConfig)
     */
    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        if (allowedURIs == null) {
            allowedURIs = new ArrayList<>();
            allowedURIs.add(fConfig.getInitParameter("loginActionURI"));
            allowedURIs.add("/gdpc/javax.faces.resource/main.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/estilo.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/loginstyle.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/theme.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/primefaces.js.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/primefaces.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/jquery/jquery.js.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/jquery/jquery-plugins.js.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/messages/messages.png.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/images/ui-icons_ffffff_256x240.png.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/fundoLogin.jpg");
            allowedURIs.add("/gdpc/javax.faces.resource/logoGD.png.xhtml");
            allowedURIs.add("/gdpc/fundoLogin.jpg");
            allowedURIs.add("/gdpc/javax.faces.resource/primefaces-extensions.js.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/keyfilter/keyfilter.js.xhtml");

            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/main.css.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/estilo.css.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/loginstyle.css.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/theme.css.xhtml");
            allowedURIs.add("/gdpc/javax.faces.resource/primefaces.js.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/primefaces.css.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/jquery/jquery.js.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/jquery/jquery-plugins.js.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/messages/messages.png.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_ffffff_256x240.png.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/fundoLogin.jpg");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/logoGD.png.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/fundoLogin.jpg");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/primefaces-extensions.js.xhtml");
            allowedURIs.add("/gdpc/pages/public/login.xhtmlgdpc/javax.faces.resource/keyfilter/keyfilter.js.xhtml");

            allowedURIs.add("/fundoLogin.jpg");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/main.css.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/estilo.css.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/loginstyle.css.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/theme.css.xhtml");
            allowedURIs.add("/javax.faces.resource/primefaces.js.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/primefaces.css.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/jquery/jquery.js.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/jquery/jquery-plugins.js.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/messages/messages.png.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_2e83ff_256x240.png.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_38667f_256x240.png.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/images/ui-icons_ffffff_256x240.png.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/fundoLogin.jpg");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/logoGD.png.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/fundoLogin.jpg");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/primefaces-extensions.js.xhtml");
            allowedURIs.add("/pages/public/login.xhtmlgdpc/javax.faces.resource/keyfilter/keyfilter.js.xhtml");

            allowedURIs.add("/javax.faces.resource/theme.css.xhtml");
            allowedURIs.add("/javax.faces.resource/loginstyle.css.xhtml");
            allowedURIs.add("/javax.faces.resource/primefaces.css.xhtml");
            allowedURIs.add("/javax.faces.resource/jquery/jquery.js.xhtml");
            allowedURIs.add("/javax.faces.resource/estilo.css.xhtml");
            allowedURIs.add("/javax.faces.resource/jquery/jquery-plugins.js.xhtml");
            allowedURIs.add("/favicon.ico");

        }
    }

    /**
     * @see Filter#destroy()
     */
    @Override
    public void destroy() {
    }

    /**
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpSession session = req.getSession();

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();
        String url = req.getRequestURL().toString();

        if (session.isNew()) {
            doLogin(request, response, req);
            return;
        }

        FactoryUserItem user = (FactoryUserItem) session.getAttribute("user");

        if (user == null && !allowedURIs.contains(req.getRequestURI())) {
            System.out.println(req.getRequestURI());
            doLogin(request, response, req);
            return;
        }

        if (user != null) {
            String database = user.getUserFactory().getUSU_BANCO();

            req.getSession().setAttribute("database", database);
        }

        chain.doFilter(request, response);
    }
}
