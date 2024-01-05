package ru.netology.sender;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class MessageSenderImplTest {
    @Mock
     GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
     LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);

    @Test
    void send() {
        Mockito.doReturn("96").when(localizationService).locale(Country.USA);
        Mockito.doReturn("172").when(localizationService).locale(Country.RUSSIA);
    }
}