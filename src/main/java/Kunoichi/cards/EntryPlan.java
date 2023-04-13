package Kunoichi.cards;

import Kunoichi.actions.PredicateDrawPileToHandAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.green.EscapePlan;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class EntryPlan extends AbstractEasyCard {
    public final static String ID = makeID(EntryPlan.class.getSimpleName());

    public EntryPlan() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SFXAction("SCENE_TORCH_EXTINGUISH", 0.2f));
        addToBot(new PredicateDrawPileToHandAction(magicNumber, c -> c.type == CardType.ATTACK));
        addToBot(new PredicateDrawPileToHandAction(magicNumber, c -> c.type == CardType.SKILL));
    }

    @Override
    public void upp() {
        isInnate = true;
        uDesc();
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return EscapePlan.ID;
    }
}