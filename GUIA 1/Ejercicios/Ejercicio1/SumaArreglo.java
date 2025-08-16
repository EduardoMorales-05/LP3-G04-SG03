/* Python 
def sumar_arreglo(arr):
    return sum(arr)

arreglo = [1, 2, 3, 4, 5]
print(sumar_arreglo(arreglo))  # Salida: 15
*/

/* C++ 
#include <iostream>
#include <vector>

int sumar_arreglo(std::vector<int> arr) {
    int suma = 0;
    for (int num : arr) {
        suma += num;
    }
    return suma;
}

int main() {
    std::vector<int> arreglo = {1, 2, 3, 4, 5};
    std::cout << sumar_arreglo(arreglo);  // Salida: 15
    return 0;
}
*/

/* Java */
public class SumaArreglo {
    public static int sumarArreglo(int[] arr) {
        int suma = 0;
        for (int num : arr) {
            suma += num;
        }
        return suma;
    }

    public static void main(String[] args) {
        int[] arreglo = {1, 2, 3, 4, 5};
        System.out.println(sumarArreglo(arreglo));  // Salida: 15
    }
}
