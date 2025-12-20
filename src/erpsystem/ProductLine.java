package erpsystem;

import java.util.LinkedList;
import java.util.Queue;

public class ProductLine implements Runnable{
    
    private int lineId;
    private String lineName;
    private LineState lineState;
    private Queue<Mission> missions;
    private Inventory inventory;
    private boolean isRunning = true;
    private String lastErrorMessage = "";
    
    public ProductLine(int lineId, String lineName, Inventory inventory){
        this.lineId = lineId;
        this.lineName = lineName;
        this.missions = new LinkedList<>();
        this.inventory = inventory;
    }
    
    @Override
    public void run() {
        while (isRunning) {
            try {
            if(!missions.isEmpty() && lineState == LineState.ACTIVE){
                Mission currentMission = missions.peek();
                System.out.println("Line " + lineName + " started working on Mission: " + currentMission.getMissionId());
                
                try {
                    //Requesting materials from the factory
                    inventory.itemConsumption(currentMission.getAquiredProduct(), currentMission.getOrderedQuantity());
                    //Production Process Simulation
                    for (int i = 0; i < currentMission.getOrderedQuantity(); i++) {
                            if (currentMission.getState() == State.CANCELED) 
                                break;
                            Thread.sleep(1000);
                            //Update the output piece by piece (in Mission class)
                            currentMission.updateProgress(1); 
                        }
                    inventory.addProduct(currentMission.getAquiredProduct(),currentMission.getOrderedQuantity());
                    currentMission.setState(State.COMPLETED);
                    missions.poll();
                    System.out.println("Mission: " + currentMission.getMissionId() + " COMPLETED by: " + lineName);
                    
                }
                
                catch (AllException.LowStockException ex) {
                        this.lastErrorMessage = "Low Stock for Mission " + currentMission.getMissionId() + ": " + ex.getMessage();
                        currentMission.setState(State.IN_PROGRESS);
                        Thread.sleep(5000);
                }
                catch (AllException.InvalidNumberException ex) {
                    this.lastErrorMessage = "ORDERED QUANTITY CANNOT BE NEGATIVE!" + ex.getMessage();
                    if (!missions.isEmpty()) {
                    Mission m = missions.peek();
                    m.setState(State.CANCELED); 
                    missions.poll();
                    }
                } 
                catch (AllException.InvalidQuantityException ex) {
                    this.lastErrorMessage = "Error in quantity!" + ex.getMessage();
                    if (!missions.isEmpty()) {
                    Mission m = missions.peek();
                    m.setState(State.CANCELED);
                    missions.poll();
                   // ErrorLogger.log(ex);
                    }
                } 
                catch (InterruptedException ex) {
                    System.out.println("Line " + lineName + " was interrupted.");
                    break; 
                }
            }
            //If there is no missions
            else 
                Thread.sleep(2000);
            
        }   
        catch (InterruptedException ex) {
            System.out.println("Line " + lineName + " was interrupted.");  
            break;
            }
        }
    }
    
    //Return last massage 
    public String getLastErrorMessage() {
        String msg = lastErrorMessage;
        lastErrorMessage = ""; 
        return msg;
    }
    
    //Adds an element to the queue.
    public void addMission(Mission mission){
        missions.offer(mission);
    }
    
    public void setLineState(LineState lineState) {
        this.lineState = lineState;
    }
    
    public LineState getLineState() {
        return lineState;
    }
    
    public void stopThread() {
        this.isRunning = false;
    }
    
     @Override
    public String toString() {
        return "ProductLine{" +
                "lineId=" + lineId +
                ", lineName='" + lineName + '\'' +
                ", lineState=" + lineState +
                ", tasks=" + missions+
                '}';
    }
}   