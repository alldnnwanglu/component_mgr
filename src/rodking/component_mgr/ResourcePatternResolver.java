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

	protected Resource[] findAllResource(String path) throws IOException {
		Enumeration<URL> resourceUrls = classLoader.getResources(path);
		Set<Resource> result = new LinkedHashSet<Resource>();
		while (resourceUrls.hasMoreElements()) {
			URL url = resourceUrls.nextElement();
			result.add(new UrlResource(url));
		}
		return result.toArray(new Resource[result.size()]);
	}

	public void getResources(String path) {
		Resource[] res;
		try {
			res = findAllResource(path);

			for (Resource re : res) {
				if (re.getFile().isDirectory()) {
					File[] fs = re.getFile().listFiles();

					for (File f : fs) {
						System.out.println(f.getPath());
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
