package com.project.management.projectmanagement.repository;

import com.project.management.projectmanagement.model.Project;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    public List<Project> findAllByOrderByProjectNameAsc();
    public List<Project> findByStartDateBetweenOrderByEndDateAsc(Date startDate, Date endDate);

    public default JasperPrint exportPDF(String reportFormat) throws SQLException, FileNotFoundException, JRException {
        try{
            List<Project> projects =  findAll();
            File file = ResourceUtils.getFile("classpath:projects.jrxml");
            JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projects);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "CNS Project Management");
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//            if (reportFormat.equalsIgnoreCase("html")) {
//                JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\projects.html");
//            }
//            if (reportFormat.equalsIgnoreCase("pdf")) {
//                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projects.pdf");
//            }
            return jasperPrint;
        }catch (Exception e){
            //log.error('Error');
            return  null;
        }
    }
}
