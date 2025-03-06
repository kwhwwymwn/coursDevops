import { Component, Input, model, ModelSignal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatIconModule, CommonModule],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
  @Input() product!: Product;

  addProductSignal: ModelSignal<any> = model(this.product)

  onAddToCartClick() {
    this.addProductSignal.set(this.product);
  }
}
