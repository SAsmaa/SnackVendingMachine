/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vending.api;

import java.util.ArrayList;
import java.util.List;
import vending.beans.Coin;
import vending.beans.Note;
import vending.beans.PriceBundle;

/**
 *
 * @author Asmaa
 */
public class InventoryUtil {

    public PriceBundle convertToCoin(List<Coin> returnCoinsArray, List<Note> noteList, int changedValue) {
        int remain = 0;
        if (changedValue >= Note.FIFTY.getNoteValue() * 10) {
            remain = changedValue / Note.FIFTY.getNoteValue() * 10;
            if (remain > 0) {

                for (int i = 0; i <= remain - 1; i++) {

                    noteList.add(Note.FIFTY);
                }
            }
            int rest = changedValue - (remain * Note.FIFTY.getNoteValue() * 10);
            if (rest != 0) {
                convertToCoin(returnCoinsArray, noteList, rest);
            }

        } else if (changedValue >= Note.TWENTY.getNoteValue() * 10) {
            remain = changedValue / Note.TWENTY.getNoteValue() * 10;
            if (remain > 0) {

                for (int i = 0; i <= remain - 1; i++) {
                    noteList.add(Note.TWENTY);
                }
            }
            int rest = changedValue - (remain * Note.TWENTY.getNoteValue() * 10);
            if (rest != 0) {
                convertToCoin(returnCoinsArray, noteList, rest);
            }

        } else if (changedValue >= Coin.TWENTYCENT.getCoinValue()) {
            remain = changedValue / Coin.TWENTYCENT.getCoinValue();
            if (remain > 0) {
                for (int i = 0; i <= remain - 1; i++) {
                    returnCoinsArray.add(Coin.TWENTYCENT);
                }
            }

            int rest = changedValue - (remain * Coin.TWENTYCENT.getCoinValue());
            if (rest != 0) {
                convertToCoin(returnCoinsArray, noteList, rest);
            }
        } else if (changedValue >= Coin.TENCENT.getCoinValue()) {
            remain = changedValue / Coin.TENCENT.getCoinValue();
            if (remain > 0) {
                for (int i = 0; i <= remain - 1; i++) {
                    returnCoinsArray.add(Coin.TENCENT);
                }
            }

            int rest = changedValue - (remain * Coin.TENCENT.getCoinValue());
            if (rest != 0) {
                convertToCoin(returnCoinsArray, noteList, rest);
            }
        } else if (changedValue >= Coin.ONE.getCoinValue()) {
            remain = changedValue / Coin.ONE.getCoinValue();
            if (remain > 0) {
                for (int i = 0; i <= remain - 1; i++) {
                    returnCoinsArray.add(Coin.ONE);
                }
            }

            int rest = changedValue - (remain * Coin.ONE.getCoinValue());
            if (rest != 0) {
                convertToCoin(returnCoinsArray, noteList, rest);
            }
        }

        return new PriceBundle(returnCoinsArray, noteList);

    }
}
