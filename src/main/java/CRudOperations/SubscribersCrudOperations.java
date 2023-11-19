package CRudOperations;

import Interface.CrudOperations;
import Model.Subscribers;
import lombok.NoArgsConstructor;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
public class SubscribersCrudOperations implements CrudOperations<Subscribers> {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection(System.getenv("url"),System.getenv("user"),System.getenv("password"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<Subscribers> findAll() {
        String sql = "select * from subscribers";
        List<Subscribers> listOfSubscribers = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name= resultSet.getString("name");
                String ref = resultSet.getString("ref");
                Subscribers subscribers = new Subscribers(id,name,ref);
                listOfSubscribers.add(subscribers);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listOfSubscribers;
    }

    @Override
    public List<Subscribers> saveAll(List<Subscribers> toSave) {
        try (PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO subscribers (id, name, reference) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            for (Subscribers subscriber : toSave) {
                statement.setInt(1, subscriber.getId());
                statement.setString(2, subscriber.getName());
                statement.setString(3, subscriber.getRef());
                statement.addBatch();
            }

            statement.executeBatch();

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
    public Subscribers save(Subscribers toSave) {
            try (PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO subscribers (id, name, ref) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

                statement.setInt(1, toSave.getId());
                statement.setString(2, toSave.getName());
                statement.setString(3, toSave.getRef());
                statement.executeUpdate();

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
    public Subscribers delete(Subscribers toDelete) {
        try (PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM subscribers WHERE id = ?")) {

            statement.setLong(1, toDelete.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return toDelete;
    }
}
