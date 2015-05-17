/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import se.dibbler.backend.constants.DibblerConstants;
import se.dibbler.backend.constants.DibblerFileType;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.constants.PictureUrl;
import se.dibbler.backend.constants.RegExp;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dao.LocationDao;
import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.dto.create.CompanyCreateDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Mapper;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.utils.FileCreator;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @inheritDoc
 */
@Stateless
public class CompanyDaoBean extends BaseDaoBean<Company, CompanyDto> implements CompanyDao<Company, CompanyDto> {

    @EJB
    LocationDao location;

    public CompanyDaoBean() {
        super(Company.class, CompanyDto.class);
    }

    public Response create(CompanyCreateDto dto) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("orgNr", dto.getOrgNr());
            Response<Company> company = super.getSingleByNamedQuery(DibblerNamedQueries.COMPANY_GET_BY_ORGNO, params);

            if (company.hasNoErrors) {
                return Response.error(DaoError.COMPANY_CREATE_UNIQUE_ORGNO);
            }

            Response<Company> parentCompany = Response.error(DaoError.COMPANY_PARENT_NON_EXISTENT);
            if (dto.getParentCompanyId() != null && !dto.getParentCompanyId().isEmpty()) {
                if (dto.getParentCompanyId().matches(RegExp.GUID)) {
                    parentCompany = super.getByExtId(dto.getParentCompanyId());
                    if (parentCompany.hasErrors) {
                        return Response.error(parentCompany.getError());
                    }
                }
            }

            Company entity = Mapper.getInstance().getMapper().map(dto, Company.class);

            if (parentCompany.hasNoErrors) {
                entity.setParentCompany(parentCompany.getData());
                parentCompany.getData().getBranchCompanies().add(entity);
            } else {
                entity.setParentCompany(null);
            }

            /*if (entity.hasErrors) {
             return Response.error(entity.getError());
             }*/
            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                Response<Map<PictureUrl, String>> createImage = FileCreator.createFilesFromBase64String(dto.getPicture(), DibblerConstants.IMAGE_URL, 80, 40, DibblerFileType.COMPANY);
                if (createImage.hasNoErrors) {
                    entity.setSmallImageUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_SMALl));
                    entity.setLargeImageUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_LARGE));
                }
            } else {
                entity.setImageUrl("N/A");
                entity.setSmallImageUrl("N/A");
                entity.setLargeImageUrl("N/A");
            }

            entity.setLocations(Arrays.asList(entity.getLocation()));
            return super.create(entity);

        } catch (Exception e) {
            super.getLogger().error("[ Error when creating company ( companyDaoBean ) ] [ ERROR: ]", e.getMessage());
            return Response.error(GenericError.CREATE);
        }
    }

    @Override
    public Response<String> update(CompanyDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<String> create(CompanyDto dto) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

    @Override
    public Response<List<CompanyDto>> getBranches(String companyId) {
        try {
            Response<Company> company = super.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            return getMapper().mapToDtoList(company.getData().getBranchCompanies());
        } catch (Exception e) {
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response<CompanyDto> getParent(String companyId) {
        try {
            Response<Company> company = super.getByExtId(companyId);
            if (company.hasErrors) {
                return Response.error(company.getError());
            }
            return getMapper().mapFromEntityToDto(company.getData().getParentCompany());
        } catch (Exception e) {
            return Response.error(GenericError.READ);
        }
    }

    @Override
    public Response addBranch(String parentId, String branchId) {

        Response<Company> parent = super.getByExtId(parentId);
        Response<Company> branch = super.getByExtId(branchId);

        branch.getData().setParentCompany(parent.getData());
        parent.getData().getBranchCompanies().add(branch.getData());

        return Response.success(parent.getData().getExtId());

    }

    @Override
    public Response removeBranch(String parentId, String branchId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);

        /*Response<Company> parent = super.getByExtId(parentId);
         Response<Company> branch = super.getByExtId(branchId);

         branch.getData().setParentCompany(parent.getData());
         parent.getData().getBranchCompanies().add(branch.getData());

         return Response.success(parent.getData().getExtId());*/
    }

}
