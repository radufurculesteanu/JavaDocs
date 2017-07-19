package ro.teamnet.zth.web;

import ro.teamnet.zth.file.LogFileWriter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * Created by Radu.Furculesteanu on 7/19/2017.
 */
public class HeadersLogFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        chain.doFilter(req, resp);
        HttpServletRequest request = (HttpServletRequest)req;
        Enumeration<String> names = request.getHeaderNames();
        while(names.hasMoreElements())
        {
            String name = names.nextElement();
            LogFileWriter.logHeader(name,request.getHeader(name));
        }
        LogFileWriter.logHeader("header","value");


    }

    public void init(FilterConfig config) throws ServletException {

    }

}
