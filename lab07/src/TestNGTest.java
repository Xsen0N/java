import org.testng.annotations.*;

public class TestNGTest {
    @DataProvider(name = "channel")
    public Object[][] createdata1(){
        Object[][] cats = {{ "Morning News", Progstate.News, 14.5F}};
        return cats;
    }
    @DataProvider(name = "states")
    public Object[][] createstate(){
        Object[][] state = {{ "switch_on"}, { "switch_off"},{ "watching"},{ "waiting"},{ "switch"}};
        return state;
    }
}
