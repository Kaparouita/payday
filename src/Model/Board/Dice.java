package Model.Board;

import     java.util.Random;

public  class Dice {
    private int dice_value;
    /**
     * roll the dice
     * @return [1,6]
     */
    public  int roll_Dice(){
        Random dice=new Random();
        dice_value=dice.nextInt(6) + 1;
        return dice_value;
    }

    /**
     * returns last dice value
     * @return
     */
    public int getDice_value() {
        return dice_value;
    }


}
