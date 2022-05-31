package com.revature.webstore.models.attachments;

public class GeneralAttachment {
    String id;
    boolean hasLight;
    boolean hasLaser;

    //<editor-fold desc="Get/Set">

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isHasLight() {
        return hasLight;
    }

    public void setHasLight(boolean hasLight) {
        this.hasLight = hasLight;
    }

    public boolean isHasLaser() {
        return hasLaser;
    }

    public void setHasLaser(boolean hasLaser) {
        this.hasLaser = hasLaser;
    }


    //</editor-fold>

}
