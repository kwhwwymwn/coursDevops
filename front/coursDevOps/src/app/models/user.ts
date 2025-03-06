import { UserType } from "./enum/user-type";

export class User {
    private _login!: string
    private _password!: string;
    private _role!: UserType;

    constructor(login: string, password: string, role: UserType){
        this._login = login;
        this._password = password;
        this._role = role;
    }

    public get login(): string {
        return this._login;
    }
    public get password(): string {
        return this._password;
    }
    public get role(): UserType {
        return this._role;
    }
}
