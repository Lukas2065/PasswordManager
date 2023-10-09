package com.example.passwordmanagerfx3;

import java.security.SecureRandom;
import java.util.HashSet;

public class PasswordGenerator {

    private String LOWERCASE_CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    private String UPPERCASE_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private String NUMBERS = "0123456789";
    private String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>/?";

    public String generatePassword(int length, Boolean includeUpper, Boolean includeLower, Boolean includeNumbers, Boolean includeSpecial) {

        StringBuilder passwordBuilder = new StringBuilder();
        SecureRandom random = new SecureRandom();

        String characters = "";
        if (includeLower) {
            characters += LOWERCASE_CHARACTERS;
        }
        if (includeUpper) {
            characters += UPPERCASE_CHARACTERS;
        }
        if (includeNumbers) {
            characters += NUMBERS;
        }
        if (includeSpecial) {
            characters += SPECIAL_CHARACTERS;
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            passwordBuilder.append(randomChar);
        }

        return passwordBuilder.toString();
    }

    public Double calculateEntropy(String password) {
        int passwordLength = password.length();
        if (passwordLength == 0) {
            return 0.0;
        }

        HashSet<Character> uniqueCharacters = new HashSet<>();
        for (char c : password.toCharArray()) {
            uniqueCharacters.add(c);
        }

        int characterSpace = uniqueCharacters.size();
        Double entropy = Math.log(Math.pow(characterSpace, passwordLength)) / Math.log(2);

        return entropy;
    }
}
