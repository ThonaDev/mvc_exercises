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

    // 1. List all users
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

    // 2. Search user by UUID
    public User findByUuid(String uuid) {

        String sql = "SELECT * FROM users WHERE uuid = ?";

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, uuid);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {

                int id = resultSet.getInt("id");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String profile = resultSet.getString("profile");

                return new User(id, uuid, userName, email, password, profile);
            }

        } catch (Exception exception) {
            System.out.println("❌ Error during search by UUID: " + exception.getMessage());
        }

        return null;
    }

    // 3. Search users by name
    public List<User> findByName(String name) {

        String sql = "SELECT * FROM users WHERE LOWER(user_name) LIKE LOWER(?)";
        List<User> users = new ArrayList<>();

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, "%" + name + "%");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String uuid = resultSet.getString("uuid");
                String userName = resultSet.getString("user_name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String profile = resultSet.getString("profile");

                users.add(new User(id, uuid, userName, email, password, profile));
            }

        } catch (Exception exception) {
            System.out.println("❌ Error during search by name: " + exception.getMessage());
        }

        return users;
    }

    // 4. Delete user by UUID
    public int removeByUuid(String uuid) {

        String sql = "DELETE FROM users WHERE uuid = ?";

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, uuid);

            return preparedStatement.executeUpdate();

        } catch (Exception exception) {
            System.out.println("❌ Error during user deletion: " + exception.getMessage());
        }

        return 0;
    }

    // 5. Update user by UUID
    public User updateByUuid(User user) {

        String sql = """
                UPDATE users
                SET user_name = ?,
                    email = ?,
                    password = ?,
                    profile = ?
                WHERE uuid = ?
                """;

        try (Connection connection = DataConnectionConfigure.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getProfile());
            preparedStatement.setString(5, user.getUuid());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected <= 0) {
                throw new RuntimeException("User not found.");
            }

            return user;

        } catch (Exception exception) {
            System.out.println("❌ Error during user update: " + exception.getMessage());
        }

        return null;
    }

    // 6. Create user
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
                throw new RuntimeException("Failed to insert new user");
            }

            return user;

        } catch (Exception exception) {
            System.out.println("❌ Error during insert new user: " + exception.getMessage());
        }

        return null;
    }
}