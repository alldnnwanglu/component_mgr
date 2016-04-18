package rodking.component_mgr;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.naming.Reference;

import rodking.component_mgr.classreading.CachingMetadataReaderFactory;
import rodking.component_mgr.classreading.MetadataReader;
import rodking.component_mgr.config.GenericBeanDefinition;
import rodking.component_mgr.io.Resource;
import rodking.component_mgr.io.UrlResource;
import rodking.component_mgr.util.ClassUtils;
import rodking.component_mgr.util.ResourceUtils;
import rodking.util.JaveType;

public class ResourcePatternResolver implements ResourceLoader {

	public final ClassLoader classLoader = ClassUtils.getDefaultClassLoader();
	private CachingMetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory();

	@Override
	public ClassLoader getClassLoader() {
		return classLoader;
	}

	/**
	 * 查找路径匹配资源
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
			// 如果 jar
			if (isJarResource(rootDirResource)) {
				// jar resource 加入到列表中
				result.addAll(doFindPathMatchingJarResources(rootDirResource, ""));// null);
			} else if (rootDirResource.getURL().getProtocol().startsWith(ResourceUtils.URL_PROTOCOL_VFS)) {
				result.addAll(null);
			} else
				result.addAll(null);
		}

		return result.toArray(new Resource[result.size()]);

	}

	/**
	 * 找到路径匹配的 jar
	 * 
	 * @param rootDirResource
	 * @param string
	 * @return
	 */
	private Set<Resource> doFindPathMatchingJarResources(Resource rootDirResource, String string) {
		// TODO Auto-generated method stub

		return null;
	}

	private boolean isJarResource(Resource resource) throws IOException {
		return ResourceUtils.isJarURL(resource.getURL());
	}

	/**
	 * 加载根目录资源
	 * 
	 * @param rootDirResource
	 * @return
	 */
	private Resource resolveRootDirResource(Resource original) throws IOException {
		// TODO Auto-generated method stub
		URL url = original.getURL();
		if (url.getProtocol().startsWith("bundle")) {
			// return new URLResource((URL)ReferenceUtils)
		}
		return null;
	}

	/**
	 * 查找路径下面的所有资源
	 * 
	 * @param path
	 *            路径
	 * @return
	 * @throws IOException
	 */
	protected Resource[] findAllResource(String path) throws IOException {
		// 通过类加载器活动 路径 的url
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

	public Resource[] getResources(String path) {
		Resource[] resources = null;
		try {

			resources = findAllResource(path);
			for (Resource resource : resources) {

				GenericBeanDefinition beanDef = new GenericBeanDefinition();
				beanDef.setBeanClassName(getClassName(resource, path));
				Class<?> beanClass = beanDef.initBean(classLoader);
				// 如果该类是 组件标签，就把它放到类 application 容器中
				if (beanClass.isAnnotationPresent(Component.class)) {
					beanDef.onInit();
					ApplicationContext.getInstance().addBean(beanDef);
				}
			}

		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resources;
	}

	/**
	 * 是否是候选组件
	 * 
	 * @param metadataReader
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	private boolean isCandidateComponent(MetadataReader metadataReader, String path)
			throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		String p = metadataReader.getResource().getFile().getPath().replace("\\", "/");
		p = p.substring(p.indexOf(path), p.length());
		p = p.substring(0, p.lastIndexOf(".class")).replace("/", ".");
		Class<?> t = classLoader.loadClass(p);
		if (t != null && t.isAnnotationPresent(Component.class)) {
			// metadataReader.
		}
		return false;
	}

	private String getClassName(Resource res, String path) throws IOException {
		String p = res.getFile().getPath().replace("\\", "/");
		p = p.substring(p.indexOf(path), p.length());
		p = p.substring(0, p.lastIndexOf(".class")).replace("/", ".");
		return p;
	}
}
