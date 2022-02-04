package com.interview.notes.code.misc.test1;

class GetPlanFactory {


    public Plan gePlan(String planType) {

        if (planType == null) {
            return null;
        }

        if (planType.equalsIgnoreCase("DOMESTICPLAN")) {
            return new DomecticPlan();
        } else if (planType.equalsIgnoreCase("COMMERCIALPLAN")) {
            return new CommercialPlan();
        } else {
            return null;
        }


    }


}
