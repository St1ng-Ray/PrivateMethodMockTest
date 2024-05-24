package org.example;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.util.Objects.isNull;

@Slf4j
public final class Executor {

    private Executor() {
    }

    public static <T> T executePrivateMethod(Class<?> aClass, String methodName, Param param) throws Throwable {
        Constructor<?> constructor = aClass.getConstructors()[0];
        var instance = constructor.newInstance();
        return execute(instance, methodName, param, null);
    }

    public static <T> T executePrivateMethod(Class<?> aClass, String methodName, Param param, Param param2) throws Throwable {
        Constructor<?> constructor = aClass.getConstructors()[0];
        var instance = constructor.newInstance();
        return execute(instance, methodName, param, param2);
    }

    public static <T> T executePrivateMethod(Object instance, String methodName, Param param) throws Throwable {
        return execute(instance, methodName, param, null);
    }

    public static <T> T executePrivateMethod(Object instance, String methodName, Param param1,
                                             Param param2) throws Throwable {
        return execute(instance, methodName, param1, param2);
    }

    public static <T> T executePrivateMethod(Object instance, String methodName) throws Throwable {
        T result = null;

        try {
            Method method = instance.getClass().getDeclaredMethod(methodName);
            method.setAccessible(true);

            try {
                result = (T) method.invoke(instance);

            } catch (IllegalAccessException e) {

                log.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e);
                throw e.getTargetException();
            }
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

    // 大致流程：反射和newInstance方法创建待测试类的全新对象
    // 获取新对象的待测试private方法，方法的参数全部由Param类传递
    // 虽说是mock private方法，但其实执行方法的并非spy对象，这个模拟仅仅返回执行结果
    private static <T> T execute(Object instance, String methodName, Param param, Param param2) throws Throwable {
        T result = null;

        try {
            Method method = isNull(param2) ? instance.getClass().getDeclaredMethod(methodName, param.getType())
                    : instance.getClass().getDeclaredMethod(methodName, param.getType(), param2.getType());
            method.setAccessible(true);

            try {
                result = isNull(param2) ? (T) method.invoke(instance, param.getValue())
                        : (T) method.invoke(instance, param.getValue(), param2.getValue());
            } catch (IllegalAccessException e) {
                log.error(e.getMessage(), e);
            } catch (InvocationTargetException e) {
                log.error(e.getMessage(), e);
                throw e.getTargetException();
            }
        } catch (NoSuchMethodException e) {
            log.error(e.getMessage(), e);
        }

        return result;
    }

}