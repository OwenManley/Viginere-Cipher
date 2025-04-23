/*Owen Manley, CSC 310, Project #4. This is a program that simulates a Vigenere cipher. It will receive an input file; then, either 
 * encrypt or decrypt the output file.
 * Couldn't get this done on time so I opted to turn it in as early as I can. Hopefully, I can still receive some credit. APologies for the 
 * inconvenience.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
//Used later for formatting the strings during decryption. It continued to not work and never decrypt; so, I opted to use this and it worked.
import java.io.PrintWriter;
//--------------------------------------------------------------------------------------------------------------------------------------------
public class VigenereCipher {
//--------------------------------------------------------------------------------------------------------------------------------------------
	//Used for encrypting the file. Pretty simple to read.
	private static String encryption(String str, String key) {
		
		  str = LowerToUpperCase(str);
		    String cipherText = "";

		    for (int i = 0; i < str.length(); i++) {
		        char c = str.charAt(i);
		        if (Character.isLetter(c)) {
		            int keyIndex = i % key.length();
		            int shift = key.charAt(keyIndex) - 'A';
		            char encryptedChar = (char) ((c + shift - 'A') % 26 + 'A');
		            cipherText += encryptedChar;
		        } else {
		            cipherText += c; // Append non-alphabetic characters unchanged
		        }
		    }
		    return cipherText;
    }//end encrypt
//--------------------------------------------------------------------------------------------------------------------------------------------
	//Used for decrypting inputted files. It is the same as encryption but some signs are changed. 
	private static String decryption(String cipherText, String key) {
		
		    /*if (key.isEmpty()) {
		        System.out.println("Decryption key is empty.");
		        return ""; // Return empty string if key is empty
		    }*/
			//Calling LowerToUpperCase here.
		    cipherText = LowerToUpperCase(cipherText);
		    String origText = "";

		    for (int i = 0; i < cipherText.length(); i++) {
		        char c = cipherText.charAt(i);
		        if (Character.isLetter(c)) {
		            int keyIndex = i % key.length();
		            int shift = key.charAt(keyIndex) - 'A';
		            char decryptedChar = (char) ((c - shift - 'A' + 26) % 26 + 'A');
		            origText += decryptedChar;
		        }//end if 
		        else {
		            origText += c; // Append non-alphabetic characters unchanged
		        }//end else
		    }//end for
		    return origText;
		}//end decryption
//--------------------------------------------------------------------------------------------------------------------------------------------
//This method simply converts every character to a capital letter once it is read.
	private static String LowerToUpperCase(String s) {
		return s.toUpperCase();
	}//end LowerToUpperCase
//--------------------------------------------------------------------------------------------------------------------------------------------
//Main method for running the program.
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Are you encrypting or decrypting? 1 for encrypt, 2 for decrypt.\n");
		int response = scanner.nextInt();
		scanner.nextLine();
		
		if (response == 1) {
			System.out.println("Enter input file name: ");
			String inputFileName = scanner.nextLine();
			System.out.println("Enter output file: ");
			String outputFileName = scanner.nextLine();
			
			System.out.println("Please enter encryption key: ");
			String key = scanner.next().toUpperCase();
			//Try-catch used in case user doesn't enter either '1' or '2'.
			try {
				
				File inputFile = new File(inputFileName);
				Scanner fileScanner = new Scanner(inputFile);
				//PrintWriter is used for decryption because it wouldn't work without it.
				PrintWriter outputFile = new PrintWriter(outputFileName);
				
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					//Performing encryption
					String encryptedLine = encryption(line, key);
	                outputFile.println(encryptedLine);
				}
	                

	                System.out.println("Encryption successful.");
	                fileScanner.close();
	                outputFile.close();
				
				} catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
            }
			
			} else if (response == 2) {
				 	System.out.println("Enter input file name:");
			        String inputFileName = scanner.nextLine();
			        System.out.println("Enter output file name:");
			        String outputFileName = scanner.nextLine();
			        //System.out.println("HELLO");
			        System.out.println("Please enter decryption key:");
			        String key = scanner.nextLine().toUpperCase();

			        try {
			        	//System.out.println("TEST");
			            File inputFile = new File(inputFileName);
			            Scanner fileScanner = new Scanner(inputFile);
			            PrintWriter outputFile = new PrintWriter(outputFileName);

			            while (fileScanner.hasNextLine()) {
			                String line = fileScanner.nextLine();
			                //Performing decryption.
			                String decryptedLine = decryption(line, key);
			                outputFile.println(decryptedLine);
			            }

			            System.out.println("Decryption successful.");
			            fileScanner.close();
			            outputFile.close();
			        } catch (FileNotFoundException e) {
			            System.out.println("File not found: " + e.getMessage());
			        }
			} else {
		        System.out.println("Invalid option. Please enter 1 for encryption or 2 for decryption.");
		    }
		    scanner.close();
	}//end main

}//end VigenereCipher class
