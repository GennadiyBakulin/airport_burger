package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SteakhouseTest {

  @Test
  void whenClientBuyBurgerForRublesThenBackReceipt300RubBurgerAndDishIsBurger() {
    Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), new PayTerminal());
    Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.RUB);
    Dish burger = steakhouse.takeOrder(paycheck);
    Assertions.assertAll(
        () -> Assertions.assertEquals(BigDecimal.valueOf(300), paycheck.getTotalAmount()),
        () -> Assertions.assertEquals(Currency.RUB, paycheck.getCurrency()),
        () -> Assertions.assertEquals(DishType.BURGER, paycheck.getDishType()),
        () -> Assertions.assertEquals(DishType.BURGER, burger.getDishType())
    );
  }

  @Test
  void whenCheckingSanitaryInspectionStationThenBackReceipt700RubRibsAndDishIsRibs() {
    PayTerminal mockPayTerminal = Mockito.mock(PayTerminal.class);
    Mockito.when(mockPayTerminal.pay(DishType.RIBS, Currency.RUB))
        .thenReturn(new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS));
    Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), mockPayTerminal);
    Paycheck paycheck = steakhouse.makeOrder(DishType.RIBS, Currency.RUB);
    Dish burger = steakhouse.takeOrder(paycheck);
    Assertions.assertAll(
        () -> Assertions.assertEquals(BigDecimal.valueOf(700), paycheck.getTotalAmount()),
        () -> Assertions.assertEquals(Currency.RUB, paycheck.getCurrency()),
        () -> Assertions.assertEquals(DishType.RIBS, paycheck.getDishType()),
        () -> Assertions.assertEquals(DishType.RIBS, burger.getDishType())
    );
  }

}
