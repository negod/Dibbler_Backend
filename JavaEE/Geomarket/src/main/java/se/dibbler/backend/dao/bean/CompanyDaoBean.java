/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import se.dibbler.backend.constants.DibblerConstants;
import se.dibbler.backend.constants.DibblerFileType;
import se.dibbler.backend.constants.DibblerNamedQueries;
import se.dibbler.backend.constants.PictureUrl;
import se.dibbler.backend.dao.CompanyDao;
import se.dibbler.backend.dto.CompanyDto;
import se.dibbler.backend.entity.Company;
import se.dibbler.backend.entity.EventText;
import se.dibbler.backend.entity.Location;
import se.dibbler.backend.error.DaoError;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.GenericError;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.CompanyMapper;
import se.dibbler.backend.utils.FileCreator;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 * @inheritDoc
 */
@Stateless
public class CompanyDaoBean extends BaseDaoBean<Company, CompanyDto> implements CompanyDao<Company, CompanyDto> {

    public CompanyDaoBean() {
        super(Company.class);
    }

    @Override
    public Response create(CompanyDto dto) {
        try {

            HashMap<String, Object> params = new HashMap<>();
            params.put("orgNr", dto.getOrgNr());
            Response<Company> categoryTexts = super.getSingleByNamedQuery(DibblerNamedQueries.COMPANY_GET_BY_ORGNO, params);
            if (categoryTexts.hasNoErrors) {
                return Response.error(DaoError.COMPANY_CREATE_UNIQUE_ORGNO);
            }

            Response<Company> entity = CompanyMapper.getInstance().mapFromDtoToEntity(dto);

            if (entity.hasErrors) {
                return Response.error(entity.getError());
            }

            if (dto.getPicture() != null && !dto.getPicture().isEmpty()) {
                Response<Map<PictureUrl, String>> createImage = FileCreator.createFilesFromBase64String(dto.getPicture(), DibblerConstants.IMAGE_URL, 80, 40, DibblerFileType.COMPANY);
                if (createImage.hasNoErrors) {
                    entity.getData().setSmallImageUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_SMALl));
                    entity.getData().setLargeImageUrl("/pictures/" + createImage.getData().get(PictureUrl.PICTURE_NAME_LARGE));
                }
            } else {
                entity.getData().setImageUrl("N/A");
                entity.getData().setSmallImageUrl("N/A");
                entity.getData().setLargeImageUrl("N/A");
            }

            return super.create(entity.getData());
        } catch (Exception e) {
            super.getLogger().error("[ Error when creating company ] [ ERROR: ]", e.getMessage());
            return Response.error(GenericError.CREATE);
        }
    }

    @Override
    public Response<String> update(CompanyDto dto, String extId) {
        return Response.error(GenericError.METHOD_NOT_IMPLEMENTED);
    }

}
