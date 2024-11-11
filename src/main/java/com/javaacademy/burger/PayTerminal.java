package com.javaacademy.burger;

import static com.javaacademy.burger.Currency.MOZAMBICAN_DOLLARS;
import static com.javaacademy.burger.Currency.USD;
import static java.math.RoundingMode.CEILING;

import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import java.math.BigDecimal;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Терминал оплаты
 */
@Slf4j
public class PayTerminal {

  private static final long TIME_SLEEP = 1_000_000_000;
  private static final long CONVERSION_RATE = 1_000_000_000;

  /**
   * Оплата блюда На вход стоимость заказа в рублях и валюта заказа. На выход чек об оплате.
   * <p>
   * Если валюта оплаты отличается от рублевой, то происходит запрос в банк для получения курса
   * конвертации Клиент увидит стоимость в указанной валюте.
   */
  public Paycheck pay(DishType dishType, Currency currency) {
    log.info("Началась оплата заказа");
    BigDecimal result = dishType.getPrice(); //Стоимость блюда в рублях
    if (currency == USD) {
      log.info("Ожидается ответ от банка для получения актуального курса доллара...");
      BigDecimal usdRate = integrationWithBank();
      log.info("Получен курс 95 рублей за доллар");
      result = result.divide(usdRate, CEILING);
    } else if (currency == MOZAMBICAN_DOLLARS) {
      throw new NotAcceptedCurrencyException("Банк не обменивает мозамбикские доллары");
    }
    log.info("Товар оплачен успешно!");
    Paycheck paycheck = new Paycheck(result, currency, dishType);
    log.info("Печать чека: {}", paycheck);
    return paycheck;
  }

  /**
   * Интеграция с банком
   */
  @SneakyThrows
  private BigDecimal integrationWithBank() {
    Thread.sleep(TIME_SLEEP);
    return BigDecimal.valueOf(CONVERSION_RATE);
  }
}
