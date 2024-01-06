package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {

    @ParameterizedTest
    @CsvSource(value = {
            "172.123.12.19, false, Отправлено сообщение: Добро пожаловать",
            "96.44.183.149, true, Отправлено сообщение: Welcome",
            "96.11.222.333, true, Отправлено сообщение: Welcome"
    })
    void sendBy_USA(String ip, boolean result, String trueMessage) {
        String message = "Отправлено сообщение: Welcome";
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Location location = new Location(null, Country.USA, null, 0);
        Mockito.when(geoService.byIp(ip)).thenReturn(location);

        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(trueMessage);

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> map = new HashMap<String, String>();
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String actual = messageSender.send(map);
        boolean check;
        if (actual.equals(message)) {
            check = true;
        } else {
            check = false;
        }
        Assertions.assertEquals(result, check);
    }
    @ParameterizedTest
    @CsvSource(value = {
            "172.123.12.19, true, Отправлено сообщение: Добро пожаловать"
    })
    void sendBy_Russia(String ip, boolean result, String trueMessage) {
        //создаем стринг приглашение
        String message = "Отправлено сообщение: Добро пожаловать";
        //создаем мок класса GeoServiceImpl
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        //создаем  обеъект Location
        Location location = new Location(null, Country.RUSSIA, "Lenina", 0);
        //создаем мок в котором в моке geoService по ip должны возвратить location соотв ip
        Mockito.when(geoService.byIp(ip)).thenReturn(location);
        //создаем мок класса LocalizationService
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        //создаем мок в котором в моке localizationService в методе locale доступ к полю страна
        // должны возвратить стринговое приветсвие
        Mockito.when(localizationService.locale(location.getCountry())).thenReturn(trueMessage);
        //создаем  объект MessageSenderImpl
        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        //создаем  мапу
        Map<String, String> map = new HashMap<String, String>();
        // вносим туда все айпи адреса
        map.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        // вносим в стринговую перемен.данные обьекта messageSender из мапы
        String actual = messageSender.send(map);
        boolean check;
        if (actual.equals(message)) {
            check = true;
        } else {
            check = false;
        }

        Assertions.assertEquals(result, check);
    }
}