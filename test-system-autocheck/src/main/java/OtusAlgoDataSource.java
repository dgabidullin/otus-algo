import org.junit.jupiter.params.provider.ArgumentsSource;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ArgumentsSource(OtusAlgoArgumentProvider.class)
public @interface OtusAlgoDataSource {

    String path();

    boolean twoInputArguments() default false;

    boolean twoOutputStringArguments() default false;
}
