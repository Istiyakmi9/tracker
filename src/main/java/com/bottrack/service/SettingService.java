package com.bottrack.service;

import com.bottrack.model.Setting;
import com.bottrack.model.VehicleDetail;
import com.bottrack.repository.SettingRepository;
import com.bottrack.serviceinterfaces.ISettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SettingService implements ISettingService {
    @Autowired
    SettingRepository settingRepository;

    public Setting addSettingDetailService(Setting setting) throws Exception {
        if (setting.getUserId() == 0)
            throw new Exception("Invalid user id. Please login again");

        if (setting.getDefaultMapZoomValue() == 0)
            throw new Exception("Please enter a valid zoom value");

        if (setting.getHistoryExpiredInDays() == 0)
            throw new Exception("Please enter a valid expiring days");

        Optional<Setting> lastSetting = Optional.ofNullable(this.settingRepository.getLastSetting());
        if (lastSetting.isEmpty())
            setting.setSettingId(1L);
        else
            setting.setSettingId(lastSetting.get().getSettingId()+1);

        var result = this.settingRepository.save(setting);
        if (result == null)
            throw new Exception("Fail to insert setting detail. Please contact to admin");

        return result;
    }

    public Setting updateSettingDetailService(Long settingId, Setting setting) throws Exception {
        if (setting.getUserId() == 0)
            throw new Exception("Invalid user id. Please login again");

        if (setting.getSettingId() == 0)
            throw new Exception("Invalid user id. Please login again");

        Optional<Setting> existSetting = this.settingRepository.findById(settingId);
        if (existSetting.isEmpty())
            throw new Exception("Setting doesn't exist");

        Setting settingDetail = existSetting.get();
        settingDetail.setEnableMapTracing(setting.isEnableMapTracing());
        settingDetail.setEnableDarkTeam(setting.isEnableDarkTeam());
        settingDetail.setDefaultMapZoomValue(setting.getDefaultMapZoomValue());
        settingDetail.setHistoryExpiredInDays(setting.getHistoryExpiredInDays());
        settingDetail.setDefaultSearchValue(setting.getDefaultSearchValue());
        var result = this.settingRepository.save(settingDetail);
        if (result == null)
            throw new Exception("Fail to insert setting detail. Please contact to admin");

        return result;
    }

    public Setting getSettingByUserIdService(int userId) throws Exception {
        if (userId == 0)
            throw new Exception("Invalid user id. Please login again");

        var result = this.settingRepository.getSettingByUserId(userId);
        return result;
    }
}
