import {from} from "rxjs";

import {Hotel} from './hotel';
import {HotelService} from './hotel.service';
import {RoomService} from '../room/room.service';
import {Room} from '../room/room';
import {Router} from '@angular/router';
import {AuthenticationService} from '../service/authentication.service';
import {FastHotelReservation} from './fastHotelReservation';
import {Address} from './address';
import {Discount} from '../room/discount';
import {Type} from '../hotel/type';
import {DatePipe} from '@angular/common';
import {Component, OnInit} from "@angular/core";

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css'],
  providers: [DatePipe]
})
export class HotelComponent implements OnInit {


  constructor(private hotelService: HotelService, private roomService: RoomService, private  router: Router, public loginService: AuthenticationService, private datePipe: DatePipe) {
    this.router.routeReuseStrategy.shouldReuseRoute = function() {
      return false;
    };
  }
  public static hotelId = 0;
  static fastReservation = new FastHotelReservation(0, 0, 0, '', new Date(), 5, 'NS');

  idOfHotel: number;
  rateOfHotel: number;
  idOfRoom:number;
  rateOfRoom:number;
  listOfHotels: Hotel[];
  listOfHotelRooms: Room[];
  displayRooms = 'none';
  displayMenu = 'none';
  displayRate = 'none';
  displayRateRoom = 'none';
  displayDescription = 'none';
  displayMenuItem = 'none';
  displayChangeHotelModal = 'none';
  displayFastResNights = 'none';
  displayLocation='none';
  menu: Map<String, Number> = new Map<String, Number>();
  menuItemName: String = '';
  menuItemPrice = 0;
  hotelId = 0;
  menuNames: String[] = [];
  menuPrices: number[] = [];
  description: String = '';
  listOfAddress: Address[] = [];
  listOfRooms: Room[] = [];
  listOfTypes: Type[] = [];
  findHotelName: String = '';
  address: String = '';
  arrival = new Date('2018-11-11');
  return = new Date('2018-11-11');

  changedHotel = new Hotel(0, '', 0, '', 0);
  ///////////////////////////////////////////////////////////////////////////////////////////////////////// promeni gornji det na prazan string
  destination: String = '';
  validDiscounts: Discount[] = [];
  displayDiscountRooms = 'none';
  fastResNumberOfNights = 0;

  ngOnInit() {
    this.destination = HotelComponent.fastReservation.destination;
    this.hotelService.getAllHotels().subscribe(listOfHotels => this.listOfHotels = listOfHotels);
    this.hotelService.getAllAddresses().subscribe(listOfAddresses => this.listOfAddress = listOfAddresses);
    this.roomService.getAllRooms().subscribe(listOfRooms => this.listOfRooms = listOfRooms);
    this.hotelService.getAllRoomTypes().subscribe(listOfTypes => this.listOfTypes = listOfTypes);
  }

  find() {
    if (this.findHotelName == "" && this.address == "") {
      alert("You have to fill hotel name or address")
    } else if (this.arrival < new Date() || this.return < new Date()) {
      alert("Both dates are required")
    } else if (this.return < this.arrival) {
      alert('Arrival date have to be bigger than return date')
    } else {
      if (this.address == "") {
        this.hotelService.findHotels(this.findHotelName, this.address, new Date(this.arrival).toISOString().split('T')[0], new Date(this.return).toISOString().split('T')[0]).subscribe(
          hotels => {
            this.listOfHotels = hotels;
            this.router.navigated = false;
            this.router.navigate([this.router.url])
          }
        )
      } else {
        let found = false;
        for (let adress of this.listOfAddress) {
          if (adress.city.toUpperCase() == this.address.toUpperCase()) {
            found = true;
          }
        }
        if (found == false) {
          alert("There is no hotel in " + this.address);
        } else {
          this.hotelService.findHotels(this.findHotelName, this.address, new Date(this.arrival).toISOString().split('T')[0], new Date(this.return).toISOString().split('T')[0]).subscribe(
            hotels => {
              this.listOfHotels = hotels;
              this.router.navigated = false;
              this.router.navigate([this.router.url])
            }
          )
        }
      }
    }


  }

  preRateHotel(id: number) {
    this.idOfHotel = id;
    this.displayRate = 'block';
  }

  preRateRoom(id: number) {
    this.idOfRoom = id;
    this.displayRateRoom = 'block';
  }

