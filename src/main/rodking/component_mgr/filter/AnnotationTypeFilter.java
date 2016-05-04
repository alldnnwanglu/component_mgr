package rodking.component_mgr.filter;

import java.lang.annotation.Annotation;

/**
 * 注释过滤器
 * @author rodking
 * @version 1.2
 */
public class AnnotationTypeFilter implements TypeFilter {
	private final Class<? extends Annotation> annotationType;

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType) {
		this(annotationType, true);
	}

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations) {
		this.annotationType = annotationType;
	}

	public Class<? extends Annotation> getAnnotation() {
		return this.annotationType;
	}

}
