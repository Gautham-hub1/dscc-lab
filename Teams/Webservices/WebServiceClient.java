package vce.webservices.client;
 
public class WebServiceClient {
 
    /**
     * Starts the web service client.
     */
    public static void main(String[] args) {
        MD5WebServiceService client = new MD5WebServiceService();
        MD5WebService md5Webservice = client.getMD5WebServicePort();
        String hash = md5Webservice.hashString("admin");
        System.out.println("MD5 hash string: " + hash);
    }
}