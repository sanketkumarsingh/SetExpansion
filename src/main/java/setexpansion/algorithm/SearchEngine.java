package setexpansion.algorithm;

import java.io.IOException;
import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class SearchEngine {
	private IndexSearcher searcher = null;
	private QueryParser parser = null;

	/** Creates a new instance of SearchEngine */
	public SearchEngine(String fileName) throws IOException {
		searcher = new IndexSearcher(DirectoryReader.open(FSDirectory.open(new File(fileName))));
		parser = new QueryParser(Version.LUCENE_41, "term", new StandardAnalyzer(Version.LUCENE_41));
	}

	public TopDocs performSearch(String queryString, int n) throws IOException, ParseException {
		Query query = parser.parse(queryString);
		return searcher.search(query, n);
	}

	public Document getDocument(int docId) throws IOException {
		return searcher.doc(docId);
	}
}
