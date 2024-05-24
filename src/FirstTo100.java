import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;
import javax.swing.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Random;


public class FirstTo100 {
    private static Clip clip;
    public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        CircularLinkedList<Player> myPlayer = new CircularLinkedList<>();
        Player james = new Player("James");
        Player john = new Player("John");
        Player daniel = new Player("Daniel");
        Player rachel = new Player("Rachel");
        myPlayer.add(james);
        myPlayer.add(john);
        myPlayer.add(daniel);
        myPlayer.add(rachel);

        // from a wave File
        File soundFile = new File("src/Sound/gameStarting.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
        clip = AudioSystem.getClip();
        clip.open(audioIn);

        Iterator<Player> iterate = myPlayer.iterator();
        Random random = new Random();
        int maxScore = 100;
        int firstDice = 0;
        int secondDice = 0;
        int highesScore = 0;
        Player tempPlayer = iterate.next();
        boolean foundWinner = false;
        while (!foundWinner) {
            if (tempPlayer.getName().equals(myPlayer.get(0).getName())) {
                System.out.println();
                System.out.println("New Round Starting");
                play();
                Thread.sleep(clip.getMicrosecondLength() / 8000);
            }
            firstDice = random.nextInt(6) + 1;
            secondDice = random.nextInt(6) + 1;
            tempPlayer.incrementScore(firstDice + secondDice);
            if (tempPlayer.getScore() > highesScore) {
                System.out.println(tempPlayer.getName() + " rolls a " + firstDice + " and " + secondDice + " now totaling " + tempPlayer.getScore() + "...new high player!");
                highesScore = tempPlayer.getScore();
            }else {
                System.out.println(tempPlayer.getName() + " rolls a " + firstDice + " and " + secondDice + " now totaling " + tempPlayer.getScore());
            }
            if (tempPlayer.getScore() >= 100) {
                System.out.println();
                System.out.println("The winner is " +  tempPlayer.getName() + " with a score of " + tempPlayer.getScore());
                foundWinner = true;
            }
            tempPlayer = iterate.next();
            Thread.sleep(500);
        }
        }

    private static void play() {
        if (clip != null) {
            clip.setFramePosition(0); // Rewind to the beginning
            clip.start();
        }
    }
}
