package com.revature.webstore.services.attachments;

import com.revature.webstore.DatabaseAccess.ProductDAO;
import com.revature.webstore.DatabaseAccess.attachments.GeneralAttachmentDAO;
import com.revature.webstore.models.Product;
import com.revature.webstore.models.attachments.GeneralAttachment;

import java.util.List;

public class GeneralAttachmentService {

    private final GeneralAttachmentDAO generalAttachmentDAO;

    public GeneralAttachmentService(GeneralAttachmentDAO generalAttachmentDAO) {
        this.generalAttachmentDAO = generalAttachmentDAO;
    }

    public void register(GeneralAttachment product) {
        generalAttachmentDAO.save(product);
    }

    public List<GeneralAttachment> getAll() {
        return generalAttachmentDAO.getAll();
    }

    public boolean getExists(String column, String input){
        return generalAttachmentDAO.getExistsInColumnByString(column, input);
    }
}
