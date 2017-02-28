import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*This class is used to create SHA-512 hashcode*/

public class HashCode  {
	protected static MessageDigest messageDigest;
    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error in SHA-512", e);
        }
    }
}
