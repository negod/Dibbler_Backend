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
import se.geomarket.backend.geomarket.entity.superclass.BaseName;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Category extends BaseName {
    
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<CategoryName> names;

    public List<CategoryName> getNames() {
        return names;
    }

    public void setNames(List<CategoryName> names) {
        this.names = names;
    }
    
}
