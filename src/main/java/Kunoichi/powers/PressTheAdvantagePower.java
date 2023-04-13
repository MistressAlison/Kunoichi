package Kunoichi.powers;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Kunoichi.KunoichiMod.makeID;

public class PressTheAdvantagePower extends AbstractEasyPower {
    public static String POWER_ID = makeID(PressTheAdvantagePower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    private int drawn;

    public PressTheAdvantagePower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("juggernaut");
    }

    @Override
    public void atStartOfTurn() {
        drawn = 0;
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        drawn++;
        if (drawn > 5) {
            flash();
            addToBot(new GainBlockAction(owner, amount));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
