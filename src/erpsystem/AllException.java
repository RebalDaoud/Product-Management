package erpsystem;

public class AllException {
    
    // (addQuantity + QuantityConsumption) Exception in Item class
    public static class InvalidQuantityException extends Exception {
        public InvalidQuantityException(String Alert){
            super (Alert);
        }        
    }
    
    // (QuantityConsumption) Exception in Item class
     public static class LowStockException extends Exception{
        public LowStockException(String Alert){
            super (Alert);
        }
     }
     
    //(removeMission) Exception in ProductLine class
     public static class NoMissionException extends Exception{
         public NoMissionException(String Alert){
            super (Alert);
         }
     }
    
}
