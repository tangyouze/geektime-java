package com.tangyouze;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author tyz
 */
public class Entry {
    public static void main(String[] args) {
        System.out.println("main");
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        /*
        输出 这样子 说明bean 已经被成功的装配了
        main
        playBean init

        注释掉下面这行之后 会发现 不会输出 playBean init
        <!--    <bean id="playBean" class="com.tangyouze.PlayBean"></bean>-->

         */
    }
}
