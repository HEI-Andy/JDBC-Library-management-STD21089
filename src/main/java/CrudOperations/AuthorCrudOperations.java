package CrudOperations;

import Interface.CrudOperations;
import Model.Author;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class AuthorCrudOperations implements CrudOperations<Author> {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(System.getenv("url"),System.getenv("user"),System.getenv("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM author")) {

            while (resultSet.next()) {
                int id= resultSet.getInt("id");
                String name= resultSet.getString("name");
                char sex= resultSet.getString("sex").charAt(0);
                Author author= new Author(id, name, sex);
                authors.add(author);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return authors;
    }

    @Override
    public List<Author> saveAll(List<Author> toSave) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO author (id, name, sex) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            for (Author author : toSave) {
                statement.setInt(1, author.getId());
                statement.setString(2,author.getName());
                statement.setString(3,String.valueOf(author.getSex()));
                statement.addBatch();
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                while (generatedKeys.next()) {
                    toSave.get(generatedKeys.getRow() - 1).setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toSave;
    }

    @Override
    public Author save(Author toSave) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO author (id, name, sex) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, toSave.getId());
            statement.setString(2, toSave.getName());
            statement.setString(3, String.valueOf(toSave.getSex()));

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    toSave.setId(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toSave;
    }

    @Override
    public Author delete(Author toDelete) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM author WHERE id = ?")) {

            statement.setInt(1, toDelete.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return toDelete;
    }
}
