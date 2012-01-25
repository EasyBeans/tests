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
 * $Id: CreateDBCollegeHelper.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */
package org.ow2.easybeans.tests.common.helper;

/**
 * Creates all sql queries used to create and delete all college database tables.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 */
public final class CreateDBCollegeHelper  {

    /**
     * Default constructor.
     */
    private CreateDBCollegeHelper(){

    }

    /**
     * Returns the query that creates the table people as follow.<br>
     * People(CodePeople, NamePeople,CodeUniversity) Primary key: CodePeople
     * @return the query used to create the table
     */
    public static String createTablePeople() {
        String sql = "CREATE TABLE People (" + "CodePeople integer, " + "NamePeople varchar(30), "
                + "CodeUniversity integer, " + "PRIMARY KEY (CodePeople), "
                + "FOREIGN KEY (codeUniversity) REFERENCES University(CodeUniversity))";
        return sql;
    }

    /**
     * Returns the query that creates the table course as follow.<br>
     * Course (CodeCourse, NameCourse,CodeProf) Primary key: CodeCourse
     * @return the query used to create the table
     */
    public static String createTableCourse()  {
        String sql = "CREATE TABLE Course ( " + "CodeCourse integer, " + "NameCourse varchar(30), " + "CodeProf integer, "
                + "PRIMARY KEY (CodeCourse), FOREIGN KEY (codeProf) REFERENCES People(CodePeople))";
        return sql;
    }

    /**
     * Returns the query that creates the table courseallocation as follow.<br>
     * CourseAllocation(CodeCourse,CodeStudent, Year) Primary key:
     * CodeCourse,CodeStudent
     * @return the query used to create the table
     */
    public static String createTableCourseAllocation()  {
        String sql = "CREATE TABLE CourseAllocation ( " + "CodeCourse integer, " + "CodeStudent integer, "
                + "Year varchar(10), " + "PRIMARY KEY (CodeCourse,CodeStudent), "
                + "FOREIGN KEY (CodeCourse) REFERENCES Course(CodeCourse), "
                + "FOREIGN KEY (CodeStudent) REFERENCES People(CodePeople))";
        return sql;
    }

    /**
     * Returns the query that creates the table university as follow.<br>
     * University (CodeUniversity,NameUniversity) Primary key: CodeUniversity
     * @return the query used to create the table
     */
    public static String createTableUniversity(){
        String sql = "CREATE TABLE University ( " + "CodeUniversity integer, " + "NameUniversity varchar(30), "
                + "year varchar(10), " + "PRIMARY KEY (CodeUniversity) )";
        return sql;

    }

    /**
     * Returns the query that deletes the table people.
     * @return the query used to delete the table
     */
    public static String dropTablePeople(){
        return "DROP TABLE People CASCADE";
    }

    /**
     * Returns the query that deletes the table university.
     * @return the query used to delete the table
     */
    public static String dropTableUniversity(){
        return "DROP TABLE University CASCADE";
    }

    /**
     * Returns the query that deletes the table course.
     * @return the query used to delete the table
     */
    public static String dropTableCourse(){
        return "DROP TABLE Course CASCADE";
    }

    /**
     * Returns the query that deletes the table courseAllocation.
     * @return the query used to delete the table
     */
    public static String dropTableCourseAllocation(){
        return "DROP TABLE CourseAllocation  CASCADE";
    }

}
