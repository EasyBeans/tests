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
 * $Id: SFSBEntityManagerQueriesTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.entitymanager;

import static org.testng.Assert.assertEquals;

import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.ow2.easybeans.tests.common.ejbs.entity.simpleentity.SimpleEntity;

/**
 * Verifies if the EntityManager methods that treats the queries work correctly.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
@Stateful
@Remote(ItfEntityManagerQueriesTester.class)
public class SFSBEntityManagerQueriesTester implements ItfEntityManagerQueriesTester {

    /**
     * The persistence context used during the tests.
     */
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * The sql native query used during the tests.
     */
    private static final String NATIVE_QUERY = "SELECT e.id FROM SIMPLE e ORDER BY e.id";

    /**
     * Other sql native query. This query has a parameter.
     */
    private static final String NATIVE_QUERY_2 = "SELECT e.name AS entity_name, e.id AS entity_id "
            + "FROM SIMPLE e WHERE e.id >= ? ORDER BY e.id";

    /**
     * The EJB QL query used during the test.
     */
    private static final String EJBQL_QUERY = "SELECT e FROM SimpleEntity e WHERE e.name LIKE :entityname ORDER BY e.id";

    /**
     * Verifies if the query returned all beans created.
     * @param queryResult the result to be compared.
     */
    private void checkQueryResult(final List queryResult) {
        // verifies if the list returned has the same number of entities that
        // was created
        assertEquals(MAX_ENTITIES, queryResult.size(), "The method did not returned all entities.");

        for (int i = 0; i < queryResult.size(); i++) {
            SimpleEntity simpleEntity = (SimpleEntity) queryResult.get(i);
            assertEquals(simpleEntity.getId(), i, "The bean id was not correctly returned");
            assertEquals(simpleEntity.getName(), ENTITY_NAME_ROOT + Integer.toString(i),
                    "The bean name was not correctly returned");
        }
    }

    /**
     * Removes all entities that have the same id used in the beans under test.
     */
    private void removeBean() {
        for (int i = 0; i < MAX_ENTITIES; i++) {
            SimpleEntity simpleEntity = entityManager.find(SimpleEntity.class, new Integer(i));
            if (simpleEntity != null) {
                entityManager.remove(simpleEntity);
            }
            entityManager.flush();
        }
    }

    /**
     * Create the entity beans used during the test.
     */
    private void createBeanTest() {

        for (int i = 0; i < MAX_ENTITIES; i++) {
            SimpleEntity simpleEntity = new SimpleEntity();
            simpleEntity.setId(i);
            simpleEntity.setName(ENTITY_NAME_ROOT + Integer.toString(i));
            entityManager.persist(simpleEntity);
            entityManager.flush();
       }

    }

    /**
     * Creates the beans used in the test and before clean the database.
     */
    public void startup() {
        removeBean();
        createBeanTest();
    }

    /**
     * Calls an EJB QL named query defined by annotation.
     */
    public void callNamedQuery() {
        Query query = entityManager.createNamedQuery("findByName");
        query.setParameter("entityName", ENTITY_NAME_ROOT + Integer.toString((MAX_ENTITIES - 1)));
        List simpleEntityResult = query.getResultList();
        assertEquals(simpleEntityResult.size(), 1, "The query did not return any value.");
        for (int i = 0; i < simpleEntityResult.size(); i++) {
            SimpleEntity simpleEntity = (SimpleEntity) simpleEntityResult.get(i);
            assertEquals(simpleEntity.getId(), MAX_ENTITIES - 1, "The bean was not correctly returned");
         }
    }

    /**
     * Calls a native query defined by annotation.
     */
    public void callNamedNativeQuery() {
        Query query = entityManager.createNamedQuery("findByAll");
        List simpleEntityResult = query.getResultList();
        // verifies if all entities were correctly returned.
        checkQueryResult(simpleEntityResult);
    }

    /**
     * Uses the createQuery method of the EntityManager.
     */
    public void callCreateQuery() {
        Query query = entityManager.createQuery(EJBQL_QUERY);
        query.setParameter("entityname", ENTITY_NAME_ROOT + "%");
        List simpleEntityResult = query.getResultList();

        // verifies if all entities were correctly returned.
        checkQueryResult(simpleEntityResult);
    }

    /**
     * Calls the method createNative Query that has only the sqlQuery as
     * parameter.
     */
    public void callCreateNativeQuery00() {
        Query query = entityManager.createNativeQuery(NATIVE_QUERY);
        List simpleEntityResult = query.getResultList();

        // verifies if the list returned has the same number of entities that
        // was created
        assertEquals(MAX_ENTITIES, simpleEntityResult.size(), "The method did not returned all entities.");

        for (int i = 0; i < simpleEntityResult.size(); i++) {
            Integer intResult = (Integer) simpleEntityResult.get(i);
            assertEquals(intResult.intValue(), i, "The bean id was not correctly returned");
        }
    }

    /**
     * Calls the method createNativeQuery that has the sqlQuery and the bean
     * class as parameters.
     */
    public void callCreateNativeQuery01() {
        Query query = entityManager.createNativeQuery(NATIVE_QUERY, SimpleEntity.class);
        List simpleEntityResult = query.getResultList();

        // verifies if all entities were correctly returned.
        checkQueryResult(simpleEntityResult);
    }

    /**
     * Calls the method that has the sqlQuery and the resultSetMapping as
     * parameters.
     */
    public void callCreateNativeQuery02() {
        Query query = entityManager.createNativeQuery(NATIVE_QUERY_2, "MappedSimpleEntity");
        query.setParameter(1, new Integer(0));

        List simpleEntityResult = query.getResultList();

        // verifies if all entities were correctly returned.
        checkQueryResult(simpleEntityResult);
    }

}
