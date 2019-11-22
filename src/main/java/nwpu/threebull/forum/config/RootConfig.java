package nwpu.threebull.forum.config;

import java.util.regex.Pattern;

import nwpu.threebull.forum.config.DataConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;


/**
 * 配置类，用于管理ContextLoadListener创建的上下文的bean
 *
 * @author wben
 * @version v1.0
 */
// 定义为配置类
@Configuration
// 引入数据库配置类
@Import(DataConfig.class)
// 定义Spring 扫描的包名，采用自定义扫描类WebPackage
@ComponentScan(basePackages = {"nwpu.threebull.forum"})
//, excludeFilters = {@Filter(type = FilterType.CUSTOM, value = RootConfig.WebPackage.class)}
public class RootConfig {
}
