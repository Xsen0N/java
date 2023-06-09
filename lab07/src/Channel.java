import java.util.ArrayList;
import java.util.List;

public class Channel {
    Channel channel;
    private String name;
    private List<String> subscribers;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    public void addSubscriber(String subscriber) {
        this.subscribers.add(subscriber);
    }

    public void removeSubscriber(String subscriber) {
        this.subscribers.remove(subscriber);
    }

    public List<String> getSubscribers() {
        return this.subscribers;
    }

    public int countSubscribers() {
        return this.subscribers.size();
    }

    public boolean hasSubscriber(String subscriber) {
        return this.subscribers.contains(subscriber);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

