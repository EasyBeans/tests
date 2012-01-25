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
 * $Id: SFSBTransAttributeTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Verifies if the container sets all types of transaction attributes that are
 * defined by the deployment descriptor.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfTransAttributeTester.class)
public class SFSBTransAttributeTester implements ItfTransAttributeTester {

    /**
     * Bean that has the transaction attribute mandatory defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefMandatory")
    private ItfTransAttributeDefinition beanMandatory;

    /**
     * Bean that has the transaction attribute required defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefRequired")
    private ItfTransAttributeDefinition beanRequired;

    /**
     * Bean that has the transaction attribute requires_new defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefRequiresNew")
    private ItfTransAttributeDefinition beanRequiresNew;

    /**
     * Bean that has the transaction attribute supports defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefSupports")
    private ItfTransAttributeDefinition beanSupports;

    /**
     * Bean that has the transaction attribute not supported  defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefNotSupported")
    private ItfTransAttributeDefinition beanNotSupported;

    /**
     * Bean that has the transaction attribute never defined in the
     * deployment descriptor.
     */
    @EJB(beanName = "SFSBTransAttributeDefNever")
    private ItfTransAttributeDefinition beanNever;

    /**
     * Used to manipulate the data in the database.
     */
    @EJB
    private ItfDatabaseManager slsbDatabaseManager;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(SFSBTransAttributeTester.class);

    /**
     * Verifies if the client use the same transaction that the bean. Creates a
     * transaction, call a bean method that acess the JDBC and after makes a
     * rollback. If the bean uses the same transaction, the data in the database
     * must be rolled back.
     * @param bean the bean that is verified.
     * @return true if the bean uses the same transaction that the client, false otherwise.
     * @throws Exception if an error occurs.
     */
    private boolean usesClientTransaction(final ItfTransAttributeDefinition bean) throws Exception {
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try {
            utx.begin();
            bean.insertTableCorrectMandatory(DB_NAME, TABLE_NAME);
        } finally {
            utx.rollback();
        }
        try {
            slsbDatabaseManager.verifyTable(DB_NAME, TABLE_NAME);
            return false;
        } catch (SQLException e) {
            return true;
        }
    }

    /**
     * Verifies if the bean has a transaction context.
     * @param bean the bean that is verified.
     * @return true if the bean uses a transaction, false otherwise.
     * @throws Exception if an error occurs.
     */
    private boolean hasTransaction(final ItfTransAttributeDefinition bean) throws Exception {
        bean.insertTableErrorMandatory(DB_NAME, TABLE_NAME);
        try {
            slsbDatabaseManager.verifyTable(DB_NAME, TABLE_NAME);
            return false;
        } catch (SQLException e) {
            return true;
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the default transaction
     * attribute value. Also, verifies if the transaction attribute type is really mandatory.
     * @throws Exception if an error occurs.
     */
    public void testMandatory() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);

        UserTransaction utx = TransactionHelper.getUserTransaction();
        try {
            utx.begin();
            beanMandatory.insertTableCorrect(DB_NAME, TABLE_NAME);
        } finally {
            utx.rollback();
        }
        try {
            slsbDatabaseManager.verifyTable(DB_NAME, TABLE_NAME);
            fail("The deployment descriptor did not override the "
                   + "annotation. The bean did not use the client transaction in mandatory mode.");
        } catch (SQLException e) {
            logger.debug("The bean thron an expected exception{0}", e);
        }

        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);

        try {
            beanMandatory.insertTableCorrect(DB_NAME, TABLE_NAME);
        } catch (EJBException e) {
            logger.debug("The bean thron an expected exception{0}", e);
            fail("The deployment descriptor did not override the annotation. "
                    + "The bean was called withou a transaction context in a mandatory mode and there was not exception.");
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really required.
     * @throws Exception if an error occurs.
     */
    public void testRequired() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolClientTransaction = usesClientTransaction(beanRequired);
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolHasTransaction = hasTransaction(beanRequired);
        if (!(bolClientTransaction && bolHasTransaction)) {
            fail("The container did not override the transaction attribute for REQUIRED");
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really requireds new.
     * @throws Exception if an error occurs.
     */
    public void testRequiresNew() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolClientTransaction = usesClientTransaction(beanRequiresNew);
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolHasTransaction = hasTransaction(beanRequiresNew);
        if (!(!bolClientTransaction && bolHasTransaction)) {
            fail("The container did not override the transaction attribute for REQUIRES_NEW");
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really supports.
     * @throws Exception if an error occurs.
     */
    public void testSupports() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolClientTransaction = usesClientTransaction(beanSupports);
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolHasTransaction = hasTransaction(beanSupports);
        if (!(bolClientTransaction && !bolHasTransaction)) {
            fail("The container did not override the transaction attribute for SUPPORTS");
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really not supported.
     * @throws Exception if an error occurs.
     */
    public void testNotSupported() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolClientTransaction = usesClientTransaction(beanNotSupported);
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolHasTransaction = hasTransaction(beanNotSupported);
        if (bolClientTransaction || bolHasTransaction) {
            fail("The container did not override the transaction attribute for NOT_SUPPORTED");
        }
    }

    /**
     * Verifies if the deployment descriptor overrides the transaction
     * attribute mandatory. Also, verifies if the transaction attribute type is really never.
     * @throws Exception if an error occurs.
     */
    public void testNever() throws Exception {
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolClientTransaction = true;
        try {
            usesClientTransaction(beanNever);
        } catch (EJBException e) {
            bolClientTransaction = false;
        }
        //cleans the database to avoid errors.
        deleteTable(DB_NAME, TABLE_NAME);
        boolean bolHasTransaction = hasTransaction(beanNever);
        if (bolClientTransaction || bolHasTransaction) {
            fail("The container did not override the transaction attribute for NEVER");
        }
    }

    /**
     * Deletes the table in the database.
     * @param dbName the database name in the registry.
     * @param tableName the table name.
     */
    protected void deleteTable(final String dbName, final String tableName) {
        // deletes the table after each test to avoid errors.
        try {
            slsbDatabaseManager.deleteTable(dbName, tableName);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }

}
