/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.entity;

import se.dibbler.company.entity.Company;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import se.dibbler.generic.entity.BaseEntity;
import se.dibbler.generic.user.Role;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */

@Entity
public class CompanyUsers extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Role userRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
    private Company company;

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public void inactivate() {}

}
