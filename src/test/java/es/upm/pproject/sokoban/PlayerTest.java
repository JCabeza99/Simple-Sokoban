package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Player;
import es.upm.pproject.sokoban.model.PlayerInterface;
public class PlayerTest {
	PlayerInterface player=new Player(0,0);
	@Test
    public void incrementLevelScoreTest1() {
        player.getScore().incrementLevelScore();
        assertEquals(player.getScore().getLevelScore(),1);
        assertEquals(player.getScore().getTotalScore(),1);
    }
	
	@Test
    public void nextLevelTest1() {
		player.getScore().setLevelScore(1);
		player.getScore().setTotalScore(1);
		player.getScore().nextLevel();
        assertEquals(player.getScore().getLevelScore(),0);
        assertEquals(player.getScore().getTotalScore(),1);
    }
	
	@Test
    public void incrementLevelScoreTest2() {
		player.getScore().setLevelScore(2147483646);
        player.getScore().incrementLevelScore();
        player.getScore().incrementLevelScore();
    }
	@Test
    public void decrementLevelScoreTest1() {
		player.getScore().setLevelScore(1);
		player.getScore().setTotalScore(1);
		player.getScore().decrementLevelScore();
        assertEquals(player.getScore().getLevelScore(),0);
        assertEquals(player.getScore().getTotalScore(),0);
    }
	
	@Test
    public void decrementLevelScoreTest2() {
		player.getScore().setLevelScore(0);
		player.getScore().setTotalScore(0);
		player.getScore().decrementLevelScore();
        assertEquals(player.getScore().getLevelScore(),0);
        assertEquals(player.getScore().getTotalScore(),0);
    }
}
