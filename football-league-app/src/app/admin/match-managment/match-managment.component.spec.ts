import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MatchManagmentComponent } from './match-managment.component';

describe('MatchManagmentComponent', () => {
  let component: MatchManagmentComponent;
  let fixture: ComponentFixture<MatchManagmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MatchManagmentComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MatchManagmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
