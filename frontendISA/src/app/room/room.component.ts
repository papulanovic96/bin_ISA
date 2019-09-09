import {Component, OnInit} from '@angular/core';
import {Room} from "./room";
import {RoomService} from "./room.service";
import {HotelService} from "../hotel/hotel.service";
import {Hotel} from "../hotel/hotel";
import {Type} from "../hotel/type";
import {Router} from "@angular/router";
import {NewRoomPrice} from "./newRoomPrice";


@Component({
  selector: 'app-room',
  templateUrl: './room.component.html',
  styleUrls: ['./room.component.css']
})
export class RoomComponent implements OnInit {

  displayAdd = 'none';
  displayChange = 'none';
  displayNewPrice = 'none';
  listOfRooms: Room[];
  listOfHotels: Hotel[] = [];
  listOfTypes: Type[] = [];
  listOfReservedRooms: Boolean[] = [];
  newRoomPrice = new NewRoomPrice(1, 0, 0, new Date(), 0);

  newRoom = new Room(0, 0, 0, 0, 0, 0, false);
  changedRoom = new Room(0, 0, 0, 0, 0, 0, false);

  constructor(private roomService: RoomService, private hotelService: HotelService, private  router: Router) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
  }

  ngOnInit() {
    this.roomService.getAllRooms().subscribe(listOfRooms => {
      this.listOfRooms = listOfRooms;
      for (let room of this.listOfRooms) {
        this.roomService.reservedRoom(room.number).subscribe(
          reservedRoomCheck => {
            this.listOfReservedRooms.push(reservedRoomCheck);
          });
      }
    });
    for (let a of this.listOfReservedRooms) {
      console.log(a + " BB")
    }
    this.hotelService.getAllHotels().subscribe(listOfHotels => this.listOfHotels = listOfHotels);
    this.hotelService.getAllRoomTypes().subscribe(listOfTypes => this.listOfTypes = listOfTypes);
  }

  convertHotelIdToHotelName(hotelId: number): String {
    for (let hotel of this.listOfHotels) {
      if (hotel.hotel_id == hotelId) {
        return hotel.name
      }
    }
    return ""
  }

  convertTypeIdToTypeName(typeId: number): String {
    for (let type of this.listOfTypes) {
      if (type.id == typeId) {
        return type.name
      }
    }
    return ""
  }

  // reservedRoom(roomId: number): Boolean {
  // return Boolean(this.roomService.reservedRoom(roomId));
  //   .subscribe(
  //   reservedRoomCheck => {
  //     console.log(reservedRoomCheck)
  //     this.reservedRoomCheck=reservedRoomCheck;
  // },
  // () => {this.reservedRoomCheck=true },
  // ()=>{return this.reservedRoomCheck}
  //)
  //return this.reservedRoomCheck
  // }

  numberOnly(event): boolean {
    const charCode = (event.which) ? event.which : event.keyCode;
    if (charCode > 31 && (charCode < 48 || charCode > 57)) {
      return false;
    }
    return true;
  }

  addRoom() {
    if (this.newRoom.pricePerNight > 0 && this.newRoom.hotelId != 0 && this.newRoom.roomType != 0) {
      this.roomService.addRoom(this.newRoom).subscribe(
        newRoom => {
          this.router.navigated = false;
          this.router.navigate([this.router.url]);
        }
      );
      alert("Successfully added room!");
      this.closeAddRoomModal();
    }
  }

  removeRoom(roomNumber) {
    this.roomService.removeRoom(roomNumber).subscribe(
      roomNumber => {
        this.router.navigated = false;
        this.router.navigate([this.router.url])
      }
    )
    alert("Successfully removed room!");
  }

  changeRoom(roomNumber) {
    this.roomService.getRoom(roomNumber).subscribe(
      changedRoom => {
        this.changedRoom = changedRoom;
        this.openChangeRoomModal();
      }
    );
  }

  changeRoomSave() {
    this.roomService.changeRoom(this.changedRoom.number, this.changedRoom).subscribe(
      changedRoom => {
        this.router.navigated = false;
        this.router.navigate([this.router.url])
        alert("Successfully changed room!");
      }
    )
  }

  getToday(): string {
    return new Date().toISOString().split('T')[0]
  }

  openAddRoomModal() {
    this.displayAdd = 'block';
  }

  closeAddRoomModal() {
    this.displayAdd = 'none';
  }

  openChangeRoomModal() {
    this.displayChange = 'block';
  }

  closeChangeRoomModal() {
    this.displayChange = 'none';
  }

  openNewPriceModal(roomId: number) {
    this.newRoomPrice.roomId=roomId;
    this.displayNewPrice = 'block';
  }

  closeNewPriceModal() {
    this.displayNewPrice = 'none';
  }

  addNewPrice(){
    if(this.newRoomPrice.duration>0 && this.newRoomPrice.newPrice>0 && this.newRoomPrice.roomId!=0) {
      this.roomService.checkIfPriceExist(this.newRoomPrice).subscribe(
        exist =>{
          console.log(exist)
          if(exist==true){
            this.roomService.addNewPrice(this.newRoomPrice).subscribe(
              newPrice=>{
                this.closeNewPriceModal();
                alert("Successfully added new price")
              }
            );
          }
          else{
            console.log(exist)
            alert('New price already exist for that period of time')
          }
        }
      )
    }
  }

}