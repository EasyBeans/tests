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
 * $Id: SLSBPCtxRefDeclaration00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.persistencectxref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkEntityManager;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContexts;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;


/**
 * This bean is used to test the persistence context declarations on the bean class.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBPCtxRefDeclaration00")
@Remote(ItfCheck00.class)
@PersistenceContexts({
        @PersistenceContext(name="persistence/pu00"),
        @PersistenceContext(name = "persistence/pu01", unitName = "testEntity00")})
@PersistenceContext(name="persistence/pu02")
public class SLSBPCtxRefDeclaration00 implements ItfCheck00{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;


    /**
     * Checks if the annotations &#64;PersistenceContext and &#64;PersistenceContexts are working properly.
     * The reference was declared on the bean class.
     * The container must:
     * <li>declare a reference using &#64;PersistenceContexts;</li>
     * <li>declare a reference using &#64;PersistenceContext.</li>
     */
    public void check() {
        checkEntityManager(sessionContext, "persistence/pu00");
        checkEntityManager(sessionContext, "persistence/pu01");
        checkEntityManager(sessionContext, "persistence/pu02");
    }
}
