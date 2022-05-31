package com.revature.webstore.services.attachments;

import com.revature.webstore.DatabaseAccess.attachments.SightAttachmentDAO;
import com.revature.webstore.models.attachments.SightAttachment;

import java.util.List;

public class SightAttachmentService {
    private final SightAttachmentDAO sightAttachmentDAO;

    public SightAttachmentService(SightAttachmentDAO sightAttachmentDAO) {
        this.sightAttachmentDAO = sightAttachmentDAO;
    }

    public void register(SightAttachment product) {
        sightAttachmentDAO.save(product);
    }

    public List<SightAttachment> getAll() {
        return sightAttachmentDAO.getAll();
    }

    public boolean getExists(String column, String input){
        return sightAttachmentDAO.getExistsInColumnByString(column, input);
    }
}
