package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.patches.OnUseEnergyPatches;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.colorless.Finesse;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class ArteTanuki extends AbstractEasyCard implements OnUseEnergyPatches.OnUseEnergyObject {
    public final static String ID = makeID(ArteTanuki.class.getSimpleName());

    public ArteTanuki() {
        super(ID, -2, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        isEthereal = true;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {}

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
        return false;
    }

    @Override
    public void upp() {
        isEthereal = false;
        uDesc();
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Finesse.ID;
    }

    @Override
    public void onUseEnergy(int amount) {
        if (amount > 0 && Wiz.adp().hand.contains(this)) {
            addToBot(new DrawCardAction(magicNumber));
        }
    }
}