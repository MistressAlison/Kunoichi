package Kunoichi.powers;

import Kunoichi.actions.OpenerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Kunoichi.KunoichiMod.makeID;

public class TrailblazerPower extends AbstractEasyPower implements OpenerAction.OpenerModifier {
    public static String POWER_ID = makeID(TrailblazerPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    private int drawn;

    public TrailblazerPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("attackBurn");
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }

    @Override
    public int thresholdModifier() {
        return amount;
    }
}
