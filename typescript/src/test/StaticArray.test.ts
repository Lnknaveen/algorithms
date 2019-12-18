import { StaticArray } from "../main/StaticArray";

describe('static array', () => {
    [
        { numbers: [10, 20, 30, 40], value: 10, expected: true },
        { numbers: [10, 20, 30, 40], value: 50, expected: false }
    ].forEach(({ numbers, value, expected }) => {
        test(`should find ${value} ${expected} in ${numbers}`, () => {
            let staticArray = new StaticArray(numbers);
            expect(staticArray.find(value)).toBe(expected);
        });
    });

    test('should print elements with indexes', () => {
        let staticArray = new StaticArray([10, 20, 30, 40]);
        staticArray.print();
    });
});