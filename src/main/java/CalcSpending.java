import helperfunctions.*;

import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Properties;
import java.util.regex.*;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import com.google.api.services.gmail.GmailScopes;
import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

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
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Balance Dashboard";

	/** Directory to store user credentials for this application. */
	private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/gmail-java-quickstart");

	/** Global instance of the {@link FileDataStoreFactory}. */
	private static FileDataStoreFactory DATA_STORE_FACTORY;


	/** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/gmail-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(GmailScopes.GMAIL_READONLY);

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize() throws IOException {
        // Load client secrets.
        InputStream in =
            CalcSpending.class.getResourceAsStream("/client_secret.json");
        GoogleClientSecrets clientSecrets =
            GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow =
                new GoogleAuthorizationCodeFlow.Builder(
                        HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(DATA_STORE_FACTORY)
                .setAccessType("offline")
                .build();
        Credential credential = new AuthorizationCodeInstalledApp(
            flow, new LocalServerReceiver()).authorize("user");
        System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
    }

    /*
     * Build and return an authorized Gmail client service.
     * @return an authorized Gmail client service
     * @throws IOException
     */
    public static Gmail getGmailService() throws IOException {
        Credential credential = authorize();
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

  	public static String getMessage(Gmail service, String userId, String messageId)
      	throws IOException {
      	//System.out.println(service + "		" + userId + "	" + messageId);
        
    	Message message = service.users().messages().get(userId, messageId).execute();
        String convertmessage = message.getSnippet().toString();

    	System.out.println("Message snippet: " + convertmessage);

    	return convertmessage;
  	}

  	public static List<Message> listMessagesMatchingQuery(Gmail service, String userId,
      String query) throws IOException {
    	ListMessagesResponse response = service.users().messages().list(userId).setQ(query).execute();

   		List<Message> messages = new ArrayList<Message>();
    	while (response.getMessages() != null) {
    		messages.addAll(response.getMessages());
    		//System.out.println(messages.get("id"));
      		if (response.getNextPageToken() != null) {
        		String pageToken = response.getNextPageToken();
        		response = service.users().messages().list(userId).setQ(query)
            	.setPageToken(pageToken).execute();
      		} 
      		else {
        		break;
      		}
    	}

    	return messages;
	}

	public static void main(String[] args) throws IOException {
		
		// Build a new authorized API client service.
        Gmail service = getGmailService();

        // Sets user to authenticated instance
        String user = "me";

        // Search query to find emails containing periodic balance
        String searchQuery = "from:alerts@internetbanking.unfcu.org: subject:Periodic Balance";

        // Return an ArrayList containing ID's of emails returned from search query
    	List<Message> email = listMessagesMatchingQuery(service, user, searchQuery);

    	for (int i = 0; i < email.size(); i++) { 		
  			String allmessages = getMessage(service, user, (String) email.get(i).get("id"));
            //GrepReader hello = new GrepReader(allmessages, "Checking Account balance is");
  		}

	}
}
