import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class statustest {

    @Parameters({"state1"})
    @Test(groups = {"state"})
    void on(String state1 ){
        String on = "switch_on";
        Assert.assertEquals(on, state1, "States are equal");
    }
    @Parameters({"state3"})
    @Test(groups = {"state"}, dependsOnMethods = {"on"})
    void inprogress(String state3 ){
        System.out.println(state3);
    }
    @Parameters({ "state5"})
    @Test(groups = {"state"},dependsOnMethods = {"on", "inprogress"})
    void switch1(String state5 ){
        System.out.println(state5);
    }
    @Parameters({"state3"})
    @Test(groups = {"state"},dependsOnMethods = {"inprogress"})
    void waiting(String state3 ){
        String waiting = "waiting";
        Assert.assertNotSame(waiting, state3, "States are equal");
    }
    @Parameters({"state2"})
    @Test(groups = {"state"}, dependsOnMethods = {"waiting"}, alwaysRun = true)
    void off(String state2 ){
        System.out.println(state2);
    }

}
