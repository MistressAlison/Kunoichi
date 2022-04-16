package Ordnance.cards;

import Ordnance.cards.abstracts.AbstractEasyCard;
import Ordnance.powers.FireAmmoPower;
import Ordnance.util.Wiz;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Ordnance.OrdnanceMod.makeID;

public class FireCell extends AbstractEasyCard {
    public final static String ID = makeID("FireCell");
    // intellij stuff skill, self, basic, , ,  5, 3, ,

    public FireCell() {
        super(ID, 1, CardType.SKILL, CardRarity.COMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        Wiz.applyToSelf(new FireAmmoPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}