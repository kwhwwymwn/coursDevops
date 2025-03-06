import { Component, effect, model, ModelSignal, OnInit } from '@angular/core';
import { SearchBarComponent } from "../search-bar/search-bar.component";
import { ProductCardComponent } from "../product-card/product-card.component";
import { Product } from '../../models/product';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-catalogue',
  standalone: true,
  imports: [SearchBarComponent, ProductCardComponent, CommonModule],
  templateUrl: './catalogue.component.html',
  styleUrl: './catalogue.component.scss'
})
export class CatalogueComponent {
  products: Product[] = [];

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
