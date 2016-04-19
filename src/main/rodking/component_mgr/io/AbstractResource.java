package rodking.component_mgr.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AbstractResource implements Resource {

	public boolean isOpen() {
		return false;
	}

	public boolean isReadable() {
		return true;
	}

	public URL getURL() throws IOException {
		throw new FileNotFoundException(getDescription() + " cannot be resolved to URL");
	}
}
