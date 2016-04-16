package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class UrlResource extends AbstractResource {

	private final URL url;
	private final URL uri;
	private final File f;
	// private final URL cleanedUrl;

	public UrlResource(URL url) {
		this.url = url;
		// this.cleanedUrl = getCleanedUrl(this.url, url.toString());
		this.uri = null;
		f = new File(url.getFile());
	}

	@Override
	public File getFile() throws IOException {
		return f;
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
}
