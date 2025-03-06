import { Component, model, ModelSignal, OnInit } from '@angular/core';
import { CatalogueComponent } from "../../components/catalogue/catalogue.component";
import { CartComponent } from "../../components/cart/cart.component";
import { UserType } from '../../models/enum/user-type';
import { NewProductComponent } from "../../components/new-product/new-product.component";

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [CatalogueComponent, CartComponent, NewProductComponent],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent {
  userType: UserType = UserType.CUSTOMER;
  UserType = UserType;

  addProductSignal: ModelSignal<any> = model()
}
