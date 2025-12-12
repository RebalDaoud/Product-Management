package erpsystem;

import java.util.Map;

public class Product {
    
    private int productId;
    private String productName;
    private Map<Item,Integer> items;
    
    public Product(int productId,String productName, Map<Item,Integer> items){
        this.productName = productName;
        this.productId = productId;
        this.items = items;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Map<Item, Integer> getItems() {
        return items;
    }
    // It's return the amount of itme and if there is no amount return 0
    public int GetAmount(Item item){
        return items.getOrDefault(item, 0);
    }
    
    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", items=" + items +
                '}';
    }
}
