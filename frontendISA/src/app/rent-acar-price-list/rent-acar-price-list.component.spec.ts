import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentACarPriceListComponent } from './rent-acar-price-list.component';

describe('RentACarPriceListComponent', () => {
  let component: RentACarPriceListComponent;
  let fixture: ComponentFixture<RentACarPriceListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentACarPriceListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentACarPriceListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
