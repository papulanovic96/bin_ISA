import { TestBed } from '@angular/core/testing';

import { FlightListService } from './flight-list.service';

describe('FlightListService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FlightListService = TestBed.get(FlightListService);
    expect(service).toBeTruthy();
  });
});
