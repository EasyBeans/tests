/**
 * EasyBeans
 * Copyright (C) 2011 Bull S.A.S.
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
 * $Id: TesterBeanClient.java 6148 2012-01-25 14:51:49Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.osgi.itests.applications.ipojoclient;

import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.File;
import java.io.IOException;

import org.ow2.easybeans.osgi.itests.applications.osgi.ITester;



/**
 * @author Florent Benoit
 */
public class TesterBeanClient {

    private String outputDirectory = null;

    private ITester tester;


    public void setTester(ITester tester) {
        this.tester = tester;
    }

    public void starting() throws IOException {
        System.out.println("TesterBeanClient.starting()");
        this.outputDirectory = System.getProperty("output.directory");
        testCreate();
    }

    private void testCreate() throws IOException {
        File jFile = new File(outputDirectory + File.separator  + "testCreateIPOJO.txt");
        PrintWriter writer = new PrintWriter(new FileWriter(jFile, false), true);
        try {
            int i = this.tester.create();
            writer.write("" + i);
            writer.write("\n");
        } catch (RuntimeException e) {
            writer.write("KO\n");
            e.printStackTrace();
            e.printStackTrace(writer);
        } catch (Exception e) {
            e.printStackTrace();
            writer.write("KO\n");
            e.printStackTrace(writer);
        } catch (Error e) {
            e.printStackTrace();
            writer.write("KO\n");
            e.printStackTrace(writer);
        } finally {
            writer.close();
        }

    }

    public void stopping() {
        System.out.println("TesterBeanClient.stopping()");
    }

}
