package org.example;

import java.util.Scanner;

public class Menu {
    private static final String MAIN_MENU = """
            Press:
                1 - to generate password
                2 - to check your password
                3 - to quit""";
    private static final String ERROR_MESSAGE = "Input ERROR!";
    private static final String[] YES = {"y", "yes", "Y", "YES"};
    private static final String[] CONTENT = {"lowercase letters", "uppercase letters", "numbers", "special symbols"};

    private final Scanner scanner = new Scanner(System.in);

    public Menu() {
    }

    public void start() {
        int selection = 0;
        while (selection != 3) {
            System.out.println(MAIN_MENU);
            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
            } else {
                selection = 0;
            }
            scanner.nextLine();
            switch (selection) {
                case 1 -> generatePassword();
                case 2 -> checkPassword();
                case 3 -> {}
                default -> System.out.println(ERROR_MESSAGE);
            }
        }
        scanner.close();
    }

    private void generatePassword() {
        int length = passwordLength();
        boolean[] include = charsToInclude();

        Generator generator = new Generator(length, include[0], include[1], include[2], include[3]);
        Password password = generator.generate();
        System.out.println("\n\tPASSWORD: " + password.toString());
        passwordScore(password);
    }

    private int passwordLength() {
        int length = -1;
        while (length < 8 || length > 128) {
            System.out.print("Password length (8 - 128 characters): ");
            if (scanner.hasNextInt()) {
                length = scanner.nextInt();
            } else {
                System.out.println(ERROR_MESSAGE);
            }
            scanner.nextLine();
        }
        return length;
    }

    private boolean[] charsToInclude() {
        boolean[] include = new boolean[4];
        int counter = 0;
        while (counter == 0) {
            for (int i = 0; i <= 3; i++) {
                System.out.printf("Include %s? [Y/n]: ", CONTENT[i]);
                String s = scanner.nextLine();
                for (String yes : YES) {
                    if (s.equals(yes)) {
                        include[i] = true;
                        counter++;
                        break;
                    }
                }
            }
            if (counter == 0) {
                System.out.println(ERROR_MESSAGE);
            }
        }
        return include;
    }

    private void checkPassword() {
        System.out.print("Your password: ");
        Password password = new Password(scanner.nextLine());
        passwordScore(password);
    }

    private void passwordScore(Password password) {
        PasswordScore passwordScore = new PasswordScore(password);
        passwordScore.timeToCrack();
    }

}
