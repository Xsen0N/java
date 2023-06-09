import java.util.ArrayList;

public class Channel {
    public ArrayList<Program> prog;
    public Channel(ArrayList<Program> prog) {
        this.prog = prog;
    }
    public ArrayList<Program> getProg() {
        return prog;
    }

    public void setProg(ArrayList<Program> prog) {
        this.prog = prog;
    }

    public Channel() {
    }
    public void add(ArrayList<Program> prog){
        this.prog = prog;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "prog=" + prog +
                '}';
    }
}

