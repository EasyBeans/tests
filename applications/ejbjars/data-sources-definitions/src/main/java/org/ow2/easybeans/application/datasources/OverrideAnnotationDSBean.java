/*
 * JOnAS: Java(TM) Open Application Server
 * Copyright (C) 2012 Bull S.A.S.
 * Contact: jonas-team@ow2.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA
 *
 * --------------------------------------------------------------------------
 *  $Id$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.datasources;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.testng.Assert;

/**
 * @author Loic Albertin
 */
@DataSourceDefinition(name = "java:comp/env/MyOverrideDS", className = "org.hsqldb.jdbc.jdbcDataSource",
        url = "jdbc:hsqldb:mem:override", isolationLevel = Connection.TRANSACTION_REPEATABLE_READ)
@Stateless(name = "OverrideAnnotationDSBeanEJB")
public class OverrideAnnotationDSBean implements IDSDefinitionBean {

    @Resource(mappedName = "java:comp/env/MyOverrideDS")
    private DataSource dataSource1;


    @Resource(mappedName = "java:comp/env/MyAddedDS")
    private DataSource dataSource2;

    public OverrideAnnotationDSBean() {
    }

    public void testDataSourceInjection() {
        checkDS(dataSource1);
        checkDS(dataSource2);

        try {
            Assert.assertEquals(Connection.TRANSACTION_READ_COMMITTED, dataSource1.getConnection().getTransactionIsolation());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void checkDS(final DataSource dataSource) {
        Assert.assertNotNull(dataSource, "Data source is null: injection failed");
        Assert.assertTrue("org.ow2.easybeans.component.jdbcpool.ConnectionManager".equals(dataSource.getClass().getName()),
                "Data Source is not a org.ow2.easybeans.component.jdbcpool.ConnectionManager as expected but a " +
                        dataSource.getClass().getName());

        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            // Try to drop table if exists
            String query = "DROP TABLE datasource_service_test";
            try {
                statement.executeUpdate(query);
            } catch (Exception e) {
                // Table may not exist
            }

            query = "create table datasource_service_test " + "(NAME varchar(50), " + "LASTNAME varchar(50))";
            statement = connection.createStatement();
            statement.executeUpdate(query);

            String insertTest = "INSERT INTO datasource_service_test (NAME, LASTNAME) VALUES ('JOHN', 'DOE')";
            statement.executeUpdate(insertTest);
        } catch (SQLException e) {
            Assert.fail("Unable to access data base", e);
        }
    }
}
