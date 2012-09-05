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

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

/**
 * @author Loic Albertin
 */
@Stateful(mappedName = "simpleTimeoutBean")
@StatefulTimeout(value = 100, unit = TimeUnit.MILLISECONDS)
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SimpleTimeoutBean extends AbstractTimeoutBean{

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public void init() {
        super.init();
    }

    @TransactionAttribute(TransactionAttributeType.SUPPORTS)
    @Override
    public void ping() {
        super.ping();
    }
}
