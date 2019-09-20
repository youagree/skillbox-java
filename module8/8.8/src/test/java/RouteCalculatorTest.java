import core.Line;
import core.Station;
import junit.framework.TestCase;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Test scheme
 *
 * [station1]   [station4]<->[station7]
 * |            |            |
 * [station2]   [station5]   [station8]
 * |            |            |
 * [station3]<->[station6]   [station9]
 */
public class RouteCalculatorTest extends TestCase {

    public RouteCalculator routeCalculator;
    public StationIndex stationIndex;

    public List<Station> stations;
    public List<Station> noConnection;
    public List<Station> oneConnection;
    public List<Station> twoConnection;
    public List<Station> actualResultStationList;

    public Line line1;
    public Station station1;
    public Station station2;
    public Station station3;

    public Line line2;
    public Station station4;
    public Station station5;
    public Station station6;

    public Line line3;
    public Station station7;
    public Station station8;
    public Station station9;

    @BeforeClass
    public void setUp() {

        line1 = new Line(1, "line1");

        station1 = new Station("station1", line1);
        station2 = new Station("station2", line1);
        station3 = new Station("station3", line1);

        line2 = new Line(2, "line2");

        station4 = new Station("station4", line2);
        station5 = new Station("station5", line2);
        station6 = new Station("station6", line2);

        line3 = new Line(3, "line3");

        station7 = new Station("station7", line3);
        station8 = new Station("station8", line3);
        station9 = new Station("station9", line3);

        stationIndex = new StationIndex();

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

        noConnection = Stream.of(station1, station2, station3)
                .collect(Collectors.toList());
        oneConnection = Stream.of(station1, station2, station3, station6, station5)
                .collect(Collectors.toList());
        twoConnection = Stream.of(station1, station2, station3, station6, station5, station4, station7, station8)
                .collect(Collectors.toList());
    }

    @Test
    public void testCalculateDuration() {
        double expected = RouteCalculator.calculateDuration(stations);
        double actual = 22;
        assertEquals(expected, actual);
    }

    @Test
    public void testGetShortRoutes() {
        actualResultStationList =  routeCalculator.getShortestRoute(station1, station3);
        assertEquals(noConnection, actualResultStationList);
    }

    @Test
    public void testGetShortRoutesOneConnection() {
        actualResultStationList = routeCalculator.getShortestRoute(station1, station5);
        assertEquals(oneConnection, actualResultStationList);
    }

    @Test
    public void testGetShortRoutesTwoConnection() {
        actualResultStationList = routeCalculator.getShortestRoute(station1, station8);
        assertEquals(twoConnection, actualResultStationList);
    }

    @Test
    public void testGetShortRoutesOneConnectionReturnNull() {

    }

    @Test
    public void testGetShortRoutesTwoConnectionReturnNull() {

    }

    /**
     * forFind intersection
     * @param stationName
     * @return Collection<Station>
     */
    @SuppressWarnings("unUsed")
    public List<Station> getStations(Station... stationName) {
        List<Station> nameOfStations = Arrays.asList(stationName);
        try{
            List<Station> intersection = stations.stream()
                    .filter(nameOfStations::contains)
                    .collect(Collectors.toList());
            return intersection;
        } catch (ClassCastException e) {
            return null;
        }
    }

    /**
     * forFind intersection
     * @param stationName
     * @return Collection<Station>
     */
    @SuppressWarnings("unUsed")
    public Collection<Station> getIntersectionStations(Station... stationName) {
        List<Station> nameOfStations = Arrays.asList(stationName);
        Collection<Station> foundStations = CollectionUtils.intersection(stations, nameOfStations);
        return foundStations;
    }

}
