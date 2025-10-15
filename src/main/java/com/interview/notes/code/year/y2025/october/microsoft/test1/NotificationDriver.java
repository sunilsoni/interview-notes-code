//package com.interview.notes.code.year.y2025.october.microsoft.test1;
//
//class NotificationDriver {
//
//    // Configuration value - rollout percentage for modern delivery
//    private static final int MODERN_PERCENT = 30; // can come from config store
//
//    public static void processEvent(String changeType, AzureResource resource) {
//
//        // 1️⃣ Get all subscribers for this change type
//        Subscriber[] subscribers = NotificationProvider.getSubscribers(changeType);
//
//        // 2️⃣ Loop through each subscriber
//        for (Subscriber sub : subscribers) {
//
//            // Build a stable key to ensure deterministic routing
//            String key = sub.endpoint + "|" + resource.uniqueId();
//
//            // Compute a stable hash bucket (0–99)
//            int bucket = Math.abs(key.hashCode() % 100);
//
//            // 3️⃣ Flighting decision
//            boolean useModern = bucket < MODERN_PERCENT;
//
//            // 4️⃣ Deliver notification
//            if (useModern) {
//                NotificationProvider.DeliverNotificationModern(changeType, resource, sub);
//                System.out.println("[LOG] Modern delivery -> " + sub.endpoint);
//            } else {
//                NotificationProvider.DeliverNotificationLegacy(changeType, resource, sub);
//                System.out.println("[LOG] Legacy delivery -> " + sub.endpoint);
//            }
//        }
//    }
//
//    // 5️⃣ Example driver test
//    public static void main(String[] args) {
//        AzureResource vm = new AzureResource("Microsoft.Compute/virtualMachines", "myVM", "sub-100");
//        processEvent("Update", vm);
//    }
//}
