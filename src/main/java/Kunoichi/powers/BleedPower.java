package Kunoichi.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.LoseHPAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

import static Kunoichi.KunoichiMod.makeID;

public class BleedPower extends AbstractEasyPower {
    public static String TEXT_ID = makeID(BleedPower.class.getSimpleName());
    public static PowerStrings strings = CardCrawlGame.languagePack.getPowerStrings(TEXT_ID);
    public AbstractCreature source;

    public BleedPower(AbstractCreature owner, AbstractCreature source, int amount) {
        super(strings.NAME, PowerType.DEBUFF, true, owner, amount);
        this.source = source;
        this.loadRegion("brutality");
    }

    public void atStartOfTurn() {
        if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT && !AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            this.flashWithoutSound();
            this.addToBot(new LoseHPAction(this.owner, this.source, this.amount, AbstractGameAction.AttackEffect.POISON));
        }
    }

    public void updateDescription() {
        if (this.owner != null && !this.owner.isPlayer) {
            this.description = DESCRIPTIONS[2] + this.amount + DESCRIPTIONS[1];
        } else {
            this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1];
        }
    }
}
