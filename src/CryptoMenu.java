import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
// Ye file teeno algorithms (AES, RSA, SHA) ko ek jagah jodti hai
// Isse ek hi program run karke sabka demo dikha sakte ho - terminal pe interactive menu ke through
public class CryptoMenu{
    public static void main(String[]args)throws Exception{
        Scanner sc = new Scanner(System.in);
        int choice;
        do{
            System.out.println("\n SecureCrypt Toolkit ");
            System.out.println("1. AES Encryption/Decryption (Symmetric)");
            System.out.println("2. RSA Encryption/Decryption (Asymmetric)");
            System.out.println("3. SHA Hashing (256 / 512 / MD5)");
            System.out.println("4. Exit");
            System.out.print("Apna choice daalo (1-4): ");
            choice = sc.nextInt();
            sc.nextLine(); // buffer clear karne ke liye
            switch (choice){
                case 1:
                    System.out.print("Encrypt karne ke liye text daalo: ");
                    String aesText = sc.nextLine();
                    KeyGenerator keyGen = KeyGenerator.getInstance("AES");
                    keyGen.init(128);
                    SecretKey key = keyGen.generateKey();
                    String enc = AESDemo.encrypt(aesText, key);
                    System.out.println("Encrypted: " + enc);
                    System.out.println("Decrypted (wapas check karne ke liye): " + AESDemo.decrypt(enc, key));
                    break;
                case 2:
                    System.out.print("Encrypt karne ke liye text daalo: ");
                    String rsaText = sc.nextLine();
                    KeyPairGenerator rsaGen = KeyPairGenerator.getInstance("RSA");
                    rsaGen.initialize(2048);
                    KeyPair pair = rsaGen.generateKeyPair();
                    String rsaEnc = RSADemo.encrypt(rsaText, pair.getPublic());
                    System.out.println("Encrypted (Public Key se): " + rsaEnc);
                    System.out.println("Decrypted (Private Key se): " + RSADemo.decrypt(rsaEnc, pair.getPrivate()));
                    break;
                case 3:
                    System.out.print("Hash banane ke liye text daalo: ");
                    String hashText = sc.nextLine();
                    System.out.println("SHA-256: " + SHADemo.getHash(hashText,"SHA-256"));
                    System.out.println("SHA-512: " + SHADemo.getHash(hashText,"SHA-512"));
                    break;
                case 4:
                    System.out.println("Program band ho raha hai...!");
                    break;
                default:
                    System.out.println("Galat choice daala hai dobara try karo.");
            }
        } while(choice != 4);
        sc.close();
    }
}
