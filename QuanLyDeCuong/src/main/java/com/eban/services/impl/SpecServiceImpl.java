/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eban.DTO.SearchResultDTO;
import com.eban.DTO.SpecificationDTO;
import com.eban.DTO.SubjectDTO;
import com.eban.DTO.TypeofspecifiDTO;
import com.eban.DTO.UserDTO;
import com.eban.pojo.Gradingsheet;
import com.eban.pojo.Specgrande;
import com.eban.pojo.Specification;
import com.eban.pojo.Subject;
import com.eban.pojo.Typeofspecifi;
import com.eban.pojo.User;
import com.eban.repositories.SpecRepocitory;
import com.eban.services.SpecService;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Admin
 */
@Service
public class SpecServiceImpl implements SpecService {

    @Autowired
    private SpecRepocitory specRepo;

    @Autowired
    private Cloudinary cloudinary;

    @Override
    public List<Specification> getListSpec(Map<String, String> params) {
        return this.specRepo.getListSpec(params);
    }

    @Override
    public Specification getSpecById(int i) {
        return this.specRepo.getSpecById(i);
    }

    @Override
    public void addSpec(Specification s) {
        // Nếu không có file, tiến hành thêm Specification vào database
        if (s.getFile() == null || s.getFile().isEmpty()) {
            this.specRepo.addSpec(s);
        } else {
            // Nếu có file, tiến hành upload file lên Cloudinary trước khi thêm Specification
            try {
                String fileName = s.getFile().getOriginalFilename();
                if (fileName != null && (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".pdf"))) {
                    Map res = this.cloudinary.uploader().upload(s.getFile().getBytes(), ObjectUtils.asMap("resource_type", "raw", "public_id", fileName));
                    String uploadedUrl = res.get("secure_url").toString();
                    String fileUrl = uploadedUrl.replace(uploadedUrl.substring(uploadedUrl.lastIndexOf("/")), "/" + fileName);
                    s.setFileSpec(fileUrl);
                    this.specRepo.addSpec(s);
                } else {
                    throw new IOException("Invalid file format. Only .doc, .docx, and .pdf are allowed.");
                }
            } catch (IOException ex) {
                Logger.getLogger(SpecServiceImpl.class.getName()).log(Level.SEVERE, "Error uploading file to Cloudinary", ex);
                throw new RuntimeException("File upload failed.");
            }
        }
    }

    @Override
    public boolean updateSpec(Specification s) {
        return this.specRepo.updateSpec(s);
    }

    @Override
    public boolean deleteSpec(int i) {
        return this.specRepo.deleteSpec(i);
    }

    @Override
    public List<Specification> getSpecsBySubjectId(int i) {
        return this.specRepo.getSpecsBySubjectId(i);
    }

    //Chuyển đổi DTO 
    private SpecificationDTO toSpecificationDTO(Specification specification) {
        SpecificationDTO dto = new SpecificationDTO();
        dto.setIdSpec(specification.getIdSpec());
        dto.setNameSpec(specification.getNameSpec());
        dto.setCredit(specification.getCredit());
        dto.setContent(specification.getContent());
        dto.setDateCreate(specification.getDateCreate());
        dto.setFileSpec(specification.getFileSpec());
        dto.setStatus(specification.getStatus());
        dto.setAuthor(toUserDTO(specification.getAuthorID()));
        dto.setSubject(toSubjectDTO(specification.getSubjectID()));
        dto.setTypeofspecifi(toTypeofspecifiDTO(specification.getTypeSpecID()));

        return dto;
    }

    private UserDTO toUserDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO dto = new UserDTO();
        dto.setIdUser(user.getIdUser());
        dto.setUsername(user.getUsername());
        dto.setFirstname(user.getFirstname());
        dto.setLastname(user.getLastname());
        dto.setEmail(user.getEmail());
        dto.setAvatar(user.getAvatar());
        return dto;
    }

    private SubjectDTO toSubjectDTO(Subject subject) {
        if (subject == null) {
            return null;
        }
        SubjectDTO dto = new SubjectDTO();
        dto.setIdSubject(subject.getIdSubject());
        dto.setNameSubject(subject.getNameSubject());
        return dto;
    }

    private TypeofspecifiDTO toTypeofspecifiDTO(Typeofspecifi typeofspecifi) {
        if (typeofspecifi == null) {
            return null;
        }
        TypeofspecifiDTO dto = new TypeofspecifiDTO();
        dto.setIdType(typeofspecifi.getIdType());
        dto.setNameType(typeofspecifi.getNameType());
        return dto;
    }

    @Override
    public SearchResultDTO<SpecificationDTO> searchSpecifications(String nameSpec, Integer credit, Integer page, String teacherName, Integer subjectId, Integer idCourse) {
        List<Specification> specifications = this.specRepo.searchSpecifications(nameSpec, credit, page, teacherName, subjectId, idCourse);
        List<SpecificationDTO> specificationDTOs = new ArrayList<>();
        long totalCount = this.specRepo.countSpecifications(nameSpec, credit, teacherName, subjectId , idCourse);
        for (Specification specification : specifications) {
            specificationDTOs.add(toSpecificationDTO(specification));
        }
        return new SearchResultDTO<>(specificationDTOs, totalCount);

    }

    @Override
    public Gradingsheet findGradingSheetByName(String name) {
        return this.specRepo.findGradingSheetByName(name);
    }

    @Override
    public void addGradingSheet(Gradingsheet gradingsheet) {
        this.specRepo.addGradingSheet(gradingsheet);
    }

    @Override
    public void addSpecgrande(Specgrande specgrande) {
        this.specRepo.addSpecgrande(specgrande);
    }

    @Override
    public List<Specgrande> getSpecgrandeBySpecId(int id) {
        return this.specRepo.getSpecgrandeBySpecId(id);
    }

}
