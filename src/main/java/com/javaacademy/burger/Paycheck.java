package com.javaacademy.burger;

import com.javaacademy.burger.dish.DishType;
import java.math.BigDecimal;
import lombok.Value;

/**
 * Чек об оплате
 */
@Value
public class Paycheck {

  BigDecimal totalAmount; //Итоговая стоимость в валюте
  Currency currency; //Валюта чека
  DishType dishType; //Какое блюдо было оплачено
}
