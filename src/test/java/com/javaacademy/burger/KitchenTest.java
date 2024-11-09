package com.javaacademy.burger;

import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class KitchenTest {

  Kitchen kitchen;

  @BeforeEach
  void initKitchen() {
    kitchen = new Kitchen();
  }

  @Test
  void kitchenHasSuccessfullyPreparedBurger() {
    kitchen.cook(DishType.BURGER);
    Dish burger = new Dish(DishType.BURGER);
    Assertions.assertTrue(
        kitchen.getCompletedDishes().get(DishType.BURGER).contains(burger));
  }

  @Test
  void kitchenThrewExceptionPreparedBurgerWhenGasWasTurnedOff() {
    kitchen.setHasGas(false);
    Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(DishType.BURGER));
  }

  @Test
  void kitchenThrewExceptionPreparedFuagra() {
    Assertions.assertThrows(UnsupportedDishTypeException.class,
        () -> kitchen.cook(DishType.FUAGRA));
  }
}