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
 * $Id: InterceptorExceptions00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base.interceptorexception;

import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfExceptions;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;
import org.ow2.easybeans.tests.common.interceptors.business.exception.CausesException00Interceptor;
import org.ow2.easybeans.tests.common.interceptors.business.exception.CausesException01Interceptor;


/**
 * Used to test interceptors exceptions.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class InterceptorExceptions00 implements ItfExceptions{

    /**
     * Gets an integer.
     * @return value.
     */
    public Integer getInt() {
        return VALUE;
    }

    /**
     * This method should throw CustomException00 exception,
     * because the interceptor used always throws this exception.
     * @throws CustomException00 interceptor exception
     * @return integer
     */
    @Interceptors({CausesException00Interceptor.class})
    public Integer throwsOneException00() throws CustomException00 {
        return VALUE;
    }

    /**
     * This method should throw CustomException01 exception,
     * because the interceptor used always throws this exception.
     * @throws CustomException01 interceptor exception
     * @return integer
     */
    @Interceptors({CausesException01Interceptor.class})
    public Integer throwsOneException01() throws CustomException01 {
        return VALUE;
    }

    /**
     * This method should throw CustomException00 exception, because the first
     * interceptor will be called before the other.
     * @throws CustomException00 interceptor exception
     * @throws CustomException01 interceptor exception
     * @return integer
     */
    @Interceptors({CausesException00Interceptor.class, CausesException01Interceptor.class})
    public Integer throwsTwoExceptions() throws CustomException00, CustomException01 {
        return VALUE;
    }

}
