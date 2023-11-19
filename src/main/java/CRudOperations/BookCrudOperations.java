package CRudOperations;

import Interface.CrudOperations;
import Model.Book;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class BookCrudOperations implements CrudOperations<Book> {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(System.getenv("url"),System.getenv("user"),System.getenv("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM book")) {

            while (resultSet.next()) {
                int id= resultSet.getInt("id");
                String bookName= resultSet.getString("book_name");
                int pageNumber= resultSet.getInt("page_number");
                String topic= resultSet.getString("topic");
                Date releaseDate= resultSet.getDate("release_date");
                Book book = new Book(id, bookName, pageNumber, topic, releaseDate);

                books.add(book);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public List<Book> saveAll(List<Book> toSave) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO book (id, book_name, page_number, topic, release_date) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            for (Book book : toSave) {
                statement.setInt(1, book.getId());
                statement.setString(2, book.getBookName());
                statement.setInt(3,book.getPageNumber());
                statement.setString(4,book.getTopic());
                statement.setDate(5,book.getRealeseDate());
                statement.addBatch();
            }

            statement.executeBatch();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    toSave.get(generatedKeys.getRow() - 1).setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return toSave;
    }


    @Override
    public Book save(Book toSave) {
        try{
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO book (id, book_name, page_number, topic, release_date) VALUES (?, ?, ?, ?, ?)");
            statement.setInt(1, toSave.getId());
            statement.setString(2, toSave.getBookName());
            statement.setInt(3,toSave.getPageNumber());
            statement.setString(4,toSave.getTopic());
            statement.setDate(5,toSave.getRealeseDate());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toSave;
    }

    @Override
    public Book delete(Book toDelete) {
        try{
            PreparedStatement statement = connection.prepareStatement("delete from book where id = ?");
            statement.setInt(1,toDelete.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return toDelete;
    }
}
