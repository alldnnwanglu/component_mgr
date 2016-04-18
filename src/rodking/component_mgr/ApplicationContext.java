package rodking.component_mgr;

import java.util.HashMap;
import java.util.Map;

import rodking.component_mgr.config.GenericBeanDefinition;

public class ApplicationContext {
	private Map<String, GenericBeanDefinition> contexts = new HashMap<String, GenericBeanDefinition>();
	private static ApplicationContext instance = new ApplicationContext();

	/**
	 * »î¶¯ app ÄÚÈÝ
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

		System.out.println(bean.getSimpleName());
	}

}
