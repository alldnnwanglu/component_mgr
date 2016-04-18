package rodking.component_mgr.classreading;

import java.util.LinkedHashMap;
import java.util.Map;

import rodking.component_mgr.io.Resource;

public class CachingMetadataReaderFactory {
	public static final int DEFAULT_CACHE_LIMIT = 256;

	private volatile int cacheLimit = DEFAULT_CACHE_LIMIT;

	private final Map<Resource, MetadataReader> classReaderCache = new LinkedHashMap<Resource, MetadataReader>(
			DEFAULT_CACHE_LIMIT, 0.75f, true) {
		@Override
		protected boolean removeEldestEntry(Map.Entry<Resource, MetadataReader> eldest) {
			return size() > getCacheLimit();
		}
	};
	
	
	public int getCacheLimit() {
		return this.cacheLimit;
	}


	public MetadataReader getMetadataReader(Resource resource) {
		return null;
	}
}
