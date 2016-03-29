package cc.aisc.ybk;

import cc.aisc.ybk.config.DataSourceConfig;
import cc.aisc.ybk.config.RedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(value = {DataSourceConfig.class, RedisConfig.class})
public class YbkApplication {

	public static void main(String[] args) {
		SpringApplication.run(YbkApplication.class, args);
	}


}
