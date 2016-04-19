package rodking.component_mgr;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;
import rodking.component_mgr.io.Resource;
import rodking.component_mgr.io.UrlResource;
import rodking.component_mgr.util.ClassUtils;

public class ResourcePatternResolver implements ResourceLoader {

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
	 */
	protected Resource[] findAllResource(String path) throws IOException {
		Enumeration<URL> resourceUrls = classLoader.getResources(path);
		Set<Resource> result = new LinkedHashSet<Resource>();
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			Resource resource = new UrlResource(url);
			if (resource.getFile().isDirectory()) {
				File[] fs = resource.getFile().listFiles();
				for (File f : fs) {
					if (f.isDirectory()) {
						Resource[] res = findAllResource(path + f.getName() + "/");
						for (Resource re : res) {
							result.add(re);
						}

					} else
						result.add(new UrlResource(f.toURL()));
				}
			} else
				result.add(new UrlResource(url));
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
