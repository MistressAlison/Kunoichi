package Kunoichi.relics;

import Kunoichi.TheKunoichi;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.PoisonPower;

import static Kunoichi.KunoichiMod.makeID;

public class Boots extends AbstractEasyRelic {
    public static final String ID = makeID(Boots.class.getSimpleName());

    public Boots() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheKunoichi.Enums.DARK_GUNMETAL_COLOR);
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        if (damageAmount > 0 && target != Wiz.adp() && info.type == DamageInfo.DamageType.NORMAL) {
            this.flash();
            this.addToTop(new DamageAction(target, new DamageInfo(Wiz.adp(), 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
    }
}
