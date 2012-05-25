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

package org.ow2.easybeans.application.statefulsync;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJBException;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

public abstract class AbsStatefulBean implements IStatefulSessionBean {

    private List<Operations> operations = null;

    /**
     * Initialize the amounts for with and without Tx.
     */
    public AbsStatefulBean() {
        this.operations = new ArrayList<Operations>();
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public void clearOperations() {
        this.operations.clear();
    }
    
    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    public List<Operations> getOperations() {
        return operations;
    }
    
    
    protected void afterBeginCall() {
        operations.add(Operations.AFTER_BEGIN);
    }
    
    protected void afterCompletionCall(boolean committed) {
        if (committed) {
            operations.add(Operations.AFTER_COMPLETION_COMMIT);
        } else {
            operations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        }
    }
    
    protected void beforeCompletionCall() {
        operations.add(Operations.BEFORE_COMPLETION);
    }
    
    /**
     * Dummy call used to check session synchronization methods.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void dummyCall() {
        
    }
    
    /**
     * Dummy call used to check session synchronization methods.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void dummyCallRollback() {
        throw new EJBException("Exception that will rollback the current transaction");
    }
    
    
}
