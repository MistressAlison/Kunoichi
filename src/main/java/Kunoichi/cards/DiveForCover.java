package Kunoichi.cards;

import Kunoichi.actions.CleansePowerAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.green.DodgeAndRoll;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static Kunoichi.KunoichiMod.makeID;

public class DiveForCover extends AbstractEasyCard {
    public final static String ID = makeID(DiveForCover.class.getSimpleName());

    public DiveForCover() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = block = 5;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.atb(new CleansePowerAction(p, magicNumber, c -> c.type == AbstractPower.PowerType.DEBUFF, powers -> {}));
    }

    @Override
    public void upp() {
        upgradeBlock(3);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.2f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return DodgeAndRoll.ID;
    }
}