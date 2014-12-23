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

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    List<CategoryText> categoryTexts;

    public List<CategoryText> getCategoryTexts() {
        return categoryTexts;
    }

    public void setCategoryTexts(List<CategoryText> categoryTexts) {
        this.categoryTexts = categoryTexts;
    }

}
