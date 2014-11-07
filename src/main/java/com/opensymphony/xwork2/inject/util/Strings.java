package com.opensymphony.xwork2.inject.util;

import java.io.Serializable;

/**
 * String utilities.
 * 
 * @author crazybob@google.com (Bob Lee)
 */
public class Strings implements Serializable {

   /**
    * Returns a string that is equivalent to the specified string with its
    * first character converted to uppercase as by {@link String#toUpperCase}.
    * The returned string will have the same value as the specified string if
    * its first character is non-alphabetic, if its first character is already
    * uppercase, or if the specified string is of length 0.
    *
    * <p>For example:
    * <pre>
    *    capitalize("foo bar").equals("Foo bar");
    *    capitalize("2b or not 2b").equals("2b or not 2b")
    *    capitalize("Foo bar").equals("Foo bar");
    *    capitalize("").equals("");
    * </pre>
    *
    * @param s the string whose first character is to be uppercased
    * @return a string equivalent to <tt>s</tt> with its first character
    *     converted to uppercase
    * @throws NullPointerException if <tt>s</tt> is null
    */
   public static String capitalize(String s) {
      if (s.length() == 0)
         return s;
      char first = s.charAt(0);
      char capitalized = Character.toUpperCase(first);
      return (first == capitalized) ? s : capitalized + s.substring(1);
   }
}
