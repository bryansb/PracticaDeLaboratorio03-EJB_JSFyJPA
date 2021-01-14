package ec.edu.ups.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MathFunction {
	public static String getMd5(String input) { 
        try { 
  
            // Static getInstance method is called with hashing MD5 
            MessageDigest md = MessageDigest.getInstance("MD5"); 
  
            // digest() method is called to calculate message digest 
            //  of an input digest() return array of byte 
            byte[] messageDigest = md.digest(input.getBytes()); 
  
            // Convert byte array into signum representation 
            BigInteger no = new BigInteger(1, messageDigest); 
  
            // Convert message digest into hex value 
            String hashtext = no.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            } 
            return hashtext; 
        }  catch (NoSuchAlgorithmException e) { 
            throw new RuntimeException(e); 
        } 
	}
	
	
	private static boolean stringLength(String string, int length) {
        if (string.length() == length)
            return true;
        return false;
    }

    private static byte sumDigits(byte[] digits) {
        byte verifier;
        byte sum = 0;
        for (byte i = 0; i < digits.length; i = (byte) (i + 2)) {
            verifier = (byte) (digits[i] * 2);
            if (verifier > 9)
                verifier = (byte) (verifier - 9);
            sum = (byte) (sum + verifier);
        }
        for (byte i = 1; i < digits.length; i = (byte) (i + 2)) {
            verifier = (byte) (digits[i] * 1);
            sum = (byte) (sum + verifier);
        }
        return (byte) ((sum - (sum % 10) + 10) - sum);
    }

    public static boolean validateDni(String idCard) {
        try {
            if (stringLength(idCard, 10)) {
                String[] data = idCard.split("");
                byte verifier = Byte.parseByte(data[0] + data[1]);
                byte[] digits = new byte[9];
                for (byte i = 0; i < 9; i++)
                    digits[i] = Byte.parseByte(data[i]);        
                if (verifier >= 1 && verifier <= 24) {
                    verifier = digits[2];
                    if (verifier <= 6) {
                        if (sumDigits(digits) == Byte.parseByte(data[9]))
                            return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}