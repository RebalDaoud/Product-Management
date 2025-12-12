package erpsystem;

public class Item {
    
    private int itemId;
    private String itemName;
    private String type;
    private double price;
    private int quantity;
    private int minLimit;

    public Item(int itemId, String itemName, String type, double price, int quantity, int minLimit) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.minLimit = minLimit;
    this.price = price;
    this.type = type;
    this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getMinLimit() {
        return minLimit;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0){
            quantity = 0;
        }   
        this.quantity = quantity;
    }
    
    public void addQuantity(int amount) throws AllException.InvalidQuantityException{
        if (amount > 0){
            quantity += amount;
        }
        else {
            throw new AllException.InvalidQuantityException("Can't add negative Quantity, Please add again");
        }
    }
    
    public boolean QuantityConsumption(int amount) throws AllException.InvalidQuantityException,
            AllException.LowStockException{
        if (amount <= 0){
            throw new AllException.InvalidQuantityException("Amount should be greater than zero");
        }
        if (quantity < amount){
            throw new AllException.LowStockException("The quantity is low, you can't consumption the quantity now, please wait...");
        }
        this.quantity -= amount;   
        return true;
    }
    
    public boolean CheckLowStock(){
        return quantity <= minLimit;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", minLimit=" + minLimit +
                '}';
    }
    
}
