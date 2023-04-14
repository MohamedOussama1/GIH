package ma.uiass.eia.pds.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class MarkovController {


    public static void main(String[] args) throws IOException {

        // Define the command to execute the Python script

        String[] cmd = {"python", "Markov.py"};

        // Create a new ProcessBuilder with the command
        ProcessBuilder processBuilder = new ProcessBuilder(cmd);

        // Set the working directory for the process builder


        processBuilder.directory(new File("C:\\Users\\Fujitsu LifeBook u\\Desktop\\pds\\Send\\GIH\\src\\main\\java\\ma\\uiass\\eia\\pds\\controller"));


        // Start the process
        Process process = processBuilder.start();

        System.out.println(process);

        // Read the output of the process

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Wait for the process to finish
        try {
            int exitCode = process.waitFor();
            System.out.println("Process exited with code " + exitCode);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

