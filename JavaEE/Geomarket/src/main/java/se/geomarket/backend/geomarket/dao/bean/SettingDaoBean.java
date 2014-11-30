/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.dao.bean;

import javax.ejb.Stateless;
import se.geomarket.backend.geomarket.dao.SettingDao;
import se.geomarket.backend.geomarket.dto.SettingDto;
import se.geomarket.backend.geomarket.entity.Setting;
import se.geomarket.backend.geomarket.generics.BaseDaoBean;
import se.geomarket.backend.geomarket.generics.MethodResponse;
import se.geomarket.backend.geomarket.mapper.SettingsMapper;

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
    public MethodResponse create(SettingDto dto) {
        MethodResponse<Setting> entity = SettingsMapper.getInstance().mapFromDtoToEntity(dto);
        if (entity.hasErrors) {
            return MethodResponse.error(entity.getErrorCode());
        }
        return super.create(entity.getData());
    }

}
