package rodking.component_mgr.io;

import java.io.File;
import java.io.IOException;
import java.net.URI;

public class UrlResource extends AbstractResource {
	/** 原始的url,被用来访问 */
	private final File f;

	public UrlResource(URI url) {
		f = new File(url);
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
