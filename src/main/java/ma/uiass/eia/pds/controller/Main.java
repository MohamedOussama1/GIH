package ma.uiass.eia.pds.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {




    public static void main(String[] args) throws IOException {

        Boolean test = false;

        String username = "user1";
        String password = "1234";
        final String USERS_FILE = "C:\\Users\\Fujitsu LifeBook u\\Desktop\\pds\\Send\\GIH\\src\\main\\resources\\realm.properties"; // Change this to the path of your realm.properties file


        System.out.println("username: " + username + " password: " + password);

        // Read the realm.properties file
        String content = new String(Files.readAllBytes(Paths.get(USERS_FILE)));
        System.out.println("@@@@@@@@@  "+content);
        System.out.println("realm.properties contents: " + content);

        // Split the content into lines
        String[] lines = content.split("\\r?\\n");

        System.out.println(lines);

        // Search for a matching user and password
        for (String line : lines) {
            String[] tokens = line.split(",");
            System.out.println(tokens);
            if (tokens.length == 2) {
                System.out.println("realm.properties username: " + tokens[0] + " password: " + tokens[1]);
                if (username.equals(tokens[0]) && password.equals(tokens[1])) {
                    test = true;
                }
            }
        }


    }
}
