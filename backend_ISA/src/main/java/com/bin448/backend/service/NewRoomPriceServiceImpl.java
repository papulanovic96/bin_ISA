package com.bin448.backend.service;

import com.bin448.backend.converter.DiscountConverter;
import com.bin448.backend.converter.FastHotelReservationConverter;
import com.bin448.backend.converter.NewRoomPriceConverter;
import com.bin448.backend.entity.DTOentity.DiscountDTO;
import com.bin448.backend.entity.DTOentity.FastHotelReservationDTO;
import com.bin448.backend.entity.DTOentity.NewRoomPriceDTO;
import com.bin448.backend.entity.Discount;
import com.bin448.backend.entity.FastHotelReservation;
import com.bin448.backend.entity.NewRoomPrice;
import com.bin448.backend.repository.DiscountRepository;
import com.bin448.backend.repository.FastHotelReservationRepository;
import com.bin448.backend.repository.NewRoomPriceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewRoomPriceServiceImpl implements NewRoomPriceService {

    private final NewRoomPriceRepository newRoomPriceRepository;
    private final DiscountRepository discountRepository;
    private final FastHotelReservationRepository fastHotelReservationRepository;

    public NewRoomPriceServiceImpl(NewRoomPriceRepository newRoomPriceRepository, DiscountRepository discountRepository, FastHotelReservationRepository fastHotelReservationRepository) {
        this.newRoomPriceRepository = newRoomPriceRepository;
        this.discountRepository = discountRepository;
        this.fastHotelReservationRepository = fastHotelReservationRepository;
    }

    @Override
    public Boolean checkIfPricesAlreadyExist(NewRoomPriceDTO newRoomPriceDTO) {
        NewRoomPrice newPrice = NewRoomPriceConverter.toEntity(newRoomPriceDTO);

        List<NewRoomPrice> newPrices = newRoomPriceRepository.findByRoom_Number(newRoomPriceDTO.getRoomId());
        boolean notFound = true;

        for (NewRoomPrice r : newPrices) {
            if ((newPrice.getStartDate().compareTo(r.getEndDate()) > 0 && newPrice.getEndDate().compareTo(r.getEndDate()) > 0 && newPrice.getStartDate().compareTo(r.getStartDate()) > 0 && newPrice.getEndDate().compareTo(r.getStartDate()) > 0)) { //date1 je posle rezervisanog odlaska

            } else if (newPrice.getStartDate().compareTo(r.getEndDate()) < 0 && newPrice.getEndDate().compareTo(r.getEndDate()) < 0 && newPrice.getStartDate().compareTo(r.getStartDate()) < 0 && newPrice.getEndDate().compareTo(r.getStartDate()) < 0) {

            } else {
                notFound = false;
            }
        }

        return notFound;
    }

    @Override
    public Boolean checkIfDiscountAlreadyExist(Date start, Date end, Long roomId) {

        List<Discount> newPrices = discountRepository.findByRoom_Number(roomId);
        boolean notFound = true;

        for (Discount r : newPrices) {
            if ((start.compareTo(r.getEndDate()) > 0 && end.compareTo(r.getEndDate()) > 0 && start.compareTo(r.getStartDate()) > 0 && end.compareTo(r.getStartDate()) > 0)) { //date1 je posle rezervisanog odlaska

            } else if (start.compareTo(r.getEndDate()) < 0 && end.compareTo(r.getEndDate()) < 0 && start.compareTo(r.getStartDate()) < 0 && end.compareTo(r.getStartDate()) < 0) {

            } else {
                notFound = false;
            }
        }

        return notFound;
    }

    @Override
    public void addNewPrice(NewRoomPriceDTO newRoomPriceDTO) {
        newRoomPriceRepository.save(NewRoomPriceConverter.toEntity(newRoomPriceDTO));
    }

    @Override
    public void addDiscount(DiscountDTO discountDTO) {
        discountRepository.save(DiscountConverter.toEntity(discountDTO));
    }

    @Override
    public List<DiscountDTO> getValidDiscounts(FastHotelReservationDTO fastHotelReservationDTO) {
        FastHotelReservation fastHotelReservation = FastHotelReservationConverter.toEntity(fastHotelReservationDTO);
        List<Discount> discounts = discountRepository.findByDestination(fastHotelReservationDTO.getDestination());
        List<Discount> appropriateDiscounts = new ArrayList<>();

        for (Discount r : discounts) {
            boolean notValid = false;
            List<Integer> closest = new ArrayList<>();
            //ako je tekuci discount
            if (fastHotelReservationDTO.getArrivalDate().compareTo(r.getStartDate()) >= 0 && fastHotelReservationDTO.getArrivalDate().compareTo(r.getEndDate()) < 0) {
                List<FastHotelReservation> fastResDiscounts = fastHotelReservationRepository.findByDiscount_Id(r.getId());
                for (FastHotelReservation res : fastResDiscounts) {
                    if (fastHotelReservation.getArrivalDate().compareTo(res.getArrivalDate()) > 0 && fastHotelReservation.getArrivalDate().compareTo(res.getEndDate()) > 0) {

                    } else if ((res.getArrivalDate().getTime() - fastHotelReservation.getArrivalDate().getTime()) / (24 * 60 * 60 * 1000) > 0 && fastHotelReservation.getArrivalDate().compareTo(res.getEndDate()) < 0) {
                        Long dec = (res.getArrivalDate().getTime() - fastHotelReservation.getArrivalDate().getTime()) / (24 * 60 * 60 * 1000);
                        if (dec.intValue() > 0) {
                            closest.add(dec.intValue());
                        }
                    } else {
                        notValid = true;
                    }
                }
            } else {
                notValid = true;
            }
            if (notValid == false) {
                if (closest.size() > 0) {
                    Integer min = closest.get(0);
                    for (Integer i : closest) {
                        if (i < min) {
                            min = i;
                        }
                    }
                    Calendar c = Calendar.getInstance();
                    c.setTime(fastHotelReservation.getArrivalDate());
                    c.add(Calendar.DATE, min);
                    Date newEndDate = c.getTime();
                    if (newEndDate.compareTo(r.getEndDate()) < 0) {
                        r.setEndDate(newEndDate);
                    }
                }
                appropriateDiscounts.add(r);

            }

        }

        return appropriateDiscounts
                .stream()
                .map(discount -> DiscountConverter.fromEntity(discount))
                .collect(Collectors.toList());
    }

}
