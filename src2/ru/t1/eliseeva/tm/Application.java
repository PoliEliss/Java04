package ru.t1.eliseeva.tm;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Application {


    @ToString
    private static class TestClass
    {
        public int a1, a2, a3;
        @ToString.Exclude
        public int a4;

        public TestClass(int a1, int a2, int a3) {
            this.a1 = a1;
            this.a2 = a2;
            this.a3 = a3;
        }
    }




    public static void main(String[] args) throws Exception {
        System.out.println("Hello World!");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        System.out.println(gson.toJson(new String[]{"1234","4321"}));
        TestClass tc = new TestClass(1,2,3);
        System.out.println(tc);
        //new Singleton();
        //Singleton.getInstance()
        Singleton singleton = (Singleton) Singleton.class.getMethod("getInstance").invoke(null);
        Singleton.class.getMethod("test").invoke(singleton);
        Singleton.class.getMethod("test",int.class).invoke(singleton,6);
        Method m = Singleton.class.getMethod("test",int.class, int.class);
        //Arrays.stream(m.getParameterAnnotations()).filter(a->a.length > 0).flatMap(a->Arrays.stream(a))
        //        .filter(a->a.annotationType() == MyAnnotation.class).map(a->(MyAnnotation)a).findFirst().get();

        Annotation[][] paramAnnotations = m.getParameterAnnotations();
        for(int param = 0; param < paramAnnotations.length; param++)
        {
            if(paramAnnotations.length == 0)
                continue;
            for(int annotationIdx = 0; annotationIdx < paramAnnotations[param].length; annotationIdx++)
            {
                if(paramAnnotations[param][annotationIdx].annotationType() != MyAnnotation.class)
                {
                    System.out.println("Опа, это не MyAnnotation.");
                    continue;
                }
                else
                {
                    MyAnnotation annotation = (MyAnnotation) paramAnnotations[param][annotationIdx];
                    System.out.println(annotation.value());
                    Object[] params = new Object[] {1234, annotation.value()};
                    m.invoke(singleton, params);
                    break;
                }
            }
        }

        for(Method method : Singleton.class.getDeclaredMethods())
        {
            if(!method.isAnnotationPresent(MyAnnotation2.class))
                continue;
            if(method.getParameterCount() == 0)
            {
                method.invoke(singleton);
            }
            else if(method.getParameterCount() == 1)
            {
                MyAnnotation2 myAnnotation2 = method.getDeclaredAnnotation(MyAnnotation2.class);
                method.invoke(singleton, myAnnotation2.value());
            }
            else
            {
                System.out.println("Вей вей чето не то в "+method.getName());
            }
        }

        }

    }
