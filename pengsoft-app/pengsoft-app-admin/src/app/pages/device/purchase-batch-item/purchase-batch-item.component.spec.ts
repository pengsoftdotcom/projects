import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PurchaseBatchItemComponent } from './purchase-batch-item.component';

describe('PurchaseBatchItemComponent', () => {
  let component: PurchaseBatchItemComponent;
  let fixture: ComponentFixture<PurchaseBatchItemComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PurchaseBatchItemComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PurchaseBatchItemComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
