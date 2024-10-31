import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    @Autowired
    private SeatService seatService;

    @GetMapping
    public List<Seat> getSeats() {
        return seatService.getAllSeats();
    }

    @PostMapping("/reserve")
    public List<Seat> reserveSeats(@RequestParam int seatsRequested) {
        return seatService.reserveSeats(seatsRequested);
    }
}
