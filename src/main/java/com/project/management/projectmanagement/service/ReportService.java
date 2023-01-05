package com.project.management.projectmanagement.service;

import com.project.management.projectmanagement.model.Project;
import com.project.management.projectmanagement.repository.ProjectRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private ProjectRepository projectRepository;
    // export
    public void exportReport(OutputStream outputStream) throws JRException, IOException, SQLException{
        List<Project> projects =  projectRepository.findAll();
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
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}