import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetNewSimComponent } from './get-new-sim.component';

describe('GetNewSimComponent', () => {
  let component: GetNewSimComponent;
  let fixture: ComponentFixture<GetNewSimComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GetNewSimComponent]
    });
    fixture = TestBed.createComponent(GetNewSimComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
