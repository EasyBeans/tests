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
 * $Id: TimerServiceTester.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.resources;

import javax.annotation.Resource;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.naming.Context;
import javax.naming.InitialContext;


/**
 * TimerService Tester.
 * @author Eduardo Studzinski Estima de Castro
 * @author Gisele Pinheiro Souza
 *
 */
public class TimerServiceTester {

    /**
     * Timer Interval Duration.
     */
    public static final int TIMER_DURATION = 500000;
    /**
     * Timer Service.
     */
    @Resource
    private TimerService tServ;

    /**
     * Default Constructor.
     */
    public TimerServiceTester(){
    }

    /**
     * Tests a timerService access.
     * @throws Exception if a problem occurs.
     */
    protected void access00() throws Exception{
        checkInstance(tServ);
    }

    /**
     * Checks if a timer service reference is working well.
     * @param ts reference
     * @throws Exception if a problem occurs
     */
    public static void checkInstance(final TimerService ts) throws Exception{
        //Creates a timer
        Timer t = ts.createTimer(TIMER_DURATION, "Timer Created");
        //Verifies a timer method
        t.getTimeRemaining();

        //Verifies if the new timer was added to the timers list
        if(ts.getTimers().size() < 1){
            throw new IllegalStateException("The Timers Collection must have at least one timer. It was created in this method.");
        }
        //Cancels the timer
        t.cancel();
    }

    /**
     * Checks if a timer service reference can be obtained using the JNDI API.
     * @throws Exception if a problem occurs
     */
    public static void checkJNDI() throws Exception{
        Context ctx = new InitialContext();

        TimerService ts = (TimerService) ctx.lookup("java:comp/TimerService");
        if (ts == null){
            throw new Exception("TimerService reference obtained using JNDI API is null.");
        }
    }
}
