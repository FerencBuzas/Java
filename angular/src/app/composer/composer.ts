export class Composer {

  constructor(
    public id: Number = 0,
    public name: String = '',
    public birthYear: Number = 0) {
  }

  equals(other: Composer) {
    return this.name === other.name
      && this.birthYear === other.birthYear;
  }

  static deepCopy(from: Composer) : Composer {
    return new Composer(from.id, from.name, from.birthYear);
  }
}
