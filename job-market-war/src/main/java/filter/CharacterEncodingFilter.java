package filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by lin on 2016-09-07-007.
 * 字符编码转换
 *
 * @link http://www.blogjava.net/algz/articles/201833.html
 */
public class CharacterEncodingFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private String encoding = null;
    private FilterConfig filterConfig = null;

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        log.trace("进入编码拦截方法");
        request.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        log.debug("filter url:{}?{}", req.getRequestURI(), req.getQueryString());
        log.debug("设置编码为{}", request.getCharacterEncoding());
        chain.doFilter(request, response);
        log.trace("退出编码拦截方法");
    }

    public void destroy() {
        this.encoding = null;
        this.filterConfig = null;
    }

    protected String selectEncoding(ServletRequest request) {
        return (this.encoding);
    }
}
