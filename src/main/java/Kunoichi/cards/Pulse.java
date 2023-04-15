package Kunoichi.cards;

import Kunoichi.actions.OpenerAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.patches.DontExhaustOnUsePatches;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Adrenaline;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class Pulse extends AbstractEasyCard {
    public final static String ID = makeID(Pulse.class.getSimpleName());

    public Pulse() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        //Non-Action timing because the check is in the UCA Constructor
        if (OpenerAction.openerPlayCheck()) {
            DontExhaustOnUsePatches.DontExhaustField.dontExhaustOnUseOnce.set(this, true);
        }
        addToBot(new GainEnergyAction(magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (OpenerAction.openerGlowCheck()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Adrenaline.ID;
    }
}