package io.kimmking.rpcfx.demo.provider;

import io.kimmking.rpcfx.demo.api.Order;
import org.junit.jupiter.api.Test;

import javax.sound.midi.Soundbank;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.AlgorithmConstraints;
import java.security.acl.Acl;

class OrderServiceImplTest {

    @Test
    void findOrderById() {
        OrderServiceImpl orderService = new OrderServiceImpl();
        Order orderById = orderService.findOrderById(3);
        System.out.println(orderById.getId());
    }

    @Test
    void findOrderByIdReflect() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName("io.kimmking.rpcfx.demo.provider.OrderServiceImpl");

        Object inst = aClass.newInstance();
        System.out.println(aClass);
        Method findOrderById = aClass.getMethod("findOrderById", int.class);
        System.out.println(findOrderById);
        Object invoke = findOrderById.invoke(inst, 3);
        System.out.println(invoke);
    }
}