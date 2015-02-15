/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import se.dibbler.backend.constants.DibblerNamedQueries;

/**
 *
 * @author Joakim Johansson (joakimjohansson@outlook.com)
 */
@Entity
@NamedQueries({
    @NamedQuery(name = DibblerNamedQueries.CATEGORY_FINDBY_LANGUAGE_EXTID, query = "SELECT c FROM CategoryText c where c.language.extId =:languageExtId"),})
public class CategoryText extends LanguageText {

    @ManyToOne(optional = false)
    Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
