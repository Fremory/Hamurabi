import java.util.*;

import static java.lang.Math.floor;

public class Hammurabi {
    public static void main(String[] args) {

    }
    Random random = new Random();
    Scanner scanner = new Scanner (System.in);
    int options;
    int bushels;
    int population;
    int landsOwned;

    public Hammurabi() {

    }

    public int askHowManyAcresToBuy(int price, int bushels){
        int landPurchased = getNumber("This year's price per land is " + price + " bushels. How many do you want to buy?");
        this.bushels -= landPurchased * price;
        while ((landPurchased * price) > this.bushels) {
            landPurchased = getNumber("You wish you can pull bushels out of thin air. Try again!");
        }
        return landPurchased;
    }

    public int askHowManyAcresToSell(int acresOwned) {
        return 0;
    }

    public int askHowMuchGrainToFeedPeople(int bushels) {
        return 0;
    }

    public int askHowManyAcresToPlant(int acresOwned, int population, int bushels) {
        return 0;
    }

    public void printSummary() {
        //String
    }

    public void finalSummary() {
        //String
    }

    public void playGame() {
        //int
    }

    public int plagueDeaths(int population) {
        if(random.nextInt(101) < 15) return population/2;
        return 0;
    }

    public int starvationDeaths(int population, int bushelsFedToPeople) {
        //returning the number of deaths
        if(population - (int) floor(bushelsFedToPeople/20) < 0) return 0;
        return population - (int) floor(bushelsFedToPeople/20);
    }

    public boolean uprising(int population, int howManyPeopleStarved) {
        if ((double) howManyPeopleStarved / population > 0.45) return true;
        return false;
    }

    public int immigrants (int population, int acresOwned, int grainInStorage) {
        return (20 * acresOwned + grainInStorage) / (100 * population) + 1;
    }

    public int harvest (int acres) {
        //Returning for the number of bushels harvested per acre
        return random.nextInt(6)+1;
    }

    public int grainEatenByRats (int bushels) {
        if(random.nextInt(100) < 40) return random.nextInt(21)+10;
        return 0;
    }

    public int newCostOfLand() {
        //Returning random integer from 0 -> 7, then add 17
        return random.nextInt(7)+17;
    }

    int getNumber(String message) {
        while (true) {
            System.out.print(message);
            try {
                return scanner.nextInt();
            }
            catch (InputMismatchException e) {
                System.out.println("\"" + scanner.next() + "\" isn't a number!");
            }
        }
    }
}

