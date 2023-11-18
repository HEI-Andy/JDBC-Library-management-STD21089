package CRudOperations;

import Interface.CrudOperations;
import Model.Book;

import java.util.List;

public class BookCrudOperations implements CrudOperations<Book> {
    @Override
    public List<Book> findAll() {
        return null;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        return null;
    }

    @Override
    public Book save(Book toSave) {
        return null;
    }

    @Override
    public Book delete(Book toDelete) {
        return null;
    }
}
