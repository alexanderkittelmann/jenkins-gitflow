package de.gedoplan.seminar.cidemo;

/**
 * Demo-Klasse: Berechnung der Fibonacci-Zahlen.
 *
 * @author dw
 */
public final class Fibonacci {
  /*
   * [CS-Warnung: Dies sollte Javadoc sein, ist aber nur ein einfacher Kommentar]
   * Ausgabe der Fibonacci-Folge bis 50
   *
   * @param n Stelle
   *
   * @return Wert
   */
  public static int fibonacci(int n) {
    if (n < 0) {
      throw new IllegalArgumentException("n<0");
    }

    if (n <= 1) {
      return n;
    }

    int last = 0;
    int current = 1;
    // while (n > 2) // [Fehler: Bricht zu früh ab]
    while (n >= 2) {
      int old = current;
      current = last + current;
      last = old;
      --n;
    }
    return current;
  }

  /*
   * [CS-Warnung: Für Util-Klassen sollte der Konstruktor da sein]
   * private Fibonacci()
   * {
   * }
   */
}
