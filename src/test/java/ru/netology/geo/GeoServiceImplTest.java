package ru.netology.geo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.sender.MessageSenderImpl;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void byIp() {

        GeoService geoService = new GeoServiceImpl();
        String ip = "172.";
        Location expected = new Location("Moscow",Country.RUSSIA,null,0);
        Location actual = geoService.byIp(ip);

             Assertions.assertEquals(expected.getCity(),actual.getCity());
             Assertions.assertEquals(expected.getCountry(),actual.getCountry());
             Assertions.assertEquals(expected.getStreet(),actual.getStreet());
             Assertions.assertEquals(expected.getBuiling(),actual.getBuiling());
    }

    @Test
    void byCoordinates() {
    }
}