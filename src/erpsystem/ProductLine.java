package erpsystem;

import java.util.LinkedList;
import java.util.Queue;

public class ProductLine {
    
    private int lineId;
    private String lineName;
    private LineState lineState;
    private Queue<Mission> missions;
    
    public ProductLine(int lineId, String lineName, LineState lineState, Queue<Mission> missions){
        this.lineId=lineId;
        this.lineName=lineName;
        this.lineState=lineState.ACTIVE;
        this.missions=new LinkedList<>(missions);
    }
    //Adds an element to the queue.
    public void addMission(Mission mission){
        missions.offer(mission);
    }
    //Retrieves and removes the element at the front (head) of the queue.
    public void removeMission() throws AllException.NoMissionException{
        missions.poll();
        throw new AllException.NoMissionException("There is no Mission yet");
    }
    //Returns the element at the front (head) of the queue without removing it
    public void getMission() throws AllException.NoMissionException {
        missions.peek();
        throw new AllException.NoMissionException("There is no Mission yet");
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
