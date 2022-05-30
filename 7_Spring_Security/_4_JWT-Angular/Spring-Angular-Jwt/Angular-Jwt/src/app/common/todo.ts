export class Todo {
  constructor(
    public id?: number,
    public description?: string,
    public _isDone?: boolean,
    public targetDate?: Date
  ) {}
}
