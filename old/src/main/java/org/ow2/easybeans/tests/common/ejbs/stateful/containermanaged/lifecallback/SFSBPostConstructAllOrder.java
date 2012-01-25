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
 * $Id: SFSBPostConstructAllOrder.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.interceptors.lifecycle.postconstruct.PostConstructLogger01;

/**
 * This bean is used to test the interceptor invocation order.
 * The list of interceptors is the following:
 * <li>Interceptor Method in an Super Interceptor Class.</li>
 * <li>Interceptor Method in an Child Interceptor Class.</li>
 * <li>Interceptor Method in a bean super class.</li>
 * <li>Interceptor Method in the bean class.</li>
 * <li>The interceptor in BasePostConstructWithException(inherited class)
 * must not be invoked, the method is override by the bean class</li>
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name="SFSBPostConstructAllOrder")
@Remote(ItfCheck02.class)
@Interceptors(PostConstructLogger01.class)
public class SFSBPostConstructAllOrder extends BasePostConstructWithException{

    /**
     * PostConstruct lifecyle callback interceptor method.
     * This method must be overwritten to avoid an IllegalStateException.
     */
    @Override
    @PostConstruct
    public void intercept00() {
        super.log(SFSBPostConstructAllOrder.class, CallbackType.POST_CONSTRUCT, SFSBPostConstructAllOrder.class);
    }
}
