export class PatientAssessDto {
  public firstName: string;
  public lastName: string;
  public age: number;
  public riskLevel: string;

  constructor(
    firstName: string,
    lastName: string,
    age: number,
    riskLevel: string
  ) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.age = age;
    this.riskLevel = riskLevel;
  }
}
