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
 * $Id: ItfEntityManagerFlushTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.flushoperation;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Verifies if the container manages the flush.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public interface ItfEntityManagerFlushTester {

    /**
     * The entity primary key.
     */
    Integer ENTITY_ID = new Integer(1);

    /**
     * The entity name.
     */
    String ENTITY_NAME = "test";

    /**
     * The alternative entity name.
     */
    String ENTITY_NAME_2 = "test2";

    /**
     * Creates the entity bean used during the test.
     */
    void startup();

    /**
     * Verifies if the default flush method is auto.
     */
    void verifyDefaultFlushMode();

    /**
     * Verifies if the container makes the flush after a query when the flush
     * mode is auto.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void setFlushModeAuto();

    /**
     * Verifies if the container does not make the flush after a query when the
     * flush mode is commit.
     */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    void setFlushModeCommit();

}
