package Ordnance.powers;

import Ordnance.OrdnanceMod;
import Ordnance.cards.abstracts.AbstractRangedCard;
import Ordnance.damageMods.FireDamage;
import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.damagemods.AbstractDamageModifier;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModContainer;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.DamageModApplyingPower;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.List;

public class FireAmmoPower extends AbstractEasyPower implements CloneablePowerInterface, DamageModApplyingPower {

    public static final String POWER_ID = OrdnanceMod.makeID("FireAmmoPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private final DamageModContainer mods = new DamageModContainer(this,  new FireDamage(false, true));

    public FireAmmoPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("painfulStabs");
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card instanceof AbstractRangedCard) {
            flash();
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        }
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new FireAmmoPower(owner, amount);
    }

    @Override
    public boolean shouldPushMods(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        return o instanceof AbstractRangedCard;
    }

    @Override
    public List<AbstractDamageModifier> modsToPush(DamageInfo damageInfo, Object o, List<AbstractDamageModifier> list) {
        return mods.modifiers();
    }
}
