import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.*;

import static java.lang.Math.floor;

public class Hammurabi {
    public static void main(String[] args) {
    }
    Random random = new Random();
    Scanner scanner = new Scanner (System.in);
    int bushels;
    int population;
    int acresOwned;
    int year;
    int price;

    public Hammurabi() {
    }
    public void setUpNewGame() {
        this.setPopulation(100);
        this.setAcresOwned(1000);
        this.setBushels(3000);
        this.setYear(0);
        this.setPrice(19);
    }
    public int howManyAcresToBuy(int landPurchased) {
        if ((landPurchased * newCostOfLand()) >= getBushels()) {
            System.out.println("Because you're trying to buy more than what you can afford, you get nothing and like it!");
            return 0;
        } else {
            setBushels(this.bushels - (landPurchased * newCostOfLand()));
            setAcresOwned(this.acresOwned + landPurchased);
            return landPurchased;
        }
    }
    public int howManyAcresToSell(int acreSold) {
        setAcresOwned(this.acresOwned - acreSold);
        if (this.acresOwned*100/this.population < 70) {
            //GAME OVER
            return 0;
        } else {
            setBushels(this.bushels + (acreSold * newCostOfLand()));
            return acreSold;
        }
    }
    public int howMuchBushelsToFeedPeople(int bushels) {
        if (this.bushels < bushels) bushels = this.bushels;
        return bushels;
    }

    public int howManyAcresToPlant(int acresToBePlanted, int population, int bushels) {
        while(true) {
            if (acresToBePlanted > population*10) {
                acresToBePlanted = population*10;
            } else if (acresToBePlanted > acresOwned) {
                acresToBePlanted = acresOwned;
            } else if (acresToBePlanted > bushels) {
                acresToBePlanted = bushels;
            }
            if (acresToBePlanted <= population*10 &&
            acresToBePlanted <= acresOwned &&
            acresToBePlanted <= bushels) {
                break;
            }
        }
        return acresToBePlanted;
    }
    public boolean gameOver(int population, int bushelsFedToPeople, int acresOwned) {
        if(starvationDeaths(population, bushelsFedToPeople) > getPopulation()*45/100 ||
                acresOwned < getPopulation()*7) return true;
        return false;
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

    //GETTERS AND SETTERS LIST//


    public int getBushels() {
        return bushels;
    }

    public void setBushels(int bushels) {
        this.bushels = bushels;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public int getAcresOwned() {
        return acresOwned;
    }

    public void setAcresOwned(int acresOwned) {
        this.acresOwned = acresOwned;
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}

