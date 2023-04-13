package Kunoichi.actions;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class OpenerAction extends AbstractGameAction {
    private Runnable doIf;

    public OpenerAction(Runnable doIf) {
        this.source = AbstractDungeon.player;
        this.actionType = ActionType.SPECIAL;
        this.doIf = doIf;
    }

    @Override
    public void update() {
        if (openerPlayCheck()) {
            doIf.run();
        }
        this.isDone = true;
    }

    private static boolean openerPlayCheck() {
        int played = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
        int threshold = 1;
        if (threshold >= played) {
            return true;
        }
        for (AbstractRelic r : Wiz.adp().relics) {
            if (r instanceof OpenerAction.OpenerModifier) {
                threshold += ((OpenerAction.OpenerModifier) r).thresholdModifier();
                if (threshold >= played) {
                    r.flash();
                    return true;
                }
            }
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OpenerAction.OpenerModifier) {
                threshold += ((OpenerAction.OpenerModifier) p).thresholdModifier();
                if (threshold >= played) {
                    p.flash();
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean openerGlowCheck() {
        int threshold = 0;
        for (AbstractRelic r : Wiz.adp().relics) {
            if (r instanceof OpenerAction.OpenerModifier) {
                threshold += ((OpenerAction.OpenerModifier) r).thresholdModifier();
            }
        }
        for (AbstractPower p : Wiz.adp().powers) {
            if (p instanceof OpenerAction.OpenerModifier) {
                threshold += ((OpenerAction.OpenerModifier) p).thresholdModifier();
            }
        }
        return AbstractDungeon.actionManager.cardsPlayedThisTurn.size() <= threshold;
    }

    public interface OpenerModifier {
        int thresholdModifier();
    }
}
