package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.evacipated.cardcrawl.mod.stslib.fields.cards.AbstractCard.GraveField;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.cards.green.PhantasmalKiller;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;

import static Kunoichi.KunoichiMod.makeID;

public class Decoy extends AbstractEasyCard implements StartupCard {
    public final static String ID = makeID(Decoy.class.getSimpleName());

    public Decoy() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {}

    public void upp() {
        isEthereal = true;
        exhaust = false;
        upgradeBaseCost(-2);
        uDesc();
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.2f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return PhantasmalKiller.ID;
    }

    @Override
    public boolean atBattleStartPreDraw() {
        applyPowers();
        Wiz.applyToSelf(new IntangiblePlayerPower(Wiz.adp(), magicNumber));
        return true;
    }
}