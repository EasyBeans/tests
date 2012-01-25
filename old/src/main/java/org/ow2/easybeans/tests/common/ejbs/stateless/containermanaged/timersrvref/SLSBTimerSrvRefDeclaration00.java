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
 * $Id: SLSBTimerSrvRefDeclaration00.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.timersrvref;

import static org.ow2.easybeans.tests.common.helper.ContextHelper.checkResource;

import javax.annotation.Resource;
import javax.annotation.Resources;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfCheck00;

/**
 * This bean is used to test the timer service declarations on the bean
 * class.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 */
@Stateless(name = "SLSBTimerSrvRefDeclaration00")
@Remote(ItfCheck00.class)
@Resources({@Resource(name = "ts/ts00", type = javax.ejb.TimerService.class),
        @Resource(name = "ts/ts01", type = javax.ejb.TimerService.class)})
@Resource(name = "ts/ts02", type = javax.ejb.TimerService.class)
public class SLSBTimerSrvRefDeclaration00 implements ItfCheck00 {

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * Checks if the annotations &#64;Resource and &#64;Resources are working
     * properly for timer service. The reference was declared on the bean
     * class. The container must: <li>declare a reference using &#64;Resources;</li>
     * <li>declare a reference using &#64;Resource.</li>
     */
    public void check() {
        checkResource(sessionContext, "ts/ts00");
        checkResource(sessionContext, "ts/ts01");
        checkResource(sessionContext, "ts/ts02");
    }
}
