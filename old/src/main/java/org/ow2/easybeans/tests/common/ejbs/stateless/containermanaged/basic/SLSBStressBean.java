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
 * $Id: SLSBStressBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.basic;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.sql.DataSource;

/**
 * Stateless session bean used for stress tests.
 * @author Florent Benoit
 */
@Stateless
public class SLSBStressBean implements SLSBStressBeanLocal {

    /**
     * Link to a session context.
     */
    @Resource
    private SessionContext sessionContext = null;

    /**
     * Link to a datasource.
     */
    @Resource(name="jdbc/myJDBC", mappedName="jdbc_1")
    private DataSource datasource = null;

    /**
     * Link to ourself.
     */
    @EJB(name="ejb/self")
    private SLSBStressBeanLocal self;

    /**
     * MULT for random.
     */
    private static final int MULT = 100;

    /**
     * @return dummy value.
     */
    public int dummy1() {
        return (int) (Math.random() * MULT);
    }


    /**
     * Do some stuff.
     */
    public void dummy2() {
        // Do some stuff;
        double a = dummy1() * dummy1();
        StringBuilder sb = new StringBuilder();
        sb.append(a);

        // Assert there was resource injection and ENC is correct
        Object o = sessionContext.lookup("jdbc/myJDBC");

        DataSource ds = null;
        // cast
        if (o != null) {
            ds = (DataSource) o;
        } else {
            throw new IllegalStateException("No resource found in ENC env");
        }

        try {
            Connection c1 = datasource.getConnection();
            c1.close();
            Connection c2 = ds.getConnection();
            c2.close();
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot use the JDBC connection");
        }

        Object b = sessionContext.lookup("ejb/self");
        SLSBStressBeanLocal bean = null;
        // cast
        if (b != null) {
            bean = (SLSBStressBeanLocal) b;
        } else {
            throw new IllegalStateException("No resource found in ENC env");
        }

        // Assert same object
        if (!self.equals(bean)) {
            throw new IllegalStateException("Different EJB objects !");
        }
    }
}
