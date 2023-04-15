package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.WeaponsReadyPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.red.Clash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class WeaponsReady extends AbstractEasyCard {
    public final static String ID = makeID(WeaponsReady.class.getSimpleName());

    public WeaponsReady() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new WeaponsReadyPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Clash.ID;
    }
}