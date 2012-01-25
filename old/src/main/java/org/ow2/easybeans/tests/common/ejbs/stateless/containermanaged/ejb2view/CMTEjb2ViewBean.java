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
 * $Id: CMTEjb2ViewBean.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.ejb2view;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.Init;
import javax.ejb.Local;
import javax.ejb.LocalHome;
import javax.ejb.Remote;
import javax.ejb.RemoteHome;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 * Bean defined as ejb 2.1.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Stateless
@Remote(CMTEjb2View.class)
@Local(CMTEjb2LocalView.class)
@RemoteHome(CMTEjb2ViewHome.class)
@LocalHome(CMTEjb2ViewLocalHome.class)
public class CMTEjb2ViewBean{

    /**
     * The bean local create method.
     * @throws CreateException if an error during the creation occurs.
     */
    @Init
    public void initLocal() throws CreateException{

    }

    /**
     * The bean create method.
     * @throws CreateException if an error during the creation occurs.
     * @throws RemoteException if a system level exception occurs.
     */
    @Init
    public void initRemote() throws CreateException, RemoteException{

    }

    /**
     * Does nothing, but has the transaction attribute mandatory.
     * @throws RemoteException if a system level exception occurs.
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void attributeMandatory() throws RemoteException{

    }

    /**
     * Does nothing, but has the transaction attribute never.
     * @throws RemoteException if a system level exception occurs.
     */
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void attributeNever() throws RemoteException{

    }

    /**
     * Does nothing, but has the transaction attribute mandatory.
     */
    @TransactionAttribute(TransactionAttributeType.MANDATORY)
    public void attributeMandatoryLocal(){

    }

    /**
     * Does nothing, but has the transaction attribute never.
     *
     */
    @TransactionAttribute(TransactionAttributeType.NEVER)
    public void attributeNeverLocal(){

    }

 }
