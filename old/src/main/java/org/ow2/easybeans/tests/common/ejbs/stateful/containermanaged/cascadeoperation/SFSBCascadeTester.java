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
 * $Id: SFSBCascadeTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.cascadeoperation;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.ow2.easybeans.tests.common.ejbs.entity.customer.Address;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Category;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Customer;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.Product;
import org.ow2.easybeans.tests.common.ejbs.entity.customer.ProductOrder;

/**
 * Verifies if the container can manage the different types of cascade.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfCascadeTester.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class SFSBCascadeTester implements ItfCascadeTester {

    /**
     * EntityManager used to manage the entities.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * The beans primary key.
     */
    public static final long ID = 1;

    /**
     * The alternative primary key.
     */
    public static final long ALTERNATIVE_ID = 2;

    /**
     * The field price of the product.
     */
    public static final float PRODUCT_PRICE = 2.4f;

    /**
     * The field price of the product.
     */
    public static final float PRODUCT_PRICE_2 = 4.6f;

    /**
     * Removes a bean from the persistence context.
     * @param <T> the entity type.
     * @param entityClass the entity class.
     */
    private <T> void removeBean(final Class<T> entityClass) {
        T objBean;
        objBean = entityManager.find(entityClass, new Long(ID));
        if (objBean != null) {
            entityManager.remove(objBean);
        }
    }

    /**
     * Removes all entities used in this test.
     *
     */
    private void removeAll() {
        removeBean(Category.class);
        removeBean(Product.class);
        removeBean(ProductOrder.class);
        removeBean(Customer.class);
        removeBean(Address.class);
    }

    /**
     * Creates all entities used during the test.
     */
    public void startup() {
        removeAll();

        //creates the category
        Category category = new Category();
        category.setId(ID);
        category.setDescription("description");
        entityManager.persist(category);

        //creates the product
        Product product = new Product(ID, "description", PRODUCT_PRICE, category);
        entityManager.persist(product);

        //creates the address
        Address address = new Address(ID, "street", "country", 1);
        entityManager.persist(address);

        //creates the customer
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setAddress(address);
        customer.setName("customer");
        entityManager.persist(customer);

        //creates the order
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        ProductOrder order = new ProductOrder(ID, "description", customer, products);
        entityManager.persist(order);

    }

    /**
     * Verifies if the container can make a refresh in cascade.
     */
    public void verifyCascadeTypeRefresh() {
        Category category = entityManager.find(Category.class, new Long(ID));
        category.setDescription("new description");

        Product product = entityManager.find(Product.class, new Long(ID));
        product.setPrice(PRODUCT_PRICE_2);

        entityManager.refresh(product);

        assertEquals(category.getDescription(), "description", "The container did not make the refresh in cascade.");
    }

    /**
     * Verifies if the conatiner makes the remove in cascade.
     */
    public void verifyCascadeTypeRemove() {
        ProductOrder order = entityManager.find(ProductOrder.class, new Long(ID));
        assertFalse(order == null, "The container did not find the order");

        entityManager.remove(order);
        entityManager.flush();

        Product productResult = entityManager.find(Product.class, new Long(ID));
        assertTrue(productResult == null, "The container did not make the delete in cascade.");
    }

    /**
     * Verifies if the container makes the merge in cascade.
     */
    public void verifyCascadeTypeMerge() {
        ProductOrder order = entityManager.find(ProductOrder.class, new Long(ID));
        Customer customer = entityManager.find(Customer.class, new Long(ID));

        entityManager.clear();
        order.setDescription("new description");
        customer.setName("new customer");

        entityManager.merge(customer);
        entityManager.flush();

        assertEquals(order.getDescription(), "new description", "The container did not make the merge in cascade");
    }

    /**
     * Verifies if the container makes the persist in cascade.
     */
    public void verifyCascadeTypePersist() {
        Customer customer = entityManager.find(Customer.class, new Long(ID));
        Address addressNew = new Address(ALTERNATIVE_ID, "street", "country", 1);
        customer.setAddress(addressNew);

        entityManager.persist(customer);
        entityManager.flush();

        Address addressResultAfterPersist = entityManager.find(Address.class, new Long(2));
        assertFalse(addressResultAfterPersist == null, "The container did not make a persist in cascade.");
    }
}
