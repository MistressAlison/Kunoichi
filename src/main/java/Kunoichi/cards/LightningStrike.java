package Kunoichi.cards;

import Kunoichi.cards.abstracts.AbstractEasyCard;
import Kunoichi.util.CardArtRoller;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.blue.Tempest;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static Kunoichi.KunoichiMod.makeID;

public class LightningStrike extends AbstractEasyCard {
    public final static String ID = makeID(LightningStrike.class.getSimpleName());

    public LightningStrike() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = damage = 6;
        baseSecondDamage = secondDamage = 6;
        tags.add(CardTags.STRIKE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, secondDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.LIGHTNING));
        addToBot(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.LIGHTNING));
    }

    @Override
    public void applyPowers() {
        secondDamage = baseSecondDamage;

        int tmp = baseDamage;
        baseDamage = baseSecondDamage;

        super.applyPowers();

        secondDamage = damage;
        baseDamage = tmp;

        super.applyPowers();

        isSecondDamageModified = (secondDamage != baseSecondDamage);
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        secondDamage = baseSecondDamage;

        int tmp = baseDamage;
        baseDamage = baseSecondDamage;

        super.calculateCardDamage(mo);

        secondDamage = damage;
        baseDamage = tmp;

        super.calculateCardDamage(null);

        isSecondDamageModified = (secondDamage != baseSecondDamage);
    }

    @Override
    public void upp() {
        upgradeDamage(2);
        upgradeSecondDamage(2);
    }

    @Override
    public CardArtRoller.ReskinInfo reskinInfo(String ID) {
        return new CardArtRoller.ReskinInfo(ID, 0.5f, 0.5f, 0.25f, 0.5f, false);
    }

    @Override
    public String cardArtCopy() {
        return Tempest.ID;
    }
}