package rodking.component_mgr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import rodking.component_mgr.annotate.Bean;
import rodking.component_mgr.annotate.Component;
import rodking.component_mgr.annotate.DAO;
import rodking.component_mgr.annotate.ProtoAction;
import rodking.component_mgr.config.GenericBeanDefinition;
import rodking.component_mgr.filter.AnnotationTypeFilter;
import rodking.component_mgr.io.Resource;

/**
 * 类路径下面的 bean 扫描
 * @author rodking
 * @version 1.0
 */
public class ClassPathBeanScanner {
	final Logger log = Logger.getLogger(ClassPathBeanScanner.class);
	public final ClassRecourceLoader resourcePR = new ClassRecourceLoader();

	private final List<AnnotationTypeFilter> includeFilters = new LinkedList<AnnotationTypeFilter>();

	public ClassPathBeanScanner() {
		registerDefaultFilters();
	}

	/**
	 * 注册默认的 filters
	 */
	protected void registerDefaultFilters() {
		// 添加 Component
		includeFilters.add(new AnnotationTypeFilter(Component.class));
		// 添加 DAO
		includeFilters.add(new AnnotationTypeFilter(DAO.class));
		// 添加 Bean
		includeFilters.add(new AnnotationTypeFilter(Bean.class));
		// 添加ProtoAction
		includeFilters.add(new AnnotationTypeFilter(ProtoAction.class));
	}

	/**
	 * 如果是需要预先加载到容器中的对象
	 * 
	 * @param clazz
	 * @return
	 * @throws IOException
	 */
	protected boolean isComponentBean(Class<?> clazz) throws IOException {
		for (AnnotationTypeFilter tf : includeFilters) {
			if (clazz.isAnnotationPresent(tf.getAnnotation()))
				return true;
		}
		return false;
	}

	/**
	 * 遍历 basePackage 下的候选bean,
	 * 并把带过滤标签的bean创建好放到 容器中.
	 * @param basePackage
	 */
	public void initCandidateComponents(String basePackage) {

		Resource[] resources = this.resourcePR.getResources(basePackage);
		for (Resource resource : resources) {
			if (resource.isReadable()) {
				try {

					String className = getClassName(resource, basePackage);
					ClassLoader classLoader = this.resourcePR.getClassLoader();
					GenericBeanDefinition beanDef = new GenericBeanDefinition(className, classLoader);

					if (isComponentBean(beanDef.getLoadClass())) {
						beanDef.setBeanClass(beanDef.getLoadClass().newInstance());
						ApplicationContext.getInstance().addBean(beanDef);
					}

				} catch (Exception e) {

				}

			}
		}
	}

	/**
	 *  扫描包
	 * @param path
	 * @throws IOException
	 */
	public void doScan(String path) throws IOException {
		String[] paths = path.split(";");
		for (String p : paths) {
			log.info("start scan path " + p);
			resourcePR.getResources(p);
			initCandidateComponents(p);
			log.info("end scan path " + p);
		}
	}

	private String getClassName(Resource res, String path) throws IOException {
		String p = res.getFile().getPath().replace("\\", "/");
		p = p.substring(p.indexOf(path), p.length());
		p = p.substring(0, p.lastIndexOf(".class")).replace("/", ".");
		return p;
	}
}
