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
 * $Id: SLSBSimpleEnvEntryByDescriptor.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.simpleentry;

import javax.annotation.Resource;
import javax.ejb.Remote;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntryByDescriptor;
import org.ow2.easybeans.tests.common.helper.ContextHelper;


/**
 * This bean is used to test injection of simple environment entries.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
@Stateless(name = "SLSBSimpleEnvEntryByDescriptor")
@Remote(ItfSimpleEnvEntryByDescriptor.class)
public class SLSBSimpleEnvEntryByDescriptor implements ItfSimpleEnvEntryByDescriptor{

    /**
     * SessionContext.
     */
    @Resource
    private SessionContext sessionContext;

    /**
     * The value will be injected by the container.
     */
    private String strInjection;

    /**
     * This value must be always the value of DECLARED_STRING constant.
     */
    private String strNotOverride = DECLARED_STRING;

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkNotOverride00() {
        if (!strNotOverride.equals(DECLARED_STRING)) {
            throw new IllegalStateException("There is not a default value declared in the deployment descriptor, "
                    + "so the container should not override the value specified in the variable declaration.");
        }
    }

    /**
     * @see org.ow2.easybeans.tests.common.ejbs.base.ItfSimpleEnvEntry
     */
    public void checkStringInjection00() {
        ContextHelper.checkSimpleEntry(sessionContext, "StringEntry", strInjection, INJECTED_STRING);
    }
}
