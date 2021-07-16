package codes.matthem.mybatisexample.runners;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.api.VerboseProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.List;

public class MybatisGeneratorRunnerWithXML {
    public static void main(String[] args) {
        List<String> warnings = List.of();

        try {
            Path path = Path.of("/Users/matthemingway/IdeaProjects/mybatisexample/MyBatis/h2-1.4.200.jar");

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(path.toFile());

            DefaultShellCallback callback = new DefaultShellCallback(true);
            ProgressCallback pcallback = new VerboseProgressCallback();

            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(pcallback);
        } catch (XMLParserException xmlpe) {
            xmlpe.printStackTrace();
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
}
