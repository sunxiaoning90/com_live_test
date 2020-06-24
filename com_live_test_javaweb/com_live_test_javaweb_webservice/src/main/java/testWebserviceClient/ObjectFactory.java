
package testWebserviceClient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the client package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Hello_QNAME = new QName("http://testWebserviceServer/", "hello");
    private final static QName _HelloToYouResponse_QNAME = new QName("http://testWebserviceServer/", "helloToYouResponse");
    private final static QName _HelloResponse_QNAME = new QName("http://testWebserviceServer/", "helloResponse");
    private final static QName _HelloToYou_QNAME = new QName("http://testWebserviceServer/", "helloToYou");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: client
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link HelloToYou }
     * 
     */
    public HelloToYou createHelloToYou() {
        return new HelloToYou();
    }

    /**
     * Create an instance of {@link HelloResponse }
     * 
     */
    public HelloResponse createHelloResponse() {
        return new HelloResponse();
    }

    /**
     * Create an instance of {@link HelloToYouResponse }
     * 
     */
    public HelloToYouResponse createHelloToYouResponse() {
        return new HelloToYouResponse();
    }

    /**
     * Create an instance of {@link Hello }
     * 
     */
    public Hello createHello() {
        return new Hello();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Hello }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testWebserviceServer/", name = "hello")
    public JAXBElement<Hello> createHello(Hello value) {
        return new JAXBElement<Hello>(_Hello_QNAME, Hello.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloToYouResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testWebserviceServer/", name = "helloToYouResponse")
    public JAXBElement<HelloToYouResponse> createHelloToYouResponse(HelloToYouResponse value) {
        return new JAXBElement<HelloToYouResponse>(_HelloToYouResponse_QNAME, HelloToYouResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testWebserviceServer/", name = "helloResponse")
    public JAXBElement<HelloResponse> createHelloResponse(HelloResponse value) {
        return new JAXBElement<HelloResponse>(_HelloResponse_QNAME, HelloResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HelloToYou }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testWebserviceServer/", name = "helloToYou")
    public JAXBElement<HelloToYou> createHelloToYou(HelloToYou value) {
        return new JAXBElement<HelloToYou>(_HelloToYou_QNAME, HelloToYou.class, null, value);
    }

}
