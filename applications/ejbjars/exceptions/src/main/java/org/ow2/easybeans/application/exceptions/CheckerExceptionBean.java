/**
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id: MetadataMerge.java 4697 2009-02-23 17:03:23Z sauthieg $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.exceptions;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;


/**
 * Bean that check if rollback is done or not.
 * Use of bean tx so that we can have access to TM.
 * @author Florent Benoit
 */
@Stateless(mappedName="CheckerExceptionBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class CheckerExceptionBean implements ICheckerException {
    
    @EJB
    private IException bean;
    
    @Resource
    private UserTransaction userTransaction;

    
    public void checkDefaultApplicationException() {
        // Get user transaction
        try {
            userTransaction.begin();
            bean.applicationException();
            throw new IllegalStateException("Application Exception should have been expected");
        } catch (NotSupportedException e) {
            throw new IllegalStateException("Unable to start tx", e);
        } catch (SystemException e) {
            throw new IllegalStateException("Unable to start tx", e);
        } catch (CheckedApplicationException e) {
            // Expected exception, check if the transaction is not rollbacked
            checkTransaction(javax.transaction.Status.STATUS_ACTIVE);
        } finally {
            // rollback of the transaction
            try {
                userTransaction.rollback();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            } catch (SecurityException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            } catch (SystemException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            }
        }
    }

    
    public void checkRollbackApplicationException() {
        // Get user transaction
        try {
            userTransaction.begin();
            bean.applicationRollbackException();
            throw new IllegalStateException("Application Exception should have been expected");
        } catch (NotSupportedException e) {
            throw new IllegalStateException("Unable to start tx", e);
        } catch (SystemException e) {
            throw new IllegalStateException("Unable to start tx", e);
        } catch (CheckedRollbackApplicationException e) {
            // Expected exception, check if the transaction is rollbacked
            checkTransaction(javax.transaction.Status.STATUS_MARKED_ROLLBACK);
        } finally {
            // rollback of the transaction
            try {
                userTransaction.rollback();
            } catch (IllegalStateException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            } catch (SecurityException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            } catch (SystemException e) {
                throw new IllegalStateException("Unable to rollback tx", e);
            }
        }
    }    
    
    
    protected void checkTransaction(int expectedTransactionStatus) {
        int currentStatus = 0;
        try {
            currentStatus = userTransaction.getStatus();
        } catch (SystemException e) {
            throw new IllegalStateException("Unable to get current transaction status");
        }
        if (expectedTransactionStatus != currentStatus) {
            throw new IllegalStateException("Invalid transaction status. Expected status '" + expectedTransactionStatus + "' and there is '" + currentStatus + "'");
        }
    }
    
}
