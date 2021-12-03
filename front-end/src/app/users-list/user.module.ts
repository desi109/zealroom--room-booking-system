export class User {
  public id: number;
  public name: string;
  public email: string;
  public position: string


  constructor(id: number, name: string, email: string, position: string) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.position = position;
  }
}
