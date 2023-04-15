package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.CounterAOEDamagePower;
import Kunoichi.powers.EvasionPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Kunoichi.KunoichiMod.makeID;

public class Retaliate extends AbstractEasyCard {
    public final static String ID = makeID(Retaliate.class.getSimpleName());

    public Retaliate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new CounterAOEDamagePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public void applyPowers() {
        magicNumber = baseMagicNumber;
        super.applyPowers();
        AbstractPower p = Wiz.adp().getPower(EvasionPower.POWER_ID);
        if (p != null) {
            magicNumber += p.amount;
        }
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        magicNumber = baseMagicNumber;
        super.calculateCardDamage(mo);
        AbstractPower p = Wiz.adp().getPower(EvasionPower.POWER_ID);
        if (p != null) {
            magicNumber += p.amount;
        }
        isMagicNumberModified = magicNumber != baseMagicNumber;
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.35f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Cleave.ID;
    }
}