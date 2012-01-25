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
 * $Id: ResourceTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.ow2.easybeans.tests.common.db.TableManager;


/**
 * Resource Tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class ResourceTester {

    /**
     * Injected DataSource.
     */
    @Resource(name = "jdbc/jdbc_1", mappedName="jdbc_1")
    private DataSource ds;

    /**
     * Default Constructor.
     */
    public ResourceTester(){
    }

    /**
     * Tests a resource access.
     * @throws Exception if a problem occurs.
     */
    protected void access00() throws Exception{
        //Resource Test
        TableManager cTest = new TableManager(ds);
        cTest.test("ResourceTester");
    }
}
