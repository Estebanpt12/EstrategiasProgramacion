# Datos del problema
valores = [2, 5, 10, 14, 15]
pesos = [1, 3, 4, 5, 7]
capacidad_mochila = 8
n = len(valores)

# Inicialización de la tabla dp con ceros
dp = [[0 for _ in range(capacidad_mochila + 1)] for _ in range(n + 1)]

# Construcción de la tabla dp
for i in range(1, n + 1):
    for j in range(1, capacidad_mochila + 1):
        if pesos[i - 1] <= j:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - pesos[i - 1]] + valores[i - 1])
        else:
            dp[i][j] = dp[i - 1][j]

# Valor máximo que se puede obtener con el peso máximo de la mochila
valor_maximo = dp[n][capacidad_mochila]
print("Valor máximo posible:", valor_maximo)

# Para encontrar los elementos incluidos
elementos_incluidos = []
j = capacidad_mochila
for i in range(n, 0, -1):
    if dp[i][j] != dp[i - 1][j]:
        elementos_incluidos.append(i - 1)  # Añadimos el índice del elemento
        j -= pesos[i - 1]

elementos_incluidos.reverse()
print("Elementos incluidos en la mochila:", elementos_incluidos)

# Imprimir la tabla dp
print("\nTabla dp:")
for row in dp:
    print(row)
