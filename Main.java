package mvc_exercise;

import mvc_exercise.view.UI;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main {
    public static void main(String[] args){
        UI.getRendered();

        // test connection
        /*String username = "postgres";
        String password = "thona09062004";
        String url = "jdbc:postgresql://localhost:5432/user_db";
        try (Connection connection = DriverManager.getConnection(
                url,
                username,
                password
        )){
            System.out.println("Connection Success!");
        }catch (Exception e){
            System.out.println("Connection Failed!");
        }*/
    }
}
