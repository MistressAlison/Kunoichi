package Ordnance.util;

import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;

import java.util.ArrayList;

public class MinionHelper {
    public static final float[] xPos = {-750f, -1150f, -750f, -1150f};
    public static final float[] yPos = {0f, 0f, 100f, 100f};
    public static final ArrayList<AbstractMonster> minions = new ArrayList<>();

    public static float[] getPosition() {
        if (AbstractDungeon.player instanceof AbstractPlayerWithMinions) {
            for (int i = 0 ; i < xPos.length ; i++) {
                if (!occupied(i)) {
                    return new float[]{xPos[i],yPos[i]};
                }
            }
        }
        return new float[]{0,0};
    }

    private static boolean occupied(int i) {
        for (AbstractMonster m : ((AbstractPlayerWithMinions)AbstractDungeon.player).getMinions().monsters) {
            if (m.drawX == (float) Settings.WIDTH * 0.75F + xPos[i] * Settings.xScale && m.drawY == AbstractDungeon.floorY + yPos[i] * Settings.yScale) {
                return true;
            }
        }
        return false;
    }

    public static void updateMinions() {
        minions.clear();
        if (AbstractDungeon.player instanceof AbstractPlayerWithMinions) {
            minions.addAll(((AbstractPlayerWithMinions) AbstractDungeon.player).getMinions().monsters);
            int i = 0;
            for (AbstractMonster m : minions) {
                m.drawX = xPos[i];
                m.drawY = yPos[i];
                m.hb.move(xPos[i], yPos[i]);
                i++;
            }
        }
    }
}
