package Kunoichi.powers;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Kunoichi.KunoichiMod.makeID;

public class WindAndSparkPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(WindAndSparkPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public WindAndSparkPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("burst");
    }

    @Override
    public void onCardDraw(AbstractCard card) {
        if (card.cost == -2 || card.costForTurn == -2) {
            flash();
            Wiz.forAllMonstersLiving(m -> {
                Wiz.applyToEnemy(m, new ShockPower(m, amount));
            });
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
