package rodking.component_mgr.config;

/**
 * @author rodking
 * 
 */
public interface BeanDefinition {
	/** bean get ������ */
	String getBeanClassName();

	/** bean set ������ */
	void setBeanClassName(String beanClassName);

	String getSimpleName();
}
