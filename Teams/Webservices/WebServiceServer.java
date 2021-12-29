package vce.webservices.server;
 
import javax.xml.ws.Endpoint;
 
public class WebServiceServer {
 
    /**
     * Starts a simple server to deploy the web service. 
     */
    public static void main(String[] args) {
        String bindingURI = "http://localhost:9898/md5WebService";
        MD5WebService webService = new MD5WebService();
        Endpoint.publish(bindingURI, webService);
        System.out.println("Server started at: " + bindingURI);
    }
}