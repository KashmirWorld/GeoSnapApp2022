package org.kashmirworldfoundation.WildlifeGeoSnap.auth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthHandler {

    public static boolean validateEmail(String email) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
