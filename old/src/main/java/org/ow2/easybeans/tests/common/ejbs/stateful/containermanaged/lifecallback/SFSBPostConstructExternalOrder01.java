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
 * $Id: SFSBPostConstructExternalOrder01.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateful.containermanaged.lifecallback;

import javax.ejb.Remote;
import javax.ejb.Stateful;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheckPostConstruct;
import org.ow2.easybeans.tests.common.ejbs.base.lifecallback.EBaseExternalCallbackOrder01;


/**
 * This bean contains PostConstruct interceptors. The first postconstruct is
 * in an intereceptor class and the second interceptor is in a superclass
 * that is inherited by the interceptor class.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateful(name="SFSBPostConstructExternalOrder01")
@Remote(ItfCheckPostConstruct.class)
public class SFSBPostConstructExternalOrder01 extends EBaseExternalCallbackOrder01{

}
