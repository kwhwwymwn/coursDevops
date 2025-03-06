import { Component, effect, model, ModelSignal, OnInit } from '@angular/core';
import { CartItemComponent } from "../cart-item/cart-item.component";
import { MatIconModule } from '@angular/material/icon';
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [CartItemComponent, MatIconModule, CommonModule],
  templateUrl: './cart.component.html',
  styleUrl: './cart.component.scss'
})
export class CartComponent {
  products: Product[] = [];

  addProductSignal: ModelSignal<any> = model()
  removeProductSignal: ModelSignal<any> = model()

  constructor() {
    effect(() => {
      const productToAdd = this.addProductSignal();
      if (productToAdd) {
        this.products.push(productToAdd);
      }
    });

    effect(() => {
      const productToRemove = this.removeProductSignal();
      if (productToRemove) {
        const index = this.products.indexOf(productToRemove, 0);
        if (index > -1) {
          this.products.splice(index, 1);
        }        
      }
    })
  }
}
