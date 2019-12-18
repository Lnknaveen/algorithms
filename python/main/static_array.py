class StaticArray:
    def __init__(self, numbers):
        self.numbers = numbers

    def find(self, value):
        returnValue = False

        for item in self.numbers:
            if item == value:
                returnValue = True
                break

        return returnValue

    def print(self):
        for index, value in enumerate(self.numbers, start=0):
            print(f"numbers[{index}]={value}")
