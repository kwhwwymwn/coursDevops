import { Component, model, ModelSignal } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';
import { Product } from '../../models/product';

@Component({
  selector: 'app-new-product',
  standalone: true,
  imports: [MatIconModule, ReactiveFormsModule],
  templateUrl: './new-product.component.html',
  styleUrl: './new-product.component.scss'
})
export class NewProductComponent {
  createProductSignal: ModelSignal<any> = model()

  newProductForm: FormGroup<{
    name: FormControl<string | null>,
    price: FormControl<number | null>,
    tva: FormControl<number | null>,
    image: FormControl<string | null>
  }> = new FormGroup({
    name: new FormControl<string | null>(''),
    price: new FormControl<number | null>(null),
    tva: new FormControl<number | null>(null),
    image: new FormControl<string | null>('')
  });

  errorMessage: string = '';

  onSubmit() {
    if (this.newProductForm.controls.price.value && this.newProductForm.controls.name.value
      && this.newProductForm.controls.tva.value && this.newProductForm.controls.image.value) {
      let newProduct: Product = new Product(this.newProductForm.controls.price.value, this.newProductForm.controls.tva.value,
        this.newProductForm.controls.name.value, this.newProductForm.controls.image.value);

        this.createProductSignal.set(newProduct);
        this.newProductForm = new FormGroup({
          name: new FormControl<string | null>(''),
          price: new FormControl<number | null>(null),
          tva: new FormControl<number | null>(null),
          image: new FormControl<string | null>('')
        })
      this.errorMessage = '';
    } else {
      this.errorMessage = "Veuillez remplir tous les champs"
    }
  }
}
