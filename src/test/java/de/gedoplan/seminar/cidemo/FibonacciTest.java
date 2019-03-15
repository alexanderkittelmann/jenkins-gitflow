package de.gedoplan.seminar.cidemo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import de.gedoplan.seminar.cidemo.Fibonacci;

import java.util.Random;

import org.junit.Test;

public class FibonacciTest {
  @Test
  public void testFibonacci0() {
    assertThat(Fibonacci.fibonacci(0), is(0));
  }

  @Test
  public void testFibonacci1() {
    assertThat(Fibonacci.fibonacci(1), is(1));
  }

  @Test
  public void testFibonacci10() {
    assertThat(Fibonacci.fibonacci(10), is(55));
  }

  @Test
  public void testFibonacciRule() {
    int n = new Random(System.currentTimeMillis()).nextInt(998) + 2;
    assertThat(Fibonacci.fibonacci(n), is(Fibonacci.fibonacci(n - 2) + Fibonacci.fibonacci(n - 1)));
  }
}
