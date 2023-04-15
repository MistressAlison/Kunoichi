package Kunoichi.patches;

import Kunoichi.powers.TirelessSpiritPower;
import Kunoichi.util.Wiz;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.EnergyManager;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import javassist.CtBehavior;

public class CardCostPatches {
    @SpirePatch2(clz = AbstractCard.class, method = "hasEnoughEnergy")
    public static class CardCostPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static SpireReturn<?> bePlayable() {
            TirelessSpiritPower power = (TirelessSpiritPower) Wiz.adp().getPower(TirelessSpiritPower.POWER_ID);
            if (power != null && power.isActive()) {
                return SpireReturn.Return(true);
            }
            return SpireReturn.Continue();
        }

        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.FieldAccessMatcher(EnergyPanel.class, "totalCount");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }

    @SpirePatch2(clz = AbstractPlayer.class, method = "useCard")
    public static class ConsumeSnowPatch {
        @SpireInsertPatch(locator = Locator.class)
        public static void useSnow(AbstractPlayer __instance, AbstractCard c) {
            if (__instance.hasPower(TirelessSpiritPower.POWER_ID) && c.costForTurn > 0) {
                if (c.costForTurn > EnergyPanel.getCurrentEnergy()) {
                    __instance.getPower(TirelessSpiritPower.POWER_ID).onSpecificTrigger();
                }
            }
        }
        public static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctBehavior) throws Exception {
                Matcher m = new Matcher.MethodCallMatcher(EnergyManager.class, "use");
                return LineFinder.findInOrder(ctBehavior, m);
            }
        }
    }
}
