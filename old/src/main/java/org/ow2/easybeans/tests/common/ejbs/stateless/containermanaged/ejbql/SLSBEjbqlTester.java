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
 * $Id: SLSBEjbqlTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejbql;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ow2.easybeans.tests.common.ejbs.entity.customer.Address;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Category;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Customer;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Product;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.ProductOrder;

/**
 * Verifies some queries in EJB QL.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless
@Remote(ItfEjbqlTester.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SLSBEjbqlTester implements ItfEjbqlTester {

    /**
     * The identifier interval between the group of products that are in the
     * different ProductOrder.
     */
    private static final int INTERVAL_BETWEEN_ID = 10;

    /**
     * The number of product order.
     */
    private static final int NUMBER_OF_ORDERS = 8;

    /**
     * The list of descriptions.
     */
    private static final String[] DESCRIPTION_VALUES = {"a", "b", "c", "d", "e", "f", "g", "h"};

    /**
     * The persistence context used during the tests.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Creates a number of products that is defined in the parameter quantity.
     * The identifier are sequencial and the initial id is the parameter
     * initialIdNumber.The description is a letter. The price is a value between
     * 0 and the the quantity of products.
     * @param quantity the quantity of products creadtesd
     * @param initialIdNumber the initial id number.
     * @return the list of the products created.
     */
    private List<Product> createProducts(final int quantity, final int initialIdNumber) {
        String[] strDescriptionValues = DESCRIPTION_VALUES;
        List<Product> lstProducts = new ArrayList<Product>();
        // creates the product.
        for (int i = 0; i < quantity; i++) {
            Product product = new Product();
            product.setId(i + initialIdNumber);
            product.setDescription(strDescriptionValues[i]);
            product.setPrice(i);
            // sets the category for a value between the intial category id = 0
            // and the maximum= 4
            product.setCategory(entityManager.getReference(Category.class, new Long(i
                    % (strDescriptionValues.length / 2))));
            entityManager.persist(product);
            entityManager.flush();
            lstProducts.add(product);
        }
        return lstProducts;
    }

    /**
     * Inserts the reference for the order in the product.
     * @param lstProducts the list of products that are in the same order.
     * @param productOrder the order that has all products.
     */
    public void insertOrderReference(final List<Product> lstProducts, final ProductOrder productOrder) {
        for (Object obj : lstProducts) {
            Product product = (Product) obj;
            product.setOrder(productOrder);
            entityManager.merge(product);
        }
    }

    /**
     * Creates the entiies that are uses in the test.
     */
    private void startup() {
        // cleans the database
        deleteAll();

        String[] strDescriptionValues = DESCRIPTION_VALUES;

        // Creates the entities Address. The DESCRIPTION_VALUES length is the
        // number of address created.
        for (int i = 0; i < strDescriptionValues.length; i++) {
            Address address = new Address();
            address.setId(i);
            address.setCountry("France");
            address.setNumber(i);
            address.setStreet(strDescriptionValues[i]);
            entityManager.persist(address);
        }
        entityManager.flush();

        // Creates teh entities Category.The DESCRIPTION_VALUES length divided
        // by 2 is the
        // number of address created.
        for (int i = 0; i < strDescriptionValues.length / 2; i++) {
            Category category = new Category();
            category.setId(i);
            category.setDescription(strDescriptionValues[i]);
            entityManager.persist(category);
        }
        entityManager.flush();

        // Creates the product order and the products associated.
        for (int i = 0; i < strDescriptionValues.length; i++) {
            ProductOrder productOrder = new ProductOrder();
            productOrder.setId(i);
            productOrder.setDescription(strDescriptionValues[i]);

            // Creates the same number of products that the order id.
            // Are created 0 + 1 + 2 + ...+7 = 28 products in total.
            // The products id are: 10, 20, 21,30,31,32, 40, 41, 42, 43...
            List<Product> lstProducts = createProducts(i, i * INTERVAL_BETWEEN_ID);
            productOrder.setProducts(lstProducts);
            entityManager.persist(productOrder);
            // inserts in the products the reference for the order.
            insertOrderReference(lstProducts, productOrder);
        }
        entityManager.flush();

        // Creates the customer.
        for (int i = 0; i < strDescriptionValues.length / 2; i++) {
            Customer customer = new Customer();
            customer.setId(i);
            customer.setName(strDescriptionValues[i]);
            // inserts the address reference. The address has teh same primary
            // key that the customer.
            customer.setAddress(entityManager.getReference(Address.class, new Long(i)));
            // inserts the orders. Each customer has 2 orders.
            List<ProductOrder> orders = new ArrayList<ProductOrder>();
            orders.add(entityManager.getReference(ProductOrder.class, new Long(i)));
            orders.add(entityManager.getReference(ProductOrder.class, new Long(i + (NUMBER_OF_ORDERS / 2))));
            customer.setOrders(orders);
            entityManager.persist(customer);
        }
        entityManager.flush();
    }

    /**
     * Removes all entities cith the name in the parameter.
     * @param entityName the entity class that are removed.
     */
    public void deleteEntity(final String entityName) {
        Query queryResult = entityManager.createQuery("SELECT x FROM " + entityName+  " x");
        List lstEntity = queryResult.getResultList();
        for (Object obj : lstEntity) {
            entityManager.remove(obj);
        }
        entityManager.flush();
    }

    /**
     * Deletes all database.
     */
    private void deleteAll() {
        deleteEntity("Product");
        deleteEntity("ProductOrder");
        deleteEntity("Customer");
        deleteEntity("Category");
        deleteEntity("Address");
    }

    /**
     * Verifies if the container manages a path expression.
     */
    public void testPathExpression() {
        startup();
        Query query = entityManager
                .createQuery("SELECT o.description FROM Customer c, IN(c.orders) o WHERE o.id = :productOrderId");
        query.setParameter("productOrderId", new Long(1));
        List lstProductOrderDesc = query.getResultList();
        assertEquals(lstProductOrderDesc.size(), 1,
                "The query did not returned the correct value in a path expression.");
    }

    /**
     * Verifies if the inner join works.
     */
    public void testInnerJoin() {
        startup();
        Query query = entityManager
                .createQuery("SELECT p.category FROM ProductOrder po JOIN po.products p WHERE po.id BETWEEN ?1 AND  ?2");
        query.setParameter(1, new Long(0));
        query.setParameter(2, new Long(NUMBER_OF_ORDERS));
        List lstCategory = query.getResultList();
        assertEquals(lstCategory.size(), NUMBER_OF_ORDERS / 2, "The inner join does not work properly");
    }

    /**
     * Verifies if the container can manage the query with is empty.
     */
    public void testIsEmpty() {
        startup();
        Query query = entityManager.createQuery("SELECT po FROM ProductOrder po  WHERE po.products IS EMPTY");
        List lstProductOrder = query.getResultList();
        for (Object obj : lstProductOrder) {
            ProductOrder order = (ProductOrder) obj;
            assertEquals(order.getId(), 0, "The query result is incorrect.");
        }
    }

    /**
     * Verifies if the container can make a bulk operation delete.
     */
    public void testDelete() {
        startup();
        Query query = entityManager.createQuery("DELETE FROM Customer c  WHERE  c.id > 2");
        query.executeUpdate();
        entityManager.flush();
        Query queryTest = entityManager.createQuery("SELECT c FROM Customer c");
        List lstCustomer = queryTest.getResultList();
        for (Object obj : lstCustomer) {
            Customer customer = (Customer) obj;
            assertTrue(customer.getId() <= 2, "The operation delete does not work.");
        }
    }

    /**
     * Verifies if the container can make a bulk operation update.
     */
    public void testUpdate() {
        startup();
        Query query = entityManager.createQuery("UPDATE Address a SET a.country = 'Brazil' WHERE a.id =1");
        query.executeUpdate();
        entityManager.flush();
        testVerifyUpdate();
      }

    /**
     * Verifies if the update was made. This is made in a different method,
     * because a different transaction is needed.
     */
    private void testVerifyUpdate(){
        Address address = entityManager.find(Address.class, new Long(1));
        assertEquals(address.getCountry(), "Brazil", "The opertion update does not work");
    }
    /**
     * Verifies if the clause having works properly.
     */
    public void testHaving() {
        startup();
        Query query = entityManager
                .createQuery("SELECT p.price, COUNT(p) FROM Product p GROUP BY p.price HAVING p.price > 2");
        query.getResultList();
    }
}
