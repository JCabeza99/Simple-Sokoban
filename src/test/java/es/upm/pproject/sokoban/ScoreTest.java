package es.upm.pproject.sokoban;

import es.upm.pproject.sokoban.model.Score;
import es.upm.pproject.sokoban.model.ScoreInterface;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

 class ScoreTest {
	
	ScoreInterface punt=new Score();
	@Test
	void incrementLevelScoreTest1() {
        punt.incrementLevelScore();
        assertEquals(1,punt.getLevelScore());
        assertEquals(1,punt.getTotalScore());
    }
	
	@Test
    void nextLevelTest1() {
		punt.setLevelScore(1);
		punt.setTotalScore(1);
		punt.nextLevel();
		assertEquals(0,punt.getLevelScore());
        assertEquals(1,punt.getTotalScore());
    }
	
	@Test
    void decrementLevelScoreTest1() {
		punt.setLevelScore(1);
		punt.setTotalScore(1);
		punt.decrementLevelScore();
		assertEquals(0,punt.getLevelScore());
        assertEquals(0,punt.getTotalScore());
    }
	
	@Test
    void decrementLevelScoreTest2() {
		punt.setLevelScore(0);
		punt.setTotalScore(0);
		punt.decrementLevelScore();
        assertEquals(0,punt.getLevelScore());
        assertEquals(0,punt.getTotalScore());
    }
}
