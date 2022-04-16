package Ordnance.actions;

import Ordnance.powers.AmmoPower;
import Ordnance.powers.BleedPower;
import Ordnance.powers.EmbeddedAmmoPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class RipArrowAction extends AbstractGameAction {

    public RipArrowAction(AbstractCreature source, AbstractCreature target, int amount) {
        this.source = source;
        this.target = target;
        this.amount = amount;
    }

    @Override
    public void update() {
        EmbeddedAmmoPower pow = (EmbeddedAmmoPower) target.getPower(EmbeddedAmmoPower.POWER_ID);
        if (pow != null) {
            if (pow.amount < amount) {
                amount = pow.amount;
            }
            if (amount > 0) {
                this.addToBot(new ReducePowerAction(target, source, pow, amount));
                this.addToBot(new ApplyPowerAction(target, source, new BleedPower(target, amount)));
                this.addToBot(new ApplyPowerAction(source, source, new AmmoPower(source, amount)));
            }
        }
        this.isDone = true;
    }
}
