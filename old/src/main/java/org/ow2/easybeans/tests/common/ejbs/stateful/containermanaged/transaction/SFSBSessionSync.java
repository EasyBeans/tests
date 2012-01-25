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
 * $Id: SFSBSessionSync.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.transaction;

import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.SessionSynchronization;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.NamingException;

import org.ow2.easybeans.tests.common.db.TableManager;

/**
 * Inserts tables in two database using the different types of transastion
 * attribute for container-managed transaction. Uses the interface
 * SesionSynchronization, so calls the callback methods afterBegin,
 * beforeCompletion and afterCompletation.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfSessionSync.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
public class SFSBSessionSync implements ItfSessionSync, SessionSynchronization {


    /**
     * The database1 name in the registry.
     */
    private String strDB1;

    /**
     * The database2 name in the registry.
     */
    private String strDB2;

    /**
     * Indicates if the transaction rolled back.
     */
    private boolean bolRollback;

    /**
     * The SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Inserts the table test the database.
     * @param dbName is the name of the database in the registry.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    private void insertTable(final String dbName) throws SQLException, NamingException {
        TableManager tableManager = new TableManager(dbName);
        tableManager.insertTable(TABLE);
    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute MANDATORY.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.MANDATORY)
    public void insertTableMandatory() throws SQLException, NamingException {
        insertTable(strDB2);

    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute NEVER.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.NEVER)
    public void insertTableNever() throws SQLException, NamingException {
        insertTable(strDB2);
    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute NOT_SUPPORTED.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.NOT_SUPPORTED)
    public void insertTableNotSupported() throws SQLException, NamingException {
        insertTable(strDB2);
    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute REQUIRED.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void insertTableRequired() throws SQLException, NamingException {
        insertTable(strDB2);
    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute REQUIRED_NEW.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.REQUIRES_NEW)
    public void insertTableRequiredNew() throws SQLException, NamingException {
        insertTable(strDB2);
    }

    /**
     * Inserts the table test in the second database with the transaction
     * attribute SUPPORTS.
     * @throws SQLException if a databse error occurs.
     * @throws NamingException if a lookup error occurs.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void insertTableSupports() throws SQLException, NamingException {
        insertTable(strDB2);
    }

    /**
     * Initializes the bean with the database name in the registry.
     * @param dbName1 database1 name in the registry.
     * @param dbName2 database2 name in the registry.
     */
    @TransactionAttribute(value = TransactionAttributeType.SUPPORTS)
    public void startup(final String dbName1, final String dbName2) {
        strDB1 = dbName1;
        strDB2 = dbName2;
        bolRollback = false;
    }

    /**
     * Inserts the table test in the first database. This callback method mustbe
     * called before the method with transaction.
     * @throws EJBException if a system error occurs.
     * @throws RemoteException used to maintain the compatibility with EJB 1.1.
     */
    public void afterBegin() throws EJBException, RemoteException {
        try {
            insertTable(strDB1);
        } catch (SQLException e) {
            throw new EJBException("SQLError " + e.getMessage());
        } catch (NamingException e) {
            throw new EJBException("LookupError " + e.getMessage());
        }
        // sets the rollback as false
        bolRollback = false;

    }

    /**
     * Verifies if the transaction was commited. If the transaction was
     * committed, the method throws an error. The beforeCompletion method
     * always call the setRolbackOnly, so the transaction never can be commited.
     * @param commited says if the transaction was commited.
     * @throws EJBException if a system error occurs.
     * @throws RemoteException used to maintain the compatibility with EJB 1.1.
     */
    public void afterCompletion(final boolean commited) throws EJBException, RemoteException {
        // if the container made a commit, it is an error, because the set
        // rollback only is active.
        if (commited) {
            throw new EJBException("The bean cannot make the commit.");
        }
        // says that the setRollbackOnly was called.
        bolRollback = true;
    }

    /**
     * Calls the setRollbackOnly.
     * @throws EJBException if a system error occurs.
     * @throws RemoteException used to maintain the compatibility with EJB 1.1.
     */
    public void beforeCompletion() throws EJBException, RemoteException {
        sessionContext.setRollbackOnly();
    }

    /**
     * Verifies if the transaction was rolled back.
     * @return true if the transaction was rolled back, false otherwise.
     */
    public boolean isRolledback() {
        return bolRollback;
    }

}
