import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMyTeamComponent } from './create-my-team.component';

describe('CreateMyTeamComponent', () => {
  let component: CreateMyTeamComponent;
  let fixture: ComponentFixture<CreateMyTeamComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMyTeamComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMyTeamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
