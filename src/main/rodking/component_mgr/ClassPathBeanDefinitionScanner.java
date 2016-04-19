package rodking.component_mgr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import rodking.component_mgr.config.BeanDefinition;
import rodking.component_mgr.config.GenericBeanDefinition;
import rodking.component_mgr.filter.AnnotationTypeFilter;
import rodking.component_mgr.io.Resource;

public class ClassPathBeanDefinitionScanner {
	public final ResourcePatternResolver resourcePR = new ResourcePatternResolver();

	private final List<AnnotationTypeFilter> includeFilters = new LinkedList<AnnotationTypeFilter>();

	public ClassPathBeanDefinitionScanner() {
		registerDefaultFilters();
	}

	protected void registerDefaultFilters() {
		includeFilters.add(new AnnotationTypeFilter(Component.class));
	}

	protected boolean isComponentBean(Class<?> clazz) throws IOException {
		for (AnnotationTypeFilter tf : includeFilters) {
			if (clazz.isAnnotationPresent(tf.getAnnotation()))
				return true;
		}
		return false;
	}

	public Set<BeanDefinition> findCandidateComponents(String basePackage) {

		Resource[] resources = this.resourcePR.getResources(basePackage);
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				try {

					String className = getClassName(resource, basePackage);
					ClassLoader classLoader = this.resourcePR.getClassLoader();
					GenericBeanDefinition beanDef = new GenericBeanDefinition(className, classLoader);

					if (isComponentBean(beanDef.getLoadClass())) {
						System.out.println("rodking :: " + beanDef.getBeanClassName());
						beanDef.setBeanClass(beanDef.getLoadClass().newInstance());
						ApplicationContext.getInstance().addBean(beanDef);
					}

				} catch (Exception e) {

				}

			}
		}
		return null;
	}

	public void doScan(String path) throws IOException {
		String basePackage = "rodking/util/";

		resourcePR.getResources(path);

		Set<BeanDefinition> candidates = findCandidateComponents(basePackage);

	}

	private String getClassName(Resource res, String path) throws IOException {
		String p = res.getFile().getPath().replace("\\", "/");
		p = p.substring(p.indexOf(path), p.length());
		p = p.substring(0, p.lastIndexOf(".class")).replace("/", ".");
		return p;
	}
}
