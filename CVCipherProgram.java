/*
 * Program written by: Raghav Bansal D20123625
 * Date: 10/10/23
 * Compiler used: Visual Studio Code
 */

//A program that will implement Caesar Cipher and Vigeneré Cipher. Ask for user to choose if user wants to encrypt or decrypt, and then ask for Caser or vigenere, and the Text want to encrypt or decrypt as per choice, and the shift key, if caeser is choose or Vigenere key if vigenere is choosen. Program will run in loop untill user choose exit

// Applying the algorithm for encryption E(x) = (X + n)mod 26 where x is character and n is key shift value
//Decryption D(x) =(X - n)mod 26 where x is character and n is key shift value

import java.util.Scanner;

//function for Cipher program where Ceaser encryption, Decryption and vigenere encryption or decryption is written
public class CVCipherProgram {
    // Caeser Cipher encryption
    public static String caesarCipherEncrypt(String text, int shift) {
        // It is class used to create a mutable for modification of characters. it is an
        // alternative version of string class.
        StringBuilder result = new StringBuilder();

        // Ceaser Cipher to encrypt Charcater
        // Converts this string to a new character array.
        for (char CaeserEncryptcharacter : text.toCharArray()) {
            if (Character.isLetter(CaeserEncryptcharacter)) {
                char base = Character.isLowerCase(CaeserEncryptcharacter) ? 'a' : 'A';
                char encryptedCharacter = (char) (((CaeserEncryptcharacter - base + shift) % 26) + base);
                result.append(encryptedCharacter);
            } else {
                result.append(CaeserEncryptcharacter);
            }
        }

        // Returns a string containing the characters in this sequence in the same order
        // as this sequence. The length of the string will be the length of this
        // sequence.
        return result.toString();
    }

    // Caeser cipher to Decrypt the characters, It is taking shift as a parameter,
    // so It can give us exact output
    public static String caesarCipherDecrypt(String text, int shift) {
        return caesarCipherEncrypt(text, 26 - (shift % 26));
    }

    // Function for Vigenere Cipher (Taking text and the keyword key as a parameter)
    public static String vigenereCipherEncrypt(String text, String keyword) {
        StringBuilder result = new StringBuilder();
        keyword = keyword.toUpperCase();
        int IndexKeyboard = 0;

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char encryptedChar = (char) (((character - base + keyword.charAt(IndexKeyboard) - 'A') % 26) + base);
                result.append(encryptedChar);
                IndexKeyboard = (IndexKeyboard + 1) % keyword.length();
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    // Function to Decrypt Vigenere Cipher taking text and Keyword as a parameter,
    // Everything is sname except it will subtract the Index key from character and
    // base
    public static String vigenereCipherDecrypt(String text, String keyword) {
        StringBuilder result = new StringBuilder();
        keyword = keyword.toUpperCase();
        int IndexKeyboard1 = 0;

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isLowerCase(character) ? 'a' : 'A';
                char decryptedChar = (char) (((character - base - (keyword.charAt(IndexKeyboard1) - 'A') + 26) % 26)
                        + base);
                result.append(decryptedChar);
                IndexKeyboard1 = (IndexKeyboard1 + 1) % keyword.length();
            } else {
                result.append(character);
            }
        }

        return result.toString();
    }

    // Main Function
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        // While loop, so it won't quit untill user choose to
        // Creating a menu, that will ask for (Encrypt, Decrypt, or type 'exit' to quit)
        // I made a Decision tree, So I don't miss the callings of the function in the
        // right selection
        while (!exit) {
            System.out.print("Choose an Option (Encrypt, Decrypt, or type 'exit' to quit): ");
            String option = scanner.nextLine().trim().toLowerCase();

            if (option.equals("exit")) {
                exit = true;
            } else if (option.equals("Encrypt")) {
                System.out.print("Choose a cipher (Caesar or Vigenere): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("Caesar")) {
                    System.out.print("Enter the Plaintext: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter the shift (key) value: ");
                    int shift = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    String encryptedText = caesarCipherEncrypt(text, shift);
                    System.out.println("Encrypted text: " + encryptedText);

                } else if (choice.equals("Vigenere")) {
                    System.out.print("Enter the Plain text: ");
                    String text = scanner.nextLine();

                    System.out.print("Enter the keyword: ");
                    String keyword = scanner.nextLine();

                    String encryptedText = vigenereCipherEncrypt(text, keyword);
                    System.out.println("Encrypted text: " + encryptedText);

                } else {
                    System.out.println("Invalid choice. Please choose either 'Caesar' or 'Vigenere'.");
                }

            } else if (option.equals("decrypt")) {
                System.out.print("Choose a cipher (Caesar or Vigenere): ");
                String choice = scanner.nextLine().trim().toLowerCase();

                if (choice.equals("Caesar")) {
                    System.out.print("Enter the Plain text: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter the shift (key) value: ");
                    int shift = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline character

                    String decryptedText = caesarCipherDecrypt(text, shift);
                    System.out.println("Decrypted text: " + decryptedText);
                } else if (choice.equals("Vigenere")) {
                    System.out.print("Enter the Plain text: ");
                    String text = scanner.nextLine();
                    System.out.print("Enter the keyword: ");
                    String keyword = scanner.nextLine();

                    String decryptedText = vigenereCipherDecrypt(text, keyword);
                    System.out.println("Decrypted text: " + decryptedText);
                } else {
                    System.out.println("Invalid choice. Please choose either 'Caesar' or 'Vigenere'.");
                }
            } else {
                System.out.println("Invalid operation. Please choose either 'Encrypt', 'Decrypt', or 'exit' to quit.");
            }
        }

        scanner.close();
    }
}
