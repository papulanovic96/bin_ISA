import { TestBed } from '@angular/core/testing';

import { RcOfficesService } from './rc-offices.service';

describe('RcOfficesService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: RcOfficesService = TestBed.get(RcOfficesService);
    expect(service).toBeTruthy();
  });
});
