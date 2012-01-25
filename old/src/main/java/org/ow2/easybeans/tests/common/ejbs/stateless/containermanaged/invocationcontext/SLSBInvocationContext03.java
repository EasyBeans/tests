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
 * $Id: SLSBInvocationContext03.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.invocationcontext;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct.PostConstruct00;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct.PostConstruct01;

/**
 * This class is used to test if the proceed() return null when the method is
 * null.
 * @author Eduardo Studzinski E. de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless
@Remote
@Interceptors({PostConstruct00.class, PostConstruct01.class})
public class SLSBInvocationContext03 implements ItfOneMethod00 {

    /**
     * Returns null.
     * @param i not used
     * @throws Exception if invocation fails
     * @return null
     */
    public Object doOne(final Integer i) throws Exception {
        return null;
    }
}
