package rodking.component_mgr;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
	 * init 初始化包<br>
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
					
					addCandidateComponents(beanDef);

				} catch (Exception e) {

				}

			}
		}
	}

	/**
	 * add 添加候选对象<br>
	 * 把不同类型的bean 放到不同的分类中,
	 * 以便以后按照分类的优先级加载
	 * @param beanDef
	 */
	private void addCandidateComponents(GenericBeanDefinition beanDef) {
		Class<?> clazz = beanDef.getLoadClass();
		for (AnnotationTypeFilter tf : includeFilters) {
			if (clazz.isAnnotationPresent(tf.getAnnotation()))
				tf.addBeans(beanDef);
		}
	}

	/**
	 *  扫描包
	 * @param path
	 * @throws IOException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	public void doScan(String path) throws IOException, InstantiationException, IllegalAccessException {
		String[] paths = path.split(";");
		for (String p : paths) {
			log.info("start scan path " + p);
			resourcePR.getResources(p);
			initCandidateComponents(p);
		
			log.info("end scan path " + p);
		}
		createCandidateComponents();
	}

	private void createCandidateComponents() throws InstantiationException, IllegalAccessException {
		for (AnnotationTypeFilter tf : includeFilters) 
		{
			List<GenericBeanDefinition> beans= tf.getGenericBeans();
			
			for(GenericBeanDefinition beanDef:beans)
			{
				beanDef.setBeanClass(beanDef.getLoadClass().newInstance());
				ApplicationContext.getInstance().addBean(beanDef);
			}
		}
	}

	private String getClassName(Resource res, String path) throws IOException {
		String p = res.getFile().getPath().replace("\\", "/");
		p = p.substring(p.indexOf(path), p.length());
		p = p.substring(0, p.lastIndexOf(".class")).replace("/", ".");
		return p;
	}
}
