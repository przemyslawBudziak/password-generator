package org.example;

import java.util.Scanner;

public class Menu {
    private Generator generator;

    public Menu() {
    }

    public void start() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Press:\n" +
                "1 - to generate password\n" +
                "2 - to quit");
        int option = scanner.nextInt();
        scanner.nextLine();
        while (option == 1) {
            int length;
            boolean[] include = new boolean[4];

            System.out.print("Length: ");
            length = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Include lowercase letters [y/n]: ");
            if (scanner.nextLine().equals("y")) {
                include[0] = true;
            }
            System.out.print("Include uppercase letters [y/n]: ");
            if (scanner.nextLine().equals("y")) {
                include[1] = true;
            }
            System.out.print("Include numbers [y/n]: ");
            if (scanner.nextLine().equals("y")) {
                include[2] = true;
            }
            System.out.print("Include special symbols [y/n]: ");
            if (scanner.nextLine().equals("y")) {
                include[3] = true;
            }

            generator = new Generator(length, include[0],include[1], include[2], include[3]);
            System.out.println("Password: " + generator.generate());

            System.out.println("Type:\n" +
                    "1 - to generate password\n" +
                    "2 - to quit");
            option = scanner.nextInt();
            scanner.nextLine();
        }

    }

//    private void test(int length, boolean[] booleans) {
//        System.out.println();
//        System.out.print(length + " ");
//        for (boolean b : booleans) {
//            System.out.print(b + " ");
//        }
//        System.out.println();
//    }

}
