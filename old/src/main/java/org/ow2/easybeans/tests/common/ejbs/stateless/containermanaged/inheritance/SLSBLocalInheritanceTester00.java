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
 * $Id: SLSBLocalInheritanceTester00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.inheritance;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanLocalInstance;

import javax.ejb.Remote;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfLocalInheritanceTester00;
import org.ow2.easybeans.tests.common.ejbs.base.inheritance.EBaseLocalInheritanceTester00;
import org.ow2.easybeans.tests.common.inheritance.ItfAddElement;


/**
 * Used to test the local annotation with inheritance.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name="SLSBLocalInheritanceTester00")
@Remote({ItfLocalInheritanceTester00.class})
public class SLSBLocalInheritanceTester00 extends EBaseLocalInheritanceTester00{

    /**
     * Used to configure the environment used by the tests.
     * @throws Exception if a problem occurs.
     */
    @Override
    public void startUp() throws Exception{
        ItfAddElement bean00 = getBeanLocalInstance(SLSBInheritance00.class, ItfAddElement.class);
        ItfAddElement bean01 = getBeanLocalInstance(SLSBInheritance01.class, ItfAddElement.class);

        super.setBeans(bean00, bean01);
    }
}
