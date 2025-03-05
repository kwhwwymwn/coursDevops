import { Component } from '@angular/core';
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
  products: Product[] = [
    new Product(199.99, 5, "Money Tree", "https://example.com/money-tree.jpg"),
    new Product(149.49, 5, "Popcorn Seed", "https://example.com/popcorn-seed.jpg"),
    new Product(259.99, 5, "Smartphone Tree", "https://example.com/smartphone-tree.jpg"),
    new Product(129.99, 5, "Candy Vine", "https://example.com/candy-vine.jpg"),
    new Product(187.75, 5, "Pizza Shrub", "https://example.com/pizza-shrub.jpg"),
    new Product(300.00, 5, "Golden Leaf Plant", "https://example.com/golden-leaf.jpg"),
    new Product(142.20, 5, "Glow-in-the-Dark Cactus", "https://example.com/glow-cactus.jpg"),
    new Product(225.50, 5, "Chocolate Blossom", "https://example.com/chocolate-blossom.jpg"),
    new Product(279.99, 5, "WiFi Bush", "https://example.com/wifi-bush.jpg"),
    new Product(109.99, 5, "Bubblegum Tree", "https://example.com/bubblegum-tree.jpg"),
    new Product(350.00, 5, "Singing Sunflower", "https://example.com/singing-sunflower.jpg"),
    new Product(174.49, 5, "Cloud Berry", "https://example.com/cloud-berry.jpg")
  ];
}
