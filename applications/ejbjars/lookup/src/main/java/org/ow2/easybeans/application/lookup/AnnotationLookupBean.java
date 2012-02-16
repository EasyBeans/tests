/**
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
 * $Id: MetadataMerge.java 4697 2009-02-23 17:03:23Z sauthieg $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.application.lookup;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;



/**
 * Simple Bean with annotation
 * @author Florent Benoit
 */
@Stateless(mappedName="AnnotationLookupBean")
@Remote(ILookup.class)
public class AnnotationLookupBean extends CommonBean {
    
    @EJB(name="entries/SimpleBean", beanName="SimpleBean1")
    private ISimple simpleBean1;

    private ISimple simpleBean1OtherWay;
    
    private ISimple simpleBean2;
    
    @EJB(lookup="java:module/SimpleBean2")
    protected void setSimpleBean(final ISimple simpleBean) {
        this.simpleBean2 = simpleBean;
    }

    @EJB(lookup="java:comp/env/entries/SimpleBean")
    protected void setSimpleBeanOtherWay(final ISimple simpleBean1OtherWay) {
        this.simpleBean1OtherWay = simpleBean1OtherWay;
    }

    
    public ISimple getBean1() {
        return simpleBean1;
    }
    
    public ISimple getBean1OtherWay() {
        return simpleBean1OtherWay;
    }

    
    public ISimple getBean2() {
        return simpleBean2;
    }
}
