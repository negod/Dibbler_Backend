/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao;

import java.util.List;
import javax.ejb.Local;
import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.dto.create.CompanyCreateDto;
import se.dibbler.backend.generics.BaseDao;
import se.dibbler.backend.generics.BaseDto;
import se.dibbler.backend.generics.BaseEntity;
import se.dibbler.backend.generics.Response;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @param <E>
 * @param <D>
 *
 */
@Local
public interface CompanyDao<E extends BaseEntity, D extends BaseDto> extends BaseDao<E, D> {

    public Response create(CompanyCreateDto dto);

    public Response<List<CompanyDto>> getBranches(String companyId);

    public Response<CompanyDto> getParent(String companyId);

    public Response addBranch(String parentId, String branchId);

    public Response removeBranch(String parentId, String branchId);

}
