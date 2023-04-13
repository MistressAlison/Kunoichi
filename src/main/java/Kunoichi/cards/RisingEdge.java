package Kunoichi.cards;

import Kunoichi.actions.OpenerAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.QuickSlash;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class RisingEdge extends AbstractEasyCard {
    public final static String ID = makeID(RisingEdge.class.getSimpleName());

    public RisingEdge() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = damage = 8;
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        addToBot(new DrawCardAction(magicNumber, new OpenerAction(() -> {
            for (AbstractCard c : DrawCardAction.drawnCards) {
                c.setCostForTurn(0);
            }
        })));
        //addToBot(new DoIfAction(() -> AbstractDungeon.actionManager.cardsPlayedThisTurn.size() == 1, () -> addToTop(new DrawCardAction(magicNumber))));
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
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.2f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return QuickSlash.ID;
    }
}