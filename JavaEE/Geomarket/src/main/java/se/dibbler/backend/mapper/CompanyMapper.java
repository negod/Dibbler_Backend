/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyMapper extends BaseMapper<CompanyDto, Company> {

    private static final CompanyMapper INSTANCE = new CompanyMapper();

    public static CompanyMapper getInstance() {
        return INSTANCE;
    }

    public CompanyMapper() {
        super(CompanyDto.class, Company.class);
    }

}
