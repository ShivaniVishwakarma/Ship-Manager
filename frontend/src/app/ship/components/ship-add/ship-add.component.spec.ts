import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShipAddComponent } from './ship-add.component';

describe('ShipAddComponent', () => {
  let component: ShipAddComponent;
  let fixture: ComponentFixture<ShipAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ShipAddComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ShipAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
