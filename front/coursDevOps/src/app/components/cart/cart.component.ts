import { Component } from '@angular/core';
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
  products: Product[] = [
    new Product(2, 149.49, 5, "Popcorn Seed", "https://example.com/popcorn-seed.jpg"),
    new Product(2, 149.49, 5, "Popcorn Seed", "https://example.com/popcorn-seed.jpg"),
    new Product(3, 259.99, 5, "Smartphone Tree", "https://example.com/smartphone-tree.jpg"),
    new Product(5, 187.75, 5, "Pizza Shrub", "https://example.com/pizza-shrub.jpg"),
    new Product(6, 300.00, 5, "Golden Leaf Plant", "https://example.com/golden-leaf.jpg"),
    new Product(6, 300.00, 5, "Golden Leaf Plant", "https://example.com/golden-leaf.jpg"),
    new Product(6, 300.00, 5, "Golden Leaf Plant", "https://example.com/golden-leaf.jpg"),
    new Product(12, 174.49, 5, "Cloud Berry", "https://example.com/cloud-berry.jpg")
  ];
}