  rateHotel() {
    return this.hotelService.rateHotel(this.idOfHotel, this.rateOfHotel).subscribe(
      data => {
        if (data === false) {
          alert('You cant rate');
          this.displayRate = 'none';
        } else{
          alert('SUCCESS');
          this.displayRate = 'none';
        }

      },
      error1 => alert('ERROR')
    );
  }

  rateRoom() {
    return this.hotelService.rateRoom(this.idOfRoom, this.rateOfRoom).subscribe(
      data => {
        if (data === false) {
          alert('You cant rate');
          this.displayRateRoom = 'none';
        } else{
          alert('SUCCESS');
          this.displayRateRoom = 'none';
        }

      },
      error1 => alert('ERROR')
    );
  }

  makeFastReservation() {
    if (this.fastResNumberOfNights > 0) {
      for (const discount of this.validDiscounts) {
        if (discount.id == HotelComponent.fastReservation.discountId) {
          const returnDate: Date = new Date((new Date(HotelComponent.fastReservation.arrivalDate)).getTime() + (60 * 60 * 24 * 1000) * this.fastResNumberOfNights);
          const discountEndDate: Date = new Date((new Date(discount.startDate)).getTime() + (60 * 60 * 24 * 1000) * (discount.duration + 1));
          const discount2 = new Date((new Date(discount.startDate)).getTime() + (60 * 60 * 24 * 1000) * discount.duration);
          if (returnDate < discountEndDate) {
            const sumPrice: number = HotelComponent.fastReservation.sumPrice * this.fastResNumberOfNights;
            HotelComponent.fastReservation.sumPrice = sumPrice;
            HotelComponent.fastReservation.userUsername = this.loginService.getLogged();
            HotelComponent.fastReservation.numberOfNights = this.fastResNumberOfNights;
            this.hotelService.addFastRes(HotelComponent.fastReservation).subscribe(
              fastRes => {
                this.fastResNumberOfNights = 0;
                alert('Reservation is successful');
                this.closeFastResNightsModal();
                this.closeDiscountRoomsModal();
              }
            );
          } else {
            alert('Discount expires ' + this.datePipe.transform(discount2, 'yyyy-MM-dd'));
          }
        }
      }

    } else {
      alert('You have to add number of nights');
    }
    // FALI NUMBER OF NIGHTS
    // arrivalDate setuje se kod papule
    // destination setuje papula

    // subscribe adding
    // destination ili ceo fast rez se setuje na nista da u zavisnosti od toga prikazujem dugme za popuste
  }

  returnDate(startDate: Date, duration: number): Date {
    return new Date((new Date(startDate)).getTime() + (60 * 60 * 24 * 1000) * duration);
  }

  getToday(): string {
    return new Date().toISOString().split('T')[0];
  }

  getTypeName(typeId: number): String {
    for (const type of this.listOfTypes) {
      if (type.id == typeId) {
        return type.name;
      }
    }
  }

  getHotelName(roomId: number): String {
    for (const room of this.listOfRooms) {
      if (roomId == room.number) {
        for (const hotel of this.listOfHotels) {
          if (hotel.hotel_id == room.hotelId) {
            return hotel.name;
          }
        }
      }
    }
  }

  getHotelAddress(roomId: number): String {
    for (const room of this.listOfRooms) {
      if (roomId == room.number) {
        for (const hotel of this.listOfHotels) {
          if (hotel.hotel_id == room.hotelId) {
            for (const addr of this.listOfAddress) {
              if (addr.id == hotel.addressId) {
                return addr.cityAddress + ', ' + addr.city;
              }
            }
          }
        }
      }
    }
  }

  listAdditionalServices(discountId: number): String {
    let combined: String = '';
    for (const discount of this.validDiscounts) {
      if (discountId == discount.id) {
        for (let i = 0; i < discount.additionalServices.length; i++) {
          if (i == 0) {
            combined = combined + ' ' + discount.additionalServices[i];
          } else {
            combined = combined + ', ' + discount.additionalServices[i];
          }
        }
      }
    }
    return combined;
  }

  getValidDiscounts() {
    this.hotelService.getValidDiscounts(HotelComponent.fastReservation).subscribe(
      listOfDiscounts => {
        this.validDiscounts = listOfDiscounts;
        this.openDiscountRoomsModal();
      }
    );
  }

  findRoomFromDiscount(roomId: number): Room {
    for (const r of this.listOfRooms) {
      if (r.number == roomId) {
        return r;
      }
    }
  }

