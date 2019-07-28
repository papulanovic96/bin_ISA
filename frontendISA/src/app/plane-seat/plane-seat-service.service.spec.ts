import { TestBed } from '@angular/core/testing';

import { PlaneSeatServiceService } from './plane-seat-service.service';

describe('PlaneSeatServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: PlaneSeatServiceService = TestBed.get(PlaneSeatServiceService);
    expect(service).toBeTruthy();
  });
});
