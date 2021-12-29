package vce.webservices.server;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
import javax.jws.WebMethod;
import javax.jws.WebService;
 
@WebService
public class MD5WebService {
    @WebMethod
    public String hashString(String input) {
        try {
            MessageDigest msgDigest = MessageDigest.getInstance("MD5");
            byte[] inputBytes = input.getBytes();
            byte[] hashedBytes = msgDigest.digest(inputBytes);
 
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < hashedBytes.length; i++) {
                sb.append(Integer.toString((hashedBytes[i] & 0xff) + 0x100, 16)
                        .substring(1));
            }
 
            return sb.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return "";          
        }
    }
}