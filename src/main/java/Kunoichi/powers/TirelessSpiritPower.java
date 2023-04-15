package Kunoichi.powers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Kunoichi.KunoichiMod.makeID;

public class TirelessSpiritPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(TirelessSpiritPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;
    private final Color greenColor = new Color(0.0F, 1.0F, 0.0F, 1.0F);
    private int triggers;

    public TirelessSpiritPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("controlled_change");
    }

    @Override
    public void renderAmount(SpriteBatch sb, float x, float y, Color c) {
        this.greenColor.a = c.a;
        c = this.greenColor;
        FontHelper.renderFontRightTopAligned(sb, FontHelper.powerAmountFont, Integer.toString(this.amount-triggers), x, y, this.fontScale, c);
    }

    @Override
    public void onSpecificTrigger() {
        triggers++;
    }

    public boolean isActive() {
        return amount > triggers;
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            this.description = DESCRIPTIONS[0];
        } else {
            this.description = DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2];
        }
    }
}
