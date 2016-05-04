package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * @author Administrator
 *
 */
public interface Resource {
	
	/**
	 * 获得资源文件
	 * @return
	 * @throws IOException
	 */
	File getFile() throws IOException;

	/**
	 * 资源名称
	 * @return
	 */
	String getFilename();

	/**
	 * 资源是否否打开
	 * @return
	 */
	boolean isOpen();
	
	/**
	 * 资源描述
	 * @return
	 */
	String getDescription(); 
	
	/**
	 * 资源是否可读
	 * @return
	 */
	boolean isReadable();

	/**
	 * 资源的url
	 * @return
	 * @throws IOException
	 */
	URL getURL() throws IOException;
}
