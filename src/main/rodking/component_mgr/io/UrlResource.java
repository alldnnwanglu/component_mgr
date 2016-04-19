package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

import com.sun.xml.internal.ws.util.StringUtils;

public class UrlResource extends AbstractResource {

	/** 原始的url,被用来访问 */
	private final URL url;

	/** 原来的URI，如果可用；用于URI和文件访问。 */
	private final URI uri;
	private final File f;

	// private final URL cleanedUrl;

	public UrlResource(URL url) {
		this.url = url;
		f = new File(url.getFile());
		this.uri = f.toURI();
	}

	@Override
	public File getFile() throws IOException {
		return f;
	}

	@Override
	public String getFilename() {
		// TODO Auto-generated method stub
		return f.getName();
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}
}
