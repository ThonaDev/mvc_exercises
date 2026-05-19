package mvc_exercise.view;

import mvc_exercise.controller.UserController;
import mvc_exercise.model.dto.CreateUserDto;
import mvc_exercise.model.dto.UpdateRequestDto;
import mvc_exercise.model.dto.UserResponseDto;
import mvc_exercise.utils.APIResponseTemplate;

import java.util.Scanner;

public class UI {

    private final static UserController userController
            = new UserController();

    private final static Scanner scanner = new Scanner(System.in);

    private static void thumbnail() {
        System.out.println("""
       =============== User Management System ================
       1. Create User
       2. Search User by UUID
       3. Search User by name
       4. Delete User by UUID
       5. Update User by UUID
       6. List all Users
       0. Exit""");
    }

    private static int insertOption() {
        System.out.print("[+] Insert your option: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static void getRendered() {

        while (true) {

            thumbnail();
            System.out.println("--");

            switch (insertOption()) {

                case 1 -> {

                    System.out.println("😍 Create user");

                    System.out.print("[+] Insert name: ");
                    String name = scanner.nextLine();

                    System.out.print("[+] Insert email: ");
                    String email = scanner.nextLine();

                    System.out.print("[+] Insert password: ");
                    String password = scanner.nextLine();

                    CreateUserDto createUserDto =
                            new CreateUserDto(name, email, password);

                    APIResponseTemplate<UserResponseDto> createdUser =
                            userController.createUser(createUserDto);

                    System.out.println(createdUser.message());
                }

                case 2 -> {

                    System.out.println("Enter uuid to search user:");

                    String uuid = scanner.nextLine();

                    System.out.println(
                            userController.getUserByUuid(uuid)
                    );
                }

                case 3 -> {

                    System.out.println("Enter user name to search user:");

                    String name = scanner.nextLine();

                    userController.searchUserByName(name)
                            .data()
                            .stream()
                            .forEach(System.out::println);
                }

                case 4 -> {

                    System.out.println("Enter uuid to delete user:");

                    String uuid = scanner.nextLine();

                    System.out.println(
                            userController.deleteUserByUuid(uuid)
                    );
                }

                case 5 -> {

                    System.out.println("Enter uuid to update user:");

                    String uuid = scanner.nextLine();

                    System.out.print("[+] Insert new name: ");
                    String name = scanner.nextLine();

                    System.out.print("[+] Insert new email: ");
                    String email = scanner.nextLine();

                    System.out.print("[+] Insert new password: ");
                    String password = scanner.nextLine();

                    System.out.print("[+] Insert new profile: ");
                    String profile = scanner.nextLine();

                    UpdateRequestDto dto =
                            new UpdateRequestDto(name, email, password, profile);

                    System.out.println(
                            userController.updateUserByUuid(uuid, dto)
                    );
                }

                case 6 -> {

                    System.out.println(userController.getAllUsers());
                }

                case 0 -> {

                    System.out.println("System closed...");

                    try {
                        Thread.sleep(100);
                    } catch (Exception ignore) {
                    }

                    System.exit(0);
                }

                default -> System.out.println("No Invalid option");
            }
        }
    }
}