import { TestBed } from '@angular/core/testing';

import { PurchaseBatchItemService } from './purchase-batch-item.service';

describe('PurchaseBatchItemService', () => {
  let service: PurchaseBatchItemService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurchaseBatchItemService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
