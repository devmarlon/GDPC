package com.gardnerdenver.filter;

import com.gardnerdenver.bean.UserItemFactoryBean;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.gardnerdenver.model.FactoryUserItem;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;

public class DistributorUserPagesFilter extends AbstractFilter implements Filter {

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        FactoryUserItem user = (FactoryUserItem) req.getSession(true).getAttribute("user");

        if (user == null) {
            return;
        }
        if (!user.getUserFactory().isDis()) {
            accessDenied(request, response, req);
            return;
        }

        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig arg0) throws ServletException {

    }
}
