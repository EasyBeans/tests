
package org.ow2.easybeans.tests.common.ws.ejbref.gen.types;

import javax.xml.bind.annotation.XmlRegistry;

import org.ow2.easybeans.tests.common.ws.ejbref.gen.types.Check;
import org.ow2.easybeans.tests.common.ws.ejbref.gen.types.CheckResponse;
import org.ow2.easybeans.tests.common.ws.ejbref.gen.types.ObjectFactory;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.ow2.easybeans.tests.common.ws.ejbref.gen.types package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ow2.easybeans.tests.common.ws.ejbref.gen.types
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Check }
     * 
     */
    public Check createCheck() {
        return new Check();
    }

    /**
     * Create an instance of {@link CheckResponse }
     * 
     */
    public CheckResponse createCheckResponse() {
        return new CheckResponse();
    }

}
