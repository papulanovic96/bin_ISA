import { TestBed } from '@angular/core/testing';

import { TicketFinalizeService } from './ticket-finalize.service';

describe('TicketFinalizeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TicketFinalizeService = TestBed.get(TicketFinalizeService);
    expect(service).toBeTruthy();
  });
});
