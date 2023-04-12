package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.unique.RandomCardFromDiscardPileToHandAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.colorless.Magnetism;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class MagnetPull extends AbstractEasyCard {
    public final static String ID = makeID(MagnetPull.class.getSimpleName());

    public MagnetPull() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_WHIFF_2", 0.2f));
        for (int i = 0 ; i < magicNumber ; i++) {
            addToBot(new RandomCardFromDiscardPileToHandAction());
        }
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
        return Magnetism.ID;
    }
}