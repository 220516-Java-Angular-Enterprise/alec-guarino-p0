package com.revature.webstore.models;

public class Replica {
    String id;
    boolean takeUnderBarrelAttachment;
    boolean takeGeneralAttachment;
    boolean takeSightAttachment;

    //<editor-fold desc="Get/Set">

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isTakeUnderBarrelAttachment() {
        return takeUnderBarrelAttachment;
    }

    public void setTakeUnderBarrelAttachment(boolean takeUnderBarrelAttachment) {
        this.takeUnderBarrelAttachment = takeUnderBarrelAttachment;
    }

    public boolean isTakeGeneralAttachment() {
        return takeGeneralAttachment;
    }

    public void setTakeGeneralAttachment(boolean takeGeneralAttachment) {
        this.takeGeneralAttachment = takeGeneralAttachment;
    }

    public boolean isTakeSightAttachment() {
        return takeSightAttachment;
    }

    public void setTakeSightAttachment(boolean takeSightAttachment) {
        this.takeSightAttachment = takeSightAttachment;
    }

    //</editor-fold>

}
