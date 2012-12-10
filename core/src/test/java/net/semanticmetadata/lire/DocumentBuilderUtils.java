package net.semanticmetadata.lire;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.lucene.document.Document;

/**
 * This is a helper class for document builder handling 
 * 
 * @author kreyssel, created 2012/12/10
 */
public final class DocumentBuilderUtils {

	private DocumentBuilderUtils() {
	}
	
	public final static Document createDocument(DocumentBuilder documentBuilder, String path, String identifier) throws IOException {
		FileInputStream in = new FileInputStream(path);
		try {
			return documentBuilder.createDocument(in, identifier);
		} finally {
			in.close();
		}
	}
}
