package Ordnance.cards;

import Ordnance.OrdnanceMod;
import Ordnance.actions.RipArrowAction;
import Ordnance.cards.abstracts.AbstractEasyCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Reclaim extends AbstractEasyCard {

    // TEXT DECLARATION

    public static final String ID = OrdnanceMod.makeID(Reclaim.class.getSimpleName());

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = CardRarity.BASIC;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = CardType.SKILL;

    private static final int COST = 0;
    private static final int RIP = 2;
    private static final int UPGRADE_PLUS_RIP = 1;

    // /STAT DECLARATION/


    public Reclaim() {
        super(ID, COST, TYPE, RARITY, TARGET);
        magicNumber = baseMagicNumber = RIP;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new RipArrowAction(p, m, magicNumber));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(UPGRADE_PLUS_RIP);
    }
}
