package com.ruraaratech.p4dafrica.Document.service;

import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Plan;

import java.util.List;

public interface PlanService {
    Plan add(FileRequest file);
    Plan get(long fileId);
    List<Plan> getAll(long sectorId);
}
