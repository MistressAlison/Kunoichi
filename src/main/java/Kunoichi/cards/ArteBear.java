package Kunoichi.cards;

import Kunoichi.actions.ApplyPowerActionWithFollowup;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Berserk;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Kunoichi.KunoichiMod.makeID;

public class ArteBear extends AbstractEasyCard {
    public final static String ID = makeID(ArteBear.class.getSimpleName());

    public ArteBear() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
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
    public void triggerOnOtherCardPlayed(AbstractCard c) {
        addToBot(new ApplyPowerActionWithFollowup(new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new StrengthPower(Wiz.adp(), magicNumber)), new ApplyPowerAction(Wiz.adp(), Wiz.adp(), new LoseStrengthPower(Wiz.adp(), magicNumber))));
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
        return Berserk.ID;
    }
}