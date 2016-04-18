package rodking.component_mgr;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.LinkedHashSet;
import java.util.Set;

public class ClassPathBeanDefinitionScanner {

	public final ResourcePatternResolver resourcePR = new ResourcePatternResolver();

	public void doScan(String path) throws IOException {
		path = "rodking/util/";

		resourcePR.getResources(path);

	}
}
