import { Component, input, InputSignal } from '@angular/core';

@Component({
  selector: 'app-product-card',
  standalone: true,
  imports: [],
  templateUrl: './product-card.component.html',
  styleUrl: './product-card.component.scss'
})
export class ProductCardComponent {
  protected productName: InputSignal<string> = input<string>('');
  protected productBasePrice: InputSignal<number> = input<number>(0);
  protected productTva: InputSignal<number> = input<number>(0);

  
}
