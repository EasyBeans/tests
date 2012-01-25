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
 * $Id: SLSBEjbRefFieldInjection.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkBeanRef;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfEJBRef;
import org.ow2.easybeans.tests.common.ejbs.base.ItfOneMethod01;

/**
 * This bean is used to test injection of enterprise beans.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBEjbRefFieldInjection")
@Remote(ItfEJBRef.class)
public class SLSBEjbRefFieldInjection implements ItfEJBRef {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext ctx;

    /**
     * Bean.
     */
    @EJB
    private ItfOneMethod01 bean00;

    /**
     * Bean.
     */
    @EJB(name = "ejb/bean01", description = "This bean is used to test name and description properties of the @EJB annotation.")
    private ItfOneMethod01 bean01;

    /**
     * Bean.
     */
    @EJB(beanInterface = ItfOneMethod01.class)
    private ItfOneMethod01 bean02;

    /**
     * Bean.
     */
    @EJB(beanName = "EJBInjectionBean")
    private ItfOneMethod01 bean03;

    /**
     * Bean.
     */
    @EJB(mappedName = "org.ow2.easybeans.tests.common.ejbs.base.EJBInjectionBean_org.ow2.easybeans.tests.common."
            + "ejbs.base.ItfOneMethod01@Remote")
    private ItfOneMethod01 bean04;

    /**
     * Bean.
     */
    @EJB(name = "ejb/bean05", beanInterface = ItfOneMethod01.class, beanName = "EJBInjectionBean", description = "bean05")
    private ItfOneMethod01 bean05;

    /**
     * Bean.
     */
    @EJB(name = "ejb/bean06", beanInterface = ItfOneMethod01.class, beanName = "EJBInjectionBean", description = "bean06",
            mappedName = "org.ow2.easybeans.tests.common.ejbs.base.EJBInjectionBean_org.ow2.easybeans.tests.common."
            + "ejbs.base.ItfOneMethod01@Remote")
    private ItfOneMethod01 bean06;

    /**
     * Checks if the annotation &#64;EJB is working properly. The annotation's
     * properties are not used.
     */
    public void check00() {
        assert bean00.getBool();
        checkBeanRef(ctx,
                "org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged."
                + "ejbref.SLSBEjbRefFieldInjection/bean00", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * properties are used: <li>name</li><li>description</li>
     */
    public void check01() {
        assert bean01.getBool();
        checkBeanRef(ctx, "ejb/bean01", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * property is used: <li>beanInterface</li>
     */
    public void check02() {
        assert bean02.getBool();
        checkBeanRef(ctx,
                "org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged."
                + "ejbref.SLSBEjbRefFieldInjection/bean02", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * property is used: <li>beanName</li>
     */
    public void check03() {
        assert bean03.getBool();
        checkBeanRef(ctx,
                "org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged."
                + "ejbref.SLSBEjbRefFieldInjection/bean03", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * property is used: <li>mappedName</li>
     */
    public void check04() {
        assert bean04.getBool();
        checkBeanRef(ctx,
                "org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged."
                + "ejbref.SLSBEjbRefFieldInjection/bean04", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * properties are used: <li>name</li> <li>beanInterface</li><li>beanName</li><li>description</li>
     */
    public void check05() {
        assert bean05.getBool();
        checkBeanRef(ctx, "ejb/bean05", ItfOneMethod01.class);
    }

    /**
     * Checks if the annotation &#64;EJB is working properly. The following
     * properties are used: <li>name</li> <li>beanInterface</li><li>beanName</li><li>description</li><li>mappedName</li>
     */
    public void check06() {
        assert bean06.getBool();
        checkBeanRef(ctx, "ejb/bean06", ItfOneMethod01.class);
    }
}
