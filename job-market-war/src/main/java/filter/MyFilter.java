package filter;

import constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lin on 2016-09-04-004.
 * 未登录不能访问仪表盘
 */
public class MyFilter implements Filter {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("初始化Filter");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.trace("进入仪表盘拦截方法");
        HttpServletRequest req = (HttpServletRequest) request;
        if (req.getSession().getAttribute(Constant.CURRENT_USER) == null) {
            log.debug("未登录！");
            ((HttpServletResponse) response).sendRedirect(request.getServletContext().getContextPath() + "/login.xhtml");
        } else {
            chain.doFilter(request, response);
        }
        log.trace("退出仪表盘拦截方法");
    }

    @Override
    public void destroy() {
        log.debug("销毁Filter");
    }
}
