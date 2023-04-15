package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.CounterDamagePower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.colorless.DeepBreath;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.EnergizedPower;

import static Kunoichi.KunoichiMod.makeID;

public class Breather extends AbstractEasyCard {
    public final static String ID = makeID(Breather.class.getSimpleName());

    public Breather() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 10;
        baseSecondMagic = secondMagic = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new EnergizedPower(p, secondMagic));
        Wiz.applyToSelf(new CounterDamagePower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(3);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return DeepBreath.ID;
    }
}