package de.gedoplan.seminar.cidemo;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import de.gedoplan.seminar.cidemo.StringUtil;

import org.junit.Test;

public class StringUtilTest {
  @Test
  public void testASCII() {
    assertThat(StringUtil.countLower("Hello, world!"), is(9));
  }

  @Test
  public void testUmlaut() {
    assertThat(StringUtil.countLower("Schoen, dass du da bist!"), is(17));
  }

}
