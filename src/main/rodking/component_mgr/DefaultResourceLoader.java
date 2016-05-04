package rodking.component_mgr;

import rodking.component_mgr.io.Resource;
import rodking.component_mgr.util.ClassUtils;

/**
 * 默认的资源装载器
 * 
 * @author rodking
 * @version 1.0
 */
public class DefaultResourceLoader implements ResourceLoader {

	@SuppressWarnings("unused")
	private ClassLoader classLoader;

	public DefaultResourceLoader() {
		this.classLoader = ClassUtils.getDefaultClassLoader();
	}

	@Override
	public ClassLoader getClassLoader() {
		return null;
	}

	public DefaultResourceLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public Resource getResource(String location) {

		return null;
	}

}
