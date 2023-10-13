/*
 * Program written by: Raghav Bansal D20123625
 * Date: 13/10/23
 * Compiler used: Visual Studio Code
 */


//A Java program to encrypt plaintext using a 2 * 2 Hill cipher.
// Program Ask for key matrix which can be written in matrix form with spaces, and then the plaintext in either UPpertext or lowertext. 
// E(K, P) = (K*P) mod 26

import java.util.Scanner;

public class HillCipher {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the key matrix (2x2): In format 1 20 23 7 ");
        int[][] keyMatrix = new int[2][2];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keyMatrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Enter the plaintext (in letters):");
        scanner.nextLine(); // Consume newline
        String plaintext = scanner.nextLine().toUpperCase(); // Convert to uppercase

        // Ensure the plaintext length is even
        if (plaintext.length() % 2 != 0) {
            plaintext += 'X';
        }

        // Encrypt the plaintext
        int[][] ciphertextMatrix = new int[2][plaintext.length() / 2];
        StringBuilder ciphertextText = new StringBuilder();
        int index = 0;
        for (int i = 0; i < plaintext.length(); i += 2) {
            int char1 = plaintext.charAt(i) - 'A';
            int char2 = plaintext.charAt(i + 1) - 'A';

            //The first encrypted character is calculated as char1 multiplied by the element of the key matrix in the first row and first column, added to char2 multiplied by the element of the key matrix in the first row and second column, mod 26.
            int encChar1 = (char1 * keyMatrix[0][0] + char2 * keyMatrix[0][1]) % 26;

            //  The second encrypted character is calculated as char1 multiplied by the element of the key matrix in the second row and first column, added to char2 multiplied by the element of the key matrix in the second row and second column, mod 26.
            int encChar2 = (char1 * keyMatrix[1][0] + char2 * keyMatrix[1][1]) % 26;

            ciphertextMatrix[0][index] = encChar1;
            ciphertextMatrix[1][index] = encChar2;
            index++;

            ciphertextText.append((char) (encChar1 + 'A'));
            ciphertextText.append((char) (encChar2 + 'A'));
        }

        // Display the ciphertext as a matrix
        System.out.println("Ciphertext Matrix:");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < ciphertextMatrix[0].length; j++) {
                System.out.print(ciphertextMatrix[i][j] + " ");
            }
            System.out.println();
        }

        // Display the ciphertext as text
        System.out.println("Ciphertext Text: " + ciphertextText.toString());

        scanner.close();
    }
}
