package com.javaacademy.burger.unit;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PayTerminalTest {

  PayTerminal payTerminal;

  @BeforeEach
  void init() {
    payTerminal = new PayTerminal();
  }

  @Test
  @DisplayName("На оплату поступил бургер, оплата в рублях.\n"
      + "  Вернулся чек с оплатой в котором указано: 300 рублей, валюта - рубль,\n"
      + "  товар - бургер")
  void whenBurgerWasAcceptedForPaymentInRubThenBackReceipt300RubBurger() {
    Paycheck paycheck = payTerminal.pay(DishType.BURGER, Currency.RUB);
    Assertions.assertAll(
        () -> Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount()),
        () -> Assertions.assertEquals(Currency.RUB, paycheck.getCurrency()),
        () -> Assertions.assertEquals(DishType.BURGER, paycheck.getDishType())
    );
  }

  @Test
  @DisplayName("На оплату поступил бургер, оплата в мозамбикских долларах,\n"
      + "  вылетела ошибка NotAcceptedCurrencyException")
  void whenBurgerWasAcceptedForPaymentInMozambicanDollarsThenThrewException() {
    Assertions.assertThrows(NotAcceptedCurrencyException.class,
        () -> payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
  }
}
