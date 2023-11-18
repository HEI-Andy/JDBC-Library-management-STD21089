package org.example;

import CRudOperations.AuthorCrudOperations;
import CRudOperations.BookCrudOperations;
import CRudOperations.SubscribersCrudOperations;
import Model.Author;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AuthorCrudOperations authorCrudOperations= new AuthorCrudOperations();
        BookCrudOperations bookCrudOperations= new BookCrudOperations();
        SubscribersCrudOperations subscribersCrudOperations= new SubscribersCrudOperations();

        List<Author> authorsToSave = new ArrayList<>();
        authorsToSave.add(new Author(1, "John Doe", 'M'));
        authorsToSave.add(new Author(2, "Jane Smith", 'F'));

        List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);

        for (Author author : savedAuthors) {
            System.out.println("Saved Author: " + author);
        }

        List<Author> listOfAuthor= authorCrudOperations.findAll();
        for (Author author : listOfAuthor){
            System.out.println("List of authors: "+author);
        }
    }
}
