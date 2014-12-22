/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import se.geomarket.backend.geomarket.entity.superclass.BaseType;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Category extends BaseType {

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<CategoryText> cateoryTexts;

    public List<CategoryText> getCateoryTexts() {
        return cateoryTexts;
    }

    public void setCateoryTexts(List<CategoryText> cateoryTexts) {
        this.cateoryTexts = cateoryTexts;
    }

}
