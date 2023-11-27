package dev.joelfrancisco.abp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.Duration;

@EnableWebMvc
@SpringBootApplication
public class AbpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AbpApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
            }
        };
    }
    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }


    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName("oregon-redis.render.com");
        redisConfig.setPort(6379);
        redisConfig.setPassword("pmV01YGNAM4lLqBmiUJH6D2FBYpJ9UNm");
        redisConfig.setUsername("red-clccj9bmot1c73df20u0");
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfig = JedisClientConfiguration.builder();
        jedisClientConfig.connectTimeout(Duration.ofSeconds(60));
        jedisClientConfig.useSsl();


        return new JedisConnectionFactory(redisConfig, jedisClientConfig.build());
    }
}
