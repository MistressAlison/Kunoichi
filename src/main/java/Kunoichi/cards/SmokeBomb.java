package Kunoichi.cards;

import Kunoichi.actions.ApplyPowerActionWithFollowup;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.status.VoidCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import static Kunoichi.KunoichiMod.makeID;

public class SmokeBomb extends AbstractEasyCard {
    public final static String ID = makeID(SmokeBomb.class.getSimpleName());

    public SmokeBomb() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new VFXAction(new SmokeBombEffect(p.hb.cX, p.hb.cY)));
        Wiz.forAllMonstersLiving(mon -> {
            ApplyPowerAction strD =  new ApplyPowerAction(m, p, new StrengthPower(mon, -magicNumber));
            ApplyPowerAction strR = new ApplyPowerAction(m, p, new GainStrengthPower(mon, magicNumber));
            addToBot(new ApplyPowerActionWithFollowup(strD, strR));
        });
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.0f, 0.7f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return VoidCard.ID;
    }
}