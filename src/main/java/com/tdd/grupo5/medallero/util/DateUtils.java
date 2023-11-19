package com.tdd.grupo5.medallero.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public final class DateUtils {
  public static Date asDate(LocalDate localDate) {
    return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }
}
