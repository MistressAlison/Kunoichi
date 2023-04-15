package Kunoichi.powers;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import static Kunoichi.KunoichiMod.makeID;

public class FearIsTheWeaknessPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(FearIsTheWeaknessPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public FearIsTheWeaknessPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("master_smite");
    }

    public void atStartOfTurnPostDraw() {
        this.flash();
        int str = 0;
        for (AbstractMonster m : AbstractDungeon.getMonsters().monsters) {
            if (Wiz.isAttacking(m)) {
                str += amount;
            }
        }
        if (str > 0) {
            addToBot(new ApplyPowerAction(owner, owner, new StrengthPower(owner, str)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
