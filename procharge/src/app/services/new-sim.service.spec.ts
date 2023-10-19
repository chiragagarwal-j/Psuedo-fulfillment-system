import { TestBed } from '@angular/core/testing';

import { NewSimService } from './new-sim.service';

describe('NewSimService', () => {
  let service: NewSimService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NewSimService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
