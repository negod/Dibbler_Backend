/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.geomarket.backend.geomarket.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.geomarket.backend.geomarket.dto.SettingDto;
import se.geomarket.backend.geomarket.entity.Setting;
import se.geomarket.backend.geomarket.generics.BaseMapper;

/**
 *
 * @author Joakikm Johansson (joakimjohansson@outlook.com)
 */
public class SetingsMapper extends BaseMapper<SettingDto, Setting> {
    
    private static final SetingsMapper INSTANCE = new SetingsMapper();
    
    private SetingsMapper() {
    }
    
    public static SetingsMapper getInstance() {
        return INSTANCE;
    }
    
    @Override
    public Setting mapFromDtoToEntity(SettingDto dto) {
        Mapper mapper = new DozerBeanMapper();
        Setting destObject = mapper.map(dto, Setting.class);
        return destObject;
    }
    
    @Override
    public SettingDto mapFromEntityToDto(Setting entity) {
        Mapper mapper = new DozerBeanMapper();
        SettingDto destObject = mapper.map(entity, SettingDto.class);
        return destObject;
    }
    
    @Override
    public void updateEntityFromDto(Setting entity, SettingDto dto) {
        entity.setAllowPush(dto.isAllowPush());
        entity.setFollowOnTop(dto.isFollowOnTop());
        entity.setMapAsDefault(dto.isMapAsDefault());
        entity.setLanguage(LanguagesMapper.getInstance().mapFromDtoToEntity(dto.getLanguage()));
        entity.setUpdatedDate(new Date());
    }
    
}
