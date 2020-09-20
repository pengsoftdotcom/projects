import { TestBed } from '@angular/core/testing';

import { PurchaseBatchService } from './purchase-batch.service';

describe('PurchaseBatchService', () => {
  let service: PurchaseBatchService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PurchaseBatchService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
