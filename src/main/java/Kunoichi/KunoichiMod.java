package Kunoichi;

import Kunoichi.cards.cardvars.Info;
import Kunoichi.powers.EvasionPower;
import Kunoichi.powers.ExposedPower;
import Kunoichi.powers.ShockPower;
import Kunoichi.powers.TirelessSpiritPower;
import Kunoichi.util.KeywordManager;
import Kunoichi.util.Wiz;
import basemod.AutoAdd;
import basemod.BaseMod;
import basemod.helpers.CardBorderGlowManager;
import basemod.helpers.RelicType;
import basemod.interfaces.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.evacipated.cardcrawl.mod.stslib.Keyword;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.google.gson.Gson;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.CardHelper;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import Kunoichi.cards.cardvars.SecondDamage;
import Kunoichi.cards.cardvars.SecondMagicNumber;
import Kunoichi.relics.AbstractEasyRelic;

import java.nio.charset.StandardCharsets;

@SuppressWarnings({"unused", "WeakerAccess"})
@SpireInitializer
public class KunoichiMod implements
        EditCardsSubscriber,
        EditRelicsSubscriber,
        EditStringsSubscriber,
        EditKeywordsSubscriber,
        EditCharactersSubscriber, PostInitializeSubscriber {

    public static final String modID = "Kunoichi";

    public static String makeID(String idText) {
        return modID + ":" + idText;
    }

    public static final Color DARK_GUNMETAL = CardHelper.getColor(18, 22, 47);

    public static final String SHOULDER1 = modID + "Resources/images/char/mainChar/shoulder2.png";
    public static final String SHOULDER2 = modID + "Resources/images/char/mainChar/shoulder1.png";
    public static final String CORPSE = modID + "Resources/images/char/mainChar/corpse.png";
    private static final String ATTACK_S_ART = modID + "Resources/images/512/bg_attack.png";
    private static final String SKILL_S_ART = modID + "Resources/images/512/bg_skill.png";
    private static final String POWER_S_ART = modID + "Resources/images/512/bg_power.png";
    private static final String ATTACK_L_ART = modID + "Resources/images/1024/bg_attack.png";
    private static final String SKILL_L_ART = modID + "Resources/images/1024/bg_skill.png";
    private static final String POWER_L_ART = modID + "Resources/images/1024/bg_power.png";
    private static final String CARD_ENERGY_S = modID + "Resources/images/512/energy.png";
    private static final String TEXT_ENERGY = modID + "Resources/images/512/text_energy.png";
    private static final String CARD_ENERGY_L = modID + "Resources/images/1024/energy.png";
    private static final String CHARSELECT_BUTTON = modID + "Resources/images/charSelect/charButton.png";
    private static final String CHARSELECT_PORTRAIT = modID + "Resources/images/charSelect/charBG3.png";

    public static final String ENABLE_CARD_BATTLE_TALK_SETTING = "enableCardBattleTalk";
    public static boolean enableCardBattleTalkEffect = false;

    public static final String CARD_BATTLE_TALK_PROBABILITY_SETTING = "cardTalkProbability";
    public static int cardTalkProbability = 10; //Out of 100

    public static final String ENABLE_DAMAGED_BATTLE_TALK_SETTING = "enableDamagedBattleTalk";
    public static boolean enableDamagedBattleTalkEffect = false;

    public static final String DAMAGED_BATTLE_TALK_PROBABILITY_SETTING = "damagedTalkProbability";
    public static int damagedTalkProbability = 20; //Out of 100

    public static final String ENABLE_PRE_BATTLE_TALK_SETTING = "enablePreBattleTalk";
    public static boolean enablePreBattleTalkEffect = false;

    public static final String PRE_BATTLE_TALK_PROBABILITY_SETTING = "preTalkProbability";
    public static int preTalkProbability = 50; //Out of 100

    public KunoichiMod() {
        BaseMod.subscribe(this);

        BaseMod.addColor(TheKunoichi.Enums.DARK_GUNMETAL_COLOR, DARK_GUNMETAL, DARK_GUNMETAL, DARK_GUNMETAL,
                DARK_GUNMETAL, DARK_GUNMETAL, DARK_GUNMETAL, DARK_GUNMETAL,
                ATTACK_S_ART, SKILL_S_ART, POWER_S_ART, CARD_ENERGY_S,
                ATTACK_L_ART, SKILL_L_ART, POWER_L_ART,
                CARD_ENERGY_L, TEXT_ENERGY);
    }

    public static String makePath(String resourcePath) {
        return modID + "Resources/" + resourcePath;
    }

    public static String makeImagePath(String resourcePath) {
        return modID + "Resources/images/" + resourcePath;
    }

    public static String makeRelicPath(String resourcePath) {
        return modID + "Resources/images/relics/" + resourcePath;
    }

    public static String makePowerPath(String resourcePath) {
        return modID + "Resources/images/powers/" + resourcePath;
    }

    public static String makeCardPath(String resourcePath) {
        return modID + "Resources/images/cards/" + resourcePath;
    }

    public static void initialize() {
        KunoichiMod thismod = new KunoichiMod();
    }

    @Override
    public void receiveEditCharacters() {
        BaseMod.addCharacter(new TheKunoichi(TheKunoichi.characterStrings.NAMES[1], TheKunoichi.Enums.THE_KUNOICHI),
                CHARSELECT_BUTTON, CHARSELECT_PORTRAIT, TheKunoichi.Enums.THE_KUNOICHI);
    }

    @Override
    public void receiveEditRelics() {
        new AutoAdd(modID)
                .packageFilter(AbstractEasyRelic.class)
                .any(AbstractEasyRelic.class, (info, relic) -> {
                    if (relic.color == null) {
                        BaseMod.addRelic(relic, RelicType.SHARED);
                    } else {
                        BaseMod.addRelicToCustomPool(relic, relic.color);
                    }
                    if (!info.seen) {
                        UnlockTracker.markRelicAsSeen(relic.relicId);
                    }
                });
    }

    @Override
    public void receiveEditCards() {
        BaseMod.addDynamicVariable(new Info());
        BaseMod.addDynamicVariable(new SecondMagicNumber());
        BaseMod.addDynamicVariable(new SecondDamage());
        new AutoAdd(modID)
                .packageFilter("Kunoichi.cards")
                .setDefaultSeen(true)
                .cards();
    }

    private void loadLangKeywords(String language) {
        Gson gson = new Gson();
        Keyword[] keywords = null;
        try {
            String json = Gdx.files.internal(modID + "Resources/localization/eng/Keywordstrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
            keywords = gson.fromJson(json, Keyword[].class);
        } catch (GdxRuntimeException e) {
            if (!e.getMessage().startsWith("File not found:")) {
                throw e;
            }
        }

        if (keywords != null) {
            for (Keyword keyword : keywords) {
                BaseMod.addKeyword(modID.toLowerCase(), keyword.PROPER_NAME, keyword.NAMES, keyword.DESCRIPTION);
                if(keyword.NAMES.length > 0 && !keyword.ID.isEmpty()) {
                    KeywordManager.setupKeyword(keyword.ID, makeID(keyword.NAMES[0]));
                }
            }
        }
    }

    @Override
    public void receiveEditKeywords() {
        String language = Settings.language.name().toLowerCase();
        loadLangKeywords("eng");
        if (!language.equals("eng")) {
            loadLangKeywords(language);
        }
    }

    private void loadLangStrings(String language) {
        String path = modID+"Resources/localization/" + language + "/";

        tryLoadStringsFile(CardStrings.class,path + "Cardstrings.json");
        tryLoadStringsFile(RelicStrings.class, path + "Relicstrings.json");
        tryLoadStringsFile(CharacterStrings.class, path + "Charstrings.json");
        tryLoadStringsFile(PowerStrings.class, path + "Powerstrings.json");
        tryLoadStringsFile(CardStrings.class, path + "CardModstrings.json");
        tryLoadStringsFile(CardStrings.class, path + "Chatterstrings.json");
        tryLoadStringsFile(CardStrings.class, path + "DamageModstrings.json");
        tryLoadStringsFile(UIStrings.class, path + "UIstrings.json");
    }

    private void tryLoadStringsFile(Class<?> stringType, String filepath) {
        try {
            BaseMod.loadCustomStringsFile(stringType, filepath);
        } catch (GdxRuntimeException e) {
            // Ignore file not found
            if (!e.getMessage().startsWith("File not found:")) {
                throw e;
            }
        }
    }

    @Override
    public void receiveEditStrings() {
        String language = Settings.language.name().toLowerCase();
        loadLangStrings("eng");
        if (!language.equals("eng")) {
            loadLangStrings(language);
        }
    }

    @Override
    public void receivePostInitialize() {
        BaseMod.addPower(ShockPower.class, ShockPower.POWER_ID);
        BaseMod.addPower(EvasionPower.class, EvasionPower.POWER_ID);
        BaseMod.addPower(ExposedPower.class, ExposedPower.POWER_ID);

        CardBorderGlowManager.addGlowInfo(new CardBorderGlowManager.GlowInfo() {
            private final Color PURPLE = new Color(-935526465);
            @Override
            public boolean test(AbstractCard c) {
                if (Wiz.adp().hasPower(TirelessSpiritPower.POWER_ID) && c.costForTurn > 0) {
                    return c.costForTurn > EnergyPanel.getCurrentEnergy();
                }
                return false;
            }

            @Override
            public Color getColor(AbstractCard c) {
                return PURPLE.cpy();
            }

            @Override
            public String glowID() {
                return TirelessSpiritPower.POWER_ID+"Glow";
            }
        });
    }
}
