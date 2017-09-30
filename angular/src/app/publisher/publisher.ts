export class Publisher {

  constructor(
    public id: Number = 0,
    public name: String = '') {
  }
  public equals(other: Publisher): boolean {
    return this.name === other.name;
  }
  
  static deepCopy(from: Publisher) : Publisher {
    return new Publisher(from.id, from.name);
  }
}
