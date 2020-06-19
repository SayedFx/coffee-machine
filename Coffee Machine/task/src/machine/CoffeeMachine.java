package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;
    private state machineState;

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            machine.takeInput(scanner.next());
        }
    }

    public CoffeeMachine() {
        water = 400;
        milk = 540;
        beans = 120;
        cups = 9;
        money = 550;
        machineState = state.READY;
        printMainMenu();
    }

    public void takeInput(String input) {
        switch (machineState) {
            case READY:
                processOption(input);
                break;
            case DISPENSE:
                processBuy(input);
                machineState = state.READY;
                printMainMenu();
                break;
            case REFILL_WATER:
                int waterIn = Integer.parseInt(input);
                water += waterIn;
                machineState = state.REFILL_MILK;
                printMilkRefillMenu();
                break;
            case REFILL_MILK:
                int milkIn = Integer.parseInt(input);
                milk += milkIn;
                machineState = state.REFILL_BEANS;
                printBeansRefillMenu();
                break;
            case REFILL_BEANS:
                int beansIn = Integer.parseInt(input);
                beans += beansIn;
                machineState = state.REFILL_CUPS;
                printCupsRefillMenu();
                break;
            case REFILL_CUPS:
                int cupsIn = Integer.parseInt(input);
                cups += cupsIn;
                machineState = state.READY;
                printMainMenu();
                break;
        }
    }

    private void printCupsRefillMenu() {
        System.out.println("Write how many disposable cups of coffee do you want to add:");
    }

    private void printBeansRefillMenu() {
        System.out.println("Write how many grams of coffee beans do you want to add:");
    }

    private void printMilkRefillMenu() {
        System.out.println("Write how many ml of milk do you want to add:");
    }

    private void processOption(String input) {

        switch (input) {
            case "buy":
                printBuyMenu();
                machineState = state.DISPENSE;
                break;
            case "fill":
                printWaterRefillMenu();
                machineState = state.REFILL_WATER;
                break;
            case "take":
                System.out.println("I gave you $" + money);
                money = 0;
                printMainMenu();
                break;
            case "remaining":
                printState();
                printMainMenu();
                break;
            case "exit":
                System.exit(0);


        }
    }

    private void printWaterRefillMenu() {
        System.out.println("Write how many ml of water do you want to add:");
    }


    private void printBuyMenu() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
    }

    private void processBuy(String input) {

        if (!haveEnoughResources(input)) {
            System.out.println("Sorry, not enough water!");
            return;
        }
        System.out.println("I have enough resources, making you a coffee!");
        switch (input) {
            case "back":
                break;
            case "1":
                water -= 250;
                beans -= 16;
                cups -= 1;
                money += 4;
                break;
            case "2":
                water -= 350;
                beans -= 20;
                cups -= 1;
                milk -= 75;
                money += 7;
                break;
            case "3":
                water -= 200;
                beans -= 12;
                cups -= 1;
                milk -= 100;
                money += 6;
                break;
        }
    }


    private boolean haveEnoughResources(String input) {
        switch (input) {
            case "back":
                return true;
            case "1":
                return water >= 250 && beans >= 16 && cups >= 1;
            case "2":
                return water >= 350 && beans >= 20 && cups >= 1 && milk >= 75;
            case "3":
                return water >= 200 && beans >= 12 && cups >= 1 && milk >= 100;
        }
        return false;
    }

    private void printMainMenu() {
        System.out.println("Write action (buy, fill, take, remaining, exit):\n");
    }

    private void printState() {
        System.out.println(getState() + "\n");
    }


    private String getState() {
        return "The coffee machine has:" +
                "\n" + water + " of water" +
                "\n" + milk + " of milk" +
                "\n" + beans + " of coffee beans" +
                "\n" + cups + " of disposable cups" +
                "\n$" + money + " of money";
    }

    enum state {
        READY, DISPENSE, REFILL_WATER, REFILL_MILK, REFILL_CUPS, REFILL_BEANS
    }
}
