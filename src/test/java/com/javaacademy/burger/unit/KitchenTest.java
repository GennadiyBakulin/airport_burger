package com.javaacademy.burger.unit;

import com.javaacademy.burger.Kitchen;
import com.javaacademy.burger.dish.Dish;
import com.javaacademy.burger.dish.DishType;
import com.javaacademy.burger.exception.KitchenHasNoGasException;
import com.javaacademy.burger.exception.UnsupportedDishTypeException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class KitchenTest {

  Kitchen kitchen;

  @BeforeEach
  void init() {
    kitchen = new Kitchen();
  }

  @Test
  @DisplayName("Кухня успешно приготовила бургер (бургер появился на столе готовой еды)")
  void kitchenHasSuccessfullyPreparedBurger() {
    kitchen.cook(DishType.BURGER);
    Assertions.assertTrue(
        kitchen.getCompletedDishes().get(DishType.BURGER).contains(new Dish(DishType.BURGER)));
  }

  @Test
  @DisplayName("Был запрошен на приготовление бургер, на кухне выключили газ,\n"
      + "вылетела ошибка KitchenHasNoGasException")
  void kitchenThrewExceptionPreparedBurgerWhenGasWasTurnedOff() {
    kitchen.setHasGas(false);
    Assertions.assertThrows(KitchenHasNoGasException.class, () -> kitchen.cook(DishType.BURGER));
  }

  @Test
  @DisplayName("Была запрошена на приготовление фуагра,\n"
      + "вылетела ошибка UnsupportedDishTypeException")
  void kitchenThrewExceptionPreparedFuagra() {
    Assertions.assertThrows(UnsupportedDishTypeException.class,
        () -> kitchen.cook(DishType.FUAGRA));
  }
}