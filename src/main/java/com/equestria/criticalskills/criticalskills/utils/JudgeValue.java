package com.equestria.criticalskills.criticalskills.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JudgeValue {
    public static boolean judgeAge(Integer age){
        if(age<0 || age>120)return false;
        return true;
    }
    public static boolean judgeGender(Integer gender){
        if(gender != 0 && gender != 1)return false;
        return true;
    }
    public static boolean judgeEmail(String email){
        Pattern pattern = Pattern.compile("[.]{1,50}@[.]{1,50}");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
