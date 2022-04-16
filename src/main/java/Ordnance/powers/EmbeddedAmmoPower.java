package Ordnance.powers;

import Ordnance.OrdnanceMod;
import basemod.interfaces.CloneablePowerInterface;
import com.evacipated.cardcrawl.mod.stslib.patches.NeutralPowertypePatch;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class EmbeddedAmmoPower extends AbstractPower implements CloneablePowerInterface {

    public static final String POWER_ID = OrdnanceMod.makeID("EmbeddedAmmoPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public EmbeddedAmmoPower(AbstractCreature owner, int amount) {
        this.ID = POWER_ID;
        this.name = NAME;
        this.owner = owner;
        this.amount = amount;
        this.type = NeutralPowertypePatch.NEUTRAL;
        this.isTurnBased = false;
        this.loadRegion("pressure_points");
        updateDescription();
    }

    @Override
    public void onDeath() {
        flash();
        this.addToBot(new ApplyPowerAction(AbstractDungeon.player, owner, new AmmoPower(AbstractDungeon.player, amount)));
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AmmoPower(owner, amount);
    }
}
