package com.bin448.backend.service;

import com.bin448.backend.converter.AddressConverter;
import com.bin448.backend.converter.HotelConverter;
import com.bin448.backend.entity.Address;
import com.bin448.backend.entity.DTOentity.AddressDTO;
import com.bin448.backend.entity.DTOentity.HotelDTO;
import com.bin448.backend.entity.Hotel;
import com.bin448.backend.exception.ForeignKeyConstraintException;
import com.bin448.backend.exception.NotFoundException;
import com.bin448.backend.repository.AddressRepository;
import com.bin448.backend.repository.HotelRepository;
import com.bin448.backend.repository.HotelReservationRepository;
import com.bin448.backend.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = false)
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final AddressRepository addressRepository;
    private final HotelReservationRepository hotelReservationRepository;

    public HotelServiceImpl(HotelRepository hotelRepository, RoomRepository roomRepository, AddressRepository addressRepository, HotelReservationRepository hotelReservationRepository) {
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
        this.addressRepository = addressRepository;
        this.hotelReservationRepository = hotelReservationRepository;
    }

    @Override
    public HotelDTO getHotel(Long hotelId) {
        return HotelConverter.fromEntity(findHotel(hotelId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> findAll() {
        return hotelRepository.findByDeleted(false).stream()
                .map(hotel -> HotelConverter.fromEntity(hotel))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<AddressDTO> findAllAddresses() {
        return addressRepository.findAll().stream()
                .map(address -> AddressConverter.fromEntity(address))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String addHotel(HotelDTO hotelDTO) {
        hotelDTO.setAvgGrade(0d);
        Hotel newHotel = HotelConverter.toEntity(hotelDTO);
        newHotel.setDeleted(false);
        hotelRepository.save(newHotel);
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String addMenuItem(String name, Double price, Long hotelId) {
        Hotel hotel = findHotel(hotelId);
        if (hotel.getMenu() == null) {
            HashMap<String, Double> menu = new HashMap<>();
            menu.put(name, price);
            hotel.setMenu(menu);
        } else {
            hotel.getMenu().put(name, price);
        }
        hotelRepository.save(hotel);
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String removeMenuItem(String name, Long hotelId) {
        Hotel hotel = findHotel(hotelId);
        if (hotel.getMenu() != null) {
            HashMap<String, Double> newMenu = hotel.getMenu();
            for (Iterator<String> it = newMenu.keySet().iterator(); it.hasNext(); ) {
                String itemName = it.next();
                if (itemName.equals(name)) {
                    it.remove();
                    break;
                }
            }
            hotel.setMenu(newMenu);
            hotelRepository.save(hotel);
        }
        return "SUCCESS";
    }

    @Override
    public HashMap<String, Double> getMenu(Long hotelId) {
        return findHotel(hotelId).getMenu();
    }

    @Override
    public String getDescription(Long hotelId) {
        return findHotel(hotelId).getDescription();
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public Boolean removeHotel(Long id) {
        if (roomRepository.findByHotel_IdAndDeleted(id, false).size() == 0) {
            Hotel hotel = findHotel(id);
            hotel.setDeleted(true);
            hotelRepository.save(hotel);
            return true;
        }
        return false;
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.SERIALIZABLE)
    public String changeHotel(HotelDTO hotelDTO, Long id) {
        hotelDTO.setHotel_id(id);
        Hotel hotel = findHotel(id);
        hotelRepository.save(HotelConverter.toEntity(hotelDTO));
        return "SUCCESS";
    }

    @Override
    @Transactional(readOnly = true)
    public void checkIfHotelExists(Long id) {
        hotelRepository.findByIdAndDeleted(id, false)
                .orElseThrow(() -> new ForeignKeyConstraintException(String.format("Hotel with id %s not found", id)));
    }

    private Hotel findHotel(Long hotelId) {
        return hotelRepository.findByIdAndDeleted(hotelId, false)
                .orElseThrow(() -> new NotFoundException(String.format("Hotel with id %s not found", hotelId)));
    }

    @Override
    @Transactional(readOnly = true)
    public List<HotelDTO> searchHotels(String name, String address, String arrival, String end) {
        List<Hotel> hotels = hotelRepository.findByDeleted(false);
        List<Hotel> addressHotels = new ArrayList<>();
        List<Address> addresses = addressRepository.findAll();
        List<Address> suitableAddresses = new ArrayList<>();
        for (Address ad : addresses) {
            if (ad.getCity().toUpperCase().equals(address.toUpperCase())) {
                suitableAddresses.add(ad);
            }
        }

        for (Hotel hotel : hotels) {
            for (Address ad : suitableAddresses) {
                if (hotel.getAddress().getId() == ad.getId()) {
                    addressHotels.add(hotel);
                }
            }
        }
        List<Hotel> nameAndAddressesHotels = new ArrayList<>();

        List<Hotel> nameHotels = hotelRepository.searchHotelName(name);
        if (nameHotels.size() == 0) {
            nameAndAddressesHotels = addressHotels;
        } else if (addressHotels.size() == 0) {
            nameAndAddressesHotels = nameHotels;
        } else {
            for (Hotel hotel : nameHotels) {
                for (Hotel hotel1 : addressHotels) {
                    if (hotel.getId().equals(hotel1.getId())) {
                        nameAndAddressesHotels.add(hotel);
                    }
                }
            }
        }

        Date arrivalDate = new Date();
        Date endDate = new Date();
        DateFormat formatter = new SimpleDateFormat("yyyy-mm-dd");

        try {
            arrivalDate = formatter.parse(arrival);
            endDate = formatter.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List<Hotel> endHotels = new ArrayList<>();
        List<Hotel> all = hotelRepository.searchHotelByDate(arrival, end);
        for (Hotel h : nameAndAddressesHotels) {
            boolean added = false;
            for (Hotel hotel : all) {
                if (h.getId() == hotel.getId()) {
                    endHotels.add(h);
                    added = true;
                }
            }
            if (added == false) {
                if (hotelReservationRepository.getByRoom_Hotel_Id(h.getId()).size() == 0) {
                    endHotels.add(h);
                }
            }
        }

        return endHotels.stream()
                .map(hotel -> HotelConverter.fromEntity(hotel))
                .collect(Collectors.toList());
    }
}
