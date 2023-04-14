package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.ShockPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.red.Shockwave;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.ShockWaveEffect;

import static Kunoichi.KunoichiMod.makeID;

public class ThunderWave extends AbstractEasyCard {
    public final static String ID = makeID(ThunderWave.class.getSimpleName());

    public ThunderWave() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 6;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ORB_PLASMA_CHANNEL"));
        addToBot(new VFXAction(p, new ShockWaveEffect(p.hb.cX, p.hb.cY, Settings.LIGHT_YELLOW_COLOR, ShockWaveEffect.ShockWaveType.CHAOTIC), 0.3F));
        Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new ShockPower(mon, magicNumber)));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.08f, 0.5f, 0.5f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Shockwave.ID;
    }
}