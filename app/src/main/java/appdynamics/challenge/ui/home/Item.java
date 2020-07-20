
package appdynamics.challenge.ui.home;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
public class Item {



    public static Item[] ITEMS = new Item[] {
            new Item("Normal Ticket", "2.8", "normal"),
            new Item("Student Ticket", "1.2", "student"),
            new Item("Onde Day Ticket", "11", "oneday"),
            new Item("Three Days Ticket", "18", "threedays"),
    };

    public static Item getItem(int id) {
        for (Item item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private final String mName;
    private final String mPrice;
    private final String mURI;

    Item(String name, String price, String uri) {
        mName = name;
        mPrice = price;
        mURI = uri;
    }

    public int getId() {
        return mName.hashCode() + mURI.hashCode();
    }

    public String getPrice() {
        return mPrice;
    }

    public String getName() {
        return mName;
    }

    public String getTicketUri() {
        return mURI;
    }



}
