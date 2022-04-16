package Ordnance.powers;

import Ordnance.OrdnanceMod;
import Ordnance.cards.abstracts.AbstractRangedCard;
import basemod.interfaces.CloneablePowerInterface;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class AmmoPower extends AbstractEasyPower implements CloneablePowerInterface {

    public static final String POWER_ID = OrdnanceMod.makeID("AmmoPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AmmoPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("painfulStabs");
        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card instanceof AbstractRangedCard && ((AbstractRangedCard) card).ammoRequirement > 0) {
            flash();
            this.amount -= ((AbstractRangedCard) card).ammoRequirement;
            if (this.amount < 0) {
                this.amount = 0;
            }
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0];
    }

    @Override
    public AbstractPower makeCopy() {
        return new AmmoPower(owner, amount);
    }
}
