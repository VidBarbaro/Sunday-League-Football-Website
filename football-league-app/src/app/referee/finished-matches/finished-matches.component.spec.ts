import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinishedMatchesComponent } from './finished-matches.component';

describe('FinishedMatchesComponent', () => {
  let component: FinishedMatchesComponent;
  let fixture: ComponentFixture<FinishedMatchesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FinishedMatchesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FinishedMatchesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
