package Kunoichi.cards;

import Kunoichi.actions.DoIfAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.powers.CounterAOEDamagePower;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.unique.SpotWeaknessAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.red.Cleave;
import com.megacrit.cardcrawl.cards.red.Havoc;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

import static Kunoichi.KunoichiMod.makeID;

public class Retaliate extends AbstractEasyCard {
    public final static String ID = makeID(Retaliate.class.getSimpleName());

    public Retaliate() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 12;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DoIfAction(() -> Wiz.isAttacking(m),
                () -> Wiz.applyToSelfTop(new CounterAOEDamagePower(p, magicNumber)),
                () -> AbstractDungeon.effectList.add(new ThoughtBubble(p.dialogX, p.dialogY, 3.0F, SpotWeaknessAction.TEXT[0], true))));
    }

    @Override
    public void upp() {
        upgradeMagicNumber(4);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.anyMonsterAttacking()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.35f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Cleave.ID;
    }
}