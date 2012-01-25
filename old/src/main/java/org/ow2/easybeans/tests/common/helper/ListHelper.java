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
 * $Id: ListHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

import java.util.List;

/**
 * This helper is used to do list modifications.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
public final class ListHelper {

    /**
     * Helper should have a private constructor.
     *
     */
    private ListHelper(){
    }

    /**
     * Creates a new list with elements of a list, plus new values.
     * @param <E> list type
     * @param list current list
     * @param values values to add
     * @return new list with new values
     * @throws InstantiationException reflection exception
     * @throws IllegalAccessException reflection exception
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> addValues(final List<E> list, final E[] values) throws InstantiationException,
            IllegalAccessException {

        if (list != null) {
            //Creates a new instance using reflection
            List<E> lstNew = list.getClass().newInstance();

            // Adds values from current list
            lstNew.addAll(list);

            // Adds the new values
            if (values != null){
                for (E eObj : values) {
                    lstNew.add(eObj);
                }
            }
            return lstNew;
        }
        return null;
    }

    /**
     * Converts an List that does not use generics to a List with generic type.
     * @param <E> the list type;
     * @param list the corrent list.
     * @return the generic list.
     * @throws InstantiationException reflection exception.
     * @throws IllegalAccessException reflection exception.
     */
    @SuppressWarnings("unchecked")
    public static <E> List<E> convertListType(final List list) throws InstantiationException,
            IllegalAccessException {
        //verifies if the list is null
        if (list != null) {
            //creates a new instance of the list
            List<E> lstReturn = list.getClass().newInstance();
            //copy to the list with defined type
            if (list.size() > 0) {
                for (Object obj : list) {
                   lstReturn.add((E) obj);
                }
            }
            return lstReturn;
        }
        return null;

    }

}
