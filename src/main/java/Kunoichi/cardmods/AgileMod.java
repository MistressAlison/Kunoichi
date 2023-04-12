package Kunoichi.cardmods;

import Kunoichi.powers.EvasionPower;
import Kunoichi.util.Wiz;
import basemod.abstracts.AbstractCardModifier;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class AgileMod extends AbstractCardModifier {
    public static String ID = makeID(AgileMod.class.getSimpleName());
    public static String[] TEXT = CardCrawlGame.languagePack.getCardStrings(ID).EXTENDED_DESCRIPTION;

    @Override
    public float modifyDamage(float damage, DamageInfo.DamageType type, AbstractCard card, AbstractMonster target) {
        if (Wiz.adp().hasPower(EvasionPower.POWER_ID)) {
            return damage + Wiz.adp().getPower(EvasionPower.POWER_ID).amount;
        }
        return damage;
    }

    public String modifyDescription(String rawDescription, AbstractCard card) {
        return TEXT[0] + rawDescription;
    }

    public boolean shouldApply(AbstractCard card) {
        return !CardModifierManager.hasModifier(card, ID);
    }

    public AbstractCardModifier makeCopy() {
        return new AgileMod();
    }

    public String identifier(AbstractCard card) {
        return ID;
    }
}
