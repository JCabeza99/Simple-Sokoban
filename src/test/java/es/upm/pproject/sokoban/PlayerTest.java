package es.upm.pproject.sokoban;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import es.upm.pproject.sokoban.model.Player;
import es.upm.pproject.sokoban.model.PlayerInterface;
class PlayerTest {
	PlayerInterface player=new Player(0,0);
	@Test
    void incrementLevelScoreTest1() {
        player.getScore().incrementLevelScore();
        assertEquals(1,player.getScore().getLevelScore());
        assertEquals(1,player.getScore().getTotalScore());
    }
	
	@Test
    void nextLevelTest1() {
		player.getScore().setLevelScore(1);
		player.getScore().setTotalScore(1);
		player.getScore().nextLevel();
        assertEquals(0,player.getScore().getLevelScore());
        assertEquals(1,player.getScore().getTotalScore());
    }
	
	@Test
    void decrementLevelScoreTest1() {
		player.getScore().setLevelScore(1);
		player.getScore().setTotalScore(1);
		player.getScore().decrementLevelScore();
        assertEquals(0,player.getScore().getLevelScore());
        assertEquals(0,player.getScore().getTotalScore());
    }
	
	@Test
    void decrementLevelScoreTest2() {
		player.getScore().setLevelScore(0);
		player.getScore().setTotalScore(0);
		player.getScore().decrementLevelScore();
        assertEquals(0,player.getScore().getLevelScore());
        assertEquals(0,player.getScore().getTotalScore());
    }
}
