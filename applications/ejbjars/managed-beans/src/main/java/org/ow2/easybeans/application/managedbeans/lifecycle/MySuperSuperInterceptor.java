/*
 * EasyBeans
 * Copyright (C) 2012 Bull S.A.S.
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
 * $Id:$
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.managedbeans.lifecycle;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.testng.Assert;

/**
 * @author Loic Albertin
 */
public class MySuperSuperInterceptor {

    public static final String INTERCEPTOR_NAME = "MySuperSuperInterceptor";

    @EJB(name = "historyBean")
    protected HistoryBean historyBean;

    @PostConstruct
    protected void postConstructInMySuperSuperInterceptor(InvocationContext invocationContext) {
        try {
            historyBean.recordInterceptor(MySuperSuperInterceptor.INTERCEPTOR_NAME);
            invocationContext.proceed();
        } catch (Exception e) {
            Assert.fail("Error in business PostConstruct interceptor", e);
        }
    }

    @AroundInvoke
    private Object aroundInvokeInSuperSuperInterceptor(InvocationContext invocationContext) throws Exception {
        return invocationContext.proceed();
    }
}
