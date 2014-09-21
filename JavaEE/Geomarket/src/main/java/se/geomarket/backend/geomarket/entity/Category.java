/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Category extends BaseEntity {
    
    private String defaultName;
    private String description;

    @OneToMany(mappedBy = "category")
    List<CategoryName> names;

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public List<CategoryName> getNames() {
        return names;
    }

    public void setNames(List<CategoryName> names) {
        this.names = names;
    }

}
