/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity.superclass;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import se.dibbler.backend.entity.Language;
import se.dibbler.backend.generics.BaseEntity;

/**
 *
 * @author Joakim
 */
@MappedSuperclass
public class BaseType extends BaseEntity {

    @NotNull(message = "cannot be null")
    private String description;
    @NotNull(message = "cannot be null")
    private String defaultName;
    @OneToOne
    @NotNull(message = "cannot be null")
    private Language defaultLanguage;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Language getDefaultLanguage() {
        return defaultLanguage;
    }

    public void setDefaultLanguage(Language defaultLanguage) {
        this.defaultLanguage = defaultLanguage;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    @Override
    public void inactivate() {
    }

}
