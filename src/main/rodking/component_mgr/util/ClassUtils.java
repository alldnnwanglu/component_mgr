package rodking.component_mgr.util;

public class ClassUtils {
	
	/**
	 * 获得并创建默认的类加载器
	 * @return
	 */
	public static ClassLoader getDefaultClassLoader() {
		ClassLoader cl = null;
		try {
			cl = Thread.currentThread().getContextClassLoader();
		} catch (Throwable ex) {
		}

		if (cl == null) // if is null use default
			cl = ClassUtils.class.getClassLoader();
		return cl;
	}
}
