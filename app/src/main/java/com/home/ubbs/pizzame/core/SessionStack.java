package com.home.ubbs.pizzame.core;

import java.util.HashMap;

/**
 * Created by udyatbhanu-mac on 6/20/17.
 *
 * Singleton object which maintains the stack across the app, it is very important to keep track of this
 * object so we don't see any unexpected result.
 */

public class SessionStack {


    private static HashMap<String, Object> stack = new HashMap<>();

    private SessionStack(){

    }

    /**
     *
     * @param key
     * @param o
     */
    public static void put(String key, Object o){
        if(stack == null){
            stack = new HashMap<>();
        }
        stack.put(key,o);
    }

    /**
     *
     * @param key
     * @return
     */
    public static Object get(String key){
        if(stack != null){
            return stack.get(key);
        }
        return null;

    }

    /**
     *
     * @param key
     * @return
     */
    public static String getString(String key){
        if(stack != null){
            return (String)stack.get(key);
        }
        return null;

    }

    /**
     *
     */
    public static void clearStack(){
        if(stack != null){
            stack.clear();
        }
    }

    /**
     *
     * @param key
     * @return
     */
    public static void remove(String key){
        if(stack != null){
             stack.remove(key);
        }

    }
}
