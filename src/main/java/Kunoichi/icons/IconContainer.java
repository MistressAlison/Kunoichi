package Kunoichi.icons;

import Kunoichi.KunoichiMod;
import Kunoichi.util.TexLoader;
import com.evacipated.cardcrawl.mod.stslib.icons.AbstractCustomIcon;

public class IconContainer {

    public static class RangedIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Ranged");
        private static AbstractCustomIcon singleton;

        public RangedIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Ranged.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new RangedIcon();
            }
            return singleton;
        }
    }

    public static class FireIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Fire");
        private static AbstractCustomIcon singleton;

        public FireIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Fire.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new FireIcon();
            }
            return singleton;
        }
    }

    public static class BleedIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Bleed");
        private static AbstractCustomIcon singleton;

        public BleedIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Bleed.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new BleedIcon();
            }
            return singleton;
        }
    }

    public static class ElectricIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Electric");
        private static AbstractCustomIcon singleton;

        public ElectricIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Electric.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new ElectricIcon();
            }
            return singleton;
        }
    }

    public static class IceIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Ice");
        private static AbstractCustomIcon singleton;

        public IceIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Ice.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new IceIcon();
            }
            return singleton;
        }
    }

    public static class ParalysisIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Paralysis");
        private static AbstractCustomIcon singleton;

        public ParalysisIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Paralysis.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new ParalysisIcon();
            }
            return singleton;
        }
    }

    public static class PoisonIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Poison");
        private static AbstractCustomIcon singleton;

        public PoisonIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Poison.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new PoisonIcon();
            }
            return singleton;
        }
    }

    public static class PunctureIcon extends AbstractCustomIcon {
        public static final String ID = KunoichiMod.makeID("Puncture");
        private static AbstractCustomIcon singleton;

        public PunctureIcon() {
            super(ID, TexLoader.getTexture("KunoichiResources/images/icons/Puncture.png"));
        }

        public static AbstractCustomIcon get()
        {
            if (singleton == null) {
                singleton = new PunctureIcon();
            }
            return singleton;
        }
    }
}
