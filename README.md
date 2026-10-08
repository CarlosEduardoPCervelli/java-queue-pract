# Fila de Atendimento de Banco

Simula a fila de um banco com **senhas normais** (`N001`, `N002`...) e
**prioritárias** (`P001`, `P002`...), usando `Queue` (implementada com
`LinkedList`). Menu interativo no terminal.

## Regra de chamada

A cada **duas senhas normais** chamadas, a próxima chamada é uma
**prioritária** (se houver alguma esperando). Casos especiais:

- Se não houver prioritária esperando, continua chamando normais.
- Se não houver normais esperando, chama as prioritárias que houver.

O programa controla isso com o contador `normaisSeguidas`: ele sobe a cada
normal chamada e volta a zero quando uma prioritária é chamada.

## Menu

```
1. Gerar senha normal
2. Gerar senha prioritária
3. Chamar próxima senha
4. Ver filas
5. Sair
```

## Por que Queue?

Uma fila é "primeiro a entrar, primeiro a sair" (FIFO), exatamente como a
fila real: `add()` coloca no final e `poll()` tira do início. Ter uma fila
separada para cada tipo de senha deixa a regra do "2 normais para 1
prioritária" simples de implementar.

## Como executar

```bash
cd src
javac FilaBanco.java
java FilaBanco
```

## Possíveis melhorias futuras
- Guardar o horário em que cada senha foi gerada.
- Mostrar em qual guichê a senha foi chamada.
