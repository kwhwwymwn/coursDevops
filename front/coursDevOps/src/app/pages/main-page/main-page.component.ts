import { Component } from '@angular/core';
import { CatalogueComponent } from "../../components/catalogue/catalogue.component";
import { CartComponent } from "../../components/cart/cart.component";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [CatalogueComponent, CartComponent],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent {

}
