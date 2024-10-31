import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rowNumber;
    private int seatNumber;
    private boolean booked;

    // Constructors, getters, and setters
    public Seat() {}
    
    public Seat(int rowNumber, int seatNumber, boolean booked) {
        this.rowNumber = rowNumber;
        this.seatNumber = seatNumber;
        this.booked = booked;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public int getRowNumber() { return rowNumber; }
    public int getSeatNumber() { return seatNumber; }
    public boolean isBooked() { return booked; }
    public void setBooked(boolean booked) { this.booked = booked; }
}
