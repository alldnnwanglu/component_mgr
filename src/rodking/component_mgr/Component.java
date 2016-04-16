package rodking.component_mgr;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * use as {@ org.springframework.stereotype.Component}
 * ������ע����������� springframework�е�Component
 * 
 * @author rodking
 * @since 0.1
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {
	String value() default "";
}
