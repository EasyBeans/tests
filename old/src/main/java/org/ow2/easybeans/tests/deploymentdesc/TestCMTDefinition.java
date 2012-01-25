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
 * $Id: TestCMTDefinition.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.ejb.EJBException;
import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.ItfCMTDeployDesc;
import org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.xmldescriptor.SFSBCMTDeployDesc;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can deploy an bean with the transaction attributes
 * defined in the deployment description. Item 13.3.7.2.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SFSBCMTDeployDesc and the SLSBDatabaseManager must be
 *              deployed to make the tests, and, the deployment descriptors must
 *              be used too.
 * @setup gets an instance of the SFSBCMTDeployDesc and the SLSBDatabaseManager.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestCMTDefinition {

    /**
     * The database used during the tests.
     */
    public static final String DB_NAME = "jdbc_1";

    /**
     * The table name.
     */
    public static final String TABLE_NAME = "test";

    /**
     * Bean used to verify the local access.
     */
    private ItfCMTDeployDesc bean;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestCMTDefinition.class);

    /**
     * Bean used to manage the database in the server side.
     */
    private ItfDatabaseManager slsbDatabaseManager;

    /**
     * Creates the stateful bean used during the tests.
     * @throws Exception if an error occurs during the lookup.
     */
    @BeforeMethod
    public void setup() throws Exception {
        bean = EJBHelper.getBeanRemoteInstance(SFSBCMTDeployDesc.class, ItfCMTDeployDesc.class);
        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);
        deleteTable(DB_NAME, TABLE_NAME);
    }

    /**
     * Verifies if the definition of trasaction attribute for all methods work.
     * The deployment descriptor is:<br> <container-transaction> <br> <method>
     * <br> <ejb-name>SFSBCMTDeployDescRemote</ejb-name> <br> <method-name>*</method-name><br>
     * </method> <br> <trans-attribute>Required</trans-attribute> <br>
     * </container-transaction><br>
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifyAllDefinition() throws Exception {
        // the transaction attribute defined for all methods is required, so the
        // method must work with or without a client transaction.

        // the method is working with transaction...
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        utx.begin();
        bean.insertTable01(DB_NAME, TABLE_NAME);
        utx.commit();

        // cleans the db to avoid error
        deleteTable(DB_NAME, TABLE_NAME);

        // the method is working without transaction
        bean.insertTable01(DB_NAME, TABLE_NAME);
    }

    /**
     * Verifies if the definition of the transaction for all method with the
     * same name works, as well as verifies the override of the wildcard.
     * @input -
     * @output the correct method execution.
     */
    @Test
    public void verifyMethodWithoutParameterDefinition() {
        // The transaction attribute is defined for all class methods that has
        // the name insertTable02. The parameters are not defined, so all
        // methods with the name insertTable02 have the same transaction
        // attribute.

        // The transaction attribute for the method with 2 parameters. The
        // deployment descriptor defines this method as mandatory, so an
        // exception must be throw when the method is called without a
        // transactional context.
        try {
            bean.insertTable02(DB_NAME, TABLE_NAME);
            fail("A method with transaction attribute mandatory is not throwing an EJBException"
                    + " when it is called without transaction context");
        } catch (Exception e) {
            assertTrue(e instanceof EJBException, "A method with transaction attribute "
                    + "mandatory is not throwing an EJBException when it is called without transaction context");
        }
        // cleans the db to avoid error
        deleteTable(DB_NAME, TABLE_NAME);

        // The transaction attribute for the method with 3 parameters. The
        // deployment descriptor defines this method as mandatory, so an
        // exception must be throw when the method is called withou a
        // transactional context.

        try {
            bean.insertTable02(DB_NAME, TABLE_NAME, 1);
            fail("A method with transaction attribute mandatory is not throwing an "
                    + "EJBException when it is called without transaction context");
        } catch (Exception e) {
            assertTrue(
                    e instanceof EJBException,
                    "A method with transaction attribute mandatory is not throwing an "
                    + "EJBException when it is called without transaction context");
        }
    }

    /**
     * Verifies if the definition of the transaction for a specific method, as
     * well as verifies the override of the wildcard.
     * @input -
     * @output the correct method execution.
     * @throws Exception if an error occurs.
     */
    @Test
    public void verifyMethodWithParameterDefinition() throws Exception {
        // The transaction attribute is defined for a method with the parameters
        // dbName and tableName, so only this method will have the transaction
        // attribute never. The other method with the same name, but with
        // diferent attributes, has the general tarsnaction attribute(REQUIRED).

        // The transaction attribute is never, so when the method is called with
        // a transactional context, an exception must be throw.
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try {
            utx.begin();
            bean.insertTable03(DB_NAME, TABLE_NAME);
            fail("A method with transaction attribute never is not throwing an "
                    + "EJBException when it is called with transaction context");
        } catch (Exception e) {
            assertTrue(e instanceof EJBException,
                    "A method with transaction attribute never is not throwing an "
                    + "EJBException when it is called with transaction context");
        } finally {
            utx.rollback();
        }

        // cleans the db to avoid error
        deleteTable(DB_NAME, TABLE_NAME);

        // the transaction attribute defined for all methods is required, so the
        // method must work with or without a client transaction.

        // the method is working with transaction...
        utx.begin();
        bean.insertTable03(DB_NAME, TABLE_NAME, 1);
        utx.commit();

        // cleans the db to avoid error
        deleteTable(DB_NAME, TABLE_NAME);

        // the method is working without transaction
        bean.insertTable03(DB_NAME, TABLE_NAME, 1);
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
