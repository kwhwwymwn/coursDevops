import { Component, Input} from '@angular/core';
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
}
