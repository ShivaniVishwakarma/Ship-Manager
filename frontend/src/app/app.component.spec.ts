import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { stringify } from '@angular/compiler/src/util';
import { TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './ship/menu/menu.component';
import { ShipAddComponent } from './ship/components/ship-add/ship-add.component';
import { ShipEditComponent } from './ship/components/ship-edit/ship-edit.component';
import { ShipListComponent } from './ship/components/ship-list/ship-list.component';
import { ShipService } from './ship/services/ship.service';

describe('AppComponent', () => {

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [
        RouterTestingModule,
        AppRoutingModule,
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        () => import('./ship/ship.module').then(m => m.ShipModule)
      ],
      declarations: [
        AppComponent,
        MenuComponent,
        ShipListComponent,
        ShipAddComponent,
        ShipEditComponent
      ],
      providers: [ShipService]
    }).compileComponents();
  });

  it('should create the app', () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app).toBeTruthy();
  });

  it(`should have as title 'Ship Manager'`, () => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.componentInstance;
    expect(app.title).toEqual('Ship Manager');
  });

  it('should render title', () => {
    const fixture = TestBed.createComponent(AppComponent);
    fixture.detectChanges();
    const compiled = fixture.nativeElement;
    expect(compiled.querySelector('.navbar div h1').textContent).toContain('Ship Manager');
  });
});
