package Kunoichi.relics;

import Kunoichi.TheKunoichi;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import static Kunoichi.KunoichiMod.makeID;

public class Scroll extends AbstractEasyRelic {
    public static final String ID = makeID(Scroll.class.getSimpleName());
    private static final int ENERGY_AMT = 1;
    private boolean firstTurn = true;

    public Scroll() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheKunoichi.Enums.DARK_GUNMETAL_COLOR);
    }

    public void atPreBattle() {
        this.firstTurn = true;
    }

    public void atTurnStart() {
        if (this.firstTurn) {
            this.flash();
            this.addToTop(new GainEnergyAction(ENERGY_AMT));
            this.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            this.firstTurn = false;
        }
    }
}
