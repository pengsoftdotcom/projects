import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SetMajorRoleComponent } from './set-major-role.component';

describe('SetMajorRoleComponent', () => {
  let component: SetMajorRoleComponent;
  let fixture: ComponentFixture<SetMajorRoleComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SetMajorRoleComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SetMajorRoleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
