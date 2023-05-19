package com.bottrack.serviceinterfaces;

import com.bottrack.model.Setting;

public interface ISettingService {
    Setting addSettingDetailService(Setting setting) throws Exception;
    Setting updateSettingDetailService(Long settingId, Setting setting) throws Exception;
    Setting getSettingByUserIdService(int userId) throws Exception;
}
