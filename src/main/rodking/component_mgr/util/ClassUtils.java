package rodking.component_mgr.util;

public class ClassUtils {
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

	public static String convertResourcePathToClassName(String resourcePath) {
		return resourcePath.replace('/', '.');
	}
}
