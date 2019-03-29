package util;

import constant.Constant;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.context.FacesContext;
import javax.faces.view.facelets.FaceletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class HTTPUtil {

    private static final Logger log = LoggerFactory.getLogger(HTTPUtil.class);

    public static User getCurrentUser() {
        return (User) HTTPUtil.getSession().getAttribute(Constant.CURRENT_USER);
    }

    public static void setCurrentUser(User user) {
        HTTPUtil.getSession().setAttribute(Constant.CURRENT_USER, user);
    }

    public static String toUrl(String url) {
        return toUrl(url, true);
    }

    public static String toUrl(String url, boolean redirect) {
        log.debug("forwarding to url: {}, with redirect = {}", url, redirect);
        if (redirect) {
            if (url.contains("?")) {
                return url + "&faces-redirect=true";
            } else {
                return url + "?faces-redirect=true";
            }
        }
        return url;
    }

    public static String returnUrlBack() {
        String url = (String) HTTPUtil.getSession().getAttribute(Constant.RETURN_URL);
        if (url != null) {
            log.debug("return url={}", url);
            HTTPUtil.getSession().setAttribute(Constant.RETURN_URL, null);
            try {
                ((HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse()).sendRedirect(url);
                return url + "?faces-redirect=true";
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "index?faces-redirect=true";
    }

    public static void setReturnBackUrl(String backUrl) {
        log.debug("to login/register page with backUrl={}", backUrl);//"returnUrlBack"
        if (backUrl != null) {
            HTTPUtil.getSession().setAttribute(Constant.RETURN_URL, backUrl);
        }
    }

    public static String getUIParam(String paramName) {
        FaceletContext faceletContext = (FaceletContext) FacesContext.getCurrentInstance().getAttributes().get(FaceletContext.FACELET_CONTEXT_KEY);
        return (String) faceletContext.getAttribute(paramName);
    }

    public static HttpSession getSession() {
        //getCurrentInstance返回null：只有进入了Faces Servlet(web.xml)才能获取到，因此在Filter里不能调这里的方法
        //http://blog.csdn.net/duankaige/article/details/5977227
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
    }
}
