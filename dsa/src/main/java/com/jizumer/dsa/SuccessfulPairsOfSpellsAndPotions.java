package com.jizumer.dsa;

import java.util.Arrays;

//2300. Successful Pairs of Spells and Potions
public class SuccessfulPairsOfSpellsAndPotions {

    public int[] successfulPairs(int[] spells, int[] potions, long success) {

        int[] pairs = new int[spells.length];

        Arrays.sort(potions);
        for (int i = 0; i < spells.length; i++) {
            double threshold = (double) success / spells[i];

            int runner = searchLowestSuccessfullPotion(potions, threshold);
            pairs[i] = potions.length - runner;
        }
        return pairs;
    }

    int searchLowestSuccessfullPotion(int[] potions, double target) {

        if (potions[0] >= target)
            return 0;
        int low = 0;
        int high = potions.length;
        int pointer = 0;

        while (low < high) {
            if(high - low < 2) {
                 if(potions[low] >= target) return low;
                 return high;
            }

            pointer = low + ((high - low) / 2);
            if (potions[pointer] == target) {
                while(pointer -1 >= 0 && potions[pointer-1] == potions[pointer]) pointer--;
                return pointer;
            }
            else if (potions[pointer] > target) {
                high = pointer;
            } else {
                low = pointer;
            }
        }
        return pointer;
    }


}