  removeHotel(hotelId) {
    this.hotelService.removeHotel(hotelId).subscribe(
      deleted => {
        if (deleted == true) {
          this.router.navigated = false;
          this.router.navigate([this.router.url]);
          alert('Successfully removed hotel!');
        } else {
          alert('Hotel still have rooms! Can\'t be deleted!');
        }
      }
    );
  }

  convertAddressIdToName(addressId: number): String {
    let fullAddress = '';
    for (const address of this.listOfAddress) {
      if (address.id == addressId) {
        fullAddress = address.cityAddress + ', ' + address.city + ', ' + address.country;
        return fullAddress;
      }
    }
    return '';
  }

  changeHotel(hotelId: number) {
    this.hotelService.getHotel(hotelId).subscribe(
      changedHotel => {
        this.changedHotel = changedHotel;
        this.openChangeHotelModal();
      }
    );
  }

  changeHotelSave() {
    this.hotelService.changeHotel(this.changedHotel.hotel_id, this.changedHotel).subscribe(
      changedHotel => {
        this.router.navigated = false;
        this.router.navigate([this.router.url]);
        alert('Successfully changed hotel!');
      }
    );
  }

  addMenuItem() {
    if (this.menuItemName != '' && this.menuItemPrice > 0) {
      this.hotelService.addMenuItem(this.menuItemName, this.menuItemPrice, this.hotelId).subscribe(
        reply => {
          this.listMenu(this.hotelId);
          this.closeMenuItemModal();
          this.menuItemName = '';
          this.menuItemPrice = 0;
        }
      );
    } else {
      alert('You have to fill required fields');
    }
  }

  removeAdditionalService(item: String) {
    this.hotelService.removeMenuItem(item, this.hotelId).subscribe(
      reply => {
        this.listMenu(this.hotelId);
        this.closeMenuItemModal();
        this.menuItemName = '';
        this.menuItemPrice = 0;
      }
    );
  }

  openDescription(hotel_id: number) {
    this.hotelService.getDescription(hotel_id).subscribe(
      description => {
        this.description = description;
        if (description == '') {
          this.description = 'There is no description for this hotel';
        }
        this.displayDescription = 'block';
      }
    );
  }

  closeDescription() {
    this.displayDescription = 'none';
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  listRooms(hotelId: number) {
    this.roomService.getRoomsByHotelId(hotelId).subscribe(listOfHotelRooms => {
      this.listOfHotelRooms = listOfHotelRooms;
      this.openModalRooms();
    });
  }

  listMenu(hotelId: number) {
    this.hotelId = hotelId;
    this.menuNames = [];
    this.menuPrices = [];
    this.hotelService.getMenu(hotelId).subscribe(
      menu => {
        this.menu = menu;
        if (menu != null) {
          for (const item of Object.keys(menu)) {
            this.menuNames.push(item);
            this.menuPrices.push(menu[item]);
          }
        }
        this.openModalMenu();
      }
    );
  }

  openReservationModal(hotelId: number) {
    HotelComponent.hotelId = hotelId;
    this.router.navigated = false;
    this.router.navigate(['/reservations']);
  }

  openMenuItemModal() {
    this.displayMenuItem = 'block';
    this.closeModalMenu();
  }

  closeMenuItemModal() {
    this.displayMenuItem = 'none';
  }

  openModalRooms() {
    this.displayRooms = 'block';
  }

  closeModalRooms() {
    this.displayRooms = 'none';
  }

  openModalMenu() {
    this.displayMenu = 'block';
  }

  closeModalMenu() {
    this.displayMenu = 'none';
  }

  openChangeHotelModal() {
    this.displayChangeHotelModal = 'block';
  }

  closeChangeHotelModal() {
    this.displayChangeHotelModal = 'none';
  }


  openDiscountRoomsModal() {
    this.displayDiscountRooms = 'block';
  }

  closeDiscountRoomsModal() {
    this.displayDiscountRooms = 'none';
  }

  openFastResNightsModal(discountId: number, price: number) {
    HotelComponent.fastReservation.discountId = discountId;
    HotelComponent.fastReservation.sumPrice = price;
    this.displayFastResNights = 'block';
  }

  closeFastResNightsModal() {
    this.displayFastResNights = 'none';
  }

  openLocationDialog(){
    this.displayLocation='block';
  }

  closeLocationDialog(){
    this.displayLocation='none';
  }

  openRateModal() {
    this.displayRate = 'display';
  }
  closeRateModal() {
    this.displayRate = 'none';
  }

  openRateRoomModal() {
    this.displayRateRoom = 'block';
  }
  closeRateRoomModal() {
    this.displayRateRoom = 'none';
  }

}
