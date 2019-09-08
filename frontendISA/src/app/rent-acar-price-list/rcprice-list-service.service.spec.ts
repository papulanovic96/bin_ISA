import { TestBed } from '@angular/core/testing';

import { RCPriceListServiceService } from './rcprice-list-service.service';

describe('RCPriceListServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RCPriceListServiceService = TestBed.get(RCPriceListServiceService);
    expect(service).toBeTruthy();
  });
});
