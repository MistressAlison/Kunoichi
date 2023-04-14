package Kunoichi.cards;

import Kunoichi.actions.DoIfAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Distraction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Kunoichi.KunoichiMod.makeID;

public class Mislead extends AbstractEasyCard {
    public final static String ID = makeID(Mislead.class.getSimpleName());

    public Mislead() {
        super(ID, 0, CardType.SKILL, CardRarity.COMMON, CardTarget.ENEMY);
        baseBlock = block = 3;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        addToBot(new DoIfAction(() -> Wiz.isAttacking(m), () -> Wiz.applyToEnemy(m, new WeakPower(m, magicNumber, false))));
    }

    @Override
    public void upp() {
        upgradeBlock(1);
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.anyMonsterAttacking()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.35f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Distraction.ID;
    }
}