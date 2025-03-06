import { Component, effect, Input, model, ModelSignal } from '@angular/core';
import { SearchBarComponent } from "../search-bar/search-bar.component";
import { ProductCardComponent } from "../product-card/product-card.component";
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';
import { UserType } from '../../models/enum/user-type';


@Component({
  selector: 'app-catalogue',
  standalone: true,
  imports: [SearchBarComponent, ProductCardComponent, CommonModule],
  templateUrl: './catalogue.component.html',
  styleUrl: './catalogue.component.scss'
})
export class CatalogueComponent {
  products: Product[] = [
    new Product(19.99, 0.2, "Graine de Lune", "https://www.airzen.fr/wp-content/uploads/2022/07/AdobeStock_117893212-scaled.jpeg")
  ];

  @Input() userType!: UserType;

  addProductSignal: ModelSignal<any> = model()
  removeProductSignal: ModelSignal<any> = model()
  createProductSignal: ModelSignal<any> = model()

  constructor() {
    effect(() => {
      const productToAdd = this.createProductSignal();
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
