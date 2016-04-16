package rodking.component_mgr;

/**
 * @author rodking
 * @see	1.0
 *
 */
public interface ResourceLoader {
	/**
	 * 
	 * @return 返回一个不为空的类加载器
	 */
	ClassLoader getClassLoader();
}
