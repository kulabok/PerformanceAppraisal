package Controllers;

import javax.servlet.*;
import java.io.IOException;

/**
 * This filter set encoding to UTF-8 and transfers control to MainController.
 * @author Viktor Bolshakov on 30.01.16.
 */
public class EncodingFilter implements Filter {
    private String encoding;

    @Override
    public void init (FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
