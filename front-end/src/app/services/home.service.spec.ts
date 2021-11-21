import { TestBed } from '@angular/core/testing';

import { HomeCompanyService } from './home.service';

describe('HomeCompanyService', () => {
  let service: HomeCompanyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(HomeCompanyService);
  });

  it('Should be created', () => {
    expect(service).toBeTruthy();
  });
});
