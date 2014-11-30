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
public class SettingsMapper extends BaseMapper<SettingDto, Setting> {

    private static final SettingsMapper INSTANCE = new SettingsMapper();

    public static SettingsMapper getInstance() {
        return INSTANCE;
    }

    public SettingsMapper() {
        super(SettingDto.class, Setting.class);
    }

}
