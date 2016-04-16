package rodking.component_mgr.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public abstract class AbstractResource implements Resource {
	public boolean exists() {
		// Try file existence: can we find the file in the file system?
		try {
			return getFile().exists();
		} catch (IOException ex) {
			// Fall back to stream existence: can we open the stream?
			try {
				InputStream is = getInputStream();
				is.close();
				return true;
			} catch (Throwable isEx) {
				return false;
			}
		}
	}

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
