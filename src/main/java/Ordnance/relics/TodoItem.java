package Ordnance.relics;

import Ordnance.TheMunitions;

import static Ordnance.OrdnanceMod.makeID;

public class TodoItem extends AbstractEasyRelic {
    public static final String ID = makeID("TodoItem");

    public TodoItem() {
        super(ID, RelicTier.STARTER, LandingSound.FLAT, TheMunitions.Enums.SANGUINE_RED_COLOR);
    }
}
