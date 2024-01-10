package com.interview.notes.code.months.year2023.nov23.test2;

import java.util.Map;
import java.util.Random;


public class BonusDropEngineImpl implements IBonusDropEngine {

    private final Map<PlayerChoice, Map<BonusPlayResult, Integer>> config = Map.of(
            PlayerChoice.LEFT, Map.of(
                    BonusPlayResult.BONUS, 22,
                    BonusPlayResult.NO_BONUS, 35,
                    BonusPlayResult.REPLAY, 22
            ),
            PlayerChoice.MIDDLE, Map.of(
                    BonusPlayResult.BONUS, 5,
                    BonusPlayResult.NO_BONUS, 10,
                    BonusPlayResult.REPLAY, 20
            ),
            PlayerChoice.RIGHT, Map.of(
                    BonusPlayResult.BONUS, 1,
                    BonusPlayResult.NO_BONUS, 3,
                    BonusPlayResult.REPLAY, 5
            )
    );

    @Override
    public BonusPlayResult calculateBonus(PlayerChoice choice) {
        int randomNumber = new Random().nextInt(100) + 1;

        Map<BonusPlayResult, Integer> chances = config.get(choice);

        int bonusChance = chances.get(BonusPlayResult.BONUS);
        int noBonusChance = chances.get(BonusPlayResult.NO_BONUS);

        if (randomNumber <= bonusChance) {
            return BonusPlayResult.BONUS;
        } else if (randomNumber <= bonusChance + noBonusChance) {
            return BonusPlayResult.NO_BONUS;
        } else {
            return BonusPlayResult.REPLAY;
        }
    }
}
