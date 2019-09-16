import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.*;

public class Airport_new {
    public static void main(String[] args) {

        Date dateFrom = new Date();
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.HOUR, +2);
        Date dateTo = temp.getTime();

        Airport airport = Airport.getInstance();

        airport.getTerminals()
                .forEach(f -> f.getFlights().stream()
                        .filter(t -> t.getType().equals(Flight.Type.DEPARTURE))
                        .filter(t -> t.getDate().after(dateFrom)
                                && t.getDate().before(dateTo))
                        .map(e -> e.getAircraft().getModel() + " / " + e)
                        .forEach(System.out::println));

        airport.getTerminals().stream()
                .flatMap(e -> e.getFlights().stream())
                .filter(t -> t.getType().equals(Flight.Type.DEPARTURE))
                .filter(t -> t.getDate().after(dateFrom)
                        && t.getDate().before(dateTo))
                .map(e -> e.getAircraft().getModel() + " / " + e)
                .forEach(System.out::println);

    }
}
