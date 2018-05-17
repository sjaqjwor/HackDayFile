package hackday.webtoon.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

public class ImageHash {


    private final String algo = "PBKDF2WithHmacSHA1";

    private final int SALT_BYTES=24;
    private final int HASH_BYTES=24;
    private final int ITERATION = 10;

    public  String createHash(String password)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_BYTES];
        random.nextBytes(salt);
        byte[] hash = pbkdf2(password.toCharArray(), salt, ITERATION, HASH_BYTES);
        return toHex(hash);
    }

    private byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes)
            throws NoSuchAlgorithmException, InvalidKeySpecException
    {
        PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, bytes * 5);
        SecretKeyFactory skf = SecretKeyFactory.getInstance(algo);
        return skf.generateSecret(spec).getEncoded();
    }

    private static String toHex(byte[] array)
    {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
            return String.format("%0" + paddingLength + "d", 0) + hex;
        else
            return hex;
    }

}
