import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaveTutorialMstComponent } from './save-tutorial-mst.component';

describe('SaveTutorialMstComponent', () => {
  let component: SaveTutorialMstComponent;
  let fixture: ComponentFixture<SaveTutorialMstComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [SaveTutorialMstComponent],
    }).compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SaveTutorialMstComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
