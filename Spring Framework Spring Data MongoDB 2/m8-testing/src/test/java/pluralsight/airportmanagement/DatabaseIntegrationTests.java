package pluralsight.airportmanagement;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pluralsight.airportmanagement.db.AirportRepository;
import pluralsight.airportmanagement.db.FlightInformationRepository;
import pluralsight.airportmanagement.domain.Aircraft;
import pluralsight.airportmanagement.domain.Airport;
import pluralsight.airportmanagement.domain.FlightInformation;
import pluralsight.airportmanagement.domain.FlightType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataMongoTest
@ExtendWith(SpringExtension.class)
@Import(DatabaseTestConfiguration.class)
class DatabaseIntegrationTests {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    FlightInformationRepository flightInformationRepository;

    @BeforeEach
    public void beforeEach() {
        Airport rome = new Airport(
                "1d1aab22-670b-48cb-a027-721e2055731f",
                "Leonardo da Vinci",
                "Rome",
                42995119);

        Airport paris = new Airport(
                "d04a8c26-7527-407c-81ef-680e5de34cea",
                "Charles de Gaulle",
                "Paris",
                72229723);

        Airport venice = new Airport(
                "7ed990d2-6471-485d-931e-c77729dc0f25",
                "Venice Airport",
                "Venice",
                30298531);

        List<Airport> airports = Arrays.asList(rome, paris, venice);
        this.airportRepository.saveAll(airports);


        FlightInformation flightOne = new FlightInformation();
        flightOne.setId("b8b50563-ca90-4afc-9d43-b674892a525c");
        flightOne.setDelayed(false);
        flightOne.setDeparture(rome);
        flightOne.setDestination(paris);
        flightOne.setDepartureDate(LocalDate.of(2019, 3, 12));
        flightOne.setType(FlightType.International);
        flightOne.setDurationMin(100);
        flightOne.setAircraft(new Aircraft("A320", 180));

        FlightInformation flightTwo = new FlightInformation();
        flightTwo.setId("c0725fbb-eebb-4aae-8b41-3887793d0c50");
        flightTwo.setDelayed(true);
        flightTwo.setDeparture(venice);
        flightTwo.setDestination(rome);
        flightTwo.setDepartureDate(LocalDate.of(2019, 5, 11));
        flightTwo.setType(FlightType.Internal);
        flightTwo.setDurationMin(80);
        flightTwo.setAircraft(new Aircraft("737", 280));

        List<FlightInformation> flights = Arrays.asList(flightOne, flightTwo);
        this.flightInformationRepository.saveAll(flights);
    }

    @AfterEach
    public void afterEach() {
        this.mongoTemplate.dropCollection(Airport.class);
        this.mongoTemplate.dropCollection(FlightInformation.class);
    }

    @Test
    public void findByMinAircraftNbSeatsShouldWork() {
        // arrange
        int minSeats = 200;

        // act
        List<FlightInformation> flights = this.flightInformationRepository
                .findByMinAircraftNbSeats(minSeats);

        // assert
        assertEquals(1, flights.size());
        assertEquals("c0725fbb-eebb-4aae-8b41-3887793d0c50", flights.get(0).getId());
    }

    @Test
    public void theCascadeOnSaveShouldWork() {
        // arrange
        Airport bucharest = new Airport(
                "02297334-ff56-4380-9004-76cd2a37ea14",
                "OTP",
                "Bucharest",
                10298531);

        Airport london = new Airport(
                "8d4f79cc-4cec-432c-9efd-8409a9ff7d38",
                "Heathrow",
                "London",
                90298531);

        FlightInformation flight = new FlightInformation();
        flight.setId("ba555186-ec60-4e9f-8462-d4a8aaa1a8ae");
        flight.setDelayed(false);
        flight.setDeparture(bucharest);
        flight.setDestination(london);
        flight.setDepartureDate(LocalDate.of(2019, 6, 11));
        flight.setType(FlightType.International);
        flight.setDurationMin(150);
        flight.setAircraft(new Aircraft("737-700", 260));

        // act
        this.flightInformationRepository.insert(flight);

        // assert
        FlightInformation dbFlight = this.flightInformationRepository
                .findById("ba555186-ec60-4e9f-8462-d4a8aaa1a8ae")
                .get();
        assertNotNull(dbFlight);

        Airport dbLondon = this.airportRepository
                .findById("8d4f79cc-4cec-432c-9efd-8409a9ff7d38")
                .get();
        assertNotNull(dbLondon);

        Airport dbBucharest = this.airportRepository
                .findById("02297334-ff56-4380-9004-76cd2a37ea14")
                .get();
        assertNotNull(dbBucharest);
    }
}
