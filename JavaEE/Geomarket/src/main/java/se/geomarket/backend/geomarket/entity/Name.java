/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import se.geomarket.backend.geomarket.generics.BaseEntity;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Entity
public class Name extends BaseEntity {

    @Column
    private String name;
    @OneToOne
    Language language;
    @OneToOne
    NameType type;

}
