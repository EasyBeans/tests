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
 * $Id: ListenerTesterBase.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.listeners;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import javax.ejb.EJB;

import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.CallbackType;
import org.ow2.easybeans.tests.common.ejbs.entity.callbacklogger.ListenerLogger;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.CallbackLoggerComparator;
import org.ow2.easybeans.tests.common.ejbs.stateless.containermanaged.callbacklogger.ItfListenerLoggerAccess;
import org.ow2.easybeans.tests.common.helper.ListHelper;

/**
 * Create the base for doing the tests that verifies if the lifecycle callback
 * methods works properly. Creates, modifies and removes an intance of an entity
 * and verifies if all callback methods for each operation was called. The
 * entities used to test are in the geometricforms package and each one has a
 * different definition of listeners.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public abstract class ListenerTesterBase implements ItfListenerTester {

    /**
     * The bean used to register the lifecycle calls in the database.
     */
    @EJB
    private ItfListenerLoggerAccess bean;

    /**
     * Verifies if the callback methods that have the same type are executed in
     * the correct order.
     * @param lifecycle the lifecycle callback method.
     * @param listenersId the list of listeners that must be called organized in
     *        the correct order.
     * @param formType the form for which the the callback was called.
     */
    public void verifySameCallbackMethodOrder(final CallbackType lifecycle, final String[] listenersId, final String formType) {

        List callbackList = bean.findCallbackEvent(formType, lifecycle);

        assertTrue(callbackList.size() == listenersId.length, "The container did not call all listeners");

        // sorts the events by date.
        if (callbackList.size() != 0) {
            ListenerLogger[] lstManager = new ListenerLogger[callbackList.size()];
            try {
                lstManager = ListHelper.convertListType(callbackList).toArray(lstManager);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            Arrays.sort(lstManager, new CallbackLoggerComparator<ListenerLogger>());
            for (int i = 0; i < lstManager.length; i++) {
                assertEquals(lstManager[i].getCallbackClassName(), listenersId[i],
                        "The callback methods were not called in the correct order.");
            }
        }
    }

    /**
     * Verifies if two callback methods that are executed before and after an
     * operation were called in order. For example, verifies if the PrePersist
     * was called before the PostPersist.
     * @param lifecycle1 the callback method which should be called before the
     *        operation.
     * @param lifecycle2 the callback method which should be called after the
     *        operation.
     * @param listenerId the listener where the callback method is.
     * @param formType the form for which the callback method was called.
     */
    public void verifyDifferentCallbackMethodOrder(final CallbackType lifecycle1, final CallbackType lifecycle2,
            final String listenerId, final String formType) {
        ListenerLogger lstManCallback1 = null;
        ListenerLogger lstManCallback2 = null;

        List lstLifecycle1 = bean.findCallbackEventByCallbackMethod(formType, lifecycle1, listenerId);
        List lstLifecycle2 = bean.findCallbackEventByCallbackMethod(formType, lifecycle2, listenerId);

        lstManCallback1 = (ListenerLogger) lstLifecycle1.get(0);
        lstManCallback2 = (ListenerLogger) lstLifecycle2.get(0);

        assertTrue(lstManCallback1.getInsertionDate() < lstManCallback2.getInsertionDate(),
                "The callback methods are not called in the correct order");
    }

    /**
     * Creates an instance of the entity under test.
     */
    protected abstract void createEntity();

    /**
     * Creates an entity and after that removes it.
     */
    protected abstract void createAndRemoveEntity();

    /**
     * Creates an entity and after that modifies it.
     */
    protected abstract void createAndModifyEntity();

    /**
     * Creates an entity, modifies and after that refreshes it.
     */
    protected abstract void createAndRefreshEntity();

    /**
     * Create a list with all listeners that must be called for the persist
     * operation. The list is ordered by the callback invocation order.
     * @return the list of listeners executed in order.
     */
    protected abstract String[] createListPersistListeners();

    /**
     * Create a list with all listeners that must be called for the remove
     * operation. The list is ordered by the callback invocation order.
     * @return the list of listeners executed in order.
     */
    protected abstract String[] createListRemoveListeners();

    /**
     * Create a list with all listners that must be called for the update
     * operation. The list is ordered by the callback invocation order.
     * @return the list of listeners executed in order.
     */
    protected abstract String[] createListUpdateListeners();

    /**
     * Create a list with all listners that must be called for the load
     * operation. The list is ordered by the callback invocation order.
     * @return the list of listeners executed in order.
     */
    protected abstract String[] createListLoadListeners();

    /**
     * Gets the name of the enitty created that extends form.
     * @return the form name.
     */
    protected abstract String getFormName();

    /**
     * Verifies if the same type of callback methods are called in the correct
     * order, as well as verifies if the callback methods that are executed
     * before and after an operation were called in order.
     * @param listeners a list of the listeners called ordered by invocation
     *        order.
     * @param lifecycletype1 the callback method executed before the operation.
     * @param lifecycletype2 the callback method executed afeter the operation.
     */
    private void verifyOrder(final String[] listeners, final CallbackType lifecycletype1, final CallbackType lifecycletype2) {
        try {
            // verifies if the callback methods are called in the correct order.
            verifySameCallbackMethodOrder(lifecycletype1, listeners, getFormName());
            // verifies if the callback methods are called in the correct order.
            verifySameCallbackMethodOrder(lifecycletype2, listeners, getFormName());
            // verifies if each lifecycletype1 is called before the
            // lifecycletype2 respective
            if (listeners.length > 0) {
                for (String strListener : listeners) {
                    verifyDifferentCallbackMethodOrder(lifecycletype1, lifecycletype2, strListener, getFormName());
                }
            }
        } finally {
            bean.deleteAll();
        }
    }

    /**
     * Verifies if the PrePersist and PostPersist are called before and after a
     * persist operation, respectively. Also, verifies if all listeners
     * specified for the entity class were called.
     */
    public void testPersistCallbackMethods() {
        //deletes all callback event from the database.
        bean.deleteAll();
        createEntity();
        String[] strListeners = createListPersistListeners();
        verifyOrder(strListeners, CallbackType.PRE_PERSIST, CallbackType.POST_PERSIST);
    }

    /**
     * Verifies if the PreRemove and PostRemove are called before and after a
     * remove operation, respectively. Also, verifies if all listeners specified
     * for the entity class were called.
     */
    public void testRemoveCallbackMethods() {
        //deletes all callback event from the database.
        bean.deleteAll();
        createAndRemoveEntity();
        String[] strListeners = createListRemoveListeners();
        verifyOrder(strListeners, CallbackType.PRE_REMOVE, CallbackType.POST_REMOVE);
    }

    /**
     * Verifies if the PreUpdate and PostUpdate are called before and after a
     * update operation, respectively. Also, verifies if all listeners specified
     * for the entity class were called.
     */
    public void testUpdateCallbackMethods() {
        //deletes all callback event from the database.
        bean.deleteAll();
        createAndModifyEntity();
        String[] strListeners = createListUpdateListeners();
        verifyOrder(strListeners, CallbackType.PRE_UPDATE, CallbackType.POST_UPDATE);
    }

    /**
     * Verifies if the PostLoad is called after the refresh operation.lso,
     * verifies if all listeners specified for the entity class were called.
     */
    public void testLoadCallbackMethods() {
        //deletes all callback event from the database.
        bean.deleteAll();
        createAndRefreshEntity();
        String[] strListeners = createListLoadListeners();
        try {
            // verifies if the PostLoad methods are called in the correct order.
            verifySameCallbackMethodOrder(CallbackType.POST_LOAD, strListeners, getFormName());
        } finally {
            bean.deleteAll();
        }
    }

}
