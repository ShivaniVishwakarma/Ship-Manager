import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListShipComponent } from './list-ship.component';

describe('ListShipComponent', () => {
  let component: ListShipComponent;
  let fixture: ComponentFixture<ListShipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListShipComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListShipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
