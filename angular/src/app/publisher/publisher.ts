export class Publisher {

  constructor(
    public id: Number = 0,
    public name: String = '') {
  }
  
  equals(other: Publisher): boolean {
    return this.name === other.name;
  }
}
