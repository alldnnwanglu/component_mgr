package rodking.component_mgr.filter;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import rodking.component_mgr.config.GenericBeanDefinition;

/**
 * 注释过滤器
 * @author rodking
 * @version 1.2
 */
public class AnnotationTypeFilter implements TypeFilter {
	private final Class<? extends Annotation> annotationType;
	private final List<GenericBeanDefinition> genericBeans = new LinkedList<GenericBeanDefinition>();

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType) {
		this(annotationType, true);
	}

	public AnnotationTypeFilter(Class<? extends Annotation> annotationType, boolean considerMetaAnnotations) {
		this.annotationType = annotationType;
	}

	public Class<? extends Annotation> getAnnotation() {
		return this.annotationType;
	}
	
	public void addBeans(GenericBeanDefinition bean)
	{
		if(null==bean) return; 
		if(genericBeans.contains(bean))
			genericBeans.remove(bean);
		genericBeans.add(bean);
	}

	public List<GenericBeanDefinition> getGenericBeans() {
		return genericBeans;
	}

}
