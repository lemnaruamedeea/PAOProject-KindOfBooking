package com.unibuc.bookingapp.service;

import com.unibuc.bookingapp.model.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

// Clasa serviciu pentru gestionarea operațiilor sistemului
public class BookingService {
    private List<Accommodation> accommodations = new ArrayList<>();
    private List<Hotel> hotels = new ArrayList<>();
    private List<Apartment> apartments = new ArrayList<>();
    private List<VacationHouse> vacationHouses = new ArrayList<>();
    private List<Reservation> reservations = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    // 1. Metoda pentru adăugarea unei cazări
    public void addAccommodation(Accommodation accommodation) {
        accommodations.add(accommodation);
        if (accommodation instanceof Hotel) {
            hotels.add((Hotel) accommodation);
        } else if (accommodation instanceof Apartment) {
            apartments.add((Apartment) accommodation);
        } else if (accommodation instanceof VacationHouse) {
            vacationHouses.add((VacationHouse) accommodation);
        }
    }

    // 2.Metode pentru accesarea listelor de cazări specifice
    public List<Accommodation> getAccommodations() {
        return accommodations;
    }
    public List<Hotel> getHotels() {
        return hotels;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public List<VacationHouse> getVacationHouses() {
        return vacationHouses;
    }


    // 3. Metoda pentru rezervarea unei camere
    public void makeReservation(Reservation reservation) {
        boolean isAvailable = true;

        // Verificăm dacă perioada este liberă doar pentru aceeași cazare
        for (Reservation existingReservation : reservations) {
            if (reservation.getAccommodationId() == existingReservation.getAccommodationId()) {
                LocalDate existingStartDate = existingReservation.getStartDate();
                LocalDate existingEndDate = existingReservation.getEndDate();
                LocalDate newStartDate = reservation.getStartDate();
                LocalDate newEndDate = reservation.getEndDate();

                // Verificăm dacă există suprapuneri între perioade
                if (((newStartDate.isAfter(existingStartDate) && newStartDate.isBefore(existingEndDate)) ||
                        (newEndDate.isAfter(existingStartDate) && newEndDate.isBefore(existingEndDate)) ||
                        (newStartDate.isBefore(existingStartDate) && newEndDate.isAfter(existingEndDate))) &&
                        newStartDate.isBefore(newEndDate)) {
                    isAvailable = false;
                    break;
                }
            }
        }

        // Verificăm disponibilitatea camerei în funcție de numărul de camere libere
        if (isAvailable) {
            for (Accommodation accommodation : accommodations) {
                if (accommodation.getId() == reservation.getAccommodationId()) {
                    if (accommodation instanceof Hotel) {
                        Hotel hotel = (Hotel) accommodation;
                        if (hotel.getFreeRooms() >= reservation.getNumberOfRooms()) {
                            hotel.setFreeRooms(hotel.getFreeRooms() - reservation.getNumberOfRooms());
                            reservations.add(reservation);
                            System.out.println("Rezervare realizată cu succes pentru " + reservation.getNumberOfRooms() + " camere în hotelul " + hotel.getName());
                        } else {
                            System.out.println("Nu sunt suficiente camere disponibile în hotelul " + hotel.getName());
                        }
                    } else if (accommodation instanceof VacationHouse) {
                        VacationHouse vacationHouse = (VacationHouse) accommodation;
                        if (vacationHouse.getFreeRooms() >= reservation.getNumberOfRooms()) {
                            reservations.add(reservation);
                            vacationHouse.setFreeRooms(vacationHouse.getFreeRooms() - reservation.getNumberOfRooms());
                            System.out.println("Rezervare realizată cu succes pentru " + reservation.getNumberOfRooms() + " camere în pensiunea " + vacationHouse.getName());
                        } else {
                            System.out.println("Nu sunt suficiente camere disponibile în pensiunea " + vacationHouse.getName());
                        }
                    }else if (accommodation instanceof Apartment) {
                        Apartment apartment = (Apartment) accommodation;
                        reservations.add(reservation);
                        System.out.println("Rezervare realizată cu succes pentru apartamentul " + apartment.getName());
                    }
                }
            }
        } else {
            System.out.println("Perioada specificată nu este disponibilă pentru această cazare.");
        }
    }

    // 4. Metoda pentru anularea unei rezervari
    public void cancelReservation(Reservation reservation) {
        // Obținem data curentă pentru a verifica dacă rezervarea este în viitor sau în trecut
        LocalDate currentDate = LocalDate.now();

        // Verificăm dacă rezervarea este în viitor
        if (reservation.getStartDate().isAfter(currentDate)) {
            // Setăm startDate și endDate la null pentru a marca rezervarea ca fiind anulată
            reservation.setStartDate(null);
            reservation.setEndDate(null);

            // Căutăm cazarea asociată rezervării
            for (Accommodation accommodation : accommodations) {
                if (accommodation.getId() == reservation.getAccommodationId()) {
                    // Verificăm tipul de cazare (de exemplu, hotel, casă de vacanță etc.)
                    if (accommodation instanceof Hotel) {
                        Hotel hotel = (Hotel) accommodation;
                        // Anulăm rezervarea și creștem numărul de camere libere
                        hotel.setFreeRooms(hotel.getFreeRooms() + reservation.getNumberOfRooms());
                        System.out.println("Rezervarea a fost anulată cu succes pentru " + reservation.getNumberOfRooms() + " camere în hotelul " + hotel.getName());
                    }

                    if (accommodation instanceof VacationHouse) {
                        VacationHouse vacationHouse = (VacationHouse) accommodation;
                        // Anulăm rezervarea și creștem numărul de camere libere
                        vacationHouse.setFreeRooms(vacationHouse.getFreeRooms() + reservation.getNumberOfRooms());
                        System.out.println("Rezervarea a fost anulată cu succes pentru " + reservation.getNumberOfRooms() + " camere în pensiunea " + vacationHouse.getName());
                    }

                    if(accommodation instanceof Apartment) {
                        Apartment apartment = (Apartment) accommodation;
                        System.out.println("Rezervarea a fost anulată cu succes pentru apartamentul " + reservation.getNumberOfRooms() + apartment.getName());
                    }
                }
            }
        } else {
            // Dacă rezervarea este în trecut, nu poate fi anulată
            System.out.println("Rezervarea nu poate fi anulată deoarece data de start a rezervării este deja trecută.");
        }
    }

    // 5. Metoda pentru adaugarea unei recenzii
    public void addReview(Review review) {
        reviews.add(review);
        System.out.println("Recenzia a fost adăugată cu succes pentru cazarea cu id-ul " + review.getAccommodationId());
    }

    // 6. Metoda pentru listarea recenziilor pentru o anumită cazare
    public List<Review> getReviewsForAccommodation(int accommodationId) {
        List<Review> reviewsForAccommodation = new ArrayList<>();
        for (Review review : reviews) {
            if (review.getAccommodationId() == accommodationId) {
                reviewsForAccommodation.add(review);
            }
        }
        return reviewsForAccommodation;
    }

    // 7. Metoda pentru listarea rezervărilor pentru o anumită cazare
    public List<Reservation> getReservationsForAccommodation(int accommodationId) {
        List<Reservation> reservationsForAccommodation = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (reservation.getAccommodationId() == accommodationId) {
                reservationsForAccommodation.add(reservation);
            }
        }
        return reservationsForAccommodation;
    }

