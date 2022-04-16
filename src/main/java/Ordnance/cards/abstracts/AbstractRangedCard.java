package Ordnance.cards.abstracts;

import Ordnance.damageMods.RangedDamage;
import Ordnance.powers.AmmoPower;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;


public abstract class AbstractRangedCard extends AbstractEasyCard {

    public int ammoRequirement;

    public AbstractRangedCard(String cardID, int cost, CardType type, CardRarity rarity, CardTarget target, int ammoRequirement) {
        super(cardID, cost, type, rarity, target);
        this.ammoRequirement = ammoRequirement;
        DamageModifierManager.addModifier(this, new RangedDamage(ammoRequirement));
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (ammoRequirement <= 0) {
            return super.canUse(p, m);
        } else {
            return super.canUse(p, m) && p.hasPower(AmmoPower.POWER_ID) && p.getPower(AmmoPower.POWER_ID).amount >= ammoRequirement;
        }

    }
}
