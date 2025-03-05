import { Component } from '@angular/core';
import { CatalogueComponent } from "../../components/catalogue/catalogue.component";
import { CartComponent } from "../../components/cart/cart.component";
import { UserType } from '../../models/enum/user-type';
import { ProductsManagerPageComponent } from "../../components/new-product/new-product.component";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [CatalogueComponent, CartComponent, ProductsManagerPageComponent],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent {
  userType: UserType = UserType.CUSTOMER;
  UserType = UserType;
}
