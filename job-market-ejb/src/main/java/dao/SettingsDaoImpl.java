package dao;

import entity.Settings;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Stateless
public class SettingsDaoImpl extends BaseDaoImpl<Settings, Long> implements SettingsDao {
    @Override
    public String get(String key) {
        Settings s = findByKey(key);
        if (s != null) {
            return s.getDValue();
        }
        return null;
    }

    private Settings findByKey(String key) {
        TypedQuery<Settings> query = em.createQuery("select s from Settings as s where s.dKey=:dKey", Settings.class);
        query.setParameter("dKey", key);
        return getSingleResult(query);
    }

    @Override
    public void add(String key, String value) {
        Settings setting = findByKey(key);
        if (setting != null) {
            setting.setDValue(value);
        } else {
            setting = new Settings();
            setting.setDKey(key);
            setting.setDValue(value);
            em.merge(setting);
        }
    }

    @Override
    public Map<String, String> findAll() {
        Map<String, String> map = new HashMap<>();
        List<Settings> list = findAll(Settings.class);
        for (Settings settings : list) {
            map.put(settings.getDKey(), settings.getDValue());
        }
        return map;
    }
}
