package com.interview.notes.code.year.y2026.jan.common.test3;

public class Snippet6 {

    public static void main(String[] args)
            throws InstantiationException, IllegalAccessException {

        Snippet6 instance = new Snippet6();

        TargetHolder<String> targetA = instance.createTarget(String.class);
        TargetHolder<Object> targetB = instance.createTarget(Object.class);

        System.out.println("Target A = " + targetA.getTarget());
        System.out.println("Target B = " + targetB.getTarget());

        String helloMessage = "Hello World";
        targetA.injectTarget(helloMessage);
        targetB.injectTarget(helloMessage);

        System.out.println("Target A is now = " + targetA.getTarget());
        System.out.println("Target B is now = " + targetB.getTarget());
    }

    private <T> TargetHolder<T> createTarget(Class<T> clazz)
            throws InstantiationException, IllegalAccessException {

        T obj = clazz.newInstance(); // reflection (deprecated but used intentionally)
        TargetHolder<T> target = new TargetHolderImpl<>();
        target.injectTarget(obj);
        return target;
    }

    public interface TargetHolder<T> {
        void injectTarget(T target);

        T getTarget();
    }

    private static class TargetHolderImpl<T> implements TargetHolder<T> {
        private T target;

        public void injectTarget(T target) {
            this.target = target;
        }

        public T getTarget() {
            return target;
        }
    }
}
