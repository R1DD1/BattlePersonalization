package net.framedev.personalization.user;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Particle;

public class Customization {
    public String getDeathMsg() {
        return deathMsg;
    }

    public void setDeathMsg(String deathMsg) {
        this.deathMsg = deathMsg;
    }

    public Effect getHitParticle() {
        return hitParticle;
    }

    public void setHitParticle(Effect hitParticle) {
        this.hitParticle = hitParticle;
    }

    public Effect getKillParticle() {
        return killParticle;
    }

    public void setKillParticle(Effect killParticle) {
        this.killParticle = killParticle;
    }

    private String deathMsg = "";
    private Effect hitParticle = Effect.GHAST_SHOOT;
    private Effect killParticle = Effect.DRAGON_BREATH;
    private Particle arrowTrail = Particle.HEART;

    public Particle getArrowTrail() {
        return arrowTrail;
    }

    public void setArrowTrail(Particle arrowTrail) {
        this.arrowTrail = arrowTrail;
    }
}
