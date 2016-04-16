package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;

public interface Resource extends InputStreamSource {
	File getFile() throws IOException;

	String getFilename();

	boolean isOpen();
	
	String getDescription();
	
	boolean isReadable();
}
