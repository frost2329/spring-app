package by.frost.annotation;

import by.frost.ApplicationRunner;
import by.frost.integration.TestApplicationRunner;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(classes = {TestApplicationRunner.class, ApplicationRunner.class})
@Transactional
public @interface IT {
}
