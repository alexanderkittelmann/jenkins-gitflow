package de.gedoplan.seminar.cidemo;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * Utility-Klasse mit einigen (unsinnigen) String-Funktionen.
 * 
 * @author dw
 */
public final class StringUtil {
  /**
   * Zählt die KLeinbuchstaben in einem String.
   * 
   * @param s
   *          String
   * @return Anzahl Kleinbuchstaben
   */
  public static int countLower(String s) {
    int result = 0;
    CharacterIterator characterIterator = new StringCharacterIterator(s);
    for (char ch = characterIterator.first(); ch != CharacterIterator.DONE; ch = characterIterator.next()) {
      if (ch >= 'a' && ch <= 'z') // [Fehler: Das klappt nur bei ASCII!]
      // if (Character.isLowerCase(ch))
      {
        ++result;
      }
    }

    return result;
  }

  private StringUtil() {
  }
}
