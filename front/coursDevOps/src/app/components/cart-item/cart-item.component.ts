import { Component, Input, model, ModelSignal } from '@angular/core';
import { Product } from '../../models/product';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss'
})
export class CartItemComponent {
  @Input() product!: Product;

  removeProductSignal: ModelSignal<any> = model(this.product)

  onRemoveFromCartClick() {
    this.removeProductSignal.set(this.product);
  }
}
