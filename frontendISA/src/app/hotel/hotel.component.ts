import {Component, OnInit} from '@angular/core';
import {Hotel} from "./hotel";
import {HotelService} from "./hotel.service";
import {RoomService} from "../room/room.service";
import {Room} from "../room/room";
import {Router} from "@angular/router";
import {AuthenticationService} from "../service/authentication.service";


@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  listOfHotels: Hotel[];
  listOfHotelRooms: Room[];
  displayRooms = 'none';
  displayMenu = 'none';
  displayDescription = 'none';
  displayMenuItem = 'none';
  displayChangeHotelModal = 'none';
  menu: Map<String, Number> = new Map<String, Number>();
  public static hotelId: number = 0;
  menuItemName: String = "";
  menuItemPrice: number = 0;
  hotelId: number = 0;
  menuNames: String[] = [];
  menuPrices: number[] = [];
  description: String = "";
  changedHotel = new Hotel(0, "", "", "", 0);

  constructor(private hotelService: HotelService, private roomService: RoomService, private  router: Router,public loginService:AuthenticationService) {
    this.router.routeReuseStrategy.shouldReuseRoute = function () {
      return false;
    }
  }

  ngOnInit() {
    this.hotelService.getAllHotels().subscribe(listOfHotels => this.listOfHotels = listOfHotels);
  }

  removeHotel(hotelId) {
    this.hotelService.removeHotel(hotelId).subscribe(
      deleted => {
        console.log(deleted)
        if (deleted==true) {
          this.router.navigated = false;
          this.router.navigate([this.router.url])
          alert("Successfully removed hotel!");
        } else {
          alert("Hotel still have rooms! Can't be deleted!");
        }
      }
    )
  }

  changeHotel(hotelId: number) {
    this.hotelService.getHotel(hotelId).subscribe(
      changedHotel => {
        this.changedHotel = changedHotel;
        this.openChangeHotelModal()
      }
    )
  }

  changeHotelSave() {
    this.hotelService.changeHotel(this.changedHotel.hotel_id, this.changedHotel).subscribe(
      changedHotel => {
        this.router.navigated = false;
        this.router.navigate([this.router.url])
        alert("Successfully changed hotel!");
      }
    )
  }

  addMenuItem() {
    if (this.menuItemName != "" && this.menuItemPrice > 0) {
      this.hotelService.addMenuItem(this.menuItemName, this.menuItemPrice, this.hotelId).subscribe(
        reply => {
          this.listMenu(this.hotelId)
          this.closeMenuItemModal()
          this.menuItemName = ""
          this.menuItemPrice = 0
        }
      )
    } else {
      alert('You have to fill required fields')
    }
  }

  removeAdditionalService(item: String) {
    this.hotelService.removeMenuItem(item, this.hotelId).subscribe(
      reply => {
        this.listMenu(this.hotelId)
        this.closeMenuItemModal()
        this.menuItemName = ""
        this.menuItemPrice = 0
      }
    )
  }

  openDescription(hotel_id: number) {
    this.hotelService.getDescription(hotel_id).subscribe(
      description => {
        this.description = description;
        if (description == "") {
          this.description = "There is no description for this hotel"
        }
        this.displayDescription = 'block'
      }
    )
  }

  closeDescription() {
    this.displayDescription = 'none'
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
    this.hotelId = hotelId
    this.menuNames = [];
    this.menuPrices = [];
    this.hotelService.getMenu(hotelId).subscribe(
      menu => {
        this.menu = menu
        if (menu != null) {
          for (let item of Object.keys(menu)) {
            this.menuNames.push(item)
            this.menuPrices.push(menu[item])
          }
        }
        this.openModalMenu();
      }
    )
  }

  openReservationModal(hotelId: number) {
    HotelComponent.hotelId = hotelId;
    this.router.navigated = false;
    this.router.navigate(['/reservations']);
  }

  openMenuItemModal() {
    this.displayMenuItem = 'block'
    this.closeModalMenu()
  }

  closeMenuItemModal() {
    this.displayMenuItem = 'none'
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

}
