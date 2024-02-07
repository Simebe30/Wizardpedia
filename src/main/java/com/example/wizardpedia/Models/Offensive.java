package com.example.wizardpedia.Models;

public enum Offensive {

    SHADOWBLADE_WHISPERER("Shadowblade Whisperer", 30),
    INFERNO_HAVOC_ROD("Inferno Havoc Rod", 120),
    CYCLONE_RIPPER_CLAWS("Cyclone Ripper Claws", 75),
    FROSTBITE_EXECUTIONER("Frostbite Executioner", 180),
    ASTRAL_IGNITION_BOWCASTER("Astral Ignition Bowcaster", 250),
    ARCANE_ANOMALY_SCEPTER("Arcane Anomaly Scepter", 50),
    PHANTOM_DOOM_SABER("Phantom Doom Saber", 300),
    VOID_IMPLOSION_TRIDENT("Void Implosion Trident", 280),
    INFERNO_INCINERATOR_MAUL("Inferno Incinerator Maul", 90),
    STARFALL_SURGE_SCIMITAR("Starfall Surge Scimitar", 220),
    VENOMOUS_DAGGERSTRIKE("Venomous Daggerstrike", 15),
    SKYSHATTER_ARBALEST("Skyshatter Arbalest", 350),
    SINGULARITY_GRAVITON_GRIMOIRE("Singularity Graviton Grimoire", 360),
    BLAZE_SERPENT_WHIP("Blaze Serpent Whip", 160),
    MALEVOLENT_SHADOWS_SKULLCRUSHER("Malevolent Shadows Skullcrusher", 400);

    private final String displayName;
    private final int powerLevel;

    Offensive(String displayName, int powerLevel) {
        this.displayName = displayName;
        this.powerLevel = powerLevel;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getPowerLevel() {
        return powerLevel;
    }

}
