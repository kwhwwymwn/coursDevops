import { Component, input, InputSignal } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
  productName: InputSignal<string> = input<string>('Nom');
  productBasePrice: InputSignal<number> = input<number>(0);
  productTva: InputSignal<number> = input<number>(0);
}
