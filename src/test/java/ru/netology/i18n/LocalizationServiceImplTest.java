package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @org.junit.jupiter.api.Test
    void locale() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String locale = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals("Добро пожаловать",locale);
    }

}