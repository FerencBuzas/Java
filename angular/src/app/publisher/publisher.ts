export class Publisher {

  constructor(
    public id: number = 0,
    public name: string = '') {
  }
  public equals(other: Publisher): boolean {
    return this.name === other.name;
  }
  
  static deepCopy(from: Publisher) : Publisher {
    return new Publisher(from.id, from.name);
  }
}
