import {Component, OnInit} from '@angular/core';
import {Reservation} from "./reservation";
import {Type} from "../hotel/type";
import {HotelService} from "../hotel/hotel.service";
import {ReservationService} from "./reservation.service";
import {Router} from "@angular/router";
import {Room} from "../room/room";
import {TypePrice} from "./typePrice";
import {HotelComponent} from "../hotel/hotel.component";

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.css']
})
export class ReservationComponent implements OnInit {
  displayRoom = 'none';
  foundRooms = 'none';
  additionalServices = 'none';
  menuNames: String[] = [];
  menuPrices: number[] = [];
  listOfTypes: Type[];
  listOfChosenRoomIds: number[] = [];
  listOfReservationTypesPrices: TypePrice[] = [];
  proceed: Boolean = true;
  newType = new Type(1, "", 0);
  newPrice: number = 0;
  listOfAvailableRooms: Room[][];
  numberOfBeds: number = 0;
  reservationAdditionalServices: String[] = []
  reservation = new Reservation(1, 0, 0, 1, new Date(), 0, 0,this.reservationAdditionalServices);

  constructor(private hotelService: HotelService, private  router: Router, private reservationService: ReservationService) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
  }

  backToHotels() {
    this.router.navigated = false;
    this.router.navigate(['/hotels']);
  }

  addTypePriceToList() {
    let newTypePrice = new TypePrice(this.newType, this.newPrice);
    if (newTypePrice.type.name != "") {
      this.listOfReservationTypesPrices.push(newTypePrice);
      this.closeRoomModal();
    }
  }

  removeType(typeId, price) {
    for (let i = 0; i < this.listOfReservationTypesPrices.length; i++) {
      if (this.listOfReservationTypesPrices[i].type.id == typeId && this.listOfReservationTypesPrices[i].price == price) {
        this.listOfReservationTypesPrices.splice(i, 1);
        break;
      }
    }
  }

  getToday(): string {
    return new Date().toISOString().split('T')[0]
  }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  addAdditionalService(item: String) {
    let found = false
    for (let i = 0; i < this.reservationAdditionalServices.length; i++) {
      if (this.reservationAdditionalServices[i] == item) {
        found = true;
        this.reservationAdditionalServices.splice(i, 1);
        break;
      }
    }
    if (found == false) {
      this.reservationAdditionalServices.push(item);
    }
  }

  finishChoosingAdditional() {/////////////////////////////////////////////////////////dodaj ovo u rez i sacuvaj rez
    this.reservation.additionalServices=this.reservationAdditionalServices
    alert('Reservation is successful')
    for (let roomId of this.listOfChosenRoomIds) {
      console.log(roomId)
      this.reservation.roomId = roomId;
      this.reservationService.addReservation(this.reservation).subscribe();
    }
    this.backToHotels()
    for (let a of this.reservation.additionalServices) {
      console.log(a)
    }
  }

  openAdditionalServicesModal() {
    this.hotelService.getMenu(this.reservation.hotelId).subscribe(
      menu => {
        if (menu != null) {
          for (let item of Object.keys(menu)) {
            this.menuNames.push(item)
            this.menuPrices.push(menu[item])
          }
        }
      }
    )
    this.additionalServices = 'block'
  }

  closeAdditionalServicesModal() {
    this.additionalServices = 'none'
  }

  openRoomModal() {
    this.displayRoom = 'block';
  }

  closeRoomModal() {
    this.displayRoom = 'none';
  }

  closeFoundRoomModal() {
    this.foundRooms = 'none';
  }

  reserve() {
    if (this.listOfReservationTypesPrices.length == 0) {
      alert("You must choose type of your room/rooms");
    }
    if (this.reservation.numberOfNights > 0 && this.reservation.numberOfGuests > 0 && this.listOfReservationTypesPrices.length > 0) {
      this.numberOfBeds = 0;
      for (let typePrice of this.listOfReservationTypesPrices) {
        this.numberOfBeds = +this.numberOfBeds + +typePrice.type.guests;
      }
      if (this.numberOfBeds >= this.reservation.numberOfGuests) {
        this.proceed = true;
        this.reservation.hotelId = HotelComponent.hotelId;

        let listOfReservationTypesId: number[] = [];
        let listOfReservationPrices: number[] = [];
        for (let typePrice of this.listOfReservationTypesPrices) {
          listOfReservationTypesId.push(typePrice.type.id);
          listOfReservationPrices.push(typePrice.price);
        }
        this.reservationService.setReservation(this.reservation).subscribe(
          res => {
            let noAvailable = false;
            this.reservationService.findAvailableRooms(this.reservation, listOfReservationTypesId, listOfReservationPrices).subscribe(
              listOfAvailableRooms => {
                this.listOfAvailableRooms = listOfAvailableRooms;
                for (let availableRooms of listOfAvailableRooms) {
                  if (availableRooms.length == 0) {
                    noAvailable = true;
                  }
                }
                if (noAvailable) {
                  alert("There is no available room!")
                } else {
                  this.listOfChosenRoomIds.length = listOfAvailableRooms.length;
                  // for (let rooms of listOfAvailableRooms) {
                  //   for (let room of rooms) {
                  //     console.log(room.number)
                  //
                  //   }
                  // }
                  this.foundRooms = 'block';
                }
              }
            );
          }
        );
      } else {
        alert("You don't have enough beds for number of guests!");

      }
    }
  }

  choseRooms(id: number, it: number) {
    //console.log(id + " " + it)
    for (let i = 0; i < this.listOfChosenRoomIds.length; i++) {
      if (i == it) {
        this.listOfChosenRoomIds[i] = id;
      }
    }
  }

  finishChoosing() {
    let duplicate = false;
    for (let i = 0; i < this.listOfChosenRoomIds.length - 1; i++) {
      for (let j = i + 1; j < this.listOfChosenRoomIds.length; j++) {
        if (this.listOfChosenRoomIds[i] == this.listOfChosenRoomIds[j]) {
          duplicate = true;
        }
      }
    }
    if (duplicate) {
      alert('You have chosen the same room multiple times!')
    } else {
      this.closeFoundRoomModal();
      this.openAdditionalServicesModal()
    }
  }

  ngOnInit() {
    this.hotelService.getAllRoomTypes().subscribe(listOfTypes => this.listOfTypes = listOfTypes);
  }
}
