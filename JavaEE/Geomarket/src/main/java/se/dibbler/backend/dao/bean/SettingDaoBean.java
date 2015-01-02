/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.dao.bean;

import javax.ejb.Stateless;
import se.dibbler.backend.dao.SettingDao;
import se.dibbler.backend.dto.SettingDto;
import se.dibbler.backend.entity.Setting;
import se.dibbler.backend.generics.BaseDaoBean;
import se.dibbler.backend.generics.Response;
import se.dibbler.backend.mapper.SettingsMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
@Stateless
public class SettingDaoBean extends BaseDaoBean<Setting, SettingDto> implements SettingDao<Setting, SettingDto> {

    public SettingDaoBean() {
        super(Setting.class);
    }

    @Override
    public Response create(SettingDto dto) {
        Response<Setting> entity = SettingsMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return Response.error(entity.getError());
        }
        return super.create(entity.getData());
    }

}
