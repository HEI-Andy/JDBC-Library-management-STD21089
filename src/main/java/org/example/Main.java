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

         System.out.println("All books:");
         List<Book> allBooks = bookCrudOperations.findAll();
         for (Book book : allBooks) {
         System.out.println(book);
         }

         List<Book> booksToSave = new ArrayList<>();
         booksToSave.add(new Book(1, "Book 1", 150, "Topic 1", Date.valueOf("2023-02-01")));
         booksToSave.add(new Book(2, "Book 2", 180, "Topic 2", Date.valueOf("2023-03-01")));

         List<Book> savedBooks = bookCrudOperations.saveAll(booksToSave);
         System.out.println("New books saved:");
         for (Book book : savedBooks) {
         System.out.println(book);
         }

         Book newBook = new Book(1, "New Book", 200, "New Topic", Date.valueOf("2023-01-01"));
         bookCrudOperations.save(newBook);
         System.out.println("New book added: " + newBook);

         List<Book> allBooksBeforeDeletion = bookCrudOperations.findAll();
         if (!allBooksBeforeDeletion.isEmpty()) {
         Book bookToDelete = allBooksBeforeDeletion.get(0);
         System.out.println("\nDeleting a book:");
         bookCrudOperations.delete(bookToDelete);
         System.out.println("Book deleted: " + bookToDelete);
         } else {
         System.out.println("\nNo books to delete.");
         }

         System.out.println("All subscribers:");
         List<Subscribers> allSubscribers = subscribersCrudOperations.findAll();
         for (Subscribers subscriber : allSubscribers) {
         System.out.println(subscriber);
         }

         List<Subscribers> subscribersToSave = new ArrayList<>();
         subscribersToSave.add(new Subscribers(1, "New Subscriber 1", "Reference 1"));
         subscribersToSave.add(new Subscribers(2, "New Subscriber 2", "Reference 2"));

         System.out.println("saving All subscribers:");
         List<Subscribers> allSubscribersBeforeSaving = subscribersCrudOperations.findAll();
         for (Subscribers subscriber : allSubscribersBeforeSaving) {
         System.out.println(subscriber);
         }

        // Save a subscriber
         Subscribers subscriberToSave = new Subscribers(3, "New Subscriber 3", "Reference 3");

         System.out.println("\nSaving a new subscriber:");
         Subscribers savedSubscriber = subscribersCrudOperations.save(subscriberToSave);
         System.out.println("New subscriber saved: " + savedSubscriber);

        System.out.println("All subscribers before deletion:");
        List<Subscribers> allSubscribersBeforeDeletion = subscribersCrudOperations.findAll();
        for (Subscribers subscriber : allSubscribersBeforeDeletion) {
            System.out.println(subscriber);
        }
        if (!allSubscribersBeforeDeletion.isEmpty()) {
            // Delete a subscriber
            Subscribers subscriberToDelete = allSubscribersBeforeDeletion.get(0);
            System.out.println("\nDeleting a subscriber:");
            subscribersCrudOperations.delete(subscriberToDelete);
            System.out.println("Subscriber deleted: " + subscriberToDelete);
        } else {
            System.out.println("\nNo subscribers to delete.");
        }
    }

}
