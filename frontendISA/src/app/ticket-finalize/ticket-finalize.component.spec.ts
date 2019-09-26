import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketFinalizeComponent } from './ticket-finalize.component';

describe('TicketFinalizeComponent', () => {
  let component: TicketFinalizeComponent;
  let fixture: ComponentFixture<TicketFinalizeComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TicketFinalizeComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TicketFinalizeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
