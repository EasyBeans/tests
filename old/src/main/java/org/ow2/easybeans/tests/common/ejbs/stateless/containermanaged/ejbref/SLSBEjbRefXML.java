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
 * $Id: SLSBEjbRefXML.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkBeanRef;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfEJBInjection;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;


/**
 * This bean is used to test injection of an ejb reference using the deployment descriptor.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name = "SLSBEjbRefXML")
@Remote(ItfEJBInjection.class)
public class SLSBEjbRefXML implements ItfEJBInjection{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Bean.
     */
    private ItfOneMethod01 bean;

    /**
     * Checks injection of an ejb reference using the deployment descriptor.
     */
    public void checkInjection() {
        assert bean.getBool();
        checkBeanRef(ctx, "ejb/bean00", ItfOneMethod01.class);
    }

}
