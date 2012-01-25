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
 * $Id: Assert.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.asserts;

import static org.ow2.easybeans.tests.common.helper.InterceptorHelper.getPrintOrderErrorMsg;

import java.util.List;

/**
 * It's creates new assert functions.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class Assert extends org.testng.Assert{

    /**
     * Asserts that two lists has the same elements.
     * @param expected first list to compare
     * @param result second list to compare
     * @param message if assert fails, exception message
     */
    public static void assertEquals(final List expected, final List result, final String message) {

        // if the arrays are null, they have the same elements
        if (expected == null ^ result == null) {
            throw new AssertionError(getPrintOrderErrorMsg(expected, result, message));
        }
        if (expected != null) {
            // Checks if the arrays has the same size
            if (expected.size() == result.size()) {
                // Compare each element
                for (int i = 0; i < expected.size(); i++) {
                    if (!(expected.get(i).equals(result.get(i)))) {
                        throw new AssertionError(getPrintOrderErrorMsg(expected, result, message));
                    }
                }
            } else {
                throw new AssertionError(getPrintOrderErrorMsg(expected, result, message));
            }
        }
    }

    /**
     * Asserts that an object is equal at least with a value in the args.
     * @param result the value that will be compared.
     * @param message the error message.
     * @param expected the list of correct values for the parameter result.
     */
    public static void assertEquals(final Object result, final Object[] expected, final String message){
        boolean bolIsEqual = false;
        int i = 0;

        if(expected == null){
            throw new AssertionError("The args are null");
        }

        while(!bolIsEqual && i < expected.length){
            if(expected[i].equals(result)){
                bolIsEqual = true;
            }else{
                i++;
            }
        }
        if(!bolIsEqual){
            throw new AssertionError(message);
        }
    }
}
