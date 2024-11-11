package com.javaacademy.burger.unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.Waitress;
import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaitressTest {

  Kitchen mock = Mockito.mock(Kitchen.class);
  Waitress waitress;

  @BeforeEach
  void init() {
    waitress = new Waitress();
  }

  @Test
  @DisplayName("Был запрошен бургер, официант принял заказ")
  void whenWasRequestedBurgerThenWaitressAcceptedOrder() {
    Assertions.assertTrue(waitress.giveOrderToKitchen(DishType.BURGER, mock));
  }

  @Test
  @DisplayName("Была запрошена фуагра, официант не принял заказ")
  void whenWasRequestedFuagraThenWaitressNotAcceptedOrder() {
    Assertions.assertFalse(waitress.giveOrderToKitchen(DishType.FUAGRA, mock));
  }
}
