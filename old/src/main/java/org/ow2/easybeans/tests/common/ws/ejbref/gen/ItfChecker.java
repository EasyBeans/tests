/* Generated by WSDLToJava Compiler. */

package org.ow2.easybeans.tests.common.ws.ejbref.gen;

import javax.jws.WebParam.Mode;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding;
import javax.jws.WebMethod;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by the Celtix 1.0
 * Tue Jul 25 14:11:46 CEST 2006
 * Generated source version: 1.0
 * 
 */

@WebService( name = "ItfChecker")

public interface ItfChecker {

    @ResponseWrapper(targetNamespace = "http://objectweb.org/easybeans/tests/common/ws/ejbref/gen/types", className = "org.ow2.easybeans.tests.common.ws.ejbref.gen.types.CheckResponse", localName = "checkResponse")
    @RequestWrapper(targetNamespace = "http://objectweb.org/easybeans/tests/common/ws/ejbref/gen/types", className = "org.ow2.easybeans.tests.common.ws.ejbref.gen.types.Check", localName = "check")
    @WebMethod(operationName = "check")
    public void check() throws WSException;
}