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
 * $Id: BaseTestTimeout.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.timerservice.timeout.base;

import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType.TIMEOUT;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTERPRISE_BEAN;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.ENTITY_MANAGER_FACTORY;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.RESOURCE_MANAGER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.TIMER;
import static org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType.USER_TRANSACTION;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.OperationType;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.OperationChecker;


/**
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public abstract class BaseTestTimeout {

    /**
     * Log checker.
     */
    private OperationChecker checker;

    /**
     * Gets the name of the bean which should perform the operation.
     * @return name
     */
    public abstract String getBeanName();

    /**
     * Creates the log checker.
     * @throws Exception if there is a problem.
     */
    public void startUp() throws Exception {
        checker = new OperationChecker();
    }

    /**
     * Tests if a timeout callback method is allowed to access an EJB.
     * @throws Exception if a problem occurs.
     */
    public void testAccessEJB00() throws Exception {
        check(ENTERPRISE_BEAN);
    }

    /**
     * Tests if a timeout callback method is allowed to access the Resource
     * Manager.
     * @throws Exception if a problem occurs.
     */
    public void testAccessResourceManager00() throws Exception {
        check(RESOURCE_MANAGER);
    }

    /**
     * Tests if a timeout callback method is allowed to access the Entity
     * Manager.
     * @throws Exception if a problem occurs.
     */
    public void testAccessEntityManager00() throws Exception {
        check(ENTITY_MANAGER);
    }

    /**
     * Tests if a timeout callback method is allowed to access the Entity
     * Manager Factory.
     * @throws Exception if a problem occurs.
     */
    public void testAccessEntityManagerFactory00() throws Exception {
        check(ENTITY_MANAGER_FACTORY);
    }

    /**
     * Tests if a timeout callback method is allowed to access the Timer
     * Service.
     * @throws Exception if a problem occurs.
     */
    public void testAccessTimerService00() throws Exception {
        check(TIMER);

    }

    /**
     * Tests if a timeout callback method is allowed to access the
     * UserTransaction.
     * @throws Exception if a problem occurs.
     */
    public void testAccessUserTransaction00() throws Exception {
        check(USER_TRANSACTION);
    }

    /**
     * Requests a timer creation and, after its experation, verifies if the specified operation
     * occured.
     * @param type the operation type.
     * @throws Exception if a problem occurs.
     */
    public void check(final OperationType type) throws Exception {
        requestStartTimer(type);
        checker.check(getBeanName(), TIMEOUT, type);
    }

    /**
     * Starts the timer.
     * @param type operation type
     * @throws Exception if a problem occurs.
     */
    public abstract void requestStartTimer(final OperationType type) throws Exception;

    /**
     * Clears logs.
     * @throws Exception if a problem occurs.
     */
    public void tearDownMethod() throws Exception {
        checker.deleteAll();
    }

}
