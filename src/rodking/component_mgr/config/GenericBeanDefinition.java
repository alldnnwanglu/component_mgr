package rodking.component_mgr.config;

/**
 * 一般bean定义
 * 
 * @author rodking
 * @des 0.1
 */
public class GenericBeanDefinition implements BeanDefinition {

	/** 用来加载类名称，包含包路径 */
	private String beanClassName;
	/** 使用类加载器加载后,包含的类 */
	private volatile Object beanClass;

	private Class<?> loadClass;
	/** 类名称 */
	private String simpleName;

	@Override
	public String getBeanClassName() {
		return this.beanClassName;
	}

	@Override
	public void setBeanClassName(String beanClassName) {
		this.beanClassName = beanClassName;

	}

	@Override
	public String getSimpleName() {
		return simpleName;
	}

	/**
	 * 初始化bean
	 * 
	 * @param classLoader
	 * @return
	 * @throws ClassNotFoundException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public Class<?> initBean(ClassLoader classLoader) throws ClassNotFoundException {
		String className = getBeanClassName();
		if (className == null) {
			return null;
		}

		Class<?> resolvedClass = classLoader.loadClass(className);
		loadClass = resolvedClass;
		return resolvedClass;
	}

	/**
	 * 加载 bean
	 * 
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public void onInit() throws InstantiationException, IllegalAccessException {
		beanClass = loadClass.newInstance();
		simpleName = this.beanClass.getClass().getSimpleName();
	}

	public Object getBeanClass() {
		return beanClass;
	}

}
