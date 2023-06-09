public class Program  {

    public  double duration;
    public String name;
    public int times;
    public Progstate state;
    private String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Program(Progstate state, String name, float duration, int times){
        this.state = state;
        this.name = name;
        this.duration = duration;
        this.times = times;
    }

    public Progstate getState() {
        return state;
    }

    public void setState(Progstate state) {
        this.state = state;
    }


    public String getName() {
        return name;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Program() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

