package nwpu.threebull.forum.tool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import nwpu.threebull.forum.entity.Admin;

public class AdminInterceptor implements HandlerInterceptor{
    private final static Log log = LogFactory.getLog(AdminInterceptor.class);

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj,
                                Exception exception) throws Exception {
        log.debug("3.Called after rendering the view");

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView model)
            throws Exception {
        log.debug("2.Called after handler method request completion, before rendering the view");

    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        log.debug("1.Called before handler method");
        // 获取session
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        // 判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (admin != null && admin.getUserName() != null) {
            return true;
        }
        // 不符合条件的转发到登录页面
        response.sendRedirect(request.getContextPath()+"/admin/login");
        return false;
    }
}
