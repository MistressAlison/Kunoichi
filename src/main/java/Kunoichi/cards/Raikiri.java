package Kunoichi.cards;

import Kunoichi.cardmods.AgileMod;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.ShockPower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import basemod.helpers.CardModifierManager;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.blue.ThunderStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;

import static Kunoichi.KunoichiMod.makeID;

public class Raikiri extends AbstractEasyCard {
    public final static String ID = makeID(Raikiri.class.getSimpleName());

    public Raikiri() {
        super(ID, 2, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = damage = 12;
        baseMagicNumber = magicNumber = 6;
        isMultiDamage = true;
        CardModifierManager.addModifier(this, new AgileMod(true));
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        addToBot(new SFXAction("THUNDERCLAP", 0.05F));
        Wiz.forAllMonstersLiving(mo -> {
            addToBot(new VFXAction(new LightningEffect(mo.drawX, mo.drawY), 0.05F));
        });
        allDmg(AbstractGameAction.AttackEffect.NONE);
        Wiz.forAllMonstersLiving(mo -> {
            Wiz.applyToEnemy(mo, new ShockPower(mo, magicNumber));
        });
    }

    @Override
    public void upp() {
        upgradeDamage(4);
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return ThunderStrike.ID;
    }
}