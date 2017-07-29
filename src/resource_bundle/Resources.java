package resource_bundle;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.NoSuchElementException;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

/**
 * The parent class for all language classes. Here the language and locale 
 * for the game is set.
 * 
 * Methods for accessing key value pair elements include getting and checking
 * the value or key.
 * 
 * For each bundle, currently messages and commands, there is a properties file
 * with associated language and locale denoted: 
 *      BundleName_language_locale.properties
 * 
 * The bundle is set using a subclass, currently Languages and CommandWords
 * 
 * Based on: https://docs.oracle.com/javase/tutorial/i18n/resbundle/propfile.html
 * (Oracle Tutorials Resource Bundle, 2016)
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */
 public class Resources {
    
    private Locale currentLocale;
    private ResourceBundle theBundle;
    private static String[] language_locale;
    
    public Resources() {
    }
    
    /**
     * Set the language, if no args then GB English is default, using a bundle
     * 
     * @source (Oracle Tutorials Resource Bundle, 2016)
     * 
     * @param args two arguments taking the form new String[] {language, country}
     * @param bundle the message bundle, i.e. useThisBundle needs useThisBundle.properties
     */
    public void setLanguage(String[] args, String bundle) {

        String language;
        String country;

        if (args.length != 2) {
            language = "en";
            country = "GB";
        } else {
            language = args[0];
            country = args[1];
        }

        currentLocale = new Locale(language, country);
        setBundle(bundle);
    }
    
    /**
     * Get the language and locale set
     * @param language_locale the main language and locale for the game
     * @language_locale the static language, locale of the class
     */
    public static void setLangLocale(String[] language_locale) {
        Resources.language_locale = language_locale;
    }
    
    /**
     * Get the language and locale set
     * @return the static language, locale of the class
     */
    public String[] getLangLocale() {
        return language_locale;
    }
    
    /**
     * Set the bundle to be used and store the value in Resources._lang
     * @param bundle the chosen bundle
     */
    public void setBundle(String bundle) {
        theBundle = ResourceBundle.getBundle(bundle, currentLocale);
    }
    
    /**
     * Get all values in the bundle
     * 
     * @source (Oracle Tutorials Resource Bundle, 2016)
     * 
     * @return a list of values from the resource bundle
     */
    public List<String> getValues() {
        Enumeration bundleKeys = theBundle.getKeys();
        List<String> values = new ArrayList<>();
        
        while (bundleKeys.hasMoreElements()) {
            String key = (String)bundleKeys.nextElement();
            values.add(theBundle.getString(key));
        }
        
        return values;
    }
    
    /**
     * Make the list from Resources.getValues() into a string 
     * and remove '[', ']' characters from the returned string
     * @return a list of strings processed into a legible string
     */
    public String showValues() {
        return getValues().toString().replaceAll("\\[|\\]", "");
    }
    
    /**
     * Using the current bundle, get the value from the key pair
     * 
     * @param key the key of the bundle
     * @return a string of the value linked to the key
     */
    public String getValue(String key) {
        try {
            return theBundle.getString(key);
        } catch (MissingResourceException e) {
            // key value pair not found, return the original key
            return key;
        }
    }
    
    /**
     * Check if parameter is a key and return the key
     * @param key the key to see if is a key
     * @return the key, after converting to a value then a key
     */
    public String checkKey(String key) {
        return getKey(getValue(key));
    }
    
    /**
     * Check if parameter is a key
     * @param key the key to see if is a key
     * @return true if is a key
     */
    public boolean isKey(String key) {
        return !getValue(key).equals(key);
    }
    
    /**
     * Using the current bundle, get the key from a value pair
     * @param value the string of the value to get a key
     * @return the key as a string
     */
    public String getKey(String value) {
        Enumeration bundleKeys = theBundle.getKeys();
        String key = "";
        
        while (bundleKeys.hasMoreElements()) {
            key = "" + bundleKeys.nextElement();

            if(getValue(key).equals(value))
                return key;
        }
        return null;
    }
    
    /**
     * Using getKey build an array list of strings of keys from the user's input
     * @param user_input the user's input
     * @return keys as an array list of strings
     */
    public List<String> getKeys(List<String> user_input) {
        List<String> user_commands = user_input.stream()
                    .map(v -> getKey(v))
                    .collect(Collectors.toCollection(ArrayList::new));
            
            if(user_commands.isEmpty())
                throw new NoSuchElementException();
            
        return user_commands;
    }
}