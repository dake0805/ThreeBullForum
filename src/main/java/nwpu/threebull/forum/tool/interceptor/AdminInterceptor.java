package nwpu.threebull.forum.tool.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerInterceptor;

import nwpu.threebull.forum.entity.Admin;

/**
 * 利用拦截器对管理员访问路径进行登录校验
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
public class AdminInterceptor implements HandlerInterceptor {

    private final static Log log = LogFactory.getLog(AdminInterceptor.class);

    /**
     * 在控制器类的处理方法被调用之前检查session中是否存在admin
     *
     * @param request
     * @param response
     * @param arg2
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
        //log.debug("1.Called before handler method");
        // 获取session
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute("admin");
        // 判断session中是否有用户数据，如果有，则返回true，继续向下执行
        if (admin != null && admin.getUserName() != null) {
            return true;
        }
        // 不符合条件的转发到登录页面
        response.sendRedirect(request.getContextPath() + "/admin/login");
        return false;
    }
}
