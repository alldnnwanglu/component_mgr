package rodking.component_mgr;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import rodking.component_mgr.config.GenericBeanDefinition;

/**
 * 容器启动入口
 * 
 * @author rodking
 * @SEE 0.1
 */
public class ApplicationContext {
	private Map<String, GenericBeanDefinition> contexts = new HashMap<String, GenericBeanDefinition>();
	private static ApplicationContext instance = new ApplicationContext();

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

	public void addBean(GenericBeanDefinition bean) {
		if (contexts.containsKey(bean.getSimpleName()))
			contexts.remove(bean.getSimpleName());
		contexts.put(bean.getSimpleName(), bean);
	}

	public void init() throws Exception {
		ClassPathBeanDefinitionScanner t = new ClassPathBeanDefinitionScanner();
		// TODO 每个包单点加测试
		t.doScan("rodking/util/;rodking/server/");
		// TODO 遍历说有包
		// t.doScan("rodking/");

		for (GenericBeanDefinition bean : contexts.values()) {
			Field[] fields = bean.getDeclaredFields();

			for (Field field : fields) {
				String fieldStr = field.getType().getSimpleName();
				// 如果有@Resource 标签的字段,就不实例化好的对象注册进去
				if (field.isAnnotationPresent(Resource.class)) {
					GenericBeanDefinition b = contexts.get(fieldStr);
					field.setAccessible(true);
					field.set(bean.getBeanClass(), b.getBeanClass());
					field.setAccessible(false);
				}
			}

		}
	}

	/**
	 * 获得bean
	 * 
	 * @param type
	 * @return
	 */
	public Object getBeanOfType(Class<?> type) {
		String key = type.getSimpleName();
		GenericBeanDefinition bean = contexts.get(key);
		if (null != bean)
			return bean.getBeanClass();
		return null;
	}

	/**
	 * 获得继承了 type 的 beans
	 * 
	 * @param type
	 * @return
	 */
	public <T> Map<String, T> getBeansOfType(Class<T> type) throws Exception {
		Map<String, T> map = new HashMap<String, T>();
		for (GenericBeanDefinition bean : contexts.values()) {
			// 判断 bean 是否继承了type
			if (type.isAssignableFrom(bean.getLoadClass())) {
				map.put(bean.getSimpleName(), (T) bean.getBeanClass());
			}
		}
		return map;
	}

	/**
	 * 获得所有带 annotationType 注释的 beans
	 * 
	 * @param annotationType
	 * @return
	 */
	public Map<String, Object> getBeansWithAnnotation(Class<? extends Annotation> annotationType) {
		Map<String, Object> map = new HashMap<String, Object>();
		for (GenericBeanDefinition bean : contexts.values()) {
			// 判断 bean 是否带有 annotationType 这个注释
			if (bean.getLoadClass().isAnnotationPresent(annotationType)) {
				map.put(bean.getSimpleName(), bean.getBeanClass());
			}
		}
		return map;
	}

}
