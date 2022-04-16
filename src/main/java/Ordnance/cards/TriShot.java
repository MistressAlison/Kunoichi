package Ordnance.cards;

import Ordnance.OrdnanceMod;
import Ordnance.cards.abstracts.AbstractRangedCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class TriShot extends AbstractRangedCard {

    // TEXT DECLARATION

    public static final String ID = OrdnanceMod.makeID(TriShot.class.getSimpleName());

    // /TEXT DECLARATION/


    // STAT DECLARATION

    private static final AbstractCard.CardRarity RARITY = CardRarity.COMMON;
    private static final AbstractCard.CardTarget TARGET = CardTarget.ENEMY;
    private static final AbstractCard.CardType TYPE = CardType.ATTACK;

    private static final int COST = 2;
    private static final int AMMO = 1;
    private static final int HITS = 3;
    private static final int DAMAGE = 4;
    private static final int UPGRADE_PLUS_DAMAGE = 2;

    // /STAT DECLARATION/


    public TriShot() {
        super(ID, COST, TYPE, RARITY, TARGET, AMMO);
        ammoRequirement = AMMO * HITS;
        magicNumber = baseMagicNumber = HITS;
        damage = baseDamage = DAMAGE;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0 ; i < magicNumber ; i++) {
            this.addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL, true));
        }
    }

    @Override
    public void upp() {
        upgradeDamage(UPGRADE_PLUS_DAMAGE);
    }
}
