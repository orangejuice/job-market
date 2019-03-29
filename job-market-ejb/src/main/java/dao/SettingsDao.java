package dao;

import entity.Settings;

import javax.ejb.Remote;
import java.util.Map;


@Remote
public interface SettingsDao extends BaseDao<Settings, Long> {
    String get(String key);

    void add(String key, String value);

    Map<String, String> findAll();
}
