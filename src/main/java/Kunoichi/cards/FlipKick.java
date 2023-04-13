package Kunoichi.cards;

import Kunoichi.actions.DoIfAction;
import Kunoichi.actions.InvertFlipAction;
import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.cards.interfaces.SkillAnimationAttack;
import Kunoichi.util.CardArtRoller;
import Kunoichi.util.Wiz;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.green.Acrobatics;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import static Kunoichi.KunoichiMod.makeID;

public class FlipKick extends AbstractEasyCard implements SkillAnimationAttack {
    public final static String ID = makeID(FlipKick.class.getSimpleName());

    public FlipKick() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 14;
        baseMagicNumber = magicNumber = 2;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new InvertFlipAction(true, false));
        addToBot(new SFXAction("ATTACK_WHIFF_1", 0.2f));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.useJumpAnimation();
                this.isDone = true;
            }
        });
        addToBot(new WaitAction(0.3f));
        addToBot(new InvertFlipAction(true, false));
        addToBot(new WaitAction(0.4f));
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                p.useFastAttackAnimation();
                this.isDone = true;
            }
        });
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        addToBot(new DoIfAction(() -> Wiz.isUnaware(m), () -> {
            Wiz.applyToEnemyTop(m, new VulnerablePower(m, magicNumber, false));
            Wiz.applyToEnemyTop(m, new WeakPower(m, magicNumber, false));
        }));
    }

    @Override
    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);
    }

    @Override
    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (Wiz.anyMonsterUnaware()) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Acrobatics.ID;
    }
}