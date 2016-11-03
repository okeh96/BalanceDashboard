package helperfunctions;
import java.io.*;

/* A simple FilterReader that strips HTML tags (or anything in between
   a pair of angle brackets) out of a stream of characters */

public class RemoveHTMLReader extends FilterReader {

	public RemoveHTMLReader(Reader in) {super(in);}

	boolean intag = false;

	/* This is the implementation of the read() method of FilterRead.
	   It calls in.read() to get a buffer full of characters, then strips out
	   the HTML tags. */

	public int read(char[] buf, int from, int len) throws IOException {
		int numchars = 0;		// how many character have been read
		while (numchars == 0) {
			numchars = in.read(buf, from, len);
			if (numchars == -1)
				return -1;			// check for EOF and handle it

			int last = from;
			for (int i = from; i < from + numchars; i++) {
				if (!intag) {		// if not an HTML tag
					if (buf[i] == '<')
						intag = true;		// check for a start tag
					else
						buf[last++] = buf[i];
				}
				else if (buf[i] == '>')
					intag = false;			// check for end of tag
			}
			numchars = last - from;
		}
		return numchars;

	}
}

