import { TestBed } from '@angular/core/testing';

import { RentaCarServiceService } from './renta-car-service.service';

describe('RentaCarServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RentaCarServiceService = TestBed.get(RentaCarServiceService);
    expect(service).toBeTruthy();
  });
});
