package com.hdu.web.interceptor;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * Created by Flight on 2015/8/1.
 */
public class EncodingFilter extends HttpServlet implements Filter {
    protected  String encoding = null;
    protected FilterConfig filterConfig = null;
    protected  boolean ignore = true;

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");

        String value = filterConfig.getInitParameter("ignore");
        if (value == null)
            this.ignore = true;
        else if(value.equalsIgnoreCase("true"))
            this.ignore = true;
        else
            this.ignore = false;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(ignore || (servletRequest.getCharacterEncoding() == null)) {
            String encoding = selectEncoding(servletRequest);
        }
        if (encoding != null){
            servletRequest.setCharacterEncoding(encoding);
            servletResponse.setCharacterEncoding(encoding);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    private String selectEncoding(ServletRequest servletRequest) {
        return (this.encoding);
    }
}
