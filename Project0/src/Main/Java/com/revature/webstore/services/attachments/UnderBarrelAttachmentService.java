package com.revature.webstore.services.attachments;

import com.revature.webstore.DatabaseAccess.attachments.GeneralAttachmentDAO;
import com.revature.webstore.DatabaseAccess.attachments.UnderBarrelAttachmentDAO;
import com.revature.webstore.models.attachments.GeneralAttachment;
import com.revature.webstore.models.attachments.UnderBarrelAttachment;

import java.util.List;

public class UnderBarrelAttachmentService {
    private final UnderBarrelAttachmentDAO underBarrelAttachmentDAO;

    public UnderBarrelAttachmentService(UnderBarrelAttachmentDAO underBarrelAttachmentDAO) {
        this.underBarrelAttachmentDAO = underBarrelAttachmentDAO;
    }

    public void register(UnderBarrelAttachment product) {
        underBarrelAttachmentDAO.save(product);
    }

    public List<UnderBarrelAttachment> getAll() {
        return underBarrelAttachmentDAO.getAll();
    }

    public boolean getExists(String column, String input){
        return underBarrelAttachmentDAO.getExistsInColumnByString(column, input);
    }
}
