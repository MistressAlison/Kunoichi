package Ordnance.powers;

import Ordnance.OrdnanceMod;
import Ordnance.damageMods.RangedDamage;
import basemod.interfaces.CloneablePowerInterface;
import com.badlogic.gdx.graphics.Color;
import com.evacipated.cardcrawl.mod.stslib.damagemods.DamageModifierManager;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.HealthBarRenderPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class BleedPower extends AbstractPower implements CloneablePowerInterface, HealthBarRenderPower {

    public static final String POWER_ID = OrdnanceMod.makeID("BleedPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Color color = Color.RED.cpy();

    public BleedPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = PowerType.DEBUFF;
        this.isTurnBased = false;
        this.loadRegion("brutality");
        updateDescription();
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            this.addToBot(new LoseHPAction(owner, owner, amount));
        }
    }

    @Override
    public int onAttacked(DamageInfo info, int damageAmount) {
        if (info.owner != owner && DamageModifierManager.getDamageMods(info).stream().noneMatch(m -> m instanceof RangedDamage)) {
            this.addToBot(new ApplyPowerAction(info.owner, owner, new BloodlustPower(info.owner, amount)));
        }
        return damageAmount;
    }

    @Override
    public void updateDescription() {
        if (owner instanceof AbstractPlayer) {
           this.description = DESCRIPTIONS[2] + amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        }
    }

    @Override
    public AbstractPower makeCopy() {
        return new BleedPower(owner, amount);
    }

    @Override
    public int getHealthBarAmount() {
        return amount;
    }

    @Override
    public Color getColor() {
        return color;
    }
}
