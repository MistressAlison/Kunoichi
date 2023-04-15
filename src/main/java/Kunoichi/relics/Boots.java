package Kunoichi.relics;

import Kunoichi.TheKunoichi;
import Kunoichi.patches.WasPowerActuallyAppliedPatches;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Kunoichi.KunoichiMod.makeID;

public class Boots extends AbstractEasyRelic implements WasPowerActuallyAppliedPatches.OnActuallyApplyPowerRelic {
    public static final String ID = makeID(Boots.class.getSimpleName());

    public Boots() {
        super(ID, RelicTier.UNCOMMON, LandingSound.FLAT, TheKunoichi.Enums.DARK_GUNMETAL_COLOR);
    }

    @Override
    public void onApplyPower(AbstractCreature target, AbstractCreature source, AbstractPower power) {
        if (target instanceof AbstractMonster && source == AbstractDungeon.player && power.type == AbstractPower.PowerType.DEBUFF) {
            this.flash();
            addToBot(new RelicAboveCreatureAction(target, this));
            addToBot(new DamageAction(target, new DamageInfo(AbstractDungeon.player, 2, DamageInfo.DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY, true));
        }
    }
}
