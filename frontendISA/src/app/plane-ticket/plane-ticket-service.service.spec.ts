import { TestBed } from '@angular/core/testing';

import { PlaneTicketServiceService } from './plane-ticket-service.service';

describe('PlaneTicketServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlaneTicketServiceService = TestBed.get(PlaneTicketServiceService);
    expect(service).toBeTruthy();
  });
});
