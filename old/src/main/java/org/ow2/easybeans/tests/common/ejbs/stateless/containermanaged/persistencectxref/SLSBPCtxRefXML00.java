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
 * $Id: SLSBPCtxRefXML00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistencectxref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkEntityManager;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;


/**
 * This bean is used to test the the persistence context reference declaration and injection in a xml descriptor.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBPCtxRefXML00")
@Remote(ItfCheck00.class)
public class SLSBPCtxRefXML00 implements ItfCheck00{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The reference must be injected by the container by XML descriptor.
     */
    private EntityManager em00;

    /**
     * Checks if the XML injection and XML declaration is working properly.
     * The container must:
     * <li>inject a reference;</li>
     * <li>declare a reference.</li>
     */
    public void check() {
        checkEntityManager(sessionContext, em00, "persistence/pu00");
        checkEntityManager(sessionContext, "persistence/puXMLDeclaration");
    }

}
