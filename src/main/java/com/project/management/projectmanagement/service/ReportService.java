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


//    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
//        String path = "C:\\Users\\hossain\\Desktop\\Report";
//        List<Project> projects = projectRepository.findAll();
//        //load file and compile it
//        File file = ResourceUtils.getFile("classpath:projects.jrxml");
//        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(projects);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy", "CNS Project Management");
//        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
//        if (reportFormat.equalsIgnoreCase("html")) {
//            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\projects.html");
//        }
//        if (reportFormat.equalsIgnoreCase("pdf")) {
//            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\projects.pdf");
//        }
//
//        return "report generated in path : " + path;
//    }
//    private JasperPrint getJasperPrint(List<Project> projects, String resourceLocation) throws FileNotFoundException, JRException {
//        File file = ResourceUtils.getFile(resourceLocation);
//        JasperReport jasperReport = JasperCompileManager
//                .compileReport(file.getAbsolutePath());
//        JRBeanCollectionDataSource dataSource = new
//                JRBeanCollectionDataSource(projects);
//        Map<String, Object> parameters = new HashMap<>();
//        parameters.put("createdBy","Hossain");
//
//        JasperPrint jasperPrint = JasperFillManager
//                .fillReport(jasperReport,parameters,dataSource);
//
//        return jasperPrint;
//    }
//
//
//    private Path getUploadPath(String fileFormat, JasperPrint jasperPrint, String fileName) throws IOException, JRException {
//        String uploadDir = StringUtils.cleanPath("./generated-reports");
//        Path uploadPath = Paths.get(uploadDir);
//        if (!Files.exists(uploadPath)){
//            Files.createDirectories(uploadPath);
//        }
//        //generate the report and save it in the just created folder
//        if (fileFormat.equalsIgnoreCase("pdf")){
//            JasperExportManager.exportReportToPdfFile(
//                    jasperPrint, uploadPath+fileName
//            );
//        }
//
//        return uploadPath;
//    }
//
//    private String getPdfFileLink(String uploadPath){
//        return uploadPath+"/"+"projects.pdf";
//    }
//    public String generateReport(String fileFormat) throws JRException, IOException {
//        List<Project> projects = projectRepository.findAll();
//        //load the file and compile it
//        String resourceLocation = "classpath:projects.jrxml";
//        JasperPrint jasperPrint = getJasperPrint(projects,resourceLocation);
//        //create a folder to store the report
//        String fileName = "/"+"projects.pdf";
//        Path uploadPath = getUploadPath(fileFormat, jasperPrint, fileName);
//        //create a private method that returns the link to the specific pdf file
//
//        return getPdfFileLink(uploadPath.toString());
//    }

    // export
    public void exportReport(OutputStream outputStream) throws JRException, IOException, SQLException{
        JasperPrint jasperPrint = projectRepository.exportPDF("pdf");
        JasperExportManager.exportReportToPdfStream(jasperPrint,outputStream);
    }
}