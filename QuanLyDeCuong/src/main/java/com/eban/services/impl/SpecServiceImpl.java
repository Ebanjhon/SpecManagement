/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eban.pojo.Specification;
import com.eban.repositories.SpecRepocitory;
import com.eban.services.SpecService;
import java.io.IOException;
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
        if (!s.getFile().isEmpty()) {
            try {
                Map res = this.cloudinary.uploader().upload(s.getFile().getBytes(), ObjectUtils.asMap("resource_type", "raw"));
                s.setFileSpec(res.get("secure_url").toString());
            } catch (IOException ex) {
                Logger.getLogger(SpecServiceImpl.class.getName()).log(Level.SEVERE, "Error uploading file to Cloudinary", ex);
            }
            this.specRepo.addSpec(s);
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

}
