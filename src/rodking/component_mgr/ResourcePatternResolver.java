package rodking.component_mgr;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Reference;

import rodking.component_mgr.io.Resource;
import rodking.component_mgr.io.UrlResource;
import rodking.component_mgr.util.ClassUtils;
import rodking.component_mgr.util.ResourceUtils;

public class ResourcePatternResolver implements ResourceLoader {

	public final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * ����·��ƥ����Դ
	 * 
	 * @param locationPattern
	 * @return
	 * @throws IOException
	 */
	protected Resource[] findPathMatchingResources(String locationPattern) throws IOException {
		String rootDirPath = locationPattern;
		Resource[] rootDirResources = getResources(rootDirPath);
		Set<Resource> result = new LinkedHashSet<Resource>(16);
		for (Resource rootDirResource : rootDirResources) {
			rootDirResource = resolveRootDirResource(rootDirResource);
			// ��� jar
			if (isJarResource(rootDirResource)) {
				// jar resource ���뵽�б���
				result.addAll(doFindPathMatchingJarResources(rootDirResource,""));//null);
			}else if(rootDirResource.getURL().getProtocol().startsWith(ResourceUtils.URL_PROTOCOL_VFS))
			{
				result.addAll(null);
			}
			else 
				result.addAll(null);
		}
		
		return result.toArray(new Resource[result.size()]);

	}

	/**
	 * �ҵ�·��ƥ��� jar
	 * @param rootDirResource
	 * @param string
	 * @return
	 */
	private Set<Resource> doFindPathMatchingJarResources(Resource rootDirResource, String string) {
		// TODO Auto-generated method stub
		
		return null;
	}

	private boolean isJarResource(Resource resource) {
		return ResourceUtils.isJarURL(resource.getURL());
	}

	/**
	 * ���ظ�Ŀ¼��Դ
	 * 
	 * @param rootDirResource
	 * @return
	 */
	private Resource resolveRootDirResource(Resource original) {
		// TODO Auto-generated method stub
		URL url = original.getURL();
		if(url.getProtocol().startsWith("bundle"))
		{
			return new URLResource((URL)ReferenceUtils.)
		}
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

	public Resource[] getResources(String path) {
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
