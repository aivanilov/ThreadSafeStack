package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class StackingFuckingGoodTest {
    @Test
    void singleThreadStacking() {
        StackingFuckingGood stack = new StackingFuckingGood();
        Assertions.assertNull(stack.pop());
        Assertions.assertNull(stack.peek());
        stack.push(2);
        stack.push(1);
        stack.push(-1);
        stack.push(3);
        Assertions.assertEquals(-1, stack.min());
        Assertions.assertEquals(3, stack.peek());
        Assertions.assertEquals(3, stack.pop());
        Assertions.assertEquals(-1, stack.min());
        Assertions.assertEquals(-1, stack.peek());
        Assertions.assertEquals(-1, stack.pop());
        Assertions.assertEquals(1, stack.min());
        Assertions.assertEquals(1, stack.peek());
        Assertions.assertEquals(1, stack.pop());
        Assertions.assertEquals(2, stack.min());
        Assertions.assertEquals(2, stack.peek());
        Assertions.assertEquals(2, stack.pop());
        Assertions.assertNull(stack.min());
        Assertions.assertNull(stack.peek());
        Assertions.assertNull(stack.pop());
        stack.push(10);
        Assertions.assertEquals(10, stack.peek());
        stack.push(5);
        Assertions.assertEquals(5, stack.pop());
        stack.push(7);
        Assertions.assertEquals(7, stack.peek());
    }

    @Test
    void multiThreading() {
        StackingFuckingGood stack = new StackingFuckingGood();
        ExecutorService executorService = Executors.newCachedThreadPool();
        AtomicInteger numberCount = new AtomicInteger();
        AtomicInteger nullCount = new AtomicInteger();

        for (int i = 0; i < 10000; i++) {
            executorService.submit(() -> {
                Integer v = stack.pop();
                determineTheCaseAndCount(numberCount, nullCount, v);

                stack.push(5);
                v = stack.pop();
                determineTheCaseAndCount(numberCount, nullCount, v);

                stack.push(10);
                v = stack.pop();
                determineTheCaseAndCount(numberCount, nullCount, v);
            });
        }

        System.out.println("numberCount = " + numberCount);
        System.out.println("nullCount = " + nullCount);
        Assertions.assertTrue(numberCount.get() > 0);
        Assertions.assertTrue(nullCount.get() > 0);
        executorService.shutdown();
    }

    private static void determineTheCaseAndCount(AtomicInteger numberCount, AtomicInteger nullCount, Integer v) {
        if (v != null) {
            numberCount.incrementAndGet();
        } else {
            nullCount.incrementAndGet();
        }
    }
}