import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseBatchComponent } from './purchase-batch.component';

describe('PurchaseBatchComponent', () => {
    let component: PurchaseBatchComponent;
    let fixture: ComponentFixture<PurchaseBatchComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [PurchaseBatchComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(PurchaseBatchComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
