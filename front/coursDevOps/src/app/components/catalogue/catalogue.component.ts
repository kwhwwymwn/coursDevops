import { Component } from '@angular/core';
import { SearchBarComponent } from "../search-bar/search-bar.component";
import { CartItemComponent } from "../cart-item/cart-item.component";
import { ProductCardComponent } from "../product-card/product-card.component";

@Component({
  selector: 'app-catalogue',
  standalone: true,
  imports: [SearchBarComponent, ProductCardComponent],
  templateUrl: './catalogue.component.html',
  styleUrl: './catalogue.component.scss'
})
export class CatalogueComponent {

}
