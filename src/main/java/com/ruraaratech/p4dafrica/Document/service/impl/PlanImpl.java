package com.ruraaratech.p4dafrica.Document.service.impl;

import com.ruraaratech.p4dafrica.Document.dao.BudgetDao;
import com.ruraaratech.p4dafrica.Document.dao.PlanDao;
import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.model.Plan;
import com.ruraaratech.p4dafrica.Document.service.PlanService;
import com.ruraaratech.p4dafrica.firebase.File.FileService;
import com.ruraaratech.p4dafrica.location.model.District;
import com.ruraaratech.p4dafrica.location.model.Sector;
import com.ruraaratech.p4dafrica.location.service.DistrictService;
import com.ruraaratech.p4dafrica.location.service.SectorService;
import com.ruraaratech.p4dafrica.sysUser.model.SysUser;
import com.ruraaratech.p4dafrica.sysUser.security.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Service
public class PlanImpl implements PlanService {
    @Autowired
    PlanDao planDao;
    @Autowired
    SectorService sectorService;
    @Autowired
    DistrictService districtService;
    @Autowired
    FileService fileService;
    @Autowired
    MyUserDetailsService userService;

    private final Logger logger = LoggerFactory.getLogger(PlanImpl.class);
    @Override
    public Plan add(MultipartFile multiPart, FileRequest file) throws IOException {
        SysUser user =userService.currentUser();
        Plan plan =new Plan();
        Sector sector =sectorService.get(file.getSectorId());
        District district =districtService.getById(sector.getDistrictId());
        plan.setCountry(district.getCountryId());
        plan.setDistrict(sector.getDistrictId());
        plan.setSector(file.getSectorId());
        String fileName =fileService.generateFileName(multiPart);
        plan.setFile(fileName);
        plan.setYear(file.getYear());
        plan.setTittle(file.getTitle());
        logger.info("uploading file....");
        URL url =fileService.uploadFile(multiPart, fileName);
        plan.setUrl(url);
        plan.setDateCreated(new Date());
        plan.setDateUpdated(new Date());
        plan.setCreatedBy(user.getUserId());
        plan.setUpdatedBy(user.getUserId());
        return planDao.save(plan);
    }

    @Override
    public Plan get(long fileId) {
        return null;
    }

    @Override
    public List<Plan> getAll(long sectorId) {
        return null;
    }

    @Override
    public List<Plan> getAll() {
        return planDao.findAll();
    }
}
