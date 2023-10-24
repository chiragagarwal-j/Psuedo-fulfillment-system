import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechargePlansComponent } from './recharge-plans.component';

describe('RechargePlansComponent', () => {
  let component: RechargePlansComponent;
  let fixture: ComponentFixture<RechargePlansComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RechargePlansComponent]
    });
    fixture = TestBed.createComponent(RechargePlansComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
