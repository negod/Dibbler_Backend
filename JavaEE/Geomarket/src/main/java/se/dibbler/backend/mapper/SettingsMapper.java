/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.dibbler.backend.mapper;

import java.util.Date;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import se.dibbler.backend.dto.SettingDto;
import se.dibbler.backend.entity.Setting;
import se.dibbler.backend.generics.BaseMapper;

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