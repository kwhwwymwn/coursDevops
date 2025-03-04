export class Product {
    private _id!: number;
    private _basePrice!: number;
    private _tva!: number;
    private _name!: string;
    private _imgUrl!: string;

    constructor(id: number, basePrice: number, tva: number, name: string, imgUrl: string){
        this._id = id;
        this._basePrice = basePrice;
        this._tva = tva;
        this._name = name;
        this._imgUrl = imgUrl;
    }

    public get id(): number {
        return this._id;
    }
    public set id(value: number) {
        this._id = value;
    }

    public get basePrice(): number {
        return this._basePrice;
    }
    public set basePrice(value: number) {
        this._basePrice = value;
    }

    public get tva(): number {
        return this._tva;
    }
    public set tva(value: number) {
        this._tva = value;
    }

    public get name(): string {
        return this._name;
    }
    public set name(value: string) {
        this._name = value;
    }

    public get imgUrl(): string {
        return this._imgUrl;
    }
    public set imgUrl(value: string) {
        this._imgUrl = value;
    }
}
