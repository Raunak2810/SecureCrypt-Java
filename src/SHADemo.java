import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// SHA encryption nahi hai, ye "Hashing" hai
// Hashing ek "one-way" process hota hai - ek baar hash bana diya toh wapas original text nahi nikal sakte
// Isi wajah se passwords ko database me hash karke store karte hain (taaki original password kabhi na dikhe)
public class SHADemo {
    public static void main(String[]args)throws NoSuchAlgorithmException{
     String data = "HostelFix@2026";
        System.out.println(" SHA Hashing Demo ");
        System.out.println("Original Data: " + data);
        System.out.println("SHA-256 Hash: " + getHash(data, "SHA-256"));
        System.out.println("SHA-512 Hash: " + getHash(data, "SHA-512"));
        System.out.println("MD5 Hash (purana aur weak algorithm hai, sirf comparison ke liye): " + getHash(data, "MD5"));
        System.out.println("\nNote: Same input se hamesha SAME hash banega,");
        System.out.println("lekin hash se wapas original text nikalna practically possible nahi hai.");
        System.out.println("Isi wajah se MD5 ko ab weak/broken maana jaata hai, SHA-256/512 zyada secure hai.");
    }
    // Diye gaye algorithm (SHA-256, SHA-512, MD5) ke hisaab se hash generate karta hai
    public static String getHash(String data, String algorithm)throws NoSuchAlgorithmException{
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        byte[] hashBytes = digest.digest(data.getBytes());
        // Bytes ko readable hex string me convert kar rahe hain
        StringBuilder hexString = new StringBuilder();
        for (byte b : hashBytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
