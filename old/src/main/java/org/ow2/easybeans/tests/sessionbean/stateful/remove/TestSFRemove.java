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
 * $Id: TestSFRemove.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.sessionbean.stateful.remove;

import static org.ow2.easybeans.tests.common.helper.EJBHelper.getBeanRemoteInstance;

import javax.ejb.NoSuchEJBException;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;
import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck02;
import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck03;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.remove.SFSBRemove00;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.remove.SFSBRemoveByException;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.remove.SFSBRemoveWithRetain;
import org.ow2.easybeans.tests.common.exception.AppException;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * Tests related with stateful instance remove operation.
 * @reference JSR 220 - EJB 3.0 Core - 4.3.11
 * @requirement Application Server must be running.<br>
 *              (Ant task: install.jar.tests.stateful.remove)
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class TestSFRemove {

    /**
     * Log helper.
     */
    private Log logger = LogFactory.getLog(TestSFRemove.class);

    /**
     * Bean.
     */
    private ItfCheck02 beanRemoveDefault;

    /**
     * Bean.
     */
    private ItfCheck00 beanRemoveByException;

    /**
     * Bean.
     */
    private ItfCheck03 beanRetain;

    /**
     * Gets bea instances used in the tests.
     * @throws Exception if there is a problem with bean initialization.
     */
    @BeforeClass
    public void startUp() throws Exception {
        beanRemoveDefault = getBeanRemoteInstance(SFSBRemove00.class, ItfCheck02.class);
        beanRemoveByException = getBeanRemoteInstance(SFSBRemoveByException.class, ItfCheck00.class);
        beanRetain = getBeanRemoteInstance(SFSBRemoveWithRetain.class, ItfCheck03.class);
    }

    /**
     * Verifies if the bean is destroyed after the remove() invocation.
     * @input -
     * @output NoSuchEJBException
     * @throws Exception if a problem occurs.
     */
    @Test(expectedExceptions = { NoSuchEJBException.class })
    public void testRemoveMethod() throws Exception {
        beanRemoveDefault.remove();
        beanRemoveDefault.check();
    }

    /**
     * Verifies if the bean is destroyed after a system exception.
     * @input -
     * @output NoSuchEJBException
     * @throws Exception if a problem occurs.
     */
    @Test(expectedExceptions = { NoSuchEJBException.class })
    public void testRemoveBySystemException() throws Exception {
        try {
            beanRemoveByException.check();
        } catch (Exception e) {
            logger.debug("Exception.");
        }
        beanRemoveByException.check();
    }

    /**
     * Verifies if the bean is not destroyed after an application exception
     * during the remote() method invocation.
     * @input -
     * @output NoSuchEJBException
     * @throws Exception if a problem occurs.
     */
    @Test
    public void testRetain() throws Exception {
        try {
            beanRetain.remove();
        }catch (AppException e) {
            logger.debug("Expected Exception occured.");
        }catch (Exception e) {
            logger.debug("Exception");
        }
        beanRetain.check();
    }
}
