package com.jhonatanrocha.arcadiaquest.diceroller;

/**
 * Created by Intera on 11/08/2018.
 */

public enum DiceEnum {

    MELEE("Ofensive", new Integer[] {1, 3, 4}),
    RANGED("Ofensive", new Integer[] {2, 5}),
    CRITICAL("Ofensive", new Integer[] {6}),
    DEFENSIVE("Defensive", new Integer[] {1}),
    CRITICAL_DEFENSIVE("Defensive", new Integer[] {6});

    private String typeName;
    private Integer[] numbers;

    public String getTypeName() {
        return typeName;
    }

    public Integer[] getNumbers() {
        return numbers;
    }

    private DiceEnum(String typeName, Integer... numbers) {
        this.typeName = typeName;
        this.numbers = numbers;
    }

    public static DiceEnum getDice(final String typeName, final Integer resultValue) {
        DiceEnum resultDice = null;

        for(DiceEnum dice : values()) {
            if(dice.getTypeName().equals(typeName)) {
                for (Integer value : dice.getNumbers()) {
                    if(value.equals(resultValue)) {
                        resultDice = dice;
                    }
                }
            }
        }
        return resultDice;
    }

    public static Integer getNumber(final String typeName, final Integer resultValue) {
        Integer resultNumber = null;

        for(DiceEnum dice : values()) {
            if(dice.getTypeName().equals(typeName)) {
                for (Integer value : dice.getNumbers()) {
                    if(value.equals(resultValue)) {
                        resultNumber = resultValue;
                    }
                }
            }
        }
        return resultNumber;
    }

    public static Boolean isMelee(final Integer resultValue) {
        Boolean flagOffensiveResult = false;

        for (Integer value : DiceEnum.MELEE.getNumbers()) {
            if(value.equals(resultValue)) {
                flagOffensiveResult = true;
            }
        }
        return flagOffensiveResult;
    }

    public static Boolean isRanged(final Integer resultValue) {
        Boolean flagOffensiveResult = false;

        for (Integer value : DiceEnum.RANGED.getNumbers()) {
            if(value.equals(resultValue)) {
                flagOffensiveResult = true;
            }
        }
        return flagOffensiveResult;
    }

    public static Boolean isCritical(final Integer resultValue) {
        Boolean flagOffensiveResult = false;

        for (Integer value : DiceEnum.CRITICAL.getNumbers()) {
            if(value.equals(resultValue)) {
                flagOffensiveResult = true;
            }
        }
        return flagOffensiveResult;
    }

    public static Boolean isDefensive(final Integer resultValue) {
        Boolean flagOffensiveResult = false;

        for (Integer value : DiceEnum.DEFENSIVE.getNumbers()) {
            if(value.equals(resultValue)) {
                flagOffensiveResult = true;
            }
        }
        return flagOffensiveResult;
    }

    public static Boolean isCriticalDefensive(final Integer resultValue) {
        Boolean flagOffensiveResult = false;

        for (Integer value : DiceEnum.CRITICAL_DEFENSIVE.getNumbers()) {
            if(value.equals(resultValue)) {
                flagOffensiveResult = true;
            }
        }
        return flagOffensiveResult;
    }

}
