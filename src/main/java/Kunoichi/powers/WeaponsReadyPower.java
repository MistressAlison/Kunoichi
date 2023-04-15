package Kunoichi.powers;

import Kunoichi.actions.AnimationAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Kunoichi.KunoichiMod.makeID;

public class WeaponsReadyPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(WeaponsReadyPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public WeaponsReadyPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("painfulStabs");
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        flash();
        addToBot(new SFXAction("CARD_POWER_WOOSH"));
        addToBot(new AnimationAction(AnimationAction.Animation.SKILL));
        addToBot(new ApplyPowerAction(owner, owner, new CounterDamagePower(owner, amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
