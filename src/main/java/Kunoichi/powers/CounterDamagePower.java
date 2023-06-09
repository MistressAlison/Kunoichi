package Kunoichi.powers;

import Kunoichi.actions.AnimationAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Kunoichi.KunoichiMod.makeID;

public class CounterDamagePower extends AbstractEasyPower {
    public static String POWER_ID = makeID(CounterDamagePower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public CounterDamagePower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("noPain");
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.type != DamageInfo.DamageType.THORNS && info.type != DamageInfo.DamageType.HP_LOSS && info.owner != null && info.owner != this.owner) {
            this.flash();
            AntinomyPower.staticTrigger();
            addToTop(new RemoveSpecificPowerAction(owner, owner, this));
            int bonus = 0;
            AbstractPower p = info.owner.getPower(ExposedPower.POWER_ID);
            if (p != null) {
                p.flashWithoutSound();
                bonus += p.amount;
            }
            addToTop(new DamageAction(info.owner, new DamageInfo(this.owner, this.amount + bonus, DamageInfo.DamageType.THORNS), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
            addToTop(new AnimationAction(AnimationAction.Animation.ATTACK));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
