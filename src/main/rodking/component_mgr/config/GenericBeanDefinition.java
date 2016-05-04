package rodking.component_mgr.config;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;


/**
 * 普通bean定义
 * 
 * @author rodking
 * @des 0.1
 */
public class GenericBeanDefinition implements BeanDefinition {

	/** bean 类名 */
	private String beanClassName;

	/** 实例化的对象 */
	private volatile Object beanClass;

	/** 用classLoader加载的类 */
	private Class<?> loadClass;

	/** 文件名称 */
	private String simpleName;

	public GenericBeanDefinition(String className, ClassLoader classLoader) throws ClassNotFoundException {
		this.beanClassName = className;
		Class<?> resolvedClass = classLoader.loadClass(className);
		this.loadClass = resolvedClass;
		this.simpleName = resolvedClass.getSimpleName();
	}

	@Override
	public String getBeanClassName() {
		return this.beanClassName;
	}

	public void setBeanClass(Object beanClass) {
		this.beanClass = beanClass;
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
	 * classloader 加载的类,没有实例化
	 * @return
	 */
	public Class<?> getLoadClass() {
		return this.loadClass;
	}

	/**
	 * 获得已经初始化的 bean
	 * @return
	 */
	public Object getBeanClass() {
		return beanClass;
	}
	
	/**
	 * classloader 加载的类 的公有注释
	 * @return
	 */
	public Annotation[] getDeclaredAnnotations()
	{
		return loadClass.getDeclaredAnnotations();
	}
	
	/**
	 * classloader 加载的类 的公有的方法
	 * @return
	 */
	public Field[] getDeclaredFields()
	{
		return loadClass.getDeclaredFields();
	}
}
