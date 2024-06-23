/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.controllers;

import com.eban.DTO.SearchResultDTO;
import com.eban.DTO.SpecificationDTO;
import com.eban.pojo.Gradingsheet;
import com.eban.pojo.Hoidong;
import com.eban.pojo.Oderdc;
import com.eban.pojo.Specgrande;
import com.eban.pojo.Specification;
import com.eban.pojo.Subject;
import com.eban.pojo.Typeofspecifi;
import com.eban.pojo.User;
import com.eban.services.OderdcService;
import com.eban.services.SpecService;
import com.eban.services.UserService;
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

    @Autowired
    private UserService userService;

    @Autowired
    private OderdcService oderdcService;

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

    @GetMapping(path = "/specifications/listspecgrande/{idSpec}", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<List<Specgrande>> getlisGrand(@PathVariable(value = "idSpec") int id) {
        List<Specgrande> specgrandes = this.specService.getSpecgrandeBySpecId(id);
        return new ResponseEntity<>(specgrandes, HttpStatus.OK);
    }

    @PostMapping(path = "/specifications/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    @CrossOrigin
    public ResponseEntity<String> create(@RequestParam Map<String, String> params, @RequestPart(required = false) MultipartFile[] file) {

        Specification spec = new Specification();
        spec.setNameSpec(params.get("nameSpec"));
        spec.setCredit(Integer.parseInt(params.get("credit")));
        spec.setContent(params.get("content"));
        spec.setDateCreate(new Date());
        spec.setStatus("editing");

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

        if (file != null && file.length > 0) {
            String fileName = file[0].getOriginalFilename();
            if (fileName != null && (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".pdf"))) {
                spec.setFile(file[0]);
            } else {
                return new ResponseEntity<>("Invalid file format. Only .doc, .docx, and .pdf are allowed.", HttpStatus.BAD_REQUEST);
            }
        }

        this.specService.addSpec(spec);

        //Cấu hình add Cot diem 
        // Parse the grading data
        String[] idGradingSheets = params.get("idgradingSheets").split(",");
        String[] gradWaves = params.get("gradWaves").split(",");

        for (int i = 0; i < idGradingSheets.length; i++) {
            Specgrande specgrande = new Specgrande();
            Gradingsheet gradingSheet = new Gradingsheet();
            gradingSheet.setIdGrade(Integer.parseInt(idGradingSheets[i]));
            specgrande.setGrandeID(gradingSheet);
            specgrande.setSpecifiID(spec);
            specgrande.setWeight(new BigDecimal(gradWaves[i]));
            this.specService.addSpecgrande(specgrande);
        }

        return new ResponseEntity<>("ok 201", HttpStatus.CREATED);
    }

    @PostMapping(path = "/specifications/update/{idSpec}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @CrossOrigin
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateSpec(
            @PathVariable(value = "idSpec") int id,
            @RequestParam(required = false) String nameSpec,
            @RequestParam(required = false) Integer credit,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String subjectId,
            @RequestParam(required = false) String typeSpecId,
            @RequestParam(required = false) String authorId,
            @RequestParam(required = false) String hoiDongID,
            @RequestPart(required = false) MultipartFile[] file) {

        Specification spec = this.specService.getSpecById(id);
        if (spec == null) {
            return ResponseEntity.badRequest().body("Specification not found");
        }

        if (nameSpec != null) {
            spec.setNameSpec(nameSpec);
        }
        if (credit != null) {
            spec.setCredit(credit);
        }
        if (content != null) {
            spec.setContent(content);
        }
        spec.setDateCreate(new Date());
        spec.setStatus("editing");

        if (subjectId != null) {
            Subject subject = new Subject();
            subject.setIdSubject(Integer.parseInt(subjectId));
            spec.setSubjectID(subject);
        }
        if (typeSpecId != null) {
            Typeofspecifi typeSpec = new Typeofspecifi();
            typeSpec.setIdType(Integer.parseInt(typeSpecId));
            spec.setTypeSpecID(typeSpec);
        }
        if (authorId != null) {
            User author = new User();
            author.setIdUser(Integer.parseInt(authorId));
            spec.setAuthorID(author);
        }
        if (hoiDongID != null) {
            Hoidong hoiDong = new Hoidong();
            hoiDong.setIdHoiDong(Integer.parseInt(hoiDongID));
            spec.setHoiDongID(hoiDong);
        }
        if (file != null && file.length > 0) {
            String fileName = file[0].getOriginalFilename();
            if (fileName != null && (fileName.endsWith(".doc") || fileName.endsWith(".docx") || fileName.endsWith(".pdf"))) {
                spec.setFile(file[0]);
            } else {
                return new ResponseEntity<>("Invalid file format. Only .doc, .docx, and .pdf are allowed.", HttpStatus.BAD_REQUEST);
            }
        }
        this.specService.updateSpec(spec);

        return ResponseEntity.ok("Specification updated successfully");
    }
// xoa de cuong
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
    public ResponseEntity<SearchResultDTO<SpecificationDTO>> searchSpecifications(
            @RequestParam(required = false) String nameSpec,
            @RequestParam(required = false) Integer credit,
            @RequestParam(required = false) Integer idCourse,
            @RequestParam(required = false) Integer authorID,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) String teacherName,
            @RequestParam(required = false) Integer subjectId) {
        SearchResultDTO<SpecificationDTO> searchResult = this.specService.searchSpecifications(nameSpec, credit, page, teacherName, subjectId, idCourse, authorID);
        if (searchResult.getResults().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(searchResult, HttpStatus.OK);
    }

    //API mua Spec 
    @PostMapping("/buySpec")
    @CrossOrigin
    public ResponseEntity<String> buySpec(@RequestParam("userId") int userId, @RequestParam("specId") int specId) {
        User user = userService.getUserById(userId);
        Specification spec = specService.getSpecById(specId);

        if (user == null) {
            return ResponseEntity.badRequest().body("Nguoi dung khong ton tai");
        }
        if (spec == null) {
            return ResponseEntity.badRequest().body("De cuong khong ton tai");
        }

        // Seting Coin 
        int cost = 1; // Default cost is 1 coin

        if (user.getCoin() < cost) {
            return ResponseEntity.badRequest().body("khong du coin");
        }

        user.setCoin(user.getCoin() - cost);
        userService.updateUserWhenBuySpec(user);
        Oderdc oderdc = new Oderdc();
        oderdc.setSpecID(spec);
        oderdc.setUserID(user);
        this.oderdcService.addOderdc(oderdc);
        return ResponseEntity.ok("Buy Spec successfully");
    }
    
    
    @GetMapping("/specifications/getSpecsbyUser/{userId}")
    @CrossOrigin
    public ResponseEntity<SearchResultDTO<SpecificationDTO>> getSpecsbyUserId(@PathVariable(value = "userId") int userId) {
        SearchResultDTO<SpecificationDTO> Result  = this.specService.getSpecsbyUserId(userId);
        if (Result.getResults().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(Result, HttpStatus.OK);
    }
    
    

}
