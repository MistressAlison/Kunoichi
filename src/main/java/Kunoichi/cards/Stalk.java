package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.ExposedPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.red.SeeingRed;
import com.megacrit.cardcrawl.cards.tempCards.Safety;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.powers.watcher.FreeAttackPower;

import static Kunoichi.KunoichiMod.makeID;

public class Stalk extends AbstractEasyCard {
    public final static String ID = makeID(Stalk.class.getSimpleName());

    public Stalk() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 1;
        baseSecondMagic = secondMagic = 4;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false));
        Wiz.applyToEnemy(m, new ExposedPower(m, secondMagic));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Safety.ID;
    }
}