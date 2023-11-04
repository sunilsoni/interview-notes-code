package com.interview.notes.code.months.nov23.test2;// BonusDropEngineTest.java

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BonusDropEngineTest {

    @Test
    public void testCalculateBonusForLeftChoice() {
        IBonusDropEngine engine = new BonusDropEngineImpl();
        BonusPlayResult result = engine.calculateBonus(PlayerChoice.LEFT);
        assertNotNull(result);
    }

    @Test
    public void testCalculateBonusForAllChoices() {
        IBonusDropEngine engine = new BonusDropEngineImpl();

        for (PlayerChoice choice : PlayerChoice.values()) {
            BonusPlayResult result = engine.calculateBonus(choice);
            assertNotNull(result);
        }
    }

    @Test
    public void testAllOutcomesPossible() {
        IBonusDropEngine engine = new BonusDropEngineImpl();
        Set<BonusPlayResult> results = new HashSet<>();

        for (int i = 0; i < 1000; i++) {
            results.add(engine.calculateBonus(PlayerChoice.LEFT));
        }

        assertEquals(3, results.size());
    }
}
