/*
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
 * $Id:$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.stateful.timeout;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.transaction.Status;
import javax.transaction.UserTransaction;

/**
 * @author Loic Albertin
 */
@Stateful(mappedName = "BMTInTxBean")
@StatefulTimeout(value = 50, unit = TimeUnit.MILLISECONDS)
@TransactionManagement(TransactionManagementType.BEAN)
public class BMTInTxBean extends AbstractTimeoutBean {

    @Resource
    private UserTransaction userTransaction;


    @Override
//    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void init() {
        super.init();
        try {
            userTransaction.begin();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to start a transaction", e);
        }
    }

    @Override
//    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void ping() {
        super.ping();
        try {
            if (userTransaction.getStatus() == Status.STATUS_ACTIVE) {
                userTransaction.commit();
            }
        } catch (Exception e) {
            throw new IllegalStateException("Unable to commit current transaction", e);
        }

    }
}
