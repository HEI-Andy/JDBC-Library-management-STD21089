package org.example;

import CRudOperations.AuthorCrudOperations;
import CRudOperations.BookCrudOperations;
import CRudOperations.SubscribersCrudOperations;
import Model.Author;
import Model.Book;
import Model.Subscribers;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        AuthorCrudOperations authorCrudOperations= new AuthorCrudOperations();
        BookCrudOperations bookCrudOperations= new BookCrudOperations();
        SubscribersCrudOperations subscribersCrudOperations= new SubscribersCrudOperations();

        // Save all authors
        List<Author> authorsToSave = new ArrayList<>();
        authorsToSave.add(new Author(1, "John Doe", 'M'));
        authorsToSave.add(new Author(2, "Jane Smith", 'F'));

        List<Author> savedAuthors = authorCrudOperations.saveAll(authorsToSave);
        for (Author author : savedAuthors) {
            System.out.println("Saved Author: " + author);
        }

        // Find all authors
         List<Author> listOfAuthor= authorCrudOperations.findAll();
         for (Author author : listOfAuthor){
         System.out.println("List of authors: "+author);
         }

         // Save an author
         Author authorToSave = new Author(3, "New Author", 'M');
         System.out.println("\nSaving a new author:");
         Author savedAuthor = authorCrudOperations.save(authorToSave);
         System.out.println("New author saved: " + savedAuthor);

         // Delete an author
         List<Author> allAuthorsBeforeDeletion = authorCrudOperations.findAll();
         for (Author author : allAuthorsBeforeDeletion) {
         System.out.println(author);
         }
         if (!allAuthorsBeforeDeletion.isEmpty()) {
         Author authorToDelete = allAuthorsBeforeDeletion.get(1);
         System.out.println("\nDeleting an author:");
         authorCrudOperations.delete(authorToDelete);
         System.out.println("Author deleted: " + authorToDelete);
         } else {
         System.out.println("\nNo authors to delete.");
         }
    }

}
