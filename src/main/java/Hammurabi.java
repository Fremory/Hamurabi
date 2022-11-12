import java.util.*;

public class Hammurabi {
    public static void main(String[] args) {

    }
    Random random = new Random();
    Scanner scanner = new Scanner (System.in);

    public Hammurabi() {

    }

    public int askHowManyAcresToBuy(int price, int bushels){
        newCostOfLand() = price;
        System.out.println("This year's land costs " +  price + " bushels. Insert how many lands you want to buy");
        int options = scanner.nextInt();
        return bushels - (options * price);
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
        if(population - (int) Math.floor(bushelsFedToPeople/20) < 0) return 0;
        return population - (int) Math.floor(bushelsFedToPeople/20);
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

