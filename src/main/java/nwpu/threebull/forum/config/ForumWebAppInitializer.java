package nwpu.threebull.forum.config;

import nwpu.threebull.forum.config.RootConfig;
import nwpu.threebull.forum.config.WebConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class ForumWebAppInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{RootConfig.class};
    }

    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * @return
     */
    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter c = new CharacterEncodingFilter();
        c.setEncoding("UTF-8");
        c.setForceRequestEncoding(true);
        return new Filter[]{c};
    }
}
