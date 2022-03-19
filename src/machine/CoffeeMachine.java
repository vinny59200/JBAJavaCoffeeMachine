package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static int water = 400;
    static int waterFilled = 0;
    static boolean isWaterPrompted = false;
    static boolean isWaterFormFilled = false;

    static int milk = 540;
    static int milkFilled = 0;
    static boolean isMilkPrompted = false;
    static boolean isMilkFormFilled = false;

    static int coffee = 120;
    static int coffeeFilled = 0;
    static boolean isCoffeePrompted = false;
    static boolean isCoffeeFormFilled = false;

    static int cups = 9;
    static int cupsFilled = 0;
    static boolean isCupsPrompted = false;
    static boolean isCupsFormFilled = false;

    static int money = 550;

    static String previousCommand = "";

    public static void main(String[] args) {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        execute:
        for (; ; ) {
            String command = new Scanner(System.in).next();
            if ("back".equals(command)) {
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            }
            /*
            ______
            | ___ \
            | |_/ /_   _ _   _
            | ___ \ | | | | | |
            | |_/ / |_| | |_| |
            \____/ \__,_|\__, |
                          __/ |
                         |___/
             */
            if ("buy".equals(command)) {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                previousCommand = "buy";
                continue execute;
            }
            if ("buy".equals(previousCommand) && "1".equals(command)) {
                CoffeeMachine.serve(1);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            } else if ("buy".equals(previousCommand) && "2".equals(command)) {
                CoffeeMachine.serve(2);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            } else if ("buy".equals(previousCommand) && "3".equals(command)) {
                CoffeeMachine.serve(3);
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            }
            /*
            ______ _ _ _
            |  ___(_) | |
            | |_   _| | |
            |  _| | | | |
            | |   | | | |
            \_|   |_|_|_|
             */
            if ("fill".equals(command)) {
                previousCommand = "fill";

                fill:
                for (; ; ) {
                    String fillCommand = "";
                    if (isWaterFormFilled && isMilkFormFilled && isCoffeeFormFilled && isCupsFormFilled) {
                        CoffeeMachine.fill(waterFilled, milkFilled, coffeeFilled, cupsFilled);
                        isWaterPrompted = false;
                        isWaterFormFilled = false;
                        isMilkPrompted = false;
                        isMilkFormFilled = false;
                        isCoffeePrompted = false;
                        isCoffeeFormFilled = false;
                        isCupsPrompted = false;
                        isCupsFormFilled = false;
                        waterFilled = 0;
                        milkFilled = 0;
                        coffeeFilled = 0;
                        cupsFilled = 0;
                        continue execute;
                    }
                    if ("fill".equals(previousCommand) && !isWaterPrompted) {
                        System.out.println("Write how many ml of water you want to add:");
                        fillCommand = new Scanner(System.in).next();
                        isWaterPrompted = true;
                        waterFilled = Integer.valueOf(fillCommand);
                        isWaterFormFilled = true;
                        continue fill;
                    } else if ("fill".equals(previousCommand) && !isMilkPrompted) {
                        System.out.println("Write how many ml of milk you want to add:");
                        fillCommand = new Scanner(System.in).next();
                        isMilkPrompted = true;
                        milkFilled = Integer.valueOf(fillCommand);
                        isMilkFormFilled = true;
                        continue fill;
                    } else if ("fill".equals(previousCommand) && !isCoffeePrompted) {
                        System.out.println("Write how many grams of coffee beans you want to add:");
                        fillCommand = new Scanner(System.in).next();
                        isCoffeePrompted = true;
                        coffeeFilled = Integer.valueOf(fillCommand);
                        isCoffeeFormFilled = true;
                        continue fill;
                    } else if ("fill".equals(previousCommand) && !isCupsPrompted) {
                        System.out.println("Write how many disposable cups of coffee you want to add:");
                        fillCommand = new Scanner(System.in).next();
                        isCupsPrompted = true;
                        cupsFilled = Integer.valueOf(fillCommand);
                        isCupsFormFilled = true;
                        
                        continue fill;
                    }
                }
            }
            /*
             _____ _   _
            |  _  | | | |
            | | | | |_| |__   ___ _ __
            | | | | __| '_ \ / _ \ '__|
            \ \_/ / |_| | | |  __/ |
             \___/ \__|_| |_|\___|_|
             */
            if ("take".equals(command)) {
                take();
                previousCommand = "take";
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            } else if ("remaining".equals(command)) {
                display();
                previousCommand = "remaining";
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                continue execute;
            } else if ("exit".equals(command)) {
                break execute;
            }
        }
        ;
    }

    static void fill(int water, int milk, int coffee, int cups) {
        CoffeeMachine.water += water;
        CoffeeMachine.milk += milk;
        CoffeeMachine.coffee += coffee;
        CoffeeMachine.cups += cups;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }

    static void take() {
        System.out.println("I gave you $" + CoffeeMachine.money);
        CoffeeMachine.money = 0;
    }

    static void serve(int coffeeId) {
        switch (coffeeId) {
            case 1:
                fetch(250, 0, 16, 4);
                break;
            case 2:
                fetch(350, 75, 20, 7);
                break;
            case 3:
                fetch(200, 100, 12, 6);
                break;
        }
    }

    static void fetch(int water, int milk, int coffee, int money) {
        if (CoffeeMachine.water < water) {
            System.out.println("Sorry, not enough water!");
        } else if (CoffeeMachine.milk < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (CoffeeMachine.coffee < coffee) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (CoffeeMachine.cups < 1) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            CoffeeMachine.water -= water;
            CoffeeMachine.milk -= milk;
            CoffeeMachine.coffee -= coffee;
            CoffeeMachine.cups -= 1;
            CoffeeMachine.money += money;
        }
    }

    static void display() {
        System.out.println("The coffee machine has:");
        System.out.println(String.format("%s ml of water", CoffeeMachine.water));
        System.out.println(String.format("%s ml of milk", CoffeeMachine.milk));
        System.out.println(String.format("%s g of coffee beans", CoffeeMachine.coffee));
        System.out.println(String.format("%s disposable cups", CoffeeMachine.cups));
        System.out.println(String.format("%s of money", CoffeeMachine.money));
    }
}


