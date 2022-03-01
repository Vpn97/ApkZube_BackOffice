import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddExampleComponent } from './add-example.component';

describe('AddExampleComponent', () => {
  let component: AddExampleComponent;
  let fixture: ComponentFixture<AddExampleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AddExampleComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddExampleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
