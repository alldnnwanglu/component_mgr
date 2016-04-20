package rodking.component_mgr.annotate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * use as {@ org.springframework.stereotype.Component}
 * 自定义组件标签 springframework. Component
 * 
 * @author rodking
 * @since 0.1
 *
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ProtoAction {
	String value() default "";
}
