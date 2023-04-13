package Kunoichi.powers;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Kunoichi.KunoichiMod.makeID;

public class AntinomyPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(AntinomyPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public AntinomyPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("rupture");
    }

    @Override
    public void onSpecificTrigger() {
        flash();
        addToTop(new ApplyPowerAction(owner, owner, new StrengthPower(owner, amount)));
    }

    public static void staticTrigger() {
        if (CardCrawlGame.isInARun() && Wiz.adp() != null) {
            AbstractPower pow = Wiz.adp().getPower(AntinomyPower.POWER_ID);
            if (pow != null) {
                pow.onSpecificTrigger();
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
