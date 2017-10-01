export class Composer {

  constructor(
    public id: number = 0,
    public name: string = '',
    public birthYear: number = 0) {
  }

  equals(other: Composer) {
    return this.name === other.name
      && this.birthYear === other.birthYear;
  }

  static deepCopy(from: Composer) : Composer {
    return new Composer(from.id, from.name, from.birthYear);
  }
}
