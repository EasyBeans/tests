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


/**
 * Interface for testing Access Timeout
 * @author Florent Benoit
 */
public interface IStatefulAccessTimeout {

    /**
     * Processing time.
     */
    static final long PROCESSING_TIME = 2000L;
    
    /**
     * Use of the default access timeout (on the bean class)
     */
    String defaultTimeout(String value);

    /**
     * Milliseconds timeout specified on the method.
     */
    String millisecondsTimeout(String value);

    /**
     * Unable to serialize calls on this bean.
     */
    String noTimeout(String value);

}