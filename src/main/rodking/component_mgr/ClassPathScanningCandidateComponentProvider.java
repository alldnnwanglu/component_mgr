package rodking.component_mgr;

import java.io.IOException;

import rodking.component_mgr.io.Resource;

/**
 * @author rodking
 * @see    0.1
 *
 */
public class ClassPathScanningCandidateComponentProvider {

	
	private ResourcePatternResolver resourcePatternResolver = new ResourcePatternResolver();
	
	public void findCandidateComponents(String path)
	{
		try {
			Resource[] resources = resourcePatternResolver.findAllResource(path);
			
			for(Resource resource: resources)
			{
				if(resource.isReadable())
				{
					resource.getFile().isDirectory();
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
