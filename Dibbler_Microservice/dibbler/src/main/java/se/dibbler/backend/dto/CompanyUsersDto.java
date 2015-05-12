/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dto;

import se.dibbler.company.dto.CompanyDto;
import se.dibbler.generic.dto.BaseDto;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyUsersDto extends BaseDto {

    RolesDto userRole;
    CompanyDto company;

    public RolesDto getUserRole() {
        return userRole;
    }

    public void setUserRole(RolesDto userRole) {
        this.userRole = userRole;
    }

    public CompanyDto getCompany() {
        return company;
    }

    public void setCompany(CompanyDto company) {
        this.company = company;
    }

}
