package nwpu.threebull.forum.tool.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

/**
 * foot输出信息的标签
 *
 * @author ThreeBullForumTeam
 * @vwesion 1.0
 */
@Component
public class FooterTag extends SimpleTagSupport {

    private static String name = "ThreeBullForum";

    private static String company = "ThreeBullForum出品";

    private static String slogan = "I am so vegetable";

    /**
     * 输出foot信息
     *
     * @throws JspException
     */
    @Override
    public void doTag() throws JspException {
        try {
            getJspContext().getOut().println("<p><a href=\"http://fly.layui.com/\" target=\"_blank\">"
                    + name + "</a> 2019 © <a href=\"https://github.com/dake0805/ThreeBullForum\" target=\"_blank\">"
                    + company + "</a></p><p><a href=\"#\" target=\"_blank\">"
                    + slogan + "</a></p>");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
