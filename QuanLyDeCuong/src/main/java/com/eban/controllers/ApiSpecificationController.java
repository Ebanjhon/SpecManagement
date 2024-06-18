/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.SpecificationDTO;
import com.eban.pojo.Hoidong;
import com.eban.pojo.Specification;
import com.eban.pojo.Subject;
import com.eban.pojo.Typeofspecifi;
import com.eban.pojo.User;
import com.eban.services.SpecService;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@RestController
@RequestMapping("/api")
public class ApiSpecificationController {

    @Autowired
    private SpecService specService;

    @GetMapping("/specifications")
    @CrossOrigin
    public ResponseEntity<List<Specification>> listSpec(@RequestParam Map<String, String> params) {
        return new ResponseEntity<>(this.specService.getListSpec(params), HttpStatus.OK);
    }

    @GetMapping(path = "/specifications/{idSpec}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Specification> retrieve(@PathVariable(value = "idSpec") int id) {
        return new ResponseEntity<>(this.specService.getSpecById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/specifications/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Logger.getLogger(ApiSpecificationController.class.getName()).log(Level.INFO, "Params: {0}", params);
        Logger.getLogger(ApiSpecificationController.class.getName()).log(Level.INFO, "Files: {0}", file.length);

        Specification spec = new Specification();
        spec.setNameSpec(params.get("nameSpec"));
        spec.setCredit(Integer.parseInt(params.get("credit")));
        spec.setContent(params.get("content"));
        spec.setDateCreate(new Date());
        spec.setStatus(params.get("status"));

        if (params.get("subjectId") != null) {
            Subject subject = new Subject();
            subject.setIdSubject(Integer.parseInt(params.get("subjectId")));
            spec.setSubjectID(subject);
        }
        if (params.get("typeSpecId") != null) {
            Typeofspecifi typeSpec = new Typeofspecifi();
            typeSpec.setIdType(Integer.parseInt(params.get("typeSpecId")));
            spec.setTypeSpecID(typeSpec);
        }
        if (params.get("authorId") != null) {
            User author = new User();
            author.setIdUser(Integer.parseInt(params.get("authorId")));
            spec.setAuthorID(author);
        }

        if (params.get("hoiDongID") != null) {
            Hoidong hoiDong = new Hoidong();
            hoiDong.setIdHoiDong(Integer.parseInt(params.get("hoiDongID")));
            spec.setHoiDongID(hoiDong);
        }

        if (file.length > 0) {
            String fileName = file[0].getOriginalFilename();
            if (fileName != null && (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".pdf"))) {
                spec.setFile(file[0]);
            } else {
                return new ResponseEntity<>("Invalid file format. Only .doc, .docx, and .pdf are allowed.", HttpStatus.BAD_REQUEST);
            }
        }
        this.specService.addSpec(spec);
        return new ResponseEntity<>("Specification created successfully", HttpStatus.CREATED);
    }

    @PutMapping(path = "/specifications/{idSpec}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Specification> updateSpec(@PathVariable(value = "idSpec") int id, @RequestBody Specification spec) {
        spec.setIdSpec(id);
        boolean result = this.specService.updateSpec(spec);
        if (result) {
            return new ResponseEntity<>(spec, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/specifications/{idSpec}")
    @CrossOrigin
    public ResponseEntity<Void> deleteSpec(@PathVariable(value = "idSpec") int id) {
        boolean result = this.specService.deleteSpec(id);
        if (result) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
///Tim theo môn học 

    @GetMapping("/specifications/subject/{subjectId}")
    @CrossOrigin
    public ResponseEntity<List<Specification>> getSpecsBySubjectId(@PathVariable(value = "subjectId") int subjectId) {
        List<Specification> specs = this.specService.getSpecsBySubjectId(subjectId);
        if (specs == null || specs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(specs, HttpStatus.OK);
    }

    //API tìm kiếm theo từ khóa đc truyền từ param 
    @GetMapping("/searchSpecifications")
    @CrossOrigin
    public ResponseEntity<List<SpecificationDTO>> searchSpecifications(
            @RequestParam(required = false) String nameSpec,//cho required = fale tức là khi truyền không có thì nó là nul , để không bị lỗi 
            @RequestParam(required = false) Integer credit,
            @RequestParam(required = false) Integer page,//
            @RequestParam(required = false) String teacherName,//Lọc theo tên GV
            @RequestParam(required = false) Integer subjectId) {
        List<SpecificationDTO> specs = this.specService.searchSpecifications(nameSpec, credit, page, teacherName, subjectId);
        if (specs == null || specs.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(specs, HttpStatus.OK);
    }

}
