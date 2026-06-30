import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

// AES ek "symmetric" encryption algorithm hai
// Matlab encrypt aur decrypt dono ke liye SAME key use hoti hai
// Real life example: file encryption, WhatsApp jaise apps tez encryption ke liye isi tarah ka tarika use karte hain
public class AESDemo{
    public static void main(String[]args)throws Exception{
        String originalText = "HostelFix Project - Confidential Data";
        // Step 1: Sabse pehle ek random AES key generate karte hain
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // 128-bit key -> standard secure size
        SecretKey secretKey = keyGen.generateKey();
        System.out.println("AES Encryption Demo ");
        System.out.println("Original Text: " + originalText);
        System.out.println("Generated AES Key (Base64 me dikhayi de rahi hai): "
                + Base64.getEncoder().encodeToString(secretKey.getEncoded()));
        // Step 2: Text ko encrypt karo
        String encryptedText = encrypt(originalText, secretKey);
        System.out.println("Encrypted Text: " + encryptedText);
        // Step 3: Wapas usi key se decrypt karo
        String decryptedText = decrypt(encryptedText, secretKey);
        System.out.println("Decrypted Text (wapas original mil gaya): " + decryptedText);
        System.out.println("\nNote: Yahan encrypt aur decrypt dono ke liye same key use hui hai,");
        System.out.println("isi wajah se AES ko 'Symmetric Encryption' kehte hain.");
    }
    // Text ko encrypt karne wala function
    public static String encrypt(String data, SecretKey key)throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    // Encrypted text ko wapas original me decrypt karne wala function
    public static String decrypt(String encryptedData, SecretKey key)throws Exception{
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
// Itne comments ham likhe hain taki easyly samjh aae project...