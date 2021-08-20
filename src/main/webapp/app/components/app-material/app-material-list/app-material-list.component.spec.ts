import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppMaterialListComponent } from './app-material-list.component';

describe('AppMaterialListComponent', () => {
  let component: AppMaterialListComponent;
  let fixture: ComponentFixture<AppMaterialListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AppMaterialListComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppMaterialListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
