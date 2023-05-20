package es.upm.pproject.sokoban;

import es.upm.pproject.sokoban.model.Score;
import es.upm.pproject.sokoban.model.ScoreInterface;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ScoreTest {
	
	ScoreInterface punt=new Score();
	@Test
    public void incrementLevelScoreTest1() {
        punt.incrementLevelScore();
        assertEquals(punt.getLevelScore(),1);
        assertEquals(punt.getTotalScore(),1);
    }
	
	@Test
    public void nextLevelTest1() {
		punt.setLevelScore(1);
		punt.setTotalScore(1);
		punt.nextLevel();
        assertEquals(punt.getLevelScore(),0);
        assertEquals(punt.getTotalScore(),1);
    }
	
	@Test
    public void incrementLevelScoreTest2() {
		punt.setLevelScore(2147483646);
        punt.incrementLevelScore();
        punt.incrementLevelScore();
    }
	@Test
    public void decrementLevelScoreTest1() {
		punt.setLevelScore(1);
		punt.setTotalScore(1);
		punt.decrementLevelScore();
        assertEquals(punt.getLevelScore(),0);
        assertEquals(punt.getTotalScore(),0);
    }
	
	@Test
    public void decrementLevelScoreTest2() {
		punt.setLevelScore(0);
		punt.setTotalScore(0);
		punt.decrementLevelScore();
        assertEquals(punt.getLevelScore(),0);
        assertEquals(punt.getTotalScore(),0);
    }
}
