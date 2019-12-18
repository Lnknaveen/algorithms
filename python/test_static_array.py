import pytest

from main.static_array import StaticArray

testdata = [
    ([1, 2, 3, 4], 2, True),
    ([1, 2, 3, 4], 5, False),
]
@pytest.mark.parametrize("numbers, value, expected", testdata)
def test_static_array(numbers, value, expected):
    array = StaticArray([1, 2, 3, 4])
    assert array.find(value) == expected


def test_print_elements_with_indexes():
    array = StaticArray([1, 2, 3, 4])
    array.print()
