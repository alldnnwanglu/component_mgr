package rodking.component_mgr.classreading;

import rodking.component_mgr.io.Resource;

public interface MetadataReader {
	/**
	 * Return the resource reference for the class file.
	 */
	Resource getResource();

	/**
	 * Read basic class metadata for the underlying class.
	 */
	ClassMetadata getClassMetadata();

	/**
	 * Read full annotation metadata for the underlying class, including
	 * metadata for annotated methods.
	 */
	AnnotationMetadata getAnnotationMetadata();
}
