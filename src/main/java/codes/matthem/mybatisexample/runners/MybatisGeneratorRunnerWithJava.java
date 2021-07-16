package codes.matthem.mybatisexample.runners;

import org.mybatis.generator.api.*;
import org.mybatis.generator.config.*;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class MybatisGeneratorRunnerWithJava {
    public static void main(String[] args) {
        List<String> warnings = List.of();

        try {
            Configuration config = buildConfiguration();

            DefaultShellCallback callback = new DefaultShellCallback(true);
            ProgressCallback pcallback = new VerboseProgressCallback();

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(pcallback);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (InvalidConfigurationException ice) {
            ice.printStackTrace();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static Configuration buildConfiguration() {
        Configuration config = new Configuration();
        config.addClasspathEntry("/Users/matthemingway/IdeaProjects/mybatisexample/MyBatis/h2-1.4.200.jar");

        Context context = getContext();

        config.addContext(context);

        return config;
    }

    private static Context getContext() {
        Context context = new Context(ModelType.FLAT);
        context.setId("H2EmbeddedContext");
        context.setTargetRuntime("MyBatis3");

        // <jdbcConnection>
        JDBCConnectionConfiguration jdbcconconfig = new JDBCConnectionConfiguration();
        jdbcconconfig.setDriverClass("org.h2.Driver");
        jdbcconconfig.setConnectionURL("jdbc:h2:~/employee");
        jdbcconconfig.setUserId("sa");
        jdbcconconfig.setPassword("");

        // <javaTypeResolver>
        JavaTypeResolverConfiguration jtrconfig = new JavaTypeResolverConfiguration();
        jtrconfig.addProperty("forceBigDecimals", "false");
        jtrconfig.addProperty("useJSR310Types", "true");

        // <javaModelGenerator>
        JavaModelGeneratorConfiguration jmgconfig = new JavaModelGeneratorConfiguration();
        jmgconfig.setTargetPackage("codes.matthem.mybatisexample.models");
        jmgconfig.setTargetProject("/Users/matthemingway/IdeaProjects/mybatisexample/src/main/java");
        jmgconfig.addProperty("constructorBased", "true");
        jmgconfig.addProperty("enableSubPackages", "true");
        jmgconfig.addProperty("trimStrings", "true");

        // <sqlMapGenerator>
        SqlMapGeneratorConfiguration sqlmapgconfig = new SqlMapGeneratorConfiguration();
        sqlmapgconfig.setTargetPackage("codes.matthem.mybatisexample.mappers");
        sqlmapgconfig.setTargetProject("/Users/matthemingway/IdeaProjects/mybatisexample/src/main/java");
        sqlmapgconfig.addProperty("enableSubPackages", "true");

        // <javaClientGenerator>
        JavaClientGeneratorConfiguration jcgconfig = new JavaClientGeneratorConfiguration();
        jcgconfig.setConfigurationType("XMLMAPPER");
        jcgconfig.setTargetPackage("codes.matthem.mybatisexample.mappers");
        jcgconfig.setTargetProject("/Users/matthemingway/IdeaProjects/mybatisexample/src/main/java");
        jcgconfig.addProperty("enableSubPackages", "true");

        // tables with <table>
        TableConfiguration tblConfig = getTableConfig(context);

        // append to context
        context.setJdbcConnectionConfiguration(jdbcconconfig);
        context.setJavaTypeResolverConfiguration(jtrconfig);
        context.setJavaModelGeneratorConfiguration(jmgconfig);
        context.setSqlMapGeneratorConfiguration(sqlmapgconfig);
        context.setJavaClientGeneratorConfiguration(jcgconfig);
        context.addTableConfiguration(tblConfig);

        return context;
    }

    private static TableConfiguration getTableConfig(Context context) {
        TableConfiguration tbConfig = new TableConfiguration(context);
        tbConfig.setTableName("%");

        return tbConfig;
    }
}
