package mcp.myclassplanner.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(value = "mcp.myclassplanner", annotationClass = Mapper.class)
public class MybatisConfig {
}
