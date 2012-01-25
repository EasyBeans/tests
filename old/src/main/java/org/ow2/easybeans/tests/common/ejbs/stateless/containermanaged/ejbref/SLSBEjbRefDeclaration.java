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
 * $Id: SLSBEjbRefDeclaration.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkBeanRef;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBs;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfEJBRef;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;


/**
 * This bean is used to test declaration of ejb references using the &#64;EJB annotation.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name = "SLSBEjbRefDeclaration")
@Remote(ItfEJBRef.class)
@EJBs({
    @EJB(name = "ejb/bean00", beanInterface=ItfOneMethod01.class),
    @EJB(name = "ejb/bean01", beanInterface=ItfOneMethod01.class, mappedName="org.ow2.easybeans.tests.common.ejbs.base."
            + "EJBInjectionBean_org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01@Remote"),
    @EJB(name = "ejb/bean02", beanInterface = ItfOneMethod01.class, beanName = "EJBInjectionBean", description = "bean02",
            mappedName = "org.ow2.easybeans.tests.common.ejbs.base.EJBInjectionBean_org.ow2.easybeans.tests.common."
            + "ejbs.base.ItfOneMethod01@Remote"),
    @EJB(name = "ejb/bean03", beanInterface=ItfEJBRef.class),
    @EJB(name = "ejb/bean04", beanInterface=ItfEJBRef.class, mappedName="org.ow2.easybeans.tests.common.ejbs.stateless."
            + "containermanaged.ejbref.SLSBEjbRefMethodInjection_org.ow2.easybeans.tests.common."
            + "ejbs.base.ItfEJBRef@Remote"),
    @EJB(name = "ejb/bean05", beanInterface = ItfEJBRef.class, beanName = "EJBInjectionBean", description = "bean05",
            mappedName = "org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbref."
            + "SLSBEjbRefMethodInjection_org.ow2.easybeans.tests.common.ejbs.base.ItfEJBRef@Remote")
    })
@EJB(name = "ejb/bean06", beanInterface=ItfEJBRef.class, mappedName="org.ow2.easybeans.tests.common."
        + "ejbs.stateless.containermanaged.ejbref.SLSBEjbRefMethodInjection_org.ow2.easybeans.tests."
        + "common.ejbs.base.ItfEJBRef@Remote")
public class SLSBEjbRefDeclaration implements ItfEJBRef{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Checks the EJB reference.
     */
    public void check00() {
        checkBeanRef(ctx, "ejb/bean00", ItfOneMethod01.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check01() {
        checkBeanRef(ctx, "ejb/bean01", ItfOneMethod01.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check02() {
        checkBeanRef(ctx, "ejb/bean02", ItfOneMethod01.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check03() {
        checkBeanRef(ctx, "ejb/bean03", ItfEJBRef.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check04() {
        checkBeanRef(ctx, "ejb/bean04", ItfEJBRef.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check05() {
        checkBeanRef(ctx, "ejb/bean05", ItfEJBRef.class);
    }

    /**
     * Checks the EJB reference.
     */
    public void check06() {
        checkBeanRef(ctx, "ejb/bean06", ItfOneMethod01.class);
    }

}
