import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class HammurabiTest {
    
    Hammurabi ham;

    boolean about(double expected, double actual) {
        return actual > 0.90 * expected && actual < 1.10 * expected;
    }

    @Before
    public void setUp() throws Exception {
        ham = new Hammurabi();
    }
    @Test
    public void HowManyAcresToBuyTest1() {
        //If price of acres are at maximum of 24
        ham.bushels = 1000;
        ham.acresOwned = 100;
        int acresBought = ham.howManyAcresToBuy(40);
        Assert.assertEquals(40, acresBought);
    }
    @Test
    public void HowManyAcresToBuyTest2() {
        //If price of acres are at minimum of 17
        ham.bushels = 679;
        ham.acresOwned = 100;
        int acresBought = ham.howManyAcresToBuy(40);
        Assert.assertEquals(0, acresBought);
    }
    @Test
    public void HowManyAcresToBuyTest3() {
        //If price of acres are random
        ham.bushels = 800;
        ham.acresOwned = 100;
        int acresBought = ham.howManyAcresToBuy(40);
        Assert.assertTrue("Acres bought is " + acresBought,
                acresBought == 40 || acresBought == 0);
    }
    @Test
    public void HowManyAcresToBuyTest4() {
        //Updated acres Owned
        ham.bushels = 800;
        ham.acresOwned = 100;
        ham.howManyAcresToBuy(40);
        Assert.assertTrue("Acres bought is " + ham.acresOwned,
                ham.acresOwned == 140 || ham.acresOwned == 100);
    }
    @Test
    public void HowManyAcresToSellTest1() {
        //If acres owned after sold is more than 70% of the population
        ham.acresOwned = 100;
        ham.population = 100;
        int acresSold = ham.howManyAcresToSell(20);
        Assert.assertEquals(20, acresSold);
    }
    @Test
    public void HowManyAcresToSellTest2() {
        //If acres owned after sold is less than 70% of the population
        //Game over scenario
        ham.acresOwned = 89;
        ham.population = 100;
        int acresSold = ham.howManyAcresToSell(20);
        Assert.assertEquals(0, acresSold);
    }
    @Test
    public void howManyAcresToSellTest3() {
        //Bushels being returned
        ham.bushels = 1000;
        ham.population = 100;
        ham.acresOwned = 100;
        ham.howManyAcresToSell(20);
        Assert.assertTrue("Total bushels after sell: " + ham.bushels
        , ham.bushels <= 1460 && ham.bushels >= 1340);
    }
    @Test
    public void howMuchBushelsToFeedPeopleTest1() {
        //If bushels fed to people are less than stock bushels
        ham.bushels = 3000;
        ham.population = 1000;
        ham.howMuchBushelsToFeedPeople(2000);
        Assert.assertEquals(2000, ham.howMuchBushelsToFeedPeople(2000));
    }
    @Test
    public void howMuchBushelsToFeedPeopleTest2() {
        //If bushels fed to people are more than stock bushels
        ham.bushels = 1500;
        ham.population = 1000;
        ham.howMuchBushelsToFeedPeople(2000);
        Assert.assertEquals(1500, ham.howMuchBushelsToFeedPeople(2000));
    }
    @Test
    public void howManyAcresToPlantTest1() {
        //If acres to be planted is more than acres owned
        ham.acresOwned = 1000;
        Assert.assertEquals(1000, ham.howManyAcresToPlant(1001, 999, 1001));
    }
    @Test
    public void howManyAcresToPlantTest2() {
        //If acres to be planted is more than population * 10
        ham.acresOwned = 1000;
        Assert.assertEquals(900, ham.howManyAcresToPlant(1000, 90, 1001));
    }
    @Test
    public void howManyAcresToPlantTest3() {
        //If acres to be planted is more than bushels
        ham.acresOwned = 1100;
        Assert.assertEquals(900, ham.howManyAcresToPlant(1000, 100, 900));
    }
    @Test
    public void howManyAcresToPlantTest4() {
        //If acres to be planted is more than bushels and bushels is more than population
        ham.acresOwned = 1100;
        Assert.assertEquals(650, ham.howManyAcresToPlant(1000, 65, 900));
    }
    @Test
    public void howManyAcresToPlantTest5() {
        //If acres to be planted is more than population and population is more than acres owned
        ham.acresOwned = 500;
        Assert.assertEquals(500, ham.howManyAcresToPlant(1000, 65, 1000));
    }
    @Test
    public void howManyAcresToPlantTest6() {
        //If acres to be planted is more than acres owned and acres owned is more than bushels
        ham.acresOwned = 800;
        Assert.assertEquals(500, ham.howManyAcresToPlant(1000, 65, 500));
    }
    @Test
    public void howManyAcresToPlantTest7() {
        //If everything is lower than acres to be planted
        ham.acresOwned = 900;
        Assert.assertEquals(800, ham.howManyAcresToPlant(1000, 90, 800));
    }
    @Test
    public void gameOverTest1() {
        //If population starved more than 45%
        ham.setPopulation(100);
        ham.setBushels(2000);
        ham.setAcresOwned(1000);
        Assert.assertEquals(true, ham.gameOver(ham.getPopulation(), 1099, ham.getAcresOwned()));
    }
    @Test
    public void gameOverTest2() {
        //If population starved exactly 45%
        ham.setPopulation(100);
        ham.setBushels(2000);
        ham.setAcresOwned(1000);
        Assert.assertEquals(false, ham.gameOver(ham.getPopulation(), 1100, ham.getAcresOwned()));
    }
    @Test
    public void gameOverTest3() {
        //If acres are less than 7 per population
        ham.setPopulation(100);
        ham.setBushels(2000);
        ham.setAcresOwned(699);
        Assert.assertEquals(true, ham.gameOver(ham.getPopulation(), 1100, ham.getAcresOwned()));
    }
    @Test
    public void gameOverTest4() {
        //If acres are exactly 7 per population
        ham.setPopulation(100);
        ham.setBushels(2000);
        ham.setAcresOwned(700);
        Assert.assertEquals(false, ham.gameOver(ham.getPopulation(), 1100, ham.getAcresOwned()));
    }
    @Test
    public final void testPlagueDeaths1() {
        int number_of_plagues = 0;
        for (int i = 0; i < 10000; i++) {
            int deaths = ham.plagueDeaths(100);
            if (deaths > 0) {
                number_of_plagues += 1;
            }
        }
        int percentPlagues = number_of_plagues / 100;
        assertTrue("Number of plagues is about " + percentPlagues + ", not about 15%.",
                   about(1500, number_of_plagues));
    }

    @Test
    public final void testPlagueDeaths2() {
        int deaths = 0;
        for (int i = 0; i < 10000; i++) {
            deaths = ham.plagueDeaths(100);
            if (deaths > 0) break;
        }
        assertEquals("In a plague, " + deaths + "% of your people die, not 50%.",
                     50, deaths);
    }
    
    @Test
    public final void testStarvationDeaths() {
        int deaths = ham.starvationDeaths(100, 1639);
        assertEquals("Wrong number of starvations deaths.", 19, deaths);
        deaths = ham.starvationDeaths(100, 2500);
        if (deaths < 0) {
            fail("You starved a negative number of people!");
        }
    }

    @Test
    public final void testUprising() {
        assertTrue("Should have had an uprising!", ham.uprising(1000, 451));
        assertFalse("Should not have had an uprising!", ham.uprising(1000, 449));
    }

    @Test
    public final void testImmigrants() {
        int imm = ham.immigrants(10, 1200, 500);
        assertEquals("Wrong number of immigrants.", 25, imm);
    }

    @Test
    public final void testHarvest() {
        int[] yield = new int[7];
        for (int i = 0; i < 1000; i++) {
            int harvest = ham.harvest(1);
            assertTrue("Illegal harvest per acre: " + harvest, harvest > 0 && harvest <= 6);
            yield[harvest] += 1;
        }
        for (int j = 1; j <= 6; j++) {
            assertTrue("You never have a yield of " + j + " bushels per acre.", yield[j] > 0);
        }
    }

    @Test
    public final void testGrainEatenByRats1() {
        int infestations = 0;
        for (int i = 0; i < 1000; i++) {
            int eaten = ham.grainEatenByRats(100);
            if (eaten > 0) {
                infestations += 1;
            }
        }
        int percentInfestations = infestations / 100;
        assertTrue("Number of rat infestations is about " + percentInfestations + 
                   ", not about 40%.", about(400, infestations));
    }

    @Test
    public final void testGrainEatenByRats2() {
        int percent = 0;
        int[] counts = new int[31];
        for (int i = 0; i < 10000; i++) {
            percent = ham.grainEatenByRats(100);
            if (percent == 0) continue;
            counts[percent] += 1;
            assertTrue("Rats ate " + percent + "% of your grain, not 10% to 30%.",
                       percent >= 10 && percent <= 30);
        }
        for (int j = 11; j < 30; j++) {
            assertTrue("Rats never ate " + j + "% of your grain.", counts[j] > 0);
        }
    }

    @Test
    public final void testNewCostOfLand() {
        int[] cost = new int[24];
        for (int i = 0; i < 1000; i++) {
            int price = ham.newCostOfLand();
            assertTrue("Illegal cost of land: " + price, price >= 17 && price <= 23);
            cost[price] += 1;
        }
        for (int j = 17; j <= 23; j++) {
            assertTrue("You never have a land cost of " + j + " bushels per acre.", cost[j] > 0);
        }
    }

}

