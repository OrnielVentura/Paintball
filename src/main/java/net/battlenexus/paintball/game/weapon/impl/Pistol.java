package net.battlenexus.paintball.game.weapon.impl;

import net.battlenexus.paintball.game.weapon.AbstractWeapon;
import org.bukkit.Material;

public class Pistol extends AbstractWeapon {
    @Override
    public Material getBlueTeamMaterial() {
        return Material.ARROW;
    }

    @Override
    public Material getRedTeamMaterial() {
        return Material.ARROW;
    }

    @Override
    public Material getNormalMaterial() {
        return Material.ARROW;
    }

    @Override
    public String name() {
        return "Pistol";
    }

    @Override
    public int clipeSize() {
        return 10;
    }

    @Override
    public int startBullets() {
        return 160;
    }

    @Override
    public int damage() {
        return 2;
    }

    @Override
    public float strength() {
        return 1;
    }

    @Override
    public int reloadDelay() {
        return 2;
    }

    @Override
    public int getShotRate() {
        return 1;
    }

    @Override
    public int getFireDelay() {
        return 500;
    }

    @Override
    public Material getReloadItem() {
        return Material.FLINT;
    }
}