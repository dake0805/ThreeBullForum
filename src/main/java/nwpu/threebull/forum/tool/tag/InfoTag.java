package nwpu.threebull.forum.tool.tag;

import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import nwpu.threebull.forum.entity.User;
import nwpu.threebull.forum.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class InfoTag extends SimpleTagSupport {
    @Override
    public void doTag() throws JspException {
        try {
            HttpSession session = ((PageContext) this.getJspContext()).getSession();
            User user = (User) session.getAttribute("user");
            Admin admin = (Admin) session.getAttribute("admin");

            if (null != user) {
                getJspContext().getOut().println("<li><p class=\"navbar-text\">欢迎，<a href=\"/user/editUser\">" + user.getUserName() + "</a></p></li>");
            } else if (null != admin) {
                getJspContext().getOut().println("<li><p class=\"navbar-text\">欢迎，" + admin.getUserName() + "</p></li>");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
