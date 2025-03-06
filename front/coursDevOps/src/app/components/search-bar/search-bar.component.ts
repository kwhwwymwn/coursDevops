import { Component, EventEmitter, model, ModelSignal, Output } from '@angular/core';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-search-bar',
  standalone: true,
  imports: [ReactiveFormsModule, MatIconModule],
  templateUrl: './search-bar.component.html',
  styleUrl: './search-bar.component.scss'
})
export class SearchBarComponent {
  protected searchForm: FormControl = new FormControl();

  @Output() searchValue = new EventEmitter<string>();

  test: ModelSignal<string> = model('hello');

  protected onKeydown(event: any){
    event.key == 'Enter' && this.searchValue.emit(this.searchForm.value);
  }

  protected onBtnClick(){
    this.searchValue.emit(this.searchForm.value);
  }

  NgOnInit(){

  }
}
