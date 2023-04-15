package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.LockOn;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

import static Kunoichi.KunoichiMod.makeID;

public class Snipe extends AbstractEasyCard {
    public final static String ID = makeID(Snipe.class.getSimpleName());

    public Snipe() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 10;
        baseMagicNumber = magicNumber = 3;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SelectCardsInHandAction(1, cardStrings.EXTENDED_DESCRIPTION[1], l -> {
            for (AbstractCard c : l) {
                int bonus = c.costForTurn == -1 ? EnergyPanel.getCurrentEnergy() : Math.max(c.costForTurn, 0);
                Wiz.att(new DamageAction(m, new DamageInfo(p, damage + (bonus*magicNumber), damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
                Wiz.att(new ExhaustSpecificCardAction(c, Wiz.adp().hand));
            }
        }));
    }

    @Override
    public void upp() {
        //upgradeDamage(3);
        upgradeMagicNumber(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return LockOn.ID;
    }
}