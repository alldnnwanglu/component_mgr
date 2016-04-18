package rodking.component_mgr.config;

/**
 * @author rodking
 * 
 */
public interface BeanDefinition {
	/** bean get 的类名 */
	String getBeanClassName();

	/** bean set 的类名 */
	void setBeanClassName(String beanClassName);

	String getSimpleName();
}
