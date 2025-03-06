import { Component } from '@angular/core';
import { CatalogueComponent } from "../../components/catalogue/catalogue.component";
import { CartComponent } from "../../components/cart/cart.component";
import { UserType } from '../../models/enum/user-type';
import { NewProductComponent } from "../../components/new-product/new-product.component";
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-main-page',
  standalone: true,
  imports: [CatalogueComponent, CartComponent, NewProductComponent],
  templateUrl: './main-page.component.html',
  styleUrl: './main-page.component.scss'
})
export class MainPageComponent {
  userType: UserType = UserType.ADMIN;
  UserType = UserType;

  constructor(private route: ActivatedRoute){
    this.route.queryParamMap.subscribe(value => {
      if(value.has('userType')){
        this.userType = Object.values(UserType)[<any>value.get('userType')];
      } else {
        this.userType = UserType.VISITOR;
      }
    })
  }
}
