import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SeatService {
    @Autowired
    private SeatRepository seatRepository;

    public List<Seat> reserveSeats(int seatsRequested) {
        List<Seat> availableSeats = seatRepository.findByBooked(false);
        List<Seat> reservedSeats = new ArrayList<>();

        for (Seat seat : availableSeats) {
            if (reservedSeats.size() < seatsRequested) {
                seat.setBooked(true);
                reservedSeats.add(seat);
            } else {
                break;
            }
        }
        seatRepository.saveAll(reservedSeats);
        return reservedSeats;
    }

    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }
}
