package com.interview.notes.code.misc.test1;

public class GenerateBill {

    public static void main(String[] agrs) {

        //1
        // static float x=0.0;
        //  ++x;
        //  test();

        int index = 0;
        boolean flag = true;
        boolean reg1 = false, reg2;

        reg2 = (flag | ((index++) == 0));
        System.out.println(reg2);
        reg2 = (reg1 | ((index += 2) > 0));

        System.out.println("index->" + index);

        GetPlanFactory gf = new GetPlanFactory();

        int unit = 45;

        Plan dom = gf.gePlan("DOMESTICPLAN");

        System.out.println("Domistic ");
        System.out.println(dom.getRate());
        // dom.caluclateBill();
        Plan com = gf.gePlan("COMMERCIALPLAN");

        System.out.println("Commercial ");
        System.out.println(com.getRate());
        //  com.caluclateBill();


    }

    int test() {
        int i = 1;
        return i;
    }
}