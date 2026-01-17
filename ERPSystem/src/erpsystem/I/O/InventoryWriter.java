package erpsystem.I.O;

import erpsystem.Item;
import erpsystem.Product;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class InventoryWriter {

    private static final String pathName = "inventory_out.txt";

    public static void writeInventory(Collection<Item> items) {
        try (FileWriter fr = new FileWriter(pathName, true)) {
            for (Item item : items) {
                fr.write(
                        item.getItemId() + ","
                        + item.getItemName() + ","
                        + item.getType() + ","
                        + item.getPrice() + ","
                        + item.getQuantity() + ","
                        + item.getMinLimit() + "\n"
                );
            }
        } catch (IOException e) {
            ErrorLogger.logError(e);
        }

    }

}
