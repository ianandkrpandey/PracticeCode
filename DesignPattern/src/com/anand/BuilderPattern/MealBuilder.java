package com.anand.BuilderPattern;

public class MealBuilder {
public Meal vegMeal() {
	Meal meal = new Meal();
	meal.additem(new VegBurger());
	meal.additem(new Coke());
	return meal;
}
public Meal nonvegMeal() {
	Meal meal = new Meal();
	meal.additem(new ChickenBurger());
	meal.additem(new Pepsi());
	return meal;
}
}
