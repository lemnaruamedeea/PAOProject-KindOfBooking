package com.unibuc.bookingapp;

import com.unibuc.bookingapp.model.*;
import com.unibuc.bookingapp.service.BookingService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ApplicationStartup {
    public static void main(String[] args) {
        BookingService bookingService = new BookingService();

        // Creăm trei obiecte de tip Hotel
        Hotel hotel1 = new Hotel(1, "Hotel Splendid", "Romania", "Bucuresti", 4, true, 10);
        Hotel hotel2 = new Hotel(2, "Grand Hotel", "Romania", "Cluj-Napoca", 5, true, 30);
        Hotel hotel3 = new Hotel(3, "Hotel Riviera", "Romania", "Constanta", 3, false, 15);

        // Adăugăm hotelurile în serviciul de rezervări
        bookingService.addAccommodation(hotel1);
        bookingService.addAccommodation(hotel2);
        bookingService.addAccommodation(hotel3);

        // Creăm trei obiecte de tip Apartment
        Apartment apartment1 = new Apartment(4, "Cozy Apartment", "Romania", "Brasov", 2, true);
        Apartment apartment2 = new Apartment(5, "Luxury Apartment", "Romania", "Timisoara", 3, false);
        Apartment apartment3 = new Apartment(6, "Spacious Apartment", "Romania", "Sibiu", 1, true);

        // Adăugăm apartamentele în serviciul de rezervări
        bookingService.addAccommodation(apartment1);
        bookingService.addAccommodation(apartment2);
        bookingService.addAccommodation(apartment3);

        // Creăm trei obiecte de tip VacationHouse
        VacationHouse vacationHouse1 = new VacationHouse(7, "Green Retreat", "Romania", "Poiana Brasov", true, 2, 5);
        VacationHouse vacationHouse2 = new VacationHouse(8, "Sunset Villa", "Romania", "Eforie Nord", false, 1, 3);
        VacationHouse vacationHouse3 = new VacationHouse(9, "Mountain Lodge", "Romania", "Sinaia", true, 3, 8);

        // Adăugăm casele de vacanță în serviciul de rezervări
        bookingService.addAccommodation(vacationHouse1);
        bookingService.addAccommodation(vacationHouse2);
        bookingService.addAccommodation(vacationHouse3);


        // Adăugăm câțiva utilizatori
        User user1 = new User(1, "John", "john@example.com", "123456789", 30);
        User user2 = new User(2, "Alice", "alice@example.com", "987654321", 25);
        User user3 = new User(3, "Bob", "bob@example.com", "555555555", 35);

        bookingService.addUser(user1);
        bookingService.addUser(user2);
        bookingService.addUser(user3);

        // Afisarea hotelurilor
        System.out.println("Hoteluri:");
        List<Hotel> hotels = bookingService.getHotels();
        for (Hotel hotel : hotels) {
            System.out.println("Nume: " + hotel.getName());
            System.out.println("ID: " + hotel.getId());
            System.out.println("Țară: " + hotel.getCountry());
            System.out.println("Oraș: " + hotel.getCity());
            System.out.println("Rating: " + hotel.getStarRating());
            System.out.println("Acces piscina: " + (hotel.hasPool() ? "Da" : "Nu"));
            System.out.println("Camere disponibile: " + hotel.getFreeRooms());
            System.out.println(); // Adăugăm o linie goală pentru a separa hotelurile în afișare
        }


        // Afisarea apartamentelor
        System.out.println("\nApartamente:");
        List<Apartment> apartments = bookingService.getApartments();
        for (Apartment apartment : apartments) {
            System.out.println("Nume: " + apartment.getName());
            System.out.println("ID: " + apartment.getId());
            System.out.println("Țară: " + apartment.getCountry());
            System.out.println("Oraș: " + apartment.getCity());
            System.out.println("Numar camere: " + apartment.getNumberOfRooms());
            System.out.println("Are AC: " + (apartment.hasAC() ? "Da" : "Nu"));
            System.out.println(); // Adăugăm o linie goală pentru a separa apartamentele în afișare
        }

// Afisarea caselor de vacanta
        System.out.println("\nCase de vacanță:");
        List<VacationHouse> vacationHouses = bookingService.getVacationHouses();
        for (VacationHouse vacationHouse : vacationHouses) {
            System.out.println("Nume: " + vacationHouse.getName());
            System.out.println("ID: " + vacationHouse.getId());
            System.out.println("Țară: " + vacationHouse.getCountry());
            System.out.println("Oraș: " + vacationHouse.getCity());
            System.out.println("Are parcare: " + (vacationHouse.hasParkingSpot() ? "Da" : "Nu"));
            System.out.println("Număr etaje: " + vacationHouse.getFloors());
            System.out.println("Camere disponibile: " + vacationHouse.getFreeRooms());
            System.out.println(); // Adăugăm o linie goală pentru a separa casele de vacanță în afișare
        }



        Reservation johnReservation = new Reservation(1, hotel1.getId(), user1.getId(), LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 5), 1);
        bookingService.makeReservation(johnReservation);

        // Rezervarea pentru Alice
        Reservation aliceReservation = new Reservation(2, apartment2.getId(), user2.getId(), LocalDate.of(2025, 8, 20), LocalDate.of(2025, 9, 1), 2);
        bookingService.makeReservation(aliceReservation);

        // Rezervarea pentru Bob
        Reservation bobReservation = new Reservation(3, vacationHouse3.getId(), user3.getId(), LocalDate.now().plusDays(1), LocalDate.now().plusDays(4), 1);
        bookingService.makeReservation(bobReservation);

        // Anulăm rezervarea pentru Alice
        bookingService.cancelReservation(aliceReservation);

        // Anulăm rezervarea pentru Bob
        bookingService.cancelReservation(bobReservation);

        // Adăugare recenzii pentru cazări
        List<Review> reviewsForHotel1 = new ArrayList<>();
        reviewsForHotel1.add(new Review(1, hotel1.getId(), user1.getId(), "Hotel minunat!", 5.0));
        reviewsForHotel1.add(new Review(2, hotel1.getId(), user2.getId(), "Servicii excelente.", 4.5));
        hotel1.setReviews(reviewsForHotel1);

        List<Review> reviewsForHotel2 = new ArrayList<>();
        reviewsForHotel2.add(new Review(3, hotel2.getId(), user3.getId(), "O experiență minunată.", 4.8));
        hotel2.setReviews(reviewsForHotel2);

        List<Review> reviewsForHotel3 = new ArrayList<>();
        reviewsForHotel3.add(new Review(4, hotel3.getId(), user1.getId(), "Hotelul are o locație excelentă.", 4.0));
        reviewsForHotel3.add(new Review(5, hotel3.getId(), user3.getId(), "Personalul amabil.", 4.2));
        hotel3.setReviews(reviewsForHotel3);

        List<Review> reviewsForApartment1 = new ArrayList<>();
        reviewsForApartment1.add(new Review(6, apartment1.getId(), user2.getId(), "Apartamentul este confortabil.", 4.0));
        apartment1.setReviews(reviewsForApartment1);

        List<Review> reviewsForApartment2 = new ArrayList<>();
        reviewsForApartment2.add(new Review(7, apartment2.getId(), user1.getId(), "Apartamentul este foarte spațios.", 4.5));
        reviewsForApartment2.add(new Review(8, apartment2.getId(), user3.getId(), "Curat și bine echipat.", 4.3));
        apartment2.setReviews(reviewsForApartment2);

        List<Review> reviewsForVacationHouse1 = new ArrayList<>();
        reviewsForVacationHouse1.add(new Review(9, vacationHouse1.getId(), user1.getId(), "Oaza de liniște perfectă.", 4.7));
        reviewsForVacationHouse1.add(new Review(10, vacationHouse1.getId(), user2.getId(), "Peisajul este superb.", 4.6));
        vacationHouse1.setReviews(reviewsForVacationHouse1);

        List<Review> reviewsForVacationHouse2 = new ArrayList<>();
        reviewsForVacationHouse2.add(new Review(11, vacationHouse2.getId(), user3.getId(), "Casa de vacanță minunată.", 4.2));
        vacationHouse2.setReviews(reviewsForVacationHouse2);

        List<Review> reviewsForVacationHouse3 = new ArrayList<>();
        reviewsForVacationHouse3.add(new Review(12, vacationHouse3.getId(), user2.getId(), "Atmosferă primitoare.", 4.4));
        vacationHouse3.setReviews(reviewsForVacationHouse3);


        // Adăugare recenzie pentru hotelul cu id-ul 1
        Review review1 = new Review(1, 1, 1, "Hotel minunat, personalul foarte amabil!", 5.0);
        bookingService.addReview(review1);

// Adăugare recenzie pentru apartamentul cu id-ul 2
        Review review2 = new Review(2, 2, 1, "Apartamentul a fost curat și confortabil.", 4.3);
        bookingService.addReview(review2);

// Adăugare recenzie pentru casa de vacanță cu id-ul 3
        Review review3 = new Review(3, 3, 2, "O experiență excelentă în această casă de vacanță!", 4.0);
        bookingService.addReview(review3);

        // Apelăm metoda pentru listarea recenziilor pentru o anumită cazare
        int accommodationId = 1; // ID-ul cazării pentru care vrem să listăm recenziile
        List<Review> reviewsForAccommodation = bookingService.getReviewsForAccommodation(accommodationId);

        // Afișăm recenziile
        if (reviewsForAccommodation.isEmpty()) {
            System.out.println("Nu există recenzii pentru această cazare.");
        } else {
            System.out.println("Recenzii pentru cazarea cu ID-ul " + accommodationId + ":");
            for (Review review : reviewsForAccommodation) {
                System.out.println(" - ID recenzie: " + review.getId());
                System.out.println("   Comentariu: " + review.getComment());
                System.out.println("   Rating: " + review.getRating());
            }
        }



        // Apelăm metoda pentru listarea rezervărilor pentru o anumită cazare
        List<Reservation> reservationsForAccommodation = bookingService.getReservationsForAccommodation(accommodationId);

        // Afișăm rezervările
        if (reservationsForAccommodation.isEmpty()) {
            System.out.println("Nu există rezervări pentru această cazare.");
        } else {
            System.out.println("Rezervări pentru cazarea cu ID-ul " + accommodationId + ":");
            for (Reservation reservation : reservationsForAccommodation) {
                System.out.println(" - ID rezervare: " + reservation.getId());
                System.out.println("   Data de început: " + reservation.getStartDate());
                System.out.println("   Data de sfârșit: " + reservation.getEndDate());
                System.out.println("   Număr de camere: " + reservation.getNumberOfRooms());
                // Poți adăuga și alte detalii relevante despre rezervare aici
            }
        }

            // Aici adăugăm cazările (dacă sunt necesare)

            // Țara pentru care vrem să căutăm cazări
            String countryToSearch = "Romania";

            // Apelăm metoda pentru căutarea cazărilor după țară
            List<Accommodation> accommodationsByCountry = bookingService.searchAccommodationsByCountry(countryToSearch);

            // Afișăm cazările găsite
            if (accommodationsByCountry.isEmpty()) {
                System.out.println("Nu există cazări disponibile în " + countryToSearch + ".");
            } else {
                System.out.println("Cazări disponibile în " + countryToSearch + ":");
                for (Accommodation accommodation : accommodationsByCountry) {
                    System.out.println(" - " + accommodation.getName());
                    // Poți adăuga și alte detalii relevante despre cazare aici
                }
            }

        int starRatingToDisplay = 4; // Aici specificăm numărul de stele

        // Apelăm metoda pentru afișarea hotelurilor după numărul de stele
        bookingService.displayHotelsByStarRating(starRatingToDisplay);

        // Alegem o rezervare existentă pentru checkout
        int reservationIdToCheckOut = 1; // Schimbă acest ID cu ID-ul unei rezervări existente

        // Apelarea metodei checkout
        bookingService.checkOut(reservationIdToCheckOut);


        // Obținerea listei de cazări
        List<Accommodation> accommodations = bookingService.getAccommodations();

        // Apelarea metodei pentru a sorta cazările după rating
        bookingService.sortAccommodationsByRating(accommodations);

        // Afișarea cazărilor sortate
        for (Accommodation accommodation : accommodations) {
            System.out.println("Cazare: " + accommodation.getName() + ", Rating mediu: " + accommodation.calculateAverageRating());
        }

         bookingService.displayAccommodationsWithReservations();
    }
}

