package mvc_exercise.model;

import mvc_exercise.utils.DataConnectionConfigure;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

// repository or DAO - data access object
public class UserDao {

    public List<User> findAll() {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection connection = DataConnectionConfigure.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String uuid = resultSet.getString("uuid");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String profile = resultSet.getString("profile");

                User user = new User(id, uuid, userName, email, password, profile);
                users.add(user);
            }
        } catch (Exception exception) {
            System.out.println("❌ Connection failed / Selection error: " + exception.getMessage());
        }
        return users;
    }

    /**
     * Removes a user from the database by their primary ID.
     * @return number of rows affected (1 if successful, 0 if not found)
     */
    public int remove(User user) {
        String sql = "DELETE FROM users WHERE id = ?";

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, user.getId());
            return preparedStatement.executeUpdate();

        } catch (Exception exception) {
            System.out.println("❌ Error during user deletion: " + exception.getMessage());
            return 0;
        }
    }

    /**
     * Updates an existing user's information in the database.
     * @return The updated User object, or null if update fails.
     */
    public User update(User uu) {
        String sql = """
                UPDATE users 
                SET user_name = ?, 
                    email = ?, 
                    password = ?, 
                    profile = ?
                WHERE id = ?
                """;

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, uu.getName());
            preparedStatement.setString(2, uu.getEmail());
            preparedStatement.setString(3, uu.getPassword());
            preparedStatement.setString(4, uu.getProfile());
            preparedStatement.setInt(5, uu.getId());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected <= 0) {
                throw new RuntimeException("User with ID " + uu.getId() + " was not found in the database.");
            }
            return uu;

        } catch (Exception exception) {
            System.out.println("❌ Error during user update: " + exception.getMessage());
            return null;
        }
    }

    public User save(User user) {
        String sql = """
                INSERT INTO users(uuid, user_name, email, password, profile)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUuid());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getProfile());

            int rowAffected = preparedStatement.executeUpdate();
            if (rowAffected <= 0) {
                throw new RuntimeException("Failed to insert new data into table users");
            }
            return user;
        } catch (Exception exception) {
            System.out.println("❌ Error during insert new user: " + exception.getMessage());
        }
        return null;
    }
}