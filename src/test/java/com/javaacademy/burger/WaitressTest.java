package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class WaitressTest {

  Waitress waitress;
  Kitchen mock = Mockito.mock(Kitchen.class);

  @BeforeEach
  void init() {
    waitress = new Waitress();
  }

  @Test
  void whenWasRequestedBurgerThenWaitressAcceptedOrder() {
    Assertions.assertTrue(waitress.giveOrderToKitchen(DishType.BURGER, mock));
  }

  @Test
  void whenWasRequestedFuagraThenWaitressNotAcceptedOrder() {
    Assertions.assertFalse(waitress.giveOrderToKitchen(DishType.FUAGRA, mock));
  }
}