    // 8. Metoda pentru căutarea cazărilor după țară
    public List<Accommodation> searchAccommodationsByCountry(String country) {
        List<Accommodation> accommodationsByCountry = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getCountry().equalsIgnoreCase(country)) {
                accommodationsByCountry.add(accommodation);
            }
        }
        return accommodationsByCountry;
    }

    // 9. Metoda pentru afișarea hotelurilor după numărul de stele
    public void displayHotelsByStarRating(int starRating) {
        System.out.println("Hoteluri cu " + starRating + " stele:");
        for (Accommodation accommodation : accommodations) {
            if (accommodation instanceof Hotel) {
                Hotel hotel = (Hotel) accommodation;
                if (hotel.getStarRating() == starRating) {
                    System.out.println(hotel.getName() + " - " + hotel.getStarRating() + " stele");
                }
            }
        }
    }

//    // 10. Metoda pentru a ordona toate cazări după rating
    public void sortAccommodationsByRating(List<Accommodation> accommodations) {
        accommodations.sort(Comparator.comparingDouble(Accommodation::calculateAverageRating).reversed());
    }

    // 11. Metoda pentru checkout
    public void checkOut(int reservationId) {
        LocalDate currentDate = LocalDate.now();

        // Găsim rezervarea cu id-ul dat
        Reservation reservationToCheckOut = null;
        for (Reservation reservation : reservations) {
            if (reservation.getId() == reservationId) {
                reservationToCheckOut = reservation;
                break;
            }
        }

        // Verificăm dacă s-a găsit rezervarea
        if (reservationToCheckOut != null) {
            // Actualizăm endDate-ul cu data curentă
            reservationToCheckOut.setEndDate(currentDate);

            // Obținem cazarea asociată rezervării
            Accommodation accommodation = getAccommodationById(reservationToCheckOut.getAccommodationId());

            // Verificăm tipul de cazare și actualizăm numărul de camere libere, dacă este cazul
            if (accommodation instanceof Hotel) {
                Hotel hotel = (Hotel) accommodation;
                hotel.setFreeRooms(hotel.getFreeRooms() + reservationToCheckOut.getNumberOfRooms());
                System.out.println("Check-out realizat cu succes pentru rezervarea cu id-ul " + reservationId + ". Numărul de camere libere în hotelul " + hotel.getName() + " a fost actualizat.");
            } else if (accommodation instanceof VacationHouse) {
                VacationHouse vacationHouse = (VacationHouse) accommodation;
                vacationHouse.setFreeRooms(vacationHouse.getFreeRooms() + reservationToCheckOut.getNumberOfRooms());
                System.out.println("Check-out realizat cu succes pentru rezervarea cu id-ul " + reservationId + ". Numărul de camere libere în pensiunea " + vacationHouse.getName() + " a fost actualizat.");
            }
        } else {
            System.out.println("Nu există nicio rezervare cu id-ul " + reservationId + ".");
        }
    }

    // Metodă pentru a obține o cazare după id
    private Accommodation getAccommodationById(int accommodationId) {
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getId() == accommodationId) {
                return accommodation;
            }
        }
        return null;
    }

    // 12. Metoda pentru adaugarea unui user
    public void addUser(User user) {
        users.add(user);
    }



    public void displayAccommodationsWithReservations() {
        for (Accommodation accommodation : accommodations) {
            System.out.println("Cazare: " + accommodation.getName());
            List<Reservation> reservationsForAccommodation = getReservationsForAccommodation(accommodation.getId());
            if (reservationsForAccommodation.isEmpty()) {
                System.out.println("Nu există rezervări pentru această cazare.");
            } else {
                System.out.println("Rezervări:");
                for (Reservation reservation : reservationsForAccommodation) {
                    System.out.println(" - Id rezervare: " + reservation.getId() +
                            ", Data de început: " + reservation.getStartDate() +
                            ", Data de sfârșit: " + reservation.getEndDate() +
                            ", Număr de camere: " + reservation.getNumberOfRooms());
                }
            }
            System.out.println();
        }
    }


}