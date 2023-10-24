import { TestBed } from '@angular/core/testing';

import { RechargeSimService } from './recharge-sim.service';

describe('RechargeSimService', () => {
  let service: RechargeSimService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RechargeSimService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
