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

package org.ow2.easybeans.application.statefulsync;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Singleton;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.testng.Assert;


/**
 * Remote tester used to validate stateful beans with session synchronization.
 * @author Florent Benoit
 */
@Singleton(mappedName="RemoteTesterStatefulSyncBean")
@TransactionManagement(TransactionManagementType.BEAN)
public class RemoteTesterStatefulSyncBean implements IRemoteStatefulSyncBean {

    @EJB(beanName="AnnotatedStatefulAfterBeginBean")
    private IStatefulSessionBean annotatedStatefulAfterBeginBean;

    // Use no-interface local view
    @EJB
    private AnnotatedStatefulAfterCompletionBean annotatedStatefulAfterCompletionBean;

    // Use no-interface local view
    @EJB
    private AnnotatedStatefulBeforeCompletionBean annotatedStatefulBeforeCompletionBean;

    // Use no-interface local view
    @EJB
    private AnnotatedStatefulSyncBean annotatedStatefulSyncBean;

    // Use no-interface local view
    @EJB(beanName="SessionSynchronizationBean")
    private IStatefulSessionBean sessionSynchronizationBean;

    // Use no-interface local view
    @EJB
    private XMLStatefulPartialSyncBean xmlStatefulPartialSyncBean;
    
    // Use no-interface local view
    @EJB
    private XMLStatefulSyncBean xmlStatefulSyncBean;
    
    

    public void checkXmlStatefulPartialSyncBean() {
        // Clear any operations
        xmlStatefulPartialSyncBean.clearOperations();
        
        // call a dummy method
        xmlStatefulPartialSyncBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_COMPLETION_COMMIT);
        
        Assert.assertEquals(xmlStatefulPartialSyncBean.getOperations(), expectedOperations);
    }
    
    public void checkXmlStatefulPartialSyncBeanRollback() {
        
        xmlStatefulPartialSyncBean.clearOperations();
        List<Operations> foundOperations = xmlStatefulPartialSyncBean.getOperations();
        // call a dummy method
        try {
            xmlStatefulPartialSyncBean.dummyCallRollback();
        } catch (EJBException expectedException) {
            // expected
        }
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        // No BEFORE_COMPLETION has it's being rollbacked
        expectedOperations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        
        Assert.assertEquals(foundOperations, expectedOperations);
    }
    
    
    public void checkAnnotatedStatefulAfterBeginBean() {
        // Clear any operations
        annotatedStatefulAfterBeginBean.clearOperations();
        
        // call a dummy method
        annotatedStatefulAfterBeginBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_BEGIN);
        
        Assert.assertEquals(annotatedStatefulAfterBeginBean.getOperations(), expectedOperations);
    }
    

    
    public void checkAnnotatedStatefulBeforeCompletionBean() {
        // Clear any operations
        annotatedStatefulBeforeCompletionBean.clearOperations();
        
        // call a dummy method
        annotatedStatefulBeforeCompletionBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.BEFORE_COMPLETION);
        
        Assert.assertEquals(annotatedStatefulBeforeCompletionBean.getOperations(), expectedOperations);
    }
    
    public void checkAnnotatedStatefulAfterCompletionBean() {
        // Clear any operations
        annotatedStatefulAfterCompletionBean.clearOperations();
        
        // call a dummy method
        annotatedStatefulAfterCompletionBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_COMPLETION_COMMIT);
        
        Assert.assertEquals(annotatedStatefulAfterCompletionBean.getOperations(), expectedOperations);
    }
    
    public void checkAnnotatedStatefulAfterCompletionBeanRollback() {
        
        annotatedStatefulAfterCompletionBean.clearOperations();
        List<Operations> foundOperations = annotatedStatefulAfterCompletionBean.getOperations();
        // call a dummy method
        try {
            annotatedStatefulAfterCompletionBean.dummyCallRollback();
        } catch (EJBException expectedException) {
            // expected
        }
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        // No BEFORE_COMPLETION has it's being rollbacked
        expectedOperations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        
        Assert.assertEquals(foundOperations, expectedOperations);
    }
    
    
    
    public void checkAnnotatedStatefulSyncBean() {
        // Clear any operations
        annotatedStatefulSyncBean.clearOperations();
        
        // call a dummy method
        annotatedStatefulSyncBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_BEGIN);
        expectedOperations.add(Operations.BEFORE_COMPLETION);
        expectedOperations.add(Operations.AFTER_COMPLETION_COMMIT);
        
        Assert.assertEquals(annotatedStatefulSyncBean.getOperations(), expectedOperations);
    }
    
    public void checkAnnotatedStatefulSyncBeanRollback() {
        
        annotatedStatefulSyncBean.clearOperations();
        List<Operations> foundOperations = annotatedStatefulSyncBean.getOperations();
        // call a dummy method
        try {
            annotatedStatefulSyncBean.dummyCallRollback();
        } catch (EJBException expectedException) {
            // expected
        }
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        // No BEFORE_COMPLETION has it's being rollbacked
        expectedOperations.add(Operations.AFTER_BEGIN);
        expectedOperations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        
        Assert.assertEquals(foundOperations, expectedOperations);
    }
    
    

    
    public void checkSessionSynchronizationBean() {
        // Clear any operations
        sessionSynchronizationBean.clearOperations();
        
        // call a dummy method
        sessionSynchronizationBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_BEGIN);
        expectedOperations.add(Operations.BEFORE_COMPLETION);
        expectedOperations.add(Operations.AFTER_COMPLETION_COMMIT);
        
        Assert.assertEquals(sessionSynchronizationBean.getOperations(), expectedOperations);
    }
    
    public void checkSessionSynchronizationBeanRollback() {
        
        sessionSynchronizationBean.clearOperations();
        List<Operations> foundOperations = sessionSynchronizationBean.getOperations();
        // call a dummy method
        try {
            sessionSynchronizationBean.dummyCallRollback();
        } catch (EJBException expectedException) {
            // expected
        }
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        // No BEFORE_COMPLETION has it's being rollbacked
        expectedOperations.add(Operations.AFTER_BEGIN);
        expectedOperations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        
        Assert.assertEquals(foundOperations, expectedOperations);
    }
    
    
    public void checkXmlStatefulSyncBean() {
        // Clear any operations
        xmlStatefulSyncBean.clearOperations();
        
        // call a dummy method
        xmlStatefulSyncBean.dummyCall();
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_BEGIN);
        expectedOperations.add(Operations.BEFORE_COMPLETION);
        expectedOperations.add(Operations.AFTER_COMPLETION_COMMIT);
        
        Assert.assertEquals(xmlStatefulSyncBean.getOperations(), expectedOperations);
        
        
    }

    public void checkXmlStatefulSyncBeanRollback() {
        // Clear any operations
        xmlStatefulSyncBean.clearOperations();
        
        List<Operations> foundOperations = xmlStatefulSyncBean.getOperations();
        // call a dummy method
        try {
            xmlStatefulSyncBean.dummyCallRollback();
        } catch (EJBException expectedException) {
            // expected
        }
        
        // Check operations
        List<Operations> expectedOperations = new ArrayList<Operations>();
        expectedOperations.add(Operations.AFTER_BEGIN);
        // No BEFORE_COMPLETION has it's being rollbacked
        expectedOperations.add(Operations.AFTER_COMPLETION_ROLLBACK);
        
        Assert.assertEquals(foundOperations, expectedOperations);
        
        
    }

}
