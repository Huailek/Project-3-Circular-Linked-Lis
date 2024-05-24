import java.util.Objects;

public class Player {
    String name;
    int score;
    public Player(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }

    public int getScore(){
        return score;
    }

    public void incrementScore(int increaseBy){
        this.score += increaseBy;
    }

    public boolean equals(Object other) {
        if (other == null || !(other instanceof Player)) {
            return false;
        } else if (other == this) {
            return true;
        } else {
            Player otherStud = (Player) other;
            return Objects.equals(name,otherStud.name);
        }
    }
}
