package com.example.zuulserver.filter;

import com.example.zuulserver.util.UserContext;
import com.example.zuulserver.util.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserContextFilter implements Filter {
    private static final Logger logger =
            LoggerFactory.getLogger(
                    UserContextFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserContextHolder
                .getContext()
                .setCorrelationId(
                        httpServletRequest
                                .getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getContext().setUserId(
                httpServletRequest
                        .getHeader(UserContext.USER_ID));
        UserContextHolder
                .getContext()
                .setAuthToken(
                        httpServletRequest
                                .getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder
                .getContext()
                .setOrgId(
                        httpServletRequest
                                .getHeader(UserContext.ORG_ID));
        filterChain
                .doFilter(httpServletRequest, servletResponse);
    }
}