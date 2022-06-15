package com.ruraaratech.p4dafrica.Document.service;

import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BudgetService {
    Budget add(MultipartFile multiPart, FileRequest file) throws IOException;
    Budget get(long fileId);
    List<Budget> getAll(long sectorId);
}
