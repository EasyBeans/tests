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
 * $Id: TestContainerTransactionException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.transaction.containermanaged.base;

import static org.testng.Assert.fail;

import javax.ejb.EJBException;

import org.ow2.easybeans.tests.common.ejbs.base.transaction.ItfContainerTransaction;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.transaction.ItfTransactionMisc00;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.EmbeddedHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies if the container manages well the runtime exception and the transaction among the beans.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class TestContainerTransactionException {

    /**
     * Constant that defines the first database name.
     */
    protected static final String DATABASE_1 = "jdbc_1";

    /**
     * Constant that defines the second database name.
     */
    protected static final String DATABASE_2 = "jdbc_2";

    /**
     * Bean used during the tests that throws a runtime exception.
     */
    private ItfContainerTransaction sfsbContainerTransactionRuntime = null;


    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestContainerTransactionException.class);


    /**
     * Initiates all things needed to execute the tests.
     * @throws Exception if an error occurs.
     */
    public void setup() throws Exception {
        // Inserts all database before execute the test
        // used because the container does not provide this feature yet
        EmbeddedHelper.bindDatasource();
        // creates the bean used to manages the databse in the server site.
        //cleans the transaction active in other test
        ExceptionHandleUtil.cleanTransaction();
        //creates the bean used during the tests
        createBeanRuntime();
        //deletes all tables
        deleteTable();
    }


    /**
     * Gets a new instance of the bean used in the runtime exception tests.
     * @param beanClass the bean class name used to make the lookup.
     * @throws Exception if a lookup error occurs.
     */
    public void createBeanRuntime(final Class beanClass) throws Exception {
        sfsbContainerTransactionRuntime = EJBHelper.getBeanRemoteInstance(beanClass, ItfContainerTransaction.class);
    }

    /**
     * Gets a new instance of the bean used in the runtime exception tests.
     * @throws Exception if a lookup error occurs.
     */
    public abstract void createBeanRuntime() throws Exception;


    /**
     * Verifies if the container manages the transaction among divers beans.The
     * first bean creates a table in the first database and calls other
     * bean(with transaction attribute REQUIRED) to insert a table in the second
     * database. After the both insertions, the first bean throws a RuntimeException
     * exception. Also, verifies if the container throws an EJBException, discards the bean and does the rollback in
     * this case(if the transaction atttribute of the first bean has a
     * transaction context).
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanReq() throws Exception {
        try {
            sfsbContainerTransactionRuntime.insertTablesUsingAuxBeanReq(DATABASE_1, DATABASE_2);
            fail("The container did not throw the EJBException.");
        } catch (EJBException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the container discarded the instance.
        if (!ExceptionHandleUtil.isDiscarded(sfsbContainerTransactionRuntime)) {
            fail("The bean was not discarded.");
        }

        // TODO - verifies if the container log the error.
    }

    /**
     * Verifies if the container manages the transaction among divers beans.The
     * first bean creates a table in the first database and calls other
     * bean(with transaction attribute NOT_SUPPORTS) to insert a table in the second
     * database. After the both insertions, the first bean throws a RuntimeException
     * exception. Also, verifies if the container throws an EJBException, discards the bean and does the rollback in
     * this case(if the transaction atttribute of the first bean has a
     * transaction context).
     * @throws Exception if an error during the tests occurs.
     */
    public void testCallOtherBeanNotSup() throws Exception {
        try {
            sfsbContainerTransactionRuntime.insertTablesUsingAuxBeanNotSup(DATABASE_1, DATABASE_2);
            fail("The container did not throw the EJBException.");
        } catch (EJBException e) {
            logger.debug("The bean threw an expected error during the execution {0}", e);
        }
        // verifies if the container discarded the instance.
        if (!ExceptionHandleUtil.isDiscarded(sfsbContainerTransactionRuntime)) {
            fail("The bean was not discarded.");
        }

        // TODO - verifies if the container log the error.
    }

    /**
     * Returns the bean that throws a runtime exception.
     * @return the bean.
     */
    public ItfContainerTransaction getRuntimeBean(){
        return sfsbContainerTransactionRuntime;
    }

    /**
     * Deletes the tables created during test.
     * @throws Exception if a lookup error occurs.
     *
     */
    public void deleteTable() throws Exception {
        // deletes the table after each test to avoid errors.
        ExceptionHandleUtil.deleteTable(DATABASE_1, ItfContainerTransaction.TABLE);
        ExceptionHandleUtil.deleteTable(DATABASE_2, ItfContainerTransaction.TABLE);
        // deletes the auxiliar bean table
        ExceptionHandleUtil.deleteTable(DATABASE_1, ItfTransactionMisc00.TABLE);
        ExceptionHandleUtil.deleteTable(DATABASE_2, ItfTransactionMisc00.TABLE);
    }


}
