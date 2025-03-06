import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { User } from '../../models/user';
import { UserType } from '../../models/enum/user-type';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login-page',
  standalone: true,
  imports: [MatIconModule, ReactiveFormsModule],
  templateUrl: './login-page.component.html',
  styleUrl: './login-page.component.scss'
})
export class LoginPageComponent {
  protected isPasswordVisible: boolean = false;
  protected errorMessage: string = '';
  
  protected loginForm: FormGroup<{
    login: FormControl<string | null>,
    password: FormControl<string | null>
  }> = new FormGroup({
    login: new FormControl<string | null>(''),
    password: new FormControl<string | null>('')
  });

  private userList: User[] = [
    new User("customer", "1234", UserType.CUSTOMER),
    new User("admin", "admin", UserType.ADMIN)
  ]

  constructor(private router: Router){
  }

  protected togglePassword() {
    this.isPasswordVisible = !this.isPasswordVisible;
  }

  protected login(){
    if(this.loginForm.controls.login.value && this.loginForm.controls.password.value){
      let userToLog = this.userList.filter(value => value.login == this.loginForm.controls.login.value
        && value.password == this.loginForm.controls.password.value);

      if(userToLog.length){
        this.router.navigate(['/'], {queryParams: {userType: Object.keys(UserType).indexOf(userToLog[0].role)}});
      } else {
        this.errorMessage = 'Identifiant ou mot de passe incorrect';
      }
    } else {
      this.errorMessage = 'Veuillez remplir tous les champs';
    }
  }
}
