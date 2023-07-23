package com.ganesh.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String host, String token){
       return "Hello "+ name + ",\n\n Your new account has been created. Please click the link below to verify your account. \n\n" +
               getVerificationUrl(host, token) + "\n\nThe support Team.";
    }

    public static String getVerificationUrl(String host, String token) {
     return host + "/v1/api/users?token=" + token;
    }
}
