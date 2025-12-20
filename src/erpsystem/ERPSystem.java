package erpsystem;

import java.time.LocalDate;
import java.util.*;

public class ERPSystem {
    public static void main(String[] args) throws InterruptedException {
        /*
        System.out.println("=== [System Initialization] ===");

        // 1. Setup Raw Materials (Item: id, code, name, price, quantity, minLimit)
        Item iron = new Item(1, "FE", "Iron", 20, 15, 5); 
        Item alu = new Item(2, "AL", "Aluminum", 10, 50, 5);
        
        // 2. Define Product Recipe (1 Car requires 5 units of Iron)
        Map<Item, Integer> carRecipe = new HashMap<>();
        carRecipe.put(iron, 5); 
        
        Product car = new Product(1, "Sports-Car", carRecipe);
        
        // 3. Initialize Inventory and Production Line
        List<Item> initialItems = new ArrayList<>(Arrays.asList(iron, alu));
        Inventory inventory = new Inventory(initialItems, List.of(car));
        ProductLine line1 = new ProductLine(1, "Alpha-Line", inventory);

        // ---------------------------------------------------------
        // CASE 1: Successful Mission (Standard Testing)
        // Description: Produces 2 units. Requires 10 Iron (15 available).
        // ---------------------------------------------------------
        Mission m1 = new Mission(101, car, 2, "Safe-Client", 
        LocalDate.now(), LocalDate.now().plusDays(2), line1);
        line1.addMission(m1);

        // ---------------------------------------------------------
        // CASE 2: Invalid Data (Testing Custom Exceptions)
        // Description: Attempting to set negative quantity.
        // ---------------------------------------------------------
        try {
            System.out.println("\n[Test] Attempting to add mission with negative quantity...");
            Mission m2 = new Mission(102, car, -5, "Error-Client", LocalDate.now(), LocalDate.now(), line1);
            line1.addMission(m2);
        } catch (Exception e) {
            System.out.println("✅ Validation Success: " + e.getMessage());
        }

        // ---------------------------------------------------------
        // CASE 3: Low Stock Scenario (Testing Logic/Wait)
        // Description: Requires 50 Iron (only 5 left after M1). Should trigger LowStock.
        // ---------------------------------------------------------
        Mission m3 = new Mission(103, car, 10, "Bulk-Client", 
        LocalDate.now(), LocalDate.now().plusDays(5), line1);
        line1.addMission(m3);

        // ---------------------------------------------------------
        // START PRODUCTION THREAD
        // ---------------------------------------------------------
        Thread productionThread = new Thread(line1);
        line1.setLineState(LineState.ACTIVE); // Ensuring the line is ACTIVE
        productionThread.start();

        System.out.println("\n=== [Monitoring Started - 15 Seconds Loop] ===");
        
        for (int i = 1; i <= 15; i++) {
            Thread.sleep(1000); // Check status every second
            
            System.out.println("\n--- Second: " + i + " ---");
            System.out.println("M1 (Valid Order): " + m1.getAccomplishLevel() + "% | State: " + m1.getState());
            System.out.println("M3 (Big Order):  " + m3.getAccomplishLevel() + "% | State: " + m3.getState());
            System.out.println("Remaining Iron in Stock: " + iron.getQuantity());

            // Print thread errors if any (from the lastErrorMessage variable)
            if (line1.getLastErrorMessage() != null && !line1.getLastErrorMessage().isEmpty()) {
                System.out.println("⚠️ NOTIFICATION: " + line1.getLastErrorMessage());
            }

            // Exit early if all missions are handled
            if (m3.getState() == State.COMPLETED || m3.getState() == State.CANCELED) {
                System.out.println("\n[Test] All missions processed.");
                break;
            }
        }

        line1.stopThread();
        System.out.println("\n=== [Test Environment Finished] ===");
        */
        
        
        
        
        
        
        
        System.out.println("--- [1] Starting System Initialization ---");

        try {
            // 1. إعداد المواد الخام
            Item i1 = new Item(1, "FE", "Iron", 20, 100, 10);
            Item i2 = new Item(2, "AL", "Aluminum", 10, 50, 5);
            List<Item> itemsList = new ArrayList<>(Arrays.asList(i1, i2));

            // 2. إعداد المنتج
            Map<Item, Integer> recipe = new HashMap<>();
            recipe.put(i1, 2); // يحتاج 2 حديد
            recipe.put(i2, 1); // يحتاج 1 ألمنيوم
            Product p1 = new Product(1, "Engine", recipe);
            List<Product> productsList = new ArrayList<>(Collections.singletonList(p1));

            // 3. إنشاء المخزون
            Inventory inventory = new Inventory(itemsList, productsList);
            System.out.println("--- [2] Inventory Ready. Iron Stock: " + i1.getQuantity() + " ---");

            // 4. إنشاء خط الإنتاج
            // ملاحظة: تأكد أن الـ Constructor يقبل (ID, Name, Inventory)
            ProductLine line1 = new ProductLine(1, "Alpha-Line", inventory);
            line1.setLineState(LineState.ACTIVE);
            // 5. إنشاء المهمة
            // نطلب إنتاج 5 قطع
            //اي فرشة رح تبين من هون
            Mission m1 = new Mission(101, p1, 5, "Customer_A", 
            LocalDate.now(), LocalDate.now().plusDays(2), line1);
            
            // 6. إضافة المهمة للخط قبل تشغيله
            line1.addMission(m1);
            System.out.println("--- [3] Mission Added to Queue. Ordered: " + m1.getOrderedQuantity() + " ---");

            // 7. تشغيل الخيط
            System.out.println("--- [4] Launching Thread... ---");
            Thread productionThread = new Thread(line1);
            productionThread.start();

            // 8. حلقة المراقبة (Monitor)
            System.out.println("--- [5] Monitoring Progress (15 seconds) ---");
            for (int i = 1; i <= 15; i++) {
                Thread.sleep(1000); // انتظر ثانية
                
                System.out.print("\rTime: " + i + "s | Done: " + m1.getDoneProducts() 
                                 + " | Progress: " + m1.getAccomplishLevel() + "%"
                                 + " | State: " + m1.getState() );
                

                // تفقد إذا حدث خطأ في الخيط
                String error = line1.getLastErrorMessage();
                if (error != null && !error.isEmpty()) {
                    System.out.println("\n\n❌ THREAD ERROR DETECTED: " + error);
                    break;
                }
            }

            // إغلاق الخيط
            line1.stopThread();
            System.out.println("\n--- [6] Test Ended ---" + m1.getState());

        } catch (Exception e) {
            System.out.println("\n❌ CRITICAL SYSTEM ERROR: ");
            e.printStackTrace();
        }
    }
}