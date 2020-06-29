import { TestBed } from '@angular/core/testing';

import { DeviceGroupService } from './device-group.service';

describe('DeviceGroupService', () => {
    let service: DeviceGroupService;

    beforeEach(() => {
        TestBed.configureTestingModule({});
        service = TestBed.inject(DeviceGroupService);
    });

    it('should be created', () => {
        expect(service).toBeTruthy();
    });
});
