package com.anand.BuilderPattern;

public class BuilderPatternDemo {
public static void main(String[] args) {
	MealBuilder mb = new MealBuilder();
	Meal vegMeal = mb.vegMeal();
	System.out.println("Veg Meal");
	vegMeal.showItems();
	System.out.println("Total Cost"+vegMeal.getCost());
	
	Meal nonvegMeal = mb.nonvegMeal();
	System.out.println("Non Veg Meal");
	nonvegMeal.showItems();
	System.out.println("Total Cost"+nonvegMeal.getCost());
	
}
}
