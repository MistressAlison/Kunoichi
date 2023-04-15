package Kunoichi.powers;

import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;

import static Kunoichi.KunoichiMod.makeID;

public class AmbushMasterPower extends AbstractEasyPower {
    public static String POWER_ID = makeID(AmbushMasterPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = strings.NAME;
    public static final String[] DESCRIPTIONS = strings.DESCRIPTIONS;

    public AmbushMasterPower(AbstractCreature owner, int amount) {
        super(NAME, PowerType.BUFF, false, owner, amount);
        this.ID = POWER_ID;
        this.loadRegion("nightmare");
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (!AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flash();
            Wiz.forAllMonstersLiving(mon -> Wiz.applyToEnemy(mon, new ExposedPower(mon, amount)));
        }
    }

    @Override
    public void updateDescription() {
        this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
    }
}
