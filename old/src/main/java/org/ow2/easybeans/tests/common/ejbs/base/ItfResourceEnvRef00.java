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
 * $Id: ItfResourceEnvRef00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.base;

/**
 * This interface is used to test resource environment entries that can be
 * declared with annotations or xml descriptors. In addition, beans that
 * are used to test resource injections should use this interface.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public interface ItfResourceEnvRef00 {

    /**
     * Checks a jdbc resource reference.
     */
    void checkJDBC();

    /**
     * Checks a jms connection factory resource reference.
     */
    void checkJMSConFactory();

    /**
     * Checks a jms queue connection factory resource reference.
     */
    void checkJMSQueueConFactory();

    /**
     * Checks a jms topic connection factory resource reference.
     */
    void checkJMSTopicConFactory();

    /**
     * Checks a jms queue resource reference.
     */
    void checkJMSQueue();

    /**
     * Checks a jms topic resource reference.
     */
    void checkJMSTopic();

    /**
     * Checks a javamail resource reference.
     */
    void checkMailSession();

    /**
     * Checks a url connection resource reference.
     */
    void checkUrl();

    /**
     * Checks an EJBContext resource reference.
     */
    void checkEJBContext();
}
