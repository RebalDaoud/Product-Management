package erpsystem;

import java.time.LocalDate;

public class Mission {
    
    private int taskId;
    private Product aquiredProduct;
    private int orderedQuantity;
    private String client;
    private LocalDate startingDate;
    private LocalDate  deliveryDate;
    private State state;
    private ProductLine productLine;
    private int accomplishLevel;
    
    public Mission(int taskId, Product aquiredProduct, int orderedQuantity, String client, LocalDate startingDate,
            LocalDate deliveryDate, ProductLine productLine){
        this.aquiredProduct = aquiredProduct;
        this.taskId = taskId;
        this.orderedQuantity = orderedQuantity;
        this.client = client;
        this.startingDate = startingDate;
        this.deliveryDate = deliveryDate;
        this.productLine = productLine;
        this.state = State.COMPLETED;
        this.accomplishLevel = 0;
    }

    public int getTaskId() {
        return taskId;
    }

    public Product getAquiredProduct() {
        return aquiredProduct;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public int getAccomplishLevel() {
        return accomplishLevel;
    }

    public void setAccomplishLevel(int accomplishLevel) {
        if (accomplishLevel < 0){
            accomplishLevel = 0;
        }
        if (accomplishLevel > 100){
            accomplishLevel = 100;
        }
        this.accomplishLevel = accomplishLevel;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        
        if (state == State.IN_PROGRESS){
            startingDate = LocalDate.now();     
        }
        if (state == State.COMPLETED){
            deliveryDate = LocalDate.now(); 
        }
    }

    @Override
    public String toString() {
        return "Mission{" + "taskId=" + taskId + 
                ", aquiredProduct=" + aquiredProduct.getProductName() + 
                ", orderedQuantity=" + orderedQuantity + 
                ", client=" + client + 
                ", state=" + state + 
                ", accomplishLevel=" + accomplishLevel + 
                '}';
    }
    
    
    
    
}
