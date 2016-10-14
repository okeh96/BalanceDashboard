import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import java.io.*;

package helperfunctions;

public class MyClass {

  /**
   * Get Message with given ID.
   *
   * @param service Authorized Gmail API instance.
   * @param userId User's email address. The special value "me"
   * can be used to indicate the authenticated user.
   * @param messageId ID of Message to retrieve.
   * @return Message Retrieved Message.
   * @throws IOException
   */
  public static Message getMessage(Gmail service, String userId, String messageId)
      throws IOException {
    Message message = service.users().messages().get(userId, messageId).execute();

    //System.out.println("Message snippet: " + message.getSnippet());

    return message;
  }