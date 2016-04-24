package rodking.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import rodking.component_mgr.annotate.Component;

public class TestClassLoader {
	public static void main(String[] args)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException,
			SecurityException, IllegalArgumentException, InvocationTargetException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class<?> t = loader.loadClass("rodking.util.JaveType");

		if (t.isAnnotationPresent(Component.class)) {
			System.out.println("rodking");
		}

		Object obj1 = t.newInstance();
		Method method1 = t.getMethod("valueOf", new Class[] { boolean.class });
		System.out.println(method1.invoke(obj1, new Object[] { true }));

		Method method3 = t.getMethod("test", new Class[] { boolean.class });
		System.out.println(method3.invoke(obj1, new Object[] { false }));

		// Class<?> t2 = loader.loadClass("rodking.util.JaveType");
		// Object obj2 = t.newInstance();
		// Method method2 = t2.getMethod("test", new Class[] { boolean.class });
		// System.out.println(method2.invoke(t2, new Object[] { true }));

		Class<?> c = loader.loadClass("rodking.util.TestClassLoader");
		if (c.isAnnotationPresent(Component.class)) {

			System.out.println("rodking");
		}

	}

	public void test(String... name) {
		for (String s : name) {
			System.out.println(s);
		}
	}
}
