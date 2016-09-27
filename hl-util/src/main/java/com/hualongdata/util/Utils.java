package com.hualongdata.util;

import com.hualongdata.util.scalaapi.Utils$;
import scala.Tuple2;

/**
 * Created by yangbajing on 16-8-31.
 */
public class Utils {

    public static String randomString(int n) {
        return Utils$.MODULE$.randomString(n);
    }

    public static Tuple2<String, String> generateSaltAndPassword(String password) {
        return Utils$.MODULE$.generateSaltAndPassword(password);
    }

    public static String saltSecretPassword(String salt, String password) {
        return Utils$.MODULE$.saltSecretPassword(salt, password);
    }

    public static boolean matchPassword(String salt, String encryptedPassword, String originalPassword) {
        return Utils$.MODULE$.matchPassword(salt, encryptedPassword, originalPassword);
    }
}
