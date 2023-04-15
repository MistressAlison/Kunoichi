package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.damageMods.PiercingDamage;
import Kunoichi.powers.ShockPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.blue.StaticDischarge;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class ShockingGrasp extends AbstractEasyCard {
    public final static String ID = makeID(ShockingGrasp.class.getSimpleName());

    public ShockingGrasp() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 5;
        baseMagicNumber = magicNumber = 1;
        DamageModifierManager.addModifier(this, new PiercingDamage());
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ORB_LIGHTNING_CHANNEL", 0.2f));
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        Wiz.applyToEnemy(m, new ShockPower(m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeDamage(1);
        upgradeMagicNumber(1);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return StaticDischarge.ID;
    }
}