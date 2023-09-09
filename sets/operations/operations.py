class Set:
    def __init__(self, elements=[]):
        self.elements = {}
        for element in elements:
            self.elements[element] = True

    def union(self, other_set):
        result_elements = Set()
        for element in self.elements:
            result_elements.add(element)
        for element in other_set.elements:
            result_elements.add(element)
        return result_elements

    def intersection(self, other_set):
        result_elements = Set()
        for element in self.elements:
            if element in other_set.elements:
                result_elements.add(element)
        return result_elements

    def complement(self, universal_set):
        result_elements = Set()
        for element in universal_set.elements:
            if element not in self.elements:
                result_elements.add(element)
        return result_elements

    def difference(self, other_set):
        result_elements = Set()
        for element in self.elements:
            if element not in other_set.elements:
                result_elements.add(element)
        return result_elements

    def symmetric_difference(self, other_set):
        result_elements = Set()
        for element in self.elements:
            if element not in other_set.elements:
                result_elements.add(element)
        for element in other_set.elements:
            if element not in self.elements:
                result_elements.add(element)
        return result_elements

    def is_subset(self, other_set):
        for element in self.elements:
            if element not in other_set.elements:
                return False
        return True

    def is_proper_subset(self, other_set):
        return self.is_subset(other_set) and len(self.elements) < len(other_set.elements)

    def is_superset(self, other_set):
        for element in other_set.elements:
            if element not in self.elements:
                return False
        return True

    def is_proper_superset(self, other_set):
        return other_set.is_proper_subset(self)

    def add(self, element):
        self.elements[element] = True

    def remove(self, element):
        if element in self.elements:
            del self.elements[element]

    def __str__(self):
        return str(list(self.elements.keys()))
