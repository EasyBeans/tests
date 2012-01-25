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
 * $Id: ComplexObject00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.interceptors.invocationcontext;

import java.util.LinkedList;
import java.util.List;

/**
 * This class is a complex object.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public class ComplexObject00 extends BeanDescriptor {

    /**
     * Serial ID.
     */
    private static final long serialVersionUID = -5695987391205758337L;

    /**
     * List of arraylist of BeanDescriptor.
     */
    private List<List<BeanDescriptor>> lstArs;

    /**
     * Default constructor.
     *
     */
    public ComplexObject00() {
        super();
        lstArs = new LinkedList<List<BeanDescriptor>>();
    }

    /**
     * Adds a new list of bean descriptors.
     * @param list bean descriptor list.
     */
    public void addDescriptors(final List<BeanDescriptor> list){
        lstArs.add(list);
    }

    /**
     * Gets list of BeanDescriptor lists.
     * @return list.
     */
    public List<List<BeanDescriptor>> getDescriptors(){
        return lstArs;
    }

}
