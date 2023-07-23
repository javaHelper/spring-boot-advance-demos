package pluralsight.airportmanagement.db;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import pluralsight.airportmanagement.domain.FlightInformation;
import pluralsight.airportmanagement.domain.FlightType;

import java.util.List;

@Repository
public interface FlightInformationRepository
        extends MongoRepository<FlightInformation, String> {

    List<FlightInformation> findByDelayedTrue();

    List<FlightInformation> findByType(FlightType flightType);

    @Query("{'aircraft.nbSeats' : {$gte: ?0}}")
    List<FlightInformation> findByMinAircraftNbSeats(int minNbSeats);
}
