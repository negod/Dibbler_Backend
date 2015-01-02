/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import se.dibbler.backend.dto.CompanyUsersDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.CompanyUsers;
import se.dibbler.backend.entity.Roles;
import se.dibbler.backend.generics.BaseMapper;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class CompanyUsersMapper extends BaseMapper<CompanyUsersDto, CompanyUsers> {

    private static final CompanyUsersMapper INSTANCE = new CompanyUsersMapper();

    public static CompanyUsersMapper getInstance() {
        return INSTANCE;
    }

    public CompanyUsersMapper() {
        super(CompanyUsersDto.class, CompanyUsers.class);
    }

    @Override
    public Response<CompanyUsers> updateEntityFromDto(CompanyUsers entity, CompanyUsersDto dto) {
        Response<Company> company = CompanyMapper.getInstance().mapFromDtoToEntity(dto.getCompany());
        if (company.hasErrors) {
            return Response.error(company.getError());
        }

        Response<Roles> role = RolesMapper.getInstance().mapFromDtoToEntity(dto.getUserRole());
        if (role.hasErrors) {
            return Response.error(role.getError());
        }

        entity.setCompany(company.getData());
        entity.setUserRole(role.getData());

        return Response.success(entity);
    }

}
