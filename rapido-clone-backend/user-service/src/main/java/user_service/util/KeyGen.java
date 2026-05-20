package user_service.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGen {
    public static void main(String[] args) {
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS512); // HS512 requires 64 bytes
        String base64Key = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println("JWT Secret Key: " + base64Key);
    }
}