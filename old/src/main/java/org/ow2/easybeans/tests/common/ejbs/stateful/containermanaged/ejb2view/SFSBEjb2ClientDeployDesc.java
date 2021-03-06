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
 * $Id: SFSBEjb2ClientDeployDesc.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;


/**
 * Client with ejb 2.1 view. The bean defined as ejb 2.1 has the interfaces define by deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateful
@Remote(ItfEjb2Client.class)
@EJB(beanName = "SimpleEjb2BeanDeployDescRemote", name =  "ejb/SimpleEjb2DeployDesc", beanInterface = SimpleEjb2Home.class)
public class SFSBEjb2ClientDeployDesc extends Ejb2Client {

    /**
     * The bean home object.
     */
    @EJB(beanName = "SimpleEjb2BeanDeployDescRemote")
    private SimpleEjb2Home  beanHome;

    /**
     * Gets the bean home interface injected by the annotation ejb.
     * @return the bean home object.
     */
    @Override
    public SimpleEjb2Home getBeanHome() {
        return beanHome;
    }

    /**
     * Gets the bean name in the environment.
     * @return the bean name.
     */
    @Override
    public String getBeanName() {
        return "java:comp/env/ejb/SimpleEjb2DeployDesc";
    }

}
