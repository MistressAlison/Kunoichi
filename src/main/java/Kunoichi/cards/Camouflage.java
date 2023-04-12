package Kunoichi.cards;

import Kunoichi.actions.ApplyPowerActionWithFollowup;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.red.Armaments;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.GainStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.SmokeBombEffect;

import static Kunoichi.KunoichiMod.makeID;

public class Camouflage extends AbstractEasyCard {
    public final static String ID = makeID(Camouflage.class.getSimpleName());

    public Camouflage() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL);
        baseBlock = block = 12;
        baseMagicNumber = magicNumber = 4;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        Wiz.forAllMonstersLiving(mon -> {
            ApplyPowerAction strD = new ApplyPowerAction(mon, p, new StrengthPower(mon, -magicNumber));
            ApplyPowerAction strR = new ApplyPowerAction(mon, p, new GainStrengthPower(mon, magicNumber));
            addToBot(new ApplyPowerActionWithFollowup(strD, strR));
        });
    }

    @Override
    public void upp() {
        upgradeBlock(4);
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Armaments.ID;
    }
}