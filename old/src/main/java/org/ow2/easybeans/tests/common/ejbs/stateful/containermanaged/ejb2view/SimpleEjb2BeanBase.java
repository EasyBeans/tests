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
 * $Id: SimpleEjb2BeanBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;

/**
 * Implements the methods of the local, remote, local-home and remote-home
 * interfaces.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public class SimpleEjb2BeanBase {

    /**
     * The bean code.
     */
    private int intCode;

    /**
     * The bean name.
     */
    private String strName;

    /**
     * Creates an instance of the bean with the default values.
     * @throws CreateException if an error during the creation occurs.
     * @throws RemoteException if a system level error occurs.
     */
    public void ejbCreate() throws CreateException, RemoteException {
        this.intCode = SimpleEjb2.DEFAULT_CODE_REMOTE;
        this.strName = SimpleEjb2.DEFAULT_NAME_REMOTE;
    }

    /**
     * Creates an instance of the bean with the values in the parameters.
     * @param code the bean code.
     * @param name the bean name.
     * @throws CreateException if an error during the creation occurs
     * @throws RemoteException if a system level error occurs.
     */
    public void init(final int code, final String name) throws CreateException, RemoteException {
        this.intCode = code;
        this.strName = name;
    }

    /**
     * Creates an instance of the bean with the default name and the code
     * defined in the parameter.
     * @param code the bean code.
     * @throws CreateException if a creation error occurs.
     */
    void ejbCreate(final int code) throws CreateException {
        this.intCode = code;
        this.strName = SimpleEjb2Local.DEFAULT_NAME_LOCAL;
    }

    /**
     * Creates an instance of the bean with the default code and the name
     * defined in the parameter.
     * @param name the bean name.
     * @throws CreateException if a creation error occurs.
     */
    void init(final String name) throws CreateException {
        this.intCode = SimpleEjb2Local.DEFAULT_CODE_LOCAL;
        this.strName = name;
    }

    /**
     * Returns the message in the parameter.
     * @param message the message.
     * @return the message.
     * @throws RemoteException if a system level error occurs.
     */
    public String sayHello(final String message) throws RemoteException {
        return message;
    }

    /**
     * Gets the bean code.
     * @return the code.
     * @throws RemoteException if a system level error occurs.
     */
    public int getCode() throws RemoteException {
        return intCode;
    }

    /**
     * Sets the bean code.
     * @param intCode the code.
     * @throws RemoteException if a system level error occurs.
     */
    public void setCode(final int intCode) throws RemoteException {
        this.intCode = intCode;
    }

    /**
     * Gets the bean name.
     * @return the bean name.
     * @throws RemoteException if a system level error occurs.
     */
    public String getName() throws RemoteException {
        return strName;
    }

    /**
     * Sets the bean name.
     * @param strName the bean name.
     * @throws RemoteException if a system level error occurs.
     */
    public void setName(final String strName) throws RemoteException {
        this.strName = strName;
    }

    /**
     * Sets the bean values.
     * @param code the bean code.
     * @param name the bean name.
     */
    void setValues(final int code, final String name) {
        this.intCode = code;
        this.strName = name;
    }

    /**
     * Gets the default message.
     * @return the message.
     */
    String sayHello() {
        return SimpleEjb2Local.DEFAULT_MESSAGE;
    }

    /**
     * Gets the bean code.
     * @return the code.
     */
    int getCodeLocal(){
        return intCode;
    }

    /**
     * Gets the bean name.
     * @return the bean name.
     */
    String getNameLocal(){
        return strName;
    }

}
