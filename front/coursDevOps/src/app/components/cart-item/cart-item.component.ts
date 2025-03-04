import { Component, input, InputSignal } from '@angular/core';

@Component({
  selector: 'app-cart-item',
  standalone: true,
  imports: [],
  templateUrl: './cart-item.component.html',
  styleUrl: './cart-item.component.scss'
})
export class CartItemComponent {
  productName: InputSignal<string> = input<string>('Nom');
  productBasePrice: InputSignal<number> = input<number>(0);
  productTva: InputSignal<number> = input<number>(0);
}
