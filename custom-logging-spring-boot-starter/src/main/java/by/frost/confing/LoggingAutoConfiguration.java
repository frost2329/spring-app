package by.frost.confing;

import by.frost.aop.FirstAspect;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties(LoggingProperties.class)
@ConditionalOnClass(LoggingProperties.class)
@ConditionalOnProperty(prefix = "app.service.logging", name = "enabled", havingValue = "true")
public class LoggingAutoConfiguration {

    @Bean
    public FirstAspect firstAspect() {
        return new FirstAspect();
    }

    @PostConstruct
    public void init() {
        log.info("LoggingAutoConfiguration connected {}", this);
    }
}
