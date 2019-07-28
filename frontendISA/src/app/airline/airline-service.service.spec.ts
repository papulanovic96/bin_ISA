import { TestBed } from '@angular/core/testing';

import { AirlineServiceService } from './airline-service.service';

describe('AirlineServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AirlineServiceService = TestBed.get(AirlineServiceService);
    expect(service).toBeTruthy();
  });
});
