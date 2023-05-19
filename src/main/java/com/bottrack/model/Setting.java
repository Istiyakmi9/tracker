package com.bottrack.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "setting")
public class Setting {
    @Column(name = "SettingId")
    @Id
    Long settingId;
    @Column(name = "EnableMapTracing")
    boolean enableMapTracing;
    @Column(name = "EnableDarkTeam")
    boolean enableDarkTeam;
    @Column(name = "DefaultMapZoomValue")
    int defaultMapZoomValue;
    @Column(name = "HistoryExpiredInDays")
    int historyExpiredInDays;
    @Column(name = "DefaultSearchValue")
    String defaultSearchValue;
    @Column(name = "UserId")
    int userId;

    public Long getSettingId() {
        return settingId;
    }

    public void setSettingId(Long settingId) {
        this.settingId = settingId;
    }

    public boolean isEnableMapTracing() {
        return enableMapTracing;
    }

    public void setEnableMapTracing(boolean enableMapTracing) {
        this.enableMapTracing = enableMapTracing;
    }

    public boolean isEnableDarkTeam() {
        return enableDarkTeam;
    }

    public void setEnableDarkTeam(boolean enableDarkTeam) {
        this.enableDarkTeam = enableDarkTeam;
    }

    public int getDefaultMapZoomValue() {
        return defaultMapZoomValue;
    }

    public void setDefaultMapZoomValue(int defaultMapZoomValue) {
        this.defaultMapZoomValue = defaultMapZoomValue;
    }

    public int getHistoryExpiredInDays() {
        return historyExpiredInDays;
    }

    public void setHistoryExpiredInDays(int historyExpiredInDays) {
        this.historyExpiredInDays = historyExpiredInDays;
    }

    public String getDefaultSearchValue() {
        return defaultSearchValue;
    }

    public void setDefaultSearchValue(String defaultSearchValue) {
        this.defaultSearchValue = defaultSearchValue;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Setting(Long settingId, boolean enableMapTracing, boolean enableDarkTeam, int defaultMapZoomValue, int historyExpiredInDays, String defaultSearchValue, int userId) {
        this.settingId = settingId;
        this.enableMapTracing = enableMapTracing;
        this.enableDarkTeam = enableDarkTeam;
        this.defaultMapZoomValue = defaultMapZoomValue;
        this.historyExpiredInDays = historyExpiredInDays;
        this.defaultSearchValue = defaultSearchValue;
        this.userId = userId;
    }

    public Setting() {}

    @Override
    public String toString() {
        return "Setting{" +
                "settingId=" + settingId +
                ", enableMapTracing=" + enableMapTracing +
                ", enableDarkTeam=" + enableDarkTeam +
                ", defaultMapZoomValue=" + defaultMapZoomValue +
                ", historyExpiredInDays=" + historyExpiredInDays +
                ", defaultSearchValue='" + defaultSearchValue + '\'' +
                ", userId=" + userId +
                '}';
    }
}
