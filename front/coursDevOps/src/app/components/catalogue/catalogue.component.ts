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
    new Product(19.99, 0.2, "Graine de Lune", "https://www.airzen.fr/wp-content/uploads/2022/07/AdobeStock_117893212-scaled.jpeg"),
    new Product(24.99, 0.4, "Huile de fleur", "https://www.promessedefleurs.com/blogwp/wp-content/uploads/2022/05/huile-Photo-a-la-une.jpg"),
    new Product(11999.99, 4.2, "Arbre à billets", "https://lh4.googleusercontent.com/proxy/-L_CPGWPlNM8gtuslFIltMJPAI0ib67UP1_qrpaqyf16MtYvnedoOOS9PpB_YEA7k-c2nclmYGTNWz1xRO8T7ZyXAv7rJa0"),
  ];
  displayedProducts: Product[] = this.products;

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

  protected onSearch(event: string){
    this.displayedProducts = this.products.filter(value => value.name.toLowerCase().includes(event.toLowerCase()))
  }
}
