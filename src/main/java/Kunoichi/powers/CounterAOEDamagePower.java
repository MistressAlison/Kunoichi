package Kunoichi.powers;

import Kunoichi.actions.AnimationAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static Kunoichi.KunoichiMod.makeID;

public class CounterAOEDamagePower extends AbstractEasyPower {
    public static String POWER_ID = makeID(CounterAOEDamagePower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public CounterAOEDamagePower(AbstractCreature owner, int amount) {
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
            int[] damages = DamageInfo.createDamageMatrix(amount, true);
            for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
                if (!m.isDeadOrEscaped()) {
                    AbstractPower p = m.getPower(ExposedPower.POWER_ID);
                    if (p != null) {
                        p.flashWithoutSound();
                        damages[AbstractDungeon.getMonsters().monsters.indexOf(m)] += p.amount;
                    }
                }
            }
            addToTop(new DamageAllEnemiesAction(owner, damages, DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.NONE));
            addToTop(new VFXAction(owner, new CleaveEffect(), 0.1F));
            addToTop(new SFXAction("ATTACK_HEAVY"));
            addToTop(new AnimationAction(AnimationAction.Animation.ATTACK));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
