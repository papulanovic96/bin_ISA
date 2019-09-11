import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RentacarOfficesComponent } from './rentacar-offices.component';

describe('RentacarOfficesComponent', () => {
  let component: RentacarOfficesComponent;
  let fixture: ComponentFixture<RentacarOfficesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RentacarOfficesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RentacarOfficesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
