package com.example.passwordmanagerfx3;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;


public class FileManager {

    private ArrayList<String> websites;
    private ArrayList<String> passwords;
    private String currentUser;
    private String currentUserFile = "CurrentUser";
    private String passwordFilename = "Password";
    private String websiteFilename = "Website";
    private String currentPassword;
    private String currentWebsite;

    public FileManager() {
        websites = new ArrayList<String>();
        passwords = new ArrayList<String>();
    }

    public String convertWebsitesToString() {
        StringBuilder contentBuilder = new StringBuilder();
        String fileContents;
        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + websiteFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                contentBuilder.append(dataFromFile.nextLine()).append(System.lineSeparator());
            }
            dataFromFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        fileContents = contentBuilder.toString();
        return fileContents;
    }

    public String convertPasswordsToString() {
        StringBuilder contentBuilder = new StringBuilder();
        String fileContents;
        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + passwordFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                contentBuilder.append(dataFromFile.nextLine()).append(System.lineSeparator());
            }
            dataFromFile.close();

        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        fileContents = contentBuilder.toString();
        return fileContents;
    }
    public void initialiseCurrentUser() {
        readCurrentUserFile();
        readWebsiteFile();
        readPasswordFile();
    }

    public void deleteWebsite(String website) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + websiteFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            String currentLine;
            while (dataFromFile.hasNextLine()) {
                currentLine = dataFromFile.nextLine();
                if (!(currentLine.equals(website))) {
                    contentBuilder.append(currentLine).append(System.lineSeparator());
                }
            }
            dataFromFile.close();
            String fileContents;
            fileContents = contentBuilder.toString();
            FileWriter fileOutput = new FileWriter(currentUser + "\\" + websiteFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            dataToFile.write(fileContents);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public void deletePassword(String password) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + passwordFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            String currentLine;
            while (dataFromFile.hasNextLine()) {
                currentLine = dataFromFile.nextLine();
                if (!(currentLine.equals(password))) {
                    contentBuilder.append(currentLine).append(System.lineSeparator());
                }
            }
            dataFromFile.close();
            String fileContents;
            fileContents = contentBuilder.toString();
            FileWriter fileOutput = new FileWriter(currentUser + "\\" + passwordFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            dataToFile.write(fileContents);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public void replacePassword(String oldPassword, String newPassword) {
        StringBuilder contentBuilder = new StringBuilder();

        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + passwordFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                contentBuilder.append(dataFromFile.nextLine()).append(System.lineSeparator());
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
        try {
            FileWriter fileOutput = new FileWriter(currentUser + "\\" + passwordFilename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            String fileContents = contentBuilder.toString();
            fileContents = fileContents.replaceAll(oldPassword, newPassword);
            dataToFile.write(fileContents);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public void writeToPasswordFile() {
        try {
            FileWriter fileOutput = new FileWriter(currentUser + "\\" + passwordFilename,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            dataToFile.println(currentPassword);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void writeToWebsiteFile() {
        try {
            FileWriter fileOutput = new FileWriter(currentUser + "\\" + websiteFilename,true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            dataToFile.println(currentWebsite);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void writeToUserFile() {
        try {
            FileWriter fileOutput = new FileWriter(currentUserFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileOutput);
            PrintWriter dataToFile = new PrintWriter(bufferedWriter);
            dataToFile.println(currentUser);
            dataToFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e) {
            System.out.println("An IO error occurred.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void readPasswordFile() {
        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + passwordFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                passwords.add(dataFromFile.nextLine());
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }
    public void readWebsiteFile() {
        try {
            FileReader fileInput = new FileReader(currentUser + "\\" + websiteFilename);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                websites.add(dataFromFile.nextLine());
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void readCurrentUserFile() {
        try {
            FileReader fileInput = new FileReader(currentUserFile);
            BufferedReader bufferedReader = new BufferedReader(fileInput);
            Scanner dataFromFile = new Scanner(bufferedReader);
            dataFromFile.useDelimiter("\r?\n|\r");
            while (dataFromFile.hasNextLine()) {
                currentUser = dataFromFile.nextLine();
            }
            dataFromFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("File was not found.");
        } catch (Exception e) {
            System.out.println("An error occurred.");
        }
    }

    public void setCurrentUser(String user) {
        currentUser = user;
    }

    public ArrayList<String> getWebsites() {
        return websites;
    }

    public ArrayList<String> getPasswords() {
        return passwords;
    }

    public void setCurrentPassword(String givenPassword) {
        currentPassword = givenPassword;
    }

    public void setCurrentWebsite(String givenWebsite) {
        currentWebsite = givenWebsite;
    }
}
