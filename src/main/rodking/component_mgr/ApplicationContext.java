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
		t.doScan("util/**/*.class");

		// TODO 遍历 contexts
		// TODO 查找是否有属性注入
		for (GenericBeanDefinition bean : contexts.values()) {
			Field[] fields = bean.getDeclaredFields();

			for (Field field : fields) {
				String fieldStr = field.getType().getSimpleName();
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
	 * 通过接口或者父类得到 beans
	 * 
	 * @param type
	 * @return
	 */
	public <T> Map<String, T> getBeansOfType(Class<T> type) {
		return null;
	}

	public GenericBeanDefinition getBeanByName(String key) {
		return contexts.get(key);
	}
}
