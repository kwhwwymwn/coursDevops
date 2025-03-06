import { Component, Input, model, ModelSignal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { UserType } from '../../models/enum/user-type';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatIconModule, CommonModule],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
  userType: UserType = UserType.ADMIN;
  UserType = UserType;
  @Input() product!: Product;

  addProductSignal: ModelSignal<any> = model(this.product)
  removeProductSignal: ModelSignal<any> = model(this.product)

  onAddToCartClick() {
    this.addProductSignal.set(this.product);
  }

  onRemoveFromCatalogueClick() {
    this.removeProductSignal.set(this.product);
  }
}
