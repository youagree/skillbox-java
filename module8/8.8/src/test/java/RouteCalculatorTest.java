import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class RouteCalculatorTest extends TestCase {

    public RouteCalculator routeCalculator;
    public StationIndex stationIndex;

    List<Station> stations;
    List<Station> noConnection;
    List<Station> oneConnection;
    List<Station> twoConnection;

    @BeforeClass
    public void setUp () {

    stationIndex = new StationIndex();

    Line line1 = new Line(1, "Тестовая линия 1");

    Station station1 = new Station("Тестовая станция 1.1", line1);
    Station station2 = new Station("Тестовая станция 1.2", line1);
    Station station3 = new Station("Тестовая станция 1.3", line1);

    Line line2 = new Line(2, "Тестовая линия 2");

    Station station4 = new Station("Тестовая станция 2.1", line2);
    Station station5 = new Station("Тестовая станция 2.2", line2);
    Station station6 = new Station("Тестовая станция 2.3", line2);

    Line line3 = new Line(3, "Тестовая линия 3");

    Station station7 = new Station("Тестовая станция 3.1", line3);
    Station station8 = new Station("Тестовая станция 3.2", line3);
    Station station9 = new Station("Тестовая станция 3.3", line3);

    stations = Stream.of(station1, station2, station3, station4, station5, station6, station7, station8, station9)
            .collect(Collectors.toList());
    List<Station> connect1 = Arrays.asList(station3, station6);
    List<Station> connect2 = Arrays.asList(station4, station7);

    Stream.of(station1, station2, station3, station4, station5, station6, station7, station8, station9)
            .peek(e -> e.getLine().addStation(e)).forEach(stationIndex::addStation);

    stationIndex.addConnection(connect1);
    stationIndex.addConnection(connect2);
    Stream.of(line1, line2, line3).forEach(stationIndex::addLine);


    routeCalculator = new RouteCalculator(stationIndex);

    noConnection = Stream.of(station1, station2, station3).collect(Collectors.toList());
    oneConnection = Stream.of(station1,station2, station3, station6, station5).collect(Collectors.toList());
    twoConnection = Stream.of(station1,station2, station3, station6, station5, station4, station7, station8)
            .collect(Collectors.toList());
    }

    @Test
    public void testCalculateDuration () {
     double expected =  RouteCalculator.calculateDuration(stations);
     double actual = 22;
     assertEquals(expected, actual);
    }

    @Test
    public void testGetShortRoutes () {
        List<Station> expected = Arrays.asList(stations.get(0), stations.get(1), stations.get(2));
        routeCalculator.getShortestRoute(stations.get(0), stations.get(2));
        assertEquals(expected, noConnection);
    }

    @Test
    public void testGetShortRoutesOneConnection () {
        List<Station> expected = Arrays.asList(stations.get(0), stations.get(1), stations.get(2), stations.get(5),
                stations.get(4));
        routeCalculator.getShortestRoute(stations.get(0), stations.get(4));
        assertEquals(expected, oneConnection);
    }

    @Test
    public void testGetShortRoutesTwoConnection () {
        List<Station> expected = Arrays.asList(stations.get(0), stations.get(1), stations.get(2),
                stations.get(5), stations.get(4), stations.get(3), stations.get(6), stations.get(7));
       routeCalculator.getShortestRoute(stations.get(0), stations.get(8));
       assertEquals(expected, twoConnection);
    }
}
