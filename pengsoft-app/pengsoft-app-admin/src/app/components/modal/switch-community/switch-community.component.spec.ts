import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SwitchCommunityComponent } from './switch-community.component';

describe('SwitchCommunityComponent', () => {
    let component: SwitchCommunityComponent;
    let fixture: ComponentFixture<SwitchCommunityComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [SwitchCommunityComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SwitchCommunityComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
