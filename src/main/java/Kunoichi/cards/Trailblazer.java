package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.PressTheAdvantagePower;
import Kunoichi.powers.TrailblazerPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.green.Dash;
import com.megacrit.cardcrawl.cards.green.Reflex;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class Trailblazer extends AbstractEasyCard {
    public final static String ID = makeID(Trailblazer.class.getSimpleName());

    public Trailblazer() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new TrailblazerPower(p, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Dash.ID;
    }
}