package com.aispace.erksystem.common;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;

/**
 * Created by Ai_Space
 */
public class PasswdUtil {
    private static final String DEFAULT_KEY = "AI_SPACE";
    private static final String DEFAULT_ALGORITHM = "PBEWITHMD5ANDDES";
    private static final StandardPBEStringEncryptor encryptor = buildEncryptor(DEFAULT_KEY, DEFAULT_ALGORITHM);

    private static StandardPBEStringEncryptor buildEncryptor(String key, String algorithm) {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setPassword(key);
        config.setAlgorithm(algorithm);
        encryptor.setConfig(config);
        return encryptor;
    }

    public static String decrypt(String encrypted) {
        return encryptor.decrypt(encrypted);
    }

    public static String encrypt(String decrypted) {
        return encryptor.encrypt(decrypted);
    }


    public static String decrypt(String encrypted, String key) {
        return decrypt(encrypted, key, DEFAULT_ALGORITHM);
    }

    public static String decrypt(String encrypted, String key, String algorithm) {
        return buildEncryptor(key, algorithm).decrypt(encrypted);
    }

    public static String encrypt(String decrypted, String key) {
        return encrypt(decrypted, key, DEFAULT_ALGORITHM);
    }

    public static String encrypt(String decrypted, String key, String algorithm) {
        return buildEncryptor(key, algorithm).encrypt(decrypted);
    }

}
