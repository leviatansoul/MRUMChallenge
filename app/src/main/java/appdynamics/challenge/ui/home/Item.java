
package appdynamics.challenge.ui.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
public class Item {


    public enum TICKET_TYPE{
        NORMAL,
        STUDENT,
        ONEDAY,
        THREEDAY
    }

    public static Item[] ITEMS = new Item[] {
            new Item(TICKET_TYPE.NORMAL, "Normal ticket" , "2.8", "/normal"),
            new Item(TICKET_TYPE.STUDENT, "Student ticket" ,  "1.2", "/reduced"),
            new Item(TICKET_TYPE.ONEDAY, "One day ticket" ,  "11", "/one-day"),
            new Item(TICKET_TYPE.THREEDAY, "Three day ticket" ,  "18", "/three-days"),
    };

    public static Item getItem(int id) {
        for (Item item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private final TICKET_TYPE mType;
    private final String mName;
    private final String mPrice;
    private final String mURI;

    Item(TICKET_TYPE type, String name, String price, String uri) {
        mType = type;
        mName = name;
        mPrice = price;
        mURI = uri;
    }

    public int getId() {
        return mType.hashCode() + mURI.hashCode();
    }

    public String getPrice() {
        return mPrice;
    }

    public TICKET_TYPE getType() {
        return mType;
    }

    public String getTicketUri() {
        return mURI;
    }

    public static String getUriFromType(TICKET_TYPE type){
        for(Item item : ITEMS){
            if (item.getType() == type){
                return item.getTicketUri();
            }
        }
        return null;
    }

    public String getName() {
        return mName;
    }



}
