package com.jinchi.java.base.base.inner;

/**
 * @Author: vhtk
 * @Description:
 * @Date: 2020/9/30
 */
public class A {

    private Integer a;
    private static Integer s = 10;

    public A() {
    }

    public A(Integer a) {
        this.a = a;
    }

    public void funA1() {
        System.out.println(a);
        // 外部类不能访问内部类属性
        //System.out.println(aa);
    }

    public void funA2() {
        // 外部类不能访问内部类方法
        //funAA1();
    }

    public static void funA3() {
        System.out.println(s);
    }

    // 普通内部类
    protected class AA {
        private Integer aa;

        public AA() {
        }

        public AA(Integer aa) {
            this.aa = aa;
        }

        public void funAA1() {
            // 内部类能访问外部类属性
            System.out.println(a);
            System.out.println(aa);
        }

        public void funAA2() {
            // 内部类能访问外部类方法
            funA1();
        }
    }

    // 静态内部类
    protected static class BB {
        private static Integer ss = 100;
        private Integer bb;

        public void funBB1() {
            // 内部类能访问外部类静态属性
            System.out.println(s);
            System.out.println(ss);
            // 内部类不能访问外部类非静态属性
            //System.out.println(a);
        }

        public void funBB2() {
            // 内部类静态方法不能访问外部类非静态方法
            //funA1();
            // 内部类静态方法能访问外部类静态方法
            funA3();
        }
    }
}
