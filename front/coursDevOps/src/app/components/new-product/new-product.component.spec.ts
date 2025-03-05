import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductsManagerPageComponent } from './products-manager-page.component';

describe('ProductsManagerPageComponent', () => {
  let component: ProductsManagerPageComponent;
  let fixture: ComponentFixture<ProductsManagerPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductsManagerPageComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductsManagerPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
