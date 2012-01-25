/**
 * EasyBeans
 * Copyright (C) 2006 Bull S.A.S.
 * Contact: easybeans@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * --------------------------------------------------------------------------
 * $Id: BeanDescriptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.invocationcontext;

import java.io.Serializable;

/**
 * This class is used to store a bean reference and an intercepted method.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class BeanDescriptor implements Serializable {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -1807144244407143163L;

    /**
     * Referenced bean.
     */
    private int intHashCode;

    /**
     * Intercepted method.
     */
    private String mtIntercepted;

    /**
     * Default constructor.
     */
    public BeanDescriptor() {
        setHashCode(0);
        setInterceptedMethod("");
    }

    /**
     * Default args-constructor.
     * @param hashCode the bean hash code.
     * @param methodSignature intercepted method signature.
     */
    public BeanDescriptor(final int hashCode, final String methodSignature) {
        setHashCode(hashCode);
        setInterceptedMethod(methodSignature);
    }

    /**
     * Gets the bean hash code.
     * @return Returns the bean hash code.
     */
    public int getHashCode() {
        return intHashCode;
    }

    /**
     * Sets the bean hash code.
     * @param hashCode The bean hash code to set.
     */
    public void setHashCode(final int hashCode) {
        this.intHashCode = hashCode;
    }

    /**
     * Gets the intercepted method.
     * @return Returns the intercepted method.
     */
    public String getInterceptedMethod() {
        return mtIntercepted;
    }

    /**
     * Sets the intercepted method.
     * @param m The intercepted method to set.
     */
    public void setInterceptedMethod(final String m) {
        this.mtIntercepted = m;

    }

    /**
     * Indicates whether some other object is "equal to" this one.
     * @param obj object to compare.
     * @return true, if equals.
     * @throws Exception if a problem occurs.
     */
    public boolean equalsWithException(final Object obj) throws Exception {
        boolean bResult = false;

        BeanDescriptor refExt = (BeanDescriptor) obj;

        if (refExt.getHashCode() == intHashCode){
                 bResult = true;
        }else{
            throw new Exception("The bean hashcodes aren't equal.");
        }

        return bResult;
    }
}
