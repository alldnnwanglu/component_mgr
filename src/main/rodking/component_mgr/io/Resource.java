package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public interface Resource {
	File getFile() throws IOException;

	String getFilename();

	boolean isOpen();
	
	String getDescription();
	
	boolean isReadable();

	URL getURL() throws IOException ;
}
