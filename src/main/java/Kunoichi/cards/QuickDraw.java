package Kunoichi.cards;

import Kunoichi.actions.OpenerAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.PerfectedStrike;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;

import static Kunoichi.KunoichiMod.makeID;

public class QuickDraw extends AbstractEasyCard {
    public final static String ID = makeID(QuickDraw.class.getSimpleName());

    public QuickDraw() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ALL_ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 1;
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("ATTACK_HEAVY"));
        addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        allDmg(AbstractGameAction.AttackEffect.NONE);
        addToBot(new OpenerAction(() -> {
            addToTop(new DrawCardAction(magicNumber));
            addToTop(new GainEnergyAction(magicNumber));
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
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
        return PerfectedStrike.ID;
    }
}