import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewTutorialMstComponent } from './view-tutorial-mst.component';

describe('ViewTutorialMstComponent', () => {
  let component: ViewTutorialMstComponent;
  let fixture: ComponentFixture<ViewTutorialMstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewTutorialMstComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewTutorialMstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
