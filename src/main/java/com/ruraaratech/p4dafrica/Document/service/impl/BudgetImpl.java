package com.ruraaratech.p4dafrica.Document.service.impl;

import com.ruraaratech.p4dafrica.Document.dao.BudgetDao;
import com.ruraaratech.p4dafrica.Document.dto.FileRequest;
import com.ruraaratech.p4dafrica.Document.model.Budget;
import com.ruraaratech.p4dafrica.Document.service.BudgetService;
import com.ruraaratech.p4dafrica.exceptions.ResourceNotFoundException;
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
public class BudgetImpl implements BudgetService {
    @Autowired BudgetDao budgetDao;
    @Autowired SectorService sectorService;
    @Autowired DistrictService districtService;
    @Autowired FileService fileService;
    @Autowired MyUserDetailsService userService;

    private final Logger logger = LoggerFactory.getLogger(BudgetImpl.class);
    @Override
    public Budget add(MultipartFile multiPart, FileRequest file) throws IOException {
        Sector sector =sectorService.get(file.getSectorId());
        Budget budget =new Budget();
        logger.info("check if budget exists exists....");
        Budget existingBudget =budgetDao.findBySectorAndYear(sector, file.getYear());
        if(existingBudget!=null){
            budget.setId(existingBudget.getId());
        }
        SysUser user =userService.currentUser();
        budget.setSector(sector);
        String fileName =fileService.generateFileName(multiPart);
        budget.setFile(fileName);
        budget.setYear(file.getYear());
        budget.setTitle(file.getTitle());
        logger.info("uploading file....");
        URL url =fileService.uploadFile(multiPart, fileName);
        logger.info("file uploaded....");
        budget.setUrl(url);
        budget.setDateCreated(new Date());
        budget.setDateUpdated(new Date());
        budget.setCreatedBy(user.getUserId());
        budget.setUpdatedBy(user.getUserId());
        return budgetDao.save(budget);
    }

    @Override
    public List<Budget> getByDistrict(long districtId) {
        District district =new District();
        district.setId(districtId);
        return budgetDao.findByDistrict(district);
    }

    @Override
    public Budget get(long fileId) {
        return budgetDao.findById(fileId).orElseThrow(()->new ResourceNotFoundException("budget not found, ID: "+fileId));

    }

    @Override
    public List<Budget> getAll(long sectorId) {
        return budgetDao.findBySector(sectorId);
    }

    @Override
    public List<Budget> getAll() {
        return budgetDao.findAllByOrderByDateCreatedDesc();
    }
}
