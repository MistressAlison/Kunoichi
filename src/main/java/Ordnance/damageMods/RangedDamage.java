package Ordnance.damageMods;

import Ordnance.OrdnanceMod;
import Ordnance.powers.BleedPower;
import Ordnance.powers.EmbeddedAmmoPower;
import basemod.BaseMod;
import basemod.helpers.TooltipInfo;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;

import java.util.ArrayList;

public class RangedDamage extends AbstractDamageModifier {

    public static final String ID = OrdnanceMod.makeID("RangedDamage");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private final int ammoCost;

    TooltipInfo rangedTooltip = null;
    TooltipInfo bleedTooltip = null;

    public RangedDamage(int ammoCost) {
        this.ammoCost = ammoCost;
    }

    @Override
    public boolean ignoresThorns() {
        return true;
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        this.addToBot(new ApplyPowerAction(target, info.owner, new EmbeddedAmmoPower(target, ammoCost)));
        this.addToBot(new ApplyPowerAction(target, info.owner, new BleedPower(target, ammoCost)));
    }

    @Override
    public ArrayList<TooltipInfo> getCustomTooltips() {
        ArrayList<TooltipInfo> l = super.getCustomTooltips();
        if (rangedTooltip == null) {
            rangedTooltip = new TooltipInfo(cardStrings.DESCRIPTION, cardStrings.EXTENDED_DESCRIPTION[0]);
        }
        if (bleedTooltip == null) {
            bleedTooltip = new TooltipInfo(BaseMod.getKeywordTitle("ordnance:bleed"), BaseMod.getKeywordDescription("ordnance:bleed"));
        }
        l.add(rangedTooltip);
        l.add(bleedTooltip);
        return l;
    }

    @Override
    public String getCardDescriptor() {
        return cardStrings.NAME;
    }

    @Override
    public boolean isInherent() {
        return true;
    }

    @Override
    public AbstractDamageModifier makeCopy() {
        return new RangedDamage(ammoCost);
    }
}
