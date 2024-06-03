/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.eban.repositories;

import com.eban.pojo.Specification;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Admin
 */
public interface SpecRepocitory {
    List<Specification> getListSpec(Map<String, String> params);
    Specification getSpecById(int id);
}
