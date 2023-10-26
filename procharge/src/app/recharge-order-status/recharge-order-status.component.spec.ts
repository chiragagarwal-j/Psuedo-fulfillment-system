import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RechargeOrderStatusComponent } from './recharge-order-status.component';

describe('RechargeOrderStatusComponent', () => {
  let component: RechargeOrderStatusComponent;
  let fixture: ComponentFixture<RechargeOrderStatusComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [RechargeOrderStatusComponent]
    });
    fixture = TestBed.createComponent(RechargeOrderStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
