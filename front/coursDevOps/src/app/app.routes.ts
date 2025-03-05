import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ProductsManagerPageComponent } from './pages/products-manager-page/products-manager-page.component';

export const routes: Routes = [
    { path: 'login', component: LoginPageComponent },
    { path: 'products', component: ProductsManagerPageComponent },
];
