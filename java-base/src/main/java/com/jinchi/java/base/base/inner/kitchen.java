package com.jinchi.java.base.base.inner;

public class kitchen extends House {
    private Cooker cooker;

    public kitchen() {
        this.cooker = new Cooker();
    }

    private void kitchenware() {
        System.out.println("我是厨房,我可以提供厨具!!!");
    }

    public void cooking() {
        this.cooker.cooking();
    }

    protected class Cooker extends Person {
        void cooking() {
            keeping();
            kitchenware();
            doing();
            System.out.println("我是厨师,有了房子和厨具,我可以做饭!!!");
        }
    }
}