package com.ruraaratech.p4dafrica.Document.service;

import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PlanService {
    Plan add(MultipartFile multiPart, FileRequest file) throws IOException;
    Plan get(long fileId);
    List<Plan> getAll(long sectorId);
}
