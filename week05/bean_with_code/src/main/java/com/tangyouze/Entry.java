package com.tangyouze;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author tyz
 * -------输出结果--------
 * hello
 * foo inited
 * 应该要打印出来
 */
public class Entry {
    public static void main(String[] args) {
        System.out.println("hello");
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            context.getBean("myBean");
        } catch (Exception e) {
            System.out.println("应该要打印出来");
        }

        context.getBean("foo");
    }
}
