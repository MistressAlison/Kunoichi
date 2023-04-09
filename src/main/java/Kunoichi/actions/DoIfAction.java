package Kunoichi.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.function.Supplier;

public class DoIfAction extends AbstractGameAction {
    private Supplier<Boolean> check;
    private Runnable process;

    public DoIfAction(Supplier<Boolean> check, Runnable process) {
        this.source = AbstractDungeon.player;
        this.actionType = ActionType.SPECIAL;
        this.check = check;
        this.process = process;
    }

    @Override
    public void update() {
        if (check.get()) {
            process.run();
        }
        this.isDone = true;
    }
}
