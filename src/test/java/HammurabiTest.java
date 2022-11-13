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
    public void HowManyLandsToBuyTest1() {
        //If price of lands are at maximum of 24
        ham.bushels = 1000;
        ham.landsOwned = 100;
        int landsBought = ham.howManyLandsToBuy(40);
        Assert.assertEquals(40, landsBought);
    }
    @Test
    public void HowManyLandsToBuyTest2() {
        //If price of lands are at minimum of 17
        ham.bushels = 679;
        ham.landsOwned = 100;
        int landsBought = ham.howManyLandsToBuy(40);
        Assert.assertEquals(0, landsBought);
    }
    @Test
    public void HowManyLandsToBuyTest3() {
        //If price of lands are random
        ham.bushels = 800;
        ham.landsOwned = 100;
        int landsBought = ham.howManyLandsToBuy(40);
        Assert.assertTrue("Lands bought is " + landsBought,
                landsBought == 40 || landsBought == 0);
    }
    @Test
    public void HowManyLandsToBuyTest4() {
        //Updated lands Owned
        ham.bushels = 800;
        ham.landsOwned = 100;
        ham.howManyLandsToBuy(40);
        Assert.assertTrue("Lands bought is " + ham.landsOwned,
                ham.landsOwned == 140 || ham.landsOwned == 100);
    }
    @Test
    public void HowManyLandsToSellTest1() {
        //If lands owned after sold is more than 70% of the population
        ham.landsOwned = 100;
        ham.population = 100;
        int landsSold = ham.howManyLandsToSell(20);
        Assert.assertEquals(20, landsSold);
    }
    @Test
    public void HowManyLandsToSellTest2() {
        //If lands owned after sold is less than 70% of the population
        //Game over scenario
        ham.landsOwned = 89;
        ham.population = 100;
        int landsSold = ham.howManyLandsToSell(20);
        Assert.assertEquals(0, landsSold);
    }
    @Test
    public void howManyLandsToSellTest3() {
        //Bushels being returned
        ham.bushels = 1000;
        ham.population = 100;
        ham.landsOwned = 100;
        ham.howManyLandsToSell(20);
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
    public void howManyLandsToPlantTest1() {
        //If lands to be planted is more than lands owned
        ham.landsOwned = 1000;
        Assert.assertEquals(0, ham.howManyLandsToPlant(1001, 999, 1001));
    }
    @Test
    public void howManyLandsToPlantTest2() {
        //If lands to be planted is more than population * 10
        ham.landsOwned = 1000;
        Assert.assertEquals(900, ham.howManyLandsToPlant(1000, 90, 1001));
    }
    @Test
    public void howManyLandsToPlantTest3() {
        //If lands to be planted is more than bushels
        ham.landsOwned = 1100;
        Assert.assertEquals(900, ham.howManyLandsToPlant(1000, 10, 900));
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
