package nwpu.threebull.forum.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;


import javax.sql.DataSource;
import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackages = {"nwpu.threebull.forum"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class DataConfig {
    // 读取数据库配置文件
    private static ResourceBundle resource = ResourceBundle.getBundle("dbconfig");

    //获取数据库配置
    private static String DRIVER = resource.getString("DRIVER");
    private static String URL = resource.getString("URL");
    private static String DBNAME = resource.getString("DBNAME");
    private static String DBCONFIG = resource.getString("DBCONFIG");
    private static String USER = resource.getString("USER");
    private static String PASSWORD = resource.getString("PASSWORD");
//
//    private static String DRIVER = "com.mysql.jdbc.Driver";
//    private static String URL = "jdbc:mysql://localhost:3307/";
//    private static String DBNAME = "threebullforum";
//    private static String DBCONFIG = "?useSSL=false&serverTimezone=UTC";
//    private static String USER = "root";
//    private static String PASSWORD = "123456";

    /**
     * 数据源设置，采用MySQL数据库，此处运用了数据源连接池BasicDataSource
     *
     * @return
     */
    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName(DRIVER); //jdbc驱动
        //数据库url
        dataSource.setUrl(URL + DBNAME + DBCONFIG);
        dataSource.setUsername(USER);
        dataSource.setPassword(PASSWORD);
//        dataSource.setInitialSize(5);
//        dataSource.setMaxTotal(10);
//        dataSource.setRemoveAbandonedTimeout(2000);
        return dataSource;
    }

    /**
     * jdbc模板设置，采用spring默认的JdbcTemplate
     *
     * @param dataSource
     * @return
     */
    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}
