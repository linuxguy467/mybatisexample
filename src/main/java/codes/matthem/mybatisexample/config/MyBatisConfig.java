package codes.matthem.mybatisexample.config;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;

@Configuration
@MapperScan({"codes.matthem.mybatisexample.mappers"})
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory getSessionFactory() {
        try (InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml")) {
            return new SqlSessionFactoryBuilder().build(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
