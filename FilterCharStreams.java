import java.io.*

public class GrepReader extends BufferedReader {
	String pattern;

	/* This class is a BufferedReader that filters out all lines that do not
	   contain banking information in an email */

	public GrepReader(Reader in, String pattern) {
		super(in);
		this.pattern = pattern;
	}

	/* This is the filter: call our superclass's readLine() to get the actual
	   lines, but only return lines that contain the banking information */

	public final String readLine() throws IOException {
		String line;
		do { line = super.readLine();}
		while ((line != NULL) && line.indexOf(pattern) == -1);
		return line;
	}
}