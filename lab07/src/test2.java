import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.*;


public class test2 {
    private Channel channel;
    @BeforeMethod
    public void setUp() {
        channel = new Channel("TestChannel");

    }
    @Parameters ({"channel_name"})
    @Test(dataProvider = "channel", dataProviderClass = TestNGTest.class, groups = "show", priority = 0)
    public void testAdd(String name1, Progstate state, float duration)   throws Exception {
        System.out.println("Program name: "+name1+" It is a " + state.toString() + "and duration is " + duration);
    }
    @Test(groups = "changing",timeOut = 5000, priority = 1)
    public void testAddNewSubscriber() throws InterruptedException {
        channel.addSubscriber("subscriber1");
        Thread.sleep(4000); // Добавляем задержку на 4 секунд, чтобы  не вызвать превышение времени ожидания
        channel.addSubscriber("subscriber2");
        List<String> subscribers = channel.getSubscribers();
        assertEquals(2, subscribers.size());
        assertTrue(subscribers.contains("subscriber1"));
        assertTrue(subscribers.contains("subscriber2"));
        System.out.println("testAddNewSubscriber");
    }

    @Test(groups = "changing")
    public void testRemoveSubscriber() {
        channel.addSubscriber("subscriber1");
        channel.addSubscriber("subscriber2");
        channel.removeSubscriber("subscriber2");
        List<String> subscribers = channel.getSubscribers();
        assertEquals(1, subscribers.size());
        assertTrue(subscribers.contains("subscriber1"));
        assertFalse(subscribers.contains("subscriber2"));
        System.out.println("testRemoveSubscriber");
    }

    @Test(groups = "show")
    public void testCountSubscribers() {
        channel.addSubscriber("subscriber1");
        channel.addSubscriber("subscriber2");
        int count = channel.countSubscribers();
        assertEquals(2, count);
        System.out.println("testCountSubscribers");
    }

    @Test(groups = "show", priority = 4)
    public void testHasSubscriber() {
        channel.addSubscriber("subscriber1");
        channel.addSubscriber("subscriber2");
        assertTrue(channel.hasSubscriber("subscriber1"));
        assertFalse(channel.hasSubscriber("subscriber3"));
        System.out.println("testHasSubscriber");
    }

    @Test(enabled = false, groups = "show")
    public void testGetName() {
        assertEquals("TestChannel", channel.getName());
        System.out.println("testGetName");
    }

    @Test(groups = "show", priority = 2)
    public void testSetName() {
        channel.setName("NewChannelName");
        assertEquals("NewChannelName", channel.getName());
        System.out.println("testSetName");
    }


    @BeforeMethod
    void beforemethod(){
        System.out.println("New Test---------");
    }

    @AfterMethod
    void afmethod(){
        System.out.println("- - - - - - - - - - - - - - - - - ");
    }
    @BeforeClass
    void beforeclass(){
        System.out.println("Channel Testing started");
    }
    @AfterClass
    void afterfclass(){
        System.out.println("Channel Testing ended");
    }

    @AfterTest
    void aftftest(){
        System.out.println("After test");
    }
    @BeforeSuite
    void befsuite(){
        System.out.println("Before suite");
    }
    @AfterSuite
    void aftsuite(){
        System.out.println("After suite");
    }
}
