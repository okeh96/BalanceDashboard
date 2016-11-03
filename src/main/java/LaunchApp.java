import helperfunctions.*;

import java.io.*;
import java.net.*;

import com.google.api.services.gmail.model.*;
import com.google.api.services.gmail.Gmail;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class LaunchApp {

	public static void main(String[] args) throws IOException {

		// Build a new authorized API client service.
		CalcSpending handler = new CalcSpending();

        Gmail service = handler.getGmailService();

        // Sets user to authenticated instance
        String user = "me";

        // Search query to find emails containing periodic balance
        String searchQuery = "from:alerts@internetbanking.unfcu.org: subject:Periodic Balance";

        // Return an ArrayList containing ID's of emails returned from search query
    	List<Message> email = handler.listMessagesMatchingQuery(service, user, searchQuery);

    	for (int i = 0; i < email.size(); i++) { 		
  			String allmessages = handler.getMessage(service, user, (String) email.get(i).get("id"));
            //GrepReader hello = new GrepReader(allmessages, "Checking Account balance is");
  		}

	}
}