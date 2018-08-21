package com.jhonatanrocha.arcadiaquest.diceroller;

/**
 * Created by Intera on 11/08/2018.
 */

public enum DiceEnum {

    MELEE("Ofensive", new Integer[] {1, 3, 5}),
    RANGED("Ofensive", new Integer[] {2, 3, 4}),
    CRITICAL("Ofensive", new Integer[] {6}),
    DEFENSIVE("Defensive", new Integer[] {2}),
    CRITICAL_DEFENSIVE("Defensive", new Integer[] {5});

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

}
