package rodking.component_mgr.config;

/**
 * bean 定义
 * @author rodking
 * @see 0.1
 * 
 */
public interface BeanDefinition {
	/** bean get bean 类名 */
	String getBeanClassName();

	/** bean set bean 类名 */
	void setBeanClassName(String beanClassName);
	
	/** bean set bean 文件名 */
	String getSimpleName();
}
