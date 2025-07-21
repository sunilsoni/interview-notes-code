package com.interview.notes.code.year.y2025.july.common.test1;

public class ExhibitionAccessSystem {

    // Demo usage
    public static void main(String[] args) {
        ExhibitionAccessSystem system = new ExhibitionAccessSystem();

        // Visitor enters
        IAccess visitorCard = system.enter(12345);

        // Simulate visitor visiting vendors
        VendorBooth vendor1 = new SampleVendor();
        VendorBooth vendor2 = new SampleVendor();

        vendor1.visit(visitorCard);
        vendor1.givePromotionalMaterials(visitorCard);
        vendor2.visit(visitorCard);

        // Visitor exits
        system.exit(visitorCard);
    }

    // Main system methods
    public IAccess enter(int personId) {
        // Get card from factory and wrap it with our counter
        IAccess card = AccessFactory.getCard(personId);
        return new CardTapCounter(card);
    }

    public void exit(IAccess card) {
        if (card instanceof CardTapCounter) {
            CardTapCounter counter = (CardTapCounter) card;
            System.out.println("Card was tapped " + counter.getTapCount() + " times");
        }
        // Additional exit processing...
    }

    // Third-party interface provided by AccessFactory
    public interface IAccess {
        void tap();
    }

    // Simulated third-party black box factory
    private static class AccessFactory {
        public static IAccess getCard(int personId) {
            return new AccessCard(); // Internal implementation
        }

        // Internal implementation - we don't have access to this
        private static class AccessCard implements IAccess {
            public void tap() {
                // Black box implementation
            }
        }
    }

    // Our decorator to count taps
    public static class CardTapCounter implements IAccess {
        private final IAccess originalCard;
        private int tapCount = 0;

        public CardTapCounter(IAccess card) {
            this.originalCard = card;
        }

        @Override
        public void tap() {
            tapCount++;
            originalCard.tap();
        }

        public int getTapCount() {
            return tapCount;
        }
    }

    // Abstract vendor booth provided by exhibition
    public abstract static class VendorBooth {
        public void visit(IAccess card) {
            // Vendor specific implementation
            card.tap();
        }

        public void givePromotionalMaterials(IAccess card) {
            // Vendor specific implementation
            card.tap();
        }
    }

    // Example vendor implementation
    public static class SampleVendor extends VendorBooth {
        @Override
        public void visit(IAccess card) {
            System.out.println("Visitor at sample vendor booth");
            super.visit(card);
        }
    }
}
