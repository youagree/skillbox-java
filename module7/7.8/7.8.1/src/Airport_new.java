import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;

import java.util.*;

public class Airport_new {
    public static void main(String[] args) {

        Date date1 = new Date();
        Calendar temp = Calendar.getInstance();
        temp.add(Calendar.HOUR, +2);
        Date date2 = temp.getTime();

        Airport airport = Airport.getInstance();

        airport.getTerminals()
                .forEach(f -> f.getFlights().stream()
                        .filter(t -> t.getType().equals(Flight.Type.DEPARTURE))
                        .filter(t -> t.getDate().after(date1)
                                && t.getDate().before(date2))
                        .map(e -> e.getAircraft().getModel() + " / " + e)
                        .forEach(System.out::println));
    }
}
