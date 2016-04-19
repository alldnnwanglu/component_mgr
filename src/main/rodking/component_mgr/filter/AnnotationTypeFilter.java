package rodking.component_mgr.filter;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;

/**
 * 注释过滤器
 *
 */
public class AnnotationTypeFilter implements TypeFilter {
	private final Class<? extends Annotation> annotationType;
	private final boolean considerMetaAnnotations;
	private final boolean considerInherited;
	private final boolean considerInterfaces;

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType) {
		this(annotationType, true);
	}

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations) {
		this(annotationType, considerMetaAnnotations, false);
	}

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations,
			boolean considerInterfaces) {
		this.considerInherited = annotationType.isAnnotationPresent(Inherited.class);
		this.considerInterfaces = considerInterfaces;
		this.annotationType = annotationType;
		this.considerMetaAnnotations = considerMetaAnnotations;
	}

	public Class<? extends Annotation> getAnnotation() {
		return this.annotationType;
	}

}
