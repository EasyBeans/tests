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
 * $Id: TestAppException.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.deploymentdesc;

import static org.testng.Assert.fail;

import java.sql.SQLException;

import javax.naming.NamingException;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ItfDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.SLSBDatabaseManager;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.exception.ItfExceptionXML;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.exception.SLSBExceptionXML;
import org.ow2.easybeans.tests.common.exception.CustomException00;
import org.ow2.easybeans.tests.common.exception.CustomException01;
import org.ow2.easybeans.tests.common.exception.IllegalException;
import org.ow2.easybeans.tests.common.exception.RollbackApplicationException;
import org.ow2.easybeans.tests.common.helper.EJBHelper;
import org.ow2.easybeans.tests.common.helper.TransactionHelper;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Verifies if the container can set the application exception defined by
 * deployment descriptor.
 * @reference JSR 220 - FINAL RELEASE
 * @requirement the bean SLSBExceptionXML and SLSBDatabaseManager must be
 *              deployed to make the tests, and, the deployment descriptor in
 *              the SLSBExceptionXML package must be used too.
 * @setup gets an instance of the SLSBExceptionXML and SLSBDatabaseManager.
 *        Also, it cleans the database(it drops the table)
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class TestAppException {

    /**
     * The database used during the tests.
     */
    public static final String DB_NAME = "jdbc_1";

   /**
     * Bean used to verify the local access.
     */
    private ItfExceptionXML bean;

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(TestAppException.class);

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
        bean = EJBHelper.getBeanRemoteInstance(SLSBExceptionXML.class, ItfExceptionXML.class);
        // creates the bean used to manages the databse in the server site.
        slsbDatabaseManager = EJBHelper.getBeanRemoteInstance(SLSBDatabaseManager.class, ItfDatabaseManager.class);
        deleteTable(DB_NAME);
    }

    /**
     * Verifies if the definition if an application exception(with rollback =
     * false) in the deployment descriptor works properly. When an application
     * exception with rollback = false occurs, the container must not mark the
     * transaction for rollback. Also, the container must re-throw the
     * exception.
     * @input -
     * @output the correct method execution without exceptions.
     * @throws Exception if some error occurs during the tests.
     */
    @Test
    public void testAppExceptionWithoutRollback() throws Exception{
        //verifies if the application exception is re-throw
        //the exception is not a checked exception
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try{
            utx.begin();
            bean.createTableWithAppException(DB_NAME);
            fail("The container did not re-throw the application exception.");
        }catch(CustomException01 e){
           logger.debug("The container threw an expected exception {0}", e);
        }finally{
            // the container must not mark the transaction for rollback, so the
            // commit should work.
            utx.commit();
        }
        //verifies if the commit worked
        verifyTable(DB_NAME);
    }


    /**
     * Verifies if the definition if an application exception overrided in a
     * deployment descriptor works properly. The annotation defines the
     * rollback as true, however the deployment descriptor defines
     * the transaction attribute as false. The deployment descriptor must
     * override the annotation value for the rollback. When an application
     * exception with rollback = false occurs, the container must not mark the
     * transaction for rollback. Also, the container must re-throw the
     * exception.
     * @input -
     * @output the correct method execution without exceptions.
     * @throws Exception if some error occurs during the tests.
     */
    @Test
    public void testAppExceptionWithRollbackOverride() throws Exception{
        //verifies if the application exception is re-throw
        //the exception is not a checked exception
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try{
            utx.begin();
            bean.createTableWithAppExceptionOverride(DB_NAME);
            fail("The container did not re-throw the application exception.");
        }catch(RollbackApplicationException e){
           logger.debug("The container threw an expected exception {0}", e);
        }finally{
            // the container must not mark the transaction for rollback, so the
            // commit should work.
            utx.commit();
        }
        //verifies if the commit worked
        verifyTable(DB_NAME);
    }
    /**
     * Verifies if the definition if an application exception(with rollback =
     * true) in the deployment descriptor works properly. When an application
     * exception with rollback = true occurs, the container must mark the
     * transaction for rollback. Also, the container must re-throw the
     * exception.
     * @input -
     * @output the correct method execution without exceptions.
     * @throws Exception if some error occurs during the tests.
     */
    @Test
    public void testAppExceptionWithRollback() throws Exception{
        //verifies if the application exception is re-throw
        //the exception is a checked exception
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try{
            utx.begin();
            bean.createTableWithAppExceptionAndRollback(DB_NAME);
            fail("The container did not re-throw the application exception.");
        }catch(CustomException00 e){
           logger.debug("The container threw an expected exception {0}", e);
        }finally{
            // the container must mark the transaction for rollback, so the
            // commit should not work.
            try{
                utx.commit();
                fail("The container did not mark the transaction for rollback");
            }catch(Exception e){
                logger.debug("The container threw an expected exception {0}", e);
            }
        }
    }


    /**
     * Verifies if the definition if an application exception(with the dafault rollback
     * value) in the deployment descriptor works properly. When an application
     * exception with rollback defult value(false) occurs, the container must not mark the
     * transaction for rollback. Also, the container must re-throw the
     * exception.
     * @input -
     * @output the correct method execution without exceptions.
     * @throws Exception if some error occurs during the tests.
     */
    @Test
    public void testAppExceptionWithRollbackDefault() throws Exception{
        //verifies if the application exception is re-throw
        //the exception is not a checked exception
        UserTransaction utx = TransactionHelper.getInternalUserTransaction();
        try{
            utx.begin();
            bean.createTableWithAppExceptionDefault(DB_NAME);
            fail("The container did not re-throw the application exception.");
        }catch(IllegalException e){
           logger.debug("The container threw an expected exception {0}", e);
        }finally{
            // the container must not mark the transaction for rollback, so the
            // commit should work.
            utx.commit();
        }
        //verifies if the commit worked
        verifyTable(DB_NAME);
    }


    /**
     * Verifies if the table was created in the database.
     * @param database the database name.
     * @throws NamingException if a lookup error occurs.
     * @throws SQLException if a database error occurs.
     */
    protected void verifyTable(final String database) throws NamingException, SQLException {
        slsbDatabaseManager.verifyTable(database, ItfExceptionXML.TABLE);
    }

    /**
     * Deletes the table in the database.
     * @param dbName the database name in the registry.
     */
    protected void deleteTable(final String dbName) {
        // deletes the table after each test to avoid errors.
        try {
            slsbDatabaseManager.deleteTable(dbName, ItfExceptionXML.TABLE);
        } catch (SQLException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        } catch (NamingException e) {
            logger.debug("The table delete threw an error during the execution {0}", e);
        }
    }


}
