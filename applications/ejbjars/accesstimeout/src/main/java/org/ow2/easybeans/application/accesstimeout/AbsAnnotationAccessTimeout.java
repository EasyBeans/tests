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

package org.ow2.easybeans.application.accesstimeout;

import java.util.concurrent.TimeUnit;

import javax.ejb.AccessTimeout;

/**
 * Abstract class.
 * @author Florent Benoit
 */
public abstract class AbsAnnotationAccessTimeout implements IAccessTimeout {

    /**
     * Use of the default access timeout (on the bean class)
     */
    public String defaultTimeout(String value) {
        // Wait
        try {
            Thread.sleep(PROCESSING_TIME);
        } catch (InterruptedException e) {
        }
        return value;
    }

    /**
     * Long method and then timeout should occur
     */
    @AccessTimeout(unit=TimeUnit.SECONDS, value = 1)
    public String longMethod(String value) {
        // Wait
        try {
            Thread.sleep(PROCESSING_TIME * 3);
        } catch (InterruptedException e) {
        }
        return value;
    }
    
    /**
     * Unable to serialize calls on this bean.
     */
    @AccessTimeout(unit=TimeUnit.SECONDS, value = 0)
    public String noTimeout(String value) {
        // Wait
        try {
            Thread.sleep(PROCESSING_TIME / 2 );
        } catch (InterruptedException e) {
        }
        return value;
    }
}
