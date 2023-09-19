package ru.t1.eliseeva.tm;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Singleton {

    public static Singleton getInstance()
    {
        return Single.instance;
    }

    public void test()
    {
        System.out.println("i am singleton");
    }

    public void test(int i)
    {
        System.out.println("i am singleton. i = "+i);
    }

    public void test(int i, @MyAnnotation(100) int i2)
    {
        System.out.println("i am singleton. i = "+i+", i2 = "+i2);
    }

    @MyAnnotation2
    public void Method1()
    {
        System.out.println("i am method1");
    }

    @MyAnnotation2
    public void Method2(int i)
    {
        System.out.println("i am method2. i = "+i);
    }

    @MyAnnotation2(1000)
    public void Method3(int i)
    {
        System.out.println("i am method3. i = "+i);
    }

    @MyAnnotation2(1000)
    public void Method4(int i, int i2)
    {
        System.out.println("i am method4. i = "+i);
    }

    private static final class Single
    {
        private static final Singleton instance = new Singleton();
    }
}
