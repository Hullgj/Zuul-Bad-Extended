package resource_bundle;

/**
 * The languages bundle for the messages bundle
 * 
 * @author  Gavin Hull
 * @version 2016.09.11
 */

public class Languages extends Resources {
    
    public Languages() {
        super.setLanguage(super.getLangLocale(), "MessagesBundle");
    }
}