
package appdynamics.challenge.ui.home;

/**
 * Represents an Item in our application. Each item has a name, id, full size image url and
 * thumbnail url.
 */
public class Item {


    public static Item[] ITEMS = new Item[] {
            new Item("Normal Ticket", "Romain Guy", "normal"),
            new Item("Student Ticket", "Romain Guy", "student"),
            new Item("Onde Day Ticket", "Romain Guy", "oneday"),
            new Item("Three Days Ticket", "Romain Guy", "threeday"),
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

    Item(String name, String author, String fileName) {
        mName = name;
        mPrice = author;
        mURI = fileName;
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
