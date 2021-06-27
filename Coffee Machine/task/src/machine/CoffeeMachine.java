package machine;

import java.awt.*;
import java.util.Scanner;

public class CoffeeMachine {


    private static int amountOfWaterInMachine = 400;
    private static int amountOfMilkInMachine = 540;
    private static int amountOfCoffeeInMachine = 120;
    private static int amountOfDisposableCups = 9;
    private static int amountOfMoney = 550;
    private static boolean machineShouldBeRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
//        System.out.println("Starting to make a coffee");
//        System.out.println("Grinding coffee beans");
//        System.out.println("Boiling water");
//        System.out.println("Mixing boiled water with crushed coffee beans");
//        System.out.println("Pouring coffee into the cup");
//        System.out.println("Pouring some milk into the cup");
//        System.out.println("Coffee is ready!");

//        calculateIngredients(numberOfCupsNeeded);


        while (machineShouldBeRunning) {
            System.out.println("Write action (buy, fill, take, remaining, exit):");
            String input = scanner.next();
            switch (input) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    printAmountIngredients();
                    break;
                case "exit":
                    machineShouldBeRunning = false;
                    break;
            }
            //printAmountIngredients();
        }

//        int numberOfPossibleCups = calculateNumberOfPossibleCups(amountOfWaterInMachine, amountOfMilkInMachine, amountOfCoffeeInMachine);

//        if (numberOfCupsNeeded > numberOfPossibleCups) {
//            System.out.printf("No, I can make only %d cup(s) of coffee\n", numberOfPossibleCups );
//        } else if (numberOfCupsNeeded == numberOfPossibleCups) {
//            System.out.println("Yes, I can make that amount of coffee");
//        } else {
//            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that\n", numberOfPossibleCups - numberOfCupsNeeded);
//        }
    }

    private static void takeMoney() {
        System.out.printf("I gave you $%d", amountOfMoney);
        amountOfMoney = 0;
    }

    private static void fillMachine() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water you want to add:");
        amountOfWaterInMachine += scanner.nextInt();

        System.out.println("Write how many ml of milk you want to add:");
        amountOfMilkInMachine += scanner.nextInt();

        System.out.println("Write how many grams of coffee beans you want to add:");
        amountOfCoffeeInMachine += scanner.nextInt();

        System.out.println("Write how many disposable cups of coffee you want to add:");
        amountOfDisposableCups += scanner.nextInt();
    }

    private static void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equals("back")) {
            return;
        }

        int buyCoffee = Integer.parseInt(input);

        switch (buyCoffee){
            case 1:
                if (coffeeCanBeMade(250, 0, 16, 4)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    reduceIngredientsInMachine(250, 0, 16, 4);
                } else {
                    System.out.println(whatIsMissing(250, 0, 16, 4));
                }
                break;
            case 2:
                if (coffeeCanBeMade(350, 75, 20, 7)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    reduceIngredientsInMachine(350, 75, 20, 7);
                } else {
                    System.out.println(whatIsMissing(350, 75, 20, 7));
                }
                break;
            case 3:
                if (coffeeCanBeMade(200, 100, 12, 6)) {
                    System.out.println("I have enough resources, making you a coffee!");
                    reduceIngredientsInMachine(200, 100, 12, 6);
                } else {
                    System.out.println(whatIsMissing(200, 100, 12, 6));
                }
                break;
        }
    }

    private static String whatIsMissing(int water, int milk, int coffee, int cups) {
        if (water > amountOfWaterInMachine) return "Sorry, not enough water!";
        if (milk > amountOfMilkInMachine) return "Sorry, not enough milk!";
        if (coffee > amountOfCoffeeInMachine) return "Sorry, not enough coffee!";
        if (cups > amountOfDisposableCups) return "Sorry, not enough cups!";
        return "Something went wrong";
    }

    private static boolean coffeeCanBeMade(int water, int milk, int coffee, int cups) {
        return ((water < amountOfWaterInMachine) && (milk < amountOfMilkInMachine) && (coffee < amountOfCoffeeInMachine) && (cups < amountOfDisposableCups));
    }

    private static void reduceIngredientsInMachine(int water, int milk, int coffee, int costs) {
        amountOfWaterInMachine -= water;
        amountOfMilkInMachine -= milk;
        amountOfCoffeeInMachine -= coffee;
        amountOfDisposableCups -= 1;
        amountOfMoney += costs;
    }

    private static void printAmountIngredients() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water:\n", amountOfWaterInMachine);
        System.out.printf("%d ml of milk:\n", amountOfMilkInMachine);
        System.out.printf("%d g of coffee beans:\n", amountOfCoffeeInMachine);
        System.out.printf("%d disposable cups:\n", amountOfDisposableCups);
        System.out.printf("$%d of money:\n", amountOfMoney);
        System.out.println();
    }

    private static int calculateNumberOfPossibleCups(int amountOfWaterInMachine, int amountOfMilkInMachine, int amountOfCoffeeInMachine) {
        int numberOfPossibleCupsBasedOnWater = calculateNumberOfPossibleCupsBasedOnWater(amountOfWaterInMachine);
        int numberOfPossibleCupsBasedOnMilk = calculateNumberOfPossibleCupsBasedOnMilk(amountOfMilkInMachine);
        int numberOfPossibleCupsBasedOnCoffee = calculateNumberOfPossibleCupsBasedOnCoffee(amountOfCoffeeInMachine);

        if (numberOfPossibleCupsBasedOnCoffee < numberOfPossibleCupsBasedOnMilk && numberOfPossibleCupsBasedOnCoffee < numberOfPossibleCupsBasedOnWater) return numberOfPossibleCupsBasedOnCoffee;
        if (numberOfPossibleCupsBasedOnMilk < numberOfPossibleCupsBasedOnCoffee && numberOfPossibleCupsBasedOnMilk < numberOfPossibleCupsBasedOnWater) return numberOfPossibleCupsBasedOnMilk;
        return numberOfPossibleCupsBasedOnWater;
    }

    private static int calculateNumberOfPossibleCupsBasedOnCoffee(int amountOfCoffeeInMachine) {
        return amountOfCoffeeInMachine / 15;
    }

    private static int calculateNumberOfPossibleCupsBasedOnMilk(int amountOfMilkInMachine) {
        return amountOfMilkInMachine / 50;
    }

    private static int calculateNumberOfPossibleCupsBasedOnWater(int amountOfWaterInMachine) {
        return amountOfWaterInMachine / 200;
    }

    private static void calculateIngredients(int numberOfCupsNeeded) {
        System.out.printf("For %d cups of coffee you will need:\n", numberOfCupsNeeded);
        System.out.printf("%d ml of water:\n", numberOfCupsNeeded * 200);
        System.out.printf("%d ml of milk:\n", numberOfCupsNeeded * 50);
        System.out.printf("%d g of coffee beans:\n", numberOfCupsNeeded * 15);
    }
}
