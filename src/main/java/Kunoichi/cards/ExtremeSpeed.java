package Kunoichi.cards;

import Kunoichi.actions.EasyXCostAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Blur;
import com.megacrit.cardcrawl.cards.green.Defend_Green;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.Arrays;

import static Kunoichi.KunoichiMod.makeID;

public class ExtremeSpeed extends AbstractEasyCard {
    public final static String ID = makeID(ExtremeSpeed.class.getSimpleName());

    public ExtremeSpeed() {
        super(ID, -1, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new EasyXCostAction(this, (base, params) -> {
            int effect = base + Arrays.stream(params).sum();
            Wiz.att(new DrawCardAction(effect, new AbstractGameAction() {
                @Override
                public void update() {
                    for (AbstractCard c : DrawCardAction.drawnCards) {
                        c.setCostForTurn(0);
                    }
                    this.isDone = true;
                }
            }));
            return true;
        }, upgraded ? 1 : 0));
    }

    public void upp() {
        uDesc();
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.2f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Blur.ID;
    }
}