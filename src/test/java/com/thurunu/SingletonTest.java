package com.thurunu;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class SingletonTest {
    @Test
    public void testSingletonInstance() {
        // Verify that calling getInstance() multiple times returns the same instance
        Singleton instance1 = Singleton.getInstance();
        Singleton instance2 = Singleton.getInstance();

        assertSame(instance1, instance2, "Multiple calls to getInstance() should return the same instance");
    }

    @Test
    public void testSingletonCreation() {
        // Verify that the first call to getInstance() creates an instance
        Singleton instance = Singleton.getInstance();

        assertNotNull(instance, "getInstance() should return a non-null instance");
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        // Verify that the constructor is private
        Constructor<Singleton> constructor = Singleton.class.getDeclaredConstructor();

        assertTrue(java.lang.reflect.Modifier.isPrivate(constructor.getModifiers()),
                "Singleton constructor should be private");

        // Attempt to make the constructor accessible and create an instance
        constructor.setAccessible(true);
        Singleton reflectionInstance = constructor.newInstance();

        // Verify that reflection-created instance is different from singleton instance
        assertNotSame(Singleton.getInstance(), reflectionInstance,
                "Reflection should create a different instance");
    }

    @Test
    public void testThreadSafety() throws InterruptedException {
        // Create an array to store instances from multiple threads
        Singleton[] instances = new Singleton[100];

        // Create multiple threads to call getInstance()
        Thread[] threads = new Thread[100];
        for (int i = 0; i < 100; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = Singleton.getInstance();
            });
            threads[i].start();
        }

        // Wait for all threads to complete
        for (Thread thread : threads) {
            thread.join();
        }

        // Verify all threads received the same instance
        for (int i = 1; i < instances.length; i++) {
            assertSame(instances[0], instances[i],
                    "All threads should receive the same singleton instance");
        }
    }

    @Test
    public void testLazyInitialization() throws Exception {
        // Use reflection to check if instance is null before first call
        Method getInstanceMethod = Singleton.class.getMethod("getInstance");

        // Reset the instance to null using reflection
        java.lang.reflect.Field instanceField = Singleton.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        // Verify getInstance() creates an instance when it was previously null
        Singleton initialInstance = Singleton.getInstance();
        assertNotNull(initialInstance, "First call to getInstance() should create an instance");
    }

}