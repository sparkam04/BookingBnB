package com.netcracker.edu.project.service;

import com.netcracker.edu.project.dao.impl.*;
import com.netcracker.edu.project.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;


//@EnableScheduling
@Service
public class EmailService {

    private final static String MESSAGE_FOR_USER_CREATED = "Dear, %s %s!\n" +
            "You were registered in the \"Booking BnB\" system.\n" +
            "http://localhost:8899\n" +
            "Data for authorization:\n" +
            "Login: %s\n" +
            "Password: %s";

    private final static String MESSAGE_FOR_BOOKING_CREATED = "Dear, %s %s!\n" +
            "You booked a room at the hotel \"%s\".\n" +
            "Address: %s, %s, %s \n" +
            "Armory reservation code: %d\n" +
            "Room Name: \"%s\"\n" +
            "Check-in: %tD %tR\n" +
            "Check-out date: %tD %tR\n" +
            "Number of people: %s\n" +
            "Armor price: %10.2f\n" +
            "Paid with: %s\n" +
            "\n" +
            "Sincerely, Hotel Administrator: %s %s\n" +
            "All questions about:\n" +
            "Phone: %s\n" +
            "E-mail: %s";

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserDatabaseDAO userDatabaseDAO;

    @Autowired
    private HotelDatabaseDAO hotelDatabaseDAO;

    @Autowired
    private RoomDatabaseDAO roomDatabaseDAO;

    @Autowired
    private LocationDatabaseDAO locationDatabaseDAO;

    @Autowired
    private CityDatabaseDAO cityDatabaseDAO;

    @Autowired
    private CountryDatabaseDAO countryDatabaseDAO;

    @Autowired
    private PaySystemDatabaseDAO paySystemDatabaseDAO;

//    @Autowired
//    private BookingDatabaseDAO bookingDatabaseDAO;
//
//    @Scheduled(fixedRate = 50000000)
//    public void run() throws ParseException {
//        Booking booking = bookingDatabaseDAO.getById(37L);
//        sendMessageBookingCreated(booking);
//        User user = userDatabaseDAO.getById(22L);
//        sendMessageUserCreated(user);
//        System.out.println("_________________________________________________________________");
//    }

    public boolean sendMessageBookingCreated(Booking booking) {
        User client = userDatabaseDAO.getById(booking.getUserId());
        Room room = roomDatabaseDAO.getById(booking.getRoomId());
        Hotel hotel = hotelDatabaseDAO.getById(room.getHotelId());
        User admin = userDatabaseDAO.getById(hotel.getOwnerId());
        Location location = locationDatabaseDAO.getById(hotel.getLocationId());
        City city = cityDatabaseDAO.getById(location.getCityId());
        Country country = countryDatabaseDAO.getById(city.getCountryId());
        PaySystem paySystem = paySystemDatabaseDAO.getById(booking.getPaySysId());

        Long days = TimeUnit.DAYS.convert(booking.getCheckOut().getTime() - booking.getCheckIn().getTime(), TimeUnit.MILLISECONDS);
        Double cost = room.getCost() * days;

        String text = String.format(MESSAGE_FOR_BOOKING_CREATED,
                client.getFirstName(), client.getLastName(),                    //"Dear, %s %s!\n"
                hotel.getHotelName(),                                           //"You booked a room at the hotel \"%s\".\n"
                country.getName(), city.getName(), location.getStreetAddress(), //"Address %s, %s, %s \n"
                booking.getId(),                                                //"Armory reservation code: %d\n"
                room.getRoomName(),                                             //"Room Name: \"%s\"\n"
                booking.getCheckIn(), hotel.getCheckInTime(),                   //"Check-in: %tD %tR\n"
                booking.getCheckOut(), hotel.getCheckOutTime(),                 //"Check-out date: %tD %tR\n"
                booking.getNumPersons(),                                        //"Number of people: %s\n"
                cost,                                                           //"Armor price: %10.2f\n"
                paySystem.getName(),                                            //"Paid with: %s\n"
                //"\n"
                admin.getFirstName(), admin.getLastName(),                      //"Sincerely, Hotel Administrator: %s %s\n"
                //"All questions about:\n"
                admin.getPhone(),                                               //"Phone: %s\n"
                admin.getEmail()                                                //"E-mail: %s"
        );

        return sendMessage(client.getEmail(), "BookingBNB - Booking rooms!", text);
    }

    public boolean sendMessageUserCreated(User user) {
        String text = String.format(MESSAGE_FOR_USER_CREATED, user.getFirstName(),
                user.getLastName(), user.getEmail(), user.getPass());

        return sendMessage(user.getEmail(), "BookingBNB - Registration in the system!", text);
    }

    public boolean sendMessage(String to, String subject, String text) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);

            emailSender.send(message);
            return true;
        } catch (MailException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public void sendMessageWithFiles(String to, String subject, String text, List<File> files) {
        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text);

            for (File file : files) {
                FileSystemResource fileSystemResource = new FileSystemResource(file);
                helper.addAttachment("Invoice", fileSystemResource);
            }

            emailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
