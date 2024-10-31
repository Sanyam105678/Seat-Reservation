import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SeatReservationUI extends JFrame {
    private JTextField seatInputField;
    private JTextArea resultArea;

    public SeatReservationUI() {
        setTitle("Seat Reservation System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input Panel
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter seats to reserve: ");
        seatInputField = new JTextField(5);
        JButton reserveButton = new JButton("Reserve");

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reserveSeats();
            }
        });

        panel.add(label);
        panel.add(seatInputField);
        panel.add(reserveButton);

        // Result Area
        resultArea = new JTextArea();
        resultArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Layout
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void reserveSeats() {
        String seatCount = seatInputField.getText();
        try {
            URL url = new URL("http://localhost:8080/api/seats/reserve?seatsRequested=" + seatCount);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder content = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();
            resultArea.setText("Reserved Seats:\n" + content.toString());

        } catch (Exception e) {
            resultArea.setText("Error reserving seats: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SeatReservationUI ui = new SeatReservationUI();
            ui.setVisible(true);
        });
    }
}
