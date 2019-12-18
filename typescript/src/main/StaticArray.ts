export class StaticArray {
    private numbers: number[];

    constructor(numbers: number[]) {
        this.numbers = numbers;
    }

    public find(value: number): boolean {
        let found = false;

        for (let index = 0; index < this.numbers.length; index++) {
            if (value == this.numbers[index]) {
                found = true;
                break;
            }
        }

        return found;
    }

    public print() {
        this.numbers.forEach((item, index) => {
            console.log(`numbers[${index}=${item}]`);
        });
    }
}