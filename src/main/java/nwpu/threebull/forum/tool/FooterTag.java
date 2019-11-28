package nwpu.threebull.forum.tool;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import org.springframework.stereotype.Component;

@Component
public class FooterTag extends SimpleTagSupport {

    private static String name = "ThreeBullForum";

    private static String company = "ThreeBullForum出品";

    private static String slogan = "I am so vegetable";

    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().println("<p><a href=\"http://fly.layui.com/\" target=\"_blank\">"
                    + name + "</a> 2019 © <a href=\"https://github.com/dake0805/ThreeBullForum\" target=\"_blank\">"
                    + company + "</a></p><p><a href=\"#\" target=\"_blank\">"
                    + slogan + "</a></p>");
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
