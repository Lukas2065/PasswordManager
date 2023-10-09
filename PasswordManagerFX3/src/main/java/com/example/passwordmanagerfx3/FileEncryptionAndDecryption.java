//package com.example.passwordmanagerfx3;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.NoSuchPaddingException;
//import javax.crypto.SecretKey;
//import java.security.NoSuchAlgorithmException;
//import java.util.Base64;
//
//public class FileEncryptionAndDecryption {
//    private SecretKey key;
//    private int KEY_SIZE = 256;
//    public void init() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(KEY_SIZE);
//        key = keyGenerator.generateKey();
//    }
//    public void encryptFiles() throws Exception {
//        FileManager fileManager = new FileManager();
//        fileManager.initialiseCurrentUser();
//        String websites = fileManager.convertWebsitesToString();
//        String passwords = fileManager.convertPasswordsToString();
//
//        byte[] websitesInBytes = websites.getBytes();
//        Cipher encryptionCipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
//        encryptionCipher.init(Cipher.ENCRYPT_MODE,key);
//        byte[] encryptedWebsites = encryptionCipher.doFinal(websitesInBytes);
//
//    }
//
//    public String decryptFiles() throws Exception {}
//
//    private String encode(byte[] data) {
//        return Base64.getEncoder().encodeToString(data);
//    }
//
//    private byte[] decode(String data) {
//        return Base64.getDecoder().decode(data);
//    }
//}
