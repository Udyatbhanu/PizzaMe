package com.home.ubbs.pizzame.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by udyatbhanu-mac on 6/21/17.
 */

public class Utils {
    private Utils(){
    }


    /**
     *
     * @param array
     * @return
     */
    public static String getRandomUrl(String[] array){
        Random generator = new Random();
        int randomIndex = generator.nextInt(array.length);
            return array[randomIndex];

    }


    public static String getWebsite(String website){

        if(website != null){
            Pattern urlPattern = Pattern.compile(
                    "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                            + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                            + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
                    Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

            Matcher matcher = urlPattern.matcher(website);

            while (matcher.find()) {
                int matchStart = matcher.start(1);
                int matchEnd = matcher.end();

                return  website.substring(matchStart, matchEnd);
                // now you have the offsets of a URL match
            }
        }

        return "";
    }



}
