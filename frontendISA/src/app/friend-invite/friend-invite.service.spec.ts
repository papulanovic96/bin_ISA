import { TestBed } from '@angular/core/testing';

import { FriendInviteService } from './friend-invite.service';

describe('FriendInviteService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FriendInviteService = TestBed.get(FriendInviteService);
    expect(service).toBeTruthy();
  });
});
