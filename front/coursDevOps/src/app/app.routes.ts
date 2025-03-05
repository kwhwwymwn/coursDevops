import { Routes } from '@angular/router';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ProductsManagerPageComponent } from './pages/products-manager-page/products-manager-page.component';
import { MainPageComponent } from './pages/main-page/main-page.component';

export const routes: Routes = [
    { path: '', component: MainPageComponent },
    { path: 'login', component: LoginPageComponent },
    { path: 'products', component: ProductsManagerPageComponent },
];
