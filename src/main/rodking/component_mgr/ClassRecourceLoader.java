package rodking.component_mgr;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import rodking.component_mgr.io.Resource;
import rodking.component_mgr.io.UrlResource;
import rodking.component_mgr.util.ClassUtils;

/**
 * 类资源装载器
 * @author rodking
 * @version 1.0
 */
public class ClassRecourceLoader implements ResourceLoader {

	public final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * 加载所有的Resource
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 * @throws URISyntaxException 
	 */
	protected Resource[] findAllResource(String path) throws IOException, URISyntaxException {
		Enumeration<URL> resourceUrls = classLoader.getResources(path);
		Set<Resource> result = new LinkedHashSet<Resource>();
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			Resource resource = new UrlResource(url.toURI());
			if (resource.getFile().isDirectory()) {
				File[] fs = resource.getFile().listFiles();
				for (File f : fs) {
					if (f.isDirectory()) {
						Resource[] res = findAllResource(path + f.getName() + "/");
						for (Resource re : res) {
							result.add(re);
						}

					} else
						result.add(new UrlResource(f.toURI()));
				}
			} else
				result.add(new UrlResource(url.toURI()));
		}

		return result.toArray(new Resource[result.size()]);
	}

	/**
	 * 加载资源
	 * 
	 * @param path
	 * @return
	 */
	public Resource[] getResources(String path) {
		Resource[] resources = null;
		try {
			resources = findAllResource(path);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resources;
	}

}
