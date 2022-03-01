import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ExampleCategoryComponent } from './example-category.component';

describe('ExampleCategoryComponent', () => {
  let component: ExampleCategoryComponent;
  let fixture: ComponentFixture<ExampleCategoryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ExampleCategoryComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ExampleCategoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
