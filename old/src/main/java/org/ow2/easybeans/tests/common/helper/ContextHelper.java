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
 * $Id: ContextHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import static org.ow2.easybeans.tests.common.resources.EMFactoryTester.checkInstance;

import java.net.URL;

import javax.ejb.EJBContext;
import javax.ejb.TimerService;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.QueueConnectionFactory;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.mail.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import org.ow2.easybeans.tests.common.resources.EJBContextTester;
import org.ow2.easybeans.tests.common.resources.EntityManagerTester;
import org.ow2.easybeans.tests.common.resources.TimerServiceTester;
import org.ow2.easybeans.tests.common.resources.UserTransactionTester;
import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

/**
 * Used to do common operations on bean contexts.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public final class ContextHelper {

    /**
     * Logger.
     */
    private static Log logger = LogFactory.getLog(ContextHelper.class);

    /**
     * Error message.
     */
    public static final String ERROR_MSG_INJECTION = "The container did not inject the default value specified "
            + "in the descriptor.";

    /**
     * Error message.
     */
    public static final String ERROR_MSG_NOT_FOUND = "Entry did not find in the environment.";

    /**
     * Error message.
     */
    public static final String ERROR_MSG_SESSION_CONTEXT = "Error in access using the Session Context.";

    /**
     * Error message.
     */
    public static final String ERROR_MSG_JNDI_ACCESS = "Error in access using the JNDI API directly.";

    /**
     * Error message.
     */
    public static final String ERROR_REFERENCE_NULL = "Reference is null.";

    /**
     * Creates a new instance of ContextHelper.
     */
    private ContextHelper() {

    }

    /**
     * Checks the following items:
     * <li>if a simple environment entry value specified in the descriptor was
     * correctly injected;</li>
     * <li>if the session context can be used to access the simple environment
     * entry;</li>
     * <li>if the JNDI API can be used directly to access the simple
     * environment entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param <E> Element Type
     * @param ejbContext ejb context instance
     * @param entryName entry name in the environment
     * @param beanValue value obtained in the bean object.
     * @param expectedValue value expected by the element
     */
    @SuppressWarnings("unchecked")
    public static <E> void checkSimpleEntry(final EJBContext ejbContext, final String entryName, final E beanValue,
            final E expectedValue) {

        // Injection
        logger.debug("Checking injection. Name = {0}", entryName);
        if (!expectedValue.equals(beanValue)) {
            throw new IllegalStateException(ERROR_MSG_INJECTION + " Name: " + entryName);
        }
        logger.debug("Injection is ok. Name = {0}", entryName);

        checkSimpleEntry(ejbContext, entryName, expectedValue);
    }

    /**
     * Checks the following items:
     * <li>if the ejb context can be used to access the environment entry;</li>
     * <li>if the JNDI API can be used directly to access the environment
     * entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param <E> Element Type
     * @param ejbContext ejb context instance
     * @param entryName entry name in the environment
     * @param beanInterface bean interface.
     */
    @SuppressWarnings("unchecked")
    public static <E> void checkBeanRef(final EJBContext ejbContext, final String entryName, final E beanInterface) {

        logger.debug("Checking ejb reference. Name = {0}", entryName);

        // Session Context
        E sctxRef = (E) getEntryByEJBContext(ejbContext, entryName);
        checkBeanRef(sctxRef);

        // JNDI Access
        E jndiRef = (E) getEntryByJNDI(entryName);
        checkBeanRef(jndiRef);

        logger.debug("EJB reference is ok. Name = {0}", entryName);
    }

    /**
     * Gets an entry using the ejb context.
     * @param <E> entry type
     * @param ejbContext reference
     * @param entryName name in the enviroment
     * @return reference
     */
    @SuppressWarnings("unchecked")
    private static <E> E getEntryByEJBContext(final EJBContext ejbContext, final String entryName) {
        // Check if the entry exists in the environment
        logger.debug("Getting reference using the ejb context. Name = {0}", entryName);

        E sctxEntry = (E) ejbContext.lookup(entryName);

        if (sctxEntry == null) {
            logger.debug("Entry reference is null. Name = {0} ", entryName);
        }

        logger.debug("Reference was gotten. Name = {0}", entryName);

        return sctxEntry;
    }

    /**
     * Gets an entry using the JNDI API.
     * @param <E> entry type
     * @param entryName name in the enviroment
     * @return reference
     */
    @SuppressWarnings("unchecked")
    private static <E> E getEntryByJNDI(final String entryName) {
        // Check if the entry exists in the environment
        logger.debug("Getting reference using the JNDI API. Name = {0}", entryName);

        E eJNDI = null;
        try {
            Context initCtx = new InitialContext();
            Context myEnv = (Context) initCtx.lookup("java:comp/env");

            eJNDI = (E) myEnv.lookup(entryName);

            if (eJNDI == null) {
                logger.debug("Entry reference is null. Name = {0}", entryName);
            }
        } catch (NamingException e) {
            throw new IllegalStateException("The context could not be obtained or entry not found. Name = " + entryName);
        }

        logger.debug("Reference was gotten. Name = {0}", entryName);
        return eJNDI;
    }

    /**
     * Checks if a bean reference is not null.
     * @param <E> bean type
     * @param ref reference
     */
    private static <E> void checkBeanRef(final E ref) {
        logger.debug("Checking ejb reference.");

        if (ref == null) {
            throw new IllegalStateException(ERROR_REFERENCE_NULL);
        }

        logger.debug("Ejb reference is ok.");
    }

    /**
     * Checks the following items:
     * <li>if the ejb context can be used to access the simple environment
     * entry;</li>
     * <li>if the JNDI API can be used directly to access the simple
     * environment entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param <E> Element Type
     * @param ejbContext ejb context instance
     * @param entryName entry name in the environment.
     * @param expectedValue value expected by the element.
     */
    @SuppressWarnings("unchecked")
    public static <E> void checkSimpleEntry(final EJBContext ejbContext, final String entryName, final E expectedValue) {

        logger.debug("Checking simple entry. Name = {0}", entryName);

        // Session Context
        E sctxValue = (E) getEntryByEJBContext(ejbContext, entryName);

        if (!expectedValue.equals(sctxValue)) {
            throw new IllegalStateException(ERROR_MSG_SESSION_CONTEXT + " Entry: " + entryName);
        }

        // JNDI Access
        E eJNDI = (E) getEntryByJNDI(entryName);

        if (!expectedValue.equals(eJNDI)) {
            throw new IllegalStateException(ERROR_MSG_JNDI_ACCESS);
        }

        logger.debug("Simple entry is ok. Name = {0}", entryName);
    }

    /**
     * Checks the following items:
     * <li>if the resource was correctly injected;</li>
     * <li>if the ejb context can be used to access the resource;</li>
     * <li>if the JNDI API can be used directly to access the resource;</li>
     * If fails, an IllegalStateException is thrown.
     * @param <E> Element Type
     * @param ejbContext session Context instance
     * @param resource resource object
     * @param resourceName resource name in the environment
     */
    @SuppressWarnings("unchecked")
    public static <E> void checkResource(final EJBContext ejbContext, final E resource, final String resourceName) {
        // Injection
        checkResource(resource);
        // Environment access
        checkResource(ejbContext, resourceName);
    }

    /**
     * Checks the following items:
     * <li>if the ejb context can be used to access the entry;</li>
     * <li>if the JNDI API can be used directly to access the entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param <E> Element Type
     * @param ejbContext ejb context instance
     * @param entryName entry name in the environment
     */
    @SuppressWarnings("unchecked")
    public static <E> void checkResource(final EJBContext ejbContext, final String entryName) {

        logger.debug("Checking resource. Name = {0}", entryName);

        // Session Context
        E sctxEntry = (E) getEntryByEJBContext(ejbContext, entryName);
        checkResource(sctxEntry);

        // JNDI Access
        E eJNDI = (E) getEntryByJNDI(entryName);
        checkResource(eJNDI);

        logger.debug("Resource is ok. Name = {0}", entryName);
    }

    /**
     * Checks if an entry is working properly.
     * @param <E> entry type
     * @param entry reference
     */
    public static <E> void checkResource(final E entry) {
        if (entry == null) {
            throw new IllegalStateException(ERROR_REFERENCE_NULL);
        }

        Class entryClass = entry.getClass();
        String entryClassName = entryClass.getName();

        try {
            if (DataSource.class.isAssignableFrom(entryClass)) {
                // Checks DataSource
                logger.debug("Checking DataSource.");
                ((DataSource) entry).getConnection().close();

            } else if (Topic.class.isAssignableFrom(entryClass)) {
                // Checks Topic
                logger.debug("Checking Topic.");
                ((Topic) entry).getTopicName();

            } else if (Queue.class.isAssignableFrom(entryClass)) {
                // Checks Queue
                logger.debug("Checking Queue.");
                ((Queue) entry).getQueueName();

            } else if (ConnectionFactory.class.isAssignableFrom(entryClass)) {
                // Checks ConnectionFactory
                logger.debug("Checking ConnectionFactory.");
                ((ConnectionFactory) entry).createConnection().close();

            } else if (QueueConnectionFactory.class.isAssignableFrom(entryClass)) {
                // Checks QueueConnectionFactory
                logger.debug("Checking QueueConnectionFactory.");
                ((QueueConnectionFactory) entry).createConnection().close();

            } else if (TopicConnectionFactory.class.isAssignableFrom(entryClass)) {
                // Checks TopicConnectionFactory
                logger.debug("Checking TopicConnectionFactory.");
                ((TopicConnectionFactory) entry).createConnection().close();

            } else if (Session.class.isAssignableFrom(entryClass)) {
                // Checks Mail Session
                logger.debug("Checking Mail Session.");
                ((Session) entry).getProperties().keySet();

            } else if (URL.class.isAssignableFrom(entryClass)) {
                // Checks TopicConnectionFactory
                logger.debug("Checking URL.");
                ((URL) entry).getHost();

            } else if (UserTransaction.class.isAssignableFrom(entryClass)) {
                // Checks UserTransaction
                logger.debug("Checking UserTransaction.");
                UserTransactionTester.checkInstance(((UserTransaction) entry));

            } else if (TimerService.class.isAssignableFrom(entryClass)) {
                // Checks TimerService
                logger.debug("Checking TimerService.");
                TimerServiceTester.checkInstance(((TimerService) entry));

            } else if (EJBContext.class.isAssignableFrom(entryClass)) {
                // Checks EJBContext
                logger.debug("Checking EJBContext.");
                EJBContextTester.checkInstance(((EJBContext) entry));

            } else if (EJBContext.class.isAssignableFrom(entryClass)) {
                // Checks EJBContext
                logger.debug("Checking EJBContext.");
                EJBContextTester.checkInstance(((EJBContext) entry));

            } else {
                logger.debug("Unknow resource. Type = {0}", entryClassName);
                throw new IllegalStateException("Unknow resource. Type = " + entryClassName);
            }
        } catch (Exception e) {
            logger.debug("Exception: {0}", e);
            throw new IllegalStateException("Exception: " + e.toString());
        }
    }

    /**
     * Checks the following items:
     * <li>if the injected reference is ok;</li>
     * <li>if the ejb context can be used to access the persitence unit entry;</li>
     * <li>if the JNDI API can be used directly to access the persistence unit
     * entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param ref reference
     * @param ejbContext ejb context instance
     * @param pUnitName persistence unit name
     */
    public static void checkEntityManagerFactory(final EJBContext ejbContext, final EntityManagerFactory ref,
            final String pUnitName) {
        logger.debug("Checking Entity Manager Factory. Name = {0}", pUnitName);

        // Check injection
        checkEntityManagerFactory(ref);

        // Environment
        checkEntityManagerFactory(ejbContext, pUnitName);

        logger.debug("Entity Manager Factory is ok. Name = {0}", pUnitName);
    }

    /**
     * Checks the following items:
     * <li>if the ejb context can be used to access the persitence unit entry;</li>
     * <li>if the JNDI API can be used directly to access the persistence unit
     * entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param ejbContext ejb context instance
     * @param pUnitName persistence unit name
     */
    public static void checkEntityManagerFactory(final EJBContext ejbContext, final String pUnitName) {
        logger.debug("Checking Entity Manager Factory. Name = {0}", pUnitName);

        // Session Context
        EntityManagerFactory sctxEntry = getEntryByEJBContext(ejbContext, pUnitName);
        checkEntityManagerFactory(sctxEntry);

        // JNDI Access
        EntityManagerFactory eJNDI = getEntryByJNDI(pUnitName);
        checkEntityManagerFactory(eJNDI);

        logger.debug("Entity Manager Factory is ok. Name = {0}", pUnitName);
    }

    /**
     * Checks if an entity manager factory reference is working properly.
     * @param ref reference
     */
    public static void checkEntityManagerFactory(final EntityManagerFactory ref) {
        try {
            checkInstance(ref, "cemf");
        } catch (Exception e) {
            throw new IllegalStateException("Error checking Entity Manager Factory reference.");
        }
    }

    /**
     * Checks the following items:
     * <li>if the injected reference is ok;</li>
     * <li>if the ejb context can be used to access the persitence unit entry;</li>
     * <li>if the JNDI API can be used directly to access the persistence
     * context entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param ref reference
     * @param ejbContext ejb context instance
     * @param pUnitName persistence unit name
     */
    public static void checkEntityManager(final EJBContext ejbContext, final EntityManager ref, final String pUnitName) {
        logger.debug("Checking Entity Manager Factory. Name = {0}", pUnitName);

        // Check injection
        checkEntityManager(ref);

        // Environment
        checkEntityManager(ejbContext, pUnitName);

        logger.debug("Entity Manager Factory is ok. Name = {0}", pUnitName);
    }

    /**
     * Checks the following items:
     * <li>if the ejb context can be used to access the persitence unit entry;</li>
     * <li>if the JNDI API can be used directly to access the persistence
     * context entry;</li>
     * If fails, an IllegalStateException is thrown.
     * @param ejbContext ejb context instance
     * @param pUnitName persistence unit name
     */
    public static void checkEntityManager(final EJBContext ejbContext, final String pUnitName) {
        logger.debug("Checking Entity Manager. Name = {0}", pUnitName);

        // Session Context
        EntityManager sctxEntry = getEntryByEJBContext(ejbContext, pUnitName);
        checkEntityManager(sctxEntry);

        // JNDI Access
        EntityManager eJNDI = getEntryByJNDI(pUnitName);
        checkEntityManager(eJNDI);

        logger.debug("Entity Manager is ok. Name = {0}", pUnitName);
    }

    /**
     * Checks if an entity manager reference is working properly.
     * @param ref reference
     */
    public static void checkEntityManager(final EntityManager ref) {
        try {
            EntityManagerTester.checkInstance(ref, "cem");
        } catch (Exception e) {
            throw new IllegalStateException("Error checking Entity Manager Factory reference.", e);
        }
    }

}
