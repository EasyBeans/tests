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
 * $Id: ResourceLevelTransTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.javasesupport;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;
import static org.testng.Assert.assertTrue;
import static org.testng.Assert.fail;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

import org.ow2.easybeans.tests.common.ejbs.entity.book.Book;
import org.ow2.util.log.Log;

/**
 * Verifies if the container supports clients j2ee.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class ResourceLevelTransTester {

    /**
     * The entity ID.
     */
    public static final int ID = 1;

    /**
     * The entity name.
     */
    public static final String ENTITY_NAME = "test";

    /**
     * The EntityManagerFactory that is used during test execution.
     */
    private EntityManagerFactory entityManagerFactory;

    /**
     * The logger.
     */
    private Log logger;

    /**
     * Creates the EntityManagerFactory and cleans the db.
     */
    public ResourceLevelTransTester() {
        // gets the EntitymanagerFactory for the persistence unite book
        entityManagerFactory = Persistence.createEntityManagerFactory("book");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Book bookResult = entityManager.find(Book.class, new Integer(ID));
        if (bookResult != null) {
            entityManager.remove(bookResult);
        }
    }

    /**
     * Verifies if the rollback works.
     */
    public void resourceLevelRollback() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // begins the transaction
        entityManager.getTransaction().begin();
        // creates a book
        Book book = new Book();
        book.setId(ID);
        book.setName(ENTITY_NAME);
        entityManager.persist(book);
        // makes a rollback
        entityManager.getTransaction().rollback();
        entityManager.close();
        // verifies if the container made the rollback
        Book bookResult = entityManager.find(Book.class, new Integer(ID));
        assertNull(bookResult, "The container did not make the rollback");
    }

    /**
     * Verifies if the serRollbackOnly works.
     */
    public void setRollbackOnly() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // begins the transaction and sets the rollbackonly
        entityManager.getTransaction().begin();
        entityManager.getTransaction().setRollbackOnly();
        // creates a book
        Book book = new Book();
        book.setId(ID);
        book.setName(ENTITY_NAME);
        entityManager.persist(book);
        // verifies if the container registered the rollback.
        assertTrue(entityManager.getTransaction().getRollbackOnly(),
                "The transaction is marked as rollback, but the container returned false for the method getRollbackOnly()");

        // tries to make the commit, the container must throw an exception.
        try {
            entityManager.getTransaction().commit();
            fail("The method setRollbackOnly was called, so the transaction cannot make the commit.");
        } catch (RollbackException e) {
            logger.info("The bean threw an expected exception");
        }
        entityManager.close();

        // verifies if the transaction was rolled back
        Book bookResult = entityManager.find(Book.class, new Integer(ID));
        assertNull(bookResult, "The container did not make the rollback");
    }

    /**
     * Verifies if the commit works.
     */
    public void resourceLevelCommit() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        // begins the trasnaction
        entityManager.getTransaction().begin();
        // inserts a book
        Book book = new Book();
        book.setId(ID);
        book.setName(ENTITY_NAME);
        entityManager.persist(book);
        // verifies if the transaction is marked as ACTIVE
        assertTrue(entityManager.getTransaction().isActive(),
                "The transaction is active, but the container returned false when the method isActive was called.");
        // verify if the rollbackonly is not set.
        assertFalse(entityManager.getTransaction().getRollbackOnly(),
                "The transaction is not marked as rollback, but the container returned true for the method getRollbackOnly()");
        // makes a commit
        entityManager.getTransaction().commit();
        entityManager.close();
        // verifies if the bean is persistent.
        Book bookResult = entityManager.find(Book.class, new Integer(ID));
        assertNotNull(bookResult, "The container did not make the rollback");

    }
}
