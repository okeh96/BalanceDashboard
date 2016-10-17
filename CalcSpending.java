import java.io.*;
import java.net.*;
import java.util.Date;
import helperfunctions.*;

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
			System.out.println("  Response Message: " + 
								  h.getResponseMessage());
			System.out.println("  Response Code: " + h.getResponseCode());
		}

	}

	public static void main(String[] args) {
		/*try { printinfo(new URL(args[0])); }
		catch (Exception e) {
			System.err.println(e);
			System.err.println("Usage: java GetURLInfo <url>");
		} 

			String message = helperfunctions.getMessage()
*/
		/* Prints the line of text that contains the correct banking
		   information */
		//try { 
		/*	if (args.length != 2)
				throw new IllegalArgumentException("Wrong number of args");
			helperfunctions.GrepReader in = new helperfunctions.GrepReader(new FileReader(), "default");
			String line;

			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

			in.close();*/
		//}

		/*catch (Exception e) {
			System.err.println(e);
			System.out.println("Usage: java CalcSpending" + " <pattern> <file>");
		}*/


		/*GoogleCredential credential = new GoogleCredential().setAccessToken(accessToken);
		Plus plus = new Plus.builder(new NetHttpTransport(), JacksonFactory.getDefaultInstance(), credential)
    		.setApplicationName("Google-PlusSample/1.0")
    		.build(); */
	}
}
