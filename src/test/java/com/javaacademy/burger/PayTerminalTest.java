package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.NotAcceptedCurrencyException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PayTerminalTest {

  PayTerminal payTerminal;

  @BeforeEach
  void init() {
    payTerminal = new PayTerminal();
  }

  @Test
  void whenBurgerWasAcceptedForPaymentInRubThenBackReceipt300RubBurger() {
    Paycheck paycheck = payTerminal.pay(DishType.BURGER, Currency.RUB);
    Assertions.assertAll(
        () -> Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount()),
        () -> Assertions.assertEquals(Currency.RUB, paycheck.getCurrency()),
        () -> Assertions.assertEquals(DishType.BURGER, paycheck.getDishType())
    );
  }

  @Test
  void whenBurgerWasAcceptedForPaymentInMozambicanDollarsThenThrewException() {
    Assertions.assertThrows(NotAcceptedCurrencyException.class,
        () -> payTerminal.pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS));
  }
}
