package com.javaacademy.burger.integration;

import com.javaacademy.burger.Currency;
import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.PayTerminal;
import com.javaacademy.burger.Paycheck;
import com.javaacademy.burger.Steakhouse;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class SteakhouseIT {

  @Test
  @DisplayName("Клиент захотел купить бургер за рубли.\n"
      + "  Заказал бургер, получил чек(300 руб, бургер, рубли),\n"
      + "  подошел за заказом, получил бургер")
  void whenClientBuyBurgerForRublesThenBackReceipt300RubBurgerAndDishIsBurger() {
    Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), new PayTerminal());
    Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(300), Currency.RUB,
        DishType.BURGER);
    Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.RUB);
    Dish burger = steakhouse.takeOrder(paycheck);
    Assertions.assertAll(
        () -> Assertions.assertEquals(paycheckExpected, paycheck),
        () -> Assertions.assertEquals(DishType.BURGER, burger.getDishType())
    );
  }

  @Test
  @DisplayName("Клиент захотел купить ребра за рубли.\n"
      + "  Заказал ребра, получил чек(700 руб, ребра, рубли),\n"
      + "  подошел за заказом, получил ребра")
  void whenCheckingSanitaryInspectionStationThenBackReceipt700RubRibsAndDishIsRibs() {
    PayTerminal mockPayTerminal = Mockito.mock(PayTerminal.class);
    Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS);
    Mockito.when(mockPayTerminal.pay(DishType.RIBS, Currency.RUB))
        .thenReturn(paycheckExpected);
    Steakhouse steakhouse = new Steakhouse(new Waitress(), new Kitchen(), mockPayTerminal);
    Paycheck paycheck = steakhouse.makeOrder(DishType.RIBS, Currency.RUB);
    Dish burger = steakhouse.takeOrder(paycheck);
    Assertions.assertAll(
        () -> Assertions.assertEquals(paycheckExpected, paycheck),
        () -> Assertions.assertEquals(DishType.RIBS, burger.getDishType())
    );
  }

  @Test
  @DisplayName("Клиент захотел купить ребра за рубли.\n"
      + "  Заказал ребра, получил чек(сумма - 700, тип заказа - ребра, валюта - рубли)")
  void whenCheckingTaxServiceBuyRibsRubThenBackReceipt700RubRibs() {
    Kitchen mockKitchen = Mockito.mock(Kitchen.class);
    Waitress mockWaitress = Mockito.mock(Waitress.class);
    PayTerminal payTerminal = new PayTerminal();
    Mockito
        .doReturn(true)
        .when(mockWaitress).giveOrderToKitchen(Mockito.any(), Mockito.any());
    Paycheck paycheckExpected = new Paycheck(BigDecimal.valueOf(700), Currency.RUB, DishType.RIBS);
    Steakhouse steakhouse = new Steakhouse(mockWaitress, mockKitchen, payTerminal);
    Paycheck paycheck = steakhouse.makeOrder(DishType.RIBS, Currency.RUB);
    Assertions.assertEquals(paycheckExpected, paycheck);
  }

  @Test
  @DisplayName("Клиент захотел купить картошку за доллары.\n"
      + "  Заказал картошку, получил чек(1, картошка, доллар)")
  void whenCheckingTaxServiceBuyFriedPotatoDollarsThenBackReceipt1DollarFriedPotato() {
    Kitchen mockKitchen = Mockito.mock(Kitchen.class);
    Waitress mockWaitress = Mockito.mock(Waitress.class);
    PayTerminal spyPayTerminal = Mockito.spy(PayTerminal.class);
    Paycheck paycheckExpected = new Paycheck(BigDecimal.ONE, Currency.USD, DishType.FRIED_POTATO);
    Mockito
        .doReturn(true)
        .when(mockWaitress).giveOrderToKitchen(Mockito.any(), Mockito.any());
    Mockito
        .doReturn(paycheckExpected)
        .when(spyPayTerminal).pay(DishType.FRIED_POTATO, Currency.USD);
    Steakhouse steakhouse = new Steakhouse(mockWaitress, mockKitchen, spyPayTerminal);
    Paycheck paycheck = steakhouse.makeOrder(DishType.FRIED_POTATO, Currency.USD);
    Assertions.assertEquals(paycheckExpected, paycheck);
  }

  @Test
  @DisplayName("Клиент захотел купить бургер за мозамбикские доллары.\n"
      + "  Заказал бургер, получил чек(1, бургер, мозамбикский доллар)")
  void whenCheckingTaxServiceBuyBurgerMozambicanDollarsThenBackReceipt1MozambicanDollarBurger() {
    Kitchen mockKitchen = Mockito.mock(Kitchen.class);
    Waitress mockWaitress = Mockito.mock(Waitress.class);
    PayTerminal spyPayTerminal = Mockito.spy(PayTerminal.class);
    Paycheck paycheckExpected = new Paycheck(BigDecimal.ONE, Currency.MOZAMBICAN_DOLLARS,
        DishType.BURGER);
    Mockito
        .doReturn(true)
        .when(mockWaitress).giveOrderToKitchen(Mockito.any(), Mockito.any());
    Mockito
        .doReturn(paycheckExpected)
        .when(spyPayTerminal).pay(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS);
    Steakhouse steakhouse = new Steakhouse(mockWaitress, mockKitchen, spyPayTerminal);
    Paycheck paycheck = steakhouse.makeOrder(DishType.BURGER, Currency.MOZAMBICAN_DOLLARS);
    Assertions.assertEquals(paycheckExpected, paycheck);
  }
}
