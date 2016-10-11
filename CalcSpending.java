import java.io.*;
import java.net.*;
import java.util.Date;

public class CalcSpending {
	public static void printinfo(URL url) throws IOException {
		URLConnection c = url.openConnection();
		c.connect();

		// Display some information about the URL contents
		System.out.println("  Content type: " + c.getContentType());
		System.out.println("  Content Encoding: " + c.getContentEncoding());
		System.out.println("  Content Length: " + c.getContentLength());
		System.out.println("  Date: " + new Date(c.getDate()));

		// If it is an HTTP connection, display some additional information.
		if (c instanceof HttpURLConnection) {
			HttpURLConnection h = (HttpURLConnection) c;
			System.out.println("  Request Method: " + h.getRequestMethod());
			System.out.println("  Response Message: " + h.getResponseMessage());
			System.out.println("  Response Code: " + h.getResponseCode());
		}

	}

	public static void main(String[] args) {
		System.out.println("Hello!");
		try { printinfo(new URL(args[0])); }
		catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java GetURLInfo <url>");
		}
	}
}
