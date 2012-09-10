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

package org.ow2.easybeans.application.timer;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timer;

@Singleton(mappedName="SimpleScheduleBean")
@Startup
public class SimpleScheduleBean implements ISimpleScheduleBean {

    
    private volatile int methodCalled = 0;
    
    private long elapsedTime = 0;
    private long lastCallDate = 0;
    
    @Schedule(second="*/3", minute="*", hour="*", info="timerCalled")
    @SuppressWarnings("unused")
    private void timerCalled(Timer timer) {
        
        methodCalled++;
        long now = System.currentTimeMillis();
        
        // Don't compute average time on the first call
        if (lastCallDate != 0) {
            elapsedTime += (now - lastCallDate);
        }
        lastCallDate = now; 
    }
    
    public int getMethodCalledCount() {
        return methodCalled;
    }

    public long getAverageElapsedTime() {
        if (methodCalled > 1) {
            return (elapsedTime / (methodCalled -1));
        }
        return 0L;
    }

    
}
