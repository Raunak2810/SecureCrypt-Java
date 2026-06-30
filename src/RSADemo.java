import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
// RSA ek "asymmetric" encryption algorithm hai
// Yahan DO keys hoti hain - Public Key (sabko de sakte ho) aur Private Key (sirf apne paas rakhni hai)
// Public key se encrypt hota hai, sirf Private key se hi decrypt ho payega
// Real life example: HTTPS websites, digital signatures isi tarah secure connection banate hain
public class RSADemo{
    public static void main(String[]args)throws Exception{
        String originalText = "Secret message from me(Raunak)";
        // Step 1: Public-Private key pair generate karte hain
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048); // 2048-bit -> industry standard secure size
        KeyPair keyPair = keyGen.generateKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();
        System.out.println(" RSA Encryption Demo ");
        System.out.println("Original Text: " + originalText);
        // Step 2: Public key se encrypt karo (ye key kisi ko bhi de sakte ho)
        String encryptedText = encrypt(originalText, publicKey);
        System.out.println("Encrypted Text (Public Key se encrypt hua): " + encryptedText);
        // Step 3: Sirf Private key se hi ye decrypt hoga
        String decryptedText = decrypt(encryptedText, privateKey);
        System.out.println("Decrypted Text (Private Key se decrypt kiya): " + decryptedText);
        System.out.println("\nNote: Yahan encrypt aur decrypt ke liye alag-alag keys use hui hain,");
        System.out.println("isi wajah se RSA ko 'Asymmetric Encryption' kehte hain.");
    }
    // Public key se data encrypt karne wala function
    public static String encrypt(String data, PublicKey publicKey)throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedBytes = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
    // Private key se data decrypt karne wala function
    public static String decrypt(String encryptedData, PrivateKey privateKey)throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);
        return new String(decryptedBytes);
    }
}
