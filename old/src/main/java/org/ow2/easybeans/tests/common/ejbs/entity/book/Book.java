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
 * $Id: Book.java 5369 2010-02-24 14:58:19Z benoitf $
 * --------------------------------------------------------------------------
 */

package org.ow2.easybeans.tests.common.ejbs.entity.book;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Defines a book with its name, id and author.
 * @author Gisele Pinheiro Souza
 * @author Eduardo Studzinski Estima de Castro
 *
 */
@Entity
@Table(name = "BOOK")
public class Book implements java.io.Serializable {

    /**
     * The version value.
     */
    private static final long serialVersionUID = 1350259495852814154L;

    /**
     * Book Id.
     */
    private int id;

    /**
     * Name of the book.
     */
    private String name;

    /**
     * The book author.
     */
    private String author;

    /**
     * Default constructor.
     */
    public Book(){
    }

    /**
     * Parametrized constructor.
     * @param id book id
     * @param name book name
     * @param author the book author
     */
    public Book(final int id, final String name, final String author){
        setId(id);
        setName(name);
        setAuthor(author);
    }

    /**
     * Gets the book Id.
     * @return the id of the book.
     */
    @Id
    public int getId() {
        return id;
    }

    /**
     * Sets book Id.
     * @param id the id's book
     */
    public void setId(final int id) {
        this.id = id;
    }

    /**
     * Sets the name.
     * @param name of book.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Gets the book name.
     * @return name of the book.
     */
    public String getName() {
        return name;
    }

    /**
     * Computes a string representation of this book.
     * @return string representation.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book[id=").append(id).append(", name=").append(getName()).append("]");
        return sb.toString();
    }

    /**
     * Gets the book author.
     * @return the author name.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the book author.
     * @param author the author name.
     */
    public void setAuthor(final String author) {
        this.author = author;
    }

}
