package rodking.component_mgr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;

import rodking.component_mgr.config.GenericBeanDefinition;

/**
 * 应用容器
 * <p>
 * 
 * @author rodking
 * @version 1.0
 */
public class ApplicationContext {
	final Logger log = Logger.getLogger(ApplicationContext.class);
	private Map<String, GenericBeanDefinition> contexts = new HashMap<String, GenericBeanDefinition>();
	private static ApplicationContext instance = new ApplicationContext();
	private GenericBeanDefinition bean;

	/**
	 * app content 容器单例对象
	 * 
	 * @return
	 */
	public static ApplicationContext getInstance() {
		if (instance == null)
			instance = new ApplicationContext();
		return instance;
	}

	/**
	 * 向容器中添加 bean
	 * <p>
	 * 如果 bean 在容器中已经存在,就会覆盖以前有的bean
	 * 
	 * @param bean
	 *            将要添加的bean
	 */
	public void addBean(GenericBeanDefinition bean) {
		if (contexts.containsKey(bean.getSimpleName()))
			contexts.remove(bean.getSimpleName());
		contexts.put(bean.getSimpleName(), bean);
		log.info("add bean [" + bean.getBeanClassName() + "]");
	}

	/**
	 * @throws Exception
	 */
	public void init() throws Exception {
		log.info("start load bean in appcontext ...");
		ClassPathBeanScanner t = new ClassPathBeanScanner();
		// TODO 每个包单点加测试
		// t.doScan("rodking/util/;rodking/server/;rodking/core/;rodking/logic/;rodking/config/;rodking/game/");
		// TODO 遍历所有包
		t.doScan("rodking/");
		log.info("start IOC all fields...");
		for (GenericBeanDefinition bean : contexts.values()) {
			Field[] fields = bean.getDeclaredFields();

			for (Field field : fields) {
				String fieldStr = field.getType().getSimpleName();
				// 如果有@Resource 标签的字段,就不实例化好的对象注册进去
				if (field.isAnnotationPresent(Resource.class)) {
					GenericBeanDefinition b = contexts.get(fieldStr);
					boolean isAccess = field.isAccessible();
					field.setAccessible(true);
					field.set(bean.getBeanClass(), b.getBeanClass());
					field.setAccessible(isAccess);
				}
			}

		}
		log.info("end IOC all fields...");
		log.info("end load beans...");
	}

	/**
	 * 通过类名获得 bean
	 * 
	 * @param <T>
	 * 
	 * @param type
	 *            类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T getBeanOfType(Class<T> type) {
		String key = type.getSimpleName();
		bean = contexts.get(key);
		if (null != bean)
			return (T) bean.getBeanClass();
		return null;
	}

	/**
	 * 通过父类得到所有继承或者实现该父类接口的标签类
	 * 
	 * @param fatherType
	 *            父类类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getBeansOfType(Class<T> fatherType) throws Exception {
		Map<String, T> map = new HashMap<String, T>();
		for (GenericBeanDefinition bean : contexts.values()) {
			// 判断 bean 是否继承了type
			if (fatherType.isAssignableFrom(bean.getLoadClass())) {
				map.put(bean.getSimpleName(), (T) bean.getBeanClass());
			}
		}
		return map;
	}

	/**
	 * 获得所有带 annotationType 注释的 beans
	 * 
	 * @param annotationType
	 *            注释类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Map<String, T> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		Map<String, T> map = new HashMap<String, T>();
		for (GenericBeanDefinition bean : contexts.values()) {
			// 判断 bean 是否带有 annotationType 这个注释
			if (bean.getLoadClass().isAnnotationPresent(annotationType)) {
				map.put(bean.getSimpleName(), (T) bean.getBeanClass());
			}
		}
		return map;
	}

}
