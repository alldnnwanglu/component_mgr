package rodking.component_mgr.config;

/**
 * һ��bean����
 * 
 * @author rodking
 * @des 0.1
 */
public class GenericBeanDefinition implements BeanDefinition {

	/** �������������ƣ�������·�� */
	private String beanClassName;
	/** ʹ������������غ�,�������� */
	private volatile Object beanClass;

	private Class<?> loadClass;
	/** ������ */
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
	 * ��ʼ��bean
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
	 * ���� bean
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
