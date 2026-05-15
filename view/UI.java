package mvc_exercise.view;

import mvc_exercise.controller.UserController;
import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;
import mvc_exercise.utils.APIResponseTemplate;

import java.util.List;
import java.util.Scanner;

public class UI {

    private final UserController userController = new UserController();
    private final Scanner scanner = new Scanner(System.in);

    public static void getRendered() {

        UI ui = new UI(); // internal object (needed because scanner + controller are not static)

        while (true) {
            System.out.println("\n========== USER MENU ==========");
            System.out.println("1. Create User");
            System.out.println("2. Get All Users");
            System.out.println("3. Get User By UUID");
            System.out.println("4. Update User");
            System.out.println("5. Delete User");
            System.out.println("6. Search User By Name");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            int option = ui.scanner.nextInt();
            ui.scanner.nextLine();

            switch (option) {

                case 1 -> {
                    System.out.println("😍 Create user:");
                    System.out.print("[+] Enter your name: ");
                    String name = ui.scanner.nextLine();

                    System.out.print("[+] Enter your email: ");
                    String email = ui.scanner.nextLine();

                    System.out.print("[+] Enter your password: ");
                    String password = ui.scanner.nextLine();

                    CreateUserDto dto = new CreateUserDto(name, email, password);

//                    System.out.println(ui.userController.createUser(dto));
                    TableView.renderUserTable(dto);
                }

                case 2 -> System.out.println(ui.userController.getAllUsers());

                case 3 -> {
                    System.out.print("Enter UUID: ");
                    String uuid = ui.scanner.nextLine();
                    System.out.println(ui.userController.getUserByUuid(uuid));
                }

                case 4 -> {
                    System.out.print("UUID: ");
                    String uuid = ui.scanner.nextLine();

                    System.out.print("New Name: ");
                    String name = ui.scanner.nextLine();

                    System.out.print("New Email: ");
                    String email = ui.scanner.nextLine();

                    System.out.print("New Password: ");
                    String password = ui.scanner.nextLine();

                    System.out.print("New Profile: ");
                    String profile = ui.scanner.nextLine();

                    UpdateRequestDto dto = new UpdateRequestDto(name, email, password, profile);

                    System.out.println(ui.userController.updateUserByUuid(uuid, dto));
                }

                case 5 -> {
                    System.out.print("Enter UUID: ");
                    String uuid = ui.scanner.nextLine();
                    System.out.println(ui.userController.deleteUserByUuid(uuid));
                }

                case 6 -> {
                    System.out.print("Enter name: ");
                    String name = ui.scanner.nextLine();
                    System.out.println(ui.userController.searchUserByName(name));
                }

                case 0 -> {
                    System.out.println("Bye!");
                    return;
                }

                default -> System.out.println("Invalid option!");
            }
        }
    }
}